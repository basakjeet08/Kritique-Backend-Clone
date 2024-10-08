package dev.anirban.kritique.service;

import dev.anirban.kritique.dto.review.ReviewFacultyDTO;
import dev.anirban.kritique.dto.review.PostReviewRequest;
import dev.anirban.kritique.dto.review.ReviewDTO;
import dev.anirban.kritique.dto.review.ReviewHistoryDTO;
import dev.anirban.kritique.entity.Faculty;
import dev.anirban.kritique.entity.Review;
import dev.anirban.kritique.entity.User;
import dev.anirban.kritique.enums.Validation;
import dev.anirban.kritique.exception.*;
import dev.anirban.kritique.repository.FacultyRepository;
import dev.anirban.kritique.repository.ReviewRepository;
import dev.anirban.kritique.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserRepository userRepo;
    private final FacultyRepository facultyRepo;
    private final ReviewRepository reviewRepo;
    private final ProfanityService profanityService;

    public ReviewDTO createReview(PostReviewRequest review) {

        // Fetching user who created the Review
        User user = userRepo
                .findById(review.getCreatedBy())
                .orElseThrow(() -> new UserNotFound(review.getCreatedBy()));

        // Fetching Faculty for which the review is created
        Faculty faculty = facultyRepo
                .findById(review.getCreatedFor())
                .orElseThrow(() -> new FacultyNotFound(review.getCreatedFor()));

        // Check if the user has already reviewed this faculty
        if (checkIfAlreadyReviewed(user, faculty))
            throw new ReviewAlreadyExistsException();


        // Checking if the given review contains bad words or not.
        if (profanityService.containsProfanity(review.getFeedback()))
            throw new ProfanityFoundException();


        // Creating the Date Format
        String formattedDate = createFormatterDate();


        // Building the Review Object
        Review newReview = Review
                .builder()
                .rating(review.getRating())
                .feedback(review.getFeedback())
                .status(Validation.NOT_VALIDATED)
                .createdAt(formattedDate)
                .createdBy(user)
                .createdFor(faculty)
                .build();

        user.addReview(newReview);
        faculty.addReview(newReview);

        // Storing the new Review created
        return reviewRepo
                .save(newReview)
                .toReviewDTO();
    }

    private String createFormatterDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    private boolean checkIfAlreadyReviewed(User user, Faculty faculty) {
        return user
                .getReviewsGiven()
                .stream()
                .anyMatch(existingReview ->
                        existingReview
                                .getCreatedFor()
                                .getId()
                                .equals(faculty.getId())
                );
    }

    public List<ReviewDTO> findAllReviews(Pageable pageable) {
        return reviewRepo
                .findAll(pageable)
                .stream()
                .map(Review::toReviewDTO)
                .toList();
    }

    public ReviewDTO findReviewById(String id) {
        return reviewRepo
                .findById(id)
                .map(Review::toReviewDTO)
                .orElseThrow(() -> new ReviewNotFound(id));
    }

    public List<ReviewHistoryDTO> findReviewByUserId(String userId, Pageable pageable) {
        return reviewRepo
                .findByCreatedBy_Uid(userId, pageable)
                .stream()
                .map(Review::toReviewHistoryDTO)
                .toList();
    }

    public List<ReviewFacultyDTO> findReviewByFacultyId(String facultyId, Pageable pageable) {
        return reviewRepo
                .findByCreatedFor_Id(facultyId, pageable)
                .stream()
                .map(Review::toReviewFacultyDTO)
                .toList();
    }

    public void deleteReview(String id) {

        // Fetching the review to be deleted
        Review review = reviewRepo
                .findById(id)
                .orElseThrow(() -> new ReviewNotFound(id));

        // Fetching the faculty for which the review is given
        Faculty faculty = facultyRepo
                .findById(review.getCreatedFor().getId())
                .orElseThrow(() -> new FacultyNotFound(review.getCreatedFor().getId()));

        // Fetching the user who gave the review
        User user = userRepo
                .findById(review.getCreatedBy().getUid())
                .orElseThrow(() -> new UserNotFound(review.getCreatedBy().getUid()));

        faculty.removeReview(review);
        user.removeReview(review);

        // Deleting the old Review
        reviewRepo.deleteById(id);
    }
}

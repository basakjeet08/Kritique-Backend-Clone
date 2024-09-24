package dev.anirban.kritique.service;

import dev.anirban.kritique.dto.ReviewPostRequest;
import dev.anirban.kritique.entity.Faculty;
import dev.anirban.kritique.entity.Review;
import dev.anirban.kritique.entity.User;
import dev.anirban.kritique.enums.Validation;
import dev.anirban.kritique.exception.FacultyNotFound;
import dev.anirban.kritique.exception.ReviewNotFound;
import dev.anirban.kritique.exception.UserNotFound;
import dev.anirban.kritique.repository.FacultyRepository;
import dev.anirban.kritique.repository.ReviewRepository;
import dev.anirban.kritique.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserRepository userRepo;
    private final FacultyRepository facultyRepo;
    private final ReviewRepository reviewRepo;

    public Review createReview(ReviewPostRequest review) {

        // Fetching user who created the Review
        User user = userRepo
                .findById(review.getCreatedById())
                .orElseThrow(() -> new UserNotFound(review.getCreatedById()));

        // Fetching Faculty for which the review is created
        Faculty faculty = facultyRepo
                .findById(review.getCreatedForId())
                .orElseThrow(() -> new FacultyNotFound(review.getCreatedForId()));

        // Building the Review Object
        Review newReview = Review
                .builder()
                .rating(review.getRating())
                .feedback(review.getFeedback())
                .status(Validation.NOT_VALIDATED)
                .createdBy(user)
                .createdFor(faculty)
                .build();


        // Calculating the new Avg for the teacher
        Double prevTotal = faculty.getAvgRating() * faculty.getReviewList().size();
        Double newAvg = (prevTotal + newReview.getRating()) / (faculty.getReviewList().size() + 1);

        // Updating the Faculty Avg , User reviewsGiven and faculty reviewList
        faculty.setAvgRating(newAvg);
        user.addReview(newReview);
        faculty.addReview(newReview);

        // Storing the new Review created
        return reviewRepo.save(newReview);
    }

    public List<Review> findAllReviews() {
        return reviewRepo.findAll();
    }

    public Review findReviewById(Integer id) {
        return reviewRepo
                .findById(id)
                .orElseThrow(() -> new ReviewNotFound(id));
    }

    public List<Review> findReviewByUserId(String userId) {
        return reviewRepo.findReviewByUserId(userId);
    }

    public List<Review> findReviewByFacultyId(String facultyId) {
        return reviewRepo.findReviewByFacultyId(facultyId);
    }

    public void deleteReview(Integer id) {

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

        // Calculating the new Review
        Double prevTotal = faculty.getAvgRating() * faculty.getReviewList().size();

        double newAvg = 0D;

        // Checking if all the reviews are removed or not
        if (faculty.getReviewList().size() - 1 != 0)
            newAvg = (prevTotal - review.getRating()) / (faculty.getReviewList().size() - 1);

        // Updating the Faculty Avg , User reviewsGiven and faculty reviewList
        faculty.setAvgRating(newAvg);
        faculty.removeReview(review);
        user.removeReview(review);

        // Deleting the old Review
        reviewRepo.deleteById(id);
    }
}

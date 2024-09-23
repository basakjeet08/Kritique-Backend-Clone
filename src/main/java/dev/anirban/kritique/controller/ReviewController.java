package dev.anirban.kritique.controller;

import dev.anirban.kritique.constants.UrlConstants;
import dev.anirban.kritique.dto.CustomResponse;
import dev.anirban.kritique.dto.ReviewPostRequest;
import dev.anirban.kritique.entity.Review;
import dev.anirban.kritique.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @PostMapping(UrlConstants.CREATE_REVIEWS)
    public CustomResponse<Review> createReviewHandler(@RequestBody ReviewPostRequest review) {
        return new CustomResponse<>(
                HttpStatus.CREATED.value(),
                "Review Created successfully",
                service.createReview(review)
        );
    }

    @GetMapping(UrlConstants.FIND_ALL_REVIEWS)
    public CustomResponse<List<Review>> findAllReviewsHandler() {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "All reviews fetched successfully",
                service.findAllReviews()
        );
    }

    @GetMapping(UrlConstants.FIND_REVIEW_BY_ID)
    public CustomResponse<Review> findReviewById(@PathVariable Integer id) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Review fetched successfully",
                service.findReviewById(id)
        );
    }

    @GetMapping(UrlConstants.FIND_REVIEW_BY_USER_ID)
    public CustomResponse<List<Review>> findReviewByUserIdHandler(@PathVariable Integer userId) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Reviews fetched successfully",
                service.findReviewByUserId(userId)
        );
    }

    @GetMapping(UrlConstants.FIND_REVIEW_BY_FACULTY_ID)
    public CustomResponse<List<Review>> findReviewByFacultyIdHandler(@PathVariable Integer facultyId) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Reviews Fetched Successfully",
                service.findReviewByFacultyId(facultyId)
        );
    }


    @DeleteMapping(UrlConstants.DELETE_REVIEW)
    public CustomResponse<Void> deleteReviewHandler(@PathVariable Integer id) {
        service.deleteReview(id);
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Review Deleted Successfully",
                null
        );
    }
}

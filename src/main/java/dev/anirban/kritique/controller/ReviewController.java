package dev.anirban.kritique.controller;

import dev.anirban.kritique.constants.UrlConstants;
import dev.anirban.kritique.dto.CustomResponse;
import dev.anirban.kritique.dto.review.PostReviewRequest;
import dev.anirban.kritique.dto.review.ReviewDTO;
import dev.anirban.kritique.dto.review.ReviewHistoryDTO;
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
    public CustomResponse<ReviewDTO> createReviewHandler(@RequestBody PostReviewRequest review) {
        return new CustomResponse<>(
                HttpStatus.CREATED.value(),
                "Review Created successfully",
                service.createReview(review)
        );
    }

    @GetMapping(UrlConstants.FIND_ALL_REVIEWS)
    public CustomResponse<List<ReviewDTO>> findAllReviewsHandler() {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "All reviews fetched successfully",
                service.findAllReviews()
        );
    }

    @GetMapping(UrlConstants.FIND_REVIEW_BY_ID)
    public CustomResponse<ReviewDTO> findReviewById(@PathVariable String id) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Review fetched successfully",
                service.findReviewById(id)
        );
    }

    @GetMapping(UrlConstants.FIND_REVIEW_BY_USER_ID)
    public CustomResponse<List<ReviewHistoryDTO>> findReviewByUserIdHandler(@PathVariable String userId) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Reviews fetched successfully",
                service.findReviewByUserId(userId)
        );
    }

    @GetMapping(UrlConstants.FIND_REVIEW_BY_FACULTY_ID)
    public CustomResponse<List<ReviewDTO>> findReviewByFacultyIdHandler(@PathVariable String facultyId) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Reviews Fetched Successfully",
                service.findReviewByFacultyId(facultyId)
        );
    }


    @DeleteMapping(UrlConstants.DELETE_REVIEW)
    public CustomResponse<Void> deleteReviewHandler(@PathVariable String id) {
        service.deleteReview(id);
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Review Deleted Successfully",
                null
        );
    }
}

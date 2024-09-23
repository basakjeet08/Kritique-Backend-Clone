package dev.anirban.kritique.controller;

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

    @PostMapping("/reviews")
    public CustomResponse<Review> createReviewHandler(@RequestBody ReviewPostRequest review) {
        return new CustomResponse<>(
                HttpStatus.CREATED.value(),
                "Review Created successfully",
                service.createReview(review)
        );
    }

    @GetMapping("/reviews")
    public CustomResponse<List<Review>> findAllReviewsHandler() {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "All reviews fetched successfully",
                service.findAllReviews()
        );
    }

    @GetMapping("/reviews/{id}")
    public CustomResponse<Review> findReviewById(@PathVariable Integer id) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Review fetched successfully",
                service.findReviewById(id)
        );
    }

    @GetMapping("/reviews/{id}/history")
    public CustomResponse<List<Review>> findReviewByUserIdHandler(@PathVariable Integer id) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Reviews fetched successfully",
                service.findReviewByUserId(id)
        );
    }

    @GetMapping("/reviews/faculty/{id}")
    public CustomResponse<List<Review>> findReviewByFacultyIdHandler(@PathVariable Integer id) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Reviews Fetched Successfully",
                service.findReviewByFacultyId(id)
        );
    }


    @DeleteMapping("/reviews/{id}")
    public CustomResponse<Void> deleteReviewHandler(@PathVariable Integer id) {
        service.deleteReview(id);
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Review Deleted Successfully",
                null
        );
    }
}

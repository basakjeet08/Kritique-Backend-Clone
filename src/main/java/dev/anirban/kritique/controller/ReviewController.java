package dev.anirban.kritique.controller;

import dev.anirban.kritique.constants.NetworkStatusCodes;
import dev.anirban.kritique.constants.UrlConstants;
import dev.anirban.kritique.dto.common.CustomResponse;
import dev.anirban.kritique.dto.common.EmptyObject;
import dev.anirban.kritique.dto.review.ReviewFacultyDTO;
import dev.anirban.kritique.dto.review.PostReviewRequest;
import dev.anirban.kritique.dto.review.ReviewDTO;
import dev.anirban.kritique.dto.review.ReviewHistoryDTO;
import dev.anirban.kritique.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @PostMapping(UrlConstants.CREATE_REVIEWS)
    public CustomResponse<ReviewDTO> createReviewHandler(@RequestBody PostReviewRequest review) {
        return new CustomResponse<>(
                NetworkStatusCodes.CREATED,
                "Review Created successfully",
                service.createReview(review)
        );
    }

    @GetMapping(UrlConstants.FIND_ALL_REVIEWS)
    public CustomResponse<List<ReviewDTO>> findAllReviewsHandler(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit
    ) {
        return new CustomResponse<>(
                NetworkStatusCodes.SUCCESSFUL,
                "All reviews fetched successfully",
                service.findAllReviews(PageRequest.of(page, limit))
        );
    }

    @GetMapping(UrlConstants.FIND_REVIEW_BY_ID)
    public CustomResponse<ReviewDTO> findReviewById(@PathVariable String id) {
        return new CustomResponse<>(
                NetworkStatusCodes.SUCCESSFUL,
                "Review fetched successfully",
                service.findReviewById(id)
        );
    }

    @GetMapping(UrlConstants.FIND_REVIEW_BY_USER_ID)
    public CustomResponse<List<ReviewHistoryDTO>> findReviewByUserIdHandler(
            @PathVariable String userId,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit
    ) {
        return new CustomResponse<>(
                NetworkStatusCodes.SUCCESSFUL,
                "Reviews fetched successfully",
                service.findReviewByUserId(userId, PageRequest.of(page, limit, Sort.Direction.DESC, "createdAt"))
        );
    }

    @GetMapping(UrlConstants.FIND_REVIEW_BY_FACULTY_ID)
    public CustomResponse<List<ReviewFacultyDTO>> findReviewByFacultyIdHandler(
            @PathVariable String facultyId,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit
    ) {
        return new CustomResponse<>(
                NetworkStatusCodes.SUCCESSFUL,
                "Reviews Fetched Successfully",
                service.findReviewByFacultyId(facultyId, PageRequest.of(page, limit, Sort.Direction.DESC, "createdAt"))
        );
    }


    @DeleteMapping(UrlConstants.DELETE_REVIEW)
    public CustomResponse<EmptyObject> deleteReviewHandler(@PathVariable String id) {
        service.deleteReview(id);
        return new CustomResponse<>(
                NetworkStatusCodes.DELETED,
                "Review Deleted Successfully",
                new EmptyObject()
        );
    }
}

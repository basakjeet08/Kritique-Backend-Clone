package dev.anirban.kritique.dto.review;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostReviewRequest {
    private String createdBy;
    private String createdFor;
    private Double rating;
    private String feedback;
}
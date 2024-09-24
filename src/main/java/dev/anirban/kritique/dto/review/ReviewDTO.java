package dev.anirban.kritique.dto.review;


import dev.anirban.kritique.entity.Review;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private String _id;
    private Integer rating;
    private String feedback;
    private String createdBy;
    private String createdFor;

    public Review toReview() {
        return Review
                .builder()
                .id(_id)
                .rating(rating)
                .feedback(feedback)
                .build();
    }
}

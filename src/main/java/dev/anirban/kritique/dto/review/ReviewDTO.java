package dev.anirban.kritique.dto.review;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private String _id;
    private Double rating;
    private String feedback;
    private String createdBy;
    private String createdFor;
    private String createdAt;
}

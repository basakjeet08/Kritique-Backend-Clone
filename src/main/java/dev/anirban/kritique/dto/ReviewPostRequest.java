package dev.anirban.kritique.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPostRequest {
    private Integer rating;
    private String feedback;

    private Integer createdById;
    private Integer createdForId;
}
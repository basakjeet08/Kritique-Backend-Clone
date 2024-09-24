package dev.anirban.kritique.dto.review;


import dev.anirban.kritique.dto.user.UserDTO;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FacultyReviewDTO {
    private String _id;
    private UserDTO createdBy;
    private Double rating;
    private String feedback;
    private String createdAt;
}
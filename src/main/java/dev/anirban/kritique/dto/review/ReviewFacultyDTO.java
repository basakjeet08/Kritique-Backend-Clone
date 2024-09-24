package dev.anirban.kritique.dto.review;

import dev.anirban.kritique.dto.user.UserDTO;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewFacultyDTO {
    private String _id;
    private Double rating;
    private String feedback;
    private UserDTO createdBy;
    private String createdFor;
    private String createdAt;
}
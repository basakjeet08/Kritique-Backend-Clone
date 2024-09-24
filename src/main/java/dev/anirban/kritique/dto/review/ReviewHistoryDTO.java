package dev.anirban.kritique.dto.review;


import dev.anirban.kritique.dto.faculty.FacultyDTO;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewHistoryDTO {
    private String id;
    private FacultyDTO createdFor;
    private Double rating;
    private String feedback;
    private String createdAt;
}
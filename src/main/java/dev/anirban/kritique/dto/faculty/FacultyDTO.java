package dev.anirban.kritique.dto.faculty;

import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDTO {
    private String id;
    private String name;
    private Double experience;
    private String photoUrl;
    private Double avgRating;
    private Integer totalRating;
}
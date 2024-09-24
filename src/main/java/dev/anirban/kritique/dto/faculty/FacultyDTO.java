package dev.anirban.kritique.dto.faculty;

import dev.anirban.kritique.entity.Faculty;
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

    public Faculty toFaculty() {
        return Faculty
                .builder()
                .name(name)
                .experience(experience)
                .photoUrl(photoUrl)
                .avgRating(avgRating)
                .totalRating(totalRating)
                .build();
    }
}
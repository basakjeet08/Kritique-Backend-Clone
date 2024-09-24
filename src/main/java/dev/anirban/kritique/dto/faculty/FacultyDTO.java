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
    private String _id;
    private String name;
    private Double experience;
    private String photoUrl;
    private Double avgRating;
    private Integer totalRatings;

    public Faculty toFaculty() {
        return Faculty
                .builder()
                .id(_id)
                .name(name)
                .experience(experience)
                .photoUrl(photoUrl)
                .avgRating(avgRating)
                .totalRating(totalRatings)
                .build();
    }
}
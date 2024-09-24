package dev.anirban.kritique.entity;


import dev.anirban.kritique.dto.faculty.FacultyDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FACULTY_DB")
public class Faculty {

    @Id
    @UuidGenerator
    private String id;
    private String name;
    private Double experience;
    private String photoUrl;
    private Double avgRating;
    private Integer totalRating;

    @OneToMany(
            mappedBy = "createdFor",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Review> reviewList;

    public void addReview(Review review) {
        if (!reviewList.contains(review)) {
            reviewList.add(review);
            review.setCreatedFor(this);
            totalRating++;
        }
    }

    public void removeReview(Review review) {
        review.setCreatedFor(null);
        reviewList.remove(review);
        totalRating--;
    }

    public FacultyDTO toFacultyDTO() {
        return FacultyDTO
                .builder()
                .id(id)
                .name(name)
                .experience(experience)
                .photoUrl(photoUrl)
                .avgRating(avgRating)
                .totalRating(totalRating)
                .build();
    }
}
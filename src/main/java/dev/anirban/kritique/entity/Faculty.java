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

            // Calculating the new Avg for the teacher
            Double prevTotal = avgRating * totalRating;
            Double newTotal = prevTotal + review.getRating();
            totalRating++;
            avgRating = newTotal / totalRating;

            reviewList.add(review);
            review.setCreatedFor(this);
        }
    }

    public void removeReview(Review review) {

        // Calculating the new Avg for the teacher
        Double prevTotal = avgRating * totalRating;
        Double newTotal = prevTotal - review.getRating();
        totalRating--;
        avgRating = (totalRating != 0) ? newTotal / totalRating : 0.0;

        reviewList.remove(review);
        review.setCreatedFor(null);
    }

    public FacultyDTO toFacultyDTO() {
        return FacultyDTO
                .builder()
                ._id(id)
                .name(name)
                .experience(experience)
                .photoUrl(photoUrl)
                .avgRating(avgRating)
                .totalRatings(totalRating)
                .build();
    }
}
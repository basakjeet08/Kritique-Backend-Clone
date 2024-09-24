package dev.anirban.kritique.entity;


import dev.anirban.kritique.dto.review.ReviewFacultyDTO;
import dev.anirban.kritique.dto.review.ReviewDTO;
import dev.anirban.kritique.dto.review.ReviewHistoryDTO;
import dev.anirban.kritique.enums.Validation;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REVIEW_DB")
public class Review {

    @Id
    @UuidGenerator
    private String id;
    private Double rating;
    private String feedback;
    @Enumerated(value = EnumType.STRING)
    private Validation status;
    private String createdAt;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    private User createdBy;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    private Faculty createdFor;

    public ReviewDTO toReviewDTO() {
        return ReviewDTO
                .builder()
                ._id(id)
                .rating(rating)
                .feedback(feedback)
                .createdBy(createdBy.getUid())
                .createdFor(createdFor.getId())
                .createdAt(createdAt)
                .build();
    }

    public ReviewHistoryDTO toReviewHistoryDTO() {
        return ReviewHistoryDTO
                .builder()
                ._id(id)
                .createdFor(createdFor.toFacultyDTO())
                .rating(rating)
                .feedback(feedback)
                .createdAt(createdAt)
                .build();
    }

    public ReviewFacultyDTO toReviewFacultyDTO() {
        return ReviewFacultyDTO
                .builder()
                ._id(id)
                .rating(rating)
                .feedback(feedback)
                .createdBy(createdBy.toUserDTO())
                .createdFor(createdFor.getId())
                .createdAt(createdAt)
                .build();
    }
}
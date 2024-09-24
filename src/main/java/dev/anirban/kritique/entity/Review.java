package dev.anirban.kritique.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.anirban.kritique.dto.review.ReviewDTO;
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
    private Integer rating;
    private String feedback;
    @Enumerated(value = EnumType.STRING)
    private Validation status;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JsonIgnore
    private User createdBy;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JsonIgnore
    private Faculty createdFor;

    public ReviewDTO toReviewDTO() {
        return ReviewDTO
                .builder()
                ._id(id)
                .rating(rating)
                .feedback(feedback)
                .createdBy(createdBy.getUid())
                .createdFor(createdFor.getId())
                .build();
    }
}
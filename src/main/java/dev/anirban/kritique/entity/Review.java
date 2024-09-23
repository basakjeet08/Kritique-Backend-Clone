package dev.anirban.kritique.entity;


import dev.anirban.kritique.enums.Validation;
import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue
    private Integer id;
    private Integer rating;
    private String feedback;
    @Enumerated(value = EnumType.STRING)
    private Validation status;


    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    private User createdBy;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    private Faculty createdFor;
}
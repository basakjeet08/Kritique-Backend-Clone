package dev.anirban.kritique.entity;


import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue
    private Integer id;
    private String name;
    private String experience;
    private String photoUrl;
    private Double avgRating;
    private Integer totalRatings;

    @OneToMany(
            mappedBy = "createdFor",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Review> reviewList;
}
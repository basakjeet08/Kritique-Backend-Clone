package dev.anirban.kritique.entity;


import dev.anirban.kritique.enums.Role;
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
@Table(name = "USER_DB")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String email;
    private String photoUrl;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(
            mappedBy = "createdBy",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Review> reviewsGiven;
}
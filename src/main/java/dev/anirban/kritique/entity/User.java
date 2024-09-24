package dev.anirban.kritique.entity;


import dev.anirban.kritique.dto.user.UserDTO;
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
    private String uid;
    private String name;
    private String anonymousName;
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

    public void addReview(Review review) {
        if (!reviewsGiven.contains(review)) {
            reviewsGiven.add(review);
            review.setCreatedBy(this);
        }
    }

    public void removeReview(Review review) {
        review.setCreatedBy(null);
        reviewsGiven.remove(review);
    }

    public UserDTO toUserDTO() {
        return UserDTO
                .builder()
                .uid(uid)
                .name(name)
                .anon_name(anonymousName)
                .email(email)
                .photoUrl(photoUrl)
                .build();
    }
}
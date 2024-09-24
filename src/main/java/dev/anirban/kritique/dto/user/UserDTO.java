package dev.anirban.kritique.dto.user;

import dev.anirban.kritique.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String uid;
    private String name;
    private String anon_name;
    private String email;
    private String photoUrl;

    public User toUser() {
        return User
                .builder()
                .uid(uid)
                .name(name)
                .anonymousName(anon_name)
                .email(email)
                .photoUrl(photoUrl)
                .build();
    }
}
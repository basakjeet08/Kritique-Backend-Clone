package dev.anirban.kritique.dto.user;

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
    private String anonymousName;
    private String email;
    private String photoUrl;
}
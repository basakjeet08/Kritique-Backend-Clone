package dev.anirban.kritique.dto.common;

import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenBody {
    private String token;
}
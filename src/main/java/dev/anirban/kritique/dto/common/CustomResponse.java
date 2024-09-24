package dev.anirban.kritique.dto.common;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse<T> {
    private Integer code;
    private String message;
    private T data;
}
package dev.anirban.kritique.exception;

import dev.anirban.kritique.dto.common.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<Object>> handleGlobalException(Exception exception) {
        CustomResponse<Object> response = new CustomResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<CustomResponse<Object>> handleUserNotFoundException(UserNotFound exception) {
        CustomResponse<Object> response = new CustomResponse<>(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FacultyNotFound.class)
    public ResponseEntity<CustomResponse<Object>> handleFacultyNotFoundException(FacultyNotFound exception) {
        CustomResponse<Object> response = new CustomResponse<>(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
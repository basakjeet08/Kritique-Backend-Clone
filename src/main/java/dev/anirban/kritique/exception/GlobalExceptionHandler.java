package dev.anirban.kritique.exception;

import dev.anirban.kritique.constants.NetworkStatusCodes;
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

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<CustomResponse<Object>> handleUserNotFoundException(UserNotFound exception) {
        CustomResponse<Object> response = new CustomResponse<>(
                NetworkStatusCodes.USER_NOT_FOUND,
                exception.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(FacultyNotFound.class)
    public ResponseEntity<CustomResponse<Object>> handleFacultyNotFoundException(FacultyNotFound exception) {
        CustomResponse<Object> response = new CustomResponse<>(
                NetworkStatusCodes.FACULTY_NOT_FOUND,
                exception.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(ReviewNotFound.class)
    public ResponseEntity<CustomResponse<Object>> handleReviewNotFoundException(ReviewNotFound exception) {
        CustomResponse<Object> response = new CustomResponse<>(
                NetworkStatusCodes.REVIEW_NOT_FOUND,
                exception.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(TokenNotFound.class)
    public ResponseEntity<CustomResponse<Object>> handleTokenNotFoundException(TokenNotFound exception) {
        CustomResponse<Object> response = new CustomResponse<>(
                NetworkStatusCodes.TOKEN_REQUIRED,
                exception.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(KIITEmailNotFound.class)
    public ResponseEntity<CustomResponse<Object>> handleInvalidEmailException(KIITEmailNotFound exception) {
        CustomResponse<Object> response = new CustomResponse<>(
                NetworkStatusCodes.EMAIL_NOT_ALLOWED,
                exception.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(ProfanityFoundException.class)
    public ResponseEntity<CustomResponse<Object>> handleProfanityException(ProfanityFoundException exception) {
        CustomResponse<Object> response = new CustomResponse<>(
                NetworkStatusCodes.PROFANITY_DETECTED,
                exception.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(ReviewAlreadyExistsException.class)
    public ResponseEntity<CustomResponse<Object>> handleReviewAlreadyExistsException(ReviewAlreadyExistsException exception) {
        CustomResponse<Object> response = new CustomResponse<>(
                NetworkStatusCodes.ALREADY_REVIEWED,
                exception.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
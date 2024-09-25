package dev.anirban.kritique.exception;

public class ReviewAlreadyExistsException extends RuntimeException {
    public ReviewAlreadyExistsException() {
        super("This faculty is already reviewed by the user");
    }
}
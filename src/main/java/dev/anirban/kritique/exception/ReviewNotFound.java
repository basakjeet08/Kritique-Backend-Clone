package dev.anirban.kritique.exception;

public class ReviewNotFound extends RuntimeException {
    public ReviewNotFound(String id) {
        super("Review with id : " + id + " is not found !!");
    }
}

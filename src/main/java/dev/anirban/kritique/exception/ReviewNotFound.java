package dev.anirban.kritique.exception;

public class ReviewNotFound extends RuntimeException {
    public ReviewNotFound(Integer id) {
        super("Review with id : " + id + " is not found !!");
    }
}

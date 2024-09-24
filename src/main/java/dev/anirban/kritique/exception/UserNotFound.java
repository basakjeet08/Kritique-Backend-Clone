package dev.anirban.kritique.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String id) {
        super("User with id : " + id + " is not found");
    }
}

package dev.anirban.kritique.exception;

public class KIITEmailNotFound extends RuntimeException {
    public KIITEmailNotFound(String email) {
        super("The provided email id " + email + " is not kiit email ID.");
    }
}
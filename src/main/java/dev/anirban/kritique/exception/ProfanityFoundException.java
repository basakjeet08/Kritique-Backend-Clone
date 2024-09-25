package dev.anirban.kritique.exception;

public class ProfanityFoundException extends RuntimeException {
    public ProfanityFoundException() {
        super("The given review contains offensive and abusive words.");
    }
}
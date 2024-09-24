package dev.anirban.kritique.exception;

public class TokenNotFound extends RuntimeException {
    public TokenNotFound() {
        super("No Token found in the request");
    }
}
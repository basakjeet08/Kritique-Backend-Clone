package dev.anirban.kritique.exception;

public class InvalidToken extends RuntimeException {
    public InvalidToken() {
        super("Invalid Token passed please login again");
    }
}
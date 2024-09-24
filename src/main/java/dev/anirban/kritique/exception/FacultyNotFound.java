package dev.anirban.kritique.exception;

public class FacultyNotFound extends RuntimeException {
    public FacultyNotFound(String id) {
        super("Faculty with id : " + id + " is not found !!");
    }
}

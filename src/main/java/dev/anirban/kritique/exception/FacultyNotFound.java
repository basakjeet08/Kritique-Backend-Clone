package dev.anirban.kritique.exception;

public class FacultyNotFound extends RuntimeException {
    public FacultyNotFound(Integer id) {
        super("Faculty with id : " + id + " is not found !!");
    }
}

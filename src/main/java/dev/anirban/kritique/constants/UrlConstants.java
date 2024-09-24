package dev.anirban.kritique.constants;

public class UrlConstants {

    // Authentication URLs
    public final static String LOGIN_USER = "/auth/login";

    // User URLs
    public final static String CREATE_USER = "/users";
    public final static String FIND_ALL_USERS = "/users";
    public final static String FIND_USER_BY_ID = "/users/{id}";
    public final static String DELETE_USER = "/users/{id}";

    // Faculty URLs
    public final static String CREATE_FACULTY = "/faculties";
    public final static String FIND_ALL_FACULTIES = "/faculties";
    public final static String FIND_ALL_FACULTY_BY_NAME = "/faculties/";
    public final static String FIND_FACULTY_BY_ID = "/faculties/{id}";
    public final static String DELETE_FACULTY = "/faculties/{id}";

    // Review URLs
    public final static String CREATE_REVIEWS = "/reviews";
    public final static String FIND_ALL_REVIEWS = "/reviews";
    public final static String FIND_REVIEW_BY_ID = "/reviews/{id}";
    public final static String FIND_REVIEW_BY_USER_ID = "/reviews/user/{userId}";
    public final static String FIND_REVIEW_BY_FACULTY_ID = "/reviews/faculty/{facultyId}";
    public final static String DELETE_REVIEW = "/reviews/{id}";
}
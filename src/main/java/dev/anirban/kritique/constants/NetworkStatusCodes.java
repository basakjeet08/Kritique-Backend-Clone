package dev.anirban.kritique.constants;

public class NetworkStatusCodes {

    // Success Codes
    public final static Integer SUCCESSFUL = 200;
    public final static Integer CREATED = 201;
    public final static Integer DELETED = 202;
    public final static Integer UPDATED = 203;

    // Data Not Found Codes
    public final static Integer USER_NOT_FOUND = 204;
    public final static Integer REVIEW_NOT_FOUND = 205;
    public final static Integer FACULTY_NOT_FOUND = 206;
    public final static Integer ALREADY_REVIEWED = 207;
    public final static Integer EMAIL_NOT_ALLOWED = 208;
    public final static Integer PROFANITY_DETECTED = 209;

    // Invalid Request or other error codes
    public final static Integer INVALID_REQUEST = 400;
    public final static Integer TOKEN_REQUIRED = 401;
    public final static Integer INVALID_TOKEN = 402;
    public final static Integer UNAUTHORIZED = 403;
    public final static Integer INTERNAL_SERVER_ERROR = 404;
    public final static Integer INVALID_EMAIL = 405;
}
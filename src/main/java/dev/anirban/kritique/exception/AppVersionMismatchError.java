package dev.anirban.kritique.exception;


/**
 * This is handler directly in the Filter {@link dev.anirban.kritique.security.VersionHeaderFilter}
 */
public class AppVersionMismatchError extends RuntimeException {
    public AppVersionMismatchError() {
        super("There is an update for the app! Kindly update from play store and try again");
    }
}

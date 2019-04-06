package ru.javaops.masterjava.persist;

public class PersistException extends RuntimeException {
    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistException(String message) {
        super(message);
    }
}

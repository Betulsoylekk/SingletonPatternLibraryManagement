package dev.patika.definexjavaspringbootbootcamp2025.exception;

public class BookIsNotAvailableException extends RuntimeException {
    public BookIsNotAvailableException() {
        super("BookIsNotAvailableException");
    }
}

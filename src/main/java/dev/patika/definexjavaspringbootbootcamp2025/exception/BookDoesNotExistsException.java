package dev.patika.definexjavaspringbootbootcamp2025.exception;

public class BookDoesNotExistsException extends RuntimeException {
    public BookDoesNotExistsException() {
        super("BookDoesNotExistsException");
    }
}

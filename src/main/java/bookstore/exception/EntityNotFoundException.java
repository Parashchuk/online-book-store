package bookstore.exception;

public class EntityNotFoundException extends RuntimeException {
    EntityNotFoundException(String message) {
        super(message);
    }

    EntityNotFoundException(String message, Throwable e) {
        super(message, e);
    }
}

package be.luckuless.code.exception;

public class DuplicateItemException extends RuntimeException {

    public DuplicateItemException() {
        super();
    }

    public DuplicateItemException(String message) {
        super(message);
    }
}

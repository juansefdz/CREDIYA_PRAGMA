package co.com.pragma.model.exceptions;

public class DocumentAlreadyExistsException extends BusinessException {
    public DocumentAlreadyExistsException(String message) {
        super(message);
    }
}
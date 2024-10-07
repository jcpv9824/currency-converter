package exceptions;

public class ErrorIllegalCharactersException extends RuntimeException {
    private String message;

    public ErrorIllegalCharactersException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

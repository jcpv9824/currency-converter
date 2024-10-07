package exceptions;

public class ErrorCurrencyIllegalCharactersException extends RuntimeException {

    private String message;

    public ErrorCurrencyIllegalCharactersException(String message) {
        this.message = message;
    }


    public String getMessage() {
        return this.message;
    }
}

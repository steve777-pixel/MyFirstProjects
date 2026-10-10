package utils;

public class AccountException extends RuntimeException {

    private final AccountError errorType;

    public AccountException(AccountError errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    // A getter method so we can check the label later
    public AccountError getErrorType() {
        return this.errorType;
    }
}
package utils;

public class BankException extends RuntimeException{

    private final BankError errorType;

    public BankException(BankError errorType, String message){
        super(message);
        this.errorType = errorType;
    }

    public BankError getErrorType(){
        return this.errorType;
    }
}
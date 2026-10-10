package utils;

import java.util.Objects;

public final class AccountNumber{

    private final String value;

    private static final String FORMAT_REGEX = "^[A-Za-z]{2}\\d{7}$";

    public AccountNumber(String value){
        if(value == null || value.isBlank() || !value.matches(FORMAT_REGEX)){
            throw new AccountException(AccountError.INVALID_ACCOUNT_NUMBER_FORMAT, "Invalid account number format. Expected: XX0000000.");
        }
        this.value = value.toUpperCase();
    }

    public String getValue(){
        return value;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        AccountNumber other = (AccountNumber) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode(){
        return Objects.hash(value);
    }
}
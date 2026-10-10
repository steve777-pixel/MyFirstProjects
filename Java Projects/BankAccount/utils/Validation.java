package utils;

public class Validation {
    public static String validateNotBlank(String value, String fieldname){
        if(value == null || value.isEmpty()){
            throw new IllegalArgumentException(fieldname + " cannot be empty!");
        }
        return value;
    }
}

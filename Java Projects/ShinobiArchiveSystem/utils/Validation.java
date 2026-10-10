package utils;

public class Validation{

    public static String validateNotBlank(String value, String fieldName){
        if(value == null || value.trim().isEmpty()){
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value;
    }

    public static <T extends Number> T validateNotNegative(T value, String fieldName){
        if(value.doubleValue() < 0.0){
            throw new IllegalArgumentException(fieldName + " cannot be negative.");
        }
        return value;
    }
}
package utils;

import java.time.LocalDate;

public class Validation {
    public static String validateNotBlank(String value, String fieldName){
        if(value == null || value.trim().isEmpty()){
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value;
    }

    public static <T extends Number> T validateNotNegative(T value, String fieldName){
        if(value == null){
            throw new IllegalArgumentException(fieldName + " cannot be null.");
        }
        if(value.doubleValue() < 0){
            throw new IllegalArgumentException(fieldName + " cannot be negative.");
        }
        return value;
    }

    public static LocalDate validateDate(LocalDate date, String fieldName){
        if(date == null){
            throw new IllegalArgumentException(fieldName + " cannot be null.");
        }
        if(date.isAfter(LocalDate.now())){
            throw new IllegalArgumentException(fieldName + " cannot be in the future.");
        }
        return date;
    }

    public static boolean isValidMemberType(String type){
        return type.equals("Guild member") || type.equals("Warrior") || type.equals("Mage") || type.equals("Healer");
    }
}

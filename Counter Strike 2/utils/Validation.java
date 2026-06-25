package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validation{

    public static String validateNotBlank(String value, String fieldName){
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException(fieldName + " cannot be blank.");
        }
        return value;
    }

    public static <T extends Number> T validateNotNegative(T value, String fieldName){
        if(value.doubleValue() < 0.0){
            throw new IllegalArgumentException(fieldName + " cannot be negative.");
        }
        return value;
    }

    public static <T extends Number, U extends Number> T validateBetween(T value, U a, U b, String fieldName){
        if(value.doubleValue() < a.doubleValue() || value.doubleValue() > b.doubleValue()){
            throw new IllegalArgumentException(String.format("%s must be in range [%d-%d].", fieldName, a.doubleValue(), b.doubleValue()));
        }
        return value;
    }

    public static char validateTier(char tier){
        char upper = Character.toUpperCase(tier);
        if(upper != 'S' && upper != 'A' && upper != 'B' && upper != 'C' && upper != 'D'){
            throw new IllegalArgumentException("Invalid tier!\nValid tiers: S, A, B, C, D.");
        }
        return upper;
    }

    public static void validateDateOrder(LocalDate startDate, LocalDate endDate){
        if(startDate == null || endDate == null){
            throw new IllegalArgumentException("Dates cannot be null.");
        }
        if(startDate.isAfter(endDate)){
            throw new IllegalArgumentException("Start date must be before end date.");
        }
    }

    public static LocalDate validateFormat(String date, String fieldName){
        if(date == null){
            throw new IllegalArgumentException("Date cannot be null.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try{
            return LocalDate.parse(date, formatter);
        }catch(DateTimeParseException e){
            throw new IllegalArgumentException(fieldName + " must be in the format dd-MM-yyyy (e.g., 25-12-2026).");
        }
    }   
}
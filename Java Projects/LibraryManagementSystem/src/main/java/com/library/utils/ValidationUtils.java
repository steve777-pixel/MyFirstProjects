package com.library.utils;

public class ValidationUtils{
    public static String validateNotBlank(String value, String fieldName){
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException(fieldName + " cannot be empty!");
        }
        return value;
    }
}
package com.airtribe.learntrack.util;

public class ValidationUtil {
    public static void validateText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
    }

    public static void validateName(String value, String fieldName) {
        validateText(value, fieldName);
    }

    public static void validatePositiveInteger(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be greater than zero.");
        }
    }
}

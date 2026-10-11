package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

/** Small static helpers for validating user input. */
public class InputValidator {

    private InputValidator() {
    }

    public static String requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }

    public static int parseInt(String value, String fieldName) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a whole number.");
        }
    }

    public static int requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be greater than zero.");
        }
        return value;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.indexOf('@') > 0
                && email.indexOf('.', email.indexOf('@')) > email.indexOf('@') + 1;
    }
}

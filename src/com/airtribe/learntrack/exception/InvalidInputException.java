package com.airtribe.learntrack.exception;

/** Thrown when user input or an operation is not valid (blank name, duplicate enrollment, etc.). */
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }
}

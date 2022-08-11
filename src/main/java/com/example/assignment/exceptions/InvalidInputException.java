package com.example.assignment.exceptions;


public class InvalidInputException extends RuntimeException {

    /**
     * @param message the detail message. The detail message is saved for
     * later retrieval by the {@link #getMessage()} method.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}

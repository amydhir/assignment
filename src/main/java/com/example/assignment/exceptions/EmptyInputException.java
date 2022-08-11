package com.example.assignment.exceptions;

public class EmptyInputException extends IllegalArgumentException{

    /**
     * Constructs an <code>IllegalArgumentException</code> with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public EmptyInputException(String s) {
        super(s);
    }

}

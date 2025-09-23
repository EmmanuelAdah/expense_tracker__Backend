package com.expensetracker.exceptions;

public class InvalidLoginCredentialsException extends UserException {
    public InvalidLoginCredentialsException(String message) {
        super(message);
    }
}

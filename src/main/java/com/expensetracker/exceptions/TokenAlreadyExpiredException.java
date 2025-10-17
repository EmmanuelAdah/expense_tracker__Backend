package com.expensetracker.exceptions;

public class TokenAlreadyExpiredException extends RuntimeException {
    public TokenAlreadyExpiredException(String message) {
        super(message);
    }
}

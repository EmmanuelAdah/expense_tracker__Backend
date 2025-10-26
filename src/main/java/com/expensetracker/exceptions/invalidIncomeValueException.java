package com.expensetracker.exceptions;

public class invalidIncomeValueException extends RuntimeException {
    public invalidIncomeValueException(String message) {
        super(message);
    }
}

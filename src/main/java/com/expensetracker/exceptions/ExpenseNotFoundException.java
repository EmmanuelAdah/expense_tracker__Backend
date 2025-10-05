package com.expensetracker.exceptions;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException(String expenseNotFound) {
        super(expenseNotFound);
    }
}

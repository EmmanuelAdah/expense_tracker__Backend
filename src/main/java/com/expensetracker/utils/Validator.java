package com.expensetracker.utils;

public class Validator {

    public static boolean isValidEmail(String email){
        return email.matches("^(?=.{3,30}@)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$"
        );
    }
}

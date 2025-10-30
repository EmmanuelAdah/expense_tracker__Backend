package com.expensetracker.utils;

import com.expensetracker.exceptions.*;
import org.postgresql.util.PSQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            InvalidLoginCredentialsException.class,
            UserNotFoundException.class,
            UsernameNotFoundException.class
    })
    public ResponseEntity<Map<String, String>> handleCustomExceptions(RuntimeException ex) {
        return buildErrorResponse(ex.getMessage());
    }

    public ResponseEntity<Map<String, String>> buildErrorResponse(String exception) {
        Map<String, String> errorMessages = Map.of("message", exception);
        return ResponseEntity.badRequest().body(errorMessages);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<Map<String, String>> handlePSQLException(PSQLException ex) {
        String message = ex.getMessage();

        if (message.contains("email")) {
            String field = "email";
            return ResponseEntity.badRequest().body(Map.of(
                    "field", field,
                    "message", field + " already exists"
            ));
        } else if (message.contains("username")) {
            String field = "username";
            return ResponseEntity.badRequest().body(Map.of(
                    "field", field,
                    "message", field + " already exists"
            ));
        }
        return ResponseEntity.status(500).body(Map.of("message", "Database error"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField(); // e.g., "firstname"
            String message = error.getDefaultMessage(); // e.g., "First name is mandatory"
            errors.put(field, message);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleExpenseNotFoundException(ExpenseNotFoundException exception) {
        Map<String, String> errorMessages = Map.of("message", exception.getMessage());
        return ResponseEntity.badRequest().body(errorMessages);
    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<Map<String, String>> handleInvalidAmountException(InvalidAmountException exception) {
        Map<String, String> errorMessages = Map.of("message", exception.getMessage());
        return ResponseEntity.badRequest().body(errorMessages);
    }

    @ExceptionHandler(invalidIncomeValueException.class)
    public ResponseEntity<Map<String, String>> handleInvalidIcomeValueException(invalidIncomeValueException exception) {
        Map<String, String> errorMessages = Map.of("message", exception.getMessage());
        return ResponseEntity.badRequest().body(errorMessages);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmailException(InvalidEmailException exception) {
        Map<String, String> errorMessages = Map.of("message", exception.getMessage());
        return ResponseEntity.badRequest().body(errorMessages);
    }
}

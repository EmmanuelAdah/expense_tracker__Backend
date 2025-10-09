package com.expensetracker.controllers;

import com.expensetracker.exceptions.InvalidLoginCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Map;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(InvalidLoginCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleException(InvalidLoginCredentialsException exception) {
        Map<String, String> errorMessages = Map.of("message", exception.getMessage());
        return ResponseEntity.badRequest().body(errorMessages);
    }
}

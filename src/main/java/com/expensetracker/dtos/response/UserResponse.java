package com.expensetracker.dtos.response;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
public class UserResponse {
    private UUID userId;
    private String email;
    private String username;
    private double balance;
    private double income;
}

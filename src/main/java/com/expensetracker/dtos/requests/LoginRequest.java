package com.expensetracker.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}

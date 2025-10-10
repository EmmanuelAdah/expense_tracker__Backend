package com.expensetracker.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginRequest {
    @NotBlank(message = "username is mandatory")
    @NotEmpty(message = "username is mandatory")
    private String username;

    @NotBlank(message = "password is mandatory")
    @NotEmpty(message = "password is mandatory")
    private String password;
}

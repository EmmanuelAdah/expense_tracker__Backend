package com.expensetracker.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "firstname")
    private String firstname;

    @NotBlank(message = "lastname")
    private String lastname;

    @NotBlank(message = "email")
    private String email;

    @NotBlank(message = "username")
    private String username;

    @NotBlank(message = "password")
    private String password;
}

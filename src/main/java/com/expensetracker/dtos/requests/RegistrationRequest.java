package com.expensetracker.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @NotBlank(message = "First name is mandatory")
    @Size(min = 3, max = 30, message = "First name must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name can only contain letters")
    private String firstname;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 3, max = 30, message = "Last name must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name can only contain letters")
    private String lastname;

    @NotBlank(message = "Email is mandatory")
    @Size(min = 10, max = 30, message = "Email must be between {min} and {max} characters")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, max = 30, message = "Username must be between {min} and {max} characters")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 100, message = "Password must be between {min} and {max} characters")
    private String password;
}

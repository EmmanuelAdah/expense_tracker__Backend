package com.expensetracker.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3, max = 30, message = "first name must be between {min} and {max} long")
    @Pattern(regexp = "^\\w+$")
    private String firstname;

    @NotBlank(message = "lastname")
    @Size(min = 3, max = 30, message = "last name must be between {min} and {max} long")
    @Pattern(regexp = "^\\w+$")
    private String lastname;

    @NotBlank(message = "email")
    @Size(min = 10, max = 30, message = "email must be between {min} and {max} long")
//    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n")
    private String email;

    @NotBlank(message = "username")
    @Size(min = 4, max = 30, message = "username must be between {min} and {max} long")
    private String username;

    @NotBlank(message = "password")
    @Size(min = 6, max = 100, message = "password must be between {min} and {max} long")
    private String password;
}

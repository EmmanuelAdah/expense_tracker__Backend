package com.expensetracker.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class RegistrationRequest {

    @NotEmpty(message = "firstname is mandatory")
    @NotBlank(message = "firstname")
    @Size(min = 3, max = 30, message = "first name must be between {min} and {max} long")
    @Pattern(regexp = "^[A-Za-z]+$", message = "first name can only contain letters")
    private String firstname;

    @NotEmpty(message = "lastname is mandatory")
    @NotBlank(message = "lastname")
    @Size(min = 3, max = 30, message = "last name must be between {min} and {max} long")
    @Pattern(regexp = "^[A-Za-z]+$", message = "last name can only contain letters")
    private String lastname;

    @NotEmpty(message = "email is mandatory")
    @NotBlank(message = "email")
    @Size(min = 10, max = 30, message = "email must be between {min} and {max} long")
//    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n")
    private String email;

    @NotEmpty(message = "username is mandatory")
    @NotBlank(message = "username")
    @Size(min = 4, max = 30, message = "username must be between {min} and {max} long")
    private String username;

    @NotEmpty(message = "password is mandatory")
    @NotBlank(message = "password is mandatory")
    @Size(min = 6, max = 100, message = "password must be between {min} and {max} long")
    private String password;
}

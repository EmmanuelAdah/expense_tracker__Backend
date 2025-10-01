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

    @NotBlank(message = "")
    private String firstname;

    @NotBlank(message = "")
    private String lastname;

    @NotBlank(message = "")
    private String email;

    @NotBlank(message = "")
    private String username;

    @NotBlank(message = "")
    private String password;
}

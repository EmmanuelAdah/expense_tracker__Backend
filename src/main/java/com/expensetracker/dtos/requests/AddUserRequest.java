package com.expensetracker.dtos.requests;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class AddUserRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
}

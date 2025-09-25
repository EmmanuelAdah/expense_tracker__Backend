package com.expensetracker.dtos.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class AddUserRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
}

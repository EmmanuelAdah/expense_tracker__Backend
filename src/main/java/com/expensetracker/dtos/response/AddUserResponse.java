package com.expensetracker.dtos.response;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class AddUserResponse {
    private String userId;
    private String email;
    private String username;

}

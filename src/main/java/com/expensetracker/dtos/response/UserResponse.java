package com.expensetracker.dtos.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserResponse {
    private long userId;
    private String email;
    private String username;
}

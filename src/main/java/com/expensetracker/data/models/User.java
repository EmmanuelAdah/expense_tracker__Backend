package com.expensetracker.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
}

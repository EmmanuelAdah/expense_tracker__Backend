package com.expensetracker.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    @DBRef
    private List<Expense> expenses = new ArrayList<>();
}

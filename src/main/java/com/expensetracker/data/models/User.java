package com.expensetracker.data.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    @OneToMany(mappedBy = "userId")
    private List<Expense> expenses = new ArrayList<>();
}

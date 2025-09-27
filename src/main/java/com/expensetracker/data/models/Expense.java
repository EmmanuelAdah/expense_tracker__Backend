package com.expensetracker.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String userId;
    private String name;
    private double amount;
    private String category;
    private Type type;
    private String dateAdded;
    private String dueDate;
}

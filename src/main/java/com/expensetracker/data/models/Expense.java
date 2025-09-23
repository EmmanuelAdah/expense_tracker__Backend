package com.expensetracker.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Expense {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private double amount;
    private String category;
    private String type;
    private String dateAdded;
    private String dueDate;
}

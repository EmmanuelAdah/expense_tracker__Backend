package com.expensetracker.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Expense {
    @Id
    private long id;

    private long userId;
    private String name;
    private double amount;
    private String category;
    private String type;
    private String dateAdded;
    private String dueDate;
}

package com.expensetracker.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String dueDate;
}

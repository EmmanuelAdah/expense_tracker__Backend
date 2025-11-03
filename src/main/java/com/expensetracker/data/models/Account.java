package com.expensetracker.data.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Account {
    private double income;
    private double balance;
}

package com.expensetracker.dtos.response;

import com.expensetracker.data.models.Type;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
public class ExpenseResponse {
    private long id;
    private String name;
    private double amount;
    private String category;
    private Type type;
    private LocalDate createdAt;
    private LocalDate dueDate;
}

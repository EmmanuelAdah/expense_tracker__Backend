package com.expensetracker.dtos.response;

import com.expensetracker.data.models.Type;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AddExpenseResponse {
    private long id;
    private String name;
    private double amount;
    private String category;
    private Type type;
    private String dateAdded;
    private String dueDate;
}

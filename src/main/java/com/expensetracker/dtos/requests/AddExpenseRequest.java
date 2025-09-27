package com.expensetracker.dtos.requests;


import com.expensetracker.data.models.Type;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AddExpenseRequest {
    private String userId;
    private String name;
    private double amount;
    private String category;
    private Type type;
    private String dateAdded;
    private String dueDate;
}

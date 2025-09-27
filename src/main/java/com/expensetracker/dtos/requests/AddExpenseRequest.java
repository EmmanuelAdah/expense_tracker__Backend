package com.expensetracker.dtos.requests;


import com.expensetracker.data.models.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AddExpenseRequest {

    private long userId;

    @NotBlank()
    @Size(min = 3, max = 100, message = "Name must be between {min} and {max} letters long")
    private String name;

    @NotNull
    private double amount;

    private String category;
    private Type type;


    private String dateAdded;
    private String dueDate;
}

package com.expensetracker.dtos.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AddExpenseRequest {

    @NotBlank()
    @Size(min = 3, max = 100, message = "Name must be between {min} and {max} letters long")
    private String name;

    @NotNull
    private long amount;

    @NotNull
    @Size(min = 3, max = 100, message = "Type must be between {min} and {max} letters long")
    private String type;

    @NotNull
    @Size(min = 3, max = 100, message = "Category must be between {min} and {max} letters long")
    private String category;
}

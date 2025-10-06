package com.expensetracker.dtos.requests;


import com.expensetracker.data.models.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Data
@Component
public class AddExpenseRequest {

    @NotBlank
    private long userId;

    @NotBlank()
    @Size(min = 3, max = 100, message = "Name must be between {min} and {max} letters long")
    private String name;

    @NotNull
    @Size(message = "amount must be greater than 0")
    private long amount;

    @NotNull
    private String category;

    @NotNull
    private Type type;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate dueDate;
}

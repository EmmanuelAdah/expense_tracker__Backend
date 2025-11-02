package com.expensetracker.utils;

import com.expensetracker.data.models.Expense;
import com.expensetracker.data.models.Type;
import com.expensetracker.data.models.User;
import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.response.ExpenseResponse;
import com.expensetracker.dtos.response.UserResponse;
import com.expensetracker.exceptions.InvalidAmountException;

import java.time.LocalDate;

public class Mapper {

    public static UserResponse map(User user){
        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setBalance(user.getBalance());
        response.setIncome(user.getIncome());
        return response;
    }

    public static Expense mapExpense(AddExpenseRequest request, long userId) {
        if (request.getAmount() <= 0)
            throw new InvalidAmountException("Amount must be greater than 0");

        Expense expense = new Expense();
        expense.setUserId(userId);
        expense.setName(request.getName().toUpperCase());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setCreatedAt(LocalDate.now());
        expense.setType(Type.valueOf(request.getType()
                .toUpperCase()
                .trim()));
        return expense;
    }

    public static ExpenseResponse map(Expense expense){
        ExpenseResponse response = new ExpenseResponse();
        response.setId(expense.getId());
        response.setName(expense.getName());
        response.setAmount(expense.getAmount());
        response.setCategory(expense.getCategory());
        response.setType(expense.getType());
        response.setCreatedAt(LocalDate.now());
        return response;
    }
}

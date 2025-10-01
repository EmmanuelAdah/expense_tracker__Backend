package com.expensetracker.utils;

import com.expensetracker.data.models.Expense;
import com.expensetracker.data.models.User;
import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.requests.RegisterRequest;
import com.expensetracker.dtos.response.AddExpenseResponse;
import com.expensetracker.dtos.response.AddUserResponse;
import com.expensetracker.dtos.response.UserResponse;
import java.time.LocalDate;

import static com.expensetracker.utils.PasswordEncoder.hashPassword;

public class Mapper {

    public static User mapUser(RegisterRequest request){
        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(hashPassword(request.getPassword()));
        return user;
    }

    public static AddUserResponse mapResponse(User user){
        AddUserResponse response = new AddUserResponse();
        response.setFirstName(user.getFirstname());
        response.setLastName(user.getLastname());
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        return response;
    }

    public static UserResponse map(User user){
        UserResponse response = new UserResponse();
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        return response;
    }

    public static Expense mapExpense(AddExpenseRequest request) {
        Expense expense = new Expense();
        expense.setName(request.getName());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setCreatedAt(String.valueOf(LocalDate.now()));
        expense.setDueDate(request.getDueDate());
        expense.setType(request.getType());
        return expense;
    }

    public static AddExpenseResponse map(Expense expense){
        AddExpenseResponse response = new AddExpenseResponse();
        response.setId(expense.getId());
        response.setName(expense.getName());
        response.setAmount(expense.getAmount());
        response.setCategory(expense.getCategory());
        response.setType(expense.getType());
        response.setDateAdded(LocalDate.now().toString());
        response.setDueDate(expense.getDueDate());
        return response;
    }
}

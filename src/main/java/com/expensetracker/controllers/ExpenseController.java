package com.expensetracker.controllers;

import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.response.AddExpenseResponse;
import com.expensetracker.services.ExpenseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {
    private final ExpenseServiceImpl expenseServiceImpl;

    @Autowired
    public ExpenseController(ExpenseServiceImpl expenseServiceImpl){
        this.expenseServiceImpl = expenseServiceImpl;
    }

    @PostMapping
    public AddExpenseResponse addExpense(AddExpenseRequest request){
        return expenseServiceImpl.saveExpense(request);
    }
}

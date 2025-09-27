package com.expensetracker.services;

import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.response.AddExpenseResponse;
import org.springframework.stereotype.Service;

@Service
public interface ExpenseService {

    AddExpenseResponse saveExpense(AddExpenseRequest request);
}

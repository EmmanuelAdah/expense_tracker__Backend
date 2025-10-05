package com.expensetracker.services;

import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.response.ExpenseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {
    ExpenseResponse saveExpense(AddExpenseRequest request);
    List<ExpenseResponse> findByUserId(Long userId);
    ExpenseResponse findById(Long expenseId);
    List<ExpenseResponse> findAll();
    void deleteAllByUserId(Long userId);
    void deleteAll();
}

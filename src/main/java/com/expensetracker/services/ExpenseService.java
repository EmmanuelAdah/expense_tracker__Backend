package com.expensetracker.services;

import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.response.ExpenseResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ExpenseService {
    ExpenseResponse saveExpense(AddExpenseRequest request);
    ExpenseResponse findById(Long expenseId);
    List<ExpenseResponse> findByUserId(long userId);
    List<ExpenseResponse> findAll();
    void deleteAllByUserId(Long userId);
    void deleteAll();
}

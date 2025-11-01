package com.expensetracker.services;

import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.response.ExpenseResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ExpenseService {

    ExpenseResponse saveExpense(AddExpenseRequest request, String username);
    ExpenseResponse findById(Long expenseId);
    List<ExpenseResponse> findByUserId(long userId);
    List<ExpenseResponse> findAll();
    void deleteByExpenseId(@Valid Long id);
    void deleteAllByUserId(Long userId);
    void deleteAll();
}

package com.expensetracker.services;

import com.expensetracker.data.models.Expense;
import com.expensetracker.data.models.User;
import com.expensetracker.data.repositories.ExpenseRepository;
import com.expensetracker.data.repositories.UserRepository;
import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.response.ExpenseResponse;
import com.expensetracker.exceptions.UserNotFoundException;
import com.expensetracker.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.expensetracker.utils.Mapper.map;
import static com.expensetracker.utils.Mapper.mapExpense;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    public ExpenseResponse saveExpense(AddExpenseRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Expense expense = expenseRepository.save(mapExpense(request));
        user.getExpenses().add(expense);
        userRepository.save(user);
        return map(expense);
    }

    @Override
    public List<ExpenseResponse> findAll() {
        return expenseRepository.findAll()
                .stream()
                .map(Mapper::map)
                .toList();
    }

    @Override
    public List<ExpenseResponse> findByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return user.getExpenses()
                .stream()
                .map(Mapper::map)
                .toList();
    }
}

package com.expensetracker.services;

import com.expensetracker.data.models.Expense;
import com.expensetracker.data.models.User;
import com.expensetracker.data.repositories.ExpenseRepository;
import com.expensetracker.data.repositories.UserRepository;
import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.response.ExpenseResponse;
import com.expensetracker.exceptions.ExpenseNotFoundException;
import com.expensetracker.exceptions.InsufficientBalanceException;
import com.expensetracker.exceptions.InvalidAmountException;
import com.expensetracker.utils.Mapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.expensetracker.utils.Mapper.map;
import static com.expensetracker.utils.Mapper.mapExpense;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final UserServiceImpl userServiceImpl;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    @Override
    public ExpenseResponse saveExpense(AddExpenseRequest request, String username) {
        User user = userServiceImpl.findByUsername(username);
        double amount = request.getAmount();
        double balance = user.getBalance();

        if (request.getAmount() <= 0)
            throw new InvalidAmountException("Amount must be greater than 0");

        if (amount > balance)
            throw new InsufficientBalanceException("Insufficient balance");

        Expense expense = expenseRepository.save(mapExpense(request, user.getUserId()));

        user.setBalance(balance - amount);
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
    public List<ExpenseResponse> findByUserId(long userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        if  (expenses.isEmpty())
            throw new ExpenseNotFoundException("No expenses found");

        return expenses.stream()
                .map(Mapper::map)
                .toList();
    }

    @Override
    public ExpenseResponse findById(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));
        return map(expense);
    }

    @Override
    public void deleteAllByUserId(@Valid Long userId) {
        while (!expenseRepository.findByUserId(userId)
                .isEmpty()) {
            expenseRepository.deleteAll();
        }
    }

    @Override
    public void deleteByExpenseId(@Valid Long id) {
        expenseRepository.deleteExpenseById(id);
    }

    @Override
    public void deleteAll() {
        expenseRepository.deleteAll();
    }
}

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
import com.expensetracker.exceptions.UserNotFoundException;
import com.expensetracker.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.expensetracker.utils.Mapper.map;
import static com.expensetracker.utils.Mapper.mapExpense;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    public ExpenseResponse saveExpense(AddExpenseRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (request.getAmount() <= 0)
            throw new InvalidAmountException("Amount must be greater than 0");

        Expense expense = expenseRepository.save(mapExpense(request, user.getUserId()));
        double amount = request.getAmount();
        double balance = user.getBalance();

        if (amount > balance)
            throw new InsufficientBalanceException("Insufficient balance");

        user.getExpenses().add(expense);
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return user.getExpenses()
                .stream()
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
    public void deleteAllByUserId(Long userId) {
        User user  = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.getExpenses().clear();
    }

    @Override
    public void deleteAll() {
        expenseRepository.deleteAll();
    }
}

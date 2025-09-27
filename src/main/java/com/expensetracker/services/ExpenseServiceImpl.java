package com.expensetracker.services;

import com.expensetracker.data.models.Expense;
import com.expensetracker.data.models.User;
import com.expensetracker.data.repositories.ExpenseRepository;
import com.expensetracker.data.repositories.UsersRepository;
import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.response.AddExpenseResponse;
import com.expensetracker.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.expensetracker.utils.Mapper.map;
import static com.expensetracker.utils.Mapper.mapExpense;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public AddExpenseResponse saveExpense(AddExpenseRequest request) {
        User user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("No such user found!"));

        Expense expense = expenseRepository.save(mapExpense(request));
        user.getExpenses().add(expense);
        usersRepository.save(user);
        return map(expense);
    }
}

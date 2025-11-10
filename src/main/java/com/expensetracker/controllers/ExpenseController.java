package com.expensetracker.controllers;

import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.response.ExpenseResponse;
import com.expensetracker.services.ExpenseServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tracker/expenses")
public class ExpenseController {
    private final ExpenseServiceImpl expenseServiceImpl;

    @PostMapping("/addExpense")
    public ResponseEntity<ExpenseResponse> addExpense(@Valid @RequestBody
                                                          AddExpenseRequest request,
                                                      Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(expenseServiceImpl.saveExpense(request, username));
    }

    @GetMapping("/findById")
    public ResponseEntity<ExpenseResponse> findById(@Valid @RequestParam UUID id){
        return ResponseEntity.ok(expenseServiceImpl.findById(id));
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<List<ExpenseResponse>> findByUserId(@Valid @RequestParam UUID userId){
        return ResponseEntity.ok(expenseServiceImpl.findByUserId(userId));
    }

    @GetMapping("/findAllExpenses")
    public ResponseEntity<List<ExpenseResponse>> findAll(){
        return ResponseEntity.ok(expenseServiceImpl.findAll());
    }

    @DeleteMapping("/deleteAllByUserId")
    public void deleteAllByUserId(@Valid @RequestParam UUID userId){
        expenseServiceImpl.deleteAllByUserId(userId);
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@Valid @RequestParam UUID id){
        expenseServiceImpl.deleteByExpenseId(id);
    }
}

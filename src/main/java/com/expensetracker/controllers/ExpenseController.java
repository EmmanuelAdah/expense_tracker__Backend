package com.expensetracker.controllers;

import com.expensetracker.dtos.requests.AddExpenseRequest;
import com.expensetracker.dtos.response.ExpenseResponse;
import com.expensetracker.services.ExpenseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tracker/expenses")
public class ExpenseController {
    private final ExpenseServiceImpl expenseServiceImpl;

    @PostMapping("/addExpense")
    public ResponseEntity<ExpenseResponse> addExpense(AddExpenseRequest request){
        return ResponseEntity.ok(expenseServiceImpl.saveExpense(request));
    }

    @GetMapping("/findById")
    public ResponseEntity<ExpenseResponse> findById(Long expenseId){
        return ResponseEntity.ok(expenseServiceImpl.findById(expenseId));
    }

    @GetMapping("/findNoteByUserId")
    public ResponseEntity<List<ExpenseResponse>> findNotesByUserId(Long userId){
        return ResponseEntity.ok(expenseServiceImpl.findByUserId(userId));
    }

    @GetMapping("/findAllNotes")
    public ResponseEntity<List<ExpenseResponse>> findAll(){
        return ResponseEntity.ok(expenseServiceImpl.findAll());
    }

    @DeleteMapping("/deleteAllByUserId")
    public void deleteAllByUserId(Long userId){
        expenseServiceImpl.deleteAllByUserId(userId);
    }


}

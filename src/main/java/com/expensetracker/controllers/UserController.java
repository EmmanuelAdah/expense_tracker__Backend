package com.expensetracker.controllers;

import com.expensetracker.dtos.response.UserResponse;
import com.expensetracker.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tracker")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/findById")
    public ResponseEntity<UserResponse> findById(@RequestParam long userId) {
        return ResponseEntity.ok(userServiceImpl.findById(userId));
    }

    @GetMapping("/find/Username")
    public ResponseEntity<UserResponse> findByUsername(@RequestParam String username){
        return ResponseEntity.ok(userServiceImpl.findByUsername(username));
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<UserResponse> findByEmail(@RequestParam String email){
        return ResponseEntity.ok(userServiceImpl.findByEmail(email));
    }

    @GetMapping("/find/All")
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(userServiceImpl.findAll());
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam long userId){
        userServiceImpl.deleteById(userId);
    }

    @DeleteMapping("/deleteByUsername")
    public void deleteByUsername(@RequestParam String username){
        userServiceImpl.deleteByUsername(username);
    }

    @DeleteMapping("/delete/Users")
    public void deleteAll(){
        userServiceImpl.deleteAll();
    }

    @PostMapping("/set/income")
    public ResponseEntity<Long> setIncome(@RequestParam long income,
                                          Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(userServiceImpl.setIncome(username, income));
    }
}

package com.expensetracker.controllers;

import com.expensetracker.data.models.User;
import com.expensetracker.dtos.response.UserResponse;
import com.expensetracker.services.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.expensetracker.utils.Mapper.map;

@RestController
@RequestMapping("/api/tracker")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/findById")
    public ResponseEntity<UserResponse> findById(@Valid @RequestParam long userId) {
        return ResponseEntity.ok(userServiceImpl.findById(userId));
    }

    @GetMapping("/find/Username")
    public ResponseEntity<UserResponse> findByUsername(@Valid @RequestParam String username){
        User user = userServiceImpl.findByUsername(username);
        return ResponseEntity.ok(map(user));
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<UserResponse> findByEmail(@Valid @RequestParam String email){
        return ResponseEntity.ok(userServiceImpl.findByEmail(email));
    }

    @GetMapping("/find/All")
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(userServiceImpl.findAll());
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@Valid @RequestParam long userId){
        userServiceImpl.deleteById(userId);
    }

    @DeleteMapping("/deleteByUsername")
    public void deleteByUsername(@Valid @RequestParam String username){
        userServiceImpl.deleteByUsername(username);
    }

    @DeleteMapping("/delete/Users")
    public void deleteAll(){
        userServiceImpl.deleteAll();
    }

    @PostMapping("/set/income")
    public ResponseEntity<Double> setIncome(@Valid @RequestParam double income,
                                          Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(userServiceImpl.setIncome(username, income));
    }

//    @PatchMapping
//    public ResponseEntity<UserResponse> updateUser(@Valid UpdateRequest request) {
//        return ResponseEntity.ok(userServiceImpl.updatePassword(request));
//    }
}

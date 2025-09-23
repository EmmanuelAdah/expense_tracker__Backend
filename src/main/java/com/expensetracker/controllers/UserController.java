package com.expensetracker.controllers;

import com.expensetracker.dtos.requests.AddUserRequest;
import com.expensetracker.dtos.requests.LoginRequest;
import com.expensetracker.dtos.response.AddUserResponse;
import com.expensetracker.dtos.response.UserResponse;
import com.expensetracker.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<AddUserResponse> registerUser(AddUserRequest request){
        return ResponseEntity.ok(userServiceImpl.registerUser(request));
    }

    @GetMapping("/findById")
    public ResponseEntity<Stream<UserResponse>> findById(Long userId){
        return ResponseEntity.ok(userServiceImpl.findById(userId));
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<UserResponse> findByUsername(String username){
        return ResponseEntity.ok(userServiceImpl.findByUsername(username));
    }

    @GetMapping("/findAll")
    public ResponseEntity<Stream<UserResponse>> findAll(){
        return ResponseEntity.ok(userServiceImpl.findAll());
    }

    @PostMapping
    public UserResponse userLogin(LoginRequest loginRequest){
        return userServiceImpl.userLogin(loginRequest);
    }
}

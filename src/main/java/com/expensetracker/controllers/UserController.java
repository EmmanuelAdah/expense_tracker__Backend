package com.expensetracker.controllers;

import com.expensetracker.dtos.requests.AddUserRequest;
import com.expensetracker.dtos.requests.LoginRequest;
import com.expensetracker.dtos.response.AddUserResponse;
import com.expensetracker.dtos.response.UserResponse;
import com.expensetracker.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/tracker")
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
    public ResponseEntity<Stream<UserResponse>> findById(String userId){
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

    @PostMapping("/userLogin")
    public UserResponse userLogin(LoginRequest loginRequest){
        return userServiceImpl.userLogin(loginRequest);
    }

    @DeleteMapping("/deleteById")
    public void deleteById(String id){
        userServiceImpl.deleteById(id);
    }
}

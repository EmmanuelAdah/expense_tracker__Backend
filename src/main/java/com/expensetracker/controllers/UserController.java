package com.expensetracker.controllers;

import com.expensetracker.dtos.requests.LoginRequest;
import com.expensetracker.dtos.requests.RegisterRequest;
import com.expensetracker.dtos.response.AddUserResponse;
import com.expensetracker.dtos.response.UserResponse;
import com.expensetracker.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tracker")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/registerUser")
    public ResponseEntity<AddUserResponse> registerUser(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(userServiceImpl.registerUser(request));
    }

    @GetMapping("/findById")
    public ResponseEntity<UserResponse> findById(@RequestParam Long userId){
        return ResponseEntity.ok().body(userServiceImpl.findById(userId));
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<UserResponse> findByUsername(@RequestParam String username){
        return ResponseEntity.ok(userServiceImpl.findByUsername(username));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(userServiceImpl.findAll());
    }

    @PostMapping("/userLogin")
    public UserResponse userLogin(@RequestBody LoginRequest loginRequest){
        return userServiceImpl.userLogin(loginRequest);
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam Long id){
        userServiceImpl.deleteById(id);
    }

    @DeleteMapping("/deleteByUsername")
    public void deleteByUsername(@RequestParam String username){
        userServiceImpl.deleteByUsername(username);
    }

    @DeleteMapping("/deleteAllUsers")
    public void deleteAll(){
        userServiceImpl.deleteAll();
    }
}

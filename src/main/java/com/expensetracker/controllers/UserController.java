package com.expensetracker.controllers;

import com.expensetracker.dtos.requests.LoginRequest;
import com.expensetracker.dtos.response.UserResponse;
import com.expensetracker.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/findByUsername")
    public ResponseEntity<UserResponse> findByUsername(@RequestParam String username){
        return ResponseEntity.ok(userServiceImpl.findByUsername(username));
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<UserResponse> findByEmail(@RequestParam String email){
        return ResponseEntity.ok(userServiceImpl.findByEmail(email));
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
    public void deleteById(@RequestParam long id){
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

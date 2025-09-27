package com.expensetracker.services;

import com.expensetracker.dtos.requests.AddUserRequest;
import com.expensetracker.dtos.requests.LoginRequest;
import com.expensetracker.dtos.response.AddUserResponse;
import com.expensetracker.dtos.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public interface UserService {

    AddUserResponse registerUser(AddUserRequest request);
    List<UserResponse> findById(String userId);
    UserResponse findByUsername(String username);
    List<UserResponse> findAll();
    UserResponse userLogin(LoginRequest loginRequest);
    void deleteById(String id);
    void deleteByUsername(String username);
    void deleteAll();
}

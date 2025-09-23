package com.expensetracker.services;

import com.expensetracker.dtos.requests.AddUserRequest;
import com.expensetracker.dtos.requests.LoginRequest;
import com.expensetracker.dtos.response.AddUserResponse;
import com.expensetracker.dtos.response.UserResponse;
import org.springframework.stereotype.Service;
import java.util.stream.Stream;

@Service
public interface UserService {

    AddUserResponse registerUser(AddUserRequest request);
    Stream<UserResponse> findById(Long userId);
    UserResponse findByUsername(String username);
    Stream<UserResponse> findAll();
    UserResponse userLogin(LoginRequest loginRequest);
}

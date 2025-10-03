package com.expensetracker.services;

import com.expensetracker.dtos.requests.LoginRequest;
import com.expensetracker.dtos.requests.RegisterRequest;
import com.expensetracker.dtos.response.UserResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {

    UserResponse registerUser(RegisterRequest request);
    UserResponse findById(Long userId);
    UserResponse findByUsername(String username);
    List<UserResponse> findAll();
    UserResponse userLogin(LoginRequest loginRequest);
    void deleteById(Long id);
    void deleteByUsername(String username);
    void deleteAll();
}

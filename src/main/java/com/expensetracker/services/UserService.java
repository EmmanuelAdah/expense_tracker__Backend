package com.expensetracker.services;

import com.expensetracker.dtos.requests.LoginRequest;
import com.expensetracker.dtos.response.UserResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {

    UserResponse findById(long userId);
    UserResponse findByUsername(String username);
    UserResponse findByEmail(String email);
    List<UserResponse> findAll();
    UserResponse userLogin(LoginRequest loginRequest);
    void deleteById(long id);
    void deleteByUsername(String username);
    void deleteAll();
}

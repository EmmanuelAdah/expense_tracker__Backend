package com.expensetracker.services;

import com.expensetracker.data.models.User;
import com.expensetracker.dtos.response.UserResponse;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public interface UserService {

    User save(User user);
    UserResponse findById(UUID userId);
    User findByUsername(String username);
    UserResponse findByEmail(String email);
    List<UserResponse> findAll();
    void deleteById(UUID id);
    void deleteByUsername(String username);
    void deleteAll();
    Double setIncome(String username, double income);
}

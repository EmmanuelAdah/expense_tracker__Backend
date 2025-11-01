package com.expensetracker.services;

import com.expensetracker.data.models.User;
import com.expensetracker.dtos.response.UserResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {

    User save(User user);
    UserResponse findById(long userId);
    User findByUsername(String username);
    UserResponse findByEmail(String email);
    List<UserResponse> findAll();
    void deleteById(long id);
    void deleteByUsername(String username);
    void deleteAll();
    Double setIncome(String username, double income);
}

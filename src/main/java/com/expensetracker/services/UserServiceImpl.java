package com.expensetracker.services;

import com.expensetracker.data.models.Account;
import com.expensetracker.data.models.User;
import com.expensetracker.data.repositories.UserRepository;
import com.expensetracker.dtos.response.UserResponse;
import com.expensetracker.exceptions.UserNotFoundException;
import com.expensetracker.exceptions.invalidIncomeValueException;
import com.expensetracker.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.expensetracker.utils.Mapper.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public UserResponse findById(UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return map(user);
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException("Invalid username or password"));
    }

    @Override
    public UserResponse findByEmail(String email){
        if (!userRepository.existsByEmail(email))
            throw new UserNotFoundException("User not found");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return map(user);
    }

    @Override
    public List<UserResponse> findAll(){
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new UserNotFoundException("User not found");
        return users
                .stream()
                .map(Mapper::map)
                .toList();
    }

    @Transactional
    @Override
    public void deleteById(UUID userId) {
        if (!userRepository.existsById(userId))
            throw new UserNotFoundException("User not found");
        userRepository.deleteById(userId);
    }

    @Transactional
    @Override
    public void deleteByUsername(String username) {
        if (!userRepository.existsByUsername(username))
            throw new UserNotFoundException("User not found");
        userRepository.deleteByUsername(username);
    }

    @Override
    public void deleteAll(){
        if (userRepository.count() == 0)
            throw new UserNotFoundException("No user found!");
        userRepository.deleteAll();
    }

    @Override
    public Double setIncome(String username, double income) {
        if (income <= 0)
            throw new invalidIncomeValueException("Income must be greater than 0");

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getAccount() == null) {
            user.setAccount(new Account());
        }


        user.getAccount().setIncome(income);
        user.getAccount().setBalance(income);
        User savedUser = userRepository.save(user);
        return savedUser.getAccount().getIncome();
    }
}

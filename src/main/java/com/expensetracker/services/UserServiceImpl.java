package com.expensetracker.services;

import com.expensetracker.data.models.User;
import com.expensetracker.data.repositories.UserRepository;
import com.expensetracker.dtos.response.UserResponse;
import com.expensetracker.exceptions.UserNotFoundException;
import com.expensetracker.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.expensetracker.utils.Mapper.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserResponse findById(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return map(user);
    }

    @Override
    public UserResponse findByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
       return map(user);
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

    @Override
    public void deleteById(long userId) {
        if (!userRepository.existsById(userId))
            throw new UserNotFoundException("User not found");
        userRepository.deleteById(userId);
    }

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
    public long setIncome(String username, long income) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setIncome(income);
        User savedUser = userRepository.save(user);
        return savedUser.getIncome();
    }
}

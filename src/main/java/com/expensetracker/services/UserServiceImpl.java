package com.expensetracker.services;

import com.expensetracker.data.models.User;
import com.expensetracker.data.repositories.UserRepository;
import com.expensetracker.dtos.requests.LoginRequest;
import com.expensetracker.dtos.requests.RegisterRequest;
import com.expensetracker.dtos.response.UserResponse;
import com.expensetracker.exceptions.InvalidLoginCredentialsException;
import com.expensetracker.exceptions.UserNotFoundException;
import com.expensetracker.utils.Mapper;
import com.expensetracker.utils.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.expensetracker.utils.Mapper.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserResponse registerUser(RegisterRequest request){
        User user = userRepository.save(mapUser(request));
        return map(user);
    }

    @Override
    public UserResponse findById(Long userId){
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
    public List<UserResponse> findAll(){
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new UserNotFoundException("User not found");
        return users
                .stream()
                .map(Mapper::map)
                .toList();
    }

    @Override
    public UserResponse userLogin(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidLoginCredentialsException("Invalid username or password"));

        if(!PasswordEncoder.checkPassword(loginRequest.getPassword(), user.getPassword()))
            throw new InvalidLoginCredentialsException("Invalid username or password");
        return map(user);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id))
            throw new UserNotFoundException("User not found");
        userRepository.deleteById(id);
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
}

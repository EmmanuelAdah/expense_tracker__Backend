package com.expensetracker.services;

import com.expensetracker.data.models.User;
import com.expensetracker.data.repositories.UsersRepository;
import com.expensetracker.dtos.requests.AddUserRequest;
import com.expensetracker.dtos.requests.LoginRequest;
import com.expensetracker.dtos.response.AddUserResponse;
import com.expensetracker.dtos.response.UserResponse;
import com.expensetracker.exceptions.InvalidLoginCredentialsException;
import com.expensetracker.exceptions.UserNotFoundException;
import com.expensetracker.utils.Mapper;
import com.expensetracker.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.expensetracker.utils.Mapper.*;

@Service
public class UserServiceImpl implements UserService{
    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public AddUserResponse registerUser(AddUserRequest request){
        User user = usersRepository.save(mapUser(request));
        return mapResponse(user);
    }

    @Override
    public Stream<UserResponse> findById(String userId){
        Optional<User> user = usersRepository.findById(userId);
        user.orElseThrow(()-> new RuntimeException("User not found"));
        return user.stream()
                .map(Mapper::map);
    }

    @Override
    public UserResponse findByUsername(String username){
        User user = usersRepository.findByUsername(username);
        if(user == null) throw new UserNotFoundException("User not found");
        return map(user);
    }

    @Override
    public Stream<UserResponse> findAll(){
        List<User> users = usersRepository.findAll();
        if (users.isEmpty()) throw new UserNotFoundException("User not found");
        return users
                .stream()
                .map(Mapper::map);
    }

    @Override
    public UserResponse userLogin(LoginRequest loginRequest) {
        User user = usersRepository.findByUsername(loginRequest.getUsername());
        if(user == null) throw new InvalidLoginCredentialsException("Invalid username or password");

        if(!PasswordEncoder.checkPassword(loginRequest.getPassword(), user.getPassword()))
            throw new InvalidLoginCredentialsException("Invalid username or password");
        return map(user);
    }

    public void deleteById(String id) {
        usersRepository.deleteById(id);
    }
}

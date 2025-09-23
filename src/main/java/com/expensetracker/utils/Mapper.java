package com.expensetracker.utils;

import com.expensetracker.data.models.User;
import com.expensetracker.dtos.requests.AddUserRequest;
import com.expensetracker.dtos.response.AddUserResponse;
import com.expensetracker.dtos.response.UserResponse;

import static com.expensetracker.utils.PasswordEncoder.hashPassword;

public class Mapper {

    public static User mapUser(AddUserRequest request){
        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(hashPassword(request.getPassword()));
        return user;
    }

    public static AddUserResponse mapResponse(User user){
        AddUserResponse response = new AddUserResponse();
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        return response;
    }

    public static UserResponse map(User user){
        UserResponse response = new UserResponse();
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        return response;
    }
}

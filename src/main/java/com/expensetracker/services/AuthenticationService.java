package com.expensetracker.services;

import com.expensetracker.data.models.User;
import com.expensetracker.data.repositories.UserRepository;
import com.expensetracker.dtos.requests.LoginRequest;
import com.expensetracker.dtos.requests.RegistrationRequest;
import com.expensetracker.dtos.response.AuthenticationResponse;
import com.expensetracker.exceptions.InvalidEmailException;
import com.expensetracker.exceptions.InvalidLoginCredentialsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.expensetracker.utils.Validator.isValidEmail;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthenticationResponse register(RegistrationRequest request) {
        if (!isValidEmail(request.getEmail()))
            throw new InvalidEmailException("Invalid email format");

        var user = User.builder()
                .firstname(request.getFirstname().toUpperCase())
                .lastname(request.getLastname().toUpperCase())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        User savedUser = userRepository.save(user);
        var token = jwtService.generateToken(user);

        return authResponse(token, savedUser);
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new InvalidLoginCredentialsException("Invalid username or password");

        var token = jwtService.generateToken(user);
        return authResponse(token, user);
    }

    public AuthenticationResponse authResponse(String token, User user) {
        return AuthenticationResponse
                .builder()
                .token(token)
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}

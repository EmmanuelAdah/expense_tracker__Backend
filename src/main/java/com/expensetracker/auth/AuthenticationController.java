package com.expensetracker.auth;

import com.expensetracker.dtos.requests.LoginRequest;
import com.expensetracker.dtos.requests.RegisterRequest;
import com.expensetracker.dtos.response.AuthenticationResponse;
import com.expensetracker.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tracker/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/register/user")
    public ResponseEntity<AuthenticationResponse> register(@Valid
            @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate/user")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid
            @RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}

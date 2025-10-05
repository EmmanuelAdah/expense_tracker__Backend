package com.expensetracker.auth;

import com.expensetracker.dtos.requests.RegisterRequest;
import com.expensetracker.services.AuthenticationService;
import com.expensetracker.services.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
@Slf4j
class AuthenticationControllerTest {
    @Mock
    private AuthenticationService authService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @InjectMocks
    private AuthenticationController controller;

    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        controller = new AuthenticationController(authService);
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        authenticationManager = new ProviderManager(provider);
    }

    @Test
    void userRegistrationTest() {
        RegisterRequest request = new RegisterRequest();
        request.setFirstname("John");
        request.setLastname("Doe");
        request.setEmail("edo@gmail.com");
        request.setUsername("edo02");
        request.setPassword("12345");
        if (log.isInfoEnabled()) {
            log.info(controller.register(request).toString());
        }
    }

}
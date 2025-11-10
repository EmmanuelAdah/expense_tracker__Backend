package com.expensetracker.auth;

import com.expensetracker.controllers.AuthenticationController;
import com.expensetracker.data.models.User;
import com.expensetracker.data.repositories.UserRepository;
import com.expensetracker.dtos.requests.LoginRequest;
import com.expensetracker.dtos.requests.RegistrationRequest;
import com.expensetracker.dtos.response.AuthenticationResponse;
import com.expensetracker.services.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@Slf4j
class AuthenticationControllerTest {
    @MockitoBean
    private UserRepository userRepository;

    @Mock
    private AuthenticationService authService;

    @InjectMocks
    private AuthenticationController controller;

    @Test
    void userRegistrationTest() {
        RegistrationRequest request = new RegistrationRequest();
        request.setFirstname("John");
        request.setLastname("Doe");
        request.setEmail("edo@gmail.com");
        request.setUsername("edo02");
        request.setPassword("12345");

        Mockito.when(userRepository.save(any(User.class)))
                .thenReturn(new User());

//        when(authService.register(any(RegisterRequest.class))).thenReturn(mockResponse);
        AuthenticationResponse response = controller.register(request).getBody();

        assertNotNull(response);
        assertEquals("mocked-jwt-token", response.getToken());

        log.info("Generated token: {}", response.getToken());
    }

    @Test
    void userAuthenticationTest() {
        LoginRequest request = new LoginRequest();
        request.setUsername("edo02");
        request.setPassword("12345");

        // Mock the expected response
        AuthenticationResponse mockResponse = new AuthenticationResponse();
        mockResponse.setToken("token was generated successfully");

        Mockito.when(authService.authenticate(any(LoginRequest.class)))
                .thenReturn(mockResponse);

//        when(authService.register(any(RegisterRequest.class))).thenReturn(mockResponse);

        AuthenticationResponse response = controller.login(request).getBody();

        assertNotNull(response);
        assertEquals("token was generated successfully", response.getToken());

        log.info("Received token: {}", response.getToken());
    }

}
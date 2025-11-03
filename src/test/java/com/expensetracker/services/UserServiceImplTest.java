package com.expensetracker.services;

import com.expensetracker.data.models.User;
import com.expensetracker.data.repositories.UserRepository;
import com.expensetracker.dtos.response.UserResponse;
import com.expensetracker.exceptions.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Test
    @DisplayName("Test findById returns UserResponse when user is found")
    void testFindByIdReturnsUserResponseWhenUserIsFound() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User mockUser = new User();
        mockUser.setUsername("testuser");
        mockUser.setPassword("hashedpassword");
        mockUser.setEmail("testuser@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // Act
        UserResponse userResponse = userService.findById(userId);

        // Assert
        assertNotNull(userResponse);
        assertEquals(mockUser.getUsername(), userResponse.getUsername());
        assertEquals(mockUser.getEmail(), userResponse.getEmail());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Test findById throws UserNotFoundException when user is not found")
    void testFindByIdThrowsExceptionWhenUserNotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> userService.findById(userId));
        verify(userRepository, times(1)).findById(userId);
    }
}
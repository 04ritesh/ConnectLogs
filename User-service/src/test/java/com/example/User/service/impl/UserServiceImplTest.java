package com.example.User.service.impl;

import com.example.User.entity.User;
import com.example.User.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .email("john@example.com")
                .build();
    }

    @Test
    void createUser_ShouldReturnSavedUser() {
        when(userRepository.existsByEmail(testUser.getEmail())).thenReturn(false);
        when(userRepository.existsByUsername(testUser.getUsername())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User result = userService.createUser(testUser);

        assertNotNull(result);
        assertEquals(testUser.getEmail(), result.getEmail());
        verify(userRepository).save(testUser);
    }

    @Test
    void createUser_ShouldThrowException_WhenEmailExists() {
        when(userRepository.existsByEmail(testUser.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> userService.createUser(testUser));
        
        assertEquals("Email already exists!", exception.getMessage());
    }

    @Test
    void createUser_ShouldThrowException_WhenUsernameExists() {
        when(userRepository.existsByEmail(testUser.getEmail())).thenReturn(false);
        when(userRepository.existsByUsername(testUser.getUsername())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> userService.createUser(testUser));
        
        assertEquals("Username already exists!", exception.getMessage());
    }

    @Test
    void getUserById_ShouldReturnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
    }

    @Test
    void getUserById_ShouldThrowException_WhenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> userService.getUserById(1L));
        
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void getAllUsers_ShouldReturnUserList() {
        List<User> users = Arrays.asList(testUser);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals(testUser.getId(), result.get(0).getId());
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser() {
        User updatedUser = User.builder()
                .firstName("Jane")
                .lastName("Smith")
                .bio("Updated bio")
                .status("ACTIVE")
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        verify(userRepository).save(testUser);
    }

    @Test
    void deleteUser_ShouldCallRepository() {
        userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
    }
}
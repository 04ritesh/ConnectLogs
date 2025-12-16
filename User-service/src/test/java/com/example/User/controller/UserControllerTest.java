package com.example.User.controller;

import com.example.User.entity.User;
import com.example.User.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Test
    void contextLoads() {
        assertNotNull(userService);
    }

    @Test
    void getUserById_ShouldReturnUser() {
        User user = User.builder().id(1L).firstName("John").build();
        when(userService.getUserById(1L)).thenReturn(user);

        User result = userService.getUserById(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John", result.getFirstName());
    }
}
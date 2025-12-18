package com.example.User.controller;

import com.example.User.entity.User;
import com.example.User.service.UserService;
import com.example.User.dto.SignupRequest;
import com.example.User.dto.LoginRequest;
import com.example.User.dto.LoginResponse;
import com.example.User.util.PasswordUtil;
import com.example.User.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;
    private final UserRepository userRepository;

    public UserController(UserService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @GetMapping
    public List<User> all() {
        return service.getAllUsers();
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteUser(id);
        return "User deleted";
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        try {
            if (userRepository.existsByEmail(request.getEmail())) {
                return ResponseEntity.badRequest().body("Email already exists");
            }
            if (userRepository.existsByUsername(request.getUsername())) {
                return ResponseEntity.badRequest().body("Username already exists");
            }
            
            User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(PasswordUtil.hashPassword(request.getPassword()))
                .bio(request.getBio())
                .status("ACTIVE")
                .build();
            
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(new LoginResponse("User created successfully", 
                savedUser.getId(), savedUser.getUsername(), savedUser.getEmail()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating user: " + e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);
            
            if (user == null || user.getPasswordHash() == null) {
                return ResponseEntity.badRequest().body("Invalid email or password");
            }
            
            if (PasswordUtil.verifyPassword(request.getPassword(), user.getPasswordHash())) {
                return ResponseEntity.ok(new LoginResponse("Login successful", 
                    user.getId(), user.getUsername(), user.getEmail()));
            } else {
                return ResponseEntity.badRequest().body("Invalid email or password");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error during login: " + e.getMessage());
        }
    }
}

package com.example.User.dto;

public class LoginResponse {
    private String message;
    private Long userId;
    private String username;
    private String email;

    public LoginResponse(String message, Long userId, String username, String email) {
        this.message = message;
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
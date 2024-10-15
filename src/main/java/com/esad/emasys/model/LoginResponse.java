package com.esad.emasys.model;

public class LoginResponse {
    private User user;
    private String message;
    private String authToken;

    // Getters and Setters
    public LoginResponse(User user, String message, String authToken) {
        this.user = user;
        this.message = message;
        this.authToken = authToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}



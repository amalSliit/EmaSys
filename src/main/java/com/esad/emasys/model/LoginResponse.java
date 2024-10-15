package com.esad.emasys.model;

public class LoginResponse {
    private User user;
    private String message;

    // Getters and Setters
    public LoginResponse(User user, String message) {
        this.user = user;
        this.message = message;
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
}



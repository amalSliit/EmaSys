package com.esad.emasys.model;

public class LoginResponse {
    private Employee emp;
    private String message;
    private String authToken;

    // Getters and Setters
    public LoginResponse(Employee employee, String message, String authToken) {
        this.emp = employee;
        this.message = message;
        this.authToken = authToken;
    }

    public Employee getEmployee() {
        return emp;
    }

    public void setEmployee(Employee employee) {
        this.emp = employee;
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



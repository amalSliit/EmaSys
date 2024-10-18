package com.esad.emasys.model;

public class LeaveResponse {
    private String message;
    private String error;

    // Getters and Setters
    public LeaveResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}



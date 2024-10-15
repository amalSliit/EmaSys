package com.esad.emasys.controller;

import com.esad.emasys.model.LoginRequest;
import com.esad.emasys.model.LoginResponse;
import com.esad.emasys.model.User;
import com.esad.emasys.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse(null, "Email is required"));
        }

        boolean authenticated = authService.isLogin(loginRequest.getEmail());

        if (authenticated) {
            User authUser = authService.getUser(loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new LoginResponse(authUser, "Login Successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, "Invalid credentials"));
        }
    }

}


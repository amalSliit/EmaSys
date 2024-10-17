package com.esad.emasys.controller;

import com.esad.emasys.model.Employee;
import com.esad.emasys.model.LoginRequest;
import com.esad.emasys.model.LoginResponse;
import com.esad.emasys.security.JwtUtil;
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

    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService) {
        this.authService = authService;
        this.jwtUtil = new JwtUtil();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String logEmail = loginRequest.getEmail();

        if (logEmail == null || logEmail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new LoginResponse(null, "Email is required", null));
        }

        boolean authenticated = authService.isLogin(logEmail);

        if (authenticated) {
            Employee authEmp = authService.getEmployee(logEmail);
            String token = "40";

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new LoginResponse(authEmp, "Login Successful", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, "Invalid credentials", null));
        }
    }

}


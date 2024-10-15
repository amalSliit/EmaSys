package com.esad.emasys.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {

    private final SecretKey secretKey;

    // Constructor: Generates a secure key from a byte array
    public JwtUtil() {
        String secKey = "employee_attendance_system_@_2024";
        secretKey = Keys.hmacShaKeyFor(secKey.getBytes(StandardCharsets.UTF_8));
    }

    // Generate JWT token for a given employee ID (without expiration)
    public String generateToken(int employeeId) {
        return Jwts.builder()
                .setSubject(String.valueOf(employeeId))  // Convert employee ID to String
                .setIssuedAt(new Date())
                .signWith(secretKey, SignatureAlgorithm.HS256) // Use the SecretKey
                .compact();
    }

    // Validate the JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true; // Token is valid
        } catch (Exception e) {
            return false; // Token is invalid
        }
    }

    // Get claims from the token
    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    // Get employee ID from the token
    public int getEmployeeId(String token) {
        return Integer.parseInt(getClaims(token).getSubject()); // Convert to int
    }
}


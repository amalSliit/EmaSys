package com.esad.emasys.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {

    private final SecretKey secretKey;

    // Generate a secure key from a byte array
    public JwtUtil() {
        String secKey = "employee_attandanse_system_@_2024";
        secretKey = Keys.hmacShaKeyFor(secKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 hours validity
                .signWith(secretKey, SignatureAlgorithm.HS256)  // Use the SecretKey
                .compact();
    }
}


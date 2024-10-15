package com.esad.emasys.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {

    private final SecretKey secretKey;
    private final String SHA_KEY = "ema_sys";
    
    // Generate a secure key from a byte array
    public JwtUtil() {
        this.secretKey = Keys.hmacShaKeyFor(SHA_KEY.getBytes(StandardCharsets.UTF_8));
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


package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private String secret = "testsecretkey";
    private long expiration = 3600000; // 1 hour

    // --------- DEFAULT CONSTRUCTOR (IMPORTANT) ----------
    public JwtUtil() {
    }

    // --------- FOR TEST CASE ----------
    public JwtUtil(String secret, long expiration, boolean enabled) {
        this.secret = secret;
        this.expiration = expiration;
    }

    // --------- GENERATE TOKEN ----------
    public String generateToken(String email, Long userId, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    // --------- VALIDATE ----------
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // --------- EXTRACT EMAIL ----------
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // --------- EXTRACT ROLE ----------
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // --------- EXTRACT USER ID ----------
    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}

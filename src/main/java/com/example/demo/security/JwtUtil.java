package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private final Key key;
    private final long expiry;
    private final boolean enabled;

    public JwtUtil(String secret, long expiry, boolean enabled) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiry = expiry;
        this.enabled = enabled;
    }

    // ----------------------------
    // TOKEN GENERATION
    // ----------------------------
    public String generateToken(String email, Long userId, String role) {
        if (!enabled) {
            return null;
        }

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ----------------------------
    // VALIDATION
    // ----------------------------
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ----------------------------
    // EXTRACTION METHODS
    // ----------------------------
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

    // ----------------------------
    // INTERNAL
    // ----------------------------
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtException;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {

    private final String secret;
    private final long expiry;
    private final boolean enabled;

    public JwtUtil(String secret, long expiry, boolean enabled) {
        this.secret = secret;
        this.expiry = expiry;
        this.enabled = enabled;
    }

    // -------------------------------------------------
    // GENERATE TOKEN (USED IN TESTS)
    // -------------------------------------------------
    public String generateToken(String username, Long userId, String email, String role) {

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(
                        SignatureAlgorithm.HS256,
                        secret.getBytes(StandardCharsets.UTF_8)
                )
                .compact();
    }

    // -------------------------------------------------
    // VALIDATE TOKEN
    // -------------------------------------------------
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // -------------------------------------------------
    // INTERNAL: EXTRACT CLAIMS
    // -------------------------------------------------
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

    // -------------------------------------------------
    // EXTRACT CLAIMS METHODS (USED IN TESTS)
    // -------------------------------------------------
    public String getEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }

    public String getRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public Long getUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }
}

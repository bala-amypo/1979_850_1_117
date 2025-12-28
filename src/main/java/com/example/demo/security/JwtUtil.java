package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;
    private final long validity; // milliseconds
    private final boolean testMode;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.validity}") long validity,
            @Value("${jwt.testmode}") boolean testMode
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validity = validity;
        this.testMode = testMode;
    }

    public String generateToken(String username, Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        return ((Number) Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("userId")).longValue();
    }

    public String getEmail(String token) {
        return (String) Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("email");
    }

    public String getRole(String token) {
        return (String) Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role");
    }
}

package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ✅ ADD THESE
    private final String secret = "mysecretkeymysecretkeymysecretkey12";
    private final long expiration = 86400000; // 1 day

    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

    public String generateToken(String username, Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            extractUsername(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

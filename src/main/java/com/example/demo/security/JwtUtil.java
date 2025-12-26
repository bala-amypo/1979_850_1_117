package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;
    private final long expiration;
    private final boolean enabled;

    // ✅ Constructor REQUIRED by Test Suite
    public JwtUtil(String secret, long expiration, boolean enabled) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
        this.enabled = enabled;
    }

    // ✅ Generate Token (EXACT signature expected)
    public String generateToken(String username, Long userId, String email, String role) {
        if (!enabled) return "";

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Validate Token
    public boolean validateToken(String token) {
        try {
            if (!enabled) return false;

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Extract Email
    public String getEmail(String token) {
        return getClaims(token).get("email", String.class);
    }

    // ✅ Extract Role
    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    // ✅ Extract User ID
    public Long getUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    // 🔒 Helper Method
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

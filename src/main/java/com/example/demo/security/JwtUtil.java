package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.Date;

public class JwtUtil {

    private String secret;
    private long expiry;
    private boolean enabled;

    // NO-ARG constructor (for Spring)
    public JwtUtil() {}

    // ARG constructor (for Tests)
    public JwtUtil(String secret, long expiry, boolean enabled) {
        this.secret = secret;
        this.expiry = expiry;
        this.enabled = enabled;
    }

    public String generateToken(String username, Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims claims(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public String getEmail(String token) {
        return claims(token).get("email", String.class);
    }

    public String getRole(String token) {
        return claims(token).get("role", String.class);
    }

    public Long getUserId(String token) {
        return claims(token).get("userId", Long.class);
    }
}

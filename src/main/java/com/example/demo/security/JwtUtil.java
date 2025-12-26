package com.example.demo.security;

import io.jsonwebtoken.*;
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

    // Generate token with 4 claims
    public String generateToken(String username, Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException |
                 SignatureException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extract claims
    private Claims claims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
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
        Object value = claims(token).get("userId");
        if (value instanceof Integer) {
            return ((Integer) value).longValue();
        } else if (value instanceof Long) {
            return (Long) value;
        } else {
            return null;
        }
    }
}

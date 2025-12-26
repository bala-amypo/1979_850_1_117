package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    public JwtUtil(String secret, long expiration, long refreshExpiration, boolean enabled) {
    this.secret = secret;
    this.expiration = expiration;
}


    
    private static final String SECRET_KEY =
            "THIS_IS_A_VERY_SECURE_SECRET_KEY_256_BITS_LONG";

    private static final long VALIDITY_IN_MS = 3600000; 

    private final Key key;

    public JwtUtil() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

  
    public String generateToken(String subject, Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_IN_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

   
    public String getEmail(String token) {
        return getClaims(token).get("email", String.class);
    }

    
    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    
    public Long getUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

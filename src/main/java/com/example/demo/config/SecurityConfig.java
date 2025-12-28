package com.example.demo.config;

import com.example.demo.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityConfig {

    private static final String JWT_SECRET = "TestSecretKeyForJWT1234567890";
    private static final long JWT_EXPIRY = 3600000L; // 1 hour
    private static final boolean JWT_ENABLED = true;

    /**
     * Returns PasswordEncoder instance for services.
     */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Returns a JwtUtil instance for services or tests.
     */
    public JwtUtil jwtUtil() {
        return new JwtUtil(JWT_SECRET, JWT_EXPIRY, JWT_ENABLED);
    }
}
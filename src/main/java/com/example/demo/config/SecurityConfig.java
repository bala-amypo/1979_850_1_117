package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    
    @Bean
    public String jwtSecret() {
        return "ThisIsASecretKeyForJwtTokenWhichMustBeAtLeast32Chars";
    }

    @Bean
    public long jwtValidity() {
        return 3600000;
    }

    @Bean
    public boolean jwtTestMode() {
        return false;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserAccountServiceImpl(UserAccountRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccount createUser(UserAccount user) {
        if (user.getRole() == null) user.setRole("USER");
        if (user.getStatus() == null) user.setStatus("ACTIVE");
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        
        return userRepo.save(user);
    }

    @Override
    public UserAccount getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public UserAccount updateUserStatus(Long id, String status) {
        UserAccount user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setStatus(status);
            return userRepo.save(user);
        }
        return null;
    }

    @Override
    public List<UserAccount> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Optional<UserAccount> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
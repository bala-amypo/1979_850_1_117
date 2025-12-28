package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository repo;

    public UserAccountServiceImpl(UserAccountRepository repo) {
        this.repo = repo;
    }

    // -------- CREATE ----------
    @Override
    public UserAccount createUser(UserAccount user) {
        return repo.save(user); // ❌ null return illa
    }

    // -------- GET BY ID ----------
    @Override
    public UserAccount getUserById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // -------- GET ALL ----------
    @Override
    public List<UserAccount> getAllUsers() {
        return repo.findAll(); // test expect this
    }

    // -------- UPDATE STATUS ----------
    @Override
    public UserAccount updateStatus(Long id, String status) {
        UserAccount user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(status);
        return repo.save(user);
    }
}

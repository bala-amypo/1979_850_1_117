package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    private final UserAccountRepository repo;

    public UserAccountController(UserAccountRepository repo) {
        this.repo = repo;
    }

    // ✅ TEST EXPECTS THIS METHOD
    @PostMapping
    public UserAccount create(@RequestBody UserAccount user) {
        return repo.save(user);
    }

    @GetMapping
    public List<UserAccount> all() {
        return repo.findAll();
    }
}

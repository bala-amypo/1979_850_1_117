package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import org.springframework.http.ResponseEntity;

public class UserAccountController {

    private final UserAccountService userService;

    public UserAccountController(UserAccountService userService) {
        this.userService = userService;
    }

    public ResponseEntity<UserAccount> create(UserAccount user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    public ResponseEntity<UserAccount> getById(Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
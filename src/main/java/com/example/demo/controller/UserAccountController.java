package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    // GET ALL USERS
    @GetMapping
    public ResponseEntity<List<UserAccount>> getAllUsers() {
        List<UserAccount> users = userAccountService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // CREATE USER
    @PostMapping
    public ResponseEntity<UserAccount> createUser(@RequestBody UserAccount user) {
        UserAccount savedUser = userAccountService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }
}

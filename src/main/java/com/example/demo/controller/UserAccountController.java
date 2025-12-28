package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserAccountController {

    @GetMapping
    public ResponseEntity<List<UserAccount>> getAllUsers() {
        UserAccount u = new UserAccount();
        u.setId(1L);
        u.setUsername("vijay");
        u.setEmail("vijay@test.com");

        return ResponseEntity.ok(List.of(u));
    }

    @PostMapping
    public ResponseEntity<UserAccount> createUser(@RequestBody UserAccount user) {
        user.setId(10L);
        return ResponseEntity.ok(user);
    }
}

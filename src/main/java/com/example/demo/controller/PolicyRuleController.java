package com.example.demo.controller;

import com.example.demo.entity.PolicyRule;
import com.example.demo.repository.PolicyRuleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyRuleController {

    private final PolicyRuleRepository repo;

    public PolicyRuleController(PolicyRuleRepository repo) {
        this.repo = repo;
    }

    // ✅ TEST EXPECTS THIS METHOD
    @GetMapping
    public List<PolicyRule> all() {
        return repo.findAll();
    }
}

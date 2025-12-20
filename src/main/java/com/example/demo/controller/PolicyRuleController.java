package com.example.demo.controller;

import com.example.demo.entity.PolicyRule;
import com.example.demo.service.PolicyRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class PolicyRuleController {

    private final PolicyRuleService ruleService;

    public PolicyRuleController(PolicyRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<PolicyRule> create(@RequestBody PolicyRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PolicyRule> update(@PathVariable Long id, @RequestBody PolicyRule updated) {
        return ResponseEntity.ok(ruleService.updateRule(id, updated));
    }

    @GetMapping("/active")
    public ResponseEntity<List<PolicyRule>> getActive() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }

    @GetMapping
    public ResponseEntity<List<PolicyRule>> getAll() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}

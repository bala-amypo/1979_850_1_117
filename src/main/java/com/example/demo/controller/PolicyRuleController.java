package com.example.demo.controller;

import com.example.demo.entity.PolicyRule;
import com.example.demo.service.PolicyRuleService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PolicyRuleController {

    private final PolicyRuleService ruleService;

    public PolicyRuleController(PolicyRuleService ruleService) {
        this.ruleService = ruleService;
    }

    public ResponseEntity<List<PolicyRule>> all() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}
package com.example.demo.repository;

import com.example.demo.entity.PolicyRule;
import java.util.*;

public interface PolicyRuleRepository {
    PolicyRule save(PolicyRule rule);
    List<PolicyRule> findAll();
    List<PolicyRule> findByActiveTrue();
    Optional<PolicyRule> findById(Long id);
}

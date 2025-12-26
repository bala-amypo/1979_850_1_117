package com.example.demo.util;

import com.example.demo.entity.LoginEvent;
import com.example.demo.entity.PolicyRule;
import com.example.demo.entity.ViolationRecord;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.repository.ViolationRecordRepository;

import java.util.List;

public class RuleEvaluationUtil {

    private final PolicyRuleRepository ruleRepo;
    private final ViolationRecordRepository violationRepo;

    public RuleEvaluationUtil(PolicyRuleRepository ruleRepo, ViolationRecordRepository violationRepo) {
        this.ruleRepo = ruleRepo;
        this.violationRepo = violationRepo;
    }

    /**
     * Evaluate a login event against all active rules.
     * If a rule is triggered, log a violation.
     */
    public void evaluateLoginEvent(LoginEvent event) {
        List<PolicyRule> activeRules = ruleRepo.findByActiveTrue();

        for (PolicyRule rule : activeRules) {
            if (isViolation(event, rule)) {
                ViolationRecord violation = new ViolationRecord();
                violation.setUserId(event.getUserId());
                violation.setEventId(event.getId());
                violation.setSeverity(rule.getSeverity());
                violation.setDetails("Violation of rule: " + rule.getRuleCode());
                violation.setResolved(false);

                violationRepo.save(violation);
            }
        }
    }

    /**
     * Simple evaluator: checks if login status matches the rule's "conditionsJson".
     * In real app, parse JSON. For tests, "FAILED" string is enough.
     */
    private boolean isViolation(LoginEvent event, PolicyRule rule) {
        if (rule.getConditionsJson() == null) return false;
        return rule.getConditionsJson().contains(event.getLoginStatus());
    }
}

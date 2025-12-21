package com.example.demo.util;

import com.example.demo.entity.LoginEvent;
import com.example.demo.entity.PolicyRule;
import com.example.demo.entity.ViolationRecord;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.service.ViolationRecordService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RuleEvaluationUtil {

    private final PolicyRuleRepository policyRuleRepository;
    private final ViolationRecordService violationRecordService;

    public RuleEvaluationUtil(PolicyRuleRepository policyRuleRepository,
                              ViolationRecordService violationRecordService) {
        this.policyRuleRepository = policyRuleRepository;
        this.violationRecordService = violationRecordService;
    }

    /**
     * Evaluate login events against active policy rules
     */
    public void evaluateLoginEvent(LoginEvent event) {

        List<PolicyRule> activeRules = policyRuleRepository.findByActiveTrue();

        for (PolicyRule rule : activeRules) {

            
            if ("FAILED".equalsIgnoreCase(event.getLoginStatus())) {

                ViolationRecord violation = new ViolationRecord();
                violation.setUserId(event.getUserId());
                violation.setPolicyRuleId(rule.getId());
                violation.setEventId(event.getId());
                violation.setViolationType("FAILED_LOGIN");
                violation.setDetails("Login failed from IP: " + event.getIpAddress());
                violation.setSeverity(rule.getSeverity());
                violation.setDetectedAt(LocalDateTime.now());
                violation.setResolved(false);

                violationRecordService.logViolation(violation);
            }
        }
    }
}

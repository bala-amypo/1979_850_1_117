package com.example.demo.util;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;

public class RuleEvaluationUtil {

    private final PolicyRuleRepository ruleRepo;
    private final ViolationRecordRepository violationRepo;

    public RuleEvaluationUtil(PolicyRuleRepository r, ViolationRecordRepository v) {
        this.ruleRepo = r;
        this.violationRepo = v;
    }

    public void evaluateLoginEvent(LoginEvent ev) {
        for (PolicyRule r : ruleRepo.findByActiveTrue()) {
            if (r.getConditionsJson() != null &&
                ev.getLoginStatus() != null &&
                r.getConditionsJson().contains(ev.getLoginStatus())) {

                ViolationRecord vr = new ViolationRecord();
                vr.setSeverity(r.getSeverity());
                vr.setResolved(false);
                violationRepo.save(vr);
            }
        }
    }
}

package com.example.demo.serviceimpl;

import com.example.demo.entity.PolicyRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.service.PolicyRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyRuleServiceImpl implements PolicyRuleService {

    private final PolicyRuleRepository ruleRepo;

    public PolicyRuleServiceImpl(PolicyRuleRepository ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    @Override
    public PolicyRule createRule(PolicyRule rule) {
        if (ruleRepo.findByRuleCode(rule.getRuleCode()).isPresent()) {
            throw new BadRequestException("Rule code already exists");
        }
        return ruleRepo.save(rule);
    }

    @Override
    public PolicyRule updateRule(Long id, PolicyRule updated) {
        PolicyRule existing = ruleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PolicyRule", id));
        existing.setDescription(updated.getDescription());
        existing.setSeverity(updated.getSeverity());
        existing.setConditionsJson(updated.getConditionsJson());
        existing.setActive(updated.getActive());
        return ruleRepo.save(existing);
    }

    @Override
    public List<PolicyRule> getActiveRules() {
        return ruleRepo.findByActiveTrue();
    }

    @Override
    public Optional<PolicyRule> getRuleByCode(String ruleCode) {
        return ruleRepo.findByRuleCode(ruleCode);
    }

    @Override
    public List<PolicyRule> getAllRules() {
        return ruleRepo.findAll();
    }
}

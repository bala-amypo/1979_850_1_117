package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "policy_rules",
        uniqueConstraints = @UniqueConstraint(columnNames = "rule_code")
)
public class PolicyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_code", nullable = false, unique = true)
    private String ruleCode;

    @Column(nullable = false)
    private String description;

    private String severity; // LOW / MEDIUM / HIGH / CRITICAL

    @Lob
    @Column(name = "conditions_json")
    private String conditionsJson;

    private Boolean active;

    // ✅ No-arg constructor
    public PolicyRule() {
        this.active = true;
    }

    // ✅ Parameterized constructor
    public PolicyRule(String ruleCode,
                      String description,
                      String severity,
                      String conditionsJson,
                      Boolean active) {

        this.ruleCode = ruleCode;
        this.description = description;
        this.severity = severity;
        this.conditionsJson = conditionsJson;
        this.active = active != null ? active : true;
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getConditionsJson() {
        return conditionsJson;
    }

    public void setConditionsJson(String conditionsJson) {
        this.conditionsJson = conditionsJson;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

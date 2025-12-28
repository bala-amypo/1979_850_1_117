package com.example.demo.dto;

public class UserAccountDTO {
    public interface PolicyRuleRepository extends JpaRepository<PolicyRule, Long> {
    List<PolicyRule> findByActiveTrue();
}

}

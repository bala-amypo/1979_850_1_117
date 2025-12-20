package com.example.demo.controller;

import com.example.demo.entity.ViolationRecord;
import com.example.demo.service.ViolationRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/violations")
public class ViolationRecordController {

    private final ViolationRecordService violationService;

    public ViolationRecordController(ViolationRecordService violationService) {
        this.violationService = violationService;
    }

    @PostMapping
    public ResponseEntity<ViolationRecord> log(@RequestBody ViolationRecord violation) {
        return ResponseEntity.ok(violationService.logViolation(violation));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ViolationRecord>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(violationService.getViolationsByUser(userId));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<ViolationRecord> markResolved(@PathVariable Long id) {
        return ResponseEntity.ok(violationService.markResolved(id));
    }

    @GetMapping("/unresolved")
    public ResponseEntity<List<ViolationRecord>> getUnresolved() {
        return ResponseEntity.ok(violationService.getUnresolvedViolations());
    }

    @GetMapping
    public ResponseEntity<List<ViolationRecord>> getAll() {
        return ResponseEntity.ok(violationService.getAllViolations());
    }
}

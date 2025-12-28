package com.example.demo.controller;

import com.example.demo.entity.ViolationRecord;
import com.example.demo.service.ViolationRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/violations")
public class ViolationRecordController {

    private final ViolationRecordService violationService;

    public ViolationRecordController(ViolationRecordService violationService) {
        this.violationService = violationService;
    }

    @PostMapping("/log")
    public ResponseEntity<ViolationRecord> log(@RequestBody ViolationRecord violation) {
        return ResponseEntity.ok(violationService.logViolation(violation));
    }

    @GetMapping("/unresolved")
    public ResponseEntity<List<ViolationRecord>> unresolved() {
        return ResponseEntity.ok(violationService.getUnresolvedViolations());
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<ViolationRecord> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(violationService.markResolved(id));
    }
}

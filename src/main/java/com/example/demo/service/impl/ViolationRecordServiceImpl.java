package com.example.demo.service.impl;

import com.example.demo.entity.ViolationRecord;
import com.example.demo.repository.ViolationRecordRepository;
import com.example.demo.service.ViolationRecordService;
import java.util.List;

public class ViolationRecordServiceImpl implements ViolationRecordService {

    private final ViolationRecordRepository repo;

    public ViolationRecordServiceImpl(ViolationRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public ViolationRecord logViolation(ViolationRecord v) {
        return repo.save(v);
    }

    @Override
    public List<ViolationRecord> getUnresolvedViolations() {
        return repo.findByResolvedFalse();
    }

    @Override
    public ViolationRecord markResolved(Long id) {
        ViolationRecord v = repo.findById(id).orElse(null);
        v.setResolved(true);
        return repo.save(v);
    }
}

package com.example.demo.controller;

import com.example.demo.entity.DeviceProfile;
import com.example.demo.repository.DeviceProfileRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
public class DeviceProfileController {

    private final DeviceProfileRepository repo;

    public DeviceProfileController(DeviceProfileRepository repo) {
        this.repo = repo;
    }

    // ✅ TEST EXPECTS THIS METHOD
    @GetMapping("/{deviceId}")
    public Optional<DeviceProfile> lookup(@PathVariable String deviceId) {
        return repo.findByDeviceId(deviceId);
    }
}

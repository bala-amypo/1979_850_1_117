package com.example.demo.controller;

import com.example.demo.entity.DeviceProfile;
import com.example.demo.service.DeviceProfileService;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class DeviceProfileController {

    private final DeviceProfileService deviceService;

    public DeviceProfileController(DeviceProfileService deviceService) {
        this.deviceService = deviceService;
    }

    public ResponseEntity<DeviceProfile> lookup(String deviceId) {
        Optional<DeviceProfile> device = deviceService.findByDeviceId(deviceId);
        return ResponseEntity.ok(device.orElse(null));
    }
}
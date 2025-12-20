package com.example.demo.controller;

import com.example.demo.entity.DeviceProfile;
import com.example.demo.service.DeviceProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceProfileController {

    private final DeviceProfileService deviceProfileService;

    public DeviceProfileController(DeviceProfileService deviceProfileService) {
        this.deviceProfileService = deviceProfileService;
    }

    @PostMapping
    public ResponseEntity<DeviceProfile> registerDevice(@RequestBody DeviceProfile device) {
        return ResponseEntity.ok(deviceProfileService.registerDevice(device));
    }

    @PutMapping("/{id}/trust")
    public ResponseEntity<DeviceProfile> updateTrust(@PathVariable Long id, @RequestParam boolean trust) {
        return ResponseEntity.ok(deviceProfileService.updateTrustStatus(id, trust));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DeviceProfile>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(deviceProfileService.getDevicesByUser(userId));
    }

    @GetMapping("/lookup/{deviceId}")
    public ResponseEntity<DeviceProfile> getByDevice(@PathVariable String deviceId) {
        return ResponseEntity.of(deviceProfileService.findByDeviceId(deviceId));
    }
}

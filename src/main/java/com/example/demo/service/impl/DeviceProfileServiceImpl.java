package com.example.demo.service.impl;

import com.example.demo.entity.DeviceProfile;
import com.example.demo.repository.DeviceProfileRepository;
import com.example.demo.service.DeviceProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceProfileServiceImpl implements DeviceProfileService {

    private final DeviceProfileRepository deviceRepo;

    public DeviceProfileServiceImpl(DeviceProfileRepository deviceRepo) {
        this.deviceRepo = deviceRepo;
    }

    @Override
    public DeviceProfile registerDevice(DeviceProfile device) {
        return deviceRepo.save(device);
    }

    @Override
    public DeviceProfile updateTrustStatus(Long id, boolean trust) {
        DeviceProfile device = deviceRepo.findById(id).orElse(null);
        if (device != null) {
            device.setIsTrusted(trust);
            return deviceRepo.save(device);
        }
        return null;
    }

    @Override
    public List<DeviceProfile> getDevicesByUser(Long userId) {
        return deviceRepo.findByUserId(userId);
    }

    @Override
    public Optional<DeviceProfile> findByDeviceId(String deviceId) {
        return deviceRepo.findByDeviceId(deviceId);
    }
}
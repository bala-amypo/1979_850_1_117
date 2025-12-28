package com.example.demo.repository;

import com.example.demo.entity.DeviceProfile;
import java.util.*;

public interface DeviceProfileRepository {
    DeviceProfile save(DeviceProfile device);
    Optional<DeviceProfile> findById(Long id);
    Optional<DeviceProfile> findByDeviceId(String deviceId);
}
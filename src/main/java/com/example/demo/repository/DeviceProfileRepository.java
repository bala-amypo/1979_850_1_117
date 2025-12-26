package com.example.demo.repository;

import com.example.demo.entity.DeviceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceProfileRepository extends JpaRepository<DeviceProfile, Long> {

    // Find a device by its unique deviceId
    Optional<DeviceProfile> findByDeviceId(String deviceId);

    // Find all devices registered by a particular user
    List<DeviceProfile> findByUserId(Long userId);

}

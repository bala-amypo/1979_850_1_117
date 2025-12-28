package com.example.demo.repository;

import com.example.demo.entity.DeviceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceProfileRepository extends JpaRepository<DeviceProfile, Long> {
    Optional<DeviceProfile> findByDeviceId(String deviceId);
}

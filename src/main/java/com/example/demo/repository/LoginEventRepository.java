package com.example.demo.repository;

import com.example.demo.entity.LoginEvent;
import java.util.List;

public interface LoginEventRepository {
    LoginEvent save(LoginEvent event);
    List<LoginEvent> findByUserId(Long userId);
    List<LoginEvent> findByUserIdAndLoginStatus(Long userId, String status);
}

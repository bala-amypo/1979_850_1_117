package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl {

    public UserAccount save(UserAccount u) {

        
        System.out.println(u.getPassword());

        u.setStatus("ACTIVE");

        return u;
    }
}

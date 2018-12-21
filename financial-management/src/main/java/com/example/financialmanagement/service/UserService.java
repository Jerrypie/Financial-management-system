package com.example.financialmanagement.service;

import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
}

package com.example.financialmanagement.controll;

import java.util.List;

import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControll {
    @Autowired  
    private UserRepository studentRepository;   
    @RequestMapping("/test")  
    public List<User> getAllUsers(){  
        return studentRepository.findAll();  
    }  
}
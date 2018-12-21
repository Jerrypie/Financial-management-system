package com.example.financialmanagement.controller;

import java.util.List;

import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.UserRepository;

import com.example.financialmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//测试数据库用
public class UserController {
    @Autowired
    public UserService userService;
    @RequestMapping("/test")  
    public void getAllUsers(){
        User user = new User();
        user.setPassword("asdfasdf");
        user.setUsername("asdfasd");
        user.setUserid(5);
        user.setEmail("asdfasdfffff");
        userService.save(user);
    }  
}
package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.User;
import com.example.financialmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
//测试数据库用
public class UserController {
    @Resource
    public UserService userService;

    @RequestMapping("/test")
    public void getAllUsers() {
        User user = new User();
        user.setPassword("password");
        user.setUsername("username");
        user.setEmail("email");
        userService.save(user);
    }
}
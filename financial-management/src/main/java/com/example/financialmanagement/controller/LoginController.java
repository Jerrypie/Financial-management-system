package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.User;
import com.example.financialmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.validation.constraints.Email;

@Controller
public class LoginController {
    @Resource
    public UserService userService;

    //接收登录信息，return到MainController
    @PostMapping("/login")
    public String login(String UserName, String Password) {
        if(UserName != null && UserName.length() != 0
            && Password != null && Password.length() != 0){

//            //如果数据库中有这条记录
//            if (){
//
//            }
        }
        return "hello";
    }
}
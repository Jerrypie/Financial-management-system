package com.example.financialmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    //接收登录信息，return到MainController
    @PostMapping("/login")
    public String login(String UserId, String Password) {
        return "/loginError";
    }
}
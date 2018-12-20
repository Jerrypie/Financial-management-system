package com.example.financialmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping({"", "/", "/index"})
    public String IndexPage() {
        return "index.html";
    }

    @PostMapping("/login")
    public String login(String UserId, String Password) {
        if ("xsc".equals(UserId) && "caiji".equals(Password)) {
            return "/main";
        }
        return "/loginError";
    }
}
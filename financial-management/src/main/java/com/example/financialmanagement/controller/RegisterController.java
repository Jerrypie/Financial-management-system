package com.example.financialmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
    @RequestMapping("/signup")
    public String SignupPage() {
        return "signup";
    }
}

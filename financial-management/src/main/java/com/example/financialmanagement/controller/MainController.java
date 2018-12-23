package com.example.financialmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @ResponseBody
    @RequestMapping("/loginError")
    public String loginError() {
        return "Password is wrong! Please login again!";
    }
}
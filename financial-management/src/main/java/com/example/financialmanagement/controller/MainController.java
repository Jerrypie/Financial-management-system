package com.example.financialmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @RequestMapping("/main")
    public String hello() {
        return "main";
    }

    @ResponseBody
    @RequestMapping("/loginError")
    public String loginError() {
        return "Password is wrong! Please login again!";
    }
}
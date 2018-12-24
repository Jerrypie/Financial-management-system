package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.BasicRecordRepository;
import com.example.financialmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MainController {

    @Resource
    private UserService userService;

    @Resource
    private BasicRecordRepository basicRecordRepository;


    @ResponseBody
    @RequestMapping("/loginError")
    public String loginError() {
        return "Password is wrong! Please login again!";
    }
}
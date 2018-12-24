package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.BasicRecordRepository;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {








    @ResponseBody
    @RequestMapping("/loginError")
    public String loginError() {
        return "Password is wrong! Please login again!";
    }
}
package com.example.financialmanagement.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.example.financialmanagement.service.MainService;
import com.example.financialmanagement.service.RecordService;
import com.example.financialmanagement.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Resource
    private UserService userService;

    @Resource
    private RecordService recordService;

    @Resource
    private MainService mainService;

    // 注册
    @PostMapping("/register.action")
    public String registerUser(String UserName, String Password, String Email, HttpServletRequest request)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String[] msg = new String[2];
        msg = userService.registerUser(UserName, Password, Email);
        request.setAttribute("msg", msg[1]);
		return msg[0];
    }
    //登录
    @PostMapping("/login")
    public String login(String UserName, String Password, HttpServletRequest request)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String[] msg = new String[2];
        msg = userService.login(UserName, Password, request);
        request.setAttribute("msg", msg[1]);
		return msg[0];
    }
    //退出登录
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("UserObj");//删除session中UserObj
        return "index.html";
    }
}//end controller
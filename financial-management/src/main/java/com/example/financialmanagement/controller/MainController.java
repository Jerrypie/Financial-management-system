package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.User;
import com.example.financialmanagement.service.MainService;
import com.example.financialmanagement.service.RecordService;
import com.example.financialmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Controller
public class MainController {

    @Resource
    private UserService userService;

    @Resource
    private RecordService recordService;

    @Resource
    private MainService mainService;

    private User user;

    // 注册
    @PostMapping("/register")
    public String registerUser(String UserName, String Password, String Email, HttpServletRequest request)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String[] msg;
        msg = userService.registerUser(UserName, Password, Email);
        request.setAttribute("msg", msg[1]);
        return msg[0];
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register.html";
    }


    //登录
    @PostMapping("/login")
    public String login(String UserName, String Password, HttpServletRequest request)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String[] msg;
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

    @GetMapping("/classStatic")
    public String classPage() {
        return "classStatic";
    }


    @GetMapping("/figure1")
    public String figure1() {
        return "figure1";
    }


    @GetMapping("/figure2")
    public String figure2() {
        return "figure2";
    }

}//end controller
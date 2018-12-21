package com.example.financialmanagement.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.example.financialmanagement.model.User;
import com.example.financialmanagement.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Resource
    public UserService userService;

    //接收登录信息，return到MainController
    @PostMapping("/registe.action")
    public String login(String UserName, String Password,String Email, HttpServletRequest request) {

        //验证用户名和密码是否为空
        if(UserName != null && UserName.length() != 0
            && Password != null && Password.length() != 0){

            //验证数据库中是否有这条记录
            User user_db = userService.getByUsername(UserName);
            if (user_db != null){
                //如果用户存在，用户重新输入
                String msg = "输入的用户名已存在，请重新输入";
                request.setAttribute("msg",msg);
                return "signup";
            }else {
                //用户不存在进行注册
                User user_new = new User();
                user_new.setUsername(UserName);
                user_new.setPassword(Password);
                user_new.setEmail(Email);
                userService.save(user_new);
                String msg = "注册成功，请登录";
                request.setAttribute("msg",msg);
                return "index";
            }
        }else{
            String msg = "输入的密码或用户名为空";
            request.setAttribute("msg",msg);
            return "signup";
        }
    }
}
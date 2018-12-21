package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.User;
import com.example.financialmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;

@Controller
public class LogstatusController {
    @Resource
    public UserService userService;

    //接收登录信息，return到MainController
    @PostMapping("/login.action")
    public String login(String UserName, String Password, HttpServletRequest request) {

        //验证用户名和密码是否为空
        if(UserName != null && UserName.length() != 0
            && Password != null && Password.length() != 0){

            //验证数据库中是否有这条记录
            User user_db = userService.getByUsernameAndPassword(UserName,Password);
            if (user_db != null){
                //如果用户存在，加入到session 中供后面使用
                HttpSession session = request.getSession();
                session.setAttribute("UserObj", user_db);
                //跳转到主函数
                return "redirect:/main";
            }else {
                String msg = "输入的密码或用户名有误，请重新输入";
                request.setAttribute("msg",msg);
                return "index";
            }
        }else{
            String msg = "输入的密码或用户名为空";
            request.setAttribute("msg",msg);
            return "index";
        }
    }

    //用户退出登录,return到index
    @PostMapping("/logout.action")
    public String logout (HttpServletRequest request){
        request.getSession().removeAttribute("UserObj");//删除session中UserObj
        return "index";
    }
}
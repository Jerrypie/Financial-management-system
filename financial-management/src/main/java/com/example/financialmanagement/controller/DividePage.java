package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.PageList;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.service.MainService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DividePage {

    @Resource
    private MainService mainService;

    @GetMapping("/dividePage.action")
    public String dividePage(@RequestParam(value="currentPage") int currentPage, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("UserObj");

        PageList pageObj =  mainService.dividePage(currentPage, user.getUsername(),3);
        model.addAttribute("pageList",pageObj);
        return "main.html";
    }
}

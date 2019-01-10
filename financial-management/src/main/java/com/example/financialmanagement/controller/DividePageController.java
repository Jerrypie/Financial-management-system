package com.example.financialmanagement.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.PageList;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.service.MainService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DividePageController {

    @Resource
    private MainService mainService;

    @GetMapping("/dividePage.action")
    public String dividePage(@RequestParam(value = "currentPage") int currentPage, Model model,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("UserObj");

        List<BasicRecord> records = mainService.getAllSortedRecordsByusername(user.getUsername());
        PageList pageObj = mainService.getPage(records,currentPage,10);
        model.addAttribute("pageList",pageObj);
        return "main.html";
    }

}

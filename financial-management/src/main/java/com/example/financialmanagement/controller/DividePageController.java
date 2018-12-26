package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.PageList;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.service.MainService;
import com.example.financialmanagement.service.RecordService;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DividePageController {

    @Resource
    private MainService mainService;

    @GetMapping("/dividePage.action")
    public String dividePage(@RequestParam(value="currentPage") int currentPage, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("UserObj");

        List<BasicRecord> records = mainService.getAllSortedRecordsByusername(user.getUsername());
        PageList pageObj = mainService.getPage(records,currentPage,3);
        model.addAttribute("pageList",pageObj);
        return "main.html";
    }

    @GetMapping("/divideTypePage.action")
    public String divideTypePage(@RequestParam(value="currentPage") int currentPage,
                                 @RequestParam(value="pageType") int pageType, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);

        List<BasicRecord> records = mainService.getAllCategoryRecord(pageType);
        PageList pageObj = mainService.getPage(records,currentPage,3);
        model.addAttribute("pageList",pageObj);
        return "main.html";
    }

    @GetMapping("/divideTimePage.action")
    public String divideTimePage(@RequestParam(value="currentPage") int currentPage,
                                 @RequestParam(value="pageTime") int pageTime, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);

        List<BasicRecord> records = mainService.getAllSortedRecordsOfUser();
        RecordService recordService = new RecordService();

        if(pageTime == 1){
            records = recordService.recordsOfThreeDays(records);
        }
        else if(pageTime == 2 ){
            records = recordService.recordsOfThisWeek(records);
        }
        else if(pageTime == 3){
            records = recordService.recordsOfThisMonth(records);
        }
        else if(pageTime == 4){
            records = recordService.recordsOfThisYear(records);
        }

        PageList pageObj = mainService.getPage(records,currentPage,3);
        model.addAttribute("pageList",pageObj);
        return "main.html";
    }
}

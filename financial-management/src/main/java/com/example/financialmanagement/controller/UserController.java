package com.example.financialmanagement.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.PageList;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.service.MainService;
import com.example.financialmanagement.service.RecordService;
import com.example.financialmanagement.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
//测试数据库用
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private RecordService recordService;

    @Resource
    private MainService mainService;

    private User user;

    @RequestMapping("/main")
    public String MainPage(HttpServletRequest request, Model model) {
        // 从session 中取出User
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);

        // 默认加载第一页
        List<BasicRecord> allrecords = mainService.getAllSortedRecordsByusername(user.getUsername());
        PageList pageList = mainService.getPage(allrecords, 1, 10);
        model.addAttribute("pageList", pageList);
        return "main.html";
    }

    // 按类别查询
    @RequestMapping("/type")
    public String EachTypePage(@RequestParam("inType") int type, HttpServletRequest request, Model model) {
        // 从session 中取出User
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);

        //默认加载第一页
        List<BasicRecord> records = mainService.getAllCategoryRecord(type);
        PageList pageList = mainService.getPage(records,1,3);
        model.addAttribute("pageList", pageList);
        return "main.html";
    }
    
    //按时间查询
    @RequestMapping("/time")
    public String EachTimePage(@RequestParam("inTime") int time,HttpServletRequest request, Model model) {
        //从session 中取出User
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);
        List<BasicRecord> records = mainService.getAllSortedRecordsOfUser();
        RecordService recordService = new RecordService();

        if(time == 1){
            records = recordService.recordsOfThreeDays(records);
        }
        else if(time == 2 ){
            records = recordService.recordsOfThisWeek(records);
        }
        else if(time == 3){
            records = recordService.recordsOfThisMonth(records);
        }
        else if(time == 4){
            records = recordService.recordsOfThisYear(records);
        }

        PageList pageList = mainService.getPage(records,1,3);
        model.addAttribute("pageList", pageList);
        return "main.html";
    }

}
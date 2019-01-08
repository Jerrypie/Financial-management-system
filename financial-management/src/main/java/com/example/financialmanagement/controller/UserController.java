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
        PageList pageList = mainService.getPage(allrecords, 1, 3);
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

    @RequestMapping(value = "/addIncomeRecordOfUser.action", method = RequestMethod.POST)
    public String addIncomeBasicRecord(@RequestParam("inValue") double value,
                                       @RequestParam("inTime") String Originrecordtime,
                                       @RequestParam("inType") int category,
                                       @RequestParam("inOther") String other, HttpServletRequest request) throws Exception {

        BasicRecord basicRecord = new BasicRecord();
        SimpleDateFormat StrParse = new SimpleDateFormat("yyyy-MM-dd");
        Date date = StrParse.parse(Originrecordtime);
        Calendar recordtime = Calendar.getInstance();
        recordtime.setTime(date);

        basicRecord.setRecordtime(recordtime);
        basicRecord.setValue(value);
        basicRecord.setCategory(category);
        basicRecord.setOther(other);
        recordService.updateByOneRecord(basicRecord);
        //取出用户
        HttpSession session = request.getSession();
        User user0 = (User) session.getAttribute("UserObj");

        user = userService.getByUsername(user0.getUsername());
        user.addRecords(basicRecord);
        userService.save(user);
        return "redirect:/main";
    }

    @RequestMapping(value = "/addOutcomeRecordOfUser.action", method = RequestMethod.POST)
    public String addOutcomeBasicRecord(@RequestParam("inValue") double value,
                                        @RequestParam("inTime") String Originrecordtime,
                                        @RequestParam("inType") int category,
                                        @RequestParam("inOther") String other, HttpServletRequest request) throws Exception {
        return addIncomeBasicRecord(-value, Originrecordtime, category, other, request);
    }

    @RequestMapping(value = "/deleteRecordOfUser.action", method = RequestMethod.POST)
    public String deleteRecordOfUser(@RequestParam("inRecords") int[] records, HttpServletRequest request) throws Exception{
        if (records != null){
            int i= 0;
            for (int recordnum : records) {
                System.out.print(i);
                i = i + 1;
                System.out.println(recordnum);
                recordService.deleteByRecordnum(recordnum);
            }
        }
        return "redirect:/main";
    }
}
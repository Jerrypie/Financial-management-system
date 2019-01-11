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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
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
        PageList pageList = mainService.getPage(allrecords, 1, 7);
        model.addAttribute("pageList", pageList);
        return "main.html";
    }

    
    //输入日期的String格式，输出Calendar格式的日期
    private Calendar DateTransform(String Originrecordtime) throws Exception {
        SimpleDateFormat StrParse = new SimpleDateFormat("yyyy-MM-dd");
        Date date = StrParse.parse(Originrecordtime);
        Calendar recordtime = Calendar.getInstance();
        recordtime.setTime(date);
        return recordtime;
    }

    //添加收入
    @PostMapping(value = "/addIncomeRecordOfUser.action")
    public String addIncomeBasicRecord(@RequestParam("inValue") double value,
                                       @RequestParam("inTime") String Originrecordtime,
                                       @RequestParam("inType") int category,
                                       @RequestParam("inOther") String other, HttpServletRequest request) throws Exception {

        BasicRecord basicRecord = new BasicRecord();
        basicRecord.setRecordtime(DateTransform(Originrecordtime));
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

    //修改记录
    @PostMapping(value = "/main/record")
    public String updateRecord(@RequestParam("mod_value") double value,
                               @RequestParam("mod_time") String Originrecordtime,
                               @RequestParam("mod_cal") int category,
                               @RequestParam("mod_other") String other,
                               @RequestParam("mod_id") int id,
                               HttpServletRequest request
    ) throws Exception {
        BasicRecord record = recordService.getByRecordId(id);
//        System.out.println(record.getRecordnum() + record.getRecordtime().toString() + record.getOther() + record.getValue());

        record.setCategory(category);
        record.setRecordtime(DateTransform(Originrecordtime));
        record.setOther(other);
        record.setValue(value);
        record.setRecordnum(id);
        recordService.updateByOneRecord(record);
//        System.out.println(record.getRecordnum()+record.getRecordtime().toString()+record.getOther()+record.getValue());
        return "redirect:/main";
    }

    //添加支出
    @PostMapping(value = "/addOutcomeRecordOfUser.action")
    public String addOutcomeBasicRecord(@RequestParam("inValue") double value,
                                        @RequestParam("inTime") String Originrecordtime,
                                        @RequestParam("inType") int category,
                                        @RequestParam("inOther") String other, HttpServletRequest request) throws Exception {
        return addIncomeBasicRecord(-value, Originrecordtime, category, other, request);
    }

    //删除记录
    @RequestMapping(value = "/deleteRecordOfUser.action", method = RequestMethod.POST)
    public String deleteRecordOfUser(@RequestParam("inRecords") int[] records, HttpServletRequest request) throws Exception {
        if (records != null) {
            int i = 0;
            for (int recordnum : records) {
                System.out.print(i);
                i = i + 1;
//                System.out.println(recordnum);
                recordService.deleteByRecordnum(recordnum);
            }
        }
        return "redirect:/main";
    }

}
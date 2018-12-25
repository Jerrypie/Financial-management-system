package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.service.MainService;
import com.example.financialmanagement.service.RecordService;
import com.example.financialmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Resource
    private UserService userService;

    @Resource
    private RecordService recordService;

    @Resource
    private MainService mainService;

    private User user;

    @RequestMapping("/main")
    public String MainPage(HttpServletRequest request, Model model) {
        //从session 中取出User
        HttpSession session =  request.getSession();
        user = (User) session.getAttribute("UserObj");

        //debug
//        System.out.println(user.getUsername());

        //调用mainservice
        mainService.setUser(user);
        List<BasicRecord> recordsList = mainService.getAllSortedRecordsOfUser();

        //设置日期格式
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("records",recordsList);
        model.addAttribute("dateFormat",dateformat);

        System.out.println("一共有"+recordsList.size()+"个records");
        return "main.html";
    }

    @RequestMapping(value = "/addUser.action", method = RequestMethod.POST)
    public String addBasicRecord(  @RequestParam("inValue")  double value,
                                 @RequestParam("inTime") String Originrecordtime,
                                 @RequestParam("inType") int category,
                                 @RequestParam("inOther") String other, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException, ParseException {

        System.out.println(value);
        System.out.println(Originrecordtime);
        System.out.println(category);
        System.out.println(other);


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
        HttpSession session =  request.getSession();
        User user0 = (User) session.getAttribute("UserObj");

        user = userService.getByUsername(user0.getUsername());
        
        user.addRecords(basicRecord);
        userService.save(user);
//        return "main.html";
        return "redirect:/main";
    }

}
package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.BasicRecord;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);
        List<BasicRecord> recordsList = mainService.divdePage(4,user.getUsername(),5).getDataList();
//        List<BasicRecord> recordsList = mainService.getAllSortedRecordsOfUser();

        //设置日期格式
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("records", recordsList);
        model.addAttribute("dateFormat", dateformat);

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
        int i= 0;
        for (int recordnum : records) {
            System.out.print(i);
            i = i + 1;
            System.out.println(recordnum);
            recordService.deleteByRecordnum(recordnum);
        }
        System.out.println("**********************************************");
        return "redirect:/main";
    }


}//end controller
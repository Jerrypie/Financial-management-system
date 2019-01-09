package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.service.MainService;
import com.example.financialmanagement.service.RecordService;
import com.example.financialmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class MainController {

    @Resource
    private UserService userService;

    @Resource
    private RecordService recordService;

    @Resource
    private MainService mainService;

    private User user;

    // 注册
    @PostMapping("/register")
    public String registerUser(String UserName, String Password, String Email, HttpServletRequest request)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String[] msg = new String[2];
        msg = userService.registerUser(UserName, Password, Email);
        request.setAttribute("msg", msg[1]);
        return msg[0];
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register.html";
    }


    //登录
    @PostMapping("/login")
    public String login(String UserName, String Password, HttpServletRequest request)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String[] msg = new String[2];
        msg = userService.login(UserName, Password, request);
        request.setAttribute("msg", msg[1]);
        return msg[0];
    }

    //退出登录
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("UserObj");//删除session中UserObj
        return "index.html";
    }

    //输入日期的String格式，输出Calendar格式的日期
    private Calendar DateTransform(String Originrecordtime) throws Exception {
        SimpleDateFormat StrParse = new SimpleDateFormat("yyyy-MM-dd");
        Date date = StrParse.parse(Originrecordtime);
        Calendar recordtime = Calendar.getInstance();
        recordtime.setTime(date);
        return recordtime;
    }

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

    @PostMapping(value = "/main/record")
    public String updateRecord(@RequestParam("mod_value") double value,
                               @RequestParam("mod_time") String Originrecordtime,
                               @RequestParam("mod_cal") int category,
                               @RequestParam("mod_other") String other,
                               @RequestParam("mod_id") int id,
                               HttpServletRequest request
    ) throws Exception {
        BasicRecord record = recordService.getByRecordId(id);
        System.out.println(record.getRecordnum() + record.getRecordtime().toString() + record.getOther() + record.getValue());

        record.setCategory(category);
        record.setRecordtime(DateTransform(Originrecordtime));
        record.setOther(other);
        record.setValue(value);
        record.setRecordnum(id);
        recordService.updateByOneRecord(record);
//        System.out.println(record.getRecordnum()+record.getRecordtime().toString()+record.getOther()+record.getValue());
        return "redirect:/main";
    }

    @PostMapping(value = "/addOutcomeRecordOfUser.action")
    public String addOutcomeBasicRecord(@RequestParam("inValue") double value,
                                        @RequestParam("inTime") String Originrecordtime,
                                        @RequestParam("inType") int category,
                                        @RequestParam("inOther") String other, HttpServletRequest request) throws Exception {
        return addIncomeBasicRecord(-value, Originrecordtime, category, other, request);
    }

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

}//end controller
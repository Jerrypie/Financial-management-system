package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.service.MainService;
import com.example.financialmanagement.service.RecordService;
import com.example.financialmanagement.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
public class MainDataController {

    @Resource
    private UserService userService;

    @Resource
    private RecordService recordService;

    @Resource
    private MainService mainService;

    private User user;

    //删除某条记录
    @DeleteMapping(value = "/main/record")
    public boolean deleteRecord(@RequestParam("id") int recordId) {
        System.out.println(recordId);
        return recordService.deleteByRecordnum(recordId);
    }

    //按类型返回记录
    @GetMapping("/main/record/type")
    public List<BasicRecord> getByCategory(@RequestParam(value = "category") int category, HttpServletRequest request) {
        // 从session 中取出User
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);

        List<BasicRecord> records = mainService.getAllCategoryRecord(category);

        return records;
    }

    //按时间返回记录
    @GetMapping("/main/record/time")
    public List<BasicRecord> getByRecordtime(@RequestParam("inTime") int time, HttpServletRequest request) {
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

        return records;
    }

    //输入日期的String格式，输出Calendar格式的日期
    private Calendar DateTransform(String Originrecordtime) throws Exception {
        SimpleDateFormat StrParse = new SimpleDateFormat("yyyy-MM-dd");
        Date date = StrParse.parse(Originrecordtime);
        Calendar recordtime = Calendar.getInstance();
        recordtime.setTime(date);
        return recordtime;
    }

    //按所给时间段返回数据
    @GetMapping("/main/record/someTime")
    public List<BasicRecord> getRecordsInTime(@RequestParam("timestart") String startrecordtime,
                                            @RequestParam("timeend") String endrecordtime,
                                            HttpServletRequest request) throws Exception {

        Calendar timestart = DateTransform(startrecordtime);
        Calendar timeend = DateTransform(startrecordtime);
        timestart.add(Calendar.MINUTE,-1);
        timeend.add(Calendar.MINUTE,1);

        //取出用户的记录
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);
        List<BasicRecord> records = mainService.getAllSortedRecordsOfUser();
        RecordService recordService = new RecordService();

        records = recordService.recordsOfSomeDays(records,timestart,timeend);

        return records;
    }


    //按收入或支出返回记录
    @GetMapping("/main/record/value")
    public List<BasicRecord> getByValue(@RequestParam(value = "income") int income, HttpServletRequest request) {
        // 从session 中取出User
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);

        List<BasicRecord> records = mainService.getAllValueRecord(income);

        return records;
    }

    @GetMapping("/main/record/totalValue")
    public double[] getTotalValue( HttpServletRequest request) {
        double[] totalvalue = new double[2];
        //从session 中取出User
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);
        List<BasicRecord> income = mainService.getAllValueRecord(1);
        List<BasicRecord> outcome = mainService.getAllValueRecord(0);
        RecordService recordService = new RecordService();

        int time = 3;
        if(time == 1){
            income = recordService.recordsOfThreeDays(income);
            outcome = recordService.recordsOfThreeDays(outcome);
        }
        else if(time == 2 ){
            income = recordService.recordsOfThisWeek(income);
            outcome = recordService.recordsOfThisWeek(outcome);
        }
        else if(time == 3){
            income = recordService.recordsOfThisMonth(income);
            outcome = recordService.recordsOfThisMonth(outcome);
        }
        else if(time == 4){
            income = recordService.recordsOfThisYear(income);
            outcome = recordService.recordsOfThisYear(outcome);
        }

        totalvalue[0] = recordService.getTotalValueOfRecords(income);
        totalvalue[1] = recordService.getTotalValueOfRecords(outcome);

        return totalvalue;
    }

}

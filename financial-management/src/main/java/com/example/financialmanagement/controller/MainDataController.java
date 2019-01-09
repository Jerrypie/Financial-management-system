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

    @DeleteMapping(value = "/main/record")
    public boolean deleteRecord(@RequestParam("id") int recordId) {
        System.out.println(recordId);
        return recordService.deleteByRecordnum(recordId);
    }

    @GetMapping("/main/record/type")
    public List<BasicRecord> getByCategory(@RequestParam(value = "category") int category, HttpServletRequest request) {
        // 从session 中取出User
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);

        List<BasicRecord> records = mainService.getAllCategoryRecord(category);

        return records;
    }

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

    @GetMapping("/main/record/value")
    public List<BasicRecord> getByValue(@RequestParam(value = "income") int income, HttpServletRequest request) {
        // 从session 中取出User
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);

        List<BasicRecord> records = mainService.getAllValueRecord(income);

        return records;
    }

}

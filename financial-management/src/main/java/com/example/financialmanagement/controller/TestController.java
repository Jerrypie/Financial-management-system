package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.BasicRecordRepository;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.UserRepository;
import com.example.financialmanagement.service.InitializeService;
import com.example.financialmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

@RestController
//测试数据库用
public class TestController {
    @Autowired
    public BasicRecordRepository basicRecordRepository;
    @Autowired
    public UserRepository userRepository;
    User user;
    @Resource
    UserService longshen;
    @Resource
    InitializeService initializeService;

    public void initdate() {
        // basicRecordRepository.deleteAll();
        userRepository.deleteAll();

        BasicRecord basicRecord = new BasicRecord();
        BasicRecord basicRecord2 = new BasicRecord();

        basicRecord.setValue(10);
        basicRecord.setOther("测试1");
        Calendar recordtime1 = Calendar.getInstance();
        recordtime1.set(2018,12-1,23);
        basicRecord.setRecordtime(recordtime1);
        basicRecordRepository.save(basicRecord);

        basicRecord2.setValue(5);
        basicRecord2.setOther("测试2");
        Calendar recordtime2 = Calendar.getInstance();
        recordtime2.set(2018,12-1,24);
        basicRecord2.setRecordtime(recordtime2);
        basicRecordRepository.save(basicRecord2);

        User user = new User();
        user.setUsername("longshen");
        user.setPassword("nb");
        user.setEmail("longshen@qq.com");
        userRepository.save(user);

        List<BasicRecord> records = basicRecordRepository.findAll();
        user.setRecords(records);
        userRepository.save(user);
    }
    //userRepository返回某用户记录
    @RequestMapping("/test.100")
    public List<BasicRecord> getAll() {
        this.initdate();
        user = userRepository.findByUsername("longshen");
        return user.getRecords();
    }
    //userRepository返回某用户所有数据
    @RequestMapping("/test.101")
    public User getUser() {
        this.initdate();
        return userRepository.findByUsername("longshen");
    }
    //UserService返回某用户所有数据
    @RequestMapping("/test.102")
    public User getAllUsers() {
        this.initdate();
        return longshen.getByUsername("longshen");
    }
    //UserService返回某用户记录
    @RequestMapping("/test.103")
    public List<BasicRecord> getAllUsersRecords() {
        this.initdate();
        user = longshen.getByUsername("longshen");
        return user.getRecords();
    }
    //UserService返回某用户排序后记录
    @RequestMapping("/test.104")
    public List<BasicRecord> getUseruser() {
        user = longshen.getByUsername("longshen");
        List<BasicRecord> records = user.getRecords();
        records = initializeService.sortByDate(records);
        return records;
    }
    //UserService返回某用户近三天记录
    @RequestMapping("/test.105")
    public List<BasicRecord> getUserThreeDay() {
        user = longshen.getByUsername("longshen");
        List<BasicRecord> records = user.getRecords();
        records = initializeService.recordsOfThreeDays(records);
        return records;
    }
}
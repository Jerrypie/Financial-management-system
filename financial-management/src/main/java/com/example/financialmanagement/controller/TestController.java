package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.BasicRecordRepository;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.UserRepository;
import com.example.financialmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
//测试数据库用
public class TestController {
    @Autowired
    public BasicRecordRepository basicRecordRepository;
    @Autowired
    public UserRepository userRepository;
    User user;
    UserService longshen;

    public void initdate() {
        basicRecordRepository.deleteAll();
        userRepository.deleteAll();

        BasicRecord basicRecord = new BasicRecord();
        BasicRecord basicRecord2 = new BasicRecord();

        basicRecord.setValue(10);
        basicRecord.setOther("测试3");
        Calendar recordtime1 = Calendar.getInstance();
        recordtime1.set(2016,8-1,28);
        basicRecord.setRecordtime(recordtime1);
        basicRecordRepository.save(basicRecord);

        basicRecord2.setValue(5);
        basicRecord2.setOther("测试4");
        Calendar recordtime2 = Calendar.getInstance();
        // recordtime2.set(2016,8-1,28);
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

    @RequestMapping("/test.99")
    public List<BasicRecord> getAll() {
        this.initdate();
        user = userRepository.findByUsername("longshen");
        return user.getRecords();
    }

    @RequestMapping("/test.98")
    public User getUser() {
        this.initdate();
        return userRepository.findByUsername("longshen");
    }

    @RequestMapping("/test.97")
    public User getAllUsers() {
        this.initdate();
        return longshen.getByUsername("longshen");
    }

    @RequestMapping("/test.101")
    public List<BasicRecord> getAllUsersRecords() {
        this.initdate();
        user = longshen.getByUsername("longshen");
        return user.getRecords();
    }
}
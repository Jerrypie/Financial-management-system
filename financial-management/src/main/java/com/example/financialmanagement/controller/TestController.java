package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.BasicRecordRepository;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//测试数据库用
public class TestController {
    @Autowired
    public BasicRecordRepository basicRecordRepository;
    @Autowired
    public UserRepository userRepository;

    public void initdate() {
        basicRecordRepository.deleteAll();
        userRepository.deleteAll();

        BasicRecord basicRecord = new BasicRecord();
        BasicRecord basicRecord2 = new BasicRecord();
        basicRecord.setValue(10);
        basicRecord.setOther("测试1");
        basicRecordRepository.save(basicRecord);
        basicRecord2.setValue(5);
        basicRecord2.setOther("测试2");
        basicRecordRepository.save(basicRecord2);

        User user = new User();
        user.setUsername("longshen");
        user.setPassword("nb");
        userRepository.save(user);

        List<BasicRecord> records = basicRecordRepository.findAll();
        user.setRecords(records);
        userRepository.save(user);
    }

    @RequestMapping("/test1")
    public List<User> getAllUsers() {
        this.initdate();
        return userRepository.findAll();
    }
}
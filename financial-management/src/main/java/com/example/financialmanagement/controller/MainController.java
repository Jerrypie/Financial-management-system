package com.example.financialmanagement.controller;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.BasicRecordRepository;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private BasicRecordRepository basicRecordRepository;

    public void initdate() {

        BasicRecord basicRecord = new BasicRecord();
        basicRecord.setValue(10.5);
        basicRecord.setCategory(1);
        basicRecord.setOther("中午吃饭");
        basicRecordRepository.save(basicRecord);

        User user = new User();
        user.setUsername("longshen");
        user.setPassword("nb");
        userRepository.save(user);

        List<BasicRecord> records = basicRecordRepository.findAll();
        user.setRecords(records);
        userRepository.save(user);
    }






    @ResponseBody
    @RequestMapping("/loginError")
    public String loginError() {
        return "Password is wrong! Please login again!";
    }
}
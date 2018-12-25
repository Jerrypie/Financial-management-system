package com.example.financialmanagement.controller;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.BasicRecordRepository;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.UserRepository;
import com.example.financialmanagement.service.MainService;
import com.example.financialmanagement.service.RecordService;
import com.example.financialmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
// 测试数据库用
public class TestController {
    @Autowired
    public BasicRecordRepository basicRecordRepository;
    @Autowired
    public UserRepository userRepository;
    User user;
    @Resource
    UserService longshen;
    @Resource
    RecordService recordService;
    @Resource
    RecordService initializeService;

    @Resource
    MainService mainService;

    public void initdate() {
        // basicRecordRepository.deleteAll();
        // userRepository.deleteAll();

        BasicRecord basicRecord = new BasicRecord();
        // BasicRecord basicRecord2 = new BasicRecord();

        basicRecord.setValue(-2);
        basicRecord.setOther("测试9");
        Calendar recordtime1 = Calendar.getInstance();
        recordtime1.set(2018,12-1,25);
        basicRecord.setRecordtime(recordtime1);
        basicRecordRepository.save(basicRecord);

        User user = longshen.getByUsername("慕大佬");
        user.addRecords(basicRecord);
        userRepository.save(user);

        // basicRecord2.setValue(-5);
        // basicRecord2.setRecordnum(63);
        // basicRecord2.setOther("测试8");
        // Calendar recordtime2 = Calendar.getInstance();
        // recordtime2.set(2018,12-1,16);
        // basicRecord2.setRecordtime(recordtime2);
        // basicRecordRepository.save(basicRecord2);

        // User user = new User();
        // user.setUsername("longshen");
        // user.setPassword("nb");
        // user.setEmail("longshen@qq.com");
        // userRepository.save(user);

        // List<BasicRecord> records = basicRecordRepository.findAll();
        // User user = longshen.getByUsername("慕大佬");
        // user.setRecords(records);
        // userRepository.save(user);
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
        return longshen.getByUsername("慕大佬");
    }
    //UserService返回某用户记录
    @RequestMapping("/test.103")
    public List<BasicRecord> getAllUsersRecords() {
        user = longshen.getByUsername("longshen");
        return user.getRecords();
    }
    //UserService返回某用户排序后记录
    @RequestMapping("/test.104")
    public List<BasicRecord> getUseruser() {
        user = longshen.getByUsername("longshen");
        List<BasicRecord> records = user.getRecords();
        records = recordService.sortByDate(records);
        return records;
    }
    //UserService返回某用户近三天记录
    @RequestMapping("/test.105")
    public List<BasicRecord> getUserThreeDay() {
        user = longshen.getByUsername("longshen");
        List<BasicRecord> records = user.getRecords();
        records = recordService.recordsOfThreeDays(records);
        return records;
    }
    //UserService返回某用户本星期记录
    @RequestMapping("/test.106")
    public List<BasicRecord> getUserWeek() {
        user = longshen.getByUsername("longshen");
        List<BasicRecord> records = user.getRecords();
        records = recordService.recordsOfThisWeek(records);
        return records;
    }
    //UserService返回某用户本月记录
    @RequestMapping("/test.107")
    public List<BasicRecord> getUserMonth() {
        user = longshen.getByUsername("longshen");
        List<BasicRecord> records = user.getRecords();
        records = recordService.recordsOfThisMonth(records);
        return records;
    }
    //UserService返回某用户本年记录
    @RequestMapping("/test.108")
    public List<BasicRecord> getUserYear() {
        user = longshen.getByUsername("longshen");
        List<BasicRecord> records = user.getRecords();
        records = recordService.recordsOfThisYear(records);
        return records;
    }

    //UserService返回某用户收入记录
    @RequestMapping("/test.118")
    public List<BasicRecord> getUserRecordsSort() {
        user = longshen.getByUsername("longshen");
        List<BasicRecord> records = user.getRecords();
        records = recordService.sortIncomeOrExpenditure(records,0);
        return records;
    }


    @RequestMapping("/test.109")
    public List<BasicRecord> testuser(){
        user = longshen.getByUsername("longshen");
        mainService.setUser(user);
        return mainService.getAllSortedRecordsOfUser();
    }

    @RequestMapping("/test.110")
    public List<BasicRecord> testuser2(){
        user = longshen.getByUsername("longshen");
        mainService.setUser(user);
        return mainService.getAllCategoryRecord(1);
    }

    @RequestMapping("/test.115")
    public List<BasicRecord> test111() {
        user = longshen.getByUsername("longshen");
        Calendar c1= Calendar.getInstance();
        Calendar c2= Calendar.getInstance();
        mainService.setUser(user);
        c1.set(2016,11,1);
        c2.set(2018, 12 , 26);
        return mainService.getALLSortedOfTime(c1,c2);
    }




}
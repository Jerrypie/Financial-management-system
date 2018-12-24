package com.example.financialmanagement.service;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.BasicRecordRepository;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MainService {
    User user;

    @Resource
    private UserRepository userRepository;

    @Resource
    private BasicRecordRepository basicRecordRepository;

    public void initdate() {

        BasicRecord basicRecord = new BasicRecord();
        basicRecord.setValue(10.5);
        basicRecord.setCategory(1);
        Date data = new Date();


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


    //按时间查询
    List<BasicRecord> presentRecord(int type) {
        //查数据库，找record，返回list
        switch (type) {
            case 1: {
                List<BasicRecord> record = new ArrayList<BasicRecord>();
                return record;
            }


            case 2: {
                List<BasicRecord> record = new ArrayList<BasicRecord>();
                return record;
            }
        }

        List<BasicRecord> record = new ArrayList<BasicRecord>();
        return record;

        //返回一个排序的内容
    }

    //按类别查询
    List<BasicRecord> presentCategoryRecord() {
        List<BasicRecord> record = new ArrayList<BasicRecord>();
        return record;
    }

}
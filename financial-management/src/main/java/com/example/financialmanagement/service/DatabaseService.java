package com.example.financialmanagement.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.BasicRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    User user = new User();

    boolean insertDB() {
        return 1 > 0;
    }

    boolean deleteDB() {
        return 1 > 0;
    }

    boolean updateDB() {
        return 1 > 0;
    }

    boolean isFindDB() {
        return 1 > 0;
    }

    //时间内多少条这个user的记录
    int findNum(Time t1, Time t2) {
        return 1;
    }

    List<BasicRecord> findRecord(Time StartTime, Time EndTime) {
        //查数据库，找record，返回list
        List<BasicRecord> record = new ArrayList<BasicRecord>();
        return record;
    }

    //按类别查询
    List<BasicRecord> findRecord() {
        List<BasicRecord> record = new ArrayList<BasicRecord>();
        return record;
    }


}
package com.example.financialmanagement.service;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {
    private User user;

    @Resource
    private RecordService recordService;


    //返回按时间排序的user所有records
    public List<BasicRecord> getAllSortedRecordsOfUser() {
        //查数据库，找record，返回list
        List<BasicRecord> records =  recordService.getAllRecordsOfUser(user.getUsername());
        return recordService.sortByDate(records);
    }

    //按类别查询
    public List<BasicRecord> getAllCategoryRecord(int type) {
        List<BasicRecord> records =  recordService.getAllRecordsOfUser(user.getUsername());
        List<BasicRecord> resRecords = new ArrayList<>();
        for (BasicRecord basicRecord: records) {
            if (basicRecord.getCategory() == type){
                resRecords.add(basicRecord);
            }
        }
        if(resRecords!=null) {
            return recordService.sortByDate(resRecords);
        }else {
            return null;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
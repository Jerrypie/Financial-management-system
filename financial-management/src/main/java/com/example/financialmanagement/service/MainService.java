package com.example.financialmanagement.service;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
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

    //按records的一定范围日期取出

    public  List<BasicRecord> getALLSortedOfTime(Calendar TimeStart, Calendar TimeEnd){
        List<BasicRecord> records =  recordService.getAllRecordsOfUser(user.getUsername());
        records = recordService.recordsOfSomeDays(records, TimeStart, TimeEnd);
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

        return recordService.sortByDate(resRecords);
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
package com.example.financialmanagement.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.User;

import org.springframework.stereotype.Service;

@Service
public class RecordService {
    User user;
    @Resource
    UserService userservice;

    public List<BasicRecord> getAllRecordsOfUser(String username) {
        user = userservice.getByUsername(username);
        return user.getRecords();
    }

    public List<BasicRecord> sortByDate(List<BasicRecord> records){
        Collections.sort(records, new DateComparetor());
        return records;
    }

    public List<BasicRecord> recordsOfThreeDays(List<BasicRecord> records){
        int i = 0;
        Calendar time_end=Calendar.getInstance();
        time_end.add(Calendar.DATE, -3);
        
        Collections.sort(records, new DateComparetor());
        BasicRecord record = records.get(0);
        while(time_end.before(record.getRecordtime())){
            i = i + 1;
            record = records.get(i);
        }
        records = records.subList(0, i);
        return records;
    }

}

class DateComparetor implements Comparator<BasicRecord> {
    @Override
    public int compare(BasicRecord o1, BasicRecord o2) {
        Calendar time1 = o1.getRecordtime();
        Calendar time2 = o2.getRecordtime();
        if(time1.after(time2)){
            return -1;
        }else if(time1.before(time2)){
            return 1;
        }
        return 0;
    }
}
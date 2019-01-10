package com.example.financialmanagement.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.PageList;
import com.example.financialmanagement.model.User;

import org.springframework.stereotype.Service;

@Service
public class MainService {
    private User user;

    @Resource
    private RecordService recordService;


    //返回按时间排序的user所有records
    public List<BasicRecord> getAllSortedRecordsOfUser() {
        //查数据库，找record，返回list
        List<BasicRecord> records = recordService.getAllRecordsOfUser(user.getUsername());
        return recordService.sortByDate(records);
    }

    public List<BasicRecord> getAllSortedRecordsByusername(String username) {
        //查数据库，找record，返回list
        List<BasicRecord> records = recordService.getAllRecordsOfUser(username);
        return recordService.sortByDate(records);
    }


    //按records的一定范围日期取出

    public List<BasicRecord> getALLSortedOfTime(Calendar TimeStart, Calendar TimeEnd) {
        List<BasicRecord> records = recordService.getAllRecordsOfUser(user.getUsername());
        records = recordService.recordsOfSomeDays(records, TimeStart, TimeEnd);
        return recordService.sortByDate(records);
    }


    //按类别查询
    public List<BasicRecord> getAllCategoryRecord(int type) {
        List<BasicRecord> records = recordService.getAllRecordsOfUser(user.getUsername());
        List<BasicRecord> resRecords = new ArrayList<>();
        for (BasicRecord basicRecord : records) {
            if (basicRecord.getCategory() == type) {
                resRecords.add(basicRecord);
            }
        }

        return recordService.sortByDate(resRecords);
    }

    //按收入或支出查询，
    public List<BasicRecord> getAllValueRecord(int income) {
        List<BasicRecord> records = recordService.getAllRecordsOfUser(user.getUsername());
        List<BasicRecord> incomeRecords = new ArrayList<>();
        List<BasicRecord> outcomeRecords = new ArrayList<>();
        for (BasicRecord basicRecord : records) {
            if (basicRecord.getValue() >= 0) {
                incomeRecords.add(basicRecord);
            }else{
                outcomeRecords.add(basicRecord);
            }
        }
        if(income == 1){
            records = incomeRecords;
        }else if(income == 0){
            records = outcomeRecords;
        }

        return records;
    }

    // //分页查询服务,返回PageList
    // public PageList dividePage(int currentPage, String username, int PageSize) {
    //     PageList pageList = new PageList();
    //     List<BasicRecord> records = this.getAllSortedRecordsByusername(username);
    //     pageList.setPageSize(PageSize);
    //     pageList.setstart((currentPage - 1) * pageList.getPageSize());

    //     //共有多少条数据
    //     int RecordNumber = records.size();

    //     pageList.setCurrentPage(currentPage);
    //     pageList.setTotalPage(RecordNumber % PageSize == 0 ? RecordNumber / PageSize : RecordNumber / PageSize + 1);
    //     pageList.setDataList(records.subList(pageList.getstart(),
    //             RecordNumber - pageList.getstart() > PageSize ? pageList.getstart() + PageSize : RecordNumber));
    //     return pageList;
    // }

    //对所给记录进行分页,返回PageList
    public PageList getPage(List<BasicRecord> records, int currentPage, int PageSize) {
        PageList pageList = new PageList();
        pageList.setPageSize(PageSize);
        pageList.setstart((currentPage - 1) * pageList.getPageSize());

        //共有多少条数据
        int RecordNumber = records.size();

        pageList.setCurrentPage(currentPage);
        pageList.setTotalPage(RecordNumber % PageSize == 0 ? RecordNumber / PageSize : RecordNumber / PageSize + 1);
        pageList.setDataList(records.subList(pageList.getstart(),
                RecordNumber - pageList.getstart() > PageSize ? pageList.getstart() + PageSize : RecordNumber));
        return pageList;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
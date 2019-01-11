package com.example.financialmanagement.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.BasicRecordRepository;
import com.example.financialmanagement.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecordService {
    private User user;

    @Resource
    private UserService userservice;
    @Resource
    private BasicRecordRepository basicRecordRepository;

    public void save(BasicRecord basicRecord) {
        basicRecordRepository.save(basicRecord);
    }

    // get特定user的records
    public List<BasicRecord> getAllRecordsOfUser(String username) {
        user = userservice.getByUsername(username);
        return user.getRecords();
    }

    //按类别查询
    public List<BasicRecord> getAllCategoryRecord(List<BasicRecord> records,int type) {
        List<BasicRecord> resRecords = new ArrayList<>();
        for (BasicRecord basicRecord : records) {
            if (basicRecord.getCategory() == type) {
                resRecords.add(basicRecord);
            }
        }
        return resRecords;
    }

    //计算所给记录的支出的总和
    public Double getTotalValueOfRecords(List<BasicRecord> records) {
        Double totalvalue = (double) 0;
        for (BasicRecord basicRecord: records){
            totalvalue = totalvalue + basicRecord.getValue();
        }
        return totalvalue;
    }

    @Transactional
    public BasicRecord getByRecordId(Integer id) {
        Optional<BasicRecord> op = basicRecordRepository.findById(id);
        return op.get();
    }

    //对记录为收入或支出分类，income为1时返回收入，其他值返回支出
    @Transactional
    public List<BasicRecord> sortIncomeOrExpenditure(List<BasicRecord> records,int income){
        
        List<BasicRecord> records_out = new ArrayList<BasicRecord>();
        if(income == 1){
            for (BasicRecord basicRecord: records){
                if(basicRecord.getValue()>=0){
                    records_out.add(basicRecord);
                }
            }
        }
        else{
            for (BasicRecord basicRecord: records){
                if(basicRecord.getValue()<0){
                    records_out.add(basicRecord);
                }
            }
        }
        return records_out;
    }

    //按记录id删除某条记录
    @Transactional
    public boolean deleteByRecordnum(int recordnum){
        basicRecordRepository.deleteById(recordnum);
        return true;
    }

    //修改保存某条记录
    @Transactional
    public boolean updateByOneRecord(BasicRecord basicRecord){
        basicRecordRepository.save(basicRecord);
        return true;
    }

    //按日期排序
    public List<BasicRecord> sortByDate(List<BasicRecord> records){
        Collections.sort(records, new DateComparetor());
        return records;
    }


    //查找给定时间起止内的记录，其中time_start更早，time_end更晚
    @Transactional
    public List<BasicRecord> recordsOfSomeDays(List<BasicRecord> records,Calendar time_start,Calendar time_end){
        int i = 0;
        int j = 0;
        int i_max = records.size();
        records = sortByDate(records);

        for (i = 0; i<i_max; i++){
            if ( !time_end.before(records.get(i).getRecordtime() )){
                break;
            }
        }


        for (j=i; j < i_max; j++){
            if (!time_start.before(records.get(j).getRecordtime())){
                break;
            }
        }

        //j值为第一个不位于时间段内记录
        records = records.subList(i, j);
        return records;
    }

    //查找近三天记录
    @Transactional
    public List<BasicRecord> recordsOfThreeDays(List<BasicRecord> records){
        Calendar time_end = Calendar.getInstance();
        Calendar time_start = Calendar.getInstance();
        time_start.add(Calendar.DATE, -3);
        records = recordsOfSomeDays(records,time_start,time_end);
        return records;
    }
    //查找本星期记录
    @Transactional
    public List<BasicRecord> recordsOfThisWeek(List<BasicRecord> records){
        Calendar time_end = Calendar.getInstance();
        Calendar time_start = Calendar.getInstance();
        int weekday = time_start.get(Calendar.DAY_OF_WEEK);
        int hour24 = time_start.get(Calendar.HOUR_OF_DAY);
        int minute = time_start.get(Calendar.MINUTE);
        int second = time_start.get(Calendar.SECOND);
        if(weekday==1){
            time_start.add(Calendar.DAY_OF_WEEK,-6);
        }
        else{
            time_start.add(Calendar.DAY_OF_WEEK,2-weekday);
        }
        time_start.add(Calendar.HOUR_OF_DAY,-hour24);
        time_start.add(Calendar.MINUTE,-minute-1);
        time_start.add(Calendar.SECOND,-second);

        records = recordsOfSomeDays(records,time_start,time_end);
        return records;
    }  
    //查找本月记录
    @Transactional
    public List<BasicRecord> recordsOfThisMonth(List<BasicRecord> records){
        Calendar time_end = Calendar.getInstance();
        Calendar time_start = Calendar.getInstance();
        int day = time_start.get(Calendar.DAY_OF_MONTH);
        int hour = time_start.get(Calendar.HOUR_OF_DAY);
        int minute = time_start.get(Calendar.MINUTE);
        int second = time_start.get(Calendar.SECOND);

        time_start.add(Calendar.DAY_OF_MONTH,1-day);
        time_start.add(Calendar.HOUR_OF_DAY,-hour);
        time_start.add(Calendar.MINUTE,-minute-1);
        time_start.add(Calendar.SECOND,-second);
//        System.out.println(records.size());
        records = recordsOfSomeDays(records,time_start,time_end);
//        System.out.println(records.size());
        return records;
    }

    //查找本年记录
    @Transactional
    public List<BasicRecord> recordsOfThisYear(List<BasicRecord> records){
        Calendar time_end = Calendar.getInstance();
        Calendar time_start = Calendar.getInstance();
        int day = time_start.get(Calendar.DAY_OF_YEAR);
        int hour = time_start.get(Calendar.HOUR_OF_DAY);
        int minute = time_start.get(Calendar.MINUTE);
        int second = time_start.get(Calendar.SECOND);

        time_start.add(Calendar.DAY_OF_YEAR,1-day);
        time_start.add(Calendar.HOUR_OF_DAY,-hour);
        time_start.add(Calendar.MINUTE,-minute-1);
        time_start.add(Calendar.SECOND,-second);

        records = recordsOfSomeDays(records,time_start,time_end);
        return records;
    }

    //分页查询
    public Page<BasicRecord> findAll(Pageable pageable) {
        return basicRecordRepository.findAll(pageable);
    }

}

//比较器，日期最新的排前面
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
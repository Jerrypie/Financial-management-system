package com.example.financialmanagement.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.service.MainService;
import com.example.financialmanagement.service.RecordService;
import com.example.financialmanagement.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainDataController {

    @Resource
    private UserService userService;

    @Resource
    private RecordService recordService;

    @Resource
    private MainService mainService;

    private User user;

    //删除某条记录
    @DeleteMapping(value = "/main/record")
    public boolean deleteRecord(@RequestParam("id") int recordId) {
        return recordService.deleteByRecordnum(recordId);
    }


    //输入日期的String格式，输出Calendar格式的日期
    private Calendar DateTransform(String Originrecordtime) throws Exception {
        SimpleDateFormat StrParse = new SimpleDateFormat("yyyy-MM-dd");
        Date date = StrParse.parse(Originrecordtime);
        Calendar recordtime = Calendar.getInstance();
        recordtime.setTime(date);
        return recordtime;
    }

    //按所给时间段返回记录
    @GetMapping("/main/record/someTime")
    public List<BasicRecord> getRecordsInTime(@RequestParam("timestart") String startrecordtime,
                                            @RequestParam("timeend") String endrecordtime,
                                            HttpServletRequest request) throws Exception {
        if (!startrecordtime.isEmpty() && !endrecordtime.isEmpty()) {
            Calendar timestart = DateTransform(startrecordtime);
            Calendar timeend = DateTransform(endrecordtime);
            timestart.add(Calendar.MINUTE, -1);
            timeend.add(Calendar.MINUTE, 1);

            //取出用户的记录
            HttpSession session = request.getSession();
            user = (User) session.getAttribute("UserObj");
            mainService.setUser(user);
            List<BasicRecord> records = mainService.getAllSortedRecordsOfUser();
            RecordService recordService = new RecordService();

            records = recordService.recordsOfSomeDays(records, timestart, timeend);

            return records;
        }
        return null;
    }


    //按收入或支出返回记录
    @GetMapping("/main/record/value")
    public List<BasicRecord> getByValue(@RequestParam(value = "income") int income, HttpServletRequest request) {
        // 从session 中取出User
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);

        List<BasicRecord> records = mainService.getAllValueRecord(income);

        return records;
    }

    //返回本月收入支出和
    @GetMapping("/main/record/totalValue")
    public Map<String, double[]> getTotalValue(HttpServletRequest request) {
        double[] totalvalue = new double[2];
        //从session 中取出User
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);

        List<BasicRecord> records = mainService.getAllSortedRecordsOfUser();

        records = recordService.recordsOfThisMonth(records);

        List<BasicRecord> income = recordService.sortIncomeOrExpenditure(records, 1);
        List<BasicRecord> outcome = recordService.sortIncomeOrExpenditure(records, 0);

        totalvalue[0] = recordService.getTotalValueOfRecords(income);
        totalvalue[1] = recordService.getTotalValueOfRecords(outcome);

        Map<String,double[]> map = new HashMap<String,double[]>();
        map.put("data1", totalvalue);

        return map;
    }

    //返回某一年每月收入支出
    @GetMapping("/main/record/everyMonth")
    public Map<String, double[]> getTotalValueOfMonth(@RequestParam("year") int year, HttpServletRequest request) {

        double[] incomevalue = new double[12];
        double[] outcomevalue = new double[12];
        int i;
        List<BasicRecord> income = new ArrayList<BasicRecord>();
        List<BasicRecord> outcome = new ArrayList<BasicRecord>();
        List<BasicRecord> month = new ArrayList<BasicRecord>();
        Calendar timestart = Calendar.getInstance();
        Calendar timeend = Calendar.getInstance();
        timestart.set(year, 0, 1, 0, 0, 0);
        timestart.add(Calendar.MINUTE,-1);
        timeend.set(year, 1, 1, 0, 0, 0);
        timeend.add(Calendar.MINUTE,-1);

        //从session 中取出User
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);
        List<BasicRecord> records = mainService.getAllSortedRecordsOfUser();
        RecordService recordService = new RecordService();
        for(i = 0; i < 12; i++ ){
            month = recordService.recordsOfSomeDays(records, timestart, timeend);
            timeend.add(Calendar.MONTH,+1);
            timestart.add(Calendar.MONTH,+1);
            income = recordService.sortIncomeOrExpenditure(month, 1);
            outcome = recordService.sortIncomeOrExpenditure(month, 0);

            incomevalue[i] = recordService.getTotalValueOfRecords(income);
            outcomevalue[i] = recordService.getTotalValueOfRecords(outcome);
        }
        //调整返回格式
        Map<String,double[]> map = new HashMap<String,double[]>();  
        map.put("data1", incomevalue);
        map.put("data2", outcomevalue);
        
        return map;
    }

    //返回某月各项收入或支出和
    @GetMapping("/main/record/oneMonth")
    public Map<String, List<Double>> getMonthRecord(@RequestParam("year") int year,
                                                @RequestParam("month") int month,
                                                @RequestParam("income") int income,  
                                                HttpServletRequest request) {
//        double[] categoryvalue = new double[6];
        List<Double> categoryvalue = new ArrayList<Double>();
        List<BasicRecord> category = new ArrayList<BasicRecord>();
        int i;
        Calendar timestart = Calendar.getInstance();
        Calendar timeend = Calendar.getInstance();
        timestart.set(year, month-1, 1, 0, 0, 0);
        timestart.add(Calendar.MINUTE,-1);
        timeend.set(year, month, 1, 0, 0, 0);
        timeend.add(Calendar.MINUTE,-1);
            
        //从session 中取出User
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);
        
        List<BasicRecord> records = mainService.getAllSortedRecordsOfUser();
        RecordService recordService = new RecordService();
        records = recordService.recordsOfSomeDays(records, timestart, timeend);

        if (income == 0) {
            for (i = 0; i < 7; i++) {
                category = recordService.getAllCategoryRecord(records, i + 1);
                category = recordService.sortIncomeOrExpenditure(category, income);
                categoryvalue.add(recordService.getTotalValueOfRecords(category));
            }
        } else {
            for (i = 0; i < 5; i++) {
                category = recordService.getAllCategoryRecord(records, i + 11);
                category = recordService.sortIncomeOrExpenditure(category, income);
                categoryvalue.add(recordService.getTotalValueOfRecords(category));
            }
        }

        //调整返回格式
        Map< String,List<Double> > map = new HashMap<String,List<Double>>();
        map.put("data1", categoryvalue);

        return map;
    }

    //返回某月每日收入支出和
    @GetMapping("/main/record/everyDay")
    public DayValue getTotalValueOfDay(@RequestParam("year") int year, 
                                                      @RequestParam("month") int month, HttpServletRequest request) {

        RecordService recordService = new RecordService();
        List<BasicRecord> dayrecord = new ArrayList<BasicRecord>();
        List<BasicRecord> income = new ArrayList<BasicRecord>();
        List<BasicRecord> outcome = new ArrayList<BasicRecord>();        
        Calendar timestart = Calendar.getInstance();
        Calendar timeend = Calendar.getInstance();
        timestart.set(year, month-1, 1, 0, 0, 0);
        timestart.add(Calendar.MINUTE,-1);
        timeend.set(year, month, 1, 0, 0, 0);
        timeend.add(Calendar.MINUTE,-1);

        //从session 中取出User
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("UserObj");
        mainService.setUser(user);
        List<BasicRecord> monthrecord = mainService.getAllSortedRecordsOfUser();
        monthrecord = recordService.recordsOfSomeDays(monthrecord, timestart, timeend);

        int i_max = timeend.get(Calendar.DATE);
        double[] incomevalue = new double[i_max];
        double[] outcomevalue = new double[i_max];
        int[] inday = new int[i_max];
        int i;
        
        timeend.set(year, month-1, 2, 0, 0, 0);
        timeend.add(Calendar.MINUTE,-1);
        //获得每天的value及对应天数
        for(i = 0; i < i_max; i++ ){
            dayrecord = recordService.recordsOfSomeDays(monthrecord, timestart, timeend);
            timeend.add(Calendar.DATE,+1);
            timestart.add(Calendar.DATE,+1);
            income = recordService.sortIncomeOrExpenditure(dayrecord, 1);
            outcome = recordService.sortIncomeOrExpenditure(dayrecord, 0);

            incomevalue[i] = recordService.getTotalValueOfRecords(income);
            outcomevalue[i] = recordService.getTotalValueOfRecords(outcome);
            inday[i] = i + 1;
        }
        //调整返回格式
        DayValue dayvalue = new DayValue(); 
        dayvalue.setIncome(incomevalue);
        dayvalue.setOutcome(outcomevalue);
        dayvalue.setDay(inday);

        return dayvalue;
    }
}
//每日收入支出模型
class DayValue{
    private double[] income;
    private double[] outcome;
    private int[] day;
	
	public int[] getDay() {
		return day;
	}
	
	public void setDay(int[] day) {
		this.day = day;
	}
	
	public double[] getOutcome() {
		return outcome;
	}
	
	public void setOutcome(double[] outcome) {
		this.outcome = outcome;
	}
	
	public double[] getIncome() {
		return income;
	}
	
	public void setIncome(double[] income) {
		this.income = income;
	}
}

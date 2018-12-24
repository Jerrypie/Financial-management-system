package com.example.financialmanagement.controller;

import java.util.Calendar;
import java.util.Date;


public class CalendarTest {

    public static void main(String[] args) {
        Calendar time=Calendar.getInstance();
        int year=time.get(Calendar.YEAR);
	    int month=time.get(Calendar.MONTH);//0代表1月，最大为11月
	    int day1=time.get(Calendar.DATE);
	    int hour=time.get(Calendar.HOUR);
	    int min=time.get(Calendar.MINUTE);
	    int sec=time.get(Calendar.SECOND);
        Date c1=time.getTime();
        System.out.println(year+"年"+month+"月"+day1+"日"+""+hour+":"+min+":"+sec);
        System.out.println("当前时间为:"+c1);

        Calendar cal = Calendar.getInstance();  
        int year1=2013;
        int month1=11;
        int day2=25;
        cal.set(year1, month1, day2);
        System.out.println(cal.get(Calendar.YEAR)+"年");
        System.out.println(cal.get(Calendar.MONTH)+"月");
        System.out.println(cal.get(Calendar.DATE)+"日");

        Calendar c4 = Calendar.getInstance();
        c4.set(2016, 8-1, 28);
        Calendar c5 = Calendar.getInstance();
        c5.set(2016,10-1,1);
        Calendar c6 = Calendar.getInstance();
        c6.set(2016,9-1,1);
        boolean b1 = c5.after(c4);
        System.out.println("2016,10,1在2016,8,28之后："+b1);
        boolean b2 = c4.after(c5);
        System.out.println("2016,8,28在2016,10,1之后："+b2);

        boolean b3 = c5.before(c4);
        System.out.println("2016,10,1在2016,8,28之前："+b3);
        boolean b4 = c4.before(c5);
        System.out.println("2016,8,28在2016,10,1之前："+b4);
    }
    
	

}
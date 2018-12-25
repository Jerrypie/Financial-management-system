package com.example.financialmanagement.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;

//注册为bean
@Entity()
@Table(name = "basicrecord")
//记录类
public class BasicRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordnum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar recordtime;  //收支时间
    private double value;    //花的钱
    private int category; //种类（要改类型）
    private String other;  //备注

    public BasicRecord() {

    }

    public BasicRecord(Calendar recordtime, int value) {
        super();
        this.setRecordtime(recordtime);
        this.setValue(value);
    }

    public BasicRecord(int recordnum, Calendar recordtime, int value) {
        super();
        this.setRecordnum(recordnum);
        this.setRecordtime(recordtime);
        this.setValue(value);
    }

    public BasicRecord(int recordnum, Calendar recordtime, int value, int category) {
        super();
        this.setRecordnum(recordnum);
        this.setRecordtime(recordtime);
        this.setValue(value);
        this.setCategory(category);
    }

    public BasicRecord(int recordnum, Calendar recordtime, int value, int category, String other) {
        super();
        this.setRecordnum(recordnum);
        this.setRecordtime(recordtime);
        this.setValue(value);
        this.setCategory(category);
        this.setOther(other);
    }


    /**
     * @return the recordnum
     */
    public int getRecordnum() {
        return recordnum;
    }

    /**
     * @param recordnum the recordnum to set
     */
    public void setRecordnum(int recordnum) {
        this.recordnum = recordnum;
    }

    /**
     * @return the recordtime
     */
    public Calendar getRecordtime() {
        return recordtime;
    }

    /**
     * @param recordtime the recordtime to set
     */
    public void setRecordtime(Calendar recordtime) {
        this.recordtime = recordtime;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @return the category
     */
    public int getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(int category) {
        this.category = category;
    }

    /**
     * @return the other
     */
    public String getOther() {
        return other;
    }

    /**
     * @param other the other to set
     */
    public void setOther(String other) {
        this.other = other;
    }

}
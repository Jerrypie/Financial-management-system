package com.example.financialmanagement.model;

import java.sql.Time;

import org.springframework.stereotype.Repository;

//注册为bean
@Repository
//记录类
public class BasicRecord {
    private int recordnum;
    private Time recordtime;  //收支时间
    private int value;    //花的钱
    private String category; //种类（要改类型）
    private String other;  //备注

    public BasicRecord() {

    }

    public BasicRecord(Time recordtime, int value) {
        super();
        this.setRecordtime(recordtime);
        this.setValue(value);
    }

    public BasicRecord(int recordnum, Time recordtime, int value) {
        super();
        this.setRecordnum(recordnum);
        this.setRecordtime(recordtime);
        this.setValue(value);
    }

    public BasicRecord(int recordnum, Time recordtime, int value, String category) {
        super();
        this.setRecordnum(recordnum);
        this.setRecordtime(recordtime);
        this.setValue(value);
        this.setCategory(category);
    }

    public BasicRecord(int recordnum, Time recordtime, int value, String category, String other) {
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
    public Time getRecordtime() {
        return recordtime;
    }

    /**
     * @param recordtime the recordtime to set
     */
    public void setRecordtime(Time recordtime) {
        this.recordtime = recordtime;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
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
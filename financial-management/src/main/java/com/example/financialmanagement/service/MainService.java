package com.example.financialmanagement.service;

import java.util.ArrayList;
import java.util.List;
import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.User;

public class MainService {
    User user;
    //按时间查询
    List <BasicRecord> presentRecord(int type) {
        //查数据库，找record，返回list
        switch(type){
            case 1:{
                List<BasicRecord> record = new ArrayList<BasicRecord>();
                return record;
            }
            
            
            case 2:{
                List<BasicRecord> record = new ArrayList<BasicRecord>();
                return record;
            } 
        }

        List<BasicRecord> record = new ArrayList<BasicRecord>();
        return record;
                
        //返回一个排序的内容
    }
    
    //按类别查询
    List <BasicRecord> presentCategoryRecord(){
        List<BasicRecord> record = new ArrayList<BasicRecord>();
        return record;
    }
    
  }
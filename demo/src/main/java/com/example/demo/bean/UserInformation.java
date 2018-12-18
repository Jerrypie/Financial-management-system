package com.example.demo.bean;

import org.springframework.stereotype.Repository;

//注册为bean
@Repository
//用户类
public class UserInformation {
    private int id;
    private String userid;
    private String password;
    
    public UserInformation() {
      
    }
    
    public UserInformation(String userid, String password) {
        super();
        this.userid = userid;
        this.password = password;
    }
   
    public UserInformation(int id, String userid, String password) {
        super();
        this.id = id;
        this.userid = userid;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return userid;
    }
    public void setName(String userid) {
        this.userid = userid;
    }

    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password = password;
    }
}
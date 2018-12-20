package com.example.financialmanagement.model;

import org.springframework.stereotype.Repository;

//注册为bean
@Repository
//用户类
public class User {
    private int userid;
    private String username;
    private String password;
    private String email;

    public User() {

    }

    public User(String username, String password) {
        super();
        this.setUsername(username);
        this.setPassword(password);
    }

    public User(int userid, String username, String password) {
        super();
        this.setUserid(userid);
        this.setUsername(username);
        this.setPassword(password);
    }

    public User(int userid, String username, String password, String email) {
        super();
        this.setUserid(userid);
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
    }


    /**
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
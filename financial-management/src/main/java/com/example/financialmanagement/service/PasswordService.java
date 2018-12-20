package com.example.financialmanagement.service;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.stereotype.Service;

//注册为bean
@Service
//SHA加密
public class PasswordService {
    private final String KEY_SHA = "SHA";
    private String password;

    public PasswordService() {

    }

    public PasswordService(String password) {
        super();
        this.setPassword(password);
        this.passwordEncrypt(this.password);
    }

    public String passwordEncrypt(String inputStr) {
        BigInteger sha = null;
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
            System.out.println("SHA加密后:" + sha.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sha.toString();
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

}
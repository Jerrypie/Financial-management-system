package com.example.financialmanagement.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import org.springframework.stereotype.Repository;

//注册为bean
@Repository
//SHA加密
public class ShaEncode {
    private final String KEY_SHA = "SHA";

    public String Encode(String inputStr){
        BigInteger sha =null;
        byte[] inputData = inputStr.getBytes(); 
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA); 
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest()); 
            System.out.println("SHA加密后:" + sha.toString()); 
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return sha.toString();
    }
}
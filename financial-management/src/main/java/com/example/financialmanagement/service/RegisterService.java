package com.example.financialmanagement.service;

import com.example.financialmanagement.model.User;
import org.springframework.stereotype.Service;


@Service
public class RegisterService {

    User user;
    PasswordService encoder;

    boolean isValid() {
        //是否重名 数据库操作
        return 1 > 0;
    }

    boolean userInsert() {
        if (this.isValid()) {
            //数据库操作
            return 1 > 0;
        }
        return 1 > 0;
    }

}
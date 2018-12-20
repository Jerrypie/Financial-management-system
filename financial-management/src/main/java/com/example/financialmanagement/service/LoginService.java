package com.example.financialmanagement.service;

import com.example.financialmanagement.model.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private User user;
    private PasswordService encoder;

    public LoginService() {

    }

    //用户存在
    boolean isUserExist() {
        return 1 > 0;
    }

    //密码匹配不匹配
    boolean marchPassword() {
        encoder.passwordEncrypt(user.getPassword());
        return 1 > 0;
    }
}
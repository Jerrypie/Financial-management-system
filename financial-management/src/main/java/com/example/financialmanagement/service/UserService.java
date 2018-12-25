package com.example.financialmanagement.service;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Base64.Encoder;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Resource
    public UserRepository userRepository;

    //保存对象
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
    //关联记录
    public void addRecords(User user, BasicRecord record) {
        List<BasicRecord> records = user.getRecords();
        records.add(record);
        user.setRecords(records);
    }

    //根据id删除对象
    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    //查询所有数据,返回一个list集合
    public List<User> getAll() {
        return userRepository.findAll();
    }

    //根据id查询数据
    @Transactional
    public Optional<User> getById(Integer id) {
        Optional<User> op = userRepository.findById(id);
        return op;
    }

    //根据id和密码查询
    @Transactional
    public User getByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Transactional
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    //字符串加密，先md5再base64
    @Transactional
    public String passwordEncrypt(String Str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        Encoder encoder = Base64.getEncoder();

        //加密后的字符串
        String newstr = encoder.encodeToString(md5.digest(Str.getBytes(StandardCharsets.UTF_8)));
        return newstr;
    }

}

package com.example.financialmanagement.service;

import com.example.financialmanagement.model.BasicRecord;
import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Base64.Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private UserRepository userRepository;

    //用户注册
    public String[] registerUser(String username, String password, String email)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String[] msg = new String[2];
        //验证用户名和密码是否为空
        if (username != null && username.length() != 0
                && password != null && password.length() != 0) {

            //验证数据库中是否有这条记录
            User user_db = this.getByUsername(username);
            if (user_db != null) {
                //如果用户存在，用户重新输入
                msg[0] = "signup";
                msg[1] = "输入的用户名已存在，请重新输入";
            } else {
                //用户不存在进行注册
                User user_new = new User();
                user_new.setUsername(username);
                password = this.passwordEncrypt(password);
                user_new.setPassword(password);
                user_new.setEmail(email);
                this.save(user_new);
                msg[0] = "redirect:/index";
                msg[1] = "注册成功，请登录";
            }
        } else {
            msg[0] = "signup";
            msg[1] = "输入的密码或用户名为空";
        }
        return msg;
    }

    //用户登录
    public String[] login(String username, String password, HttpServletRequest request) 
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        
        String[] msg = new String[2];
        //验证用户名和密码是否为空
        if (username != null && username.length() != 0
                && password != null && password.length() != 0) {

            //验证数据库中是否有这条记录
            password = this.passwordEncrypt(password);
            User user_db = this.getByUsernameAndPassword(username, password);
            if (user_db != null) {

                //如果用户存在，加入到session 中供后面使用
                HttpSession session = request.getSession();
                session.setAttribute("UserObj", user_db);
                msg[0] = "redirect:/main";
                msg[1] = "登录成功";
            } else {
                msg[0] = "index.html";
                msg[1] = "输入的密码或用户名有误，请重新输入";
            }
        } else {
            msg[0] = "index.html";
            msg[1] = "输入的密码或用户名为空";
        }
        return msg;
    }

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

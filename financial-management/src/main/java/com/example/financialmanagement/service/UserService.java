package com.example.financialmanagement.service;

import com.example.financialmanagement.model.User;
import com.example.financialmanagement.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;


    //保存对象
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    //根据id删除对象
    @Transactional
    public void delete(int id){

        userRepository.deleteById(id);
    }

    //查询所有数据,返回一个list集合
    public List<User> getAll() {
        return userRepository.findAll();
    }

    //根据id查询数据
    public Optional<User> getById(Integer id) {
        Optional<User> op = userRepository.findById(id);
        return op;
    }

    //根据id和密码查询
    public User getByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }

    public  User getByName(String username) {
        return userRepository.findByUsername(username);
    }
}

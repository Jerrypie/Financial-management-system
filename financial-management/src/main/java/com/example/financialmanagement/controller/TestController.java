// package com.example.financialmanagement.controller;

// import java.util.List;

// import com.example.financialmanagement.model.BasicRecord;
// import com.example.financialmanagement.model.BasicRecordRepository;
// import com.example.financialmanagement.model.User;
// import com.example.financialmanagement.model.UserRepository;

// import org.aspectj.lang.annotation.Before;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// //测试数据库用
// public class TestController {
//     @Autowired
//     public BasicRecordRepository basicRecordRepository;
//     @Autowired
//     public UserRepository userRepository;

//     public void initdate(){
//         basicRecordRepository.deleteAll();
//         userRepository.deleteAll();

//         BasicRecord basicRecord = new BasicRecord();
//         basicRecord.setValue(10);
//         basicRecord.setOther("测试1");
//         basicRecordRepository.save(basicRecord);
//         basicRecord.setValue(5);
//         basicRecord.setOther("测试2");
//         basicRecordRepository.save(basicRecord);

//         User user = new User();
//         user.setUsername("longshen");
//         user.setPassword("nb");
//         userRepository.save(user);

//         List<BasicRecord> records = basicRecordRepository.findAll();
//         user.setRecords(records);
//         userRepository.save(user);
//     }

//     @RequestMapping("/test1")  
//     public void getAllUsers(){
//         User user = new User();
//         user.setPassword("password");
//         user.setUsername("username");
//         user.setEmail("email");
//     }  
// }
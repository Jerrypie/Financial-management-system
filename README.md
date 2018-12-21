Personal Financial Management System

软件课程设计  -- HUST
## 工作进展

12.17  登录注册页面 前端 v1

12.21 登录后端开发完成

12.22 注册后端完成



## 项目结构

```
- src
	-main
		-java(com-example)
			-controller 控制器模块
				-IndexController.java 跳转主页面
				-LogStatusController.java 控制login和logout
				-MainController.java 主页面的控制器
				-RegisterController.java 注册控制器
                -TestController.java 暂时没什么用，测试
                -UserController.java 暂时没什么用，测试
			-model 模型模块
				-BasicRecord.java 记账记录的实体类
				-User.java 用户实体类
				-UserRepository.java 用户Repository层，封装数据库操作
				-BasicRecordRepository.java 记账记录Repository层，封装数据库操作
			-service 业务层
				-PasswordService.java 提供密码加密服务（暂时不用）
				-UserService.java 用户给控制器提供服务，服务层，调用Repository层
				-BasicRecordService.java 记录的业务层
		-resource
			-static 静态文件
			-templates 动态模板html文件
	-test
- target ：class文件
```







## 遇到的问题

## 资源分享

[java 菜鸟教程](http://www.runoob.com/java/java-tutorial.html)

[廖雪峰git](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/)

[W3School](http://www.w3school.com.cn/index.html)



## 后端想法

#### 控制器：

#### Controller 层

**登录**



用户存不存在：

存在 -> 加密密码，并与数据库中存的密码比较 -> 保存到session中 ；然后跳转到主页面

​										->密码错了，登录失败，返回消息

不存在-> 返回一个错误消息提示 跳转首页



**注册:** 

不合法-> 返回提醒，跳转到注册页面

不存在 -> 调用数据库函数，加入数据库

登录控制**加密密码** 存到数据库里



**主页面**：

**MainController**

从session中得到用户的userid，发给Service层



#### Service 层

功能：

**密码加密：**

```
PasswordService {
  String passWord
  passwordEncrypt (String ) {
    
    ...
    ...
    
    return String
  }
}
```

**登录服务：**

```
LoginService {
  User user
  PassWordService encoder
  //用户存在
  bool isUserExist (){
	...
  }
  
  //密码匹配不匹配
  bool marchPassword (){
    Encoder.passwordEncrypt()
    ...
  }
}
```

**注册服务：**

```
RegisterService {
  User user
  PassWordService encoder  
  
  bool isValid() {
    ...//是否重名 数据库操作
  }
  
  bool userInsert() {
    if(this.isValid()){
      ...//数据库操作
      ...
    }
  }
 
}
```

**主体部分**：

```
MainService {
  User user
  //按时间查询
  List <BasicRecord> presentRecord(int type) {
  	//查数据库，找record，返回list
  	switch(type){
      case:
      	...
      case:
      	...
    
  	}
  	返回一个排序的内容
  }
  
  //按类别查询
  List <BasicRecord> presentCategoryRecord(){
    ...
  }
  
}
```

**数据库操作部分：**

```
DatabaseService {
  User user

  bool insertDB() {
  	...  
  }
  
  bool deleteDB(){
    ...
  }
  
  bool updateDB(){
    ...
  }
  
  bool isFindDB(){
    ...
  }
  
  //时间内多少条这个user的记录
  int findNum(Time t1， Time t2){
    ...
  }
  
  List <BasicRecord> findRecord(Time StartTime, Time EndTime) {
  	//查数据库，找record，返回list
  }
  
   //按类别查询
  List <BasicRecord> findRecord(){
    ...
  }
 
  
}
```



#### 模型

POJO

两个TABLE

用户模型：

```
User {
	int userid
	String username
	String password
	String email
}
```

记录模型

```
BasicRecord {
  int recordnum
  Time recordtime //收支时间
  int value // 花的钱
  Category category //种类
  String other // 其他
}
```




Personal Financial Management System

软件课程设计  -- HUST
## 工作进展

12.17  登录注册页面 前端 v1

12.21 登录后端开发完成

12.22 注册后端完成

12.24 加入了**MainService**， 封装了返回按时间排序的user所有records，按类别查询，使用首先应该setUser



## 项目结构

```
- src
	-main
		-java(com-example)
			-component 组件模块
				-LoginHandlerInterceptor.java 登录检查，没有登录的用户不能访问其它资源
			-config 配置模块
				-MvcConfig.java 扩展spring MVC功能，设置映射和注册拦截器，除了特定的请求都拦截
			-controller 控制器模块
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
				-mainService   //主页面的服务，调用RecordService的服务
				-RecordService   //get特定user的records    //按日期排序    //三天的记录
				-UserService.java 用户给控制器提供服务，服务层，调用Repository层
				-BasicRecordService.java 记录的业务层
		-resource
			-static 静态文件
			-templates 动态模板html文件
	-test
- target ：class文件
```



## API

```

```



## 遇到的问题

## 资源分享

[java 菜鸟教程](http://www.runoob.com/java/java-tutorial.html)

[廖雪峰git](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/)

[W3School](http://www.w3school.com.cn/index.html)



## 前端想法

财务管理-> 收支详表

​		-> 近三天

​		->本星期

​		-> 本月

​		->本年	

​		最右->收入 支出

分类统计-> 

​	->  总分类

​	-> 收入支出区分 收入分类 支出分类

图表 EChart.js

​	





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




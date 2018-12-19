Personal Financial Management System

软件课程设计  -- HUST
## 工作进展

12.17  登录注册页面 前端 v1

## 学习进展

### Long

* 11.4 学习和测试html的基础内容，比如标签，属性，以及html的基本样式。

### Xie

* 11.4 学习java基础语法、数据类型以及相关运算操作，编写几个简单的java程序并调试运行。



### Mu

- 11.4 java基础语法、变量、条件分支、循环、数组、字符串、正则表达式。

  ​

## 遇到的问题

- 11.4（Mu）vscode java编译环境，面向对象protected理解??
- 11.4（Xie）java内置类库的使用，vscode相关操作不熟练。
- 11.4（Long) 对html的内联还有头部元素等一些标签较为生疏。

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

从session中得到用户的userid，发给Service层



#### Service 层

功能：

**密码加密：**

```
PassWordService {
  String PassWord
  PasswordEncrypt (String ) {
    
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
  PassWordService Encoder
  //用户存在
  bool IsUserExist (){
	...
  }
  
  //密码匹配不匹配
  bool MarchPassword (){
    Encoder.PasswordEncrypt()
    ...
  }
}
```

**注册服务：**

```
RegisterService {
  User user
  PassWordService Encoder  
  
  bool Isvalid() {
    ...//是否重名 数据库操作
  }
  
  bool UserInsert() {
    if(this.IsValid()){
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
  List <BasicRecord> PresentRecord(int Type) {
  	//查数据库，找record，返回list
  	switch(Type){
      case:
      	...
      case:
      	...
    
  	}
  	返回一个排序的内容
  }
  
  //按类别查询
  List <BasicRecord> PresentCategoryRecord(){
    ...
  }
  
}
```

**数据库操作部分：**

```
DatabaseService {
  User user

  bool InsertDB() {
  	...  
  }
  
  bool DeleteDB(){
    ...
  }
  
  bool UpdateDB(){
    ...
  }
  
  bool IsFindDB(){
    ...
  }
  
  //时间内多少条这个user的记录
  int FindNum(Time t1， Time t2){
    ...
  }
  
  List <BasicRecord> FindRecord(Time StartTime, Time EndTime) {
  	//查数据库，找record，返回list
  }
  
   //按类别查询
  List <BasicRecord> FindRecord(){
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
  String Other // 其他
}
```




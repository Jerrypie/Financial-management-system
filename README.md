Personal Financial Management System

软件课程设计  -- HUST
## 工作进展

12.17  登录注册页面 前端 v1

12.21 登录后端开发完成

12.22 注册后端完成

12.24 加入了**MainService**， 封装了返回按时间排序的user所有records，按类别查询，使用首先应该setUser

1.10  数据可视化 问题：ie浏览器的支持度

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



## API（最初设计）

```
login:登录页面
	-/login:GET获取登录页面
	-/login:POST登录
		username
		password
		
register:注册页面
	-/register:GET获取注册页面
	-/register:POST注册
		username
		password
		email
		
main:
	-/main:GET获取该用户按时间排序的所有记录,概览页面
	-/main/mainPage/:GET获取某一分页记录
		currentPage
		
	-/main/category:GET获取该用户某支出类型所有记录
		(int) category:前端维护映射，保持一致就行
	-/main/categoryPage:GET获取支出类型某一分页记录
		(int) category：前端维护映射，保持一致就行
		currentPage
		
	-/main/Time:GET获取该用户某一段时间的所有记录
		(int) inTime
			1：近三天
			2：本周
			3：本月
			4：本年
	-/main/timePage:GET获取时间段内某一分页记录
		(int) inTime
			1：近三天
			2：本周
			3：本月
			4：本年
		currentPage
	
	-/main/Income:GET获取支出或收入记录
    	
	-/main/Outcome:GET获取支出或收入记录

			
	-/main/IncomePage:GET获取支出或收入记录
		currentPage

	-/main/OutcomePage:GET获取支出或收入记录
		currentPage
		
	-/main/Income:POST增加一条收入记录
		recordtime:收支时间
    	value:花的钱
    	category:种类
    	other:备注
    	
    -/main/Outcome:POST增加一条支出记录
		recordtime:收支时间
    	value:花的钱
    	category:种类
    	other:备注
    	
    -/main/deleteRecord: POST删除一条记录
    	recordnum：需删除的记录id
    	
    -/main/changeRecord: POST修改某一条记录
    	recordnum:记录的id
    	recordtime:收支时间
    	value:花的钱
    	category:种类
    	other:备注
```



## API（最新）

```
index:登录页面
	-/login:GET方法，获取登录页面
		input:
		
		return:
			"index.html":返回登录页面
			
	-/login:POST方法，提交表单登录
		input:
			String UserName:用户名
			Sting Password:密码
		return:
			"index.html":登录失败，用户密码输入错误或用户名（密码）为空，返回登录页面并提示
			"redirect:/main":登录成功，用户名及密码正确，重定向到main页面
			
register:注册页面
	-/register:GET方法，获取注册页面
		input:
		
		return:
			"register.html":返回注册页面
			
	-/register:POST方法，提交表单注册
        input:
            String UserName:用户名
            String Password:密码
            String Email:邮箱
        return:
        	"register.html":注册失败，输入的用户名已存在或用户名（密码）为空，返回注册页面并提示
        	"redirect:/index":注册成功，重定向到登录页面
		
main:财务管理页面
	-/main:GET方法，获取该用户按时间排序的所有记录的概览页面，并返回第一页记录
		input:
		return:
			"main.html":返回财务概览页面与第一页数据
		
	-/main/page:GET方法，获取某一分页记录
        input:
            int currentPage:所需记录的页数位置
        return:
        	"main.html":返回财务概览页面与所需页面数据
		
	-/main/addIncomeRecord:POST方法，增加一条收入记录
        input:
            String inTime:收支时间
            String inOther:备注
            double inValue:花的钱
            int inType:种类
        return:
        	"redirect:/main":重定向到财务概览页面第一页
    	
    -/main/addOutcomeRecord:POST方法，增加一条支出记录
        input:
            String inTime:收支时间
            String inOther:备注
            double inValue:花的钱
            int inType:种类
        return:
        	"redirect:/main":重定向到财务概览页面第一页
    	
    -/main/updateRecord: POST方法，修改某一条记录
        input:
            int mod_id:记录的id
            double mod_value:花的钱
            int mod_cal:种类
            String mod_time:收支时间
            String mod_other:备注
        return:
        	"redirect:/main":重定向到财务概览页面第一页
    	
    -/main/deleteRecord: POST方法，同步方式删除一条记录（勾选）
        input:
            int[] inRecords：需删除的记录id
        return:
        	"redirect:/main":重定向到财务概览页面第一页
    	
    -/main/record:DELETE方法，异步请求删除一条记录（右侧框）
        input:
            int id:记录的id
        return:
        	boolean:josn格式，删除成功为1
    	
    -/main/record/totalValue:GET方法，异步请求获取本月合计支出与收入（本月1日至今日）
        input:
        return:
        	double[] data1:json格式，其中data1[0]为收入，data1[1]为支出
    	
classStatic:财务统计页面

	-/classStatic:GET方法，获取财务统计页面
		input:
		return:
			"classStatic.html":返回财务统计页面
				
	-/main/record/value:GET方法，按请求参数获取支出或者收入记录
        input:
            int income:为1时获取收入记录，为0时获取支出记录
        return:
        	List<BasicRecord> records:json格式，支出或收入记录的列表
	
	-/main/record/someTime:GET方法，按请求参数获取某段时间内的记录
        input:
            String timestart:所需记录的开始日期
            String timeend:所需记录的结束日期
        return:
        	List<BasicRecord> records:json格式，时间段内记录的列表


figure1:条形图
	-/figure1:GET方法，获取条形图页面
		input:
		return:
			"figure1.html":返回条形图页面
	
	-/main/record/everyMonth:GET方法，获取某一年的每月合计支出与收入
        input:
            int year:所需记录数据的年份
        return:
        	double[] data1:json格式，每月的合计收入
        	double[] data2:json格式，每月的合计支出

figure2:饼状图
	-/figure2:GET方法，获取饼状图页面
        input:
        return:
        	"figure2.html":返回饼状图页面
	
	-/main/record/oneMonth:GET方法，获取某月各类型收入或支出和
        input:
            int year:所需记录数据的年份
            int month:所需记录数据的月份
            int income:所需支出或收入，为1时获取各类型收入记录，为0时获取各类型支出记录
        return:
        	List<Double> data1:json格式，各类型收入或支出和的列表

figure3:折线图
	-/figure3:GET方法，获取折线图页面
		input:
		return:
			"figure3.html":返回折线图页面
	
	-/main/record/everyDay:GET方法，获取某月每日收入、支出和与对应日期
		input:
            int year:所需记录数据的年份
            int month:所需记录数据的月份
        return:
        	double[] income:json格式,每日收入
    		double[] outcome:json格式，每日支出
    		int[] day:json格式，对应日期
		
sidebar:侧边菜单栏
	-/logout:用户登出
		input:
		return:
			"index.html":返回登录页面
		
	
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




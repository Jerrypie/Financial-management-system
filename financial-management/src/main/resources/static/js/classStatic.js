$(function () {
    //1.初始化Table
    var IncomeTable = new incomeTableInit();
    IncomeTable.Init();

    var OutcomeTable = new outTableInit();
    OutcomeTable.Init();

    $("#btn_query").on('click',function () {
        var startTime = $("#startTime").val();
        var endTime   = $("#endTime").val();
        var start = new Date(startTime);
        var end = new Date(endTime);
        if (start > end){
            alert("开始日期不能超过截止日期");
            return false;
        }else{
            console.log(startTime);
            console.log(endTime);
            $("#timeTable").bootstrapTable('destroy');
            var timeTable = new timeTableInit();
            timeTable.Init();
        }
    });


});

var incomeTableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#incomeTable').bootstrapTable({
            url: '/main/record/value',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            // toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                // checkbox: false
                // },{
                field: 'recordtime',
                title: '日期',
                formatter: dateFormatter,
                width: 150
            }, {
                field: 'value',
                title: '金额'
            }, {
                formatter: otherFormatter,
                field: 'category',
                title: '类型'
            }, {
                field: 'other',
                title: '备注'
            }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            income: 1
        };
        return temp;
    };

    return oTableInit;
};

var outTableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#outcomeTable').bootstrapTable({
            url: '/main/record/value',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            // toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: {income: 0},//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                // checkbox: false
                // },{
                field: 'recordtime',
                title: '日期',
                width: 150,
                formatter: dateFormatter
            }, {
                field: 'value',
                formatter: outcomeFormatter,
                title: '金额'
            }, {
                formatter: otherFormatter,
                field: 'category',
                title: '类型'
            }, {
                field: 'other',
                title: '备注'
            }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            income: 1
        };
        return temp;
    };

    return oTableInit;
};

function dateFormatter(value, row, index) {
    var formattedDate = new Date(value);
    var d = formattedDate.getDate();
    var m =  formattedDate.getMonth();
    m += 1;  // JavaScript months are 0-11
    var y = formattedDate.getFullYear();


    return formattedDate.getFullYear()
        +'-' + ('0' + (formattedDate.getMonth()+1)).slice(-2)
        +'-' + ('0' + formattedDate.getDate()).slice(-2);
}


var timeTableInit = function () {
    var timeObj = new Object();
    //初始化Table
    timeObj.Init = function () {
        $('#timeTable').bootstrapTable({
            url: '/main/record/someTime',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            // toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: timeObj.queryParams(),//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                // checkbox: false
                // },{
                field: 'recordtime',
                title: '日期',
                width: 150,
                formatter: dateFormatter
            }, {
                field: 'value',
                formatter: outcomeFormatter,
                title: '金额'
            }, {
                formatter: otherFormatter,
                field: 'category',
                title: '类型'
            }, {
                field: 'other',
                title: '备注'
            }]
        });
    };

    //得到查询的参数
    timeObj.queryParams = function () {
        return {
            timestart: $("#startTime").val(),
            timeend: $("#endTime").val()
        };
    };

    return timeObj;
};

function otherFormatter(value) {
    var otherstr;
    switch (value) {
        case 1:
            otherstr = "网购";
            break;
        case 2:
            otherstr = "餐饮";
            break;
        case 3:
            otherstr = "娱乐";
            break;
        case 4:
            otherstr = "生活";
            break;
        case 5:
            otherstr = "学习";
            break;
        default:
            otherstr = "其他";
    }

    return otherstr;
}

function outcomeFormatter(value, row, index){
    return -value;
}
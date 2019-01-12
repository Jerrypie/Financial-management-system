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
        } else if (!startTime){
            alert("请输入开始日期");
        } else if(!endTime){
            alert("请输入截止日期")
        } else{
            // console.log(startTime);
            // console.log(endTime);
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
            mobileResponsive:true,
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            showExport: true,                     //是否显示导出按钮
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
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
                formatter: dateFormatter
                // width: 150
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
            }],

            buttonsAlign:"right",  //按钮位置
            Icons:'glyphicon-export',
            exportTypes:['json', 'xml', 'csv', 'txt', 'sql', 'xlsx'],           //导出文件类型
            exportDataType: "all",             //basic当前页', 'all所有, 'selected'.
            exportOptions: {
                fileName: '个人财务记录',  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: '用户信息报表',
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight']
            }

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
            mobileResponsive:true,
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            showExport: true,                     //是否显示导出按钮
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: {income: 0},//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
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
            }],

            buttonsAlign:"right",  //按钮位置
            Icons:'glyphicon-export',
            exportTypes:['json', 'xml', 'csv', 'txt', 'sql', 'xlsx'],           //导出文件类型
            exportDataType: "all",             //basic当前页', 'all所有, 'selected'.
            exportOptions: {
                fileName: '个人财务记录',  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: '用户信息报表',
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight']
            }
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
    return (formattedDate.getFullYear() +'-' + ('0' + (formattedDate.getMonth()+1)).slice(-2) +'-' + ('0' + formattedDate.getDate()).slice(-2));
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
            mobileResponsive:true,
            showExport: true,                     //是否显示导出按钮
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: timeObj.queryParams(),//传递参数（*）
            sidePagination: "client",           //分页方式
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,                //模糊查询
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            showFooter: true,                   //显示footer
            // responseHandler: function (res) {
            //     return res.data;
            // },
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
                title: '金额',
                footerFormatter: function (val) {
                    var count = 0.0;
                    var income = 0.0;
                    var outcome = 0.0;
                    for (var i in val) {

                        if (val[i].value > 0){
                            income += val[i].value;
                        } else {
                            outcome += val[i].value;
                        }
                        // count += val[i].value;
                    }
                    count = income + outcome;
                    return "总计 "+ count+"元" +"&nbsp;"+"&nbsp;"+"&nbsp;" + "支出 "+ (-outcome) + " 元"+"&nbsp;"+"&nbsp;"+"&nbsp;" +  "收入 "+income +"元"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;";
                }
            }, {
                formatter: otherFormatter,
                field: 'category',
                title: '类型'
            }, {
                field: 'other',
                title: '备注'
            }],

            buttonsAlign:"right",  //按钮位置
            Icons:'glyphicon-export',
            exportTypes:['json', 'xml', 'csv', 'txt', 'sql', 'xlsx'],           //导出文件类型
            exportDataType: "all",             //basic当前页', 'all所有, 'selected'.
            exportOptions: {
                fileName: '个人财务记录',  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: '用户信息报表',
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight']
            }
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
    var otherStr="";
    switch (value) {
        case 1:
            otherStr="网络购物";
            break;
        case 2:
            otherStr="餐饮食品";
            break;
        case 3:
            otherStr="人情往来";
            break;
        case 4:
            otherStr="娱乐休闲";
            break;
        case 5:
            otherStr="生活居家";
            break;
        case 6:
            otherStr="学习进修";
            break;
        case 7:
            otherStr="其他支出";
            break;

        case 11:
            otherStr="其它收入";
            break;
        case 12:
            otherStr="兼职收入";
            break;

        case 13:
            otherStr="奖金收入";
            break;

        case 14:
            otherStr="工资收入";
            break;

        case 15:
            otherStr="理财收入";
            break;

        default:
            otherStr="其他";
    }

    return otherStr;
}

function outcomeFormatter(value, row, index){
    return -value;
}
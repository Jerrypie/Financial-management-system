
//默认
var now =  new Date();
var year  = now.getFullYear();
var month = now.getMonth()+1;

$("#time").val(now.getFullYear()+'-' + (now.getMonth()+1) );
//支出
var myChartOutcome = echarts.init(document.getElementById('piechartOutcome'));
//收入
var myChartIncome = echarts.init(document.getElementById('piechartIncome'));
//触发click
$("#btn").click(function () {

    var valid = /^(19|20)\d{2}-((0[1-9])|([1-9])|10|11|12)$/;
    var time = $("#time").val();
    if (valid.test(time)){
        var date = new Date(time);
        year = date.getFullYear();
        month = date.getMonth()+1;

        echartRefreshOutcome(myChartOutcome,year,month);
        echartRefreshIncome(myChartIncome,year,month);
    }else{
        alert("请输入正确的日期格式")
    }
});

myChartIncome.setOption({
    title:{
        text:'收入项目比例图'
    },
    tooltip:{},
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {
                show: true,
                type: ['pie', 'funnel']
            },
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    legend:{
        type: 'scroll',
        orient: 'vertical',
        right: 0,
        // top: 3,
        bottom: 10,
        data:['其它收入','兼职收入','奖金收入','工资收入','理财收入']
    },

    series: {
            type:'pie',
            selectedMode: 'single',
            radius: [0, '65%'],
        label: {
            normal: {
                formatter: '{b|{b}：}{c}  {per|{d}%}  ',
                rich: {
                }
            }
        },
            data:[
                {name: "测试1", value: 120},
                {name: "测试2", value: 120},
                {name: "测试3", value: 120}
            ]
    }
});

myChartOutcome.setOption({
    title:{
        text:'支出项目比例图'
    },
    tooltip:{},
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {
                show: true,
                type: ['pie', 'funnel']
            },
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    legend:{
        type: 'scroll',
        orient: 'vertical',
        right: 0,
        // top: 3,
        bottom: 10,
        data:['网络购物','餐饮食品','人情往来','娱乐休闲','生活居家','学习进修','其他支出']
    },
    series: {
        type:'pie',
        selectedMode: 'single',
        radius: [0, '65%'],
        label: {
            normal: {
                formatter: '{b|{b}：}{c}  {per|{d}%}  ',
                rich: {
                }
            }
        },
        data:[
            {name: "测试1", value: 120},
            {name: "测试2", value: 120},
            {name: "测试3", value: 120}
        ]
    }
});

echartRefreshOutcome(myChartOutcome,year,month);
echartRefreshIncome(myChartIncome,year,month);
// myChart1.showLoading();

function echartRefreshOutcome(myChart,year,month) {
//打开loading动画
    myChart.showLoading();
    $.ajax({
        type: "get",
        async: true, //异步请求数据
        url: "/main/record/oneMonth",
        data: {
            income: 0,
            year: year,
            month: month
        },
        dataType: "json", // 返回json
        success: function (result) {
            if (result){
                var dataArray =  result.data1;
                myChart.hideLoading();

                myChart.setOption({        //加载数据图表
                    series: {
                        type: 'pie',
                        data: [
                            {name: "网络购物", value: -dataArray[0]},
                            {name: "餐饮食品", value: -dataArray[1]},
                            {name: "人情往来", value: -dataArray[2]},
                            {name: "娱乐休闲", value: -dataArray[3]},
                            {name: "生活居家", value: -dataArray[4]},
                            {name: "学习进修", value: -dataArray[5]},
                            {name: "其他支出", value: -dataArray[5]}
                        ]
                    }
                });
            }//end if
        },//end success

        error: function (errorMsg) {
            // alert("请求图表数据失败");
            myChart.hideLoading();
        }
    });
}

function echartRefreshIncome(myChart,year,month) {
//打开loading动画
    myChart.showLoading();
    $.ajax({
        type: "get",
        async: true, //异步请求数据
        url: "/main/record/oneMonth",
        data: {
            income: 1,
            year: year,
            month: month
        },
        dataType: "json", // 返回json
        success: function (result) {
            if (result){
                var dataArray =  result.data1;
                myChart.hideLoading();

                myChart.setOption({        //加载数据图表
                    series: {
                        type: 'pie',
                        data: [
                            {name: "其它收入", value: dataArray[0]},
                            {name: "兼职收入", value: dataArray[1]},
                            {name: "奖金收入", value: dataArray[2]},
                            {name: "工资收入", value: dataArray[3]},
                            {name: "理财收入", value: dataArray[4]}
                        ]
                    }
                });
            }//end if
        },//end success

        error: function (errorMsg) {
            // alert("请求图表数据失败");
            myChart.hideLoading();
        }
    });
}


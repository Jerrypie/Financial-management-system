//初始化echarts实例

var myChart = echarts.init(document.getElementById('line1'));

//默认
var now =  new Date();
var year  = now.getFullYear();
var month = now.getMonth()+1;



$("#time").val(now.getFullYear()+'-' + (now.getMonth()+1) );

//触发click
$("#btn").click(function () {

    var valid = /^(19|20)\d{2}-((0[1-9])|([1-9])|10|11|12)$/;
    var val = $("#time").val();

    if (valid.test(val)){
        var time = $("#time").val();
        var date = new Date(time);
        year = date.getFullYear();
        month = date.getMonth()+1;
        echartRefresh(myChart,year,month);
    } else {
        alert("请输入正确日期格式");
    }
});


//设置图标配置项，初始化
myChart.setOption({
    title:{
        text:'收入支出趋势图',
        x:'left'
    },
    tooltip:{
        trigger: 'axis'
    },
    legend:{
        data:['当日收入','当日支出','当月结余']
    },

    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },


    xAxis:{
        type : 'category',
        boundaryGap : false
    },
    yAxis: {
        type : 'value'
    },
    series:[
        {
            name:'当日收入',
            type:'line',
            data:[],
            smooth: true,
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    // {type : 'min', name: '最小值'}
                ]
            }
        },
        {
            name:'当日支出',
            type:'line',
            smooth: true,
            data:[],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    // {type : 'min', name: '最小值'}
                ]
            }
        },
        {
            name:'当月结余',
            color: 'green',
            type:'line',
            smooth: true,

            itemStyle:{
                normal:{
                    lineStyle:{
                        width:2,
                        type:'dotted'  //'dotted'虚线 'solid'实线
                    }
                }
            },
            data:[]
        }
    ]
});

echartRefresh(myChart,year,month);

function echartRefresh(myChart,year,month) {
//打开loading动画
    myChart.showLoading();
    $.ajax({
        type: "get",
        async: true, //异步请求数据
        url: "/main/record/everyDay",
        data: {
            year: year,
            month: month
        },
        dataType: "json", // 返回json
        success: function (result) {
            if (result){

                var dayIncome = result.income;
                var dayOutcome = result.outcome;
                var dayBalance = [];
                var monthBalance = [];

                for (var i =0; i<dayIncome.length;i++){
                    dayBalance[i] = dayIncome[i] + dayOutcome[i];
                }

                monthBalance[0] = dayBalance[0];
                for (var j = 1; j<dayIncome.length; j++){
                    monthBalance[j] = monthBalance[j-1]+dayBalance[j];
                }

                myChart.hideLoading();

                myChart.setOption({
                    xAxis:{
                        data:result.day
                    },
                    series: [
                        {
                            name:'当日收入',
                            type:'line',
                            data: dayIncome
                        },
                        {
                            name:'当日支出',
                            type:'line',
                            data: dayOutcome
                        },
                        {
                            name:'当月结余',
                            type:'line',
                            data: monthBalance
                        }
                    ]
                });
            }//end if
        },//end success

        error: function (errorMsg) {
            // alert("请求图表数据失败");
            myChart.hideLoading();
        }
    });

}


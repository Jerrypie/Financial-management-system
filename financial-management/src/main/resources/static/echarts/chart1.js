

//默认2018年
var year = 2018;

//初始化echarts实例
var myChart = echarts.init(document.getElementById('linechart1'));

//触发click
$("#btn").click(function () {
    year = $("#year").val();
    echartRefresh(myChart,year);
});

//设置图标配置项
myChart.setOption({
    title:{
        text:'收入支出月份统计图'
    },
    tooltip:{},
    legend:{
        data:['收入','支出','净收入']
    },
    xAxis:{
        data:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
    },
    yAxis:{},
    series:[
        {
            name:'收入',
            type:'bar',
            data:[]
        },
        {
            name:'支出',
            type:'bar',
            data:[]
        },
        {
            name:'净收入',
            type:'bar',
            data:[]
        }
    ]
});

echartRefresh(myChart, year);

function echartRefresh(myChart,year) {


//打开loading动画
    myChart.showLoading();

    var income = [];
    var outcome = [];
    var purecome = [];

    $.ajax({
        type: "get",
        async: true, //异步请求数据
        url: "/main/record/everyMonth",
        data: {year: year},
        dataType: "json", // 返回json
        success: function (result) {
            if (result){
                income =  result.data1;
                outcome = result.data2;

                for (var j =0; j < outcome.length; j++){
                    outcome[j] = -outcome[j];
                }

                for(var i = 0; i < income.length; i++) {
                    purecome[i]=income[i]-outcome[i];
                }

                myChart.hideLoading();

                myChart.setOption({        //加载数据图表
                    series: [
                        {
                            name:'收入',
                            type:'bar',
                            data: income
                        },
                        {
                            name:'支出',
                            type:'bar',
                            data: outcome
                        },
                        {
                            name:'净收入',
                            type:'bar',
                            data: purecome
                        }

                    ]
                });
            }//end if
        },//end success

        error: function (errorMsg) {
            alert("请求图表数据失败");
            myChart.hideLoading();
        }
    });
}




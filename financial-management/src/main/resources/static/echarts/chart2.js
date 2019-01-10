
//默认
var now =  new Date();
var year  = now.getFullYear();
var month = now.getMonth()+1;

$("#time").val(now.getFullYear()+'-' + (now.getMonth()+1) );
//支出
var myChart1 = echarts.init(document.getElementById('piechart1'));
//收入
var myChart2 = echarts.init(document.getElementById('piechart2'));
//触发click
$("#btn").click(function () {
    var time = $("#time").val();
    var date = new Date(time);
    year = date.getFullYear();
    month = date.getMonth()+1;

    echartRefresh(myChart1,year,month,0);
    echartRefresh(myChart2,year,month,1);
});

myChart1.setOption({
    title:{
        text:'收入项目比例图'
    },
    tooltip:{},
    series: {
            type:'pie',
            data:[
                {name: "测试1", value: 120},
                {name: "测试2", value: 120},
                {name: "测试3", value: 120}
            ]
    }
});

myChart2.setOption({
    title:{
        text:'收入项目比例图'
    },
    tooltip:{},
    series: {
        type:'pie',
        data:[
            {name: "测试1", value: 120},
            {name: "测试2", value: 120},
            {name: "测试3", value: 120}
        ]
    }
});

echartRefresh(myChart1,year,month,0);
echartRefresh(myChart2,year,month,1);
// myChart1.showLoading();

function echartRefresh(myChart,year,month,income) {
//打开loading动画
    myChart.showLoading();
    $.ajax({
        type: "get",
        async: true, //异步请求数据
        url: "/main/record/oneMonth",
        data: {
            income: income,
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
                            {name: "网购", value: (income*2-1)*dataArray[0]},
                            {name: "餐饮", value: (income*2-1)*dataArray[1]},
                            {name: "娱乐", value: (income*2-1)*dataArray[2]},
                            {name: "生活", value: (income*2-1)*dataArray[3]},
                            {name: "学习", value: (income*2-1)*dataArray[4]},
                            {name: "其他", value: (income*2-1)*dataArray[5]}
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


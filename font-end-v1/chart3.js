//初始化echarts实例
var myChart = echarts.init(document.getElementById('line1'));
//设置图标配置项
myChart.setOption({
    title:{
        text:'收入支出月份趋势图',
        x:'center'
    },
    tooltip:{},
    legend:{
        data:['当日收入','当日支出','当月结余'],
        y:'95% 90%'
    },
    xAxis:{
        data:[]
    },
    yAxis:{},
    series:[
        {
            name:'当日收入',
            type:'line',
            data1:[]
        },
        {
            name:'当日支出',
            type:'line',
            data2:[]
        },
        {
            name:'当月结余',
            type:'line',
            data5:[]
        }
    ]
})


//打开loading动画
myChart.showLoading();
function bindData(){
    //定时，读取数据
    setTimeout(function(){
        //获取数据后，隐藏loading动画
        myChart.hideLoading();
        //异步加载数据
        // json文件最后一行不能有逗号
        $.get('data3.json').done(function (data) {

            // 取出每月json文件的收入支出数组
            var data3=[];
            var data4=[];
            var data5=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
            data3=data.data1;
            data4=data.data2;
            var datasum=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
            // 每日结余计算
            for(var i = 0; i < data5.length; i++)
            {
                data5[i]=data3[i]-data4[i];
            }
            datasum[0]=data5[0];
            // 每月当前日期结余
            for(var j = 1; j < datasum.length; j++)
            {
                datasum[j]= datasum[j-1]+ data5[j];
            }
            myChart.setOption({
                xAxis:{
                    data:["1日","2日","3日","4日","5日","6日","7日","8日","9日","10日","11日","12日","14日","15日","16日","17日","18日","19日","20日","21日","22日","23日","24日","25日","26日","27日","28日","29日","30日","31日"],
                },
                series:[
                    {
                        //根据名字对应到相应的系列
                        name:"当日收入",
                        data:data.data1
                    },
                    {
                        //根据名字对应到相应的系列
                        name:"当日支出",
                        data:data.data2
                    },
                    {
                        //根据名字对应到相应的系列
                        name:"当月结余",
                        data:datasum
                    }
                ]
            })
            // 图表自适应div大小
            window.onresize = myChart.resize;
        })
    },1500)
}

bindData()

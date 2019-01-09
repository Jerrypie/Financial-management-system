//初始化echarts实例
var myChart = echarts.init(document.getElementById('linechart1'));
//设置图标配置项
myChart.setOption({
    title:{
        text:'收入支出月份统计图'
    },
    tooltip:{},
    legend:{
        data:['收入','支出']

    },
    xAxis:{
        data:[]
    },
    yAxis:{},
    series:[
        {
            name:'收入',
            type:'bar',
            data1:[]
        },
        {
            name:'支出',
            type:'bar',
            data2:[]
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
        $.get('data.json').done(function (data) {
            // data = eval('('+data+')');
            // var data3=data.data1-data.data2;
            myChart.setOption({
                xAxis:{
                    data:data.name
                },
                series:[
                    {
                        //根据名字对应到相应的系列
                        name:"收入",
                        data:data.data1
                    },
                    {
                        //根据名字对应到相应的系列
                        name:"支出",
                        data:data.data2
                    }
                ]
            })
        })
    },1500)
}

bindData()

var myChart2 = echarts.init(document.getElementById('piechart1'));

myChart2.setOption({
    title:{
        text:'支出项目比例图'
    },
    tooltip:{},
    series:[
        {
            name:'支出',
            type:'pie',
            data:[]
        }
    ]
})

myChart2.showLoading();
function bindData(){
    //定时，读取数据
    setTimeout(function(){
        //获取数据后，隐藏loading动画
        myChart2.hideLoading();
        //异步加载数据
        // json文件最后一行不能有逗号
        $.get('data2.json').done(function (data) {
            // data = eval('('+data+')');
            myChart2.setOption({
                series:[
                    {
                        //根据名字对应到相应的系列
                        data:data.data
                    }
                ]
            })
        })
    },1500)
}

bindData()
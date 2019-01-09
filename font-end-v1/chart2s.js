var myChart1 = echarts.init(document.getElementById('piechart2'));

myChart1.setOption({
    title:{
        text:'收入项目比例图'
    },
    tooltip:{},
    series:[
        {
            name:'收入',
            type:'pie',
            data:[]
        }
    ]
})

myChart1.showLoading();
function bindData(){
    //定时，读取数据
    setTimeout(function(){
        //获取数据后，隐藏loading动画
        myChart1.hideLoading();
        //异步加载数据
        // json文件最后一行不能有逗号
        $.get('data2s.json').done(function (data) {
            // data = eval('('+data+')');
            myChart1.setOption({
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
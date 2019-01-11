$(document).ready(function(){
    $(".btn_delete").click(function () {
        var th = $(this);
        var id = $(this).attr('value');

        var a=confirm("是否删除?");
        if(a){
            $.ajax({
                type: "post",
                url:"/main/record",
                dataType: "json",
                data:{
                    '_method':'delete',
                    "id":id
                },
                success: function (result) {
                    // console.log(th.attr('value'));
                    th.parent().parent().remove();
                }
            })
        }

    });

    $.ajax({
        type: "get",
        url:"/main/record/totalValue",
        dataType: "json",
        data:{},
        success: function (result) {
            // result.data1[0];
            // console.log(result.data1[0]);
            $("#presentIncome").append("本月总收入："+ result.data1[0] + "元 ," + "总支出 "+ (-result.data1[1]) + "元");
        }
    });

    var list = $(".category");

    list.each(function () {
        // console.log($(this).text());
        var num = $(this).text();
        switch (num) {
            case "1":
                $(this).text("网购");
                break;
            case "2":
                $(this).text("餐饮");
                break;
            case "3":
                $(this).text("娱乐");
                break;
            case "4":
                $(this).text("生活");
                break;
            case "5":
                $(this).text("学习");
                break;
            case "6":
                $(this).text("其他");
                break;
            default:
                $(this).text("其他");
        }

        $(this).attr("number",num);
    });

    $(".btn_edit").click(function(){
        $("#modal_other").val($(this).parent().prev().text());
        var a = $(this).parent().prev();

        $("#modal_ca").val(a.prev().attr("number"));
        $("#modal_val").val(a.prev().prev().text());
        $("#modal_time").val(a.prev().prev().prev().text());
        $("#mod_id").val( $(this).attr('id') );

    });


});


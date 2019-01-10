$(document).ready(function(){
    $(".btn_edit").click(function(){
        $("#modal_other").val($(this).parent().prev().text());
        var a = $(this).parent().prev();
        $("#modal_ca").val(a.prev().text());
        $("#modal_val").val(a.prev().prev().text());
        $("#modal_time").val(a.prev().prev().prev().text());
        $("#mod_id").val( $(this).attr('id') );
        // console.log($(this).attr('id'));
    });

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
            console.log(result.data1[0]);
            $("#presentIncome").append("本月总收入："+ result.data1[0] + "元 ," + "总支出 "+ (-result.data1[1]) + "元");
        }
    })

});

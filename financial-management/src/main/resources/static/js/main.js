$(document).ready(function(){

    changeType();
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

    $(".btn_edit").click(function(){
        $("#modal_other").val($(this).parent().prev().text());
        var other = $(this).parent().prev();
        var typeVal  = other.prev().attr("category");

        if (typeVal < 10) {
            //处理支出情况
            changeModaloutcome(typeVal);
        } else {
            //收入情况
            changeModalIncome(typeVal);
        }

        $("#modal_val").val(other.prev().prev().text());
        $("#modal_time").val(other.prev().prev().prev().text());
        $("#mod_id").val( $(this).attr('id') );
    });

    $("#inBtn").click(changeInVal());


    $("#outBtn").click(changeOutVal());


});


function changeInVal() {
    var inVal = $("#inVal").val();

    if (inVal < 0){
        $("#inVal").val(-inVal);
    }
}

function changeOutVal() {
    var outVal = $("#outVal").val();
    // console.log(outVal);
    if (outVal > 0){
        $("#outVal").val(-outVal);
    }
}

function changeType() {
    var list = $(".category");

    list.each(function () {
        // console.log($(this).text());
        var num = $(this).attr("category");
        switch (num) {
            case "1":
                $(this).text("网络购物");
                break;
            case "2":
                $(this).text("餐饮食品");
                break;
            case "3":
                $(this).text("人情往来");
                break;
            case "4":
                $(this).text("娱乐休闲");
                break;
            case "5":
                $(this).text("生活居家");
                break;
            case "6":
                $(this).text("学习进修");
                break;
            case "7":
                $(this).text("其他支出");
                break;

            case "11":
                $(this).text("其它收入");
                break;
            case "12":
                $(this).text("兼职收入");
                break;

            case "13":
                $(this).text("奖金收入");
                break;

            case "14":
                $(this).text("工资收入");
                break;

            case "15":
                $(this).text("理财收入");
                break;

            default:
                $(this).text("其他");
        }
    });
}

function changeModalIncome(typeVal) {
    //处理收入情况
    $("#modal_ca").empty();

    $("#modal_ca").append(
        "<option value=15>理财收入</option>" +
        "<option value=14>工资收入</option>" +
        "<option value=13>奖金收入</option>" +
        "<option value=12>兼职收入</option>" +
        "<option value=11>其它收入</option>"
    );
    $("#modal_ca").val(typeVal);
}

function changeModaloutcome(typeVal) {
    //处理收入情况
    $("#modal_ca").empty();

    $("#modal_ca").append(
        "<option value=7>其他支出</option>" +
        "<option value=6>学习进修</option>" +
        "<option value=5>生活居家</option>" +
        "<option value=4>娱乐休闲</option>" +
        "<option value=3>人情往来</option>" +
        "<option value=2>餐饮食品</option>" +
        "<option value=1>网络购物</option>"
    );

    $("#modal_ca").val(typeVal);
}

function changeModalByValue(){
    var changeval = $("#modal_val").val();

    var selectVal = $("#modal_ca").val();

    if (changeval >= 0 ){
        // 变为收入
        selectVal = (selectVal > 10) ? selectVal : 11;
        // console.log(selectVal);
        changeModalIncome(selectVal);
    } else {
        //变为支出
        selectVal = (selectVal < 10) ? selectVal : 7;
        // console.log(selectVal);
        changeModaloutcome(selectVal);
    }
}


function checkFormatOut() {
    var valid  = /^(\d{1,4})(-)(\d{1,2})\2(\d{1,2})$/;
    var val = $("#outTime").val();
    if (!valid.test(val)){
        alert("请输入正确的日期格式");
        return false;
    }
}


function checkFormatIn() {
    var valid  = /^(\d{1,4})(-)(\d{1,2})\2(\d{1,2})$/;
    var val = $("#inTime").val();
    if (!valid.test(val)){
        alert("请输入正确的日期格式");
        return false;
    }
}


function checkFormatMod() {
    var valid  = /^(\d{1,4})(-)(\d{1,2})\2(\d{1,2})$/;
    var val = $("#modal_time").val();
    if (!valid.test(val)){
        alert("请输入正确的日期格式");
        return false;
    }
}



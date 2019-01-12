

function Checked()
{
 //   var strmg = myform.UserName.value;
    var pass = myform.Password.value;
    var pass2 = myform.Password2.value;

    if(pass != pass2){//判断两次输入的值是否一致，不一致则显示错误信息
        alert("两次输入的密码不一致！");
        //myform.Password2.focus();
        return false;
    }
}
//各种格式的验证

function checkOnInput(input, tip) {//对用户名的验证（开头不能是数字）
    if (input.validity.patternMismatch === true) {
        input.setCustomValidity(tip);
    }
}

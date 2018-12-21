

function Checked()
{
 //   var strmg = myform.UserName.value;
    var pass = myform.Password.value;
    var pass2 = myform.Password2.value;
  //  var eml = myform.Email.value;

/*    if(strmg == "")//如果用户名为空
    {
        alert("您还没有填写用户名！");
        myform.UserName.focus();
        return false;
    }
    if(pass == "")//如果密码为空
    {
        alert("您忘记填写密码了！");
        //myform.Password.focus();
        return false;
    }
    if(pass2 == "")//如果密码为空
    {
        alert("您忘记填写密码了！");
        //myform.Password2.focus();
        return false;
    }
    if(eml == "")//如果邮箱为空
    {
        alert("您忘记填写邮箱了！");
        //myform.email.focus();
        return false;
    }
*/

    if(pass != pass2){//判断两次输入的值是否一致，不一致则显示错误信息
        alert("两次输入的密码不一致！");
        //myform.Password2.focus();
        return false;
    }
}
//各种格式的验证

function checkOnInput1(input, tip) {//对用户名的验证（开头不能是数字）
    if (input.validity.patternMismatch === true) {
        input.setCustomValidity(tip);
    } else {
        input.setCustomValidity('');
    }
}
function checkOnInput2(input, tip) {//对邮箱格式的验证
    if (input.validity.patternMismatch === true) {
        input.setCustomValidity(tip);
    } else {
        input.setCustomValidity('');
    }
}
function checkOnInput3(input, tip) {//对密码格式的验证
    if (input.validity.patternMismatch === true) {
        input.setCustomValidity(tip);
    } else {
        input.setCustomValidity('');
    }
}
function checkOnInput4(input, tip) {//对重复密码格式的验证
    if (input.validity.patternMismatch === true) {
        input.setCustomValidity(tip);
    } else {
        input.setCustomValidity('');
    }
}
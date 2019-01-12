function Checked()
{
    var strmg = myform.UserName.value;
    var pass = myform.Password.value;

    if(strmg.isEmpty())//如果用户名为空
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
}

function checkOnInput(input, tip) {//对用户名的验证（开头不能是数字）
    if (input.validity.patternMismatch === true) {
        input.setCustomValidity(tip);
    }
}

function Checked()
{
    var strmg = myform.UserId.value;
    var pass = myform.Password.value;

    if(strmg == "")//如果用户名为空
    {
        alert("您还没有填写用户名！");
        myform.UserId.focus();
        return false;
    }
    if(pass == "")//如果密码为空
    {
        alert("您忘记填写密码了！");
        //myform.Password.focus();
        return false;
    }
}
//页面加载的时候,所有的复选框都是未选中的状态
function chooseAllOrNot(){
    var chElt=document.getElementById("chooseAll");
    var checkedElt=chElt.checked;
    console.log(checkedElt)
    var allCheck=document.getElementsByClassName("chooseRecord");
    if(checkedElt){
        for(var i=0;i<allCheck.length;i++){
            allCheck[i].checked=true;
        }
    }else{
        for(var i=0;i<allCheck.length;i++){
            allCheck[i].checked=false;
        }
    }
}
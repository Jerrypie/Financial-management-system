$.sidebarMenu = function(menu) {
  var animationSpeed = 300;
  
  $(menu).on('click', 'li a', function(e) {
    var $this = $(this);
    var checkElement = $this.next();

    if (checkElement.is('.treeview-menu') && checkElement.is(':visible')) {
      checkElement.slideUp(animationSpeed, function() {
        checkElement.removeClass('menu-open');
      });
      checkElement.parent("li").removeClass("active");
    }

    //If the menu is not visible
    else if ((checkElement.is('.treeview-menu')) && (!checkElement.is(':visible'))) {
      //Get the parent menu
      var parent = $this.parents('ul').first();
      //Close all open menus within the parent
      var ul = parent.find('ul:visible').slideUp(animationSpeed);
      //Remove the menu-open class from the parent
      ul.removeClass('menu-open');
      //Get the parent li
      var parent_li = $this.parent("li");

      //Open the target menu and add the menu-open class
      checkElement.slideDown(animationSpeed, function() {
        //Add the class active to the parent li
        checkElement.addClass('menu-open');
        parent.find('li.active').removeClass('active');
        parent_li.addClass('active');
      });
    }
    //if this isn't a link, prevent the page from being redirected
    if (checkElement.is('.treeview-menu')) {
      e.preventDefault();
    }
  });
}

// <!-- 可滑出侧边栏 -->
$(document).ready(function () {
  var trigger = $('.hamburger'),
    overlay = $('.overlay'),
   isClosed = false;

  trigger.click(function () {
    hamburger_cross();      
  });

  function hamburger_cross() {

    if (isClosed == true) {          
    overlay.hide();
    trigger.removeClass('is-open');
    trigger.addClass('is-closed');
    isClosed = false;
    } else {   
    overlay.show();
    trigger.removeClass('is-closed');
    trigger.addClass('is-open');
    isClosed = true;
    }
  }
  
  $('[data-toggle="offcanvas"]').click(function () {
    $('#wrapper').toggleClass('toggled');
  });  
});

// var browser={
//   versions:function(){
//       var u = navigator.userAgent, app = navigator.appVersion;
//       //移动设备浏览器版本信息
//       return {
//           //IE内核
//           trident: u.indexOf('Trident') > -1,
//           //opera内核      
//           presto: u.indexOf('Presto') > -1,
//           //苹果、谷歌内核          
//           webKit: u.indexOf('AppleWebKit') > -1,
//           //火狐内核
//           gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,
//           //是否为移动终端
//           mobile: !!u.match(/AppleWebKit.*Mobile.*/), 
//           //ios终端
//           ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
//           //android终端或者uc浏览器
//           android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, 
//           //是否为iPhone或者QQHD浏览器
//           iPhone: u.indexOf('iPhone') > -1 , 
//           //是否iPad           
//           iPad: u.indexOf('iPad') > -1,
//           //是否web应该程序，没有头部与底部
//           webApp: u.indexOf('Safari') == -1
//       };
//   }(), 
//   language:(navigator.browserLanguage || navigator.language).toLowerCase()  
// }   
 
// if(browser.versions.mobile || browser.versions.ios || browser.versions.android || browser.versions.iPhone || browser.versions.iPad){    cssChange();     
// }  

// function cssChange(){
//   var link = document.getElementsByTagName('link')[0];
//   //PC端应用的样式文件：style_A.css
//   alert('当前应用样式文件是：'+link.getAttribute('href'));
//   link.setAttribute('href','style_B.css');
//   //Mobile端应用样式文件：style_B.css
//   alert('当前应用样式文件是：'+link.getAttribute('href'));
// }

// //0.1s后模拟点击
// setTimeout(function() {
//   // IE
//   if(document.all) {
//     document.getElementById("parentIframe").click();
//   }
//   // 其它浏览器
//   else {
//     var e = document.createEvent("MouseEvents");
//     e.initEvent("click", true, true);
//     document.getElementById("parentIframe").dispatchEvent(e);
//   }
// }, 100);

jQuery(function($) {
        
  $(".sidebar-dropdown > a").click(function(){
    $(".sidebar-submenu").slideUp(250);
    if ($(this).parent().hasClass("active")){
        $(".sidebar-dropdown").removeClass("active");
        $(this).parent().removeClass("active");
    }else{
      $(".sidebar-dropdown").removeClass("active");
      $(this).next(".sidebar-submenu").slideDown(250);
       $(this).parent().addClass("active");
    }

  });

   $("#toggle-sidebar").click(function(){
     $(".page-wrapper").toggleClass("toggled");	    
    });

     if(! /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
             $(".sidebar-content").mCustomScrollbar({
                      axis:"y",
                      autoHideScrollbar: true,
                      scrollInertia: 300
              });
              $(".sidebar-content").addClass("desktop");

      }
});
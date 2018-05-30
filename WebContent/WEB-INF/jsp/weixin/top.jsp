<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="微信商城">
<meta http-equiv="description" content="微信商城">
<meta charset="UTF-8">
<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">
<!-- 启用 WebApp 全屏模式（IOS）-->
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<!--设置状态栏的背景颜色（IOS）-->
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<!-- 关闭电话号码的自动识别-->
<meta content="telephone=no" name="format-detection">
<!-- 关闭邮箱的自动识别-->
<meta content="email=no" name="format-detection">
<title></title>
<link rel="stylesheet" href="http://apps.bdimg.com/libs/animate.css/3.1.0/animate.min.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/global.css" />
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/js/doT.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/common.js"></script>
<script src="${SHOPDOMAIN}/front/js/jquery.lazyload.min.js"></script>
<script>
	var SHOPDOMAIN = "${SHOPDOMAIN}";
	var USERIMGSRC = "${USERIMGSRC}";
	var userId = "${sessionFrontUser.id}";
</script>
 <script>
        if(/Android (\d+\.\d+)/.test(navigator.userAgent)){
            var version = parseFloat(RegExp.$1);
            if(version>2.3){
                var phoneScale = parseInt(window.screen.width)/640;
                document.write('<meta name="viewport" content="width=640, minimum-scale = '+ phoneScale +', maximum-scale = '+ phoneScale +', target-densitydpi=device-dpi">');
            }else{
                document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
            }
        }else{
            document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
        }
    </script>
</head>
<body>
<div class="w-fixedlandscape">
	<img src="${SHOPDOMAIN}/front/images/wap/lineRow.png" style="margin-top:20px;"/>
    <p class="w-fixedlandscapeP">请 <b>切换到竖屏</b>，以达到最佳浏览效果</p>
</div>
<script>
window.addEventListener('orientationchange', function(event){
    if ( window.orientation == 180 || window.orientation==0 ) {
        window.location.href= window.location.href;
       	$('.w-fixedlandscape').css('display','none');
    }
    if( window.orientation == 90 || window.orientation == -90 ) {
       	$('.w-fixedlandscape').css('display','block');
    }
});
</script>

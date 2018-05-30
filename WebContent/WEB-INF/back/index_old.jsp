<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>茶王争霸赛</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/css.css">
    <link href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="${RESOURCEDOMAIN}/assets/plugins/bootbox/bootbox.min.js"
	type="text/javascript"></script>
    <script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</head>
<body>
<div class="verticalScreen">
    <div class=" index-body">
        <a href="${RESOURCEDOMAIN}/teaKing/answer">
            <div class="start_btn"></div>
        </a>
        <p class="index-gz">规则说明</p>
        <div class="tea1"></div>
        <div class="tea2"></div>
    </div>
</div>
<p class="crossScreen">请竖屏</p>
</body>
<script>
	var result = "${result}";
	if(result == "1"){
		bootbox.dialog({
			message : "${message} ",
			title : "提示",
			buttons : {
				main : {
					label : "确定!",
					className : "blue",
					callback : function() {
					}
				}
			}
		});
	}
	pushHistory();
	window.addEventListener("popstate", function(e) {
	    //alert("我监听到了浏览器的返回按钮事件啦");//根据自己的需求实现自己的功能
		if(confirm("确定要退出系统？"))
			WeixinJSBridge.call('closeWindow');
	}, false);

	function pushHistory() {
	    var state = {
	        title: "title",
	        url: "#"
	    };
	    window.history.pushState(state, "title", "#");
	}
</script>
</html>
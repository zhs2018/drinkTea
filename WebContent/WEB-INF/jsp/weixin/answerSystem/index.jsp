<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no,email=no" name="format-detection">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>答题赢好礼</title>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/style.css">
<link
	href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>
	<div class="verticalScreen">
		<div class="index-bg">
			<img class="index-b" src="${RESOURCEDOMAIN}/image/weixin/img1.png"
				alt=""> <a href="${RESOURCEDOMAIN}/answer/answer">
				<div class="index-btn"></div></a>

				 <a href="${RESOURCEDOMAIN}/answer/rule">
				<div class="index-btn_1"></div></a>
		</div>
	</div>

	<p class="crossScreen">请竖屏</p>
	<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
	<script
		src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="http://bootboxjs.com/bootbox.js"></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</body>
<script>
	var result = "${result}";
	if (result == "1") {
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
	} else if (result == "2") {
		bootbox.dialog({
			message : "${message} ",
			title : "提示",
			buttons : {
				main : {
					label : "确定!",
					className : "blue",
					callback : function() {
						window.location.href = "${RESOURCEDOMAIN}/index";
					}
				}
			}
		});
	}
	pushHistory();
	window.addEventListener("popstate", function(e) {
		//alert("我监听到了浏览器的返回按钮事件啦");//根据自己的需求实现自己的功能
		if (confirm("确定要退出系统？"))
			WeixinJSBridge.call('closeWindow');
	}, false);

	function pushHistory() {
		var state = {
			title : "title",
			url : "#"
		};
		window.history.pushState(state, "title", "#");
	}
</script>
</html>
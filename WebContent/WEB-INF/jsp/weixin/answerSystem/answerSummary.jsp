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
</head>
<body>
	<div class="verticalScreen">
		<div class="summary-bg">
			<div class="summary-b-div">
				<img class="summary-b" src="${RESOURCEDOMAIN}/image/weixin/img2.png"
					alt="">
			</div>
			<div>
				<p style="color: red;">答案：${sk1.option} &nbsp; ${sk2.option}
					&nbsp; ${sk3.option}</p>
				<p>${sk.intro}</p>
			</div>
			<div class="summary-frame">
				<p>
					你本次答对了<label>${count}</label>问
				</p>
				<p>
					获得红包<label>${money}</label>元
				</p>
				<p>${result.count}问中总共答对<label>${result.allRight}</label>问
				</p>
				<p>
					累计获得<label>${result.money }</label>元
				</p>
				<div class="tea"></div>
			</div>
		</div>
	</div>

	<p class="crossScreen">请竖屏</p>
	<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
	<script>
		var result = "${result}";
		if (result == "1") {
			alert("${message}");
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
</body>
</html>
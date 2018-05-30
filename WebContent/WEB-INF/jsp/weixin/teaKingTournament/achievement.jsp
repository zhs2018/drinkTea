<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>茶王争霸</title>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/css.css">
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</head>
<body>

	<div class="achievement-bg">
		<div class="achievement-surplus"></div>
		<div class="achievement-nmb">答题完成</div>
		<p class="achievement-p">
			答题所用时间<label>${time}</label>mm
		</p>
		<p class="achievement-p">
			请在<label>${endTime}</label>以后查看题目答案
		</p>
		<ul class="achievement-ul">
			<!--查看上期题目-->
			<a href="viewTopic">
				<li class="see">查看上期题目</li>
			</a>
			<!--返回首页-->
			<a href="index">
				<li class="ranking">返回首页</li>
			</a>
		</ul>
	</div>
</body>
<script>
	pushHistory();
	window.addEventListener("popstate", function(e) {
		window.location.href = "${RESOURCEDOMAIN}/teaKing/index";
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
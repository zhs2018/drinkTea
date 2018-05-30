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
	<div class="verticalScreen" id="vertical">
		<img src="${RESOURCEDOMAIN}/image/weixin/draw/0120.gif" alt=""
			width="100%" height="100%">
	</div>
	<p class="crossScreen">请竖屏</p>
	<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
	<script
		src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="http://bootboxjs.com/bootbox.js"></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</body>
<script>
	$(function() {
		function jump() {
			window.location.href = "${RESOURCEDOMAIN}/answer/indexes";
		}
		setTimeout(jump, 3000);
	})
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no,email=no" name="format-detection">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>我的抽奖</title>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
<style>
body {
	background: #eee;
}
</style>
</head>
<body>
	<div class="verticalScreen">
		<div class="envelope-top">
			<div class="envelope-f">
				<img src="${user.image}" alt="">
				<p class="envelope-name">${user.userName}</p>
			</div>
		</div>
		<div class="envelope-div">
			<p class="envelope-t">抽奖记录</p>
			<ul class="envelope-list">

				<c:forEach items="${wList}" var="list" varStatus="vs">
					<li>
						<div class="envelope-li-div">
							<h4>${list.prizePro.prizeName}</h4>
							<p>
								<fmt:formatDate value="${list.time}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</p>
						</div>
					</li>
				</c:forEach>

			</ul>
		</div>
	</div>
	<p class="crossScreen">请竖屏</p>
	<script src="${RESOURCEDOMAIN}/js/layer1/js/jquery-1.11.2.min.js"></script>
	<script src="${RESOURCEDOMAIN}/js/layer1/js/html_rem.js"></script>
</body>
</html>
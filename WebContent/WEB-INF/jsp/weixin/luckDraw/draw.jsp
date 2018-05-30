
<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no,email=no" name="format-detection">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>欢乐抽奖</title>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
<style>
h3 {
	text-align: right;
}

.aboutUs-div-p label {
	color: red;
}
</style>
</head>
<body>
	<div class="verticalScreen">
		<div class="aboutUs-div">
			<div class="aboutUs-div-p">
				<c:choose>
					<c:when test="${not empty pwfs}">
						<p style="text-align: center;">${pwfs.title}</p>
						<p>${pwfs.intro}</p>

						<c:choose>
							<c:when test="${not empty mList}">
								<c:forEach items="${mList}" var="m" varStatus="vs">
									<a href="javascript:void(0)" onclick="pDetails(${m.id})"><div>
											<p style="color: red;">
												<img src="${RESOURCEDOMAIN}/${m.picture}" height="100px"
													width="100px"></img>
											</p>
											<p>点我进入商品详情哦!</p>
											<div></a>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<p>暂时没有抽奖的商品</p>
							</c:otherwise>
						</c:choose>

					</c:when>
					<c:otherwise>
						<p>暂时没有抽奖活动！</p>
						<p>如果您有抽奖的名额，可直接到个人中心点击幸运抽奖进行抽奖！</p>
					</c:otherwise>
				</c:choose>

			</div>

		</div>
	</div>
	<p class="crossScreen">请竖屏</p>

	<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
	<script>
function pDetails(id){
window.location.href="${DOMAIN}/productDetails?id="+id;
}
</script>
</body>
</html>

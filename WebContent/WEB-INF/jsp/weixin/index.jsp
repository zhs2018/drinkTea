<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>Tea茶商城</title>
<link rel="stylesheet"
	href="${RESOURCEDOMAIN}/css/weixin/css/swiper.min.css">
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/css/tea.css">
<style>
body {
	background: #eee;
}

.swiper-slide img {
	width: 100%;
}

.swiper-pagination-bullet-active {
	background: #ef4136;
}
</style>
</head>
<body>
	<div class="verticalScreen">
		<div class="content">

			<div class="page_display">
				<div class="swiper-container pro-banner">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<a href=""> <%-- <c:if test="${empty mP.picture}">
									<img src="${RESOURCEDOMAIN}/image/weixin/images/bannerimg.png"
										alt="" />
								</c:if> --%> <c:if test="${!empty mP.picture}">
									<img src="${RESOURCEDOMAIN}/${mP.picture}" alt="" />
								</c:if>
							</a>
						</div>
						<div class="swiper-slide">
							<a href="">
								<%--  <c:if test="${empty mP.pictureOne}">
									<img src="${RESOURCEDOMAIN}/image/weixin/images/tea1.png"
										alt="" />
								</c:if> --%> <c:if test="${!empty mP.pictureOne}">
									<img src="${RESOURCEDOMAIN}/${mP.pictureOne}" alt="" />
								</c:if>
							</a>
						</div>
						<div class="swiper-slide">
							<a href=""> <%-- <c:if test="${empty mP.pictureTwo}">
									<img src="${RESOURCEDOMAIN}/image/weixin/images/tea2.png"
										alt="" />
								</c:if> --%> <c:if test="${!empty mP.pictureTwo}">
									<img src="${RESOURCEDOMAIN}/${mP.pictureTwo}" alt="" />
								</c:if>
							</a>
						</div>

					</div>
					<div class="swiper-pagination"></div>
				</div>

				<div class="product_title">
					<h4>${mP.name}${mP.introduce}</h4>
					<c:if test="${mP.tab==2}">
						<p class="price">￥${mP.nowPrice}</p>
					</c:if>
					<c:if test="${mP.tab==3}">
						<p class="price">￥${mP.nowPrice}</p>
					</c:if>
					<c:if test="${mP.tab==1}">
						<p class="price">免费领取</p>
					</c:if>
					<p class="product_ys">已售${mP.saleCount}件</p>
				</div>
				<a href='${RESOURCEDOMAIN}/eve?id=${mP.id}'>
					<div class="product_title margin_top" id="product_Popup">
						<p class="consumption1">
							<span>宝贝评价(${count})</span>
						</p>
						<img class="product_img"
							src="${IMGDOMAIN}/image/weixin/images/go.png">
					</div>
				</a>
			</div>
			<footer class="footer">
				<div class="footer-img">
					<a href="${RESOURCEDOMAIN}/personalCenter"><img
						src="${IMGDOMAIN}/image/weixin/imgp.png" alt=""></a>
				</div>
				<c:if test="${mP.tab==2 || mP.tab==3}">
					<a href='${RESOURCEDOMAIN}/confirmOrder?proId=${mP.id}'
						class="footer-btn">立即购买</a>
				</c:if>
				<c:if test="${mP.tab==1}">
					<a href='${RESOURCEDOMAIN}/freeRecevice?proId=${mP.id}'
						class="footer-btn">立即领取</a>
				</c:if>

			</footer>
		</div>
	</div>
	<p class="crossScreen">请竖屏</p>
	<script src="${RESOURCEDOMAIN}/js/weixin/js/jquery-1.11.2.min.js"></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/js/html_rem.js"></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/js/swiper.min.js"></script>
	<script type="text/javascript">
		var mySwiper = new Swiper('.pro-banner', {
			loop : true,
			speed : 400,
			autoplayDisableOnInteraction : false,
			pagination : '.swiper-pagination'
		})
	</script>
</body>
</html>
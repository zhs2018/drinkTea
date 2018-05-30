<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no,email=no" name="format-detection">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/css/tea.css">
<title>我要下单</title>
<style>
body {
	background:;
}
</style>
</head>
<body>


	<div>
		<ul class="class_nav">
			<li class="style"><a data-toggle="tab" href="javascript:void(0)"
				onclick="relyPrice('1')">默认排序</a></li>
			<li><a data-toggle="tab" href="javascript:void(0)"
				onclick="relyPrice('2')">按销量</a></li>
			<li><a data-toggle="tab" href="javascript:void(0)"
				onclick="relyPrice('3')">按价格</a></li>
		</ul>
	</div>
	<div class="page_img">

		<!-------------------------全部------------------------------>
		<div class="you">
			<c:forEach items="${ManageProduct}" var="mp">
				<div>
					<a href='${RESOURCEDOMAIN}/productDetails?id=${mp.id}'> <img
						src="${RESOURCEDOMAIN}/${mp.picture}" alt="">
						<p class="you_t">${mp.name}${mp.introduce}</p> <c:if
							test="${mp.tab==2}">
							<p class="you_p">￥${mp.nowPrice}</p>
						</c:if> <c:if test="${mp.tab==1}">
							<p class="you_p">免费领取</p>
						</c:if> <c:if test="${mp.tab==3}">
							<p class="you_p">扫码答题专属</p>
						</c:if>
					</a> <input type="text" name="id" value="${mp.id}" hidden="true">
					<input type="text" id="mes" value="${message}" hidden="true">
					<input type="text" id="m" value="${me}" hidden="true">
				</div>
			</c:forEach>
		</div>
	</div>
	<script type='text/javascript'
		src='${RESOURCEDOMAIN}/js/weixin/js/jquery-1.11.2.min.js'
		charset='utf-8'></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/js/html_rem.js"></script>
	<script>
		function relyPrice(ty) {
			window.location.href = "${RESOURCEDOMAIN}/relyPrice?ty=" + ty;
		}
		$(document).ready(function() {
			var cont = "${ManageProduct}";
			if ("[]" == cont) {
				$(".you").css("display", "none");
			} else {
				$(".you").css("display", "block");
			}
			var state = '${ty}';
			$(".class_nav li").removeClass();
			if (parseInt(state)) {
				$(".class_nav li").eq(parseInt(state) - 1).addClass("style");
			} else {
				$(".class_nav li").eq(0).addClass("style");
			}
			$('.class_nav li').click(function() {
				$(this).addClass('style').siblings().removeClass('style');
			});
		})
	</script>
	<script>
		$(function() {
			var mes = $("#mes").val();
			if (mes != null && mes != "") {
				alert("请在" + mes + "之后再来免费领取！");
			}
			var m = $("#m").val();
			if (m != null && m != "") {
				alert(m + "免费领取成功");
			}
		});
	</script>
</body>
</html>
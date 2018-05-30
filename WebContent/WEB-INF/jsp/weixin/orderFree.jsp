<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>订单详情</title>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
<style>
body {
	background: #eeeeee;
}
</style>
</head>
<body>
	<div class="verticalScreen">
		<div class="plays-top">
			免费领取
			<div class="plays-top-cg"></div>
		</div>

		<div class="plays_add" style="padding-bottom: 0.75rem">
			<a href="">
				<p class="plays_name">
					收货人：<label>${Address.userName}</label>
				</p>
				<p class="plays_tel">${Address.telPhone}</p>
				<div class="">
					收货地址：<label>${Address.addressDetails}</label>
				</div>
			</a>
		</div>
		<c:forEach items="${Products}" var="od">
			<div class="cart_sp margin_top">
				<img src="${RESOURCEDOMAIN}/${od.picture}" alt="" />
				<div class="cart_xx">
					<p class="cart_t">${od.name}</p>
					<p class="consumption">
						<label>免费领取</label>
					</p>
					<p class="order-numb">×1</p>
				</div>
			</div>
		</c:forEach>
		<div class="orderd-xq">
			<div>
				<label>商品数量：</label><span>${Order.count}件</span>
			</div>
			<div>
				<label>商品总价：</label><span>￥0</span>
			</div>
		</div>
		<div class="orderd-sfk">
			<label>实付款</label> <span>￥0</span>
		</div>

		<div class="orderd-dd">
			<p>订单编号：${Order.orderNumber}</p>
			<p>
				创建时间：
				<fmt:formatDate value="${Order.orderTime}"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</p>
		</div>
	</div>

	<p class="crossScreen">请竖屏</p>
	<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
	<script src="${RESOURCEDOMAIN}/js/layer1/layer/layer.js"></script>
	<script>
		function cancel_Order(id) {
			$.ajax({
				url : "${RESOURCEDOMAIN}/cancel_Order",
				type : "post",
				data : {
					id : id
				},
				dataType : "json",
				success : function(data) {
					if (data.status == 1) {
						window.location.href = "${RESOURCEDOMAIN}/myOrder";
					} else if (data.status == 0) {
						layer.msg(data.Message);
					}
				},
				error : function(data) {
					layer.msg(data.Message);
				}
			})
		}

		function eval(id) {
			window.location.href = "${RESOURCEDOMAIN}/evaluateOrder/" + id;
		}
	</script>
	<script>
		$(document).ready(function() {

			//取消订单
			$('.deleteOrder').click(function() {
				var orderID = $(this).parents('.my_div_list ').attr('id');
				layer.open({
					content : $('#delete_Popup').html()
				});
				$('.div_btn_ok').click(function() {
					layer.closeAll();
					$('#' + orderID).remove();
				})
			});
		});
	</script>
</body>
</html>
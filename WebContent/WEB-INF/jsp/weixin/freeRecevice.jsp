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
<title>确认订单</title>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
</head>
<body>
	<div class="verticalScreen">
		<form action="frees" method="post" id="form_sb">
			<div class="content">
				<div class="order_add" onclick="myAdd(${mp.id})">
					<%--  <a href="${RESOURCEDOMAIN}/myAddress"> --%>
					<i><img src="${RESOURCEDOMAIN}/image/weixin/position.png"></i>
					<p class="order_name">
						收货人：<label>${obj.userName} <input type="text"
							hidden="true" value="${obj.userName}" name="userName" /> <input
							type="text" hidden="true" value="${obj.id}" name="addressId" />
						</label>
					</p>
					<p class="order_tel">${obj.telPhone}
						<input type="number" hidden="true" value="${obj.telPhone}"
							name="telPhone" />
					</p>
					<div class="">
						收货地址：<label>${obj.addressDetails}</label>
					</div>
					<!--  </a> -->
					<img class="con_add_img con_add_img_go"
						src="${RESOURCEDOMAIN}/image/weixin/go.png" alt="" />
				</div>

				<div class="cart_sp margin_top">
					<img name="picture" src="${RESOURCEDOMAIN}/${mp.picture}" alt="" />
					<div class="cart_xx">
						<p class="cart_t">
							品牌名字<input type="text" hidden="true" value="${mp.name}"
								name="goodName" />${mp.name}<!-- 奢宠精致茶礼盒装乌龙茶 -->
							<input type="text" hidden="true" value="${mp.id}" name="goodId" />
						</p>
						<p class="consumption">
							<label id="nowPrice">免费领取</label>
						</p>
						<p class="order-numb">×1</p>
					</div>
				</div>
				<div class="personal_div_h" id="ps_Popup"
					style="border-top: 1px solid #ececec">
					<p class="personal_t" style="margin-left: 0;">配送方式</p>
					<p class="personal_t_p personal_t_p_fs">快递</p>
				</div>


				<!--提交订单-->
				<div class="cart_settlement_div">

					<button class="cart_settlement_btn" type="submit">确认领取</button>
					<div class="cart_settlement_total"
						style="text-align: left; color: red;">请点击确认领取按钮！</div>
				</div>
			</div>
	</div>

	</form>

	</div>
	<p class="crossScreen">请竖屏</p>
	<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
	<script src="${RESOURCEDOMAIN}/js/layer1/layer/layer.js"></script>
	<script src=" https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script>
function myAdd(id){
	window.location.href="${RESOURCEDOMAIN}/myAddress?mpID="+id;
}
</script>

</body>
</html>
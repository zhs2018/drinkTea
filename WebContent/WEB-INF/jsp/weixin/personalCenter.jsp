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
<title>个人中心</title>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
<style>
body {
	background: #eeeeee;
}
</style>
</head>
<body>
	<div class="verticalScreen">
		<div class="personal_top">
			<div class="personal_set">
				<div class="personal_set_icon">

					<c:if test="${rs==1}">
						<a href="${RESOURCEDOMAIN}/perCenter"><img
							src="${RESOURCEDOMAIN}/image/message.gif" alt="" /><span
							style="color: white;">消息</span></a>
					</c:if>
					<c:if test="${rs==2}">
						<a href="${RESOURCEDOMAIN}/perCenter"><img
							src="${RESOURCEDOMAIN}/image/message2.png" alt="" /><span
							style="color: white;">消息</span></a>
					</c:if>
				</div>
			</div>
			<div class="personal_user">
				<a href="${RESOURCEDOMAIN}/changeUser">
					<div class="personal_head">
						<%-- ${user.image} --%>
						<img src="${user.image}" onClick="javascript:0;" width="100px"
							alt="" />
					</div>
					<p class="personal_name">${user.userName}</p>
				</a>
			</div>
		</div>

		<div class="personal_div">
			<!--我的订单-->
			<a href="${RESOURCEDOMAIN}/myOrder">
				<div class="personal_div_h">
					<p class="personal_t">我的订单</p>
					<label class="personal_t_l"><img
						src="${RESOURCEDOMAIN}/image/weixin//go.png"></label>
					<p class="personal_t_p">查看全部订单</p>
				</div>
			</a>

			<div class="order_list">
				<div>

					<a
						<%-- href="${RESOURCEDOMAIN}/orderStatus" --%> onclick="gotos('2')">
						<img src="${RESOURCEDOMAIN}/image/weixin//dfk.png" alt="" />
						<p>待付款</p> <label>${length1}</label>
					</a>
				</div>

				<a
					<%-- href="${RESOURCEDOMAIN}/orderStatus" --%> onclick="gotos('3')">
					<div>
						<img src="${RESOURCEDOMAIN}/image/weixin/dfh.png" alt="" />
						<p>待发货</p>
						<label>${length2}</label>
					</div>
				</a> <a
					<%-- href="${RESOURCEDOMAIN}/orderStatus" --%> onclick="gotos('4')">
					<div>
						<img src="${RESOURCEDOMAIN}/image/weixin/dsh.png" alt="" />
						<p>待收货</p>
						<label>${length3}</label>
					</div>
				</a> <a
					<%-- href="${RESOURCEDOMAIN}/orderStatus" --%> onclick="gotos('5')">
					<div>
						<img src="${RESOURCEDOMAIN}/image/weixin/dpj.png" alt="" />
						<p>待评价</p>
						<label>${length4}</label>
					</div>
				</a> <a href="${RESOURCEDOMAIN}/refunds">
					<div>
						<img src="${RESOURCEDOMAIN}/image/weixin/bjj.png" alt="" />
						<p>售后</p>
						<label>${length0}</label>
					</div>
			</div>
		</div>
		</a>
		<ul class="personal-list">
			<a href="${RESOURCEDOMAIN}/myRedEnvelope">
				<li><img src="${RESOURCEDOMAIN}/image/weixin/hb.png" alt="">
					<div class="personal-list-div">
						<h3>我的红包</h3>
						<p>查看累积的红包记录</p>
					</div></li>
			</a>

			<a href="${RESOURCEDOMAIN}/myAddress">
				<li><img src="${RESOURCEDOMAIN}/image/weixin/wddz.png" alt="">
					<div class="personal-list-div">
						<h3>我的地址</h3>
						<p>收货地址及时变更</p>
					</div></li>
			</a>

			<a href="${RESOURCEDOMAIN}/luckdraw/myLuckdraw">
				<li><img src="${RESOURCEDOMAIN}/image/luck.png" alt="">
					<div class="personal-list-div">
						<h3>我的奖品</h3>
						<p>查看我获得的奖品</p>
					</div></li>
			</a>
			<a href="${RESOURCEDOMAIN}/luckdraw/drawinfo">
				<li><img src="${RESOURCEDOMAIN}/image/draw.png" alt="">
					<div class="personal-list-div">
						<h3>幸运抽奖</h3>
						<p>欢迎进入幸运大抽奖</p>
					</div></li>
			</a>

			<a href="${RESOURCEDOMAIN}/myFriend">
				<li><img src="${RESOURCEDOMAIN}/image/weixin/gywm.png" alt="">
					<div class="personal-list-div">
						<h3>我的好友</h3>
						<p>查看好友请点击这里</p>
					</div></li>
			</a>
			<a href="${RESOURCEDOMAIN}/myfeedback">
				<li><img src="${RESOURCEDOMAIN}/image/weixin/yjfk.png" alt="">
					<div class="personal-list-div">
						<h3>意见反馈</h3>
						<p>您的建议是努力的动力</p>
					</div></li>
			</a>

		</ul>
		<img class="personal-banner"
			src="${RESOURCEDOMAIN}/image/weixin/img4p.png" alt="">
	</div>
	<form id="jumpto" action="${RESOURCEDOMAIN}/orderStatus" method="post">
		<input id="ty" type="hidden" name="ty" value="">
	</form>
	<p class="crossScreen">请竖屏</p>
	<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</body>
</html>

<script>
	function gotos(ty) {
		$("#ty").val(ty);
		$("#jumpto").submit();
	}
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>支付成功</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
</head>
<body>
<div class="verticalScreen">
    <div class="plays-top">
        买家已付款
        <div class="plays-top-img"></div>
    </div>

    <div class="plays_add">
        <a href="">
            <p class="plays_name">收货人：<label>${address.userName }</label></p>
            <p class="plays_tel">${address.telPhone}</p>
            <div class="">收货地址：<label>${address.addressDetails}</label></div>
        </a>
    </div>

    <div class="plays_operation">

        <p><label>共${gOrder.goodsCount}件商品</label><span>合计：<label>¥${order.price}</label></span></p>

        <a href="javascript:void(0)" onclick="seeOrders(${order.id},${order.orderState})"><div class="plays-productionOrder">查看订单</div></a>
        <a href="${RESOURCEDOMAIN}/manageCity"><div class="plays-return">返回首页</div></a>
    </div>
    <div class="plays-remind">
        <p>安全提醒</p>
        <p class="plays-t">付款成功后，我们不会以付款异常、卡单、系统升级等原因联系您。 请勿泄露您的银行卡号、手机验证码、否则会造成钱财损失。</p>
    </div>
</div>
	<form id="jumpDetail" action="${RESOURCEDOMAIN}/seeOrders" method="post">
	<input id="deId" type="hidden" name="id" value="">
	<input id="deSta" type="hidden" name="orderStatus" value="">
</form>
<p class="crossScreen">请竖屏</p>
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
<script>
function seeOrders(id,orderStatus){
	$("#deId").val(id);
	$("#deSta").val(orderStatus);
	$("#jumpDetail").submit();
}
</script>
</body>
</html>
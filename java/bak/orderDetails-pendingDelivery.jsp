<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
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
               等待卖家发货
        <div class="plays-top-fh"></div>
    </div>

    <div class="plays_add" style="padding-bottom: 0.75rem">
        <a href="">
            <p class="plays_name">收货人：<label>${Address.userName}</label></p>
            <p class="plays_tel">${Address.telPhone}</p>
            <div class="">收货地址：<label>${Address.addressDetails}</label></div>
        </a>
    </div>
    <c:forEach items="${Products}" var="od">
    <div class="cart_sp margin_top">
        <img src="${RESOURCEDOMAIN}/${od.picture}" alt=""/>
        <div class="cart_xx">
            <p class="cart_t">${od.name}</p>
            <p class="consumption"><label>¥${od.nowPrice}</label></p>
            <p class="order-numb">×1</p>
        </div>
    </div>
    </c:forEach>
    <div class="orderd-xq">
        <div><label>商品数量：</label><span>${Order.count}件</span></div>
        <div><label>商品总价：</label><span>￥${Order.price}</span></div>
    </div>
    <div class="orderd-sfk">
        <label>实付款</label>
        <span>￥${Order.price}</span>
    </div>

    <div class="orderd-dd">
        <p>订单编号：${Order.orderNumber}</p>
        <p>创建时间：<fmt:formatDate value="${Order.orderTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
    </div>

    <div class="orderd-footer">
        <div><a href="javascript:void(0)" onclick ="refund(${Order.id})">申请退款</div>
    </div>
</div>


<p class="crossScreen">请竖屏</p>
<script src="js/jquery-1.11.2.min.js"></script>
<script src=" ${RESOURCEDOMAIN}/js/layer1/js/html_rem.js"></script>
<script>
function refund(id){
	window.location.href =
		"${RESOURCEDOMAIN}/shop/refund_Money/"+ id ;
}
</script>

</body>
</html>
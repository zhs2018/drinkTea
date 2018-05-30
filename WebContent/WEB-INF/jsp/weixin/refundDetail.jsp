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
    <title>退款详情</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
    <%-- <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/css.css"> --%>
</head>
<body>
<div class="verticalScreen">
    <div class="plays-top">
        退款申请已提交，等待卖家处理
    </div>
 <c:forEach items="${Products}" var="od">
   <div class="cart_sp margin_top">
        <img src="${RESOURCEDOMAIN}${od.picture}" alt=""/>
        <div class="cart_xx">
            <p class="cart_t">${od.name}</p>
            <p class="consumption"><label>¥${od.nowPrice}</label></p>
            <p class="order-numb">×1</p>
        </div>
    </div>
    <div class="orderd-xq" style="border-bottom: 0.5rem solid #eee">
        <div><label>退款金额：</label><span>￥${refund.refundMoney}</span></div>
        <div><label>申请时间：</label><span><fmt:formatDate value="${refund.refTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></div>
        <div><label>退款编号：</label><span>${Order.orderNumber}</span></div>
        <div><label>退款状态：</label><span>
        	<c:choose>
		       <c:when test="${Order.orderState ==60}">退款处理中</c:when>
		       <c:when test="${Order.orderState ==70}">退款已通过</c:when>
		       <c:when test="${Order.orderState ==80}">退款未通过</c:when>
        	</c:choose>
        </span></div>
    </div>

</c:forEach>
</div>
<p class="crossScreen">请竖屏</p>
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</body>
</html>
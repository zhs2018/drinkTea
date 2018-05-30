<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>退款</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
    <style>
        body {
            background: #eee;
        }
    </style>
</head>
<body>
<div class="verticalScreen">
<form action="${RESOURCEDOMAIN}/refund_reason" method ="post">
    <div class="refund-reason">
        <label>退款原因：</label>
        <textarea class="" name="refundReason"></textarea>
        <input type="text" placeholder="" name = "orderId" value="${OrderId}" hidden="true"/>
    </div>
    <div class="refund-m">
        <label>退款金额：</label>
        <input type="text" placeholder="" name="refundMoney"/>
    </div>

    <div class="refund-m">
        <label>备注：</label>
        <input type="text" placeholder="" name="remarkMesssage"/>
    </div>
    <div class="refund-btn"><button class="refund-btn" type="submit">提交申请</button></div>
    </form>
</div>
<p class="crossScreen">请竖屏</p>
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</body>
</html>
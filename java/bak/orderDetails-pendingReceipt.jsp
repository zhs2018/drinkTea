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
        等待买家收货
        <div class="plays-top-sh"></div>
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
        <p>创建时间<fmt:formatDate value="${Order.orderTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
    </div>

    <div class="orderd-footer">
        <div class="confirmReceipt">确认收货</div>
    </div>
</div>

<!--确认订单弹窗-->
<div id="confirmReceipt_Popup" style="display: none">
    <div style="background: #fff;padding-top: 20px;border-radius: 6px;">
        <p class="auth_close_p" style="color: #fe3c00;">请确认收到货物后在收货!</p>
        <div class="div_btn_div">
            <div class="div_btn_cancel" onclick="layer.closeAll()">取消</div>
            <div class="div_btn_ok">确认</div>
        </div>
    </div>
</div>

<p class="crossScreen">请竖屏</p>
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
<script src="layer/layer.js"></script>
<script>
    $(document).ready(function () {
        //确认收货
        $('.confirmReceipt').click(function () {
        	var id = '${Order.id}';
            layer.open({
                content: $('#confirmReceipt_Popup').html()
            });
            $(".div_btn_ok").click(function(){
            	$.ajax({
        			url:"${RESOURCEDOMAIN}/shop/confirmReceipt",
        			type: "post",
        			dataType:"json",
                    data:{
                    	'id':id
                    },
        			success:function(data){
        				if(data.result=="0"){
        					 window.location.href="${RESOURCEDOMAIN}/shop/evaluateOrder/"+id;
        				}else{
        					layer.msg(data.message);
        				}
        			},
        			error:function(data){
        				layer.msg("服务器响应失败！");
        			}
                });
            });
        });

    });

</script>
</body>
</html>
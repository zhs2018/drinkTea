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
			等待买家付款
			<div class="plays-top-dfk"></div>
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
					<p class="cart_t">品牌名字${od.name}</p>
					<p class="consumption">
						<label>¥${od.nowPrice}</label>
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
				<label>商品总价：</label><span>￥${Order.price}</span>
			</div>
			<div>
				<label>红包抵扣：</label><span>￥${Order.redMoney}</span>
			</div>
		</div>
		<div class="orderd-sfk">
			<label>实付款</label> <span>￥${Order.price}</span>
		</div>

		<div class="orderd-dd">
			<p>订单编号：${Order.orderNumber}</p>
			<p>
				创建时间：
				<fmt:formatDate value="${Order.orderTime}"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</p>
		</div>

		<div class="orderd-footer">
			<div onclick = "apply(${Order.id})">立即付款</div>
			<div class="cancelOrder">取消订单</div>
		</div>
	</div>

	<!--取消订单弹窗-->
	<div id="cancel_Popup" style="display: none">
		<div style="background: #fff; padding-top: 20px; border-radius: 6px;">
			<p class="auth_close_p">确定要取消订单吗？</p>
			<div class="div_btn_div">
				<div class="div_btn_cancel" onclick="layer.closeAll()">取消</div>
				<div class="div_btn_ok" onclick="cancel_Order(${Order.id})">确认</div>
			</div>
		</div>
	</div>
	<div id="confirm_Popup1" style="display: none">
        <div class="personal_Popup">
            <p class="confirm_Popup_p">支付详情
                <img src="${RESOURCEDOMAIN}/image/weixin/close.png" alt="">
            </p>

            <!--支付详情-->
            <div class="confirm-play-xq">
                <!--支付方式-->
                <div class="confirm_Popup_div">
                    <label class="confirm-t">支付方式：</label>
                    <img class="confirm-go" src="${RESOURCEDOMAIN}/image/weixin/go1.png" alt="">
                    <lable  class="confirm-vx">微信支付</lable>
                </div>

                <div class="confirm_play">
                    <label>需要付款</label>
                    <span id="xyfk">￥</span>
                </div>
            </div>

            <!--支付成功-->
            <div class="confirm-success">
                <div class="confirm_play-success"></div>
                <p>支付成功</p>
            </div>


            <!--支付失败-->
            <div class="confirm-fail">
                <div class="confirm_play-fail"></div>
                <p>支付失败，请返回重试</p>
            </div>
            <div class="personal_Popup_btn">确认付款</div>
        </div>
    </div>
	<p class="crossScreen">请竖屏</p>
	<script src=" ${RESOURCEDOMAIN}/js/layer1/js/jquery-1.11.2.min.js"></script>
	<script src=" ${RESOURCEDOMAIN}/js/layer1/js/html_rem.js"></script>
	<script src="${RESOURCEDOMAIN}/js/layer1/layer/layer.js"></script>
	<script>
function cancel_Order(id){
	  $.ajax({
		url:"${RESOURCEDOMAIN}/cancel_Order",
		type:"post",
		data:{
			"id":id
		},
		dataType:"json",
		success:function(data){
			if(data.status=="1"){
               window.location.href="${RESOURCEDOMAIN}/index";
		   }else if(data.status=="0"){

		}
		},
		error:function(data){

		}
	})
}
function apply(id){
	 var ID = id;
	$.ajax({
		url:"${RESOURCEDOMAIN}/save",
		type:"post",
		data:{
			"id":ID
		},
		dataType:"json",
		success:function(data){
			if(data.result == 0){
				$("#xyfk").html("￥"+data.money);
		        layer.open({
		            content: $('#confirm_Popup1').html(),
		            type: 1
		            ,
		            anim: 'up'
		            ,
		            style: 'position:fixed; bottom:0; left:0; width: 100%; height: 23.4375rem; padding:10px 0; border:none;'
		        });
				WeiXinPay(data);
				/* setTimeout(function(){
					window.location.href = "${RESOURCEDOMAIN}/paymentSuccess?id="+data.id;
				}, 1000); */
			 }else if(data.result == 1){
				alert(data.message);
			}
		},
		error:function(data){
			alert("服务器响应失败！");
		}
	})
}
function WeiXinPay(data){
	var payInfo = JSON.parse(data.payInfo);
        $('.confirm_Popup_p img').click(function () {
            layer.closeAll();
        });
        //点击确认付款
        $('.personal_Popup_btn').click(function () {
            //隐藏支付详情
            $('.confirm-play-xq').hide();
            function onBridgeReady(){
 			   WeixinJSBridge.invoke(
 			       'getBrandWCPayRequest',
					payInfo,
 			        function(res){
 			    	// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
 			           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
 			        	 //这是点击完成之后跳转的路径
 				           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
 				        	  $('.confirm-success').show();
 				        	 window.location.href = "${RESOURCEDOMAIN}/paymentSuccess?id="+data.id;
 				           }else{
 				        	  $('.confirm-fail').show();
 				           }
 			           }
 			       }
 			   );
 			}
		 	if (typeof WeixinJSBridge == "undefined"){
	 		   if( document.addEventListener ){
	 		       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	 		   }else if (document.attachEvent){
	 		       document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
	 		       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	 		   }
		 	}else{
		 		   onBridgeReady();
		 	}
            //成功时
            //$('.confirm-success').show();
            //zzjg();
            //失败时
            //$('.confirm-fail').show();
        })
}


</script>
	<script>
    $(document).ready(function () {
        //取消订单
        $('.cancelOrder').click(function () {
            var orderID = $(this).parents('.my_div_list ').attr('id');
            layer.open({
                content: $('#cancel_Popup').html()
            });

        });

        $(".pay_now").click(function(){

        });
    });

</script>
</body>
</html>
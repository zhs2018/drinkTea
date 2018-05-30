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
<title>我的订单</title>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
<style>
body {
	background: #eeeeee;
}
</style>
</head>
<body>
	<div class="verticalScreen">
		<ul class="my_oreder_list">
			<li class="style"><a data-toggle="tab" href="javascript:void(0)"
				onclick="gotos('1')"> 所有订单</a></li>
			<li class="style"><a data-toggle="tab" href="javascript:void(0)"
				onclick="gotos('2')">待付款</a></li>
			<li class="style"><a data-toggle="tab" href="javascript:void(0)"
				onclick="gotos('3')">待发货</a></li>
			<li class="style"><a data-toggle="tab" href="javascript:void(0)"
				onclick="gotos('4')">待收货</a></li>
			<li class="style"><a data-toggle="tab" href="javascript:void(0)"
				onclick="gotos('5')">待评价</a></li>
		</ul>
		<div>
			<!-----------------------------全部----------------------->
			<div class="my_order" style="display: block;">
				<!--待付款-->

				<c:forEach items="${OrderList}" var="order" varStatus="vs">
					<div class="my_div_list margin_bottom" id="id_${vs.index+1 }">
						<div class="my_t">
							<p class="my_t_left">
								<fmt:formatDate value="${order.orderTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
								<!-- 2016-09-30 09:45 -->
							</p>
							<p class="my_t_right">
								<c:choose>
									<c:when test="${order.orderState ==10}">订单已取消</c:when>
									<c:when test="${order.orderState ==20}">等待付款</c:when>
									<c:when test="${order.orderState ==30}">已付款</c:when>
									<c:when test="${order.orderState ==40}">已发货</c:when>
									<c:when test="${order.orderState ==50}">交易成功</c:when>
									<c:when test="${order.orderState ==60}">退款处理中</c:when>
									<c:when test="${order.orderState ==70}">退款已通过</c:when>
									<c:when test="${order.orderState ==80}">退款未通过</c:when>
									<c:when test="${order.orderState ==90}">订单已评价</c:when>

								</c:choose>
							</p>
						</div>
						<c:forEach items="${order.products}" var="od" varStatus="vs">
							<div class="cart_sp ">
								<img src="${RESOURCEDOMAIN}/${od.picture}" alt="" />
								<div class="cart_xx">
									<p class="cart_t">${od.name}</p>
									<div class="cart_cz1">
										<c:if test="${od.tab==2}">
											<p class="consumption">
												<label>￥${od.nowPrice}</label>
											</p>
										</c:if>
										<c:if test="${od.tab==1}">
											<p class="consumption">
												<label>免费领取</label>
											</p>
										</c:if>
										<p class="number">
											<label>×1</label>
										</p>
									</div>
								</div>
							</div>
						</c:forEach>
						<div class="my-tj">
							共${order.count}件商品 小计：<label> <c:if
									test="${empty order.price}">¥0</c:if> <c:if
									test="${!empty order.price}">¥${order.price}</c:if></label>
						</div>
						<div class="my_btn">
							<c:choose>
								<c:when test="${order.orderState ==20}">
									<div class=" payment_btn" onclick="apply(${order.id})">立即支付</div>
								</c:when>
								<c:when test="${order.orderState ==30}">
									<div class=" payment_btn" onclick="refund(${order.id})">申请退款</div>
								</c:when>
								<c:when test="${order.orderState ==40}">
									<div class=" payment_btn confirmReceipt" id="${order.id}">确认收货</div>
								</c:when>
								<c:when test="${order.orderState ==50}">
									<div class=" payment_btn" onclick="eval(${order.id})">立即评价</div>
								</c:when>
							</c:choose>
							<c:if test="${order.orderState ==20}">
								<div class="cancelOrder" id="${order.id}">取消订单</div>
							</c:if>
							<a href="javascript:void(0)"
								onclick="seeOrders(${order.id},${order.orderState})">
								<div class="payment_btn">查看详情</div>
							</a>
						</div>
					</div>
				</c:forEach>
			</div>
			<!--暂无订单-->
			<div class="w-noOrder">
				<img src="${RESOURCEDOMAIN}/image/weixin/hasnoOrder.png" alt="">
				<p class="w-prompt">您还没有相关订单</p>
				<p class="w-guide">可以去看看有哪些想买的</p>
				<button class="w-randomBtn" style="color: #555">随便逛逛</button>
			</div>

		</div>
	</div>

	<!--取消订单弹窗-->
	<div id="cancel_Popup" style="display: none">
		<div style="background: #fff; padding-top: 20px; border-radius: 6px;">
			<p class="auth_close_p">确定要取消订单吗？</p>
			<div class="div_btn_div">
				<div class="div_btn_cancel" onclick="layer.closeAll()">取消</div>
				<div class="div_btn_ok">确认</div>
			</div>
		</div>
	</div>

	<!--确认订单弹窗-->
	<div id="confirmReceipt_Popup" style="display: none">
		<div style="background: #fff; padding-top: 20px; border-radius: 6px;">
			<p class="auth_close_p" style="color: #fe3c00;">请确认收到货物后在收货!</p>
			<div class="div_btn_div">
				<div class="div_btn_cancel" onclick="layer.closeAll()">取消</div>
				<div class="div_btn_ok">确认</div>
			</div>
		</div>
	</div>

	<!--删除订单弹窗-->
	<div id="delete_Popup" style="display: none">
		<div style="background: #fff; padding-top: 20px; border-radius: 6px;">
			<p class="auth_close_p">确定要删除该订单吗？</p>
			<div class="div_btn_div">
				<div class="div_btn_cancel" onclick="layer.closeAll()">取消</div>
				<div class="div_btn_ok">确认</div>
			</div>
		</div>
	</div>
	<div id="confirm_Popup1" style="display: none">
		<div class="personal_Popup">
			<p class="confirm_Popup_p">
				支付详情 <img src="${RESOURCEDOMAIN}/image/weixin/close.png" alt="">
			</p>

			<!--支付详情-->
			<div class="confirm-play-xq">
				<!--支付方式-->
				<div class="confirm_Popup_div">
					<label class="confirm-t">支付方式：</label> <img class="confirm-go"
						src="${RESOURCEDOMAIN}/image/weixin/go1.png" alt="">
					<lable class="confirm-vx">微信支付</lable>
				</div>

				<div class="confirm_play">
					<label>需要付款</label> <span id="xyfk">￥</span>
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
	<form id="jumpto" action="${RESOURCEDOMAIN}/orderStatus" method="post">
		<input id="ty" type="hidden" name="ty" value="">
	</form>
	<form id="jumpDetail" action="${RESOURCEDOMAIN}/seeOrders"
		method="post">
		<input id="deId" type="hidden" name="id" value=""> <input
			id="deSta" type="hidden" name="orderStatus" value="">
	</form>
	<p class="crossScreen">请竖屏</p>
	<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
	<script src="${RESOURCEDOMAIN}/js/layer1/layer/layer.js"></script>
	<script src=" https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script>
<%-- 立即支付
    --%>
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

 function gotos(ty){
	   $("#ty").val(ty);
	   $("#jumpto").submit();
 }

function seeOrders(id,orderStatus){
	$("#deId").val(id);
	$("#deSta").val(orderStatus);
	$("#jumpDetail").submit();
}

function cancel_Order(id){
		 $.ajax({
			url:"${RESOURCEDOMAIN}/cancel_Order",
			type:"post",
			data:{
				id:id
			},
			dataType:"json",
			success:function(data){
				if(data.status==1){
	                window.location.href="${RESOURCEDOMAIN}/myOrder";
			   }else if(data.status==0){
			 		layer.msg(data.Message);
				}
			},
			error:function(data){
				layer.msg(data.Message);
			}
		})
}
<%--
	申请退款
--%>
function refund(id){
	window.location.href =
		"${RESOURCEDOMAIN}/refund_Money/"+ id ;
}
<%--
	立即评价
--%>
function eval(id){
	window.location.href =
		"${RESOURCEDOMAIN}/evaluateOrder/"+ id ;
}

function confirmReceipt(id){
	layer.closeAll();
		$.ajax({
			url:"${RESOURCEDOMAIN}/confirmReceipt",
			type: "post",
			dataType:"json",
            data:{
            	'id':id
            },
			success:function(data){
				if(data.result=="0"){
					 window.location.href="${RESOURCEDOMAIN}/evaluateOrder/"+id;
				}else{
					layer.msg(data.message);
				}
			},
			error:function(data){
				layer.msg("服务器响应失败！");
			}
        });
}
    $(document).ready(function () {
    	var cont = "${OrderList}";
    	if("[]" == cont){
    		$(".my_order").css("display","none");
    		$(".w-noOrder").css("display","block");
    	}else{
    		$(".my_order").css("display","block");
    		$(".w-noOrder").css("display","none");
    	}
    	var state = '${ty}';
        $(".my_oreder_list li").removeClass();
        if(parseInt(state)){
        	$(".my_oreder_list li").eq(parseInt(state)-1).addClass("style");
        }else{
        	$(".my_oreder_list li").eq(0).addClass("style");
        }

        $('.my_oreder_list li').click(function () {
            $(this).addClass('style').siblings().removeClass('style');
        });

        //取消订单
        $('.cancelOrder').click(function () {
            var orderID = $(this).parents('.my_div_list ').attr('id');
            var order = $(this).attr('id');
            layer.open({
                content: $('#cancel_Popup').html()
            });
            $('.div_btn_ok').click(function () {
            	cancel_Order(order);
                layer.closeAll();
                $('#' + orderID).remove();
            })
        });

        //确认收货
        $('.confirmReceipt').click(function () {
        	var order = $(this).attr('id');
            layer.open({
                content: $('#confirmReceipt_Popup').html()
            });
            $('.div_btn_ok').click(function () {
            	confirmReceipt(order);
            })
        });
        $(".w-randomBtn").click(function(){
        	window.location.href="${RESOURCEDOMAIN}/index";
        });
    });

</script>
</body>
</html>
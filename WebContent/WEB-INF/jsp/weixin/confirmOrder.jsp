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
		<form action="save" method="post" id="form_sb">
			<div class="content">
				<div class="order_add" onclick="myAdd(${mp.id})">
					<a href="${RESOURCEDOMAIN}/myAddress"> <i><img
							src="${RESOURCEDOMAIN}/image/weixin/position.png"></i>
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
					</a> <img class="con_add_img con_add_img_go"
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
							<label id="nowPrice">${mp.nowPrice}</label>
						</p>
						<p class="order-numb">×1</p>
					</div>
				</div>

				<div class="con_add margin_top">
					<!-- </i> -->
					<p class="consumption1">购买数量</p>
					<div class="product_js" style="margin-top: 0.87125rem">
						<div class="js_jian">-</div>
						<input class="js_input" style="background: #fff" type="number"
							name="count" id="count" value="1" onChange="change()" />
						<div class="js_jia">+</div>
					</div>
				</div>

				<div class="personal_div_h" id="ps_Popup"
					style="border-top: 1px solid #ececec">
					<p class="personal_t" style="margin-left: 0;">配送方式</p>
					<p class="personal_t_p personal_t_p_fs">快递</p>
				</div>

				<div class="order-Red">
					<div class="order-Red-input">
						<label>红包抵现</label> <input type="number" placeholder="输入红包金额"
							id="redMoney" name="redMoney" value="" onChange="result()">
						<input type="text" hidden="true" value="${money}" name="money"
							id="money" />
					</div>
					<p class="order-Red-k">
						可使用红包：<label>¥ <c:if test="${money == null}">0.00</c:if> <c:if
								test="${money != null}">
								<fmt:formatNumber pattern="0.00" maxFractionDigits="2"
									value="${money}" />
							</c:if>
						</label>
					</p>
				</div>

				<div class="personal_div_h">
					<p class="personal_t" style="margin-left: 0;">备注信息</p>
					<textarea style="width: 16.875rem;" class="personal_div_h_txt"
						placeholder="选填：对本次交易的说明" name="customerMessage"></textarea>
				</div>

				<div class="personal_div_h border0">
					<div class="cart_settlement_total" style="color: #333">
						共<span id="hejicount">1</span>件商品 合计:<label>￥</label><label
							id="heji">${mp.nowPrice}</label>
					</div>
				</div>

				<!--提交订单-->
				<div class="cart_settlement_div">
					<div class="cart_settlement_btn" onclick="apply(${obj.id})">提交订单</div>
					<div class="cart_settlement_total" style="margin-left: 0.625rem">
						实付:<label id="shifu">￥${mp.nowPrice}</label> <input type="text"
							hidden="true" value="${mp.nowPrice}" name="price" id="price" />
					</div>
				</div>
			</div>
			<!--支付式弹窗-->
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
		</form>

	</div>
	<p class="crossScreen">请竖屏</p>
	<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
	<script src="${RESOURCEDOMAIN}/js/layer1/layer/layer.js"></script>
	<script src=" https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script>
var pri = "${mp.nowPrice}";

function apply(id){
	$("#price").val(parseFloat($("#shifu").html().split("￥")[1].trim()));
	var sub = $("#form_sb").serialize();
	$.ajax({
		url:"${RESOURCEDOMAIN}/save",
		type:"post",
		data:sub,
		dataType:"json",
		success:function(data){
			if(data.result == 0){
			 alert(123);
				 window.location.href = "${RESOURCEDOMAIN}/demo/paySuccessful?id="+data.id;
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

            function onBridgeReady(){
 			   WeixinJSBridge.invoke(
 			       'getBrandWCPayRequest',
					payInfo,
 			        function(res){
 			    	// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
 			           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
 			        	 //这是点击完成之后跳转的路径
 				           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
 				        	  $('.confirm-play-xq').hide();
 				        	  $('.confirm-success').show();
 				        	 window.location.href = "${RESOURCEDOMAIN}/paymentSuccess?id="+data.id;
 				           }else{
 				        	  $('.confirm-play-xq').hide();
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
function result(){
    var me = parseFloat($("#redMoney").val().trim());
    var money =parseFloat($("#money").val().trim());
    var sf = parseFloat($("#shifu").html().split("￥")[1].trim());
    if(isNaN(money)){
        $("#redMoney").val(0.00);
    }else{
        if(me>0){
        	if(me > money){
        		alert("亲，请输入正确的红包金额！");
        		if(me > sf){
                    $("#redMoney").val((sf-0.01).toFixed(2));
                }else{
                    $("#redMoney").val(money.toFixed(2));
                }
            }else{
                if(me >= sf){
                    alert("亲，请至少付款0.01元！");
                    $("#redMoney").val((sf-0.01).toFixed(2));
                }else{
                    $("#redMoney").val(me.toFixed(2));
                }
            }
        }else{
        	$("#redMoney").val("0.00");
        }
    }
    zzjg();
}

function myAdd(id){
	window.location.href="${RESOURCEDOMAIN}/myAddress?mpID="+id;
}

function change(){
	var count = $("#count").val().trim();
	if(parseInt(count) <= 0){
		$("#count").val(1);
		count = 1;
	}else{
		$("#count").val(count);
	}
	var nowPrice= parseFloat(pri)*parseInt(count);
	$("#heji").html(nowPrice.toFixed(2));
	$("#hejicount").html(count);
	zzjg();
}

$(function(){
	$(".js_jia").click(function(){
		var count = $("#count").val();
		$("#count").val((parseInt(count)+1));
		count = $("#count").val().trim();
		var nowPrice= parseFloat(pri)*parseInt(count);
		$("#heji").html(nowPrice.toFixed(2));
		$("#hejicount").html(count);
		zzjg();
	});
	$(".js_jian").click(function(){
		var count = $("#count").val();
		if(parseInt(count) <= 1){
			$("#count").val(1);
		}else{
			$("#count").val((parseInt(count)-1));
		}
		count = $("#count").val();
		var nowPrice= parseFloat(pri)*parseInt(count);
		$("#heji").html(nowPrice.toFixed(2));
		$("#hejicount").html(count);
		zzjg();
	});
});
</script>
	<script>


	//提交订单
    <%-- $('.cart_settlement_btn').click(function {
    	 $("#xyfk").html($("#shifu").html());
        layer.open({
            content: $('#confirm_Popup1').html(),
            type: 1
            ,
            anim: 'up'
            ,
            style: 'position:fixed; bottom:0; left:0; width: 100%; height: 23.4375rem; padding:10px 0; border:none;'
        });
        $('.confirm_Popup_p img').click(function () {
            layer.closeAll();
        });

        //点击确认付款
        $('.personal_Popup_btn').click(function () {
            //隐藏支付详情
            $('.confirm-play-xq').hide();
            //成功时
            $('.confirm-success').show();
            zzjg();
            //失败时
            //$('.confirm-fail').show();
        })
    }); --%>
    function zzjg(){
    	var mon = parseFloat($("#heji").html().trim()).toFixed(2);
    	var red;
    	if($("#redMoney").val().trim() == ""){
    		red = 0;
    	}else{
    		red = parseFloat($("#redMoney").val().trim()).toFixed(2);
    	}
    	if((mon-red) == 0){
    		$("#xyfk").html("￥0.01");
    		$("#shifu").html("￥0.01");
    	}else{
    		$("#xyfk").html("￥"+(mon-red).toFixed(2));
    		$("#shifu").html("￥"+(mon-red).toFixed(2));
    	}
    }
</script>
</body>
</html>
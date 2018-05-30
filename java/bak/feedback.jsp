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
    <title>意见反馈</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
    <style>
        body {
            background: #eee;
        }
    </style>
</head>

<body>
<div class="verticalScreen">
<form  action="${RESOURCEDOMAIN}/shop/myfeed" novalidate="novalidate" method="post" id="formId">
    <p class="feedback-p">反馈问题详述  </p>
    <textarea class="feedback-textarea" placeholder="写下您对该产品的评价或购物体会~" id ="contents" name="contents"></textarea>
    <div class="refund-m">
        <label>手机号：</label>
        <input type="text" placeholder="请输入您的手机号，方便我们更快向您反馈" id="phone" name="phone"/>
    </div>

         <div class="refund-btn" onclick="saveContent()"> 提交</div>
      <!--   <div class="refund-btn" ><button type="submit"> 提交</button></div> -->
        </form>
</div>
<p class="crossScreen">请竖屏</p>
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</body>
<script>
function saveContent(){
	var content = $("#contents").val();
	var phone=$("#phone").val();
 	//var sub = $("#formId").serialize();
	$.ajax({
		url:"${RESOURCEDOMAIN}/shop/myfeed",
		type:"post",
		data:{
			"content":content,
			"phone":phone
		},
		dataType:"json",
		success:function(data){
			if(data.status == "1"){
			window.location.href="${RESOURCEDOMAIN}/shop/personalCenter";
			}else if(data.status == "0"){
				alert("data.message");
			}
		},
		error:function(data){
			 layer.msg(data.Message);
		}
	})
}
</script>
</html>
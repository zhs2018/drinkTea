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
    <title>发表评价</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
    <script type="text/javascript" src="${RESOURCEDOMAIN}/js/jquery.min.js"></script>
    <style>
        body {
            background: #eee;
        }
    </style>
</head>

<body>
<div class="verticalScreen">
   <div class="published-div">
       <img src="${RESOURCEDOMAIN}/${mp.picture}"  alt="商品图片">
       <p>${mp.name}${mp.introduce}</p>
       <input type="text" name= "orderId" value="${orderId}" id="orderId" hidden="true">
   </div>
    <textarea class="textarea" placeholder="您有什么建议、想法和期望，请告诉我们~" id="content" name="content"></textarea>
    <div class="refund-btn" onclick="saveContent()">
    	发表评价
     </div>
</div>
<p class="crossScreen">请竖屏</p>
<script src=" ${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src=" ${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</body>
 <script>
     function saveContent(){
	 	var content = $("#content").val();
	 	var id = $("#orderId").val();
		$.ajax({
			url:"${RESOURCEDOMAIN}/shop/comment",
			type:"post",
			data: {
				"content":content,
				"id":id
			},
			dataType:"json",
			success:function(data){
				if(data.status == "0"){
					window.location.href = "${RESOURCEDOMAIN}/shop/myOrder";
				}else if(data.status == "1"){
					layer.msg(data.Message);
				}
			},
		});
	}

   </script>

</html>








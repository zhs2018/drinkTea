<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>我的好友</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
    <style>
        body{
            background: #eee;
        }
    </style>
</head>
<body>
<form class="" method="post"
  action="myFriend" novalidate="novalidate">
<div class="verticalScreen">

    <ul class="comm-list">
    	<c:if test="${empty friends}">
    		<li>暂无好友</li>
    	</c:if>
   	 	<c:forEach items="${friends}" var="friend" varStatus="vs">
	       <li>
	           <div class="comm-name">
	           		<c:if test="${!empty friend.user.image}">
	           		<img src="${friend.user.image}" alt="头像">
	           		</c:if>
	           		<c:if test="${empty friend.user.image}">
	           		<img src="${RESOURCEDOMAIN}/image/weixin/logo1.png" alt="头像">
	           		</c:if>

	           <p>
	           <c:if test="${!empty friend.user.userName}">
	           	${friend.user.userName}
	           	</c:if>
	           	<c:if test="${empty friend.user.userName}">
	           		未获取到该用户信息
	           	</c:if>
	           </p></div>
	           <p class="comm-p"></p>
	           <p class="comm-time">添加时间
	           <fmt:parseDate value="${friend.createTime}" var="date" pattern="yyyyMMddHHmmss"/>
				<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss" />
	           </p>
	       </li>
      </c:forEach>
    </ul>
    <div style="height:3.1rem;"></div>
    <div class="refund-btn getCode" style="border:none;position: fixed;"><button class="refund-btn">获取二维码添加新朋友</button></div>
</div>
</form>
<p class="crossScreen">请竖屏</p>
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</body>
<script>
	$(function(){
		var flag = true;
		$(".getCode").click(function(){
			if(flag){
				alert("稍后会将二维码以图片的形式发送到公众号，请注意查收！");
				$.ajax({
					  url:"${RESOURCEDOMAIN}/getFriend",
					  type:"post",
					  data:{},
					  dataType:"json",
					  success:function(data){
							alert(data.message);
					  }
				  })
			}else{
				alert("请耐心等待服务器响应！");
			}
		});
	});
</script>
</html>
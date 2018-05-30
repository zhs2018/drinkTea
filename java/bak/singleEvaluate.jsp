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
    <title>宝贝评价</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
    <style>
        body{
            background: #eee;
        }
    </style>
</head>
<body>
<form class="" method="post"
  action="keyint" novalidate="novalidate">
<div class="verticalScreen">

    <ul class="comm-list">
    	<c:if test="${empty resultList}">
    		<li>暂无宝贝评价</li>
    	</c:if>
   	 	<c:forEach items="${resultList}" var="orders" varStatus="vs">
	       <li>
	           <div class="comm-name"><img src="${RESOURCEDOMAIN}/image/weixin/logo1.png" alt="头像"><p>${orders.wuName}</p></div>
	           <p class="comm-p">${orders.content}</p>
	           <p class="comm-time"><fmt:formatDate value="${orders.evaluateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></p>
	       </li>
      </c:forEach>
    </ul>
</div>
</form>
<p class="crossScreen">请竖屏</p>
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</body>
</html>
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
    <title>我的红包</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
    <style>
        body{
            background: #eee;
        }
    </style>
</head>
<body>
<div class="verticalScreen">
    <div class="envelope-top">
        <div class="envelope-f">
            <img src="${user.image}" alt="">
            <p class="envelope-name">${user.userName}</p>
        </div>
        <div class="envelope-hb">
            <i></i>
            <label>￥
            	<c:if test="${money == 0}">0</c:if>
            	<c:if test="${money != 0}">${money}</c:if>
            </label>
        </div>
    </div>
    <div class="envelope-div">
        <p class="envelope-t">红包明细</p>
        <ul class="envelope-list">
      <c:forEach  items="${objList}" var="obj" varStatus="vs">
            <li>
                <div class="envelope-li-div">
                  <h4>答题得红包</h4>
                    <p><fmt:formatDate value="${obj.answerTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
                </div>
                <label>+ ${obj.money}</label>
            </li>
      </c:forEach>
          </ul>
    </div>
</div>
<p class="crossScreen">请竖屏</p>
<script src="${RESOURCEDOMAIN}/js/layer1/js/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/layer1/js/html_rem.js"></script>
</body>
</html>
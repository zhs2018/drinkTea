<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>消息中心</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
    <style>
     .message-p p{
       display:none
     }
     .message-p p:nth-child(1){
       display:block
     }
<%-- .message-list li:active{
    background: #eee;
}  --%>
    </style>
</head>
<body>

<div class="verticalScreen">
    <ul class="message-list">
    <c:forEach items="${ObjList}" var="ob">
    <a href='${RESOURCEDOMAIN}/content?id=${ob.id}'>
     <c:if test="${ob.readSign == 2}">
         <li class="">
            <div class="message-title"><p>${ob.headTitle}</p><label>${ob.releaseTime}</label></div>
            <div class="message-p">${ob.centerContent}
              <input type="text" id="readSign" name="readSign" value="${ob.readSign}" hidden="true"></div>
        </li>
        </c:if>
        <c:if test="${ob.readSign == 1}">
         <li class="active">
            <div class="message-title"><p>${ob.headTitle}</p><label>${ob.releaseTime}</label></div>
            <div class="message-p">${ob.centerContent}
              <input type="text" id="readSign" name="readSign" value="${ob.readSign}" hidden="true"></div>
        </li>
        </c:if>
        </a>
        </c:forEach>
        <p style="text-align: center; margin-top: 50%;color:#999">暂时没更多消息^-^</p>
    </ul>
</div>
<p class="crossScreen">请竖屏</p>
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>

</body>
</html>
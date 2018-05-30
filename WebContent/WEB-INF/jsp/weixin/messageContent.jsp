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
       .verticalScreen{
       padding:0 0.75rem
       }
       .message-p{
          padding:0 0.875rem;
          font-size:0.875rem
       }
    </style>
</head>
<body>
<div class="verticalScreen">
            <p style="text-align:center;font-size:1rem;margin:0.625rem 0">${ob.headTitle}</p>
            <p style="text-align:center;color:#999;font-size:0.75rem;margin-bottom:20px">${ob.releaseTime}
            <div  class="message-p">
                <p> ${ob.centerContent}</p>
             </div>
</div>
<p class="crossScreen">请竖屏</p>
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</body>
</html>
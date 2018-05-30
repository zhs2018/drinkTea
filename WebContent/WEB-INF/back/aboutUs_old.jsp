
<%@ page language="java" contentType="text/html; charset=UTF-8"  isELIgnored="false"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>关于我们</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
    <style>

    </style>
</head>
<body>
 <div class="verticalScreen">
    <div class="aboutUs-div">

        <h3><p>${abouts.headline}</p></h3>
        <p class="aboutUs-zl"><label>time</label><label class="aboutUs-t">tea</label><label class="aboutUs-m">TEA茶商城</label>
        </p>
         <p> <img src="${RESOURCEDOMAIN}/${abouts.picture}" width="200px"/></p>
           <div class="aboutUs-div-p">
            <p>${abouts.fielda}</p>
            <P>${abouts.fieldb}</P>
            <p>${abouts.fieldc}</p>
            <p>${abouts.content}</p>
       </div>
     </div>
</div>
<<p class="crossScreen">请竖屏</p>

<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</body>
</html>
<%--src="${RESOURCEDOMAIN}/image/weixin/img9.png"
<p> <img src="${RESOURCEDOMAIN}/${abouts.picture}" width="120px"/></p>

function getTime()
{
    var time = new Date();
    $("#times").html(time.toLocaleString());
}
$(function(){
    setInterval("getTime()",1000);
});

--%>


  <script>
   function getTime()
    {
     var time = new Date();
       $("#times").html(time.toLocaleString());
   }
      $(function(){
     setInterval("getTime()",1000);
  });

</script>












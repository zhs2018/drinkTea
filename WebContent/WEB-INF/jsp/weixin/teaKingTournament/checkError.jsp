<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>茶王争霸</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/css.css">
    <script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
    <script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</head>
<body>
<div class="verticalScreen">
    <div class="checkError-bg">
        <div class="checkError-w">
            <p class="checkError-title">题目：</p>
            <p class="checkError-p"></p>
            <p class="checkError-ts">正确答案：</p>
            <p class="checkError-answer"></p>
        </div>
        <div class="checkError-template"></div>

        <div class="checkError-template-div">
            <p class="checkError-hr"></p>
            <div class="checkError-w">
            </div>
        </div>

        <a href="${RESOURCEDOMAIN}/teaKing/achievement/today">
            <div class="checkError-button"></div>
        </a>
    </div>
</div>
<p class="crossScreen">请竖屏</p>
</body>
<script>
	var data = ${subjects};
	 $(function () {
	        $(".checkError-p").html(data.subjects[0].subject.name);
	        $(".checkError-answer").html(data.subjects[0].subject.answer);
	        $.each(data.subjects,function (i,item) {
	            var div = $("<div class='checkError-c'>第"+item.num+"题</div>")
	            $(".checkError-template-div .checkError-w").append(div);
	        });
	        $(".checkError-c").eq(0).addClass("active")
	        $('.checkError-template-div .checkError-w .checkError-c').click(function () {
	            $(this).addClass('active').siblings().removeClass('active');
	            var index = $(".checkError-template-div .checkError-w .checkError-c").index($(this));
	            $(".checkError-p").html(data.subjects[index].subject.name);
	            $(".checkError-answer").html(data.subjects[index].subject.answer);
	        })
	    })
</script>
</html>
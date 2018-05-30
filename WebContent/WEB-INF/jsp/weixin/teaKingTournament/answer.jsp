<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>茶王争霸</title>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/css.css">
<link
	href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/imgLoader.js"></script>
<style>
.loader {
	background: #C0DAD9;
}

.loader__bar {
	color: #fff;
	line-height: 2em;
	height: 2em;
	position: absolute;
	left: 50%;
	top: 50%;
	width: 10em;
	text-align: center;
	margin-left: -5em;
	margin-top: -1em;
	overflow: hidden;
}

.loader__progress, .loader__progress-bg, .loader__info {
	position: absolute;
	height: 100%;
	left: 0;
	top: 0;
}

.loader__progress {
	z-index: 1;
	width: 0;
	background: #1A3831;
	border-radius: 1em;
}

.loader__progress-bg {
	z-index: 0;
	width: 100%;
	background: #3C776A;
	border-radius: 1em;
}

.loader__info {
	z-index: 2;
	width: 100%;
}
</style>
</head>
<body>
	<div class="answer-bg" style="display: none">
		<div class="time">
			<input id="times" name="times" value="${times}" hidden="true">
		</div>
		<div class="answer-div">
			<div class="height_7">
				<p class="answer-title">请选出下图中绿茶属于哪个品种？:</p>
				<div class="answer-banner-d">
					<img class="answer-banner" src="${RESOURCEDOMAIN}/${subject.img}"
						alt="banner">
				</div>
			</div>
			<ul class="answer-ul"
				style="overflow-y: auto; -webkit-overflow-scrolling: touch; overflow: -moz-scrollbars-horizontal">

			</ul>
		</div>
	</div>
	<form action="result" style="display: none" method="post">
		<input class="answer" name="answer" value="-1" type="text"> <input
			name="time" id="time" value="0" type="text">
	</form>
	<script
		src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="http://bootboxjs.com/bootbox.js"></script>
</body>
<script>
	<c:forEach items="${subject.options}" var="option" varStatus="vs">
	$(".answer-ul").append(
			$("<li><label></label>"
					+ String.fromCharCode(parseInt('${vs.index+65}'))
					+ " ${option.option}</li>"));
	</c:forEach>
	var i = $("#times").val();
	var flag = true;
	var timer;
	function show() {
		i--;
		$('.time').text(i);
		if (i == 0) {
			flag = false;
			$("form").submit();
			clearInterval(timer);
		}
	}
	$(document).ready(function() {
		console.log($('.height_7').outerHeight());
		console.log($(window).outerHeight());
		var h = $(window).outerHeight() - $('.height_7').outerHeight();
		$('.answer-ul').height(h)
	})
	$(function() {
		$(".answer-bg").css("display", "none");
		var callbacks = [];
		$("body").css("background", "#3C776A");
		var loader = "<section id='loader' class='loader'>"
				+ "<div class='loader__bar'>"
				+ "<div class='loader__progress-bg'></div>"
				+ "<div id='loader__progress' class='loader__progress'></div>"
				+ "<div id='loader__info' class='loader__info'>Loading 0%</div>"
				+ "</div></section>";
		$("body").append(loader);
		imgLoader([ '${RESOURCEDOMAIN}/${subject.img}',
				'${RESOURCEDOMAIN}/image/weixin/teaking/img01.png',
				'${RESOURCEDOMAIN}/image/weixin/teaking/img02.png',
				'${RESOURCEDOMAIN}/image/weixin/teaking/bg2.png' ], function(
				percentage) {
			var percentT = percentage * 100;
			$('#loader__info').html('Loading ' + (parseInt(percentT)) + '%');
			$('#loader__progress')[0].style.width = percentT + '%';
			if (percentage == 1) {
				$('#loader').remove();
				$(".answer-bg").css("display", "block");
				timer = setInterval("show()", 1);
				$(".answer-ul li").click(
						function() {
							if (flag) {
								var index = $(".answer-ul li").index(this);
								var answer = $(this).html().split(
										"<label></label>", 2)[1];
								bootbox.dialog({
									message : "您确定选择 " + answer + "?",
									title : "提示",
									buttons : {
										main : {
											label : "确定!",
											className : "blue",
											callback : function() {
												flag = false;
												clearInterval(timer);
												$("#time").val(i);
												$(".answer").val(index);
												$("form").submit();
											}
										}
									}
								});
							} else {
								bootbox.dialog({
									message : "答题结果已经提交，请等待服务器响应! ",
									title : "提示",
									buttons : {
										main : {
											label : "确定!",
											className : "blue",
											callback : function() {
											}
										}
									}
								});
							}
						});
			}
		});
	});

	pushHistory();
	window.addEventListener("popstate", function(e) {
		//alert("我监听到了浏览器的返回按钮事件啦");//根据自己的需求实现自己的功能
		if (confirm("确定要退出答题？"))
			window.location = '${RESOURCEDOMAIN}/teaKing/index';
	}, false);

	function pushHistory() {
		var state = {
			title : "title",
			url : "#"
		};
		window.history.pushState(state, "title", "#");
	}
</script>
</html>
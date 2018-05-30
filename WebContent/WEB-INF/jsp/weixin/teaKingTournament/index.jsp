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

    <div class=" index-body">
        <a href="${RESOURCEDOMAIN}/teaKing/viewTopic">
        	<div class="start_btn">查看上期题目</div>
        </a>
        <a href="${RESOURCEDOMAIN}/teaKing/answer">
        <div class="start_btn2">开始答题</div>
        </a>
       <a class="index-gz" href="${RESOURCEDOMAIN}/teaKing/ranking">查看历史排名</a><div><a class="index-gz" href="${RESOURCEDOMAIN}/teaKing/rule">规则说明</a></div>
        <div class="tea1"></div>
        <div class="tea2"></div>
    </div>


<!--弹窗1-->
<div id="layer-Popup" class="layer-Popup" style="display:none">
    <div class="layer-Popup-div">
        <h1>温馨提示</h1>
        <p class="p1">现在非答题时间 </p>
        <p class = "msg">请在09:00-10:30时间段答题</p>
    </div>
</div>

<!--弹窗2
<div id="layer-Popup2" class="layer-Popup">
    <div class="layer-Popup-div">
        <h1>温馨提示</h1>
        <p class="p1">现在非答题时间 </p>
        <p>不可重复答题，请明日再来</p>
    </div>
</div>
-->
</body>
<script src="${RESOURCEDOMAIN}/js/weixin/layer_mobile/layer.js"></script>
<script>
	$(function(){
		var result = "${result}";
		if(result == "1"){
			$(".layer-Popup-div .p1").html("亲，现在是非答题时间");
			$(".msg").html("请在${message}时间段答题");
			layer.open({
	            content: $('#layer-Popup').html()
	            , yes: function (index) {
	            }
	        });
		}
		if(result == "2"){
			$(".layer-Popup-div .p1").html("亲，现在是非答题时间");
			$(".msg").html("请耐心等待！");
			layer.open({
	            content: $('#layer-Popup').html()
	            , yes: function (index) {
	            }
	        });
		}
		if(result == "3"){
			$(".layer-Popup-div .p1").html("系统错误！！");
			$(".msg").html("${message}");
			layer.open({
	            content: $('#layer-Popup').html()
	            , yes: function (index) {
	            }
	        });
		}
		if(result == "4"){
			$(".layer-Popup-div .p1").html("亲，耐心等待！");
			$(".msg").html("题目还没有公布！");
			layer.open({
	            content: $('#layer-Popup').html()
	            , yes: function (index) {
	            }
	        });
		}
	});
    pushHistory();
	function pushHistory() {
	    var state = {
	        title: "title",
	        url: "${RESOURCEDOMAIN}/teaKing/index"
	    };
	    window.history.pushState(state, "title", "#");
	}
</script>
</html>
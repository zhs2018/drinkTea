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
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</head>
<body>
	<div class="wiew-bg">
		<div class="answer-div">
			<div class="height_7">
				<p class="answer-title" style="width: 100%">请选出下图中绿茶属于哪个品种？:</p>
				<div class="answer-banner-d">
					<img class="answer-banner" src="${RESOURCEDOMAIN}/${subject.img}"
						alt="banner">
				</div>
			</div>
			<p class="view-r">
				正确答案：<label> <c:if test="${empty answer.option}">正确答案未公布</c:if>
					<c:if test="${!empty answer.option}">${answer.option}<br>${subject.intro}</c:if>
				</label>
			</p>
			<ul class="achievement-ul" style="margin-top: 9rem">
				<!--查看上期题目-->
				<a href="javascript:0" onclick="ranking()">
					<li class="see">查看${time}排行榜</li>
				</a>
			</ul>
		</div>
	</div>
	<div id="layer-Popup" class="layer-Popup" style="display: none">
		<div class="layer-Popup-div">
			<h1>温馨提示</h1>
			<p class="p1">${time}排行榜未公布</p>
			<p class="msg">请在答案公布以后再查看</p>
		</div>
	</div>
</body>
<script src="${RESOURCEDOMAIN}/js/weixin/layer_mobile/layer.js"></script>
<script>
function ranking(){
	<c:if test="${empty answer.option}">
		layer.open({
	        content: $('#layer-Popup').html()
	        , yes: function (index) {
	        }
	    });
	</c:if>
	<c:if test="${!empty answer.option}">
		window.location.href = "${RESOURCEDOMAIN}/teaKing/rankingList";
	</c:if>
}
pushHistory();
window.addEventListener("popstate", function(e) {
    window.location.href = "${RESOURCEDOMAIN}/teaKing/index";
}, false);

function pushHistory() {
    var state = {
        title: "title",
        url: "#"
    };
    window.history.pushState(state, "title", "#");
}
</script>
</html>
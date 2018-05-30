<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>排行榜</title>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/css.css">
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</head>
<body>
	<div class="verticalScreen">
		<div class="ranking-bg">
			<div class="ranking-top">
				<div class="ranking-headbg">
					<c:if test="${!empty user.image }">
						<img class="ranking-head" src="${user.image }" alt="">
					</c:if>
					<c:if test="${empty user.image }">
						<img class="ranking-head"
							src="${RESOURCEDOMAIN}/image/weixin/head.png" alt="">
					</c:if>
				</div>
			</div>

			<div class="ranking-r" style="text-align: center">
				<p class="ranking-pm">
					排名：<label> <c:if test="${empty ranking}">无</c:if> <c:if
							test="${!empty ranking}">${ranking}</c:if>
					</label>
				</p>
				<p class="ranking-jl" style="text-align: center">
					用时:<label> <c:if test="${empty ranking}">0mm</c:if> <c:if
							test="${!empty ranking}">${time - myAnswer.overTime}mm</c:if>
					</label>
				</p>
			</div>
			<div class="ranking-hr"></div>
			<div class="ranking-bd">
				<ul>

					<c:forEach items="${answers}" var="answer" varStatus="vs">
						<li>
							<div class="ranking-div">
								<c:choose>
									<c:when test="${vs.index==0}">
										<div class="ranking-champion"></div>
										<div class="ranking-phead champion">
									</c:when>
									<c:when test="${vs.index==1}">
										<div class="ranking-michael"></div>
										<div class="ranking-phead michael">
									</c:when>
									<c:when test="${vs.index==2}">
										<div class="ranking-third"></div>
										<div class="ranking-phead third">
									</c:when>
									<c:otherwise>
										<div class="ranking-nmb">${vs.index+2 }</div>
										<div class="ranking-phead">
									</c:otherwise>
								</c:choose>

								<div class="ranking-frame">
									<c:if test="${!empty answer.user.image }">
										<img class="ranking-head" src="${answer.user.image }" alt="">
									</c:if>
									<c:if test="${empty answer.user.image }">
										<img class="ranking-head"
											src="${RESOURCEDOMAIN}/image/weixin/head.png" alt="">
									</c:if>
								</div>
								<div class="name">${answer.user.userName}</div>
								<div class="ranking-reward">
									用时<label>${time - answer.overTime}mm</label>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<p class="crossScreen">请竖屏</p>
</body>
<script>
	pushHistory();
	window.addEventListener("popstate", function(e) {
		window.location.href = "${RESOURCEDOMAIN}/teaKing/index";
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
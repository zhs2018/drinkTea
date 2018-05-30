<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>茶王争霸赛</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/css.css">
    <script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
    <script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
</head>
<body>
<div class="verticalScreen">
    <div class="achievement-bg">
        <div class="achievement-surplus"></div>
        <div class="achievement-nmb">${score}分</div>
        <p class="achievement-p">你答对了<label>${right}</label>道题目</p>
        <p class="achievement-p">当前排名<label>${ranking}</label>名</p>
        <ul class="achievement-ul">
            <!--查看错题-->
            <a href="${RESOURCEDOMAIN}/teaKing/checkError">
                <li class="see"></li>
            </a>
            <!--查看排行榜-->
            <a href="${RESOURCEDOMAIN}/teaKing/rankingList">
                <li class="ranking"></li>
            </a>
        </ul>
    </div>
</div>
<p class="crossScreen">请竖屏</p>
</body>
<script>
pushHistory();
window.addEventListener("popstate", function(e) {
		window.location = '${RESOURCEDOMAIN}/teaKing/index';
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
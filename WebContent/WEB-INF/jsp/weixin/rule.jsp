<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<style>
body {
	float: left;
	background: url("../drinkTea/image/weixin/teaking/bg3.png");
	background-size: 100% 100%;
	font-size: 1rem;
}
</style>
</head>
<body>
	<div class="rule-div">
		<h3 style="text-align: center;" >${cashDesc.cashTitle}</h3>
		<p style="text-indent:2em; padding:0px; margin:0px; ">${cashDesc.cashDesc}</p>
		<div style="text-align: center; padding-top: 30px;"><img src="${DOMAIN}/${cashDesc.cashImage}" style="width: 200px;hight:200px;"></div>
	</div>
</body>

</html>
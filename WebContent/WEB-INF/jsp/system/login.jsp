<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="IE=9" http-equiv="X-UA-Compatible">
<title>喝茶吧微信商城管理系统</title>

</head>
<LINK href="${RESOURCEDOMAIN}/css/system/reset.css" rel="stylesheet">
<LINK href="${RESOURCEDOMAIN}/css/system/global.css" rel="stylesheet">
<LINK href="${RESOURCEDOMAIN}/css/system/component.css" rel="stylesheet">
<LINK href="${RESOURCEDOMAIN}/css/system/login.css" rel="stylesheet">

<SCRIPT src="${RESOURCEDOMAIN}/js/jquery.min.js"></SCRIPT>
<SCRIPT src="${RESOURCEDOMAIN}/js/system/jquery.cookie.js"></SCRIPT>
<SCRIPT src="${RESOURCEDOMAIN}/js/system/jquery.validate.js"></SCRIPT>
<SCRIPT src="${RESOURCEDOMAIN}/js/system/jquery.validate.message_cn.js"></SCRIPT>
<SCRIPT src="${RESOURCEDOMAIN}/js/system/jquery.metadata.js"></SCRIPT>
<SCRIPT src="${RESOURCEDOMAIN}/js/system/jquery.nicescroll.min.js"></SCRIPT>
<SCRIPT src="${RESOURCEDOMAIN}/js/system/login.js"></SCRIPT>
<SCRIPT src="${RESOURCEDOMAIN}/js/system/tinybox.js"></SCRIPT>
<SCRIPT src="${RESOURCEDOMAIN}/js/system/mgTextWidth.js"></SCRIPT>
<SCRIPT src="${RESOURCEDOMAIN}/js/system/components.js"></SCRIPT>
<SCRIPT src="${RESOURCEDOMAIN}/js/system/global.js"></SCRIPT>

<SCRIPT>
	$(document).ready(function() {
		$('.login_form').fadeIn();
		registValidate();//注册验证
	});

	var destPage = null;
	var pageType = null;
</SCRIPT>

<META name="GENERATOR" content="MSHTML 11.00.9600.17105">
</HEAD>
<BODY scroll="no">
	<DIV id="login_wrapper">
		<DIV id="login_main">
			<DIV class="login_logo"></DIV>
			<DIV class="IE_alert">
				<P>很遗憾，您的浏览器过于古老，暂时无法使用麦客</P>
				<P class="suggestBrowser">
					我们建议您使用 <A
						href="http://www.google.cn/intl/zh-CN/chrome/browser/?installdataindex=chinabookmarkcontrol&amp;brand=CHUN">谷歌浏览器</A>，或
					<A
						href="http://windows.microsoft.com/zh-CN/internet-explorer/download-ie">更高版本的IE浏览器</A>
				</P>
			</DIV>
			<DIV class="login_form">
				<DIV class="login_form_header">
					<P class="p_login login_active">登录</P>
					<DIV class="clearB"></DIV>
				</DIV>
				<FORM class="login_items"  id="login_items">
					<LABEL class="input_val"><INPUT name="loginname" class="input"
						id="login_email" autocomplete="off"><SPAN>用户名</SPAN><IMG
						src="${RESOURCEDOMAIN}/image/system/icon/login_mail.png"></LABEL> <LABEL
						class="input_val"><INPUT name="password" class="input"
						id="login_password" type="password" autocomplete="off"><SPAN>密码</SPAN><IMG
						src="${RESOURCEDOMAIN}/image/system/icon/login_password.png"></LABEL>
					<DIV class="login_msg">
						<P class="login_error">
							<SPAN></SPAN>
						</P>
					</DIV>
					<DIV class="clearB"></DIV>
					<A class="login_btn submit">登&nbsp;&nbsp;&nbsp;&nbsp;录</A>
				</FORM>
			</DIV>
			<P class="contactUs">联系我们：  山东和合信息科技有限公司</P>
		</DIV>
	</DIV>
	<!-- 忘记密码 -->
	<DIV class="popwin_forgetPassword" style="display: none;">
		<P class="popwin_title">忘记密码</P>
		<DIV class="popwin_content">
			<P class="popwin_error">呃..这好像不是你注册时的邮箱</P>
			<P class="popwin_tips">请输入注册时的邮箱账号</P>
			<FORM>
				<INPUT name="SIGNUPEMAIL"
					class="input popwin_input popwin_newForm_input" type="text">
				<A class="button btn_blue popwin_forgetPassword_confirm">确定</A> <A
					class="button btn_gray popwin_cancel" onclick="TINY.box.hide();">取消</A>
			</FORM>
		</DIV>
	</DIV>
	<!--footer Start-->

</BODY>
</HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>茶客茶道微信商城管理系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link
	href="${RESOURCEDOMAIN}/css/system/jstree/dist/themes/default/style.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${RESOURCEDOMAIN}/assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-toastr/toastr.min.css" />
<link
	href="${RESOURCEDOMAIN}/assets/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${RESOURCEDOMAIN}/assets/css/style-metronic.css"
	rel="stylesheet" type="text/css" />
<link href="${RESOURCEDOMAIN}/assets/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="${RESOURCEDOMAIN}/assets/css/style-responsive.css"
	rel="stylesheet" type="text/css" />
<link href="${RESOURCEDOMAIN}/assets/css/plugins.css" rel="stylesheet"
	type="text/css" />
<link href="${RESOURCEDOMAIN}/assets/css/themes/default.css"
	rel="stylesheet" type="text/css" id="style_color" />
<link href="${RESOURCEDOMAIN}/assets/css/pages/error.css" rel="stylesheet"
	type="text/css" />
<link href="${RESOURCEDOMAIN}/assets/css/custom.css" rel="stylesheet"
	type="text/css" />
<link href="${RESOURCEDOMAIN}/css/system/global.css" rel="stylesheet"
	type="text/css" />
<LINK href="${RESOURCEDOMAIN}/css/indexfront.css" rel="stylesheet">
<script src="${RESOURCEDOMAIN}/assets/plugins/jquery-1.10.2.min.js"
	type="text/javascript"></script>

<script src="${RESOURCEDOMAIN}/js/jquery.form.js" type="text/javascript"></script>
<script src="${RESOURCEDOMAIN}/js/jquery.min.js" type="text/javascript"></script>
</head>
<style>
* {
	font-family: "Microsoft Yahei", "微软雅黑", Tahoma, Arial, Helvetica,
		STHeiti;
}
</style>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN HEADER -->
	<div class="header navbar navbar-fixed-top">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="header-inner">
			<!-- BEGIN LOGO -->
			<a class="navbar-brand" href="${DOMAIN}/system/index">茶客茶道微信商城管理系统
			</a>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse"> <img
				src="${RESOURCEDOMAIN}/assets/img/menu-toggler.png" alt="" />
			</a>
			<!-- END RESPONSIVE MENU TOGGLER -->
			<!-- BEGIN TOP NAVIGATION MENU -->
			<ul class="nav navbar-nav pull-right">
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<li class="dropdown user"><a href="#" class="dropdown-toggle"
					style="padding-top: 10px;" data-toggle="dropdown"
					data-hover="dropdown" data-close-others="true"> <span
						class="username">用户：${sessionUser.userName } </span> <i
						class="fa fa-angle-down"></i>
				</a>
					<ul class="dropdown-menu">
						<li><a href="javascript:;" id="trigger_fullscreen"> <i
								class="fa fa-arrows"></i> 全屏
						</a></li>
						<li><a href="${DOMAIN}/system/logout"> <i
								class="fa fa-key"></i> 退出
						</a></li>
					</ul></li>
				<!-- END USER LOGIN DROPDOWN -->
			</ul>
			<!-- END TOP NAVIGATION MENU -->
		</div>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar-wrapper">
			<div class="page-sidebar navbar-collapse collapse">
				<!-- BEGIN SIDEBAR MENU -->
				<ul class="page-sidebar-menu" data-auto-scroll="true"
					data-slide-speed="200">
					<li class="sidebar-toggler-wrapper">
						<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
						<div class="sidebar-toggler hidden-phone"
							style=" margin-bottom: 5px;"></div> <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					</li>

					<c:forEach items="${ROLEWIXIN }" var="roles">
						<li id="menus${roles.id }"><a href="javascript:;"> <i
								class="fa ${roles.icon }"></i> <span class="title">
									${roles.name } </span> <span class="arrow "> </span>
						</a>
							<ul class="sub-menu">
								<c:forEach items="${roles.sysMenuList }" var="rolesecond">
									<li><a
										href="${RESOURCEDOMAIN}/${rolesecond.linkUrl } ">
											<i class="fa ${roles.icon }"></i> ${rolesecond.name }
									</a></li>
								</c:forEach>
							</ul></li>
					</c:forEach>
					<%-- <li id="menus1200"><a href="javascript:;"> <i
							class="fa fa-info"></i> <span class="title"> 数据统计 </span> <span
							class="arrow "> </span>
					</a>
						<ul class="sub-menu">
							<li><a href="${RESOURCEDOMAIN}/system/main.html?typeid=1200">
									<i class="fa fa-info"></i>我的店铺
							</a></li>
						</ul></li> --%>

				</ul>
				<!-- END SIDEBAR MENU -->
			</div>
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<div class="modal fade" id="portlet-config" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true"></button>
								<h4 class="modal-title">Modal title</h4>
							</div>
							<div class="modal-body">Widget settings form goes here</div>
							<div class="modal-footer">
								<button type="button" class="btn blue">Save changes</button>
								<button type="button" class="btn default" data-dismiss="modal">Close</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
				<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<!-- BEGIN STYLE CUSTOMIZER -->
				<script>
					var tddImageInit = "/uploads/";
					var RESOURCEDOMAIN = "${RESOURCEDOMAIN}";
				</script>
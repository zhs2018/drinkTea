<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datepicker/css/datepicker.css"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/plugins/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-colorpicker/css/colorpicker.css"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/plugins/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datetimepicker/css/datetimepicker.css"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/clockface/js/clockface.js"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/plugins/clockface/js/clockface.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/moment.min.js"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${RESOURCEDOMAIN}/assets/scripts/core/app.js"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/scripts/core/app.js"></script>
<script src="${RESOURCEDOMAIN}/assets/scripts/custom/components-pickers.js"
	tppabs="http://www.keenthemes.com/preview/metronic_admin/assets/scripts/custom/components-pickers.js"></script>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			修改密码 <small>用户</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					系统设置 </a> <i class="fa fa-angle-right"></i>
			</li>
			<li><a href="javascript:void(0)"> 修改密码 </a>
			</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>基本信息
				</div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					action="saveupdatesecret" novalidate="novalidate">
					<div class="form-body">
						<div class="alert alert-danger display-hide">
							<button data-close="alert" class="close"></button>
							You have some form errors. Please check below.
						</div>
						<div class="alert alert-success display-hide">
							<button data-close="alert" class="close"></button>
							Your form validation is successful!
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">原始密码 <span
								class="required"> * </span> </label>
							<div class="col-md-4">
								<input type="password" class="form-control w_require"
									value="" name="oldpassword" style="width:500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">新密码 <span
								class="required"> * </span> </label>
							<div class="col-md-4">
								<input type="password" class="form-control  w_password" id="newPass"
									value="" name="newpassword" style="width:500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">新密码重复 <span
								class="required"> * </span> </label>
							<div class="col-md-4">
								<input type="password" class="form-control w_renewPass" id="oldPass"
									value="" name="renewpassword" style="width:500px;">
							</div>

						</div>

					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green" type="submit" >提交</button>
						</div>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<script>
	$(function() {
		jQuery(document).ready(function() {
			// initiate layout and plugins
			ComponentsPickers.init();
		});
		var res="${result}";
		if(res=="success"){
			bootbox.dialog({
				message : "修改密码成功",
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
		if(res=="faile"){
			bootbox.dialog({
				message : "修改密码失败",
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


	})
</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
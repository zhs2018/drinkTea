<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<link rel="stylesheet" href="${RESOURCEDOMAIN}/js/default/default.css" />
<link rel="stylesheet" href="${RESOURCEDOMAIN}/js/code/prettify.css" />
<jsp:include page="../top.jsp"></jsp:include>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			抽奖时间设置<small>时间</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					抽奖时间设置</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)">抽检时间</a></li>
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
					<i class="fa fa-reorder"></i>抽奖时间设置
				</div>
				<div class="tools"></div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" method="post" id="form_config"
					action="${DOMAIN}/system/luckdraw/saveTime" novalidate="novalidate">
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
							<label class="control-label col-md-3">开始时间 ： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input size="25" type="text" name="startIme"
					value="${drawTime.startIme}" readonly class="form_datetime" >
					<input size="25" type="text" name="id"
					value="${drawTime.id}" hidden="true" >
							</div>
						</div>
                            <div class="form-group">
							<label class="control-label col-md-3">结束时间 ： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input size="25" type="text" name="endTime"
									value="${drawTime.endTime}" readonly class="form_datetime">
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn green rollesubmit" type="submit">提交</button>
							</div>
						</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$(".form_datetime").datetimepicker({
			format : 'yyyy-mm-dd hh:ii',
		});
	});
</script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<!-- END PAGE CONTENT-->
<script charset="utf-8" src="${RESOURCEDOMAIN }/js/kindeditor-all.js"></script>
<script charset="utf-8" src="${RESOURCEDOMAIN}/js/code/prettify.js"></script>
<jsp:include page="../foot.jsp"></jsp:include>

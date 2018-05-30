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
			消息中心 <small>消息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					消息中心 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 消息中心 </a></li>
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
					<i class="fa fa-reorder"></i>消息
				</div>
				<div class="tools"></div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" method="post" id="form_config"
					action="save" novalidate="novalidate">
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
							<label class="control-label col-md-3">消息标题： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${Obj.headTitle}" name="headTitle"> <input
									type="text" value="${Obj.id}" name="id" hidden="true">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">选择发送人群年龄段： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<select name="userType">
									<option value="10">所有人</option>
									<option value="8">男</option>
									<option value="9">女</option>
									<option value="1">小于18岁</option>
									<option value="2">18岁~24岁</option>
									<option value="3">25岁~30岁</option>
									<option value="4">30岁~40岁</option>
									<option value="5">40岁~50岁</option>
									<option value="6">50岁~60岁</option>
									<option value="7">大于60岁</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">添加发布时间 ： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input size="25" type="text" name="releaseTime"
									value="${Obj.releaseTime}" readonly class="form_datetime">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">消息内容 ：</label>
							<div class="col-md-4">
								<textarea rows="5" cols="50" id="descn" name="centerContent">${Obj.centerContent}</textarea>
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
			format : 'yyyy-mm-dd hh:ii'
		});
	});
	$(function() {
		var editor2 = "";
		KindEditor
				.ready(function(K) {
					editor2 = K
							.create(
									'textarea[id="descn"]',
									{
										cssPath : '${RESOURCEDOMAIN}/js/code/prettify.css',
										uploadJson : '${RESOURCEDOMAIN}/js/kindeditor/jsp/upload_json.jsp',
										fileManagerJson : '${RESOURCEDOMAIN}/js/kindeditor/jsp/file_manager_json.jsp',
										allowFileManager : true,
										urlType : 'domain',
										afterBlur : function() {
											this.sync();
										}
									});
					prettyPrint();
				});
	});

	$(function() {
		var editor2 = "";
		KindEditor
				.ready(function(K) {
					editor2 = K
							.create(
									'textarea[id="descns"]',
									{
										cssPath : '${RESOURCEDOMAIN}/js/kindeditor/plugins/code/prettify.css',
										uploadJson : '${RESOURCEDOMAIN}/fileupload/kindeditor/uploadJson',
										fileManagerJson : '${RESOURCEDOMAIN}/fileupload/kindeditor/fileManagerJson',
										allowFileManager : true,
										urlType : 'domain',
										afterBlur : function() {
											this.sync();
										}
									});
					prettyPrint();
				});
	});
</script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<!-- END PAGE CONTENT-->
<script charset="utf-8" src="${RESOURCEDOMAIN }/js/kindeditor-all.js"></script>
<script charset="utf-8" src="${RESOURCEDOMAIN}/js/code/prettify.js"></script>
<jsp:include page="../foot.jsp"></jsp:include>

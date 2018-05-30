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
			抽奖简介 <small>抽奖</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					抽奖简介 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 抽奖简介 </a></li>
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
					<i class="fa fa-reorder"></i>抽奖
				</div>
				<div class="tools"></div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" method="post" id="form_config"
					action="${DOMAIN}/system/luckdraw/update" novalidate="novalidate">
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
							<label class="control-label col-md-3">抽奖标题： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${draw.title}" name="title"> <input
									type="hidden" value="${draw.id}" name="id">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">抽奖简介 ：</label>
							<div class="col-md-4">
								<textarea rows="5" cols="50" id="descn" name="intro">${draw.intro}</textarea>
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
</script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<!-- END PAGE CONTENT-->
<script charset="utf-8" src="${RESOURCEDOMAIN }/js/kindeditor-all.js"></script>
<script charset="utf-8" src="${RESOURCEDOMAIN}/js/code/prettify.js"></script>
<jsp:include page="../foot.jsp"></jsp:include>

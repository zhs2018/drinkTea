<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			红包提现设置 <small>红包提现信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					红包提现设置 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 红包提现信息 </a></li>
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
					<i class="fa fa-reorder"></i>信息
				</div>
				<div class="tools"></div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					enctype="multipart/form-data" action="${DOMAIN}/system/cashmember/saveCashMoney"
					novalidate="novalidate">
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
							<label class="control-label col-md-3">用户昵称 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${user.userName}" name="userName" readonly="readonly"> <input
									type="hidden" value="${user.id }" name="id">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">真实姓名 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${user.name}" name="name" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">手机号： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${user.phone}" name="phone" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">可提取金额 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${user.restMoney}" name="restMoney" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">提取金额 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="" name="cashMoney" >
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">提现办理人 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="" name="createPeople" >
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn green" type="submit">提交</button>
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
	function check1() {
		$("#form1").submit();
	}
</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
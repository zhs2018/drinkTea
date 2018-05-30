<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/layer_uploader.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<script type="text/javascript" src="${RESOURCEDOMAIN}/js/layer/layer.js"></script>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			红包编辑<small>&nbsp;&nbsp;红包</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					红包设置</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 编辑红包 </a></li>
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
				<form class="form-horizontal" method="post" id="form_config"
					action="${DOMAIN}/system/tking/refundTime/saveRed">
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
							<label class="control-label col-md-3">赛事类型 ： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require" value="茶王争霸赛"
									readonly> <input type="text" value="${redMoney.id }"
									name="id" id="id" hidden="true" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">会员红包数 ： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require" name="moneyNum"
									id="moneyNum" value="${redMoney.moneyNum}">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">非会员红包数 ： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require" name="moneyNums"
									id="moneyNums" value="${redMoney.moneyNums}">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">红包获得人数 ： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require" name="countWeb"
									id="countWeb" value="${redMoney.countWeb}">
							</div>
						</div>
					</div>
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


<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>



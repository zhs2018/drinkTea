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
			赛事<small>&nbsp;&nbsp;信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					茶王争霸 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 添加赛事 </a></li>
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
					enctype="multipart/form-data"
					action="${DOMAIN}/system/user/saveUser" novalidate="novalidate">
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
								<input type="text" class="form-control w_require" name="name"
									id="name"> <input type="text" value="${id }" id="id"
									hidden />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">答题数量 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="number" class="form-control w_require" name="num"
									id="num">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">开始时间~结束时间 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require" name="time"
									id="time" class="form-control" />
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green rollesubmit" type="button"
								onclick="save();">提交</button>
						</div>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>

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
<script>
	//保存新增的赛事信息
	function save() {
		var time = $("#time").val();
		var num = $("#num").val();
		var id = $("#id").val();
		$.ajax({
			url : "${DOMAIN}/system/tking/update",
			type : "post",
			dataType : "json",
			data : {
				"time" : time,
				"num" : num,
				"id" : id
			},
			success : function(data) {
				if (data.status == 1) {
					layer.msg("修改成功", {
						icon : 1
					});
					setTimeout(function() {
						window.location.href = "${DOMAIN}/system/tking/list";
					}, 1000);
				} else {
					layer.msg(data.Message);
				}
			},
			error : function() {

			}
		})
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#time").daterangepicker({
			timePicker : true,
			timePickerIncrement : 30,
			format : 'YYYY-MM-DD HH:mm:ss'
		}, function(start, end, label) {
		});
	});
</script>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<style>
#imgPreview_div div {
	display: inline-block;
	margin-bottom: 10px;
	margin-left: 10px;
	text-align: center;
	width: 80px;
	text-align: center;
}

select {
	background-color: #fff;
	border: 1px solid #ccc;
	width: 100px;
	height: 25px;
}

.form-control {
	display: inline;
}
</style>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			公益商品管理 <small>公益商品信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					公益商品管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)">公益 </a></li>
		</ul>
	</div>
</div>

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>基本信息
				</div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					action="${DOMAIN}/system/welfare/product/save"
					novalidate="novalidate" enctype="multipart/form-data">
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
							<label class="control-label col-md-3">商品名称： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${manageProducts.name }" name="name"
									style="width: 300px;"> <input type="hidden"
									value="${manageProducts.id}" name="id" name="id">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">商品价格： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_price"
									value="${manageProducts.nowPrice }" name="nowPrice"
									style="width: 180px;" id="nowPrice">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">图片：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<div data-provides="fileinput" class="fileinput fileinput-new">
									<div class="input-group input-large">
										<div data-trigger="fileinput"
											class="form-control uneditable-input span3">
											<i class="fa fa-file fileinput-exists"></i>&nbsp; <span
												class="fileinput-filename"></span>
										</div>
										<span class="input-group-addon btn default btn-file"> <span
											class="fileinput-new"> 选择文件 </span> <span
											class="fileinput-exists"> Change </span><input type="file"
											name="myfile" accept="image/png,image/gifimage/jpg,image/jpeg">
										</span>
									</div>

									<c:if test="${not empty obj.picUrl }">
										<br>
										<br>
										<img
											src="${RESOURCEDOMAIN}${fn:replace(obj.picUrl,'.','_800_600.')}"
											height="200px" width="400px" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">图片：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<div data-provides="fileinput" class="fileinput fileinput-new">
									<div class="input-group input-large">
										<div data-trigger="fileinput"
											class="form-control uneditable-input span3">
											<i class="fa fa-file fileinput-exists"></i>&nbsp; <span
												class="fileinput-filename"></span>
										</div>
										<span class="input-group-addon btn default btn-file"> <span
											class="fileinput-new"> 选择文件 </span> <span
											class="fileinput-exists"> Change </span><input type="file"
											name="myfile1" accept="image/png,image/gifimage/jpg,image/jpeg">
										</span>
									</div>

									<c:if test="${not empty obj.picUrl }">
										<br>
										<br>
										<img
											src="${RESOURCEDOMAIN}${fn:replace(obj.picUrl,'.','_800_600.')}"
											height="200px" width="400px" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">图片：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<div data-provides="fileinput" class="fileinput fileinput-new">
									<div class="input-group input-large">
										<div data-trigger="fileinput"
											class="form-control uneditable-input span3">
											<i class="fa fa-file fileinput-exists"></i>&nbsp; <span
												class="fileinput-filename"></span>
										</div>
										<span class="input-group-addon btn default btn-file"> <span
											class="fileinput-new"> 选择文件 </span> <span
											class="fileinput-exists"> Change </span><input type="file"
											name="myfile2" accept="image/png,image/gifimage/jpg,image/jpeg">
										</span>
									</div>

									<c:if test="${not empty obj.picUrl }">
										<br>
										<br>
										<img
											src="${RESOURCEDOMAIN}${fn:replace(obj.picUrl,'.','_800_600.')}"
											height="200px" width="400px" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">商品简介 ： </label>
							<div class="col-md-4">
								<textarea name="introduce" id="introduce"
									style="width: 700px; height: 300px;">${manageProducts.introduce }</textarea>
							</div>
						</div>

						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn green rollesubmit" type="submit" value="提交">提交</button>
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
	function updatecomm() {
		var sub = $("#form_config").serialize();
		$
				.ajax({
					url : "${DOMAIN}/system/commodity/updatecomm",
					type : "post",
					dataType : "json",
					data : sub,
					success : function(data) {
						if (data.status == "1") {
							layer.msg(data.message, {
								icon : 1
							});
							setTimeout(
									function() {
										window.location.href = "${DOMAIN}/system/commodity/commodit";
									}, 1000);
						} else {
							layer.msg(data.Message);
						}
					},
					error : function(data) {
						layer.msg(data.Message);
					}
				})
	}

	function examine(id) {
		window.location.href = "${DOMAIN}/system/commodity/myAbout/" + id;
	}

	$(function() {
		$.extend($.validator.messages, {
			required : "必选字段",
			remote : "请修正该字段",
			email : "请输入正确格式的电子邮件",
			url : "请输入合法的网址",
			date : "请输入合法的日期",
			dateISO : "请输入合法的日期 (ISO).",
			number : "请输入合法的数字",
			digits : "只能输入整数",
			creditcard : "请输入合法的信用卡号",
			equalTo : "请再次输入相同的值",
			maxlength : $.validator.format("请输入一个长度最多是 {0} 的字符串"),
			minlength : $.validator.format("请输入一个长度最少是 {0} 的字符串"),
			rangelength : $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
			range : $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
			max : $.validator.format("请输入一个最大为 {0} 的值"),
			min : $.validator.format("请输入一个最小为 {0} 的值")
		})
	})
</script>
<jsp:include page="../foot.jsp"></jsp:include>
<!-- END PAGE CONTENT-->

<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
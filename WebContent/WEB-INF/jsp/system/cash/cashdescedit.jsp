<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			提现说明设置 <small>设置</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					提现说明设置 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 说明信息 </a></li>
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
					enctype="multipart/form-data" action="${DOMAIN}/system/cashdesc/saveDesc"
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
							<label class="control-label col-md-3">标题： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${cashDesc.cashTitle}" name="cashTitle"> <input
									type="hidden" value="${cashDesc.id }" name="id">
							</div>
						</div>
							<div class="form-group">
							<label class="control-label col-md-3">说明： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<textarea  class="form-control w_require"
									  name="cashDesc" >${cashDesc.cashDesc}</textarea>
							</div>
						</div>
							<div class="form-group">
							<label class="control-label col-md-3">添加二维码：<span
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
											name="myfile"
											accept="image/gif,image/jpeg,image/jpg,image/png">
										</span>
									</div>

									<c:if test="${not empty cashDesc.cashImage}">
										<br>
										<br>
										<img
											src="${RESOURCEDOMAIN}/${cashDesc.cashImage}"
											height="200px" width="200px" />
									</c:if>
								</div>
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
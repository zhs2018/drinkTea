<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			轮播图设置 <small>轮播图</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					轮播图设置 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 轮播图信息 </a></li>
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
					enctype="multipart/form-data" action="save"
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
							<label class="control-label col-md-3">轮播图名称 ： <span class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${obj.title }" name="title"> <input type="hidden"
									value="${obj.id }" name="id">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">链接 ： </label>
							<div class="col-md-4">
								<input type="text" class="form-control"
									value="${obj.linkUrl }" name="linkUrl">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">排序 ： </label>
							<div class="col-md-4">
								<input type="text" class="form-control w_weight"
									value="${obj.sorts }" name="sorts">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">图片(建议800*600以上)：<span
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
										<span class="input-group-addon btn default btn-file">  <span
											class="fileinput-new"> 选择文件 </span> <!-- <span
											class="fileinput-exists"> Change </span> --><input type="hidden"
											value="" name="..."><input type="file" name="myfile" accept="image/gif,image/jpg,image/png"
											>
										</span> <!-- <a data-dismiss="fileinput"
											class="input-group-addon btn red fileinput-exists" href="#">
											Remove </a> -->
									</div>

									<c:if test="${not empty obj.picUrl }">
										<br>
										<br>
										<img src="${RESOURCEDOMAIN}${fn:replace(obj.picUrl,'.','_800_600.')}" height="200px"
											width="400px" />
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green rollesubmit" type="submit" >提交</button>
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
</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
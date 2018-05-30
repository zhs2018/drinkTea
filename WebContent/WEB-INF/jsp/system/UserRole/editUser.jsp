<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/layer_uploader.css" />
<script type="text/javascript" src="${RESOURCEDOMAIN}/js/layer/layer.js"></script>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			用户管理<small>&nbsp;&nbsp;编辑</small>
		</h3>
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
					enctype="multipart/form-data" action="${DOMAIN}/system/user/saveUser"
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
							<label class="control-label col-md-3">工号 ： <span class="required"> * </span></label>
							<div class="col-md-4">
								<input type="number" class="form-control" name="jobNumber" value="${user.jobNumber}">
							</div>
						</div>
						<div class="form-group">
							<input type="text" value = "${user.id}" name="id" hidden/>
							<label class="control-label col-md-3">员工姓名 ： <span class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									 name="userName" id="userName" value="${user.userName}">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">初始密码 ： <span class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require" value="123456"
									 name="password">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">员工角色 ： <span class="required"> * </span></label>
							<div class="col-md-4">
							<select name="roleId">
							<c:forEach items="${roleList}" var="role" varStatus="vs">
							<option value="${role.id}">${role.roleName}</option>
							</c:forEach>
							</select>
								<%-- <label><input type="radio" value="1" name="roleId"
									<c:if test="${user.roleId == 1}">checked = "checked"</c:if>
								/>系统管理员</label>
								<label><input type="radio" value="2" name="roleId"
									<c:if test="${user.roleId == 2}">checked = "checked"</c:if>
								/>普通员工</label> --%>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">手机号 ： <span class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control" name="phone" value="${user.phone }">
							</div>
						</div>
						<!-- <div class="form-group">
							<label class="control-label col-md-3">员工头像(建议800*600以上)：<span
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
										<span class="input-group-addon btn default btn-file">
											<span class="fileinput-new"> 选择文件 </span>
											<span class="fileinput-exists"> 选择 </span>
											<input type="file" name="myfile" id="myfile">
										</span>
										<a data-dismiss="fileinput"
											class="input-group-addon btn red fileinput-exists" href="#">
											删除
										</a>
									</div>
								</div>
							</div>
						</div> -->
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green rollesubmit" type="button" onclick="saveUser();">提交</button>
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
<script>
	function saveUser(){
		var sub = $("#form_config").serialize();
		$.ajax({
			url:"${DOMAIN}/system/user/updateUser",
			type:"post",
			data:sub,
			dataType:"json",
			success:function(data){
				if(data.status == 1){
					layer.msg("修改成功",{icon: 1});
					setTimeout(function(){
						window.location.href = "${DOMAIN}/system/user/list";
					}, 1000);
				}else if(data.status == 0){
					layer.msg(data.Message);
				}
			},
			error:function(data){
				layer.msg(data.Message);
			}
		})
	}
</script>
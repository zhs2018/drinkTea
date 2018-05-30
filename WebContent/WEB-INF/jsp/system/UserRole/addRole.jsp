<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			添加角色 <small>选择权限组成角色</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					角色管理 </a> <i class="fa fa-angle-right"></i>
			</li>
			<li><a href="javascript:void(0)"> 添加角色 </a>
			</li>
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
					<i class="fa fa-reorder"></i>基本信息
				</div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config"
					action="saverole.html" novalidate="novalidate">
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
							<label class="control-label col-md-3">角色名称 <span
								class="required"> * </span> </label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require" id="roleName" name="roleName" style="width:500px;"
								onblur="checkout();">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">角色菜单<span
								class="required"> * </span> </label>
								<div class="col-md-4">
									<div class="form-control height-auto">
										<input type="hidden" id="menuId" name="menuId">
										<div class="scroller" style="height: 400px;" data-always-visible="1">
											<div id="tree_4" class="tree-demo"></div>
										</div>
									</div>
								</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green rollesubmit" type="button" onclick="addRoleData();">提交</button>
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

<script type="text/javascript">
//在输入角色名称的输入框上加了一个失去焦点事件，判断一下角色名称之前是不是已经存在了
function checkout(){
	var roleName = $("#roleName").val();

	if(roleName == ""){
		layer.msg("角色名称不能为空");
	}else{
		$.ajax({
			url:"${DOMAIN}/system/role/checkout",
			data:{
				"roleName":roleName
			},
			dataType:"json",
			success:function(data){
				if(data.status == 0){
					layer.msg(data.Message);
				}
			},
			error:function(){
				layer.msg("程序出错了，请联系程序员小哥");
			}
		})
	}
}

//添加角色数据
function addRoleData(){
	var sub = $("#form_config").serialize();
	var menuId = $("#menuId").val();
	var roleName = $("#roleName").val();
	if(roleName == ""){
		layer.msg("角色名称不能为空");
	}else if(menuId == ""){
		layer.msg("所选菜单不能为空");
	}else{
		$.ajax({
			url:"${DOMAIN}/system/role/saveRole",
			type:"post",
			dataType:"json",
			data:sub,
			success:function(data){
				if(data.status == 0){
					layer.msg(data.Message);
				}else if(data.status == 1){
					layer.msg("添加成功",{icon: 1});
					setTimeout(function(){
						window.location.href = "${DOMAIN}/system/role/list";
					}, 1000);
				}
			},
			error:function(){
				layer.msg("程序出错了，请联系程序员小哥");
			}
		});
	}
}
</script>
<script>
		$("#tree_4").jstree({
			'plugins': ["wholerow", "checkbox", "types"],
			"core": {
				"themes": {
					"responsive": false
				},
				"check_callback": true,
				"data": ${sysMenu}
			},
			"types": {
				"default": {
					"icon": "jstree-folder"
				},
				"file": {
					"icon": "fa fa-file icon-state-warning icon-lg"
				}
			}
		}).bind('click.jstree', function(node, tree_obj) {
			$("#menuId").val($('#tree_4').jstree().get_checked());
		});

</script>
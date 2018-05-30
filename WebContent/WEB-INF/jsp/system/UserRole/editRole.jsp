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
			编辑角色 <small>选择权限组成角色</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					角色管理 </a> <i class="fa fa-angle-right"></i>
			</li>
			<li><a href="javascript:void(0)"> 编辑角色 </a>
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
								<input type="text" class="form-control w_require" id="roleName" value="${role.roleName}" name="roleName" style="width:500px;"
								onblur="checkout();">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">一级菜单 &nbsp; </label>
							<div class="col-md-4">
								<button class="btn purple" type="button" onclick="insertFirstMenu();">
									<i class="fa fa-check"></i> 添加一级菜单
								</button>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">角色菜单<span
								class="required"> * </span> </label>
								<div class="col-md-4">
									<div class="form-control height-auto">
										<input type="hidden" id="menuId" value="${menuId }" name="menuId">
										<div class="scroller" style="height: 400px;" data-always-visible="1">
											<div id="tree_4" class="tree-demo"></div>
										</div>
									</div>
								</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green rollesubmit" type="button" onclick="updateRoleData('${role.id}');">提交</button>
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

//修改角色数据
function updateRoleData(id){
	var sub = $("#form_config").serialize();
	$.ajax({
		url:"${DOMAIN}/system/role/update/" + id,
		type:"post",
		data:sub,
		success:function(data){
				layer.msg("修改成功",{icon: 1});
				setTimeout(function(){
					window.location.href = "${DOMAIN}/system/role/list";
				}, 1000);
		},
		error:function(){
			layer.msg("程序出错了，请联系程序员小哥");
		}
	});
}
</script>
<script>
function html(obj){
	var h = "<input id='inp' style='height:30px;width:250px;margin:20px 20px 10px' placeholder='请输入\""+obj.text+"\"的子菜单名称'/>"+
	"<span style='color:red'>*</span>"+
	"<input id='link' style='height:30px;width:250px;margin:20px 20px 10px' placeholder='请输入该菜单的链接地址'/>"+
	"<span style='color:red'>*</span>"+
	"<input id='sort' style='height:30px;width:250px;margin:20px 20px 10px' placeholder='请输入该菜单的排序数值'/>"+
	"<span style='color:red'>*</span>"+
	"<input id='icon' style='height:30px;width:250px;margin:20px 20px 10px' placeholder='请输入该菜单的icon'/>";
	return h;
};
function u(obj){
	var h = "<input id='inp' style='height:30px;width:250px;margin:20px 20px 10px' placeholder='请修改\""+obj.text+"\"的菜单名称'/>"+
	"<span style='color:red'>*</span>"+
	"<input id='sort' style='height:30px;width:250px;margin:20px 20px 10px' placeholder='请修改该菜单的排序数值'/>"+
	"<span style='color:red'>*</span>"+
	"<input id='link' style='height:30px;width:250px;margin:20px 20px 10px' placeholder='请修改该菜单的链接地址'/>"+
	"<input id='icon' style='height:30px;width:250px;margin:20px 20px 10px' placeholder='请修改该菜单的icon'/>";
	return h;
};
function first(){
	var h = "<input id='inp' style='height:30px;width:250px;margin:20px 20px 10px' placeholder='请输入菜单名称'/>"+
	"<span style='color:red'>*</span>"+
	"<input id='sort' style='height:30px;width:250px;margin:20px 20px 10px' placeholder='请输入该菜单的排序数值'/>"+
	"<span style='color:red'>*</span>"+
	"<input id='icon' style='height:30px;width:250px;margin:20px 20px 10px' placeholder='请输入该菜单的icon'/>";
	return h;
};
//在根目录下添加一级菜单
function insertFirstMenu(){
	layer.open({
		type:1,
		title:"添加根目录下菜单(带<span style='color:red'>*</span>的为必填)",
		content:first(),
		skin: 'demo-class',
		area:['300px'],
		btn:['添加','取消'],
		yes:function(index){
			var inp = $("#inp").val();//新添加的菜单名称
			var link = $("#link").val();//该菜单的链接，点了该菜单能跳到哪个页面
			var iconClass = $("#icon").val();
			var sort = $("#sort").val();
			/* var sub = JSON.stringify({
				"name":inp,
				"parentId":parentId,
				"linkUrl":link,
				"iconClass":iconClass
			}); */
			$.ajax({
				url:"${DOMAIN}/system/menu/insertFirstMenu",
				type:"post",
				dataType:"json",
				//contentType: "application/json",
				data:{
					"name":inp,
					"linkUrl":link,
					"icon":iconClass,
					"sort":sort
				},
				success:function(data){
					if(data.status == 1){
						layer.msg("添加菜单成功",{icon: 1});
						setTimeout(function(){
							location.reload();
						},1000);
					}
					if(data.status == 0){
						layer.msg(data.Message);
					}
				},
				error:function(){
					layer.msg("程序出错，请联系程序员小哥");
				}
			});
			parent.layer.close(index);
		}
	})
}
		$("#tree_4").jstree({
			'plugins': ["wholerow", "checkbox", "types","contextmenu"],
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
			},
			"contextmenu":{
				"items":{
					"create":null,
					"rename":null,
					"remove":null,
					"cpp":null,
					"新建菜单":{
						"label":"新建菜单",
						"action":function(data){
							var inst = jQuery.jstree.reference(data.reference),
		                    obj = inst.get_node(data.reference);
							layer.open({
								type:1,
								title:"添加菜单(带<span style='color:red'>*</span>的为必填)",
								content:html(obj),
								skin: 'demo-class',
								area:['300px'],
								btn:['添加','取消'],
								yes:function(index){
									var inp = $("#inp").val();//新添加的菜单名称
									var parentId = obj.id;//右键单击的当前菜单id，即要新建的菜单的父级id
									var link = $("#link").val();//该菜单的链接，点了该菜单能跳到哪个页面
									var iconClass = $("#icon").val();
									var sort = $("#sort").val();
									/* var sub = JSON.stringify({
										"name":inp,
										"parentId":parentId,
										"linkUrl":link,
										"iconClass":iconClass
									}); */
									$.ajax({
										url:"${DOMAIN}/system/menu/insertMenu",
										type:"post",
										dataType:"json",
										//contentType: "application/json",
										data:{
											"name":inp,
											"parentId":parentId,
											"linkUrl":link,
											"icon":iconClass,
											"sort":sort
										},
										success:function(data){
											if(data.status == 1){
												layer.msg("添加菜单成功",{icon: 1});
												setTimeout(function(){
													location.reload();
												},1000);
											}
											if(data.status == 0){
												layer.msg(data.Message);
											}
										},
										error:function(){
											layer.msg("程序出错，请联系程序员小哥");
										}
									});
									parent.layer.close(index);
								}
							})
		                    }
						},
					"删除菜单":{
						"label":"删除菜单",
						"action":function(data){
							var inst = jQuery.jstree.reference(data.reference),
		                    obj = inst.get_node(data.reference);
							var sub = JSON.stringify({
								"id":obj.id
							});
							layer.confirm('您确定删除该菜单吗？', {
								  title:"删除菜单",
								  btn: ['确定删除','要不算了'] //按钮
								},
								//“确定删除”的事件
								function(){
									$.ajax({
										url:"${DOMAIN}/system/menu/deleteMenu",
										type:"post",
										dataType:"json",
										data:sub,
										contentType: "application/json",
										success:function(data){
											if(data.status == 1){
												layer.msg("删除菜单成功",{icon: 1});
												setTimeout(function(){
													location.reload();
												},1000);
											}
											if(data.status == 0){
												layer.msg(data.Message);
											}
										},
										error:function(){
											layer.msg("程序出错，请联系程序员小哥");
										}
									});
								},
								//“要不算了”的事件
								function(){
								  layer.close();
								});
						}
					},
					"修改菜单":{
						"label":"修改菜单",
						"action":function(data){
							var inst = jQuery.jstree.reference(data.reference),
		                    obj = inst.get_node(data.reference);
							layer.open({
								type:1,
								title:"修改菜单(带<span style='color:red'>*</span>的为必填)",
								content:u(obj),
								skin: 'demo-class',
								area:['300px'],
								btn:['修改','取消'],
								yes:function(index){
									var inp = $("#inp").val();//新添加的菜单名称
									var id = obj.id;//右键单击的当前菜单id
									var link = $("#link").val();//该菜单的链接，点了该菜单能跳到哪个页面
									var iconClass = $("#icon").val();
									var sort = $("#sort").val();
									$.ajax({
										url:"${DOMAIN}/system/menu/updateMenu",
										type:"post",
										dataType:"json",
										data:{
											"name":inp,
											"id":id,
											"linkUrl":link,
											"icon":iconClass,
											"sort":sort
										},
										success:function(data){
											if(data.status == 1){
												layer.msg("修改菜单成功",{icon: 1});
												setTimeout(function(){
													location.reload();
												},1000);
											}
											if(data.status == 0){
												layer.msg(data.Message);
											}
										},
										error:function(){
											layer.msg("程序出错，请联系程序员小哥");
										}
									});
									parent.layer.close(index);
								}
							})
						}
					}
				}
			}
		}).bind('click.jstree', function(node, tree_obj) {
			$("#menuId").val($('#tree_4').jstree().get_checked());
		});

</script>
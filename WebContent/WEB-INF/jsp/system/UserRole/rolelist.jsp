<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			角色列表 <small>分配系统角色</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					角色及权限</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 角色菜单管理</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-6" style="width: 100%">
		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class=" fa fa-cogs"></i>信息
				</div>
			</div>
			<div class="portlet-body">
					 <button class="btn purple" type="button" onclick="addRole();">
						<i class="fa fa-check"></i> 添加角色
					</button>
					<br> <br>
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>角色名称</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty roleList}">
									<c:forEach items="${roleList}" var="product" varStatus="vs">

										<tr class="">
											<td>${vs.index +1}</td>
											<td>${product.roleName}</td>
											<td><fmt:formatDate value="${product.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><a class="btn default btn-xs purple"
												onclick="editRole(${product.id})"> <i
													class="fa fa-edit"></i> 编辑
											</a> &nbsp; &nbsp; <a id="${product.id }"
												class="btn default btn-xs black" onclick="deleteRole('${product.id}');" >
												<i class="fa fa-trash-o"></i> 删除
											</a> </td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="7">没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<div class="page_and_btn">
						<div></div>
						${uc.page.pageStr }
					</div>
				</div>
			</div>
		</div>
		<!-- END SAMPLE TABLE PORTLET-->
	</div>
</div>

<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<script>
	//跳转到编辑角色页面
	function editRole(id) {
		window.location.href = "${DOMAIN}/system/role/edit/" + id
	}

	//跳转到新加角色页面
	function addRole(){
		window.location.href = "${DOMAIN}/system/role/addRole";
	}

	//删除角色
	function deleteRole(id){
		layer.confirm("您确定删除该角色吗？",{
			title:"删除角色",
			btn: ['确定删除','留着吧']
		},
		function(){
			$.ajax({
				url:"${DOMAIN}/system/role/deleteRole/"+id,
				type:"post",
				dataType:"json",
				success:function(data){
					if(data.status == 1){
						layer.msg("删除菜单成功",{icon: 1});
						setTimeout(function(){
							window.location.href = window.location.href;
						},1500);
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
		function(){
			layer.close();
		});
	}
</script>
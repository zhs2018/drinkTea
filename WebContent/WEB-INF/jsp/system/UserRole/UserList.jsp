<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<script type="text/javascript" src="${RESOURCEDOMAIN}/js/layer/layer.js"></script>
<style type="text/css">
.aa{
	cursor: pointer;
}
</style>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			员工 <small>信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					角色及权限 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 用户管理 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<form action="${DOMAIN}/system/user/list" method="post">
		<div class="col-md-6" style="width: 100%">
			<!-- BEGIN SAMPLE TABLE PORTLET-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class=" fa fa-cogs"></i>信息 （总共有${su.page.totalResult }位员工）
					</div>
				</div>

				<div class="portlet-body">
					<div class="col-md-4" style="width: 250px;">
						<div class="controls">
							<div class="input-prepend input-group">
								<input type="text" style="width: 240px" name="userName"
									placeholder="登录名" class="form-control" class="span4">
							</div>
						</div>
					</div>

					<div class="col-md-4" style="width: 250px;">
						<div class="controls">
							<div class="input-prepend input-group">
							<select name="roleId"  class="form-control">
								<option value="">请选择用户类型</option>
								<option value="1">系统管理员</option>
								<option value="2">普通员工</option>
							</select>
							</div>
						</div>
					</div>

					<button class="btn purple" type="submit">
						<i class="fa fa-check"></i> 查询
					</button>
					<button class="btn purple" type="button" onclick="addUser();">
						<i class="fa fa-check"></i> 添加员工
					</button>
					<br> <br>
					<div class="table-responsive" style="margin-top: 5px">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>用户ID</th>
									<th>工号</th>
									<th>登录名</th>
									<th>手机号</th>
									<th>用户类型</th>
									<th>入职时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty sysUserList}">
										<c:forEach items="${sysUserList}" var="product" varStatus="vs">

											<tr class="" style="vertical-align: middle;">
												<td style="vertical-align: middle;">${product.id}</td>
												<td style="vertical-align: middle;">${product.jobNumber}</td>
												<td style="vertical-align: middle;">${product.userName}</td>
												<td style="vertical-align: middle;">${product.phone}</td>
												<td style="vertical-align: middle;">${product.roleName}
												<%-- <c:choose>
													<c:when test="${product.roleId==1}">系统管理员</c:when>
													<c:when test="${product.roleId==2}">普通员工</c:when>
												</c:choose> --%>
												</td>
												<td><fmt:formatDate value="${product.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td style="vertical-align: middle;">
												<a class="btn default btn-xs purple" href="${DOMAIN}/system/user/editUser/${product.id}"> <i class="fa fa-edit"></i> 编辑
												</a> &nbsp; &nbsp;<a id="${product.id }" onclick="deleteUser('${product.id}');" class="btn default btn-xs black" ><i class="fa fa-trash-o"></i> 删除
											</a>
												</td>
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
							${su.page.pageStr }
						</div>
					</div>
				</div>

			</div>

	<!-- END SAMPLE TABLE PORTLET-->
</div>
	</form>
	<div id="p" style="display:none">
   		<label for="num">会员转移工号：</label>
   		<input type="number" style="" class="num" id="num" value="">
	</div>
</div>
<!-- END PAGE CONTENT-->
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
<jsp:include page="../foot.jsp"></jsp:include>
<script>
	function updaterole(id) {
		window.location.href = "updateuser.html?userId=" + id
				+ "&typeid=${param.typeid}";
	}
	//跳转到添加员工的页面
	function addUser(){
		window.location.href = "${DOMAIN}/system/user/addUser";
	}

	//删除员工信息
	function deleteUser(id){
		layer.confirm("您确定删除该员工吗？",{
				title:"删除员工",
				content:$('#p').html(),
				btn: ['确定删除','留着吧']
			},
			function(){
				var sub = $("#form_config").serialize();
				var num = $(".layui-layer-content .num").val();
				 $.ajax({
						url:"${DOMAIN}/system/user/deleteUser/"+id,
						type:"post",
						data:{
							"jobNumber":num
						},
						dataType:"json",
						success:function(data){
							if(data.status == 1){
								layer.msg("删除成功",{icon: 1});
								setTimeout(function(){
									window.location.href = "${DOMAIN}/system/user/list";
								}
								, 1000);
							}else if(data.status == 0){
								layer.msg(data.Message);
							}
						},
						error:function(data){
							layer.msg(data.Message);
						}
					})
			},
			function(){
				layer.close();
			});
	}
</script>
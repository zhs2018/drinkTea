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
			自定义菜单管理 <small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					自定义菜单管理 </a></li>
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
				<div class="portlet-body">
					<div class="alert alert-block alert-info fade in">
						<button type="button" class="close" data-dismiss="alert"></button>
						<h4 class="alert-heading">帮助!</h4>
						<p>说明：如果需要点击跳转到指定页面，请设置类型为“跳转链接”，如果需要点击打开微信扫一扫，请设置类型为“扫一扫”</p>
						<p>1.如需设置商城首页，请在“设置”列中设为"${RESOURCEDOMAIN }/shop/index",类型为“跳转链接”</p>
						<p>2.如需设置微信扫一扫，请在“设置”列中填写需要的key值"，类型为“扫一扫”</p>
						<p>注：如果菜单为主菜单项，设置中的内容将不起作用，填写链接时请填写完整的链接地址</p>
						<p>&nbsp;&nbsp;&nbsp;扫一扫仅支持微信iPhone5.4.1以上版本，和Android5.4以上版本的微信用户，旧版本微信用户点击后将没有回应</p>
					</div>
				</div>
				<div class="table-responsive">
					<div class="setting_frame">
						<a class="button btn blue add_menu">添加主菜单</a>
					</div>
					<form action="savemenu" id="menu_form">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>显示顺序</th>
									<th style="width: 200px;">菜单名称</th>
									<th>类型</th>
									<th>设置</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${menus }" var="menu">
									<tr class="menuItem primary">
										<td><input type="hidden" name="id"
											value="${menu.id }" /> <input type="hidden"
											name="parentId" value="${menu.parentId }" /><input
											type="text" name="sort" class="form-control"
											value="${menu.sort }" /></td>
										<td><input type="text" name="name" class="form-control"
											value="${menu.name }" /></td>
										<td><select name="type" class="form-control">
												<option value="scancode_push"
													<c:if test="${menu.type=='scancode_push' }">selected="selected"</c:if>>扫一扫</option>
												<option value="view"
													<c:if test="${menu.type=='view' }">selected="selected"</c:if>>跳转链接</option>
										</select></td>
										<td><input type="text" name="content"
											class="form-control" value="${menu.content }" />
											<%--<input
											type="hidden" name="responseId"
											value="${companyMenu.responseId }" />--%>
											</td>
										<td><a href="javascript:void(0);" class="add_submenu"
											parentId="${menu.id }">增加子菜单</a>|<a
											href="javascript:void(0);" class="del_menu"
											menuId="${menu.id }">删除</a></td>
									</tr>
									<c:forEach items="${menu.childMenu }"
										var="childMenu">
										<tr class="menuItem item${childMenu.id }">
											<td><input type="hidden" name="id"
												value="${childMenu.id }" /> <input type="hidden"
												name="parentId" value="${childMenu.parentId }" /><input
												class="form-control" type="text" name="sort"
												value="${childMenu.sort }" /></td>
											<td>|____<input type="text" name="name"
												class="form-control"
												style="margin-top: -20px; margin-left: 40px; width: 150px;"
												value="${childMenu.name }" /></td>
											<td><select name="type" class="form-control">
												<option value="scancode_push"
													<c:if test="${childMenu.type=='scancode_push' }">selected="selected"</c:if>>扫一扫</option>
												<option value="view"
													<c:if test="${childMenu.type=='view' }">selected="selected"</c:if>>跳转链接</option>
										</select></td>
											<td><input type="text" name="content"
												class="form-control" value="${childMenu.content }" />
												<%--<input type="hidden" name="responseId"
												value="${subCompanyMenu.responseId }" /></td>--%>
											<td><a href="javascript:void(0);" class="del_submenu"
												menuId="${childMenu.id }">删除</a></td>
										</tr>

									</c:forEach>

								</c:forEach>
							</tbody>
						</table>
					</form>

					<a class="button btn blue settingPersonal_save validate_submit">保
						存</a> <a
						class="button btn blue settingPersonal_save update_weixinmenu">生成自定义菜单</a>
				</div>
			</div>
		</div>
	</div>
	<!-- END SAMPLE TABLE PORTLET-->
</div>
</div>
<!-- END PAGE CONTENT-->
<script src="${RESOURCEDOMAIN}/js/system/menu.js"></script>
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
<jsp:include page="../foot.jsp"></jsp:include>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			消息列表 <small>消息信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					消息列表 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 消息列表 </a></li>
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
					<i class=" fa fa-cogs"></i>消息
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th style="width: 10%">消息编号</th>
								<th style="width: 10%">消息标题</th>
								<th style="width: 15%">发布时间</th>
								<th style="width: 35%">消息内容</th>
								<th style="width: 10%">消息接受群体</th>
								<th style="width: 15%">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty ObjList}">
									<c:forEach items="${ObjList}" var="product" varStatus="vs">

										<tr class="">
											<td>${product.id}</td>
											<td>${product.headTitle}</td>
											<td>${product.releaseTime}</td>
											<td>${product.centerContent}</td>
											<td style="text-align: center;"><c:if
													test="${product.userType==1}">小于18岁</c:if> <c:if
													test="${product.userType==2}">18岁~24岁</c:if> <c:if
													test="${product.userType==3}">25岁~30岁</c:if> <c:if
													test="${product.userType==4}">30岁~40岁</c:if> <c:if
													test="${product.userType==5}">40岁~50岁</c:if> <c:if
													test="${product.userType==6}">50岁~60岁</c:if> <c:if
													test="${product.userType==7}">大于60岁</c:if> <c:if
													test="${product.userType==8}">男</c:if> <c:if
													test="${product.userType==9}">女</c:if> <c:if
													test="${product.userType==10}">所有人</c:if></td>
											<td><a class="btn default btn-xs purple"
												href="javascript:void(0)"
												onclick="editProduct(${product.id})"> <i
													class="fa fa-edit"></i> 编辑
											</a> &nbsp; &nbsp; <a id="${product.id }"
												class="btn default btn-xs purple" title="分类"
												href="javascript:void(0)"
												onclick="delProduct(${product.id})"> <i
													class="fa fa-trash-o"></i> 删除
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
					<div class="page_and_btn">${obj.page.pageStr }</div>
				</div>
			</div>
		</div>
		<!-- END SAMPLE TABLE PORTLET-->
	</div>
</div>
<script>
	function editProduct(id) {
		window.location.href = "getone?id=" + id;

	}
	function delProduct(id){
		layer.confirm("您确定删除该角色吗？",{
			title:"删除角色",
			btn: ['确定删除','留着吧']
		},
		function(){
			window.location.href = "delones/" + id ;
		},
		function(){
			layer.close();
		}
		)
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
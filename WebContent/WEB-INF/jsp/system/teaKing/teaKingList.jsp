<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<style>
.aa {
	cursor: pointer;
}
</style>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			赛事 <small>信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					茶王争霸 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 赛事列表 </a></li>
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
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>赛事类型</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty objList}">
									<c:forEach items="${objList}" var="one" varStatus="vs">
										<tr>
											<td>${one.id}</td>
											<td><c:if test="${one.type == 1 }">日赛</c:if> <c:if
													test="${one.type == 2 }">周赛</c:if> <c:if
													test="${one.type == 3 }">月赛</c:if> <c:if
													test="${one.type == 4 }">季赛</c:if> <c:if
													test="${one.type == 5 }">年赛</c:if></td>
											<td><c:choose>
													<c:when test="${one.type == 1 }">
														<fmt:formatDate value="${one.startTime }" pattern="HH:mm" />
													</c:when>
													<c:otherwise>
														<fmt:formatDate value="${one.startTime }"
															pattern="yyyy-MM-dd HH:mm:ss" />
													</c:otherwise>
												</c:choose></td>
											<td><c:choose>
													<c:when test="${one.type == 1 }">
														<fmt:formatDate value="${one.endTime }" pattern="HH:mm" />
													</c:when>
													<c:otherwise>
														<fmt:formatDate value="${one.endTime }"
															pattern="yyyy-MM-dd HH:mm:ss" />
													</c:otherwise>
												</c:choose></td>
											<td><a class="btn default btn-xs purple"
												onclick="edit('${one.id}');"> <i class="fa fa-edit"></i>
													编辑
											</a></td>
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
						${page.page.pageStr }
					</div>
				</div>
			</div>
		</div>
		<!-- END SAMPLE TABLE PORTLET-->
	</div>
</div>

<script>
	//跳到编辑页面
	function edit(id) {
		window.location.href = "${DOMAIN}/system/tking/edit/" + id;
	}
	//跳到添加页面
	function add() {
		window.location.href = "${DOMAIN}/system/tking/add";
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
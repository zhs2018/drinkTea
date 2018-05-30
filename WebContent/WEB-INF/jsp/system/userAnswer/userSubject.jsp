<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/css/system/datepicker3.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/css/plugins.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			答题情况 <small>用户扫码答题查询</small><br> <br>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					答题情况</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)">扫码答题情况</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="userSubject" method="post">
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
									<th>微信昵称</th>
									<th>微信头像</th>
									<th>答题总数</th>
									<th>正确总数</th>
									<th>获取红包</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${!empty users}">
										<c:forEach items="${users}" var="user" varStatus="vs">
											<tr class="">
												<td>${vs.index+1}</td>
												<td>${user.userName}</td>
												<td><img src="${user.image}" width="100px"
													height="100px" /></td>
												<td>${user.allCount}</td>
												<td>${user.rightCount}</td>
												<td><fmt:formatNumber value="${user.moneyAll }"
														type="number" pattern="￥0.00" /></td>
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
							${user.page.pageStr }
						</div>
					</div>
				</div>
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
		</div>
</form>
</div>
<!-- END PAGE CONTENT-->
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="${RESOURCEDOMAIN}/assets/scripts/custom/components-pickers.js"></script>
<script>

</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
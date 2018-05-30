<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${RESOURCEDOMAIN}/css/system/datepicker3.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" type="text/css" href="${RESOURCEDOMAIN}/assets/css/plugins.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			员工信息 <small>业绩查询</small><br><br>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					 角色及权限</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)">员工业绩查询</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="list" method="post">
	<div class="row m-t-sm">
		<div class="col-sm-11 m-b-xs">
			<div class="form-group" style="float: left;width:0px">
				<div class="btn-group" style="float: left;"></div>
				<div class="input-prepend input-group input-noneTable" style="">
					<i class="glyphicon glyphicon-calendar fa fa-calendar "></i>选择月份 <input
						type="text" name="ttime" placeholder="选择时间" id="ttime" style="width:300px"
						 class="form-control form_datetime inputActive" value="${time}" onfocus=this.blur()>
						<span class="m-l m-lStyle" style="float: right;margin-top:-32px">
						<button type="submit" class="btn btn-sm green"
							style="margin-left: 305px; float: right;height: 31px;">
							<i class="fa"></i>查询
						</button></span>
						<input type="hidden" value="${typeid }" name="typeid">
				</div>
			</div>
		</div>
	</div>
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
									<th>工号</th>
									<th>姓名</th>
									<th>手机号</th>
									<th>入职时间</th>
									<th>会员数量</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${!empty users}">
										<c:forEach items="${users}" var="user" varStatus="vs">
											<tr class="">
												<td>${vs.index+1}</td>
												<td>${user.jobNumber}</td>
												<td>${user.userName}</td>
												<td>${user.phone}</td>
												<td><fmt:formatDate value="${user.createTime }" pattern="yyyy-MM-dd" /></td>
												<td>${user.count}</td>
												 <td style="width:10%;text-align: center;vertical-align: middle;"><a
													class="btn big blue" href="javascript:void(0)"
													onclick="toinfo(${user.id})"> <i class=""></i>
													查看详情
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
<script src="${RESOURCEDOMAIN}/assets/scripts/custom/components-pickers.js"></script>
	<script>
	$(document).ready(
		function() {
			$('#ttime').daterangepicker({
						language:  'zh-CN',
						timePicker : false,
						timePickerIncrement : 30,
						format : 'YYYY-MM-DD'
					},
			function(start, end, label){
			});
		}
	)
	function toinfo(id){
		window.location.href="${RESOURCEDOMAIN}/system/user/memberUser?userId="+id+"&time=${time}";
	}
</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
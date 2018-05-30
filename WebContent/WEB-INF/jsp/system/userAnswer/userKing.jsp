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
			答题情况 <small>茶王争霸赛答题排行</small><br> <br>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					答题情况</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)">茶王争霸赛答题排行</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="userKing" method="post">
	<div class="row m-t-sm">
		<div class="col-sm-11 m-b-xs">
			<div class="form-group" style="float: left; width: 0px">
				<div class="btn-group" style="float: left;"></div>
				<div class="input-prepend input-group input-noneTable" style="">
					<i class="glyphicon glyphicon-calendar fa fa-calendar "></i>选择月份 (<span
						style="color: red;">2017.8.1-2017.8.2查询的为8.2号整天的数据</span>)<input
						type="text" name="ttime" placeholder="选择时间" id="ttime"
						style="width: 300px"
						class="form-control form_datetime inputActive" value="${sTime}"
						onfocus=this.blur()> <span class="m-l m-lStyle"
						style="float: right; margin-top: -32px">
						<button type="submit" class="btn btn-sm green"
							style="margin-left: 305px; float: right; height: 31px;">
							<i class="fa"></i>查询
						</button>
					</span> <input type="hidden" value="${typeid }" name="typeid">
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
									<th>微信昵称</th>
									<th>微信头像</th>
									<th>答对个数</th>
									<th>剩余时间</th>
									<th>是否为会员</th>
									<th>获取红包数</th>
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
												<td>${user.rightCount}</td>
												<td>${user.allTime}</td>
												<td>${user.member}</td>
												<td><fmt:formatNumber value="${user.mo}" type="number"
														pattern="￥0.0" /></td>
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
<script
	src="${RESOURCEDOMAIN}/assets/scripts/custom/components-pickers.js"></script>
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
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
			抽检时间列表<small>抽奖时间</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
				抽奖时间 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)">抽奖时间 </a></li>
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
					<i class=" fa fa-cogs"></i>时间
				</div>
			</div>

			<div class="portlet-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty drawTime}">
										<td>1</td>
										<td>${drawTime.startIme}</td>
										<td>${drawTime.endTime}</td>
										<td><a class="btn default btn-xs purple"
												href="javascript:void(0)"
												onclick="editTime(${drawTime.id})"> <i
													class="fa fa-edit"></i> 编辑</td>
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
						${obj.page.pageStr }
					</div>
				</div>
			</div>
		</div>
		<!-- END SAMPLE TABLE PORTLET-->
	</div>
</div>

<script>
	//跳到编辑页面
	function editTime(id) {
		window.location.href = "${DOMAIN}/system/luckdraw/time/" + id;
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
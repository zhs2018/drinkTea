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
			倒计时 与 红包金额的编辑<small>信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					茶王争霸 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 赛事倒计时与红包分发 </a></li>
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
								<th>倒计时时间(单位：毫秒)</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty refundTime}">
									<c:forEach items="${refundTime}" var="one" varStatus="vs">
										<tr>
											<td>${one.id}</td>
											<td>茶王争霸赛（倒计时）</td>
											<td>${one.time}</td>
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
						${obj.page.pageStr }
					</div>
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>赛事类型</th>
								<th>会员红包数</th>
								<th>非会员红包数</th>
								<th>多少人获取红包</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty redMoney}">
									<c:forEach items="${redMoney}" var="red" varStatus="vs">
										<tr>
											<td>${red.id}</td>
											<td>茶王争霸赛（红包发放）</td>
											<td>${red.moneyNum}元</td>
											<td>${red.moneyNums}元</td>
											<td>${red.countWeb}位</td>
											<td><a class="btn default btn-xs purple"
												onclick="editRed('${red.id}');"> <i class="fa fa-edit"></i>
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
	function edit(id) {
		window.location.href = "${DOMAIN}/system/tking/refundTime/edit/";
	}
	function editRed(id) {
		window.location.href = "${DOMAIN}/system/tking/refundTime/editRed/";
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
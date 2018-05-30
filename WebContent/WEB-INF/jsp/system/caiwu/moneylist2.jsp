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
			销售资金记录 <small>财务管理</small><br>
			<br>
			<div style="width: 800px; color: red">
				资金总计：
				<fmt:formatNumber type="number" value="${moneyAll}" pattern="0.00"
					maxFractionDigits="2" />
				元
			</div>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					财务管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 我的资金记录</a></li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="moneylists2" method="post">
	<div class="row m-t-sm">
		<div class="col-sm-11 m-b-xs">
			<div class="form-group" style="float: left; width: 0px">
				<div class="btn-group" style="float: left;"></div>
				<div class="input-prepend input-group input-noneTable" style="">
					<i class="glyphicon glyphicon-calendar fa fa-calendar "></i>选择月份 <input
						type="text" name="ttime" placeholder="入账时间" id="ttime"
						style="width: 300px"
						class="form-control form_datetime inputActive" onfocus=this.blur()
						value="${ttime}"> <span class="m-l m-lStyle"
						style="float: right; margin-top: -32px">
						<button type="submit" class="btn btn-sm green"
							style="margin-left: 305px; float: right; height: 31px;">
							<i class="fa"></i>查询
						</button>
					</span> <input type="hidden" value="${param.typeid }" name="typeid">
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
									<th style="text-align: center;">订单编号</th>
									<th style="text-align: center;">订单价格</th>
									<th style="text-align: center;">下单时间</th>
									<th style="text-align: center;">订单状态</th>
									<th style="text-align: center;">入账时间</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="product" varStatus="vs">
											<tr class="">
												<td
													style="width: 20%; text-align: center; vertical-align: middle; WORD-WRAP: break-word">${product.orderNumber}
												</td>
												<td
													style="width: 20%; text-align: center; vertical-align: middle; WORD-WRAP: break-word">￥:&nbsp${product.price}元
												</td>
												<td
													style="width: 20%; text-align: center; vertical-align: middle; WORD-WRAP: break-word"><fmt:formatDate
														value="${product.orderTime}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td
													style="width: 20%; text-align: center; vertical-align: middle; WORD-WRAP: break-word">
													<span style="color: red"><c:choose>
															<%-- <c:when test="${ product.orderState==11&&product.applyMethod!=3}">待支付</c:when>
															<c:when test="${ product.orderState==11&&product.applyMethod==3}">货到付款-待发货</c:when> --%>
															<c:when test="${ product.orderState==30}">待发货</c:when>
															<c:when test="${ product.orderState==40}">待收货</c:when>
															<c:when test="${ product.orderState==50}">已完成</c:when>
															<c:when test="${ product.orderState==60}">退货处理中</c:when>
															<c:when test="${ product.orderState==70}">退货已通过</c:when>
															<c:when test="${ product.orderState==80}">退货未通过</c:when>
														</c:choose></span>
												</td>
												<td
													style="width: 20%; text-align: center; vertical-align: middle;">
													<fmt:formatDate value="${product.applyTime }"
														pattern="yyyy-MM-dd HH:mm:ss" />
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
							${productOrder.page.pageStr }
						</div>
					</div>
				</div>
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
		</div>
</form>
</div>
<script>
	$(document).ready(function() {
		$('#ttime').daterangepicker({
			language : 'zh-CN',
			timePicker : true,
			timePickerIncrement : 30,
			format : 'YYYY-MM-DD H:mm'
		}, function(start, end, label) {
			console.log(start.toISOString(), end.toISOString(), label);
		});
	});
</script>
<!-- END PAGE CONTENT-->
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
<script src="${RESOURCEDOMAIN}/js/system/moment.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
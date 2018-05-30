<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			普通商品订单列表 <small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					订单管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 订单列表 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-6" style="width: 100%">
		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<form action="orderslist" method="post">
			<input type="hidden" value="${param.ty}" name="ty">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class=" fa fa-cogs"></i>信息
					</div>
				</div>
				<div class="portlet-body">
					<div class="col-md-4" style="width: 950px;">
						<div class="controls">
							<div class="input-prepend input-group">
								<span class="add-on input-group-addon"><i
									class="glyphicon glyphicon-calendar fa fa-calendar"></i></span> <input
									type="text" style="width: 300px" name="ttime"
									placeholder="订单时间" id="ttime" class="form-control"
									readonly="readonly" value="${ttime}" class="span4">
							</div>
						</div>
					</div>


					<script type="text/javascript">
						$(document).ready(
								function() {
									$('#ttime').daterangepicker(
											{
												timePicker : true,
												timePickerIncrement : 30,
												format : 'YYYY-MM-DD H:mm'

											},
											function(start, end, label) {
												console.log(
														start.toISOString(),
														end.toISOString(),
														label);
											});
								});
					</script>

					<button class="btn purple" type="submit">
						<i class="fa fa-check"></i> 查找
					</button>
					<button class="btn green" type="button" onclick="toexcel()">
						<i class="fa fa-share"></i> 导出
					</button>
					<br> <br>
					<div class="span6">
						<!--BEGIN TABS-->
						<div class="tabbable tabbable-custom">
							<ul class="nav nav-tabs">
								<li class="gt-1"><a data-toggle="tab" href="#tab_1_1"
									onclick="gotos('-1')">所有订单</a></li>
								<li class="gt20"><a data-toggle="tab" href="#tab_1_2"
									onclick="gotos('20')">待付款</a></li>
								<li class="gt30"><a data-toggle="tab" href="#tab_1_3"
									onclick="gotos('30')">待发货</a></li>
								<li class="gt40"><a data-toggle="tab" href="#tab_1_4"
									onclick="gotos('40')">待收货</a></li>
								<li class="gt50"><a data-toggle="tab" href="#tab_1_5"
									onclick="gotos('50')">交易完成</a></li>
								<li class="gt0"><a data-toggle="tab" href="#tab_1_6"
									onclick="gotos('10')">已关闭</a></li>
							</ul>
							<div class="tab-content">
								<div id="tab_1_1" class="tab-pane active">
									<div class="table-responsive" style="margin-top: 10px">
										<table class="table table-bordered table-hover"
											style="TABLE-LAYOUT: fixed">
											<thead>
												<tr>
													<th style="text-align: center; width: 14%">订单编号</th>
													<th style="text-align: center; width: 10%">商品价格</th>
													<th style="text-align: center; width: 10%">下单时间</th>
													<th style="text-align: center; width: 10%">收货人</th>
													<th style="text-align: center; width: 13%">收货人电话</th>
													<th style="text-align: center; width: 21%">买家留言</th>
													<th style="text-align: center; width: 21%">订单状态</th>
													<th style="text-align: center; width: 11%">操作</th>
												</tr>
											</thead>
											<tbody>
												<c:choose>
													<c:when test="${not empty orResultlist}">
														<c:forEach items="${orResultlist}" var="orders"
															varStatus="vs">
															<tr>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;">${orders.orderNumber}
																</td>

																<td
																	style="width: 10%; text-align: center; vertical-align: middle;"><c:if
																		test="${empty orders.price}">0</c:if> <c:if
																		test="${!empty orders.price}">${orders.price}</c:if></td>

																<td
																	style="width: 10%; text-align: center; vertical-align: middle;"><fmt:formatDate
																		value="${orders.orderTime}"
																		pattern="yyyy-MM-dd HH:mm:ss" /></td>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;">${orders.userAddress.userName}</td>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;">
																	${orders.userAddress.telPhone}</td>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;">${orders.customerMessage}</td>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;">
																	<%-- ${orders.orderState} --%> <span style="color: red"><c:choose>
																			<c:when test="${orders.tabs==1}">免费领取</c:when>
																			<c:when test="${orders.tabs==1&&orders.orderState==30}">免费领取/待收货</c:when>
																			<c:when test="${orders.tabs==1&&orders.orderState==40}">免费领取/待收货</c:when>
																			<c:when test="${orders.tabs==1&&orders.orderState==50}">免费领取/已完成</c:when>
																			<c:when test="${orders.tabs==1&&orders.orderState==90}">免费领取/已评价</c:when>
																			<c:when test="${orders.orderState==20}">待支付</c:when>
																			<%-- <c:when test="${ orders.orderState==30&&orders.applyMethod==3}">货到付款-待发货</c:when> --%>
																			<c:when test="${ orders.orderState==30}">待发货</c:when>
																			<c:when test="${ orders.orderState==40}">待收货</c:when>
																			<c:when test="${ orders.orderState==50}">已完成</c:when>
																			<c:when test="${ orders.orderState==60}">退款处理中</c:when>
																			<c:when test="${ orders.orderState==70}">退款已通过</c:when>
																			<c:when test="${ orders.orderState==80}">退款未通过</c:when>
																			<c:when test="${ orders.orderState==90}">商品已评价</c:when>
																			<c:when test="${ orders.orderState==10}">已取消</c:when>
																			<c:when test="${ orders.orderState==100}">系统自动取消</c:when>
																		</c:choose></span>
																</td>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;"><a
																	class="btn big blue" href="javascript:void(0)"
																	onclick="toinfo(${orders.id})"> <i class=""></i>
																		查看详情
																</a>
																<c:choose>
																		<c:when test="${ orders.orderState==30}">
																			<a class="btn big red" href="javascript:void(0)"
																				onclick="changeState(${orders.id})"> <i
																				class=""></i>标记为已发货
																			</a>
																		</c:when>
																		<c:when test="${ orders.orderState==1}">
																			<a class="btn big red" href="javascript:void(0)"
																				onclick="changeState(${orders.id})"> <i
																				class=""></i>标记为已发货
																			</a>
																		</c:when>
																	</c:choose></td>
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
										<div class="page_and_btn">${obj.page.pageStr}</div>
									</div>
								</div>
							</div>
						</div>
						<!--END TABS-->
					</div>
				</div>
			</div>
		</form>
		<!-- END SAMPLE TABLE PORTLET-->
	</div>

</div>

<script>
	function toinfo(id) {

		window.location.href = "ordersinfo/"
				+ id;
	}

	function changeState(id){
		alert(123);
		window.location.href = "${DOMAIN}/system/orders/stateChage?id="+id;
	}

function gotos(ty) {
	 window.location.href = "orderlist?typeid=${param.typeid}&ty=" + ty ;
	}

	function toexcel() {
	var time = 	$("#ttime").val();
	if(time!=null&&time!=""){
		window
		.open("${DOMAIN}/system/orders/toexcel?ttime="
				+ $("#ttime").val());
	}else{
		layer.alert("请输入导出订单的时间段");
	}

	}


	$(function() {
		var party = "${param.ty}";
		if (party == "") {
			$(".gt-1").addClass("active");
		} else {
			$(".gt" + party).addClass("active");
		}
		ComponentsPickers.init();

	})

</script>
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
<jsp:include page="../foot.jsp"></jsp:include>
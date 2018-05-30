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
		<form action="OrdersList" method="post">
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
								<li class="gt10"><a data-toggle="tab" href="#tab_1_6"
									onclick="gotos('10')">已关闭</a></li>
							</ul>
							<div class="tab-content">
								<div id="tab_1_1" class="tab-pane active">
									<div class="table-responsive" style="margin-top: 10px">
										<table class="table table-bordered table-hover"
											style="TABLE-LAYOUT: fixed">
											<thead>
												<tr>
													<th style="text-align: center; width: 10%">订单编号</th>
													<th style="text-align: center; width: 10%">商品价格</th>
													<th style="text-align: center; width: 10%">下单时间</th>
													<th style="text-align: center; width: 10%">收货人</th>
													<th style="text-align: center; width: 13%">收货人电话</th>
													<th style="text-align: center; width: 21%">买家留言</th>
													<th style="text-align: center; width: 11%">订单状态</th>
													<th style="text-align: center; width: 20%">操作</th>
												</tr>
											</thead>
											<tbody>
												<c:choose>
													<c:when test="${not empty orResultlist}">
														<c:forEach items="${orResultlist}" var="sysUser"
															varStatus="vs">
															<tr>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;">${sysUser.orderNumber}
																</td>
																<%-- <input type="text" name="orderId" id="orderId" value="${sysUser.orderId}" hidden="true"> --%>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;"><c:if
																		test="${empty sysUser.price}">0</c:if> <c:if
																		test="${!empty sysUser.price}">${sysUser.price}</c:if></td>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;"><fmt:formatDate
																		value="${sysUser.orderTime}"
																		pattern="yyyy-MM-dd HH:mm:ss" /></td>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;">${sysUser.uaName}</td>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;">
																	${sysUser.uaPhone}</td>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;">${sysUser.customerMessage}</td>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;">
																	<span style="color: red"><c:choose>
																			<c:when test="${sysUser.tabs==1}">免费领取</c:when>
																			<c:when test="${sysUser.tabs==1&&sysUser.orderState==30}">待发货</c:when>
																			<c:when test="${sysUser.tabs==1&&sysUser.orderState==40}">免费领取/待收货</c:when>
																			<c:when test="${sysUser.tabs==1&&sysUser.orderState==50}">免费领取/已完成</c:when>
																			<c:when test="${sysUser.tabs==1&&sysUser.orderState==90}">免费领取/已评价</c:when>
																			<c:when test="${ sysUser.orderState==20}">待支付</c:when>
																			<c:when test="${ sysUser.orderState==30}">待发货</c:when>
																			<c:when test="${ sysUser.orderState==40}">待收货</c:when>
																			<c:when test="${ sysUser.orderState==50}">已完成</c:when>
																			<c:when test="${ sysUser.orderState==60}">退款处理中</c:when>
																			<c:when test="${ sysUser.orderState==70}">退款已通过</c:when>
																			<c:when test="${ sysUser.orderState==10}">已取消</c:when>
																			<c:when test="${ sysUser.orderState==80}">退款未通过</c:when>
																			<c:when test="${ sysUser.orderState==90}">商品已评价</c:when>
																			<c:when test="${ sysUser.orderState==100}">系统自动取消</c:when>
																		</c:choose></span>
																</td>
																<td
																	style="width: 10%; text-align: center; vertical-align: middle;"><a
																	class="btn big blue" href="javascript:void(0)"
																	onclick="toinfo(${sysUser.orderId})"> <i class=""></i>查看订单详情
																</a> <c:choose>
																		<c:when test="${ sysUser.orderState==30}">
																			<a class="btn big red" href="javascript:void(0)"
																				onclick="changeState(${sysUser.orderId})"> <i
																				class=""></i>标记为已发货
																			</a>
																		</c:when>
																		<c:when test="${ sysUser.orderState==1}">
																			<a class="btn big red" href="javascript:void(0)"
																				onclick="changeState(${sysUser.orderId})"> <i
																				class=""></i>标记为已发货
																			</a>
																		</c:when>
																	</c:choose></td>
															</tr>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<tr class="main_info">
															<td colspan="8">没有相关数据</td>
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
		window.location.href = "stateChage?id="+id;
	}

function gotos(ty) {
	 window.location.href = "orderlist?typeid=${param.typeid}&ty=" + ty ;
	}

	function toexcel() {
		var ttime=$("#ttime").val();
		var orderN = $("#orderid").val();
	  if(null!=ttime||ttime!=""){
		$.ajax({
			url:"toexcel",
			type:"post",
			dataType:"json",
			data:{
				orderNum:orderN,
				ttime:ttime
			},
	      success:function(data){
				  if(data.status==1){
					  window.location.href="list"
				  }
			  },
		  error:function(data){
				  layer.msg(data.Message);
			  }
		})
	}else{
		layer.confirm("请输入订单编号",{
			title:"订单编号",
			btn: ['确定','知道啦']
		},
		function(){
			layer.close();
		},
		function(){
			layer.close();
		}
		)
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
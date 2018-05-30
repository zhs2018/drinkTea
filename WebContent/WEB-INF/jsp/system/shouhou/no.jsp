<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>

<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			售后服务 <small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					售后服务 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)">已拒绝列表</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-6" style="width: 100%">
		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<form action="returnorderslist.html?typeid=${param.typeid }" method="post">
			<input type="hidden" value="${param.ty }" name="ty">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class=" fa fa-cogs"></i>信息
					</div>
				</div>
				<div class="portlet-body">
					<div class="span6">
						<!--BEGIN TABS-->
						<div class="tabbable tabbable-custom">
							<ul class="nav nav-tabs">
								<li class="gt-1"><a data-toggle="tab" href="#tab_1_3"
									>已拒绝(${empty no ?0 :no})</a></li>
							</ul>
							<div class="tab-content">
								<div id="tab_1_1" class="tab-pane active">
									<div class="table-responsive" style="margin-top: 10px">
										<table class="table table-bordered table-hover"
											style="TABLE-LAYOUT: fixed">
											<thead>
												<tr>
													<th style="text-align: center;width:180px">订单编号</th>
													<th style="text-align: center;width:20%">商品名称</th>
													<th style="text-align: center;width:20%">买家姓名</th>
													<th style="text-align: center;width:20%">退款金额</th>
													<th style="text-align: center;width:20%">退款原因</th>
													<th style="text-align: center;width:20%">拒绝原因</th>
													<th style="text-align: center;width:20%">状态</th>
												</tr>
											</thead>
											<tbody>
												<c:choose>
													<c:when test="${not empty orResultlist}">
														<c:forEach items="${orResultlist}" var="orders" varStatus="vs">
															<tr class="" style="height: 50px">
																<td style="width:10%;text-align: center;vertical-align: middle;">${orders.orderNumber}</td>
																<td style="width:10%;text-align: center;vertical-align: middle;">${orders.manageProducts.name}</td>
																<td style="width:20%;text-align: center;vertical-align: middle;">${orders.userAddress.userName}</td>
																<td style="width:10%;text-align: center;vertical-align: middle;"/>￥${orders.refundMessage.refundMoney}</td>
																<td style="width:20%;text-align: center;vertical-align: middle;">${orders.refundMessage.refundReason}</td>
																	<td
																	style="width:20%;text-align: center;vertical-align: middle;">${orders.noReason}</td>
																<td style="width:20%;text-align: center;vertical-align: middle;">
																	<span style="color: red">
																		已拒绝
																	</span>
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
											${os.page.pageStr }
										</div>
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
		window.location.href = "ordersreturninfo?typeid=${param.typeid}&orderid="
				+ id;

	}
	function fahuo(id) {
		bootbox
				.dialog({
					message : '<div style="margin-left:100px"><div>审核状态:<select id="wuliu" class="form-control" style="width:200px"><option value="1">审核通过</option><option value="2">审核不通过</option></select></div>'
							+ '<br><div>原因:<input type="text" class="form-control" style="width:200px" id="yundanhao"></div></div>',
					title : "退款审核",
					buttons : {
						main : {
							label : "审核!",
							className : "blue",
							callback : function() {
								$
										.ajax({
											url : '${RESOURCEDOMAIN}/system/shouhou/tuihuo',
											type : 'post',
											dataType : 'json',
											data : {
												orderid : id,
												shenhestatus : $(
														"#wuliu option:selected")
														.val(),
												shenhe : $("#yundanhao").val()
											},
											success : function(data) {
												if (data.stat == "1") {
													window.location.href = window.location.href;
												} else {
													alert(data.Message);
												}
											}
										})
							}
						}
					}
				});
	}
	function gotos(ty) {
		window.location.href = "${DOMAIN}/system/shouhou/returnOrder/"+ty;
	}

	function toexcel() {
		window
				.open("${RESOURCEDOMAIN}/system/orders/toexcel?typeid=${param.typeid}&from="
						+ $("#tfrom").val()
						+ "&tto="
						+ $("#tto").val()
						+ "&ty=${param.ty}");
	}
	$(function() {
		var party = "${ty}";
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
	src="${RESOURCEDOMAIN}/js/system/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/bootstrap-timepicker.min.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/moment.min.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/daterangepicker.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/bootstrap-datetimepicker.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/system/components-pickers.js"></script>
<jsp:include page="../foot.jsp"></jsp:include>
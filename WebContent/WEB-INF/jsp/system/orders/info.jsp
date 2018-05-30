<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			订单详情 <small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					订单管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 订单详情</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">

		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>订单信息
				</div>
			</div>
			<div class="portlet-body form">
				<div class="form-wizard">
					<div class="navbar steps">
						<div class="navbar-inner">
							<ul class="row-fluid nav nav-pills">
								<li class="span3 active" style="width: 19.6%"><a
									class="step" style="background-color: #fff;" href="#tab1">
										<span class="number">1</span> <span class="desc"
										style="font-size: 10pt"><i class="icon-ok"></i> 创建 <fmt:formatDate
												value="${orders.orderTime}" type="both"
												pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</a></li>
								<li
									class="span3 <c:if test="${orders.orderState==30||orders.orderState==40||orders.orderState==50||orders.orderState==60 ||orders.orderState==70||orders.orderState==80||orders.orderState==90}">active</c:if>"
									style="width: 19.6%"><a class="step"
									style="background-color: #fff;" href="#tab2"> <span
										class="number">2</span> <span class="desc"
										style="font-size: 10pt"><i class="icon-ok"></i> 付款 <fmt:formatDate
												value="${orders.applyTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</a></li>
								<li
									class="span3 <c:if test="${orders.orderState==40||orders.orderState==50||orders.orderState==60 ||orders.orderState==70||orders.orderState==80||orders.orderState==90}">active</c:if>"
									style="width: 19.6%"><a class="step"
									style="background-color: #fff;" href="#tab3"> <span
										class="number">3</span> <span class="desc"
										style="font-size: 10pt"><i class="icon-ok"></i> 发货 <fmt:formatDate
												value="${orders.goodsTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</a></li>
								<li
									class="span3 <c:if test="${orders.orderState==50||orders.orderState==60 ||orders.orderState==70||orders.orderState==80||orders.orderState==90}">active</c:if>"
									style="width: 19.6%"><a class="step"
									style="background-color: #fff;" href="#tab4"> <span
										class="number">4</span> <span class="desc"
										style="font-size: 10pt"><i class="icon-ok"></i> 确认收货<fmt:formatDate
												value="${orders.acceptTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</a></li>
								<li
									class="span3 <c:if test="${orders.orderState==60||orders.orderState==70||orders.orderState==80||orders.orderState==90}">active</c:if>"
									style="width: 19.6%"><a class="step"
									style="background-color: #fff;" href="javascript:void(0)">
										<span class="number">5</span> <span class="desc"
										style="font-size: 10pt"><i class="icon-ok"></i> 系统审核 <fmt:formatDate
												value="${orders.sysTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="progress progress-success progress-striped" id="bar">
						<div class="progress-bar"
							style="width: <c:choose>
									<c:when test="${ orders.orderState==20}">20%</c:when>
									<c:when test="${ orders.orderState==30}">40%</c:when>
									<c:when test="${ orders.orderState==40}">60%</c:when>
									<c:when test="${ orders.orderState==50}">80%</c:when>
									<c:when test="${ orders.orderState==60}">100%</c:when>
									<c:when test="${ orders.orderState==70}">100%</c:when>
									<c:when test="${ orders.orderState==80}">100%</c:when>
									<c:when test="${ orders.orderState==90}">100%</c:when>
									<c:when test="${ orders.orderState==10}">20%</c:when>
								</c:choose>;"></div>
					</div>
				</div>
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					action="savewxpay.html" novalidate="novalidate">
					<div class="form-body"
						style="margin-top: -20px; float: left; width: 50%">
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left; width: 100%">微信用户信息：
								${orders.webUser.userName} &nbsp; &nbsp; ${orders.webUser.phone}

						</div>
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left; width: 100%">订单金额： <c:if
									test="${orders.tabs==1}">0</c:if> <c:if
									test="${empty orders.tabs}">${orders.price}</c:if></label>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left; width: 100%">订单状态 ：<c:choose>
									<c:when test="${ orders.orderState==20}">待支付</c:when>
									<c:when test="${ orders.orderState==30}">待发货</c:when>
									<c:when test="${ orders.orderState==40}">待收货</c:when>
									<c:when test="${ orders.orderState==50}">已完成</c:when>
									<c:when test="${ orders.orderState==60}">退款处理中</c:when>
									<c:when test="${ orders.orderState==70}">退款已完成</c:when>
									<c:when test="${ orders.orderState==80}">退款未通过</c:when>
									<c:when test="${ orders.orderState==90}">商品已评价</c:when>
									<c:when test="${ orders.orderState==10}">用户已取消</c:when>
									<c:when test="${ orders.orderState==100}">系统自动取消</c:when>
								</c:choose>
							</label>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left; width: 100%">付款方式 ： <c:choose>
									<c:when test="${ empty orders.applyMethod}">暂无</c:when>
									<c:when test="${not empty orders.applyMethod}">
										<c:if test="${orders.applyMethod==0 }">微信支付
											</c:if>
										<c:if test="${orders.applyMethod==2 }">价格为0，无需支付
											</c:if>
										<c:if test="${orders.applyMethod==3 }">货到付款
											</c:if>
									</c:when>
								</c:choose>
							</label>

						</div>

						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left; width: 100%">订单编号：
								${orders.orderNumber} </label>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left; width: 100%">收货信息 ：
								${orders.userAddress.userName} &nbsp;
								${orders.userAddress.telPhone}
								&nbsp;${orders.userAddress.province }
								&nbsp;${orders.userAddress.city}&nbsp;${orders.userAddress.area}&nbsp;${orders.userAddress.addressDetails}
								&nbsp; &nbsp;&nbsp; &nbsp; </label>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left; width: 100%">买家留言 ：
								${orders.customerMessage}</label>
						</div>
					</div>

					<div style="clear: both"></div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>商品信息
				</div>
			</div>
			<div class="portlet-body ">
				<!-- BEGIN FORM-->
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>商品</th>
							<th>单价(元)</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders.products}" var="ods">
							<tr class="">

								<td><img alt=""
									<%-- src="/uploads/${ods.prodUri}" --%> width="50px">${ods.name }<%-- <c:if
										test="${not empty ods.prodSpecName }">[${ods.prodSpecName}]</c:if> --%>
								</td>
								<td style="vertical-align: middle;"><c:if
										test="${ods.tab==1}">0</c:if> <c:if test="${ods.tab!=1}">￥${ods.nowPrice }</c:if>

								</td>
								<td style="vertical-align: middle;">${orders.count }</td>
								<td style="vertical-align: middle;"><c:if
										test="${ods.tab==1}">0</c:if> <c:if test="${ods.tab!=1}">￥${orders.count*ods.nowPrice}</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div style="float: right">
					商品小计:
					<c:if test="${orders.tabs==1}">0</c:if>
					<c:if test="${empty orders.tabs}">${orders.price}</c:if>
				</div>
				<br> <br>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
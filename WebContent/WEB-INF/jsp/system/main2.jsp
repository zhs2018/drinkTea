<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />

<jsp:include page="top.jsp"></jsp:include>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			系统统计<small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					数据中心</a>
			<li><a href="javascript:void(0)"> </a></li>
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
					<i class="fa fa-reorder"></i>当前账户：
				</div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form1" method="post"
					action="savefocuson.html" novalidate="novalidate">
					<div class="form-body">
						<table class="table table-bordered table-hover">
							<tbody>
								<tr class="">
									<td>出售中得商品数：</td>
									<td style="color: red" class="countTongji4">${pOnCounts}</td>
									<td>下架商品数：</td>
									<td style="color: red" class="countTongji5">${pOutCounts}</td>
									<td>已取消订单数：</td>
									<td style="color: red" class="countTongji6">${CancelOrderCount}</td>
									<td>待付款订单数：</td>
									<td style="color: red" class="countTongji7">${HasOrderCount}</td>
								</tr>
								<tr class="">

									<td>待发货订单数：</td>
									<td style="color: red" class="countTongji8">${SendOrderCount}</td>
									<td>待收货订单数：</td>
									<td style="color: red" class="countTongji8">${AcceptOrderCount}</td>
									<td>待退货订单数：</td>
									<td style="color: red" class="countTongji9">${refundOrderCount}</td>
									<td>已完成订单数：</td>
									<td style="color: red" class="countTongji10">${FinishOrderCount}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>数据统计
				</div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form1" method="post"
					action="savefocuson.html" novalidate="novalidate">
					<div class="form-body">
						<table class="table table-bordered table-hover">
							<tbody>
								<tr class="">
									<td>总订单（笔）：</td>
									<td style="color: red" class="countTongji12">${AllOrderCount}</td>
									<td>总消费金额（元）：</td>
									<td style="color: red" class="countTongji12">￥${totalMoney}</td>

								</tr>
							</tbody>
						</table>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet-body form">
			请选择月份查询：<input size="25" type="text" id="sTime" name="sTime" value=""
				readonly class="form_datetime">
			<button onclick="getOK()">查询</button>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>

<script type="text/javascript">
$(function() {
	  $(".form_datetime").datetimepicker({format: 'yyyy-mm',autoclose: true,startView:3,minView:3,todayHighlight:true});
});
</script>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet box blue">
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<div class="form-horizontal" novalidate="novalidate" id="main"
					style="height:320px; width: 1650px"></div>
				<!-- ECharts单文件引入 -->
				<script src="${RESOURCEDOMAIN}/js/build/dist/echarts.js"></script>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="foot2.jsp"></jsp:include>
 <script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/datastatus.js"></script>
	<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/shine.js"></script>
	<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/dark.js"></script>

<script type="text/javascript">
      function getOK(){
    	  var sTime= $("#sTime").val();
    	  url="${RESOURCEDOMAIN}/system/getData";
    	  main=document.getElementById('main');
    	  getChartData(sTime,url,main);
      }
    </script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
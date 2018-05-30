<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<script type="text/javascript" src="${RESOURCEDOMAIN}/js/layer/layer.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/zclip/ZeroClipboard.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/jquery-qrcode-master/src/jquery.qrcode.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/jquery-qrcode-master/src/qrcode.js"></script>
<style type="text/css">
.aa {
	cursor: pointer;
}
</style>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			二维码 <small>管理</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					二维码管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 茶叶小包 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<form action="${DOMAIN}/system/qrcode/smallPackage" method="post">
		<div class="col-md-6" style="width: 100%">
			<!-- BEGIN SAMPLE TABLE PORTLET-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class=" fa fa-cogs"></i>信息 （总共有${su.page.totalResult }个二维码）
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
					<button class="btn green" type="button" onclick="toexcel()">
						<i class="fa fa-share"></i> 导出
					</button>


					<button class="btn purple" type="button" onclick="add();">
						<i class="fa fa-check"></i> 添加二维码
					</button>
					<br> <br>
					<div class="table-responsive" style="margin-top: 5px">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>二维码ID</th>
									<th>小包名称</th>
									<th>添加人</th>
									<th style="width: 300px">关联题号</th>
									<th style="width: 300px">添加时间</th>
									<th style="width: 400px">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty smallPackageList}">
										<c:forEach items="${smallPackageList}" var="one"
											varStatus="vs">
											<tr class="" style="vertical-align: middle;">
												<td style="vertical-align: middle;">${one.id}</td>
												<td style="vertical-align: middle;">${one.name}</td>
												<td style="vertical-align: middle;">${one.createName}</td>
												<td style="vertical-align: middle;">${one.questionNum}题</td>
												<td><fmt:formatDate value="${one.createTime}"
														pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td style="vertical-align: middle;"><a class="aa"
													href="${DOMAIN}/system/qrcode/editSmallPackage/${one.id}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
													<a class="aa" onclick="deleteSmallPackage('${one.id}');">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
													<a class="aa" onclick="openUrl('${one.id}');">生成二维码</a></td>
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
							${su.page.pageStr }
						</div>
					</div>
				</div>

			</div>

			<!-- END SAMPLE TABLE PORTLET-->
		</div>
	</form>
</div>

<!-- END PAGE CONTENT-->
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
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
<script>

function toexcel() {
	var time = 	$("#ttime").val();
	if(time!=null&&time!=""){
		window
		.open("${DOMAIN}/system/qrcode/toexcel?ttime="
				+ $("#ttime").val());
	}else{
		layer.alert("请输入导出订单的时间段");
	}

	}


	//生成二维码
	function openUrl(id) {
		showMessage(
				"<center><div id='qrcodeCanvasHide' style='color:red'><div id='qrcodeCanvas'></div></div></center>",
				"请用微信扫一扫");
		jQuery('#qrcodeCanvas').qrcode({
			text : "${DOMAIN_WWW}/answer/index?id=" + id
		});
	}

	//跳转到添加二维码的页面
	function add() {
		window.location.href = "${DOMAIN}/system/qrcode/addSmallPackage";
	}

	//删除小包二维码
	function deleteSmallPackage(id) {
		layer
				.confirm(
						"您确定删除该二维码吗？",
						{
							title : "删除二维码",
							btn : [ '确定删除', '留着吧' ]
						},
						function() {
							var sub = $("#form_config").serialize();
							$
									.ajax({
										url : "${DOMAIN}/system/qrcode/deleteSmallPackage/"
												+ id,
										type : "post",
										data : sub,
										dataType : "json",
										success : function(data) {
											if (data.status == 1) {
												layer.msg("删除成功", {
													icon : 1
												});
												setTimeout(
														function() {
															window.location.href = "${DOMAIN}/system/qrcode/smallPackage";
														}, 1000);
											} else if (data.status == 0) {
												layer.msg(data.Message);
											}
										},
										error : function(data) {
											layer.msg(data.Message);
										}
									})
						}, function() {
							layer.close();
						});
	}

	//弹框
	function showMessage(message, title) {
		bootbox.dialog({
			message : message,
			title : title,
			buttons : {
				main : {
					label : "确定",
					className : "blue",
					callback : function() {

					}
				}
			}
		});
	}
</script>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN }/assets/plugins/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN }/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />

<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			审核评论 <small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					评论管理</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 审核评论 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-6" style="width: 100%">
		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<form action="wait" method="post">
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
							<div class="table-responsive" style="margin-top: 10px">
								<table class="table table-bordered table-hover"
									style="TABLE-LAYOUT: fixed">
									<thead>
										<tr>
											<th style="text-align: center;">用户</th>
											<th style="text-align: center;">被评价商品名称</th>
											<th style="text-align: center;">评论内容</th>
											<th style="text-align: center;">评论时间</th>
											<th style="text-align: center;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${not empty objList}">
												<c:forEach items="${objList}" var="one" varStatus="vs">
													<tr class="">
														<td
															style="width:10%;text-align: center;vertical-align: middle;WORD-WRAP: break-word">${one.wuName}
														</td>
														<td
															style="width:10%;text-align: center;vertical-align: middle;WORD-WRAP: break-word">${one.mpName}</td>
														<td
															style="width:10%;text-align: center;vertical-align: middle;WORD-WRAP: break-word">${one.content}</td>
														<td style="width:10%;text-align: center;vertical-align: middle;WORD-WRAP: break-word"
														><fmt:formatDate value="${one.evaluateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
														<td
															style="width:10%;text-align: center;vertical-align: middle;">
															<a class="btn big   green" style="margin-top: 10px"
															onclick="checkout(${one.id})" href="javascript:void(0)">
																<i class=""></i> 审核
														</a> <br>
														</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr class="main_info">
													<td colspan="4">没有相关数据</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
								<div class="page_and_btn">
									<div></div>
									${ep.page.pageStr }
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
	function checkout(id) {
		bootbox.dialog({
					message : '<div style="margin-left:100px"><div>审核状态:<select id="wuliu" class="form-control" style="width:200px"><option value="1">审核通过</option><option value="2">审核不通过</option></select></div>'
							+ '</div>',
					title : "评论审核",
					buttons : {
						main : {
							label : "审核!",
							className : "blue",
							callback : function() {
								$.ajax({
											url : '${DOMAIN}/system/evaluate/checkout',
											type : 'post',
											dataType : 'json',
											data : {
												"id" : id,
												"status" : $("#wuliu option:selected").val()
											},
											success : function(data) {
												if (data.status == "1") {
													window.location.href = window.location.href;
												} else {
													layer.msg("程序出错了，请联系程序员小哥");
												}
											}
										})
							}
						}
					}
				});
	}
</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
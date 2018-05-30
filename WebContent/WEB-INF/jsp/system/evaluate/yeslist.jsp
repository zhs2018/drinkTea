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
			已通过列表 <small></small>
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
		<form action="yeslist.html" method="post">
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

<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
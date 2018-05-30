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
			红包提现说明列表<small>信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					提现说明 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 提现说明 </a></li>
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
								<th>标题</th>
								<th>说明</th>
								<th>二维码</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty cashDesc}">
                           <c:forEach items="${cashDesc}" var="cashDesc" varStatus="vs">
										<tr>
											<td>1</td>
											<td>${cashDesc.cashTitle}</td>
											<td>${cashDesc.cashDesc}</td>
											<td><img src="${DOMAIN}${cashDesc.cashImage}"  style="width:200px;hight:200px;"></td>
											<td><a class="btn default btn-xs purple"
												onclick="editDesc();"> <i class="fa fa-edit"></i>
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
	function editDesc() {
		window.location.href = "${DOMAIN}/system/cashdesc/editDesc";
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
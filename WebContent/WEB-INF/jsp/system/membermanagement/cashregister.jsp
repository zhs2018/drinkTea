<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			会员提现列表 <small>会员提现信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					会员提现记录 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)">会员提现列表 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
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
				<form action="${DOMAIN}/system/cashmember/cashRecordList" method="post">
					<div class="col-md-4" style="width: 700px;">
						<div class="controls">
							<div class="input-prepend input-group">
								<input type="text" style="width: 280px; margin-left: 340px"
									id="userName" name="userName" placeholder="会员昵称"
									class="form-control" value="${userName}" class="span4">
							</div>
						</div>
					</div>
					<button class="btn purple" type="submit" />
					<i class="fa fa-check"></i> 查找
					</button>
				</form>
				<br> <br>
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>微信昵称</th>
								<th>微信头像</th>
								<th>真实姓名</th>
								<th>手机号</th>
								<th>剩余红包金额</th>
								<th>性别</th>
								<th>提现钱数</th>
								<th>提现时间</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${!empty cashRecordList}">
									<c:forEach items="${cashRecordList}" var="cash" varStatus="vs">
										<tr>
											<td>${vs.index+1}</td>
											<td>${cash.webUser.userName}</td>
											<td><img src="${cash.webUser.image}" width="100px"
												height="100px" /></td>
											<td><c:if
													test="${!empty cash.webUser.name}">${cash.webUser.name}</c:if></td>
											<td><c:if test="${!empty cash.webUser.phone}">${cash.webUser.phone}</c:if></td>
											<td>
											<c:if test="${empty cash.webUser.restMoney}">0</c:if>
											<c:if test="${!empty cash.webUser.restMoney}">${cash.webUser.restMoney}</c:if>
											</td>
											<td><c:if test="${cash.webUser.sex == 1}">男</c:if> <c:if
													test="${cash.webUser.sex == 2}">女</c:if></td>
													<td> ${cash.cashMoney}</td>
											<td><ftm:formatDate value="${cash.cashTime}" pattern="yyyy-MM-dd HH:mm:ss" ></ftm:formatDate></td>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="9">没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<div class="page_and_btn">
						<div></div>
						${page.page.pageStr }
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/zclip/ZeroClipboard.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/jquery-qrcode-master/src/jquery.qrcode.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/jquery-qrcode-master/src/qrcode.js"></script>
<script>
	function editProduct(id) {
		window.location.href = "commodityController?id=" + id
				+ "&typeid=${param.typeid}";
	}
	function deleteUsers(id) {
		window.location.href = "${DOMAIN}/system/commodity/editUsers/" + id;
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
</div>
<!-- END PAGE CONTENT         success:function(data){
    			 if(data.status=="1"){
    				 data.list;
    				window.location.href="${DOMAIN}/system/user/memberUser";
    			}-->
<jsp:include page="../foot.jsp"></jsp:include>
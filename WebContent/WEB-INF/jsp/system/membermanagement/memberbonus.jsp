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
			会员分红列表 <small>会员分红信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					会员分红列表 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)">会员分红列表 </a></li>
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
				<form action="${DOMAIN}/system/user/bonus" method="post">
					<div class="col-md-4" style="width: 700px;">
						<div class="controls">
							<div class="input-prepend input-group">
								<input type="text" style="width: 280px; margin-left: 340px"
									id="userName" name="userName" placeholder="微信昵称"
									class="form-control" value="${num}" class="span4">
							</div>
						</div>
					</div>
					<input type="hidden" name="userId" value="${userId}"> <input
						type="hidden" name="time" value="${time}">
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
								<th>红包总金额</th>
								<th>剩余金额</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${!empty list}">
									<c:forEach items="${list}" var="user" varStatus="vs">
										<tr>
											<td>${vs.index+1}</td>
											<td>${user.userName}</td>
											<td><img src="${user.image}" width="100px"
												height="100px" /></td>
											<%-- <td><c:if test="${empty user.moneyX}">0</c:if> <c:if
													test="${!empty user.moneyX}">${user.moneyX}</c:if></td> --%>
											<%-- <td><c:if test="${empty user.moneyY}">0</c:if> <c:if
													test="${!empty user.moneyY}">${user.moneyY}</c:if></td> --%>
											<td><c:if test="${empty user.moneyXY}">0</c:if> <c:if
													test="${!empty user.moneyXY}">${user.moneyXY}</c:if></td>
											<td><c:if test="${empty user.restMoney}">0</c:if> <c:if
													test="${!empty user.restMoney}">${user.restMoney}</c:if></td>
											<td><a class="btn default btn-xs red" onclick="seePeople(${user.id})"> <i
														class="fa fa-edit"></i>查看共享分红人员
													</a></td>
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

function seePeople(id){
	window.location.href = "${DOMIAN}/drinkTea/system/user/seePeople/"+id;
}
</script>
<!-- END PAGE CONTENT-->
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
</div>
<jsp:include page="../foot.jsp"></jsp:include>
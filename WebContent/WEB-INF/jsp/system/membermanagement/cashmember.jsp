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
			会员提现列表 <small>会员提现信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					会员提现列表 </a> <i class="fa fa-angle-right"></i></li>
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
				<form action="${DOMAIN}/system/cashmember/cashlist" method="post">
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
								<th>已提现红包数</th>
								<th>性别</th>
                                <th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${!empty userList}">
									<c:forEach items="${userList}" var="user" varStatus="vs">
										<tr>
											<td>${vs.index+1}</td>
											<td>${user.userName}</td>
											<td><img src="${user.image}" width="100px"
												height="100px" /></td>
											<td><c:if test="${!empty user.name}">${user.name}</c:if></td>
											<td><c:if test="${!empty user.phone}">${user.phone}</c:if></td>
											<td><c:if test="${empty user.restMoney}">0元</c:if> <c:if
													test="${!empty user.restMoney}">${user.restMoney}</c:if></td>
												<td><c:if test="${empty user.cashMoney}">0元</c:if> <c:if
													test="${!empty user.cashMoney}">${user.cashMoney}</c:if></td>
											<td><c:if test="${user.sex == 1}">男</c:if> <c:if
													test="${user.sex == 2}">女</c:if></td>
                                            <td><a class="btn default btn-xs purple"
												href="javascript:void(0)"
												onclick="editRed(${user.id})"> <i
													class="fa fa-edit"></i> 红包提现
											</a>

                                            </td>
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
	function editRed(id) {
		window.location.href = "${DOMAIN}/system/cashmember/moneyRegister?id="+id;
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
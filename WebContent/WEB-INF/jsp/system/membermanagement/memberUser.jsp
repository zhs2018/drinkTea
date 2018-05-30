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
			会员列表 <small>会员信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					会员列表 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)">会员列表 </a></li>
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
				<form action="${DOMAIN}/system/user/memberUser" method="post">
					<div class="col-md-4" style="width: 700px;">
						<div class="controls">
							<div class="input-prepend input-group">
								<input type="text" style="width: 280px; margin-left: 340px"
									id="userName" name="num" placeholder="员工工号"
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
								<th>免费领取数量</th>
								<th>剩余抽奖次数</th>
								<th>订单总金额</th>
								<th>性别</th>
								<th>年龄段</th>
								<th>绑定工号</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${!empty users}">
									<c:forEach items="${users}" var="user" varStatus="vs">
										<tr>
											<td>${vs.index+1}</td>
											<td>${user.userName}</td>
											<td><img src="${user.image}" width="100px"
												height="100px" /></td>
											<td><c:if test="${empty user.freeRec}">0</c:if> <c:if
													test="${!empty user.freeRec}">${user.freeRec}</c:if></td>
											<td><c:if test="${empty user.drawCount}">0</c:if> <c:if
													test="${!empty user.drawCount}">${user.drawCount}</c:if></td>
													<td><c:if test="${empty user.money}">0</c:if> <c:if
													test="${!empty user.money}">${user.money}</c:if></td>
											<td><c:if test="${user.sex == 1}">男</c:if> <c:if
													test="${user.sex == 2}">女</c:if></td>
											<td><c:if test="${user.age == 1}">小于18岁</c:if> <c:if
													test="${user.age == 2}">18岁~24岁</c:if> <c:if
													test="${user.age == 3}">25岁~30岁</c:if> <c:if
													test="${user.age == 4}">30岁~40岁</c:if> <c:if
													test="${user.age == 5}">40岁~50岁</c:if> <c:if
													test="${user.age == 6}">50岁~60岁</c:if> <c:if
													test="${user.age == 7}">大于60岁</c:if></td>
											<td>${user.sysUser.jobNumber}</td>
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
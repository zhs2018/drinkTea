<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			中奖信息列表 <small>中奖信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					中奖管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 中奖信息列表 </a></li>
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
								<th>序号</th>
								<th>微信名</th>
								<th>联系人</th>
								<th>联系方式</th>
								<th>详细地址</th>
								<th>奖品名称</th>
								<th>奖品图片</th>
								<th>中奖时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty wipList}">
									<c:forEach items="${wipList}" var="wip" varStatus="vs">

										<tr class="">
											<td>${vs.index+1}</td>
											<td>${wip.webUser.userName}</td>
											<td>${wip.userAddress.userName}</td>
											<td>${wip.userAddress.telPhone}</td>
											<td>${wip.userAddress.province}&nbsp;${wip.userAddress.city}&nbsp;${wip.userAddress.area}<br>
											${wip.userAddress.addressDetails}</td>
											<td>${wip.prizePro.prizeName}</td>
											<td><img
												src="${RESOURCEDOMAIN}/${wip.prizePro.prizeImg}"
												width="120px" /></td>
											<td><fmt:formatDate value="${wip.time}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><c:if test="${wip.sendSign==1}">
													<a id="${wip.id }" class="btn default btn-xs red"
														onclick="sendWip(${wip.id})"> <i class=" fa fa-edit"></i>
														标记为奖品已送出
													</a>
												</c:if> <c:if test="${wip.sendSign==2}">
													<a class="btn default btn-xs red"> <i
														class="fa fa-edit"></i>奖品已送出
													</a>
												</c:if> <a id="${wip.id }" class="btn default btn-xs purple"
												onclick="delWip(${wip.id})"> <i class="fa fa-trash-o"></i>
													删除
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
	function editProduct(id) {
		window.location.href = "getone?id=" + id;
				 + "&typeid=${param.typeid}"
	}
	function delWip(id){
		layer.confirm("您确定删除该角色吗？",{
			title:"删除角色",
			btn: ['确定删除','留着吧']
		},
		function(){
			window.location.href = "${DOMIAN}/drinkTea/system/prizes/del/" + id ;
		},
		function(){
			layer.close();
		}
		)
	}
	function editProduct(id) {
		window.location.href = "getone?id=" + id;
				 + "&typeid=${param.typeid}"
	}
	function sendWip(id){
		layer.confirm("您确定标记为已送出吗？",{
			title:"确定送出",
			btn: ['确定','留着吧']
		},
		function(){
			window.location.href = "${DOMIAN}/drinkTea/system/prizes/sendWip/" + id ;
		},
		function(){
			layer.close();
		}
		)
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>

<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
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
			公益列表 <small>公益信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					公益列表 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 公益列表 </a></li>
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
					<i class=" fa fa-cogs"></i>消息
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th style="width: 10%">编号</th>
								<th style="width: 10%">公益标题</th>
								<th style="width: 35%">公益简介</th>
								<th style="width: 15%">发布时间</th>
								<th style="width: 15%">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty ObjList}">
									<td>1</td>
									<td>${ObjList.title}</td>
									<td>${ObjList.intro}</td>
									<td><fmt:formatDate value="${ObjList.time}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td><c:choose>
											<c:when test="${ObjList.sign==1}">
												<a class="btn big blue" href="javascript:void(0)"
													onclick="change(${ObjList.id})"> <i class=""></i>发布
												</a>
											</c:when>
											<c:when test="${ObjList.sign==2}">
												<a class="btn big red" href="javascript:void(0)"
													onclick="state(${ObjList.id})"> <i class=""></i>取消发布
												</a>
											</c:when>
										</c:choose> <a class="btn big blue" href="javascript:void(0)"
										onclick="changeState(${ObjList.id})"> <i class=""></i>编辑
									</a></td>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="7">没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<div class="page_and_btn">${obj.page.pageStr }</div>
				</div>
			</div>
		</div>
		<!-- END SAMPLE TABLE PORTLET-->
	</div>
</div>
<script>
function change(id){
   window.location.href="${DOMAIN}/system/welfare/change/"+id;
}

function state(id){
	   window.location.href="${DOMAIN}/system/welfare/state?id="+id;
	}
function changeState(id){
	   window.location.href="${DOMAIN}/system/welfare/changeState?id="+id;
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			意见反馈列表 <small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					意见反馈管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 意见反馈列表 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-6" style="width: 100%">
		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<form action="idea" method="post">
			<input type="hidden" value="${param.ty}" name="ty">
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
							<ul class="nav nav-tabs">
								<li class="gt-1"><a data-toggle="tab" href="#tab_1_1"
									onclick="gotos('-1')">所有意见</a></li>
								<li class="gt20"><a data-toggle="tab" href="#tab_1_2"
									onclick="gotos('20')">未读</a></li>
								<li class="gt30"><a data-toggle="tab" href="#tab_1_3"
									onclick="gotos('30')">已读</a></li>
							</ul>
							<div class="tab-content">
								<div id="tab_1_1" class="tab-pane active">
									<div class="table-responsive" style="margin-top: 10px">
										<table class="table table-bordered table-hover"
											style="TABLE-LAYOUT: fixed">
											<thead>
												<tr>
													<th style="text-align: center; width: 13%">序号</th>
													<th style="text-align: center; width: 13%">用户名</th>
													<th style="text-align: center; width: 15%">联系方式</th>
													<th style="text-align: center; width: 30%">意见内容</th>
													<th style="text-align: center; width: 15%">状态</th>
													<th style="text-align: center; width: 15%">操作</th>
												</tr>
											</thead>
											<tbody>
												<c:choose>
													<c:when test="${not empty objList}">
														<c:forEach items="${objList}" var="idea" varStatus="vs">
															<tr>
																<td
																	style="width: 13%; text-align: center; vertical-align: middle;">${vs.index+1}
																</td>
																<td
																	style="width: 13%; text-align: center; vertical-align: middle;">${idea.userName}</td>
																<td
																	style="width: 15%; text-align: center; vertical-align: middle;">${idea.phone}</td>
																<td
																	style="width: 30%; text-align: center; vertical-align: middle;">${idea.contents}</td>
																<td
																	style="width: 15%; text-align: center; vertical-align: middle;">
																	<span style="color: red"><c:choose>
																			<c:when test="${idea.state==20}">未读</c:when>
																			<c:when test="${idea.state==30}">已读</c:when>
																		</c:choose></span>
																</td>
																<td
																	style="width: 15%; text-align: center; vertical-align: middle;">
																	<c:choose>
																		<c:when test="${idea.state==30}">
																			<a class="btn big red" href="javascript:void(0)"
																				onclick="deleteIdea(${idea.id})"> <i class=""></i>删除
																			</a>
																			<i class=""></i>
																		</c:when>

																		<c:when test="${idea.state==20}">
																			<a class="btn big blue" href="javascript:void(0)"
																				onclick="changeState(${idea.id})"> <i class=""></i>标记为已读
																			</a>
																		</c:when>
																	</c:choose>
																</td>
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
										<div class="page_and_btn">${obj.page.pageStr}</div>
									</div>
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
	function changeState(id) {
		$.ajax({
			url:"changeState",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				if(data.status=="1"){
					window.location.href="${DOMAIN}/system/ideaTicking/idea";
				}else{
					layer.confirm("删除失败");
				}
			},
			error:function(data){
				layer.confirm("删除失败");
			}
		})

	}

function gotos(ty) {
	 window.location.href = "ideaTickingState?typeid=${param.typeid}&ty=" + ty ;
	}

function deleteIdea(id){
	$.ajax({
		url:"deleteIdeaTicking",
		type:"post",
		data:{"id":id},
		dataType:"json",
		success:function(data){
			if(data.status=="1"){
				window.location.href="ideaTickingState?ty="+data.ty;
			}else{
				layer.confirm("删除失败");
			}
		},
		error:function(data){
			layer.confirm("删除失败");
		}
	})

}


	$(function() {
		var party = "${param.ty}";
		if (party == "") {
			$(".gt-1").addClass("active");
		} else {
			$(".gt" + party).addClass("active");
		}
		ComponentsPickers.init();

	})

</script>
<!-- END PAGE CONTENT-->
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
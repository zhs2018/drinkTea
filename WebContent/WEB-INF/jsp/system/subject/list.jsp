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
			题目列表 <small>题目信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					题库管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 扫码答题列表 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="list" method="post">
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
					<div class="col-md-4" style="width: 700px;">
						<div class="input-prepend input-group">
							<input type="text" style="width: 250px;" id="name" name="name"
								placeholder="题目包含关键字" class="form-control"
								value="${subInfo.name }" class="span4">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn purple" type="submit">
								<i class="fa fa-check"></i> 查找
							</button>
						</div>
					</div>
					<br> <br> <br>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>题目</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty subList}">
										<c:forEach items="${subList}" var="sub" varStatus="vs">

											<tr class="">
												<td>${vs.index +1}</td>
												<td><a class="purple" href="javascript:void(0)"
													onclick="editSubject(${sub.id})"> ${sub.name}</a></td>
												<td style="width: 500px"><a
													class="btn default btn-xs purple" href="javascript:void(0)"
													onclick="editSubject(${sub.id})"> <i class="fa fa-edit"></i>
														编辑
												</a> &nbsp; &nbsp;<a id="${sub.id }"
													class="btn default btn-xs red remove" title="题目"
													href="javascript:void(0)"> <i class="fa fa-trash-o"></i>
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
							${subInfo.page.pageStr }
						</div>
					</div>
				</div>
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
		</div>
	</div>
</form>
<script>
	function editSubject(id) {
		window.location.href = "edit?id=" + id;
	}
	var result = "${result}";
	if(result == "1"){
		bootbox.dialog({
			 message: "${message}",
			 title: "提示",
			 buttons: {
			 main: {
			 label: "确定!",
			 className: "blue",
			 callback: function() {
			 }
			 }
			 }
		 });
	}
	$(function(){
		$(".remove").click(function(){
			var delId=$(this).attr("id");
			var title=$(this).attr("title");
			 bootbox.dialog({
				 message: "确定要删除这个"+title+"?",
				 title: "提示",
				 buttons: {
				 success: {
				 label: "取消!",
				 className: "white",
				 callback: function() {
				 }
				 },
				 main: {
				 label: "确定!",
				 className: "blue",
				 callback: function() {
					 var DATA = '';
						DATA = '{"CTIDS":"'+delId+'","TYPE":"${type}","EXCLUSION":"false"}';
						$.ajax({
							url: "delone",
							type: "post",
							data: {
								"DATA": DATA
							},
							success: function(data){
								if (eval("("+data+")").result) {
									bootbox.dialog({
										 message: "成功删除",
										 title: "提示",
										 buttons: {
										 main: {
										 label: "确定!",
										 className: "blue",
										 callback: function() {
											 window.location.href=window.location.href;
										 }
										 }
										 }
									 });

								}
							}
						});
				 }
				 }
				 }
			 });
		});
	})
</script>
<!-- END PAGE CONTENT-->
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
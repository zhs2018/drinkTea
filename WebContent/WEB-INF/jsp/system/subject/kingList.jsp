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
			<li><a href="javascript:void(0)"> 茶王争霸列表 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="kingList" method="post">
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
					<div class="col-md-4" style="width: 700px;"></div>
					<br> <br> <br>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>题目</th>
									<th>发布时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty subList}">
										<input type="text" id="time" value="${sub}" hidden="true">
										<input type="text" id="time1" value="${sub1}" hidden="true">
										<input type="text" id="time2" value="${sub2}" hidden="true">
										<input type="text" id="tm" value="${s}" hidden="true">
										<input type="text" id="message" value="${message}"
											hidden="true">
										<c:forEach items="${subList}" var="sub" varStatus="vs">
											<tr class="">
												<td>${vs.index +1}</td>
												<td><img
													src="${RESOURCEDOMAIN}${fn:replace(sub.img,'.','_800_600.')}"
													width="200px" /></td>
												<td>${sub.createTime}</td>
												<td style="width: 500px"><c:if
														test="${empty sub.answer}">
														<a class="btn default btn-xs purple"
															href="javascript:void(0)"
															onclick="editSubject(${sub.id})"> <i
															class="fa fa-edit"></i>公布答案 <%--  <c:if test="${!empty sub.answer}">编辑</c:if> --%>

														</a>
													</c:if> &nbsp; &nbsp;<a id="${sub.id }"
													class="btn default btn-xs red remove" title="题目"
													href="javascript:void(0)"> <i class="fa fa-trash-o"></i>
														删除
												</a>&nbsp; &nbsp; <a class="btn default btn-xs blue"
													href="javascript:void(0)" onclick="editSub(${sub.id})">
														<i class="fa fa-edit"></i>编辑
												</a> <c:if test="${empty sub.subNumber}">
														<a class="btn default btn-xs red"
															href="javascript:void(0)" onclick="sendRed(${sub.id})">
															<i class="fa fa-edit"></i>确定发红包

														</a>
													</c:if></td>
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
		var myDate = new Date();
		var t=myDate.getHours()//获取当前小时数(0-23)
		var t1=myDate.getMinutes();
		var t2=myDate.getSeconds();
		var time= $("#time").val();
		var time1= $("#time1").val();
		var time2= $("#time2").val();
		var tm = $("#tm").val();
		if(t<time){
			alert("请在"+tm+"之后公布答案");
		}else if(t==time){
			if(t1<time1){
				alert("请在"+tm+"之后公布答案");
			}else{
				window.location.href = "editKing?id=" + id;
			}
			}else{
				window.location.href = "editKing?id=" + id;
		}
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
						DATA = '{"CTIDS":"'+delId+'","TYPE":"1","EXCLUSION":"false"}';
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

  function editSub(id){
		var current=$("#cur").html();
		window.location.href = "editorSub?id="+id+"&cur="+current;
	}

function sendRed(id){
	window.location.href = "sendRed?id="+id;
}

$(function(){
	var message = $("#message").val();
	if(message!=null&&message!=""){
		alert(message);g
	}
})
</script>
<!-- END PAGE CONTENT-->
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
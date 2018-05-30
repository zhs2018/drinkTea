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
			奖品列表 <small>奖品分类信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					奖品管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 奖品列表 </a></li>
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
								<th>奖品名称</th>
								<th>奖品类别</th>
								<th>奖品图片</th>
								<th>奖品数量</th>
								<th>奖品剩余数量</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty objList}">
									<c:forEach items="${objList}" var="product" varStatus="vs">

										<tr class="">
											<td>${product.prizeName}</td>
											<td>${product.prizeType}</td>
											<td><img src="${RESOURCEDOMAIN}/${product.prizeImg}"
												width="120px" /></td>
											<td>${product.prizeNum}</td>
											<td>${product.restNum}</td>
											<td>
												<%-- <a class="btn default btn-xs purple"
												href="javascript:void(0)"
												onclick="editProduct(${product.id})"> <i
													class="fa fa-edit"></i> 查看获奖人
											</a> &nbsp; &nbsp; --%> <a class="btn default btn-xs purple"
												href="javascript:void(0)"
												onclick="editProduct(${product.id})"> <i
													class="fa fa-edit"></i> 编辑
											</a> &nbsp; &nbsp; <a id="${product.id }"
												class="btn default btn-xs purple"
												onclick="delProduct(${product.id})"> <i
													class="fa fa-trash-o"></i> 删除
											</a>
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
	function delProduct(id){
		layer.confirm("您确定删除该角色吗？",{
			title:"删除角色",
			btn: ['确定删除','留着吧']
		},
		function(){
			window.location.href = "deloned/" + id ;
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
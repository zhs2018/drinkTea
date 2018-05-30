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
			选项列表 <small>类型选项信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					分类管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 分类列表 </a></li>
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
				<button class="btn purple" type="button" onclick="add();">
					<i class="fa fa-check"></i> 添加类型
				</button>
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>分类名称</th>
								<th>录入时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty objList}">
									<c:forEach items="${objList}" var="product" varStatus="vs">
										<tr class="">
											<td>${vs.index}</td>
											<td>${product.typeName}</td>
											<%-- <td>${product.typeSort}</td>
											<td><img src="${RESOURCEDOMAIN}${product.typeListImg}" width="120px"/></td> --%>
											<td>${fn:substring(product.addTime,0,19)}</td>
											<td><a class="btn default btn-xs purple"
												href="javascript:void(0)"
												onclick="editProduct(${product.id})"> <i
													class="fa fa-edit"></i> 编辑
											</a> &nbsp; &nbsp; <a id="${product.id }"
												class="btn default btn-xs purple" title="类型"
												href="javascript:void(0)"
												onclick="delProduct(${product.id})"> <i
													class="fa fa-trash-o"></i> 删除
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
					<div class="page_and_btn">${obj.page.pageStr }</div>
				</div>
			</div>
		</div>
		<!-- END SAMPLE TABLE PORTLET-->
	</div>
</div>
<script>

function add(){
	window.location.href = "${DOAMIN}/drinkTea/system/catalog/info";
}

	function editProduct(id) {
	var current=$("#cur").html();
	window.location.href = "getone?id="+id+"&cur="+current;

	}
	function delProduct(id){
		layer.confirm("您确定删除该角色吗？",{
			title:"删除角色",
			btn: ['确定删除','留着吧']
		},
		function(){
			window.location.href = "delones/" + id ;
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
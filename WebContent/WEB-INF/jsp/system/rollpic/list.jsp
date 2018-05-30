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
			轮播图列表 <small>商品轮播图信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					轮播图管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 轮播图列表 </a></li>
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
								<th>名称</th>
								<th>排序</th>
								<th>链接</th>
								<th>图片</th>
								<th>录入时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty objList}">
									<c:forEach items="${objList}" var="product" varStatus="vs">

										<tr class="">
											<td>${product.title}</td>
											<td>${product.sorts}</td>
											<td>${product.linkUrl}</td>
											<td><img src="${RESOURCEDOMAIN}${fn:replace(product.picUrl,'.','_800_600.')}"
												width="200px" /></td>
											<td>${fn:substring(product.addtime,0,19)}</td>
											<td><a class="btn default btn-xs purple"
												href="javascript:void(0)"
												onclick="editProduct(${product.id})"> <i
													class="fa fa-edit"></i> 编辑
											</a> &nbsp; &nbsp;<a id="${product.id }"
												class="btn default btn-xs purple"
												title="轮播图" href="javascript:void(0)"
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
		window.location.href = "getone?id=" + id ;
	}
	function delProduct(id){
		layer.confirm("您确定删除该角色吗？",{
			title:"删除角色",
			btn: ['确定删除','留着吧']
		},
		function(){
			window.location.href = "delone/" + id ;
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
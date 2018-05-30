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
			关于我们 <small>信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					关于列表 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)">列表 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="myAbout" method="post">
	<div class="row">
		<div class="col-md-6" style="width: 100%">
			<!-- BEGIN SAMPLE TABLE PORTLET-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class=" fa fa-cogs"></i>关于我们
					</div>
				</div>
				<div class="portlet-body">
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>标题</th>
									<th>商品图片</th>
									<th>字段1</th>
									<th>字段2</th>
									<th>字段3</th>
									<th>字段4</th>

								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty list}">
										<c:forEach items="${list}" var="product" varStatus="vs">

											<tr class="">

												<a class="purple" href="javascript:void(0)"
													onclick="editProduct(${product.id})"></a>
												<td>${product.id}</td>
												<td>${product.headline}</td>
												<td><img src="${RESOURCEDOMAIN}/${product.picture}"
													width="120px" /></td>
												<td>${product.fielda}</td>
												<td>${product.fieldb}</td>
												<td>${product.fieldc}</td>
												<td>${product.content}</td>

												<td><a class="btn default btn-xs purple"
													href="javascript:void(0)" onclick="update(${product.id})">
														<i class="fa fa-edit"></i> 编辑
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
							${obj.page.pageStr}
						</div>
					</div>
				</div>
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
		</div>
	</div>
</form>

<script type="text/javascript"
	src="${SHOPDOMAIN}/js/system/zclip/ZeroClipboard.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/js/system/jquery-qrcode-master/src/jquery.qrcode.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/js/system/jquery-qrcode-master/src/qrcode.js"></script>
<script>

    function update(id){

    	window.location.href="${DOMAIN}/system/commodity/update/"+id;
    }
    function addziduan(){

    	window.location.href="${DOMAIN}/system/commodity/addziduan";
    }
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/js/system/listContact.js"></script>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
table {
	text-align: center
}
</style>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			抽奖商品列表 <small>抽奖商品信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					抽奖商品管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 抽奖商品列表 </a></li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->


<form action="commodit" method="post">
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
					<br> <br>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>名称</th>
									<th>商品价格</th>
									<th>剩余数量(件)</th>
									<th>商品简介</th>
									<th>商品图片</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty manageList}">
										<c:forEach items="${manageList}" var="product" varStatus="vs">
											<tr class="">
												<td>${vs.index+1}</td>
												<td>${product.name}</td>
												<td>${product.nowPrice}元</td>
												<td>${product.restGoods}</td>
												<td>${product.introduce}</td>
												<td><img src="${RESOURCEDOMAIN}/${product.picture}"
													width="100px" /></td>
												<td><a class="btn default btn-xs purple" title="商品"
													href="javascript:void(0)" onclick="editor(${product.id})">
														<i class="fa fa-edit"></i> 编辑
												</a> &nbsp; &nbsp;<a id="${product.id}"
													class="btn default btn-xs red " title="商品"
													href="javascript:void(0)" onclick="del(${product.id})">
														<i class="fa fa-trash-o"></i> 删除
												</a></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr class="main_info">
											<td colspan="9">没有相关数据</td>
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
</form>

<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/zclip/ZeroClipboard.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/jquery-qrcode-master/src/jquery.qrcode.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/system/jquery-qrcode-master/src/qrcode.js"></script>
<script>


    function editor(id){
    	window.location.href="${DOMAIN}/system/prizes/product/editor/"+id;
    }

	  function del(id){

		  layer.confirm("确定删除该商品吗",{
				title:"删除角色",
				btn: ['确定删除','取消删除'],
				},
				function(){
					window.location.href="${DOMAIN}/system/prizes/product/del/"+id;
				},
				function(){
					layer.close();
				})
	  }
</script>
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>

<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
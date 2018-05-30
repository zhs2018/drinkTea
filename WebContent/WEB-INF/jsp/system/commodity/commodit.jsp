
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			商品列表 <small>商品信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					商品管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 商品列表 </a></li>

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
					<div class="col-md-4" style="width: 700px;">
						<div class="controls">
							<div class="input-prepend input-group">
								<input type="text" style="width: 300px; margin-left: 300px"
									id="name" name="name" placeholder="商品名称" class="form-control"
									value="${man.name }" class="span3">
							</div>
						</div>
					</div>

					<button class="btn purple" type="submit">
						<i class="fa fa-check"></i> 查找
					</button>
					<br> <br>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>名称</th>
									<th>商品价格</th>
									<th>商品进价</th>
									<th>商品重量</th>
									<th>商品货号</th>
									<th>库存(低于50为红色)</th>
									<th>商品图片</th>
									<th>商品类型</th>
									<th>操作</th>

								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty commodit}">
										<c:forEach items="${commodit}" var="product" varStatus="vs">
											<tr class="">
												<td>${vs.index+1}</td>
												<td>${product.name}</td>
												<td><c:if test="${empty product.nowPrice}">0元</c:if> <c:if
														test="${!empty product.nowPrice}">${product.nowPrice}元</c:if>
												</td>
												<td>${product.cost}元</td>
												<td>${product.type}</td>

												<td>${product.specifications}</td>
												<td><c:if test="${product.restGoods<50}">
														<span style="color: red;">${product.restGoods}</span>
													</c:if> <c:if test="${product.restGoods>=50}">${product.restGoods}</c:if></td>
												<td><img src="${RESOURCEDOMAIN}/${product.picture}"
													width="100px" /></td>
												<td style="color: red;">
													<%-- <input type="text" value="${cur}" name="cur" hidden="true"> --%>
													<c:if test="${product.tab==1}">免费商品</c:if> <c:if
														test="${product.tab==2}">普通商品</c:if> <c:if
														test="${product.tab==3}">扫码答题商品</c:if>
												</td>
												<td><a class="btn default btn-xs purple" title="商品"
													href="javascript:void(0)"
													onclick="deleteUsers(${product.id})"> <i
														class="fa fa-edit"></i> 编辑
												</a> &nbsp; &nbsp;<a id="${product.id}"
													class="btn default btn-xs red " title="商品"
													href="javascript:void(0)"
													onclick="deleteUser(${product.id})"> <i
														class="fa fa-trash-o"></i> 删除
												</a> &nbsp; &nbsp;<a class="btn default btn-xs green openUrl"
													href="javascript:void(0)" onclick="examine(${product.id })">
														<i class="fa"></i> 查看商品
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


    function deleteUsers(id){
    	var current = $("#cur").html();
    	window.location.href="${DOMAIN}/system/commodity/editUsers?id="+id+"&cur="+current;
    }

    function examine(id){
    	window.location.href="${DOMAIN}/system/commodity/examine/"+id;
    }

	  function deleteUser(manId){
		  layer.confirm("确定删除该商品吗",{
				title:"删除角色",
				btn: ['确定删除','取消删除'],
				},
				function(){
					$.ajax({
						url:"${DOMAIN}/system/commodity/deleteUser",
						type:"post",
						data: {
							id : manId
						},
						dataType:"json",
						success:function(data){

							if(data.status == 1){

								setTimeout(function(){
									window.location.href = "${DOMAIN}/system/commodity/commodit";
								}
								, 1000);
							}else if(data.status == 0){
								layer.msg(data.Message);
							}
						},
						error:function(data){
							layer.msg(data.Message);
						}
					})
				},
				function(){
					layer.close();
				})
	  }


</script>
<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
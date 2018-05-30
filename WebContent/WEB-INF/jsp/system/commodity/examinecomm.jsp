
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<link href="${RESOURCEDOMAIN}/css/swfupload.css" rel="stylesheet"
	type="text/css" />
<!-- END STYLE CUSTOMIZER -->
<style>
#imgPreview_div div {
	display: inline-block;
	margin-bottom: 10px;
	margin-left: 10px;
	text-align: center;
	width: 80px;
	text-align: center;
}

select {
	background-color: #fff;
	border: 1px solid #ccc;
	width: 100px;
	height: 25px;
}

.form-control {
	display: inline;
}
</style>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			查看商品 <small>商品信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					商品详情</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 详情 </a></li>
		</ul>
	</div>
</div>

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>基本详情
				</div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					action="examine" novalidate="novalidate">
					<div class="form-body">
						<div class="alert alert-danger display-hide">
							<button data-close="alert" class="close"></button>
							You have some form errors. Please check below.
						</div>
						<div class="alert alert-success display-hide">
							<button data-close="alert" class="close"></button>
							Your form validation is successful!
						</div>


						<div class="form-group">
							<label class="control-label col-md-3">商品名称： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_price"
									value="${manage.name}" name="name" style="width: 180px;"
									id="name" readonly />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">出售价格： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_price"
									value="${manage.nowPrice }" name="nowPrice"
									style="width: 180px;" id="nowPrice" readonly />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">商品进价： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_price"
									value="${manage.cost }" id="cost" name="cost"
									style="width: 220px;" readonly />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">商品重量： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${manage.type }" id="type" name="type"
									style="width: 240px;" readonly />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">商品货号： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${manage.specifications }" id="specifications"
									name="specifications" style="width: 400px;" readonly />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">商品分类： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_price"
									placeholder="请输入商品类型如：红茶" value="${manage.typeSort}"
									name="typeSorts" style="width: 200px;" id="typeSort" readonly />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">商品头像： <span
								class="required"> * </span>
							</label> <img src="${RESOURCEDOMAIN}/${manage.picture}" width="400px"
								/  readonly />

						</div>

						<div class="form-group">
							<label class="control-label col-md-3">商品描述 ： </label>
							<div class="col-md-4">
								<textarea name="introduce" id="introduce"
									style="width: 400px; height: 280px;" readonly />${manage.introduce }</textarea>
								<link rel="stylesheet"
									href="${RESOURCEDOMAIN}/js/kindeditor/themes/default/default.css" />
								<link rel="stylesheet"
									href="${RESOURCEDOMAIN}/js/kindeditor/plugins/code/prettify.css" />
								<script charset="utf-8"
									src="${RESOURCEDOMAIN }/js/kindeditor/kindeditor-all.js"></script>
								<script charset="utf-8"
									src="${RESOURCEDOMAIN}/js/kindeditor/plugins/code/prettify.js"></script>
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<a class="btn green rollesubmit"
									href="${DOMAIN}/system/commodity/commodit">返回</a>
							</div>
						</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/swfupload/swfupload.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/swfupload/swfupload.queue.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/swfupload/fileprogress.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/js/swfupload/shophandlers.js"></script>
<script>
	       function(){
			var sub = $("#form_config").serialize();
				$.ajax({
					url:"${DOMAIN}/system/commodity/updatecomm",
					type: "post",
					dataType:"json",
                    data:sub,
					success:function(data){
						if(data.status=="1"){
							layer.msg(data.message,{icon: 1});
							setTimeout(function(){
						   		window.location.href="${DOMAIN}/system/commodity/commodit";
							},1000);
						}else{
							layer.msg(data.Message);
						  }
					},
					error:function(data){
						layer.msg(data.Message);
					}
				})
	}


	      $(function(){
		      $.extend($.validator.messages, {
		        required: "必选字段",
		        remote: "请修正该字段",
		        email: "请输入正确格式的电子邮件",
		        url: "请输入合法的网址",
		        date: "请输入合法的日期",
		        dateISO: "请输入合法的日期 (ISO).",
		        number: "请输入合法的数字",
		        digits: "只能输入整数",
		        creditcard: "请输入合法的信用卡号",
		        equalTo: "请再次输入相同的值",
		        accept: "请输入拥有合法后缀名的字符串",
		        maxlength: $.validator.format("请输入一个长度最多是 {0} 的字符串"),
		        minlength: $.validator.format("请输入一个长度最少是 {0} 的字符串"),
		        rangelength: $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		        range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		        max: $.validator.format("请输入一个最大为 {0} 的值"),
		        min: $.validator.format("请输入一个最小为 {0} 的值")
		      })
	     })

</script>
<jsp:include page="../foot.jsp"></jsp:include>
<!-- END PAGE CONTENT-->


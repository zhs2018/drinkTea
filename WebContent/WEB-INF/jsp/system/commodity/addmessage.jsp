
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<link href="${RESOURCEDOMAIN}/css/swfupload.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet"
	href="${RESOURCEDOMAIN}/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="${RESOURCEDOMAIN}/js/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8"
	src="${RESOURCEDOMAIN }/js/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8"
	src="${RESOURCEDOMAIN}/js/kindeditor/plugins/code/prettify.js"></script>
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
			关于我们 <small>编辑信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					关于管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 信息 </a></li>
		</ul>
	</div>
</div>

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>基本列表
				</div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					enctype="multipart/form-data"
					action="${RESOURCEDOMAIN}/system/commodity/save"
					novalidate="novalidate">
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
							<label class="control-label col-md-3">标题栏 ： </label>
							<div class="col-md-4">
								<textarea name="headline" id="headline"
									style="width: 500px; height: 65px;">${about.headline }</textarea>
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

						<div class="form-group">
							<label class="control-label col-md-3">图片(建议800*600以上)：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<div data-provides="fileinput" class="fileinput fileinput-new">
									<div class="input-group input-large">
										<div data-trigger="fileinput"
											class="form-control uneditable-input span3">
											<i class="fa fa-file fileinput-exists"></i>&nbsp; <span
												class="fileinput-filename"></span>
										</div>
										<span class="input-group-addon btn default btn-file"> <span
											class="fileinput-new"> 选择文件 </span> <!-- <span
											class="fileinput-exists"> Change </span> -->
											<input type="hidden" value="" name="..."><input
											type="file" name="myfile" accept="image/*""
											>
										</span>
									</div>

									<c:if test="${not empty obj.picUrl }">
										<br>
										<br>
										<img
											src="${RESOURCEDOMAIN}${fn:replace(obj.picUrl,'.','_800_600.')}"
											height="200px" width="400px" />
									</c:if>
								</div>
							</div>
						</div>

						<input type="hidden" id="aboutUsId" name="id" value="${about.id}">

						<div class="form-group">
							<label class="control-label col-md-3">字段1 ： </label>
							<div class="col-md-4">
								<textarea name="fielda" id="fielda"
									style="width: 500px; height: 150px;" name="fielda">${about.fielda}</textarea>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">字段二 ： </label>
							<div class="col-md-4">
								<textarea name="fieldb" id="fieldb"
									style="width: 500px; height: 150px;" name="fieldb">${about.fieldb }</textarea>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">字段三 ： </label>
							<div class="col-md-4">
								<textarea name="fieldc" id="fieldc"
									style="width: 500px; height: 150px;" name="fieldc">${about.fieldc }</textarea>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">字段四 ： </label>
							<div class="col-md-4">
								<textarea name="content" id="content"
									style="width: 500px; height: 150px;" name="content">${about.content }</textarea>
							</div>
						</div>

						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn green rollesubmit" type="submit">提交</button>
								<a class="btn green rollesubmit"
									href="${DOMAIN}/system/commodity/myAbout">返回</a>
							</div>
						</div>
				</form>
			</div>
		</div>
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

           var result = "${result}";
            if(result == "0"){
	            alert("${message}");

               }else if(result=="1"){
            	   alerl("添加失敗");
               }


             var result = "${result}";
            if(result == "2"){
	         /*    alert("${message}") */;
	            window.location.href="${DOMAIN}/system/commodity/myAbout/";
               }else if(result=="3"){
            	   alerl("添加失敗");
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

<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
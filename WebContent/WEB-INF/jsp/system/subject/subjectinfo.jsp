<%@ page language="java"
	import="java.util.*,javax.servlet.ServletRequest" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>.
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
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
			茶王争霸管理 <small>录入茶王争霸题目信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					茶王争霸管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 信息 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>基本信息
				</div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					action="savesubinfoImg" enctype="multipart/form-data"
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
							<div class="col-md-1">
								<input name="type" id="type" value="1" hidden="true">
							</div>
						</div>
						<div class="form-group" id="img">
							<label class="control-label col-md-3">题目图片：<span
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
											class="fileinput-new"> 选择文件 </span> <span
											class="fileinput-exists"> Change </span><input type="file"
											name="myfile"
											accept="image/gif,image/jpeg,image/jpg,image/png">
										</span>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group" id="intro">
							<label class="control-label col-md-3">题目介绍： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<textarea name="intro" style="width: 350px; height: 80px;"
									value="${sub.intro}">这里写关于茶叶的介绍</textarea>
							</div>
						</div>
						<div class="form-group" id="createTime">
							<label class="control-label col-md-3">发布时间： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input id="ttime" name="createTime" type="text"
									class="form-control" style="width: 510px;"
									value="${sub.createTime}"> <input type="hidden"
									value="${cur}" name="cur">
							</div>
						</div>
						<div id="allOptions">
							<div class="form-group">
								<label class="control-label col-md-3">选项： <span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<select class="form-control sub_select" name="option" id="opt"
										style="width: 510px;">
										<c:forEach items="${list}" var="list">
											<option value="${list.typeName}">${list.typeName}</option>
										</c:forEach>
									</select>
								</div>

							</div>
							<div class="form-group">
								<label class="control-label col-md-3">选项： <span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<select class="form-control sub_select" name="option" id="opt"
										style="width: 510px;">
										<c:forEach items="${list}" var="list">
											<option value="${list.typeName}">${list.typeName}</option>
										</c:forEach>
									</select>

								</div>
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn green rollesubmit" type="button"
									style="display: none" onclick="checkInfo();" id="formSubmit">提交</button>
								<button class="btn green rollesubmit" type="button"
									onclick="update();" id="formButton">提交</button>
								<button class="btn green rollesubmit" type="button"
									onclick="addOption();">增加选项</button>
								<button class="btn green rollesubmit" type="button"
									onclick="delOption();">删除选项</button>
							</div>
						</div>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<script>
	function checkInfo() {
		if ($.trim($(".fileinput-filename").html()) == "") {
			layer.msg("请选择题目图片！！", {
				icon : 2
			});
			return;
		}
		if ($.trim($("#ttime").val()) == "") {
			layer.msg("请输入发布时间！！", {
				icon : 2
			});
			return;
		}
		var list = document.getElementsByName("options");
		for (var i = 0; i < list.length; i++) {
			var obj = $("select[name='option']");
			var a = obj[i].value;
			if ($.trim(a) == "") {
				layer.msg("选项不能为空！！", {
					icon : 2
				});
				return;
			}
		}
		var list = $(".sub_select");
		alert(list.size());
		var arr = [];
		for (var i = 0; i < list.length; i++) {
			var obj = list[i];
			var a = $(obj).val();
			if (arr == null || arr.length == 0) {
				arr.push(a);
			} else {
				for (var i = 0; i < arr.length; i++) {
					var b = arr[i];
					if (a == b) {
						layer.msg("选项内容不能重复！！", {
							icon : 2
						});
						return;
					}
				}
				arr.push(a);
			}
		}
		$("form").submit();
	}

	/* <input type="text" class="form-control w_require" name="option" style="width:510px;"> */
	function addOption() {
		$("#allOptions")
				.append(
						'<div class="form-group">'
								+ '<label class="control-label col-md-3">选项：  <span class="required"> * </span></label>'
								+ '<div class="col-md-4">'
								+ '<select class="form-control sub_select" name="option" id="opt" style="width:510px;">'
								+ '<c:forEach items="${list}" var="list">'
								+ '<option value="${list.typeName}">${list.typeName}</option>'
								+ '</c:forEach>' + '</select>' + '</div>'
								+ '</div>');
	}

	function delOption() {
		//var list = document.getElementsByName("option");
		var inputs = $("select[name='option']");
		if (inputs.length) { // 如果addText里有input的话
			// 提取最后一个input
			var input = inputs[inputs.length - 1];
			// 获取父节点，也就是div
			var div = input.parentNode;
			var divs = div.parentNode;
			// 删除div节点
			divs.parentNode.removeChild(divs);
		}

	}

	function update() {
		var type = $("#type").val();
		var cur = $("#cur").val();
		//var list  = $("select[name='options']");
		var list = $(".sub_select");
		/*if($.trim($("#subName").val()) == ""){
			layer.msg("题目不能为空！！",{icon: 2});
			return ;
		} */
		var arr = [];
		for (var i = 0; i < list.length; i++) {
			var obj = list[i];
			var a = $(obj).val();
			if (arr == null || arr.length == 0) {
				arr.push(a);
			} else {
				for (var i = 0; i < arr.length; i++) {
					var b = arr[i];
					if (a == b) {
						layer.msg("选项内容不能重复！！", {
							icon : 2
						});
						return;
					}
				}
				arr.push(a);
			}
		}

		$("form").submit();
	}
	$(document).ready(function() {
		$('#ttime').datepicker({
			timePicker : true,
			timePickerIncrement : 30,
			format : 'yyyymmdd'

		}, function(start, end, label) {
			console.log(start.toISOString(), end.toISOString(), label);
		});
	});
</script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="${RESOURCEDOMAIN}/assets/scripts/custom/components-pickers.js"></script>
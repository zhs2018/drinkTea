<%@ page language="java"
	import="java.util.*,javax.servlet.ServletRequest" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>.
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<!-- END STYLE CUSTOMIZER -->
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<link rel="stylesheet" href="${RESOURCEDOMAIN}/js/default/default.css" />
<link rel="stylesheet" href="${RESOURCEDOMAIN}/js/code/prettify.css" />
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
			扫码答题管理 <small>录入扫码题目信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					扫码答题管理 </a> <i class="fa fa-angle-right"></i></li>
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
					action="savesubinfo" enctype="multipart/form-data"
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
						<div id="allOptions">
							<div class="form-group">
								<div class="col-md-1">
									<input name="type" id="type" value="0" hidden="true">
								</div>
							</div>
							<div class="form-group" id="sub">
								<label class="control-label col-md-3">题目：<span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<input type="text" class="form-control w_require" id="subName"
										value="${subInfo.name }" name="name" style="width: 510px;">
									<input type="hidden" value="${subInfo.id }" name="id">
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
											<span class="input-group-addon btn default btn-file">
												<span class="fileinput-new"> 选择文件 </span> <span
												class="fileinput-exists"> Change </span><input type="file"
												name="myfile"
												accept="image/gif,image/jpeg,image/jpg,image/png">
											</span>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group" id="intro">
								<label class="control-label col-md-3">茶叶介绍：<span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<textarea rows="5" cols="50" id="descn" name="intro">${subInfo.intro}</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">第一问： <span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<input type="text" name="" id="" value="类型" readonly="readonly">
								</div>

							</div>
							<div class="form-group">
								<label class="control-label col-md-3">选项： <span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<select class="form-control sub_select" name="option" id="opt"
										style="width: 510px;">
										<c:forEach items="${typeList}" var="type">
											<option value="${type.typeName}">${type.typeName}</option>
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
										<c:forEach items="${typeList}" var="type">
											<option value="${type.typeName}">${type.typeName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn green rollesubmit" type="button"
									onclick="addOption();">增加选项</button>
								<button class="btn green rollesubmit" type="button"
									onclick="delOption();">删除选项</button>
							</div>
						</div>
					</div>
					<div class="form-horizontal" id="form_config">
						<div class="form-body">
							<div class="alert alert-danger display-hide">
								<button data-close="alert" class="close"></button>
								You have some form errors. Please check below.
							</div>
							<div class="alert alert-success display-hide">
								<button data-close="alert" class="close"></button>
								Your form validation is successful!
							</div>
							<div id="allOptions1">
								<div class="form-group">
									<label class="control-label col-md-3">第二问： <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<input type="text" name="" id="" value="省份"
											readonly="readonly">
									</div>

								</div>
								<div class="form-group">
									<label class="control-label col-md-3">选项： <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<select class="form-control sub_select" name="option1"
											id="opt" style="width: 510px;">
											<c:forEach items="${provinceList}" var="province">
												<option value="${province.provinceName}">${province.provinceName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">选项： <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<select class="form-control sub_select" name="option1"
											id="opt" style="width: 510px;">
											<c:forEach items="${provinceList}" var="province">
												<option value="${province.provinceName}">${province.provinceName}</option>
											</c:forEach>
										</select>

									</div>
								</div>
							</div>
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-9">
									<button class="btn green rollesubmit" type="button"
										onclick="addOption1();">增加选项</button>
									<button class="btn green rollesubmit" type="button"
										onclick="delOption1();">删除选项</button>
								</div>
							</div>
						</div>
					</div>
					<!-- method="post"
					action="savesubinfo2" enctype="multipart/form-data"
					novalidate="novalidate" -->
					<div class="form-horizontal" id="form_config">
						<div class="form-body">
							<div class="alert alert-danger display-hide">
								<button data-close="alert" class="close"></button>
								You have some form errors. Please check below.
							</div>
							<div class="alert alert-success display-hide">
								<button data-close="alert" class="close"></button>
								Your form validation is successful!
							</div>
							<div id="allOptions2">
								<div class="form-group">
									<label class="control-label col-md-3">第三问： <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<input type="text" name="" id="" value="季节"
											readonly="readonly">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">选项： <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<select class="form-control sub_select" name="option2"
											id="opt" style="width: 510px;">
											<c:forEach items="${seasonList}" var="season">
												<option value="${season.seasonName}">${season.seasonName}</option>
											</c:forEach>
										</select>
									</div>

								</div>
								<div class="form-group">
									<label class="control-label col-md-3">选项： <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<select class="form-control sub_select" name="option2"
											id="opt" style="width: 510px;">
											<c:forEach items="${seasonList}" var="season">
												<option value="${season.seasonName}">${season.seasonName}</option>
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
										onclick="addOption2();">增加选项</button>
									<button class="btn green rollesubmit" type="button"
										onclick="delOption2();">删除选项</button>
								</div>
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
	$(function() {
		var editor2 = "";
		KindEditor
				.ready(function(K) {
					editor2 = K
							.create(
									'textarea[id="descn"]',
									{
										cssPath : '${RESOURCEDOMAIN}/js/code/prettify.css',
										uploadJson : '${RESOURCEDOMAIN}/js/kindeditor/jsp/upload_json.jsp',
										fileManagerJson : '${RESOURCEDOMAIN}/js/kindeditor/jsp/file_manager_json.jsp',
										allowFileManager : true,
										urlType : 'domain',
										afterBlur : function() {
											this.sync();
										}
									});
					prettyPrint();
				});
	});

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
		/* 	$("form").submit(); */

		var list1 = $(".sub_select1");
		alert(list1.size());
		var arr1 = [];
		for (var i = 0; i < list1.length; i++) {
			var obj1 = list1[i];
			var a1 = $(obj1).val();
			if (arr1 == null || arr1.length == 0) {
				arr1.push(a1);
			} else {
				for (var i = 0; i < arr1.length; i++) {
					var b1 = arr1[i];
					if (a1 == b1) {
						layer.msg("选项内容不能重复！！", {
							icon : 2
						});
						return;
					}
				}
				arr1.push(a1);
			}
		}
		/* $("form1").submit(); */

		var list2 = $(".sub_select2");
		alert(list2.size());
		var arr2 = [];
		for (var i = 0; i < list2.length; i++) {
			var obj2 = list2[i];
			var a2 = $(obj2).val();
			if (arr2 == null || arr2.length == 0) {
				arr2.push(a2);
			} else {
				for (var i = 0; i < arr2.length; i++) {
					var b2 = arr2[i];
					if (a2 == b2) {
						layer.msg("选项内容不能重复！！", {
							icon : 2
						});
						return;
					}
				}
				arr2.push(a2);
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
								+ '<c:forEach items="${typeList}" var="type">'
								+ '<option value="${type.typeName}">${type.typeName}</option>'
								+ '</c:forEach>' + '</select>' + '</div>'
								+ '</div>');
	}
	function addOption1() {
		$("#allOptions1")
				.append(
						'<div class="form-group">'
								+ '<label class="control-label col-md-3">选项：  <span class="required"> * </span></label>'
								+ '<div class="col-md-4">'
								+ '<select class="form-control sub_select1" name="option1" id="opt" style="width:510px;">'
								+ '<c:forEach items="${provinceList}" var="province">'
								+ '<option value="${province.provinceName}">${province.provinceName}</option>'
								+ '</c:forEach>' + '</select>' + '</div>'
								+ '</div>');
	}
	function addOption2() {
		$("#allOptions2")
				.append(
						'<div class="form-group">'
								+ '<label class="control-label col-md-3">选项：  <span class="required"> * </span></label>'
								+ '<div class="col-md-4">'
								+ '<select class="form-control sub_select2" name="option2" id="opt" style="width:510px;">'
								+ '<c:forEach items="${seasonList}" var="season">'
								+ '<option value="${season.seasonName}">${season.seasonName}</option>'
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

	function delOption1() {
		//var list = document.getElementsByName("option");
		var inputs = $("select[name='option1']");
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
	function delOption2() {
		//var list = document.getElementsByName("option");
		var inputs = $("select[name='option2']");
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
		//var list  = $("select[name='options']");
		var list = $(".sub_select");
		if ($.trim($("#subName").val()) == "") {
			layer.msg("题目不能为空！！", {
				icon : 2
			});
			return;
		}
		if ($.trim($(".fileinput-filename").html()) == "") {
			layer.msg("请选择题目图片！！", {
				icon : 2
			});
			return;
		}
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
<!-- END PAGE CONTENT-->
<script charset="utf-8" src="${RESOURCEDOMAIN }/js/kindeditor-all.js"></script>
<script charset="utf-8" src="${RESOURCEDOMAIN}/js/code/prettify.js"></script>
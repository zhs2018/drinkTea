<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/layer_uploader.css" />
<script type="text/javascript" src="${RESOURCEDOMAIN}/js/layer/layer.js"></script>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			二维码管理<small>&nbsp;&nbsp;小包添加</small>
		</h3>
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
					<i class="fa fa-reorder"></i>信息
				</div>
				<div class="tools"></div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" method="post" id="form_config"
					enctype="multipart/form-data"
					action="${DOMAIN}/system/user/saveUser" novalidate="novalidate">
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
							<label class="control-label col-md-3">二维码名称 ： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require" name="name"
									value="${smlPk.name}" id="name" style="width: 200px"> <input
									type="text" class="form-control w_require" name="falseId"
									id="falseId" value="${smlPk.id}" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">扫码答题题目： <span
								class="required"> * </span>
							</label>
							<div class="col-md-3">
								<select class="form-control aaa" name="changeOn" id="one"
									onchange="OneChange();">
									<option value="0">请选择题目</option>
									<c:forEach items="${subjectList}" var="one">
										<option class="${one.id }" value="${one.id }">${one.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">扫码答题图片： <span
								class="required"> * </span>
							</label>
							<div class="col-md-3">
								<div class="form-control" id="pic"
									style="width: 400px; height: 350px;"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">类型答案 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-3">
								<select class="form-control" name="option" id="optionOne">
									<option value="0">请选择答案</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">省市答案 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-3">
								<select class="form-control" name="option" id="optionTwo">
									<option value="0">请选择答案</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">季节答案 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-3">
								<select class="form-control" name="option" id="optionThree">
									<option value="0">请选择答案</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green rollesubmit" type="button"
								onclick="saveSmallPackage();">提交</button>
						</div>
					</div>
				</form>
			</div>

			<!-- END VALIDATION STATES-->
		</div>
	</div>

	<!-- END PAGE CONTENT-->
	<jsp:include page="../foot.jsp"></jsp:include>
	<script type="text/javascript"
		src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link rel="stylesheet" type="text/css"
		href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
	<script>
		//几个下拉选的关联事件
		$(function() {
			//改变
			$(".aaa").change(function() {
				var index = $("select").index(this);
				console.log("选择的select编号：" + index);
				$("option").css("display", "block"); //选择的option
				$(":selected").each(function(n, m) {
					var value = $(this).val();
					$("." + value).css("display", "none");
					$(this).css("display", "block");
				});
			})
		})

		function OneChange() {
			var subId = $("#one").val();
			$
					.ajax({
						url : "${DOMAIN}/system/qrcode/selectOptionBySubId/"
								+ subId,
						type : "post",
						dataType : "json",
						success : function(data) {
							$("#pic").html('');
							var sk = data.sk;
							$("#pic")
									.append(
											'<img style = "width:370px;height:320px;" src="${RESOURCEDOMAIN}/'+sk.img+'" alt=/>')
							$("#optionOne").empty();
							var optionList = data.optionList;
							for (var i = 0; i < optionList.length; i++) {
								var one = optionList[i];
								$("#optionOne").append(
										'<option value="'+one.id+'">'
												+ one.option + '</option>');
							}
							$("#optionTwo").empty();
							var optionList1 = data.optionList1;
							for (var i = 0; i < optionList1.length; i++) {
								var one1 = optionList1[i];
								$("#optionTwo").append(
										'<option value="'+one1.id+'">'
												+ one1.option + '</option>');
							}

							$("#optionThree").empty();
							var optionList2 = data.optionList2;
							for (var i = 0; i < optionList2.length; i++) {
								var one2 = optionList2[i];
								$("#optionThree").append(
										'<option value="'+one2.id+'">'
												+ one2.option + '</option>');
							}
						},
						error : function() {
							layer.msg("程序出错，请联系程序员小哥");
						}
					})
		}

		//保存所有的小包二维码信息
		function saveSmallPackage() {
			var sub_one = $("#one").val();
			var op_one = $("#optionOne").val();
			var op_two = $("#optionTwo").val();
			var op_three = $("#optionThree").val();
			var name = $("#name").val();
			alert(name);
			if (sub_one == 0 || name == "" || op_one == 0 || op_two == 0
					|| op_three == 0) {
				layer.msg("小包二维码信息没有填全", {
					icon : 2
				});
				return false;
			}
			//存该二维码信息的时候，先在小包表里插入二维码名称和创建时间，然后获得该数据的小包id，然后再存到package_subject表里，
			//需要给后台传递该小包的名称，四个选项的id，四个答案的id
			$
					.ajax({
						url : "${DOMAIN}/system/qrcode/updateSmallPackage",
						type : "post",
						data : {
							"name" : name,
							"subOneId" : sub_one,
							"opOneId" : op_one,
							"opTwoId" : op_two,
							"opThreeId" : op_three,
						},
						dataType : "json",
						success : function(data) {
							if (data.status == 1) {
								layer.msg("保存成功", {
									icon : 1
								});
								setTimeout(
										function() {
											window.location.href = "${DOMAIN}/system/qrcode/smallPackage";
										}, 1000);
							} else if (data.status == 2) {
								layer.msg(data.Message);
							}
						},
						error : function(data) {
							layer.msg(data.Message);
						}
					})
		}
	</script>
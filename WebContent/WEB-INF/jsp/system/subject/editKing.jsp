<%@ page language="java"
	import="java.util.*,javax.servlet.ServletRequest" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
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
			茶王争霸题库管理 <small>编辑题目信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					题库管理 </a> <i class="fa fa-angle-right"></i></li>
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
					action="updateKinginfo" enctype="multipart/form-data"
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
						<input value="${id}" name="id" hidden="true" />
						<div id="allOptions">
							<div class="form-group" id="sub">
								<label class="control-label col-md-3">题目：<span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<img
										src="${RESOURCEDOMAIN}${fn:replace(KingList.img,'.','_800_600.')}"
										width="200px" />
								</div>
							</div>
							<div class="form-group" id="img">
								<label class="control-label col-md-3"><span
									class="required"></span> </label>
								<div class="col-md-4">
									<div data-provides="fileinput" class="fileinput fileinput-new">
										<div class="input-group input-large">
											<div data-trigger="fileinput"
												class="form-control uneditable-input span3">
												<i class="fa fa-file fileinput-exists"></i>&nbsp; <span
													class="fileinput-filename"></span>
											</div>
											<span class="input-group-addon btn default btn-file">
												<span class="fileinput-new"> 选择文件 </span> <!-- <span
											class="fileinput-exists"> Change </span> --> <input
												type="hidden" value="" name="..."><input type="file"
												name="myfile"
												accept="image/gif,image/jpg,image/png,image/jpeg">
											</span>
										</div>
									</div>
								</div>
							</div>
							<c:forEach items="${options }" var="one">
								<div class="form-group">
									<label class="control-label col-md-3">选项： <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<input type="text" class="form-control option"
											readonly="readonly" value="${one.option }" name="option"
											style="width: 510px;" onChange="update()">
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">答案： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<select class="form-control" name="answer" id="answer">
									<c:if test="${empty KingList.answer}">
										<option value='-1' selected>请选择答案</option>
									</c:if>
									<c:forEach items="${options}" var="option" varStatus="vs">
										<c:if test="${option.id == KingList.answer}">
											<option value="${vs.index}" selected>${option.option}</option>
										</c:if>
										<c:if test="${option.id != KingList.answer}">
											<option value="${vs.index}">${option.option}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn green rollesubmit" type="submit"
									id="formButton">提交</button>
								<!-- <button class="btn green rollesubmit" type="button" onclick="addOption();">增加选项</button>
							 <button class="btn green rollesubmit" type="button" onclick="delOption();">删除选项</button> -->
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
function check(){
	var type = $("#type").val();
	if(type == 1){
		$("#img").show();
		$("#sub").hide();
		$("#formSubmit").show();
		$("#formButton").hide();
	}else{
		$("#img").hide();
		$("#sub").show();
		$("#formSubmit").hide();
		$("#formButton").show();
	}
}


function delOption(){
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

function addOption(){
	$("#allOptions").append(
		'<div class="form-group">'+
			'<label class="control-label col-md-3">选项：  <span class="required"> * </span></label>'+
			'<div class="col-md-4">'+
				'<input type="text" class="form-control w_require option" name="option" style="width:510px;" onChange="update()">'+
			'</div>'+
		'</div>'
	);
}
function update(){
	var index = $("#answer").val();
	$("#answer").empty();
	$("#answer").append(
			"<option value='-1'>请选择答案</option>"
		);
	$(".option").each(function ,ele){
		if(i == index){
			$("#answer").append(
				"<option value='"+i+"' selected>"+$(ele).val()+"</option>"
			);
		}else{
			$("#answer").append(
					"<option value='"+i+"'>"+$(ele).val()+"</option>"
				);
		}
	});
}
</script>
<script type="text/javascript"
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${RESOURCEDOMAIN}/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
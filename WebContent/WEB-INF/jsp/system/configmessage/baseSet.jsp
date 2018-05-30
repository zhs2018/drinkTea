<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<link href="${SHOPDOMAIN}/css/swfupload.css" rel="stylesheet"
	type="text/css" />
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			我的信息 <small>商户</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					系统管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 我的信息 </a></li>
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
					action="saveuser.html" novalidate="novalidate">
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
							<label class="control-label col-md-3">商户名称 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${company.companyName }" name="companyName"
									style="width: 500px;"> <input type="hidden"
									value="${company.companyId }" name="companyId">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">联系人 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${company.companyContact }" name="companyContact"
									style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">手机号 ： </label>
							<div class="col-md-4">
								<input type="text" class="form-control w_mobile"
									value="${company.mobile }" name="mobile" style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">appid ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									readonly="readonly" value="${companyConfig.appid }"
									name="appid" style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">appsecret ： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									readonly="readonly" value="${companyConfig.appsecret }"
									name="appsecret" style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">开通时间 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control " readonly="readonly"
									value="${fn:substring(c.addTime,0,19) }" style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">关注公共账号连接 ： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${companyConfig.focusUrl }" name="focusUrl"
									style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">gh_id ： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control w_user_gh w_require"
									readonly="readonly" value="${companyConfig.gh }" name="gh"
									style="width: 500px;"> <input type="hidden"
									value="${companyConfig.gh }" id="ghBack">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">用户最低允许提现金额 ： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input type="text" class="form-control  w_rweight"
									value="${companyConfig.allow_money }" name="allow_money"
									style="width: 500px;"> 元
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">是否开启货到付款的功能 ： </label>
							<div class="col-md-4">
								<select class="form-control" name="isCashOnDelivery">
									<option value="1"
										<c:if test="${companyConfig.isCashOnDelivery == 1 }"> selected="selected"</c:if>>开启</option>
									<option value="0"
										<c:if test="${companyConfig.isCashOnDelivery == 0 }"> selected="selected"</c:if>>暂不开启</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3" onclick="kk()">商户分享二维码背景图片
								<br> <a href="${SHOPDOMAIN}/image/demo.jpg" target="_blank">下载demo</a>：
							</label>
							<div class="col-md-4">
								<div class="flash_btns">
									<span id="spanButtonPlaceHolder"></span> <input id="btnCancel"
										type="button" value="取消上传" style="display: none"
										onclick="swfu.cancelQueue();" disabled="disabled"
										class="c_input" />
								</div>

								<div class="fieldset flash" id="fsUploadProgress">
									<span class="legend">传输队列</span>
									<div id="imgPreview_div">
										<c:if test="${!empty companyConfig.qrcode }">
											<div id="0_imgdiv">
												<img width="70px" height="70px"
													src="${USERIMGSRC }/uploads/${companyConfig.qrcode }"><a
													href="javascript:delCurImgdiv(0,this);">删除</a>
											</div>
										</c:if>
									</div>
								</div>
								<div id="divStatus">
									<span id="fileCount_span"><c:if
											test="${!empty companyConfig.qrcode }">1</c:if> <c:if
											test="${empty companyConfig.qrcode }">0</c:if></span>个文件已上传
								</div>
								<input type="hidden" id="thumbs" name="qrcode"
									value="<c:if test="${!empty companyConfig.qrcode }">${companyConfig.qrcode }</c:if>"
									class="w_requireImg" />
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green" type="submit">提交</button>
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
	src="${SHOPDOMAIN}/js/swfupload/swfupload.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/js/swfupload/swfupload.queue.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/js/swfupload/fileprogress.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/js/swfupload/handlers.js"></script>
<script>
	var swfu;
	$(function() {
		var settings = {
			flash_url : "${SHOPDOMAIN}/js/swfupload/swfupload.swf",
			upload_url : "/uploads/jscripts/uploadImageFile.do",
			//upload_url : "jscripts/uploadImageFile.do",
			post_params : {
				"companyId" : "${sessionUser.companyId}"
			},
			use_query_string : true,
			file_size_limit : "10 MB",
			file_types : "*.png;*.jpeg;*.jpg;*.gif",
			file_types_description : "图片文件",
			file_upload_limit : 1,
			file_queue_limit : 1,
			custom_settings : {
				progressTarget : "fsUploadProgress",
				cancelButtonId : "btnCancel"
			},
			// Button settings   按钮设置
			button_image_url : "${SHOPDOMAIN}/image/swfupload/up2.png",
			button_width : "250",
			button_height : "40",
			button_placeholder_id : "spanButtonPlaceHolder",
			button_text : '<span class="theFont"></span>',
			button_text_style : ".theFont { font-size: 12; }",
			button_text_left_padding : 6,
			button_text_top_padding : 3,

			// The event handler functions are defined in handlers.js   handlers.js中定义的事件处理程序函数

			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			upload_start_handler : uploadStart,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete,
			queue_complete_handler : queueComplete
		// Queue plugin event
		};
		swfu = new SWFUpload(settings);
	});
	//删除图片  只是从页面上删除
	function delCurImgdiv(curImgId, kthis) {
		idDel = true;
		//把thumbs里面该图片的路径去掉
		$("#thumbs").val("");//当前的值
		$("#" + curImgId + "_imgdiv").remove();//删除元素
		//当前文件数量 -1
		var curfilecount = $("#fileCount_span").text();//当前已经上传的图片的数量
		swfu.subUploadedCount(1); // 递减 一个已经上传成功的数量;
		if (curfilecount > 0) {
			curfilecount = curfilecount * 1 - 1;
			upCount_t = upCount_t - 1;
			$("#fileCount_span").html(curfilecount);
		}
	}
</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
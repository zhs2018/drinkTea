<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			首次关注设置 <small>关注</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					回复设置 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 首次关注设置 </a></li>
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
					<i class="fa fa-reorder"></i>设置
				</div>
				<div class="tools"></div>
			</div>
			<div class="portlet-body">
				<div class="alert alert-block alert-info fade in">
					<button data-dismiss="alert" class="close" type="button"></button>
					<h4 class="alert-heading">帮助!</h4>
					<p>1.存在邀请人回复:</p>
					<p>例子： 恭喜您由【X】邀请成为第【Y】位会员！
						注：【X】为邀请人昵称，【Y】为此公众号的第多少位会员，X，Y为两个代码请向管理员获取！</p>
					<p>2.未存在邀请人回复：</p>
					<p>可自由发挥填写</p>
				</div>
			</div>

			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form1" method="post"
					action="savefocuson.html" novalidate="novalidate">
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
							<label class="control-label col-md-3">存在邀请人回复 ： </label>
							<div class="col-md-4">
								<textarea name="focusHas" id="focushas"
									style="width: 600px; height: 180px;">${rf.focusHas }</textarea>
								<input type="hidden" value="${rf.companyId }" name="companyId">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">未存在邀请人回复 ： </label>
							<div class="col-md-4">
								<textarea name="focusNoHas" id="focusnohas"
									style="width: 600px; height: 180px;">${rf.focusNoHas }</textarea>
								<link rel="stylesheet"
									href="${SHOPDOMAIN}/js/kindeditor/themes/default/default.css" />
								<link rel="stylesheet"
									href="${SHOPDOMAIN}/js/kindeditor/plugins/code/prettify.css" />
								<script charset="utf-8"
									src="${SHOPDOMAIN }/js/kindeditor/kindeditor-all.js"></script>
								<script charset="utf-8"
									src="${SHOPDOMAIN}/js/kindeditor/plugins/code/prettify.js"></script>
								<SCRIPT src="${SHOPDOMAIN}/js/system/kindeditorimgupload.js"></SCRIPT>
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green" type="button" onclick="check1()">提交</button>
						</div>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<script>
	var editor1 = "";
	KindEditor
			.ready(function(K) {
				editor1 = K
						.create(
								'textarea[id="focushas"]',
								{
									cssPath : '${SHOPDOMAIN }/js/kindeditor/plugins/code/prettify.css',
									uploadJson : '${SHOPDOMAIN }/kindeditor/uploadJson.html?companyId=${sessionUser.companyId }',
									fileManagerJson : '${SHOPDOMAIN }/kindeditor/fileManagerJson.html',
									allowFileManager : true,
									afterCreate : function() {
										var self = this;
										K.ctrl(document, 13, function() {
											self.sync();
											document.forms['form1'].submit();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
											document.forms['form1'].submit();
										});
									}
								});
				prettyPrint();
			});
	var editor2 = "";
	KindEditor
			.ready(function(K) {
				editor2 = K
						.create(
								'textarea[id="focusnohas"]',
								{
									cssPath : '${SHOPDOMAIN }/js/kindeditor/plugins/code/prettify.css',
									uploadJson : '${SHOPDOMAIN }/kindeditor/uploadJson.html?companyId=${sessionUser.companyId }',
									fileManagerJson : '${SHOPDOMAIN }/kindeditor/fileManagerJson.html',
									allowFileManager : true,
									afterCreate : function() {
										var self = this;
										K.ctrl(document, 13, function() {
											self.sync();
											document.forms['form1'].submit();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
											document.forms['form1'].submit();
										});
									}
								});
				prettyPrint();
			});
	/**
	 * 检测文本类型提交
	 */
	function check1() {
		editor1.sync();
		editor2.sync();
		$("#form1").submit();
	}
</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<style>
.alert {
	background-color: #F9EDBE;
	border: 1px solid #F0C36D;
	border-radius: 2px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
	color: #333333;
	padding: 8px 35px 8px 10px;
	text-shadow: none;
}

#responseHighmusic2 {
	width: 400px
}

.tableList {
	border: 1px solid #ccc;
	width: 600px;
	float: left;
	clear: both;
	margin-bottom: 10px;
	height: 330px;
}

.no-border {
	border: none;
}

.tableList td {
	height: 40px;
	line-height: 40px;
	float: left
}

.tableList .tright {
	text-align: right;
	width: 50px;
	margin-right: 10px;
}

#smallphototd, .insertimage {
	float: left;
}
</style>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			添加回复 <small>可以选择文本、图文、语音消息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					回复管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 添加回复 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-reorder"></i>录入回复
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"> </a> <a
					href="#portlet-config" data-toggle="modal" class="config"> </a>
			</div>
		</div>
		<div class="portlet-body">
			<div class="tabbable-custom ">
				<ul class="nav nav-tabs ">
					<li class="active" id="f_text"><a href="#tab_5_1"
						data-toggle="tab"> 文本消息 </a></li>
					<li class="" id="f_pic"><a href="#tab_5_2" data-toggle="tab">
							图文消息 </a></li>
					<li class="" id="f_video"><a href="#tab_5_3" data-toggle="tab">
							语音消息 </a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="tab_5_1">
						<form action="${SHOPDOMAIN }/system/response/upload.html"
							id="form1" method="post" class="form-horizontal"
							novalidate="novalidate">
							<input type="hidden" value="1" name="responseType"> <input
								type="hidden" value="${objList.responseId }" id="texthidden"
								name="responseId"> <input type="hidden"
								value="${mstat }" name="mstat">
							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									You have some form errors. Please check below.
								</div>
								<div class="alert alert-success display-hide">
									<button class="close" data-close="alert"></button>
									Your form validation is successful!
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">关键字： <span
										class="required"> * </span>
									</label>
									<div class="col-md-4" id="texttemp">
										<input type="text" class="form-control" name="responseTitle"
											id="responseTitle1" value="${objList.responseTitle }"
											onblur="checkissame(this.value,1,this.id);"><input
											type="hidden" id="responseText1"
											value="${objList.responseTitle }">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">回复模式： </label>
									<div class="col-md-4">
										<input type="radio" class="toggle" name="responseClass"
											id="responseClassText2" onclick="back('text')"
											checked="checked" value="2" /><label
											for="responseClassText2">命令模式</label> <input type="radio"
											class="toggle" name="responseClass" id="responseClassText4"
											onclick="showKey('text',1)" value="4" /><label
											for="responseClassText4">缺省模式</label>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">回复信息： </label>
									<div class="col-md-4">
										<textarea name="responseDes" id="responseDesText"
											style="width: 600px; height: 180px;">${objList.responseDes }</textarea>
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
									<button type="submit" class="btn green" onclick="check1()">保存</button>
								</div>
							</div>
						</form>
					</div>
					<div class="tab-pane" id="tab_5_2">
						<form method="post" class="form-horizontal" method="post"
							action="${SHOPDOMAIN }/system/response/upload2.html" id="form2">
							<input type="hidden" value="2" name="responseType"> <input
								type="hidden" value="${objList1[0].responseId }" id="pichidden"
								name="responseId"> <input type="hidden"
								value="${mstat }" name="mstat">
							<div class="add_formLeft" style="overflow: hidden; width: 100%;"
								tabindex="5000">
								<div class="form-group">
									<label class="control-label col-md-3">关键字： <span
										class="required"> * </span>
									</label>
									<div class="col-md-4" id="pictemp">
										<input type="hidden" id="responseTitle2hiden"
											value="${objList1[0].responseTitle }"><input
											type="text" class="form-control" name="responseTitle"
											id="responseTitle2" value="${objList1[0].responseTitle }"
											onblur="checkissame(this.value,2,this.id);">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">回复模式： </label>
									<div class="col-md-4">
										<input type="radio" class="toggle" onclick="back('pic')"
											name="responseClassPic" id="responseClassPic2"
											checked="checked" value="2" /><label for="responseClassPic2">命令模式</label>
										<input type="radio" class="toggle" name="responseClassPic"
											id="responseClassPic4" onclick="showKey('pic',1)" value="4" /><label
											for="responseClassPic4">缺省模式</label>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">图文信息： </label>
									<div class="col-md-4" style="width: 670px;">
										<c:forEach items="${objList1 }" var="k" varStatus="indexk">
											<table class="tableList" id="table0">
												<input id="oidd" type='hidden' name='orderimgid'
													value='${k.responseId}'>
												<colgroup>
													<col style="width: 24%;">
												</colgroup>
												<tbody>
													<tr class="no-border">
														<td class="tright"><label for="cname">标题：</label></td>
														<td><label class="input_val" style="margin-top: 5px;">
																<input type="text" class="form-control" name="titlename"
																id="titlename0" value="${k.titlename}">
														</label></td>
													</tr>
													<tr>
														<td class="tright"><label for="cname">图片：</label></td>
														<td>
															<div id="smallphototd">
																<c:if test="${!empty k.responsePic}">
																	<input type="hidden" name="responsePics" size="6"
																		class="text inputmedium" readonly
																		value="${k.responsePic }" style="width: 60%" />
																	<span class="help-inline">&nbsp;&nbsp;<img
																		style="margin-top: 0px; margin-right: 15px;"
																		src="${k.responsePic }" width="40px" height="30px"><a
																		class="btn_blue button insertimage">选择图片</a>
																	</span>
																</c:if>
																<c:if test="${empty k.responsePic}">
																	<input type="hidden" name="responsePics" size="6"
																		class="text inputmedium" readonly
																		value="${dl.picUrl }" style="width: 60%" />
																	<span class="help-inline">&nbsp;&nbsp;<img
																		style="margin-top: 0px; margin-right: 15px; display: none"
																		src="${dl.picUrl }" width="40px" height="30px"><a
																		class="btn_blue button insertimage">选择图片</a>
																	</span>
																</c:if>
															</div>
														</td>
													</tr>
													<tr>
														<td class="tright"><label for="cname">描述：</label></td>
														<td style='height: 90px'><textarea name="responseDes"
																id="responseDes" class="form-control" rows="" cols=""
																style="width: 300px; height: 80px">${k.responseDes}</textarea></td>
													</tr>
													<tr>
														<td class="tright"><label for="cname">回复：</label></td>
														<td><select name="isOuts" onchange="isOutClick(this)"
															class="form-control"
															style="margin-top: 10px; width: 400px">
																<option
																	<c:if test="${k.isOut==2}">selected="selected"</c:if>
																	value="2">外链</option>
														</select></td>
													</tr>
													<tr>
														<td class='tright'></td>
														<td><input type='text'
															class='form-control responseHighmusicUrl0'
															name='responseHighmusicUrl' id='responseHighmusic2'
															style="margin-top: 8px;" value="${k.responseHighmusic}"></td>
													</tr>
													<tr>
														<td><c:if test="${indexk.index!=0 }">
																<a href='javascript:void(0)'
																	onclick='delthis(this,${k.responseId})'
																	class='button btn red'
																	style="float: right; margin-left: 60px; margin-right: 50px">删除</a>
															</c:if></td>
													</tr>
												</tbody>
											</table>
										</c:forEach>
										<c:if test="${empty objList1 }">
											<table class="tableList" id="table0">
												<input id="oidd" type='hidden' name='orderimgid'
													value='${k.responseId}'>
												<colgroup>
													<col style="width: 24%;">
												</colgroup>
												<tbody>
													<tr class="no-border">
														<td class="tright"><label for="cname">标题：</label></td>
														<td><label class="input_val" style="margin-top: 5px;">
																<input type="text" class="form-control" name="titlename"
																id="titlename0" value="">
														</label></td>
													</tr>
													<tr>
														<td class="tright"><label for="cname">图片：</label></td>
														<td>
															<div id="smallphototd">

																<input type="hidden" name="responsePics" size="6"
																	class="text inputmedium" readonly value="${dl.picUrl }"
																	style="width: 60%" /> <span class="help-inline">&nbsp;&nbsp;<img
																	style="margin-top: 0px; margin-right: 15px; display: none"
																	src="${dl.picUrl }" width="40px" height="30px"><a
																	class="button btn_blue insertimage">选择图片</a>
																</span>


															</div>
														</td>
													</tr>
													<tr>
														<td class="tright"><label for="cname">描述：</label></td>
														<td style='height: 90px'><textarea name="responseDes"
																id="responseDes" class="form-control" rows="" cols=""
																style="width: 300px; height: 80px"></textarea></td>
													</tr>
													<tr>
														<td class="tright"><label for="cname">回复：</label></td>
														<td><select name="isOuts" onchange="isOutClick(this)"
															class="form-control"
															style="margin-top: 10px; width: 400px">
																<option value="2" selected="selected">外链</option>
														</select></td>
													</tr>

													<tr>
														<td class='tright'></td>
														<td><input type='text'
															class='form-control responseHighmusicUrl0'
															style="margin-top: 8px;" name='responseHighmusicUrl'
															id='responseHighmusic2'></td>
													</tr>
												</tbody>
											</table>
										</c:if>

										<div>

											<a href="javascript:void(0)" onclick="add()"
												class="button btn red" style="color: white; float: right;">再添加一个</a>
										</div>
										<div style="clear: both;"></div>
										<div id="appendstring"></div>
									</div>
								</div>
							</div>
						</form>
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn green" onclick="check2()">保存</button>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="tab_5_3">
						<form method="post" class="form-horizontal" method="post"
							action="${SHOPDOMAIN }/system/response/upload.html" id="form3">
							<input type="hidden" value="3" name="responseType"> <input
								type="hidden" value="${objList.responseId }" name="responseId"
								id="voicehidden"> <input type="hidden" value="${mstat }"
								name="mstat">
							<div class="add_formLeft" style="overflow: hidden; width: 100%;"
								tabindex="5000">
								<div class="form-group">
									<label class="control-label col-md-3">关键字： <span
										class="required"> * </span>
									</label>
									<div class="col-md-4" id="voicetemp">
										<input type="hidden" id="responseTitle3hiden"
											value="${objList.responseTitle }"><input type="text"
											class="form-control" name="responseTitle" id="responseTitle3"
											value="${objList.responseTitle }"
											onblur="checkissame(this.value,3,this.id);">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">回复模式： </label>
									<div class="col-md-4">
										<label class="input_val" id="texttemp"> <input
											type="radio" class="toggle" name="responseClassVoice"
											id="responseClassVoice2" onclick="back('voice')"
											checked="checked" value="2" /><label
											for="responseClassVoice2">命令模式</label> <input type="radio"
											class="toggle" name="responseClassVoice"
											id="responseClassVoice4" onclick="showKey('voice',1)"
											value="4" /><label for="responseClassVoice4">缺省模式</label>
										</label>

									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">音乐链接： <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="responseMusic"
											id="responseMusic" value="${objList.responseMusic }">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">高品质音乐链接： <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<input type="text" class="form-control"
											name="responseHighmusic" id="responseHighmusic"
											value="${objList.responseHighmusic }">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">描述： </label>
									<div class="col-md-4">
										<textarea name="responseDes" id="responseDes" rows=""
											class="form-control"
											style="margin-left: 0px; width: 523px; height: 115px;"
											cols="">${objList.responseDes }</textarea>
									</div>
								</div>
							</div>
						</form>
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn green" onclick="check3()">保存</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var ridClass = "";
	var texttemp = "";//文本保存
	var pictemp = "";//图文保存
	var voicetemp = ""//声音保存
	var IsKey = "${Iskey}";
	var IsDefaultkey = "${IsDefaultkey}";

	var KeyDes = "${KeyDes}";
	if (IsKey != "") {
		if (KeyDes == "texttemp") {
			texttemp = $("#" + KeyDes).html();
		}
		if (KeyDes == "pictemp") {
			pictemp = $("#" + KeyDes).html();
		}
		if (KeyDes == "voicetemp") {
			voicetemp = $("#" + KeyDes).html();
		}
		$("#" + KeyDes).html(IsKey);

	}
	if (IsDefaultkey != "") {
		if (KeyDes == "texttemp") {
			texttemp = $("#" + KeyDes).html();
		}
		if (KeyDes == "pictemp") {
			pictemp = $("#" + KeyDes).html();
		}
		if (KeyDes == "voicetemp") {
			voicetemp = $("#" + KeyDes).html();
		}
		$("#" + KeyDes).html(IsDefaultkey);

	}
	var editor1 = "";
	KindEditor.ready(function(K) {
		editor1 = K.create('textarea[id="responseDesText"]', {
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
	$(function() {
		newResponseValidate();
		var rid = "${objList.responseType}";
		ridClass = "${objList.responseClass}";
		if (rid == "") {
			rid = "${objList1[0].responseType}";
		}
		if (ridClass == "") {
			ridClass = "${objList1[0].responseClass}";
		}
		if (rid == "1") {
			$("#f_pic").css("display", "none");
			$("#f_video").css("display", "none");
			$("#f_text").addClass("active");
			$("#tab_5_1").addClass("active");
		} else if (rid == "2") {
			$("#f_text").css("display", "none");
			$("#f_video").css("display", "none");
			$("#f_pic").addClass("active");
			$("#tab_5_1").removeClass("active");
			$("#tab_5_2").addClass("active");
		} else if (rid == "3") {
			$("#f_pic").css("display", "none");
			$("#f_text").css("display", "none");
			$("#f_video").addClass("active");
			$("#tab_5_1").removeClass("active");
			$("#tab_5_3").addClass("active");
		}
		if (ridClass == "1") {
			$("#responseClassText1").attr("checked", "checked");
			$("#responseClassPic1").attr("checked", "checked");
			$("#responseClassVoice1").attr("checked", "checked");

		} else if (ridClass == "2") {
			$("#responseClassText2").attr("checked", "checked");
			$("#responseClassPic2").attr("checked", "checked");
			$("#responseClassVoice2").attr("checked", "checked");
		} else if (ridClass == "3") {
			$("#responseClassText3").attr("checked", "checked");
			$("#responseClassPic3").attr("checked", "checked");
			$("#responseClassVoice3").attr("checked", "checked");
		} else if (ridClass == "4") {
			$("#responseClassText4").attr("checked", "checked");
			$("#responseClassPic4").attr("checked", "checked");
			$("#responseClassVoice4").attr("checked", "checked");
		}
		if ($("#smallphototd").children().html() == undefined) {
			$("#smallphototd")
					.html(
							"<div><input type='file' id='filesmall' onchange='fileChange(this);'  name='responsePicfile' class='photo'><br></div>");
		}
	});
	//删除
	function delsimg(id, kthis) {
		if (confirm("确定要删除这张图片吗")) {
			$.ajax({
				url : '${SHOPDOMAIN }/system/response/delsimg.html',
				type : 'POST',
				data : {
					'showId' : id
				},
				timeout : 100000,
				error : function() {

				},
				success : function(result) {
					$(kthis).parent().remove();
					//这个地方暂未确定
				}
			});
		}
	}
	//检测文件上传类型
	function fileChange(target) {
		//检测上传文件的类型
		var imgName = $(target).val();
		var ext, idx;
		if (imgName == '') {
			return;
		} else {
			idx = imgName.lastIndexOf(".");
			if (idx != -1) {
				ext = imgName.substr(idx + 1).toUpperCase();
				ext = ext.toLowerCase();
				// alert("ext="+ext);
				if (ext != 'jpg' && ext != 'png' && ext != 'jpeg'
						&& ext != 'gif') {
					//document.all.submit_upload.disabled=true;
					alert("只能上传.jpg  .png  .jpeg  .gif类型的文件!");
					$(target).val("");
					return;
				}
			} else {
				//document.all.submit_upload.disabled=true;
				alert("只能上传.jpg  .png  .jpeg  .gif类型的文件!");
				$(target).val("");
				return;
			}
		}
	}
	/**
	 * 检测文本类型提交
	 */
	function check1() {
		editor1.sync();
		if (document.getElementById("responseTitle1")) {//存在
			if ($("input[name='responseClass']:checked").val() == "1") {
				checkisuse("form1", 1);
			} else {
				$("#form1").submit();
			}
		}
		if ($("input[name='responseClass']:checked").val() == "4") {
			checkisuse("form1", 4);
		} else {
			$("#form1").submit();
		}
	}
	/**
	 * 检测图文类型提交
	 */
	function check2() {
		if (document.getElementById("responseTitle2")) {//存在
			if ($("#responseTitle2").val() != "") {//文本
				if ($("input[name='responseClassPic']:checked").val() == "1") {
					checkisuse("form2", 1);
				} else {
					$("#form2").submit();
				}
			}
		}
		if ($("input[name='responseClassPic']:checked").val() == "4") {
			checkisuse("form2", 4);
		} else {
			$("#form2").submit();
		}
	}
	/**
	 * 检测音乐类型提交
	 */
	function check3() {

		if (document.getElementById("responseTitle3")) {//存在
			if ($("#responseTitle3").val() != "") {//文本
				if ($("input[name='responseClassVoice']:checked").val() == "1") {
					checkisuse("form3", 1);
				} else {
					$("#form3").submit();
				}
			}
		}
		if ($("input[name='responseClassVoice']:checked").val() == "4") {
			checkisuse("form2", 4);
		} else {
			$("#form3").submit();
		}

	}
	/**
	 * 如果是不同回复，那么检测是否存在同名的回复
	 */
	function checkissame(title, type, id) {
		if (title == "") {
			return;
		}
		var titlehidden = $("#" + id + "hiden").val();
		if (title == titlehidden) {
			return;
		}
		$.ajax({
			url : '${SHOPDOMAIN }/system/response/issame.html',
			type : 'post',
			async : false,
			data : {
				'title' : title,
				'type' : type
			},
			timeout : 10000,
			error : function() {
				alert('error......  ajax call return error');
			},
			success : function(result) {
				if (result == "no") {
					bootbox.dialog({
						message : "此标题已经出现回复中,不允许采用",
						title : "提示",
						buttons : {
							success : {
								label : "取消!",
								className : "white",
								callback : function() {
								}
							},
							main : {
								label : "确定!",
								className : "blue",
								callback : function() {
								}
							}
						}
					});
					$("#responseTitle1").val("");
					$("#responseTitle2").val("");
					$("#responseTitle3").val("");
				}
			}
		});
	}
	var i = 1;

	var maxcount = '${objList1size}';
	if (maxcount != "") {
		i = maxcount;
	}
	/**
	 * 添加一个
	 */
	function add() {
		if (i > 9) {
			bootbox.dialog({
				message : "最大不能超过10条",
				title : "提示",
				buttons : {
					success : {
						label : "取消!",
						className : "white",
						callback : function() {
						}
					},
					main : {
						label : "确定!",
						className : "blue",
						callback : function() {
						}
					}
				}
			});
			return;
		}

		var addstring = "<table class='tableList' id='table"+i+"'><colgroup><col style='width: 24%;'></colgroup><tbody><tr class='no-border'><td class='tright'><label for='cname'>标题："
				+ "</label></td><td><label class='input_val' style=' margin-top: 5px;'><input type='text' class='form-control error' name='titlename' id='titlename"
				+ i
				+ "'  value=''"
				+ "'></label>&nbsp;&nbsp;</td></tr><tr  class='odd'><td class='tright'><label for='cname'>图片：</label></td>"
				+ "<td>"
				+ "<input type='hidden' name='responsePics' size='6'	class='text inputmedium' readonly value='' style='width: 60%' /> <span class='help-inline'>"
				+ "&nbsp;&nbsp;<img style='margin-top: 0px;margin-right: 15px;display:none'  src='${dl.picUrl }' width='40px' height='30px'><a class='btn_blue button insertimage'>选择图片</a> </span></div>"
				+ "</div> </td></tr><tr><td class='tright'><label for='cname'>描述：</label></td><td style='height:90px'><textarea class='form-control' name='responseDes' id='responseDes' style='width: 300px;height: 80px' rows='' cols=''></textarea></td></tr>"
				+ "<tr><td class='tright'><label for='cname'>回复：</label></td><td><select name='isOuts' class='form-control'  style='margin-top: 10px;width:400px' onchange='isOutClick(this)'><option value='2' selected='selected'>外链</option> </select> </td></tr><tr><td class='tright'></td>"
				+ "<td>"
				+ "<input type='text' class='form-control responseHighmusicUrl"+i+"' name='responseHighmusicUrl' style='margin-top: 8px;' id='responseHighmusic2'></td></tr>"
				+ "<tr><td></td><td><a href='javascript:void(0)' onclick='delthis(this,0)' class='button btn red' style='float:right;margin-left: 60px;margin-right:50px'>删除</a></td></tr>";
		$("#appendstring").append(addstring);
		i++;
	}
	/**
	 * 删除
	 */
	function delthis(kthis, kid) {
		if (kid == "0") {
			$(kthis).parent().parent().parent().parent().remove();
			i--;
			return;
		}
		bootbox.dialog({
			message : "确定要删除这条信息吗？",
			title : "提示",
			buttons : {
				success : {
					label : "取消!",
					className : "white",
					callback : function() {
					}
				},
				main : {
					label : "确定!",
					className : "blue",
					callback : function() {

						$.ajax({
							url : '${SHOPDOMAIN }/system/response/delthis.html',
							type : 'post',
							async : false,
							data : {
								'kid' : kid
							},
							timeout : 10000,
							error : function() {
								alert('error......  ajax call return error');
							},
							success : function(result) {
								$(kthis).parent().parent().parent().parent()
										.remove();
								i--;
							}
						});
					}
				}
			}
		});

	}
	/**
	 * 检测是否存在首次回复
	 */
	function checkisuse(form, model) {
		if (ridClass == "1") {
			$("#" + form).submit();
			return;
		}
		if (ridClass == "4") {
			$("#" + form).submit();
			return;
		}

		$.ajax({
			url : '${SHOPDOMAIN }/system/response/isusetype.html',
			type : 'post',
			async : false,
			timeout : 10000,
			data : {
				'model' : model
			},
			error : function() {
				alert('error......  ajax call return error');
			},
			success : function(result) {
				if (result == "1") {
					bootbox.dialog({
						message : "其他回复已存在首次回复",
						title : "提示",
						buttons : {
							success : {
								label : "取消!",
								className : "white",
								callback : function() {
								}
							},
							main : {
								label : "确定!",
								className : "blue",
								callback : function() {
								}
							}
						}
					});
					return false;
				} else if (result == "4") {
					bootbox.dialog({
						message : "其他回复已存在缺省回复",
						title : "提示",
						buttons : {
							success : {
								label : "取消!",
								className : "white",
								callback : function() {
								}
							},
							main : {
								label : "确定!",
								className : "blue",
								callback : function() {
								}
							}
						}
					});
					return false;
				} else {
					$("#" + form).submit();
					return true;
				}
			}
		});
	}

	/**
	 * 恢复到其他命令模式
	 */
	function back(type) {

		if (type == "text") {
			if (texttemp == "") {
				return;
			}
			$("#texttemp").html(texttemp);
		}
		if (type == "pic") {
			if (pictemp == "") {
				return;
			}
			$("#pictemp").html(pictemp);
		}
		if (type == "voice") {
			if (voicetemp == "") {
				return;
			}
			$("#voicetemp").html(voicetemp);
		}
	}

	/**
	 * 检测是否存在首次回复
	 */
	function showKey(type, model) {
		if (type == "text") {
			if (texttemp == "") {
				texttemp = $("#texttemp").html();
				$("#texttemp").html("");
			}
		}
		if (type == "pic") {
			if (pictemp == "") {
				pictemp = $("#pictemp").html();
				$("#pictemp").html("");
			}
		}
		if (type == "voice") {
			if (voicetemp == "") {
				voicetemp = $("#voicetemp").html();
				$("#voicetemp").html("");
			}
		}

		if (model == "0") {
			$
					.ajax({
						url : '${SHOPDOMAIN }/system/response/getkeyfirst.html',
						type : 'post',
						async : false,
						timeout : 10000,
						error : function() {
							alert('error......  ajax call return error');
						},
						success : function(result) {
							var htmltemp = "<input type='hidden' name='responseTitle' id='"+type+"title'><select id='level"
									+ type
									+ "' onchange='showlevel2(this.value,\""
									+ type + "\")'>";
							htmltemp = htmltemp + result + "</select>";
							$("#" + type + "temp").html(htmltemp);
						}
					});
		} else if (model == "1") {
			$("#" + type + "temp").html("缺省回复");
		}

	}

	/**
	 * key的第二个选择select
	 */
	function showlevel2(val, type) {
		$("#level2").remove();
		$.ajax({
			url : '${SHOPDOMAIN }/system/response/getkeyseconde.html',
			type : 'post',
			async : false,
			timeout : 10000,
			data : {
				'pid' : val
			},
			error : function() {
				alert('error......  ajax call return error');
			},
			success : function(result) {
				if (result != "") {
					var htmltemp = "<select id='level2' >";
					htmltemp = htmltemp + result + "</select>";
					$("#" + type + "temp").append(htmltemp);
				}
			}
		});
	}

	/**
	 * 如果选择了key 那么检测 key是否已经用过，
	 */
	function iskeyused(val, form, id) {
		$.ajax({
			url : '${SHOPDOMAIN }/system/response/iskeyused.html',
			type : 'post',
			async : false,
			timeout : 10000,
			data : {
				'keyid' : val,
				'id' : ids
			},
			error : function() {
				alert('error......  ajax call return error');
			},
			success : function(result) {
				if (result == "") {
					alert("该key已经使用，请更换其他的key");
					return;
				} else {
					$("#" + form).submit();
				}
			}
		});
	}

	function isOutClick(kthis) {
		if ($(kthis).val() == 2) {
			$(kthis).parent().parent().next().css("display", "none");
			$(kthis).parent().parent().next().next().css("display", "");
		} else {
			$(kthis).parent().parent().next().next().css("display", "none");
			$(kthis).parent().parent().next().css("display", "");
		}

	}
</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
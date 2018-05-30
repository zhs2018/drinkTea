<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no,email=no" name="format-detection">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>答题赢好礼</title>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/style.css">
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/style.css">
<link
	href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" />
<style type="text/css">
.layui-m-layercont {
	line-height: 1
}

.answer-wrong p {
	font-size: 1rem;
}

.layui-m-layercont {
	border-bottom: none
}
</style>
</head>
<body>
	<div class="verticalScreen">
		<div class="answer-bg">
			<div class="answer-div">
				<p class="title">${subject.name}</p>
				<input name="id" id="id" value="${subject.id}" type="text"
					hidden="true"> <input name="nums" id="nums" value="${num}"
					hidden="true" type="text">

				<c:if test="${!empty subject.img}">
					<p class="image">
						<img src="${RESOURCEDOMAIN}/${subject.img}" alt="此题没有图片"
							width="150" height="150">
					</p>
				</c:if>
			</div>
			<div>
				<span style="font-size: 16px;">类别:</span>
				<ul class="answer-option1">
				</ul>
			</div>
			<div>
				<P>
					<span style="font-size: 16px;">省份:</span>
				</P>
				<ul class="answer-option2">
				</ul>
			</div>
			<div>
				<P>
					<span style="font-size: 16px;">季节:</span>
				</P>
				<ul class="answer-option3">
				</ul>
			</div>
			<!-- <ul class="answer-option">
			</ul> -->
		</div>
		<div class="dui" style="display: none">
			<div class="answer-yes">
				<p>恭喜！答对了！</p>
			</div>
		</div>
		<div class="cuo" style="display: none">
			<div class="answer-wrong">
				<p class="t">抱歉！答错了</p>
				<p class="a">
					正确答案：<label></label>
				</p>
			</div>
		</div>
	</div>
	<p class="crossScreen">请竖屏</p>
	<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
	<script src="${RESOURCEDOMAIN}/js/ui.js"></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
	<script src="${RESOURCEDOMAIN}/js/weixin/layer_mobile/layer.js"></script>
	<script
		src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="http://bootboxjs.com/bootbox.js"></script>

	<script>
		<c:forEach items="${subject.opList1}" var="option1" varStatus="vs">
		$(".answer-option1").append(
				$('<li><label></label>'
						+ String.fromCharCode(parseInt('${vs.index+65}'))
						+ " ${option1.option}</li>"));
		</c:forEach>

		<c:forEach items="${subject.opList2}" var="option2" varStatus="vs">
		$(".answer-option2").append(
				$("<li><label></label>"
						+ String.fromCharCode(parseInt('${vs.index+65}'))
						+ " ${option2.option}</li>"));
		</c:forEach>

		<c:forEach items="${subject.opList3}" var="option3" varStatus="vs">
		$(".answer-option3").append(
				$("<li><label></label>"
						+ String.fromCharCode(parseInt('${vs.index+65}'))
						+ " ${option3.option}</li>"));
		</c:forEach>

		var fg = 1;
		$(".answer-option1 li").click(function() {
			if (fg == 1) {
				this.style.backgroundColor = "red";
				getData1(this);
				fg = 2;
			} else {
				alert("答案已提交");
			}
		});

		var flag = true;
		function getData1(li) {
			var index = $(".answer-option1 li").index(li);
			var num = $("#nums").val();
			if (num != null && num != "") {
				num = num;
			} else {
				num = 0;
			}
			$
					.ajax({
						url : "${RESOURCEDOMAIN}/answer/next",
						type : "post",
						dataType : "json",
						data : {
							"answer" : index,
							"sign" : 1,
							"num" : num
						},
						success : function(data) {
							console.log(JSON.stringify(data));
							if (data.result == "0") {
								$("#nums").val(data.num);
								if (data.num == 1) {
									alert("做答完一问");
								} else if (data.num == 2) {
									alert("做答完两问");
								} else if (data.num == 3) {
									alert("做答完三问，请等在结果");
									setTimeout(
											function() {
												window.location.href = "${RESOURCEDOMAIN}/answer/answerSummary";
											}, 1000);
								}

							}
						},
						error : function(data) {
							alert("服务器未响应！");
						}
					})
		}
		var mg = 1;
		$(".answer-option2 li").click(function() {
			if (mg == 1) {
				this.style.backgroundColor = "red";
				getData2(this);
				mg = 2;
			} else {
				alert("答案已提交");
			}

		});
		var flag = true;
		function getData2(li) {
			var index = $(".answer-option2 li").index(li);
			var num = $("#nums").val();
			if (num != null && num != "") {
				num = num;
			} else {
				num = 0;
			}
			$
					.ajax({
						url : "${RESOURCEDOMAIN}/answer/next",
						type : "post",
						dataType : "json",
						data : {
							"answer" : index,
							"sign" : 2,
							"num" : num
						},
						success : function(data) {
							console.log(JSON.stringify(data));
							if (data.result == "0") {
								$("#nums").val(data.num);
								if (data.num == 1) {
									alert("做答完一问");
								} else if (data.num == 2) {
									alert("做答完两问");
								} else if (data.num == 3) {
									alert("做答完三问，请等在结果");
									setTimeout(
											function() {
												window.location.href = "${RESOURCEDOMAIN}/answer/answerSummary";
											}, 1000);
								}
							}
						},
						error : function(data) {
							alert("服务器未响应！");
						}
					})
		}

		var pg = 1;
		$(".answer-option3 li").click(function() {
			if (pg == 1) {
				this.style.backgroundColor = "red";
				getData3(this);
				pg = 2;
			} else {
				alert("答案已提交");
			}

		});
		var flag = true;

		function getData3(li) {
			var index = $(".answer-option3 li").index(li);
			var num = $("#nums").val();
			if (num != null && num != "") {
				num = num;
			} else {
				num = 0;
			}
			$
					.ajax({
						url : "${RESOURCEDOMAIN}/answer/next",
						type : "post",
						dataType : "json",
						data : {
							"answer" : index,
							"sign" : 3,
							"num" : num
						},
						success : function(data) {
							console.log(JSON.stringify(data));
							if (data.result == "0") {
								$("#nums").val(data.num);
								if (data.num == 1) {
									alert("做答完一问");
								} else if (data.num == 2) {
									alert("做答完两问");
								} else if (data.num == 3) {
									alert("做答完三问，请等在结果");
									setTimeout(
											function() {
												window.location.href = "${RESOURCEDOMAIN}/answer/answerSummary";
											}, 1000);
								}
							}
						},
						error : function(data) {
							alert("服务器未响应！");
						}
					})
		}

		var result = "${result}";
		if (result == "1") {
			bootbox.dialog({
				message : "${message} ",
				title : "提示",
				buttons : {
					main : {
						label : "确定!",
						className : "blue",
						callback : function() {
						}
					}
				}
			});
		}

		pushHistory();
		window.addEventListener("popstate", function(e) {
			if (confirm("确定要退出答题？"))
				WeixinJSBridge.call('closeWindow');
		}, false);

		function pushHistory() {
			var state = {
				title : "title",
				url : "#"
			};
			window.history.pushState(state, "title", "#");
		}
	</script>
	<script src="${RESOURCEDOMAIN}/js/system/listContact.js"></script>
	<script>
		/* 	 function get(li) {
		 if (flag) {
		 var index = $(".answer-option1 li").index(li);
		 alert("index"+index);
		 var answer = String.fromCharCode(index + 65);
		 alert("answer"+answer);
		 var check = $(li).html().split("<label></label>", 2);
		 alert("check"+check);
		 bootbox
		 .dialog({
		 message : "您确认选择 : " + check[1] + "?",
		 title : "提示",
		 buttons : {
		 main : {
		 label : "确定!",
		 className : "blue",
		 callback : function() {
		 flag = false;
		 var id =$("#id").val();
		 var num = $(".answer-t label").html()
		 .trim();
		 alert("num"+num);
		 .ajax({
		 url :"${RESOURCEDOMAIN}/answer/next",
		 type : "post",
		 dataType : "json",
		 data : {
		 "num" :id,
		 "answer" : index,
		 "sign":1
		 },
		 success : function(data) {
		 console
		 .log(JSON
		 .stringify(data));
		 if (data.result == "0") {
		 flag = true;
		 console.log(flag);
		 if (data.rightOrWrong == "0") {
		 $(
		 ".answer-option li")
		 .eq(
		 index)
		 .addClass(
		 "yes");
		 layer
		 .open({
		 content : $(
		 '.dui')
		 .html(),
		 yes : function(
		 index) {
		 }
		 });
		 } else {
		 $(
		 ".answer-option li")
		 .eq(
		 index)
		 .addClass(
		 "wrong");
		 $(
		 ".answer-wrong .a label")
		 .html(
		 data.answer);
		 layer
		 .open({
		 content : $(
		 '.cuo')
		 .html(),
		 yes : function(
		 index) {
		 }
		 });
		 }
		 setTimeout(
		 function() {
		 layer
		 .closeAll();
		 $(
		 ".title-num")
		 .html(
		 data.num);
		 $(
		 ".title")
		 .html(
		 data.subject.name);
		 $(
		 ".image")
		 .html(
		 '<c:if test="${!empty'
		 + data.subject.img
		 + '}">'
		 + '<p class="image"> <img src="${RESOURCEDOMAIN}/'+data.subject.img+'" alt="此题没有图片"  width="150" height="150" color = "red"></p>'
		 + '</c:if>'

		 '<img src="${RESOURCEDOMAIN}/'+data.subject.img+'" alt="banner" width="150" height="150" >'	 );
		 $(
		 ".answer-option")
		 .empty();
		 $(
		 data.subject.options)
		 .each(
		 function(
		 i,
		 element) {
		 $(
		 ".answer-option")
		 .append(
		 $("<li><label></label>"
		 + String
		 .fromCharCode(i + 65)
		 + " "
		 + element.option
		 + "</li>"))
		 });
		 $(
		 ".answer-option li")
		 .click(
		 function() {
		 getData(this);
		 });
		 }, 3000);
		 } else if (data.reault == "1") {
		 window.location.href = "${RESOURCEDOMAIN}/answer/index";
		 } else if (data.result == "2") {
		 if (data.rightOrWrong == "0") {
		 $(
		 ".answer-option li")
		 .eq(
		 index)
		 .addClass(
		 "yes");
		 layer
		 .open({
		 content : $(
		 '.dui')
		 .html(),
		 yes : function(
		 index) {
		 }
		 });
		 } else {
		 $(
		 ".answer-option li")
		 .eq(
		 index)
		 .addClass(
		 "wrong");
		 $(
		 ".answer-wrong .a label")
		 .html(
		 data.answer);
		 layer
		 .open({
		 content : $(
		 '.cuo')
		 .html(),
		 yes : function(
		 index) {
		 }
		 });
		 }
		 setTimeout(
		 function() {
		 layer
		 .closeAll();
		 window.location.href = "${RESOURCEDOMAIN}/answer/answerSummary";
		 }, 3000);
		 }
		 },
		 error : function(data) {
		 alert("服务器未响应！");
		 }
		 })
		 }
		 }
		 }
		 });

		 } else {
		 bootbox.dialog({
		 message : "已经提交答案，请等待服务器响应！ ",
		 title : "提示",
		 buttons : {
		 main : {
		 label : "确定!",
		 className : "blue",
		 callback : function() {
		 }
		 }
		 }
		 });
		 }
		 } */
		/* function getData2(li){
		var index = $(".answer-option2 li").index(li);
		alert("index"+index);
		var answer = String.fromCharCode(index + 65);
		alert("answer"+answer);
		var check = $(li).html().split("<label></label>", 2);
		alert("check"+check);	bootbox
		.dialog({
			message : "您确认选择 : " + check[1] + "?",
			title : "提示",
			buttons : {
				main : {
					label : "确定!",
					className : "blue",
					callback : function() {
						flag = false;
						var id =$("#id").val();
						$.ajax({
									url :"${RESOURCEDOMAIN}/answer/next",
									type : "post",
									dataType : "json",
									data : {
										"num" :id,
										"answer" : index,
										"sign":1
									},
									success : function(data) {
										console.log(JSON.stringify(data));
										if (data.result == "0") {
		                                alert("页面不刷新");
									}
									},
									error : function(data) {
										alert("服务器未响应！");
									}

								})
					}
				}
			}
		});

		}
		 */

		/* 		mizhu.confirm('', '确定选择此选项？', function(flag) {
		 if(flag) {
		 mizhu.alert('温馨提醒', '确定');

		 }
		 }) */
	</script>
</body>
</html>

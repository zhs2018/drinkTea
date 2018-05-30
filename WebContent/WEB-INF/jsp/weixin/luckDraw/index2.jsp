<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <title>幸运大抽奖</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title></title>
		<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/style1.css" />
	</head>

	<body>
		<div class="cj">
			<ul class="cj-list">
			<c:forEach items="${draw}" var="draw" varStatus="vs">
              <li class="hh"><img src="../image/01.png" /><span>${draw.id},${draw.prizeName},${draw.prizeImg}</span></li>
				</c:forEach>
			</ul>
			 <div class="dj-cj">
				<img src="../image/02.png"  />
				<input type="hidden" id="drawId" value=""/>
			</div>
			<input type="hidden" name="message" id ="message" value="${message}">
			<input type="hidden" name="mks" id = "mks" value="${mks}">
			<input type="hidden" name="mess" id = "mess" value="${mess}">
			<div class="cj-result" style="display: none;">
				<div class="jl">
				    <p>恭喜您！</p>
					<p id = "name"></p>
					<div id = "img"></div>
				</div>
				<div class="config" onClick="determine()">
					确定领取
				</div>
			</div>
		</div>
		<script src="${RESOURCEDOMAIN}/js/js/js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="${RESOURCEDOMAIN}/js/js/js/layer/layer.js"></script>
		<script type="text/javascript" charset="utf-8">
		$(function() {
			$("span").hide();
			var mks = $("#mks").val();
			if (mks != null && mks != "") {
				alert(mks);
				window.location.href = "${DOMAIN}/luckdraw/prizes";
			}

			var mes = $("#message").val();
			if (mes != null && mes != "") {
				alert(mes);
				window.location.href = "${DOMAIN}/luckdraw/prizes";
			}

			var mess = $("#mess").val();
			if (mess != null && mess != "") {
				alert(mess);
				window.location.href = "${DOMAIN}/manageCity";
			}
		});

		//确定领取
		function determine(){
			var id=$("#drawId").val();
			$.ajax({
				url:"${DOMAIN}/luckdraw/drawPrizes",
				type:"post",
				data:{
					"id":id
				},
			    dataType:"json",
			    success : function(data) {
					if (data.result == 1) {
						window.location.href = "${DOMAIN}/luckdraw/drawinfo";
					} else if (data.result == 2) {
						window.location.href = "${DOMAIN}/teaKing/index";
					}
				},
				error : function(data) {
					alert("系统错误");
				}
			});

		}
			$(function() {
				$('.hh').click(function() {
				var m = $(this).children("span").html();
				var arr = m.split(",");
				var id=arr[0];
				var name=arr[1];
				var img = arr[2];
				$("#drawId").val(id);
				$("#name").text(name);
				$("#img").html('<img src="${RESOURCEDOMAIN}/' +img+'"></img>');
				 	layer.open({
						type: 1,
						skin: 'cj-result', //
						closeBtn: 2, //
						anim: 2,
						scrollbar: false,
						shadeClose: true, //
						content: $('.cj-result').html(),
					});
				});

			});

			function stopScroll(e) {
				document.documentElement.style.overflow = 'hidden';
			}

		</script>
	</body>

</html>
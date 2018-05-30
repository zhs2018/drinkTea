<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no,email=no" name="format-detection">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>我的地址</title>
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/tea.css">
<link rel="stylesheet" href="${RESOURCEDOMAIN}/css/layer_uploader.css" />
<style>
body {
	background: #eee;
}
</style>
</head>
<body>

	<div class="verticalScreen">
		<c:forEach items="${objList}" var="obj" varStatus="vs">
			<div class="m_add_div">
				<a href="#" onclick="jump(${obj.id},${mpId})"> <input
					type="text" id="Id" name="id" placeholder="" value="${obj.id}"
					hidden="true" /> <input type="text" id="mpId" name="mpId"
					placeholder="" value="${mpId}" hidden="true" /> <input type="text"
					id="mpTab" name="mpTab" placeholder="" value="${mpTab}"
					hidden="true" />
					<p class="m_name">${obj.userName}</p>
					<p class="m_tel">${obj.telPhone}</p>
					<p class="m_add">${obj.addressDetails}</p>
				</a>
			</div>

			<div class="m_edit">
				<c:if test="${obj.status == 2}">
					<div class="m_m style" onclick="change(${obj.userId},${obj.id})">
						<label class='cart_settlement_q'> <span
							class="m_add_xz style"></span><label>默认收货地址</label></label>
					</div>
				</c:if>
				<c:if test="${obj.status != 2}">
					<div class="m_m" onclick="change(${obj.userId},${obj.id})">
						<label class='cart_settlement_q'> <span
							class="m_add_xz style"></span><label>默认收货地址</label></label>
					</div>
				</c:if>
				<div class="m_div_bj">
					<p class="m_bj">
						<a href="javascript:void(0)" onclick="edit_Address('${obj.id}')">
							<img src="${RESOURCEDOMAIN}/image/weixin/add_bj.png" alt="编辑" /><label>编辑</label>
						</a>
					</p>
					<p class="m_delet">
						<img src="${RESOURCEDOMAIN}/image/weixin/delete.png" alt="删除" /><label>删除</label>
					</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id="delete_Popup" style="display: none">
		<div style="background: #fff; padding-top: 20px; border-radius: 6px;">
			<p class="auth_close_p">确定要删除该地址吗？</p>
			<div class="div_btn_div">
				<div class="div_btn_cancel" onclick="layer.closeAll()">取消</div>
				<div class="div_btn_ok" onclick="del_Address()">确认</div>
			</div>
		</div>
	</div>
	<div class="heigh3"></div>
	<a href="${RESOURCEDOMAIN}/add_Address"><div class="refund-btn">添加新地址</div></a>
	<p class="crossScreen">请竖屏</p>
	<script src="${RESOURCEDOMAIN}/js/layer1/js/jquery-1.11.2.min.js"></script>
	<script src="${RESOURCEDOMAIN}/js/layer1/js/html_rem.js"></script>
	<script src="${RESOURCEDOMAIN}/js/layer1/layer/layer.js"></script>
	<script>

function addAdress(){
	var mpId=$("#mpId").val();
	alert(mpId);
	 window.location.href="${RESOURCEDOMAIN}/add_Address?mpId"+mpId;
}
 function jump(id,mpId){
	 var tab = $("#mpTab").val();
	 if(tab==2){
		 window.location.href="${RESOURCEDOMAIN}/confirmOrder?id="+id+"&proId="+mpId;
	 }else if(tab==3) {
		 window.location.href="${RESOURCEDOMAIN}/confirmOrder?id="+id+"&proId="+mpId;
	 }else{
		 window.location.href="${RESOURCEDOMAIN}/freeRecevice?id="+id+"&proId="+mpId;
	 }

}
 function change(uid,id){
  $.ajax({
	  url:"${RESOURCEDOMAIN}/change_Address",
	  type:"post",
	  data:{
		  userId:uid,
		  id:id,

	  },
	  dataType:"json",
	  success:function(data){
		  if(data.status==1){
			  setTimeout(function(){
					window.location.href = "${RESOURCEDOMAIN}/add_Address";
				}, 1000);
		  }else if(data.status ==0){
			  layer.msg(data.Message);
		  }
	  },
	  error:function(data){
		  layer.msg(data.Message);
	  }
  })
 }
function del_Address(){
	var id=$("#Id").val();
 	$.ajax({
		url:"${RESOURCEDOMAIN}/del_Address",
		type:"post",
		data:{
			id:id
		},
		dataType:"json",
		success:function(data){
			if(data.status==1){
                window.location.href="${RESOURCEDOMAIN}/myAddress";
		   }else if(data.status==0){
			   window.location.href="${RESOURCEDOMAIN}/myAddress";
		}
		},
		error:function(data){
			window.location.href="${RESOURCEDOMAIN}/myAddress";
		}
	})
}
  function edit_Address(id){
      window.location.href="${RESOURCEDOMAIN}/edit_Address/"+id;
  }

</script>
	<script>

    $(function () {

    	$(".refund-btn").css({"position":"fixed","bottom":"0px"})
        //删除地址
        $('.m_delet').click(function () {
            layer.open({
                content: $('#delete_Popup').html()
            });
            $('.div_btn_ok').click(function () {
                layer.closeAll();
            })
        });

        $('.m_m').click(function () {
            $('.m_add_div').each(function () {
                $(this).find('.m_m ').removeClass('style')
            });
            $(this).addClass('style')
        });

        $(".m_m").click(function(){
        	$(".m_m").removeClass("style");
        	$(this).addClass("style");
        });
    })
</script>
</body>
</html>
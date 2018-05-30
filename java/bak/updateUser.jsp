<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>我的信息</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/bound.css">
    <style type="text/css">
      #userName{
        font-size:0.9rem
      }
    </style>
</head>
<body>
<form action="${RESOURCEDOMAIN}/shop/updateUserInfo" method="post" class="form" >
<p class="salesman-title">基本信息：</p>
<div class="salesman-info">
    <div><label>昵称：</label><input id="userName" type="text" name="userName" placeholder="请填写您的昵称" value="${user.userName}"></div>
    <div><label>性别： </label>
    	<c:if test="${user.sex == 2}"><span>男</span> <span class="active">女</span></c:if>
    	<c:if test="${user.sex != 2}"><span  class="active">男</span> <span>女</span></c:if>
    </div>
    <div id="choice"><label>年龄段：</label>
    	<c:if test="${empty user.age}"> <p class="salesman-xz" cid>请选择</p></c:if>
		<c:if test="${user.age == 1}"> <p class="salesman-xz" cid="id_1">小于18岁</p></c:if>
		<c:if test="${user.age == 2}"> <p class="salesman-xz" cid="id_2">18岁~24岁</p></c:if>
		<c:if test="${user.age == 3}"> <p class="salesman-xz" cid="id_3">25岁~30岁</p></c:if>
		<c:if test="${user.age == 4}"> <p class="salesman-xz" cid="id_4">30岁~40岁</p></c:if>
		<c:if test="${user.age == 5}"> <p class="salesman-xz" cid="id_5">40岁~50岁</p></c:if>
		<c:if test="${user.age == 6}"> <p class="salesman-xz" cid="id_6">50岁~60岁</p></c:if>
		<c:if test="${user.age == 7}"> <p class="salesman-xz" cid="id_7">大于60岁</p></c:if>
      </div>
     <div><label>姓名：</label>
     	<input id="name" type="text" name="name" placeholder="请填写您的真实姓名" value="${user.name}">
     </div>
     <div><label>手机号：</label>
     	<input id="phone" type="number" name="phone" placeholder="请填写您的真实电话" value="${user.phone}">
     </div>
</div>

<div id="salesman_Popup" style="display: none">
    <ul class="salesman-ul">
        <li class="active" id="id_1">小于18岁</li>
        <li id="id_2">18岁~24岁</li>
        <li id="id_3">25岁~30岁</li>
        <li id="id_4">30岁~40岁</li>
        <li id="id_5">40岁~50岁</li>
        <li id="id_6">50岁~60岁</li>
        <li id="id_7">大于60岁</li>
    </ul>
</div>

<input type="hidden" class="sex" name="sex" value="${user.sex}">
<input type="hidden" class="age" name="age" value="${user.age}">
<input type="hidden" name="id" value="${user.id}">
<div class="salesman-btn">提交信息</div>
</form>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/layer_mobile/layer.js"></script>
<script>
    $(function () {
    	var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
        $('.salesman-info div span').click(function () {
            $(this).addClass('active').siblings().removeClass('active')
        });

        $('#choice').click(function () {
                layer.open({
                    content: $('#salesman_Popup').html(),
                    type: 1
                    ,
                    anim: 'up'
                    ,
                    style: 'position:fixed; bottom:0; left:0; width: 100%; height: 29.375rem; padding:10px 0; border:none;'
                });
                if ($('.salesman-xz').attr('cid') != '') {
                    var cid=$('.salesman-xz').attr('cid');
                    $('.salesman-ul li').each(function () {
                         if($(this).attr('id')==cid){
                             $(this).addClass('active').siblings().removeClass('active');
                         }
                    })
                 }

                $('.salesman-ul li').click(function () {
                    var id = $(this).attr('id');
                    $(this).addClass('active').siblings().removeClass('active');
                    $('.salesman-xz').text($(this).text());
                    $('.salesman-xz').attr('cid', id);
                    layer.closeAll()
                });
        })
        $(".salesman-btn").click(function(){
        	var sex = $(".salesman-info span").index($(".salesman-info .active").eq(0));
        	$(".sex").val((sex+1));
        	if($.trim($("#userName").val()) == ''){
        		alert("亲，请输入您的昵称！");
        		return;
        	}
        	var cid=$('.salesman-xz').attr('cid');;
        	if(cid == ''){
        		alert("亲，您忘了选择年龄段！");
        		return;
        	}
        	$(".age").val(cid.split("_",2)[1]);
        	if($.trim($("#name").val()) == ''){
        		alert("亲，请输入您的真实姓名！");
        		return;
        	}
        	var phone = $("#phone").val();
        	if(!reg.test(phone)){
        		alert("亲，您的手机号格式输入错误！");
        		return;
        	}
        	$(".form").submit();
        });
        var result = "${result}";
        if(result == "1"){
        	alert("${message}");
        }else if(result == "0"){
        	alert("${message}");
        	window.location.href = "${RESOURCEDOMAIN}/shop/personalCenter";
        }
    })
</script>
</body>
</html>
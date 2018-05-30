<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>关联信息</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/bound.css">
</head>
<body>
<form action="relate" method="post" class="form">
<p class="salesman-title">请填写以下信息：</p>
<div class="salesman-info">
    <div><label>业务员工号：</label><input id="id" type="number" name="id" placeholder="请填写与您对接业务员工号" value="${id}"></div>
    <div><label>您的性别： </label> <span class="active">男</span> <span>女</span></div>
    <div id="choice"><label>您的年龄段：</label>
        <p class="salesman-xz" cid>请选择</p></div>
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

<input type="hidden" class="sex" name="sex" value="${sex}">
<input type="hidden" class="age" name="age" value="${age}">
<input type="hidden" name="path" value="${path}">
<div class="salesman-btn">提交信息</div>
</form>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/layer_mobile/layer.js"></script>
<script>
    $(function () {
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
        	var cid=$('.salesman-xz').attr('cid');;
        	if($("#id").val().trim() == ""){
        		alert("亲，工号不能为空奥！");
        		return;
        	}
        	if($("#id").val().trim().length >=9){
        		alert("亲，工号输入格式不正确！");
        		return;
        	}
        	if(cid == ''){
        		alert("亲，您忘了选择年龄段！");
        		return;
        	}
        	$(".age").val(cid.split("_",2)[1]);
        		$(".form").submit();
        });
        var result = "${result}";
        if(result == "1"){
        	alert("${message}");
        }
    })
</script>
</body>
</html>
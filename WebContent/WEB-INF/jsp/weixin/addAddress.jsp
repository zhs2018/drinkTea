<%@ page language="java" contentType="text/html; charset=UTF-8"  isELIgnored="false"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>添加地址</title>
    <link rel="stylesheet" href=" ${RESOURCEDOMAIN}/css/weixin/tea.css">
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/layer_uploader.css" />
<script type="text/javascript" src="${RESOURCEDOMAIN}/js/layer1/layer/layer.js"></script>
</head>
<body>
<div class="verticalScreen">
<form action="${RESOURCEDOMAIN}/save_Address" method="post" id="formId">
    <div class="add_div">
        <!--收货人-->
        <div class="add_h">
            <label>收货人</label>
             <input type="text" id ="userName" name="userName" placeholder="" value="${obj.userName} "/>
             <input type="text" name="id" placeholder="" value="${obj.id}" hidden="true"/>
        </div>
        <!--联系电话-->
        <div class="add_h">
            <label>联系电话</label>
         <input type="text"  id = "telPhone" name="telPhone" placeholder="" value="${obj.telPhone}" >
        </div>
        <!--所在地区-->
         <div class="add_h">
            <label>所在地区</label>
            <select id="s_province" name="province"></select>  
            <select id="s_city" name="city" ></select>  
            <select id="s_county" name="area"></select>
            <script  src="${RESOURCEDOMAIN}/js/weixin/linkage.js" type="text/javascript"></script>
            <script type="text/javascript">_init_area();</script>
        </div>

<div id="show"></div>
        <!--详细地址-->
        <div class="add_htxt">
            <textarea type="text" id="addressDetails" name="addressDetails" placeholder="请填写详细地址，不少于5个字" value="${obj.addressDetails}">${obj.addressDetails}</textarea>
        </div>
    </div>
    <div class="add_h1 margin_top" style="background: #fff">
        <div class="add-div-m">
            <span class="add_default" id="check_span"></span>
            <label for="check_span">设置默认地址</label>
			<input type="text" id="status" name ="status" value="1" style="display:none">
        </div>
    </div>
    <div class="refund-btn"><button class="refund-btn" type="button" onclick="save();" >提交</button></div>
    </form>
</div>
<p class="crossScreen">请竖屏</p>
<script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
<script>
    $('.add-div-m').click(function () {
        $(this).find('.add_default').toggleClass('style');
    })
</script>
<script type="text/javascript">
	       var Gid  = document.getElementById ;
	       var showArea = function(){
			  Gid('show').innerHTML = "<h3>省" + Gid('s_province').value + " - 市" +
			  Gid('s_city').value + " - 县/区" +
			  Gid('s_county').value + "</h3>"
			}
	      Gid('s_county').setAttribute('onchange','showArea()');
</script>
<script type="text/javascript">
function save(){
	var phone = $("#telPhone").val();
    var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if(phone == ''){
     alert("手机号码不能为空！");
    }else if(phone.length !=11){
      alert("请输入有效的手机号码！");
    }else if(!myreg.test(phone)){
     alert("请输入有效的手机号码！");
    }else if(null==$("#userName").val()||$("#userName").val()==''){
    	alert("请输入用户名");
    }else if(null==$("#addressDetails").val()||$("#addressDetails").val()==''){
    	alert("请输入详细地址");
    }else{
        hu();
    }
  }
function hu(){
	if($("#check_span").hasClass("style")){
		$("#status").val(2);
		console.log("选中");
	}else{
		$("#status").val(1);
		console.log("未选中");
	}
var userAddress = $("#formId").serialize();
var telPhone=$("#telphone").val();
	$.ajax({
		url:"${RESOURCEDOMAIN}/save_Address",
		type:"post",
		data:
			userAddress,
		dataType:"json",
		success:function(data){
			if(data.status == 1){
				setTimeout(function(){
					window.location.href = "${RESOURCEDOMAIN}/myAddress";
				}, 1000);
			}else if(data.status == 0){
				layer.msg(data.Message);
			}
		},
		error:function(data){
			/* layer.msg(data.Message); */
		}
	})

}

</script>
</body>
</html>
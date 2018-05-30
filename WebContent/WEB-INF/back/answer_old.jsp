<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>茶王争霸赛</title>
    <link rel="stylesheet" href="${RESOURCEDOMAIN}/css/weixin/css.css">
    <script src="${RESOURCEDOMAIN}/js/weixin/jquery-1.11.2.min.js"></script>
    <script src="${RESOURCEDOMAIN}/js/weixin/html_rem.js"></script>
    <script src="${RESOURCEDOMAIN}/js/weixin/imgLoader.js"></script>
     <style>
        .loader {
            background: #C0DAD9;
        }

        .loader__bar {
            color: #fff;
            line-height: 2em;
            height: 2em;
            position: absolute;
            left: 50%;
            top: 50%;
            width: 10em;
            text-align: center;
            margin-left: -5em;
            margin-top: -1em;
            overflow: hidden;
        }

        .loader__progress,.loader__progress-bg,.loader__info {
            position: absolute;
            height: 100%;
            left: 0;
            top: 0;
        }

        .loader__progress {
            z-index: 1;
            width: 0;
            background: #1A3831;
            border-radius: 1em;
        }

        .loader__progress-bg {
            z-index: 0;
            width: 100%;
            background: #3C776A;
            border-radius: 1em;
        }

        .loader__info {
            z-index: 2;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="verticalScreen">
    <div class="answer-bg">
        <div class="time">5</div>
        <div class="answer-div">
            <div class="answer-number">第<label>${num}</label>/${count}题</div>
            <p class="answer-title">${subject.name }</p>
            <ul class="answer-ul">
                <li><label></label>A ${subject.optionA}</li>
                <li><label></label>B ${subject.optionB}</li>
                <li><label></label>C ${subject.optionC}</li>
                <li><label></label>D ${subject.optionD}</li>
            </ul>
        </div>
    </div>
</div>
<p class="crossScreen">请竖屏</p>
<form action="next" style="display:none" class="zhi" method="post">
	<input name="num" value="${num }" type="text">
	<input name="id" value="${subject.id }" type="text">
	<input class="answer" name="answer" value=" " type="text">
	<input name="time" id="time" value="0" type="text">
</form>
</body>
<script>
var i = 6;
var flag = true;
var timer;
 function show() {
	        i--;
	        $('.time').text(i);
	        if (i == 0 && flag) {
	            clearInterval(timer);
	            flag = true;
   	         setTimeout(function () {
            	 $("form").submit();
            },1000);
	        }
	    }
var result="${result}";
if(result == "1"){
	alert("${message}");
}
$(function(){
$(".verticalScreen").css("display","none");
var callbacks = [];
$("body").css("background","#3C776A");
var loader = "<section id='loader' class='loader'>"+
    "<div class='loader__bar'>"+
    "<div class='loader__progress-bg'></div>"+
    "<div id='loader__progress' class='loader__progress'></div>"+
    "<div id='loader__info' class='loader__info'>Loading 0%</div>"+
    "</div></section>";
$("body").append(loader);
	imgLoader(['${RESOURCEDOMAIN}/image/weixin/bg2.png', '${RESOURCEDOMAIN}/image/weixin/img02.png', '${RESOURCEDOMAIN}/image/weixin/img01.png'], function (percentage) {
		var percentT = percentage * 100;
	    $('#loader__info').html('Loading ' + (parseInt(percentT)) + '%');
	    $('#loader__progress')[0].style.width = percentT + '%';
	    if (percentage == 1) {
	    	$('#loader').remove();
	        $(".verticalScreen").css("display","block");
	        timer = setInterval("show()", 1000);
	        $(".answer-ul li").click(function () {
	            if(flag){
	                var index= $(".answer-ul li").index(this);
	                var answer = String.fromCharCode(index+65);
	                $(".answer").val(answer);
	                clearInterval(timer);
	                $("#time").val(i);
	                if(answer == "${subject.answer}"){
	                	$(".answer-ul li").eq(index).addClass("right");
	                }else{
	                	$(".answer-ul li").eq(index).addClass("wrong");
	                	$(".answer-ul li").eq("${subject.answer}".charCodeAt(0)-65).addClass("right");
	                }
	                setTimeout(function () {
	                	 $("form").submit();
	                },1000);
	                flag = false;
	            }else{
	            	alert("答题结果已经提交，请等待服务器响应!");
	            }
	        });
	    }
	});
});


pushHistory();
window.addEventListener("popstate", function(e) {
    //alert("我监听到了浏览器的返回按钮事件啦");//根据自己的需求实现自己的功能
	if(confirm("确定要退出答题？"))
		window.location = '${RESOURCEDOMAIN}/teaKing/index';
}, false);

function pushHistory() {
    var state = {
        title: "title",
        url: "#"
    };
    window.history.pushState(state, "title", "#");
}
</script>
</html>
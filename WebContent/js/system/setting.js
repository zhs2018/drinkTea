/*
*@Description: 设置页面
*@Author:      Smily
*@Update:      Smily(2012-11-27)
*/
$(document).ready(function(){
	//头部active属性
	$("#header .list_left li").removeClass("active");
	$("#header .user").addClass("active");

	//user();//成员列表
	//newOne();//邀请新成员验证
	//settingPersonalValidate();//个人设置验证
	//settingOthers();//其他设置验证

	//滚动条
	$(".user_list").niceScroll({
		cursorborder:"",
		cursorcolor:"#999"
	});
	$(".setting_bottom:eq(0)").niceScroll({
		cursorborder:"",
		cursorcolor:"#999"
	});

	//个人设置、成员管理、其他设置之间的切换	
	$(".setting_title:eq(0)").show();
	$(".setting_title").click(function(){
		var i = $(".setting_title").index($(this));
		var j = $(".setting_bottom").index($(".setting_bottom:visible"));
		if (i!=j) {
			$(".setting_title").not(this).removeClass("setting_active");
			$(this).addClass("setting_active");
			$(".setting_bottom:not(:eq("+i+"))").hide(0, function(){
				$(".setting_bottom:eq("+i+")").fadeIn(100, function(){
					if ( i==1 ) {
						$(".user_list").getNiceScroll().resize();
					}
				});
			});
			
		}		
	});
	
	//弹窗--删除成员
	$(".members_delete").live("click",function(){
		click_delete = $(this).parent(".members_operate").parent("tr");
		deletID = $(this).parent(".members_operate").parent("tr").attr("id");
		TINY.box.show({
			html:$("#popwin_delete").html(),
			boxid:'frameless',
			width:414,
			height:224,
			fixed:false,
			maskid:'blackmask',
			maskopacity:40,
			openjs:function(){

				$(".tbox .popwin_confirm").click(function() {
					deleteMember();
				});
			}
		});
	});

	//导出权限的改变
	$(".changelimit").live("click",function(){
		changetxt = $(this);
		changeID = $(this).closest("tr").attr("id");
		if ($(this).hasClass("toadmin")) {
			limit = "HIGH";
		}else{
			limit = "LOW";
		}
		changeLimit();
	});

	//弹窗--邀请新成员
	$("#setting_invite").click(function(){
		TINY.box.show({
			html:$("#popwin_invite").html(),
			post:true,
			openjs:function(){
				$('.input_inviteEmail').unbind('paste').bind('paste',function(){
					var $this = $(this),
						tmpVal;
					setTimeout(function () {
						tmpVal = $this.val().replace(/(<([^>]+)>)/g, "");
						$this.val($.trim(tmpVal));
					}, 200);
				});
				newOne();//邀请新成员邮箱的验证
				invitedUser();//已邀请的用户列表
				cancelInvite();//邀请新成员已被邀请过的提示
				$(".invite_submit").click(function(){
					$(this).closest('form').submit();
				});
			},
			closejs:function(){
				$(".input_inviteEmail").siblings("label").remove();
				$(".input_inviteEmail").siblings(".error_img").remove();
				$(".invited_confirm").css("opacity","0");
			},
			animate:false,
			maskid:'blackmask',
			maskopacity:40
		});
	});

	//删除公司账号
	$(".setting_deleteAll").click(function(){
		TINY.box.show({
			html:$("#popwin_destroy").html(),
			boxid:'destroyAccount',
			width:414,
			height:244,
			fixed:false,
			maskid:'blackmask',
			maskopacity:40,
			openjs:function(){
				$("#destroyAccount [name='OLDPASSWORD']").keyup(function(){
					$("#destroyAccount .popwin_validateFail").css("visibility", "hidden");
				});
				$("#destroyAccount .popwin_confirm").click(function() {
					$.ajax({
						url: 'handler/handleVerifyPasswordOnPersonalSettings.php',
						type: 'POST',
						data: {
							DATA: '{"OLDPASSWORD":"'+hex_md5($("#destroyAccount [name='OLDPASSWORD']").val())+'"}'
						},
						dataType:'JSON',
						success: function(data){
							if (data.flag) {
								deleteClient();
							}else{
								$("#destroyAccount .popwin_validateFail").css("visibility", "visible");
							}
						}
					});
				});
			}
		});
	});

	// //个人设置页面的保存
	// $(".settingPersonal_save").click(function(){
	// 	settingPersonalValidate();
	// 	//savepersonal();
	// });

	//修改设置的保存
	$("#setting_personal").find("input").keyup(function(){
		$(".settingPersonal_save").removeClass("btn_gray").addClass("btn_blue").text("保 存");
	});
	$("#setting_others").find("input").keyup(function(){
		$(".settingCompany_save").removeClass("btn_gray").addClass("btn_blue").text("保 存");
	});

});

//成员列表
function user(){
	$.ajax({
		url: 'handler/handleGetAllUser.php',
		type: 'POST',
		dataType:'JSON',
		success: function(data){
			try {
				var json = eval(data);
			} catch(err) {
				var json = eval({'flag':false});
			}
			if (json.flag){				
				$(".setting_title:eq(1)").show();
				$(".setting_title:eq(2)").show();
				for (var i in json.data){
					var nickname = json.data[i].nickname;
					var email = json.data[i].email;
					var id = json.data[i].id;
					$(".members_list .user_list table").append("<tr class='deletMust' id='"+id+"'><td width='25%' class='nickname'>"+nickname+"</td><td width='35%'>"+email+"</td><td width='20%' class='limit'><span class='permission'></span></td><td width='20%' class='members_operate'><span class='changelimit'>赋予普通权限</span><span class='members_delete'>移除</span></td></tr>");
					if (json.data[i].high){
						$(".permission:last").addClass("yes").attr("title","管理员");
						$(".changelimit:last").addClass("toordinary").text("赋予普通权限");
					}else{
						$(".permission:last").addClass("no").attr("title","普通用户");
						$(".changelimit:last").addClass("toadmin").text("赋予高级权限");
					}
					//隐藏登录者自己的操作
					if(json.data[i].self){
						$('tr:last').find(".members_operate span").css("display","none");
					}
				}
			}else{
				$(".setting_title").not('.setting_active').remove();
				$(".setting_bottom").not(':first').remove();
			}
		},
		err: function() {
			$(".setting_title").not('.setting_active').remove();
			$(".setting_bottom").not(':first').remove();
		}
	});
}

//邀请新成员邮箱的验证
function newOne(){
	$(".tbox #invite").validate({
		rules: {
			EMAIL: {
				required: true,
				email: true,
				rangelength: [3,225]
			}
		},
		onkeyup: false,
		onclick: false,
		onfocusout: false,
		success: function(label){
			$(label).parent().addClass("checkRight");
			$(label).remove();
			$(".error_img").remove();
		},
		errorPlacement: function(error,element){
			element.parent().removeClass("checkRight");
			error.appendTo(element.parent()).after("<div class='error_img'></div>");
		},
		submitHandler: function() {
			$.ajax({
				type: "post",
				url: "handler/handleVerifyEmailOnInvite.php",
				data: {
					DATA: '{"EMAIL":"'+$("[name='EMAIL']:last").val()+'"}'
				},
				error: function () {
					return false;
				},
				success: function (data) {
					var json = eval("("+data+")");
					if (json.flag!==true){
						if(json.data=="INVITED"){
							if ($(".invited_confirm").css("opacity")==1) {
								saveinvite();
							}else{
								$(".invited_confirm").css("opacity","1");
							}
						}else{
							$(".input_inviteEmail").parent("label").removeClass("checkRight");
							$(".invited_confirm").css("opacity","0");
							$(".input_inviteEmail").after("<label for='EMAIL' generated='true' class='error' style=''>该邮箱已注册过</label>").after("<div class='error_img'></div>");
						}
					}else{
						$(".invited_confirm").css("opacity","0");
						saveinvite();						
					}
				}
			});
		}
	});

	$(".input_inviteEmail").keyup(function(e){
		if (e.keyCode!=13) {
			$(this).siblings("label").remove();
			$(this).siblings(".error_img").remove();
			$(".invited_confirm").css("opacity","0");
		}				
	});

	
	// $(".input_inviteEmail").keyup(function(e){
	// 	var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
	// 	$(".input_inviteEmail").siblings("label").remove();
	// 	$(".input_inviteEmail").siblings(".error_img").remove();
	// 	if (keyCode != 13){
	// 		$.ajax({
	// 			type: "post",
	// 			url: "handler/handleVerifyEmailOnInvite.php",
	// 			data: {
	// 				DATA: '{"EMAIL":"'+$("[name='EMAIL']:last").val()+'"}'
	// 			},
	// 			error: function () {
	// 				return false;
	// 			},
	// 			success: function (data) {
	// 				var json = eval("("+data+")");
	// 				if (json.flag!==true){
	// 					if(json.data=="INVITED"){
	// 						$(".invited_confirm").css("opacity","1");
	// 					}else{
	// 						$(".input_inviteEmail").parent("label").removeClass("checkRight");
	// 						$(".invited_confirm").css("opacity","0");
	// 						$(".input_inviteEmail").after("<label for='EMAIL' generated='true' class='error' style=''>该邮箱已注册过</label>").after("<div class='error_img'></div>");
	// 					}
	// 				}else{
	// 					$(".invited_confirm").css("opacity","0");						
	// 				}
	// 			}
	// 		});
	// 	}
	// });
}

//邀请新成员的提交
function saveinvite() {
	// console.trace();
	$.ajax({
		type: "post",
		url: "handler/handleInvite.php",
		data: {
			DATA: '{"EMAIL":"'+$("[name='EMAIL']:last").val()+'"}'
		},
		error: function () {
			return false;
		},
		success: function (data) {
			var json = eval("("+data+")");
			if (json.flag){
				var myDate = new Date();
				var year = myDate.getFullYear();
				var month = myDate.getMonth();
				var day = myDate.getDate();
				var hour = myDate.getHours();
				var minite = myDate.getMinutes();
				var second = myDate.getSeconds();
				if (day > 9){
					day = day;
				}else{
					day = "0" + day;
				}
				if (hour > 9){
					hour = hour;
				}else{
					hour = "0" + hour;
				}
				if (minite > 9){
					minite = minite;
				}else{
					minite = "0" + minite;
				}
				if (second > 9){
					second = second;
				}else{
					second = "0" + second;
				}
				$(".invited_confirm").css("opacity","0");
				$(".input_inviteEmail").siblings("label").hide().siblings(".error_img").hide();
				invitedUser();
				$("[name='EMAIL']:last").val("");
			}
		}
	});
}

//已邀请的用户列表
function invitedUser(){
	$.ajax({
		url: 'handler/handleGetAllInvitedUser.php',
		type: 'POST',
		dataType:'JSON',
		success: function(data){
			var json = eval(data);
			if (json.flag){
				$(".invite_list").empty();
				$(".invite_send").text("已发送的邀请").css("border-bottom-color","#599FD1");
				for (var i in json.data){
					var invitedEmail = json.data[i].email;
					var invitedTime = json.data[i].time;
					var id = json.data[i].id;
					$(".invite_list").append("<li id='"+id+"'>"+invitedEmail+"<span class='cancel_invite'>撤销邀请</span><br /><span class='invite_time'>最后一次邀请时间："+invitedTime+"</span></li>");
					var dom_height = $(".tcontent").height();
					$(".invite_list").closest(".tinner").css("height",dom_height);
					//hover的时候权限设置、重置密码和移除按钮出现
					cancelInvite();
				}
			}else{
				$(".invite_send").text("").css("border-bottom-color","#ffffff");
			}
		}
	});
}

//弹窗--邀请新成员hover出现取消邀请
function cancelInvite(){
	$(".invite_list li").live("mouseover",function(){
		$(this).find(".cancel_invite").css("display","inline-block");
	});
	$(".invite_list li").live("mouseout",function(){
		$(this).find(".cancel_invite").css("display","none");
	});
	$(".cancel_invite").live("click",function(){
		invite_delete = $(this).parent("li");
		deleteInviteID = $(this).parent("li").attr("id");
		length_dom = $(this).closest(".tinner");
	dom_height = length_dom.height();
		deleteInvite();
	});
}

//取消邀请
function deleteInvite() {
    $.ajax({
        type: "post",
        url: "handler/handleRemoveUser.php",
        data: {
            DATA: '{"TGTUSID":"'+deleteInviteID+'"}'
        },
        error: function () {
            return false;
        },
        success: function (data) {
            var json = eval("("+data+")");
           	if (json.flag){
           		invite_delete.remove();
           		if($(".tbox .invite_list").find("li").length==0){
           			$(".invite_send").text("").css("border-bottom-color","#ffffff");
           		}
           		invitedUser();
		}
        }
    });
}

//个人设置的验证
function settingPersonalValidate(){	
	var state = false;
	$(".reset_password").blur(function(){
		$(".reset_password").each(function(){
			if ($(this).val() != ""){
				state = true;
				return false;
			}else{
				state = false;
				$(this).removeClass("error").siblings("label.error").remove();
			}
		});

		$(".reset_password").mikeValidate({
			rules: {
				required: function(){
					if(state){
						return true;
					}else{
						return false;
					}
				},
			},
			maxlength: 255,
			submitHandler: function(){
				savepersonal();
			}

		});
		$(".newpasswordagain").mikeValidate({
			rules: {
				required: function(){
					if(state){
						return true;
					}else{
						return false;
					}
				},
				maxlength: 255,
				equalTo: $(".newpassword")
			},
			onkeyup: false,
			submitHandler: function(){
				savepersonal();
			}
		});
	});

	$(".resetNickname").mikeValidate({
		rules: {
			required: true,
			maxlength: 30
		},
		submitHandler: function(){
			savepersonal();
		}
	});

	$(".resetPhone").mikeValidate({	
		rules: {
			required: false,
			maxlength: 30
		},
		submitHandler: function(){
			savepersonal();
		}
	});


	$(".oldpassword").mikeValidate({
		rules: {
			required: function(){
				if(state){
					return true;
				}else{
					return false;
				}
			},
			maxlength: 255,
			remote: {
				url: "handler/handleVerifyPasswordOnPersonalSettings.php",
				data: {
					DATA:function(){
						var data =  '{"OLDPASSWORD":"'+hex_md5($("[name='OLDPASSWORD']").val())+'"}';
						return data;
					}
				}
			}
		},
		messages: {
			required: "此项为必填项",
			maxlength: "输入内容超长",
			remote: "密码错误，请重新输入",
		},
		onkeyup: false,
		submitHandler: function(){
			savepersonal();
		}
	});

	$(".newpassword").mikeValidate({
		rules: {
			required: function(){
				if(state){
					return true;
				}else{
					return false;
				}
			},
			maxlength: 255
		},
		submitHandler: function(){
			savepersonal();
		}
	});

	$(".newpasswordagain").mikeValidate({
		rules: {
			required: function(){
				if(state){
					return true;
				}else{
					return false;
				}
			},
			maxlength: 255,
			equalTo: $(".newpassword")
		},
		onkeyup: false,
		submitHandler: function(){
			savepersonal();
		}
	});


	$(".email").mikeValidate({
		rules: {
			required: true,
			maxlength: 255,
			email: true,
			remote: {
				url: "handler/handleVerifyEmailOnSettings.php",
				data: {
					DATA:function(){
						var data =  '{"EMAIL":"'+$("[name='EMAIL']").val()+'"}';
						return data;
					}
				}
			}
		},
		messages: {
			required: "此项为必填项",
			maxlength: "输入内容超长",
			email: "请输入正确格式的电子邮件",
			remote: "此邮箱已被占用，请重新输入",
		},
		onkeyup: false,
		submitHandler: function(){
			savepersonal();
		}
	});

	// $("#setting_personal").validate({
	// 	rules: {
	// 		NICKNAME:{
	// 			required: true,
	// 			maxlength: 30
	// 		},
	// 		OLDPASSWORD: {
	// 			required:function(){
	// 				if(state){
	// 					return true;
	// 				}else{
	// 					return false;
	// 				}
	// 			},
	// 			maxlength: 255,
	// 			remote:{
	// 				type: "POST",
	// 				url: "handler/handleVerifyPasswordOnPersonalSettings.php",
	// 				data: {
	// 					DATA:function(){
	// 						var data =  '{"OLDPASSWORD":"'+hex_md5($("[name='OLDPASSWORD']").val())+'"}';
	// 						return data;
	// 					}
	// 				}
	// 			}
	// 		},
	// 		NEWPASSWORD: {
	// 			required:function(){
	// 				if(state){
	// 					return true;
	// 				}else{
	// 					return false;
	// 				}
	// 			},
	// 			maxlength: 255
	// 		},
	// 		NEWPASSWORDAGAIN: {
	// 			required:function(){
	// 				if(state){
	// 					return true;
	// 				}else{
	// 					return false;
	// 				}
	// 			},
	// 			maxlength: 255,
	// 			equalTo:"#NEWPASSWORD"
	// 		},
	// 		EMAIL: {
	// 			required: true,
	// 			email: true,
	// 			maxlength: 255,
	// 			remote:{
	// 				type: "POST",
	// 				url: "handler/handleVerifyEmailOnSettings.php",
	// 				data: {
	// 					DATA:function(){
	// 						var data =  '{"EMAIL":"'+$("[name='EMAIL']").val()+'"}';
	// 							return data;
	// 						}
	// 				}
	// 			}
	// 		}
	// 	},
	// 	messages: {
	// 		OLDPASSWORD:{
	// 			remote:"密码错误，请重新输入"
	// 		},
	// 		EMAIL:{
	// 			remote:"此邮箱已被占用，请重新输入"
	// 		}
	// 	},
	// 	onkeyup: false,
	// 	onclick: false,
	// 	focusInvalid: false,
	// 	success: function(label){
	// 		$(label).parent().addClass("checkRight");
	// 		$(label).remove();
	// 	},
	// 	errorPlacement: function(error,element){
	// 		element.parent().removeClass("checkRight");
	// 		error.appendTo(element.parent()).after("<div class='error_img'></div>");
	// 	},
	// 	submitHandler: function(){
	// 		savepersonal();
	// 	}
	// });
}

//个人设置页面的保存
function savepersonal() {
	var formid = $("#setting_personal");
	var datasavePersonal = tojson(formid);
    $.ajax({
        type: "post",
        url: "handler/handleAlterPersonalSettings.php",
        dataType: "JSON",
        data: {
            DATA: datasavePersonal
        },
        error: function () {
            return false;
        },
        success: function (data) {
           	if (data.flag){
           		//$(".tips_success").show().delay(3000).fadeOut(300);
           		$(".settingPersonal_save").removeClass("btn_blue").addClass("btn_gray").text("已保存");
           	};
        }
    });
}



//公司设置的验证
function settingOthers(){
	$("#setting_others").validate({             
	    rules: {
	    	COMPANY:{
	    		required: true,
	    		maxlength: 255
	    	},
	    	ACCOUNT: {
				required: true,
				chrnum: true,
				rangelength: [3,225],
				remote:{
			                    type: "POST",
			                    url: "handler/handleVerifyClientAccountOnSettings.php",
			                    data: {
				                    DATA:function(){
				                    		var data =  '{"ACCOUNT":"'+$("[name='ACCOUNT']").val()+'"}';
							return data;
						}
			                    }
               			}
	    	}
	    },
	    messages: {
	    	ACCOUNT:{
	    		remote:"该地址已被占用",
	    	}
	    },
	    onkeyup: false, 
	    onclick: false,
	    focusInvalid: false,
	    success: function(label){
			$(label).parent().addClass("checkRight");
			$(label).remove();
		},
		errorPlacement: function(error,element){
			element.parent().removeClass("checkRight");
			error.appendTo(element.parent()).after("<div class='error_img'></div>");
		},
	    submitHandler: function(){
	    	saveothers();
	    }
	});
}
//公司设置页面的保存
function saveothers() { 
	var formid = $("#setting_others");
	var dataSaveothers = tojson(formid);
    $.ajax({
        type: "post",
        url: "handler/handleAlterClientInfoOnSettings.php",
        data: {
            DATA: dataSaveothers
        },
        error: function () {
            return false;
        },
        success: function (data) {
            var json = eval("("+data+")");
           	if (json.flag){
           		//$(".tips_success").show().delay(3000).fadeOut(300);
           		$(".settingCompany_save").removeClass("btn_blue").addClass("btn_gray").text("已保存");
           	};
        }
    });
}


//删除成员
function deleteMember() { 
    $.ajax({
        type: "post",
        url: "handler/handleRemoveUser.php",
        data: {
            DATA: '{"TGTUSID":"'+deletID+'"}'
        },
        error: function () {
            return false;
        },
        success: function (data) {        	
            var json = eval("("+data+")");
           	if (json.flag){
           		TINY.box.hide();
           		click_delete.remove();
           		//alert("删除成功！");
           		//location.href = "listContact.php";
           	};
        }
    });
}

//修改权限
function changeLimit() { 
    $.ajax({
        type: "post",
        url: "handler/handleAlterUserAuthTypeOnSettings.php",
        data: {
            DATA: '{"TGTUSID":"'+changeID+'","TYPE":"'+limit+'"}'
        },
        error: function () {
            return false;
        },
        success: function (data) {        	
            var json = eval("("+data+")");
           	if (json.flag){
           		if(changetxt.hasClass("toadmin")){
					changetxt.text("赋予普通权限").removeClass("toadmin").addClass("toordinary");
					changetxt.closest('td').siblings(".limit").find('span').removeClass("no").addClass("yes");
				}else{
					changetxt.text("赋予高级权限").removeClass("toordinary").addClass("toadmin");
					changetxt.closest('td').siblings(".limit").find('span').removeClass("yes").addClass("no");
				}
           		//location.href = "listContact.php";
           	};
        }
    });
}

//删除公司账号
function deleteClient(){
	$.ajax({
		url: "handler/handleDeleteClient.php",
		type: "post",
		dataType: "json",
		success: function(data){
			if (data.flag) {
				$.ajax({
					type: "post",
					url: "handler/handleLogout.php",
					success: function (data) {
						var json = eval("("+data+")");
						if (json.flag){
							$.cookie("remember", "false", { expires: -1 });
							$.cookie("userName", '', { expires: -1 });
							$.cookie("passWord", '', { expires: -1 });						
							location.href = "login.php";
						}
					}
				});
			}
		}
	});
}
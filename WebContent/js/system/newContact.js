$(document).ready(function() {
	// 2013-07-24 添加记录多条的object
	commonFormValidate();
	$.validator.addClassRules({
		w_double : {
			number : true,
			range : [ 0, 150 ]
		},
		w_kucun : {
			digits : true,
			required : true,
			number : true,
			range : [ 0, 15000 ]
		},
		w_weight : {
			number : true,
			range : [ 0, 15000 ]
		},
		w_rweight : {
			required : true,
			number : true,
			range : [ 0, 15000 ]
		},
		w_rbili : {
			required : true,
			range : [ 0, 100 ]
		},
		w_price : {
			required : true,
			number : true,
			range : [ 0, 150000 ]
		},
		w_require : {
			required : true,
		},
		w_email : {
			email : true,
			rangelength : [ 3, 225 ]
		},
		w_user_login : {
			user_login : true
		},
		w_agent_shi_user_login :{
			agent_shi_user_login : true
		},
		w_mobile : {
			mobile : true
		},
		w_user_gh : {
			user_gh : true
		},
		w_order : {
			required : true,
			digits : true,
			range : [ 0, 100 ]
		},
		w_password : {
			required : true,
			minlength : 6,
			maxlength : 20,
			equalTo : "#oldPass"
		},
		w_renewPass : {
			required : true,
			minlength : 6,
			maxlength : 20,
			equalTo : "#newPass"
		},
		w_rdateISO:{
			required : true,
			dateISO:true
		}
	});

	jQuery.validator.addMethod("positiveinteger", function(value, element) {
		var aint = parseInt(value);
		return aint > 0 && (aint + "") == value;
	}, "请输入正整数");

	jQuery.validator.addMethod("mobile", function(value, element) {
		var length = value.length;
		var mobile = /^1[3|4|5|8][0-9]\d{4,8}$/
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "手机号码格式错误");

	jQuery.validator.addMethod("user_login", function(value, element) {

		var stuNo = $("#loginBack").val();
		if (stuNo == value) {
			return true;
		} else {
			var result = checkIsUseLogin(value);
			return result;
		}
	}, "登录名已经注册过，不允许重复使用");
	
	jQuery.validator.addMethod("agent_shi_user_login", function(value, element) {

		var stuNo = $("#loginBack").val();
		if (stuNo == value) {
			return true;
		} else {
			var result = agent_shi_user_login(value);
			return result;
		}
	}, "登录名已经注册过，不允许重复使用");
	

	jQuery.validator.addMethod("user_gh", function(value, element) {
		var stuNo = $("#ghBack").val();
		if (stuNo == value) {
			return true;
		} else {
			var result = checkIsUseGH(value);
			return result;
		}
	}, "该gh_id已经注册过，不允许重复使用");

	function agent_shi_user_login(value) {
		var result = true;
		$.ajax({
			url : SHOPDOMAIN+'/system/agentshi/isuselogin.html',
			type : 'POST',
			data : {
				"login" : value
			},
			async : false,
			success : function(data) {
				if (data == "success") {
					result = true;
				} else {
					result = false;
				}
			}
		});
		return result;
	}
	
	function checkIsUseLogin(value) {
		var result = true;
		$.ajax({
			url : SHOPDOMAIN+'/system/users/isuselogin.html',
			type : 'POST',
			data : {
				"login" : value
			},
			async : false,
			success : function(data) {
				if (data == "success") {
					result = true;
				} else {
					result = false;
				}
			}
		});
		return result;
	}
	
	
	function checkIsUseGH(value) {
		var result = true;
		$.ajax({
			url : SHOPDOMAIN+'/system/users/isusegh.html',
			type : 'POST',
			data : {
				"gh" : value
			},
			async : false,
			success : function(data) {
				if (data == "success") {
					result = true;
				} else {
					result = false;
				}
			}
		});
		return result;
	}
});

// 常用--验证
function commonFormValidate() {
	$("#form_config").validate({
		onkeyup : false,
		onclick : false,
		focusInvalid : false,
		success : function(label) {
			$(label).parent().addClass("checkRight");
			$(label).remove();
		},
		errorPlacement : function(error, element) {
			element.parent().removeClass("checkRight");
			error.appendTo(element.parent()).after();
		},
		submitHandler : function(form) {
			form.submit();
		}
	})
}

function tixian(){
	$("#tixian").validate({
		rules : {
			responseTitle : {
				required : true,
				maxlength : 32
			},
			responseHighmusic : {
				required : true,
				maxlength : 32
			},
			responseMusic : {
				required : true,
				maxlength : 32
			}
		},
		onkeyup : false,
		onclick : false,
		focusInvalid : false,
		onfocusout : function(element, event) {
			var label = $(element).siblings("label.error");

			if (this.check(element) == false) {
				// 校验失败，显示错误信息
				this.element(element);
				$(element).parent().find(".error_img").show();
				label.show();

			} else {
				// 校验通过，移除错误信息
				$(element).removeClass("error");
				$(element).parent().find(".error_img").hide();
				label.hide();
			}

		},
		errorPlacement : function(error, element) {
			if (element.parent().find(".error_img").length == 0) {
				element.parent().append();
			}
			if (error.attr('for') === element.attr('id')) {
				error.appendTo(element.parent());
			}
		},
		submitHandler : function(form) {
			$.ajax({
				url : '/jgmarkting/system/caiwu/tixian.html',
				type : 'post',
				dataType : 'json',
				data : $("#tixian").serialize(),
				success : function(data) {
					if (data.Status == "1") {
						alert(data.Message);
					} else {
						alert(data.Message);
					}
				}
			})
		}
	})
}

// 新建回复--验证
function newResponseValidate() {
	$("#form1").validate({
		rules : {
			responseTitle : {
				required : true,
				maxlength : 32
			}
		},
		onkeyup : false,
		onclick : false,
		focusInvalid : false,
		onfocusout : function(element, event) {
			var label = $(element).siblings("label.error");

			if (this.check(element) == false) {
				// 校验失败，显示错误信息
				this.element(element);
				$(element).parent().find(".error_img").show();
				label.show();

			} else {
				// 校验通过，移除错误信息
				$(element).removeClass("error");
				$(element).parent().find(".error_img").hide();
				label.hide();
			}

		},
		errorPlacement : function(error, element) {
			if (element.parent().find(".error_img").length == 0) {
				element.parent().append();
			}
			if (error.attr('for') === element.attr('id')) {
				error.appendTo(element.parent());
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})
	$("#form2").validate({
		rules : {
			responseTitle : {
				required : true,
				maxlength : 32
			},
			titlename : {
				required : true,
				maxlength : 32
			}
		},
		onkeyup : false,
		onclick : false,
		focusInvalid : false,
		onfocusout : function(element, event) {
			var label = $(element).siblings("label.error");

			if (this.check(element) == false) {
				// 校验失败，显示错误信息
				this.element(element);
				$(element).parent().find(".error_img").show();
				label.show();

			} else {
				// 校验通过，移除错误信息
				$(element).removeClass("error");
				$(element).parent().find(".error_img").hide();
				label.hide();
			}

		},
		errorPlacement : function(error, element) {
			if (element.parent().find(".error_img").length == 0) {
				element.parent().append();
			}
			if (error.attr('for') === element.attr('id')) {
				error.appendTo(element.parent());
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})
	$("#form3").validate({
		rules : {
			responseTitle : {
				required : true,
				maxlength : 32
			},
			responseHighmusic : {
				required : true,
				maxlength : 32
			},
			responseMusic : {
				required : true,
				maxlength : 32
			}
		},
		onkeyup : false,
		onclick : false,
		focusInvalid : false,
		onfocusout : function(element, event) {
			var label = $(element).siblings("label.error");

			if (this.check(element) == false) {
				// 校验失败，显示错误信息
				this.element(element);
				$(element).parent().find(".error_img").show();
				label.show();

			} else {
				// 校验通过，移除错误信息
				$(element).removeClass("error");
				$(element).parent().find(".error_img").hide();
				label.hide();
			}

		},
		errorPlacement : function(error, element) {
			if (element.parent().find(".error_img").length == 0) {
				element.parent().append();
			}
			if (error.attr('for') === element.attr('id')) {
				error.appendTo(element.parent());
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})
}

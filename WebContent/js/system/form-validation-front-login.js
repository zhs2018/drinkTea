var FormValidation = function() {

	$.validator.addClassRules({
		w_double : {
			number : true,
			range : [ 0, 150 ]
		},
		w_6_20 : {
			required : true,
			rangelength : [ 6, 20 ]
		},
		w_kucun : {
			digits : true,
			required : true,
			number : true,
			range : [ 0, 1500000 ]
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
		w_sort : {
			range : [ 0, 1000 ]
		},
		w_price : {
			required : true,
			number : true,
			range : [ 0, 150000 ]
		},
		w_positive_number : {
			number : true,
			range : [ 1, 150 ]
		},
		w_require : {
			required : true,
			rangelength : [ 1, 225 ]
		},
		w_email : {
			email : true,
			rangelength : [ 3, 225 ]
		},
		w_content : {
			rangelength : [ 10, 200 ]
		},
		w_rangelength : {
			rangelength : [ 0, 225 ]
		},
		w_merchants_login : {
			merchants_login : true,
			rangelength : [ 6, 11 ]
		},
		w_forget_logins : {
			forget_logins : true,
			rangelength : [ 6, 11 ]
		},
		w_mobile : {
			mobile : true
		},
		w_mobile_email : {
			mobile_email : true
		},
		w_user_gh : {
			user_gh : true
		},
		w_order : {
			required : true,
			digits : true,
			range : [ 0, 100 ]
		},
		w_passwd : {
			required : true,
			minlength : 6,
			maxlength : 20
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
		}
	});

	jQuery.validator.addMethod("mobile", function(value, element) {
		var length = value.length;
		var mobile = /^1[3|4|5|8][0-9]\d{4,8}$/;
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "手机号码格式错误");
	
	jQuery.validator.addMethod("mobile_email", function(value, element) {
		var length = value.length;
		var email = /^(?:\w+\.?)*\w+@(?:\w+\.)*\w+$/;
		var length = value.length;
		var mobile = /^1[3|4|5|8][0-9]\d{4,8}$/;
		alert(email.test(value));
		alert( length == 11 && mobile.test(value));
		return this.optional(element) || email.test(value)||   (length == 11 && mobile.test(value));
	}, "格式错误");

	jQuery.validator.addMethod("merchants_login", function(value, element) {
		var result = merchants_logins(value);
		return result;
	}, "手机号已经注册过，不允许重复使用");

	jQuery.validator.addMethod("forget_logins", function(value, element) {
		var result = forget_logins(value);
		return result;
	}, "手机号未注册过");

	jQuery.validator.addMethod("chek_name", function(value, element) {
		var stuNo = $("#loginBack").val();
		if (/^.*?[\d]+.*$/.test(stuNo) && /^.*?[A-Za-z].*$/.test(stuNo) && /^.{6,16}$/.test(stuNo)) {
			return true;
		}
	}, "请使用字母加数字");

	function merchants_logins(value) {
		var result = true;
		$.ajax({
			url : SHOPDOMAIN + '/userIsuse.html',
			type : 'POST',
			data : {
				"phone" : value,
				"type" : 1
			},
			async : false,
			dataType : "json",
			success : function(data) {
				if (data.res_code == "0") {
					result = true;
				} else {
					$.dialogReg('confirm', '', '该手机号已经注册过', 0, function() {
						window.location.href = SHOPDOMAIN + '/wap/login.html'
					}, function() {
						window.location.href = SHOPDOMAIN + '/wap/retrievePassword.html'
					});
					result = false;
				}
			}
		});
		return result;
	}

	function forget_logins(value) {
		var result = true;
		$.ajax({
			url : SHOPDOMAIN + '/userIsuse.html',
			type : 'POST',
			data : {
				"phone" : value,
				"type" : 2
			},
			async : false,
			dataType : "json",
			success : function(data) {
				if (data.res_code == "0") {
					$("#phones").attr("can", true);
					result = true;
				} else {
					$("#phones").attr("can", false);
					$.dialog('confirm', '', '该手机号未注册过，请注册', 0, function() {
						window.location.href = SHOPDOMAIN + '/wap/registered.html'
					});
					result = false;
				}
			}
		});
		return result;
	}

	// basic validation
	var isSubmit = false;// 验证是否已经提交过

	var handleValidation1 = function() {
		var form1 = $('#form_config');

		form1.validate({
			errorElement : 'span', // default input error message container
			errorClass : 'help-block help-block-error', // default input error
			// message class
			focusInvalid : false, // do not focus the last invalid input
			ignore : "", // validate all fields including form hidden input
			messages : {
				select_multi : {
					maxlength : jQuery.validator.format("Max {0} items allowed for selection"),
					minlength : jQuery.validator.format("At least {0} items must be selected")
				}
			},
			invalidHandler : function(event, validator) { // display error
				$.each(validator.invalid, function(item, val) {
					if((undefined==$("input[name='" + item + "']").val())){
						$.tooltip("内容 " + val);
					}else{
						$.tooltip($("input[name='" + item + "']").attr("placeholder") + " " + val);
					}
				});
			},

			highlight : function(element) { // hightlight error inputs
				// $(element).closest('.form-group').addClass('has-error'); //
				// set
			},

			unhighlight : function(element) { // revert the change done by
				// $(element).closest('.form-group').removeClass('has-error');
				// // set
			},

			success : function(label) {
				// label.closest('.form-group').removeClass('has-error'); // set
			},

			submitHandler : function(form) {
				if (isSubmit == true) {
					$.dialog('alert', '提示', '正在处理中...', 0);
					// UIToastr.kk2("友情提示", "资料已经提交，请勿重复操作", 'warning');
				} else {
					$(form).ajaxSubmit({
						dataType : "json",
						success : function(json) {
							if (json.res_code == '0') {// 成功刷新当前页面
								$.tooltip(json.message, 2500, true);
								isSubmit = true;
								if (json.token != undefined) {
									$.cookie('token', json.token, {
										expires : 30,
										path:"/"
									});
								}
								if($("#referrer").val()!=""){
									setInterval("window.location.href='" + $("#referrer").val() + "'", 1000);
								}else{
									setInterval("window.location.href='" + json.res_url + "'", 1000);
								}
							} else {// 失败
								$.tooltip(json.message);
								// 验证码已失效，请重新输入
								if (json.res_code == "user.ER1004" || json.res_code == "user.ER1013") {
									chCaptcha();
								}
							}
						},
						error : function(json) {
							showError(json.message);
						}
					});
				}
				return false;
			}
		});
	}

	var handleWysihtml5 = function() {
		if (!jQuery().wysihtml5) {

			return;
		}

		if ($('.wysihtml5').size() > 0) {
			$('.wysihtml5').wysihtml5({
				"stylesheets" : [ "../../assets/global/plugins/bootstrap-wysihtml5/wysiwyg-color.css" ]
			});
		}
	}

	return {
		init : function() {
			// handleWysihtml5();
			handleValidation1();

		}

	};

}();

$(".sys_go_back").click(function() {
	history.back(-1);
})
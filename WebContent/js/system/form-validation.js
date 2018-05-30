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
		w_discount : {
			digits : true,
			required : false,
			number : true,
			range : [ 1, 100 ]
		},
		w_discountss : {
			number : true,
			range : [ 1, 100 ]
		},
		w_discounts : {
			number : true,
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
			digits : true,
			number : true,
			range : [ 0, 1700 ]
		},
		w_price : {
			required : true,
			number : true,
			range : [ 0.1, 150000 ]
		},
		
		w_positive_number : {
			number : true,
			range : [ 1, 300 ]
		},
		w_require : {
			required : true,
			rangelength : [ 1, 225 ]
		},
		w_email : {
			email : true,
			rangelength : [ 3, 225 ]
		},
		w_rangelength : {
			rangelength : [ 0, 225 ]
		},
		w_merchants_login : {
			merchants_login : true,
			rangelength : [ 6, 11 ]
			
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
		w_isQq : {
			isQq:true
		},
		w_renewPass : {
			required : true,
			minlength : 6,
			maxlength : 20,
			equalTo : "#newPass"
		}
	});

	jQuery.validator.addMethod("isQq", function(value, element) {       
		var tel = /^[1-9]\d{4,9}$/;
	    return this.optional(element) || (tel.test(value));
	}, "QQ不正确");   
	
	jQuery.validator.addMethod("mobile", function(value, element) {
		var length = value.length;
		var mobile = /^1[3|4|5|7|8][0-9]\d{4,8}$/
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "手机号码格式错误");

	jQuery.validator.addMethod("merchants_login", function(value, element) {

		var stuNo = $("#loginBack").val();
		if (stuNo == value) {
			return true;
		} else {
			var result = merchants_logins(value);
			return result;
		}
	}, "登录名已经注册过，不允许重复使用");

	jQuery.validator.addMethod("chek_name", function(value, element) {
		var stuNo = $("#loginBack").val();
		if (/^.*?[\d]+.*$/.test(stuNo) && /^.*?[A-Za-z].*$/.test(stuNo)
				&& /^.{6,16}$/.test(stuNo)) {
			return true;
		}
	}, "请使用字母加数字");
	
	function merchants_logins(value) {
		var result = true;
		$.ajax({
			url : 'isuselogin.html',
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

	// basic validation
	var isSubmit = false;// 验证是否已经提交过

	var handleValidation1 = function() {
		// for more info visit the official plugin documentation:
		// http://docs.jquery.com/Plugins/Validation
		var form1 = $('#form_config');
		var error1 = $('.alert-danger', form1);
		var success1 = $('.alert-success', form1);

		form1.validate({
			errorElement : 'span', // default input error message container
			errorClass : 'help-block help-block-error', // default input error
			// message class
			focusInvalid : false, // do not focus the last invalid input
			ignore : "", // validate all fields including form hidden input
			messages : {
				select_multi : {
					maxlength : jQuery.validator
							.format("Max {0} items allowed for selection"),
					minlength : jQuery.validator
							.format("At least {0} items must be selected")
				}
			},
			invalidHandler : function(event, validator) { // display error
				// alert on form
				// submit
				success1.hide();
				error1.show();
				Metronic.scrollTo(error1, -200);
			},

			highlight : function(element) { // hightlight error inputs
				$(element).closest('.form-group').addClass('has-error'); // set
				// error
				// class
				// to
				// the
				// control
				// group
			},

			unhighlight : function(element) { // revert the change done by
				// hightlight
				$(element).closest('.form-group').removeClass('has-error'); // set
				// error
				// class
				// to
				// the
				// control
				// group
			},

			success : function(label) {
				label.closest('.form-group').removeClass('has-error'); // set
				// success
				// class
				// to
				// the
				// control
				// group
			},

			submitHandler : function(form) {
				success1.show();
				error1.hide();
				if (isSubmit == true) {
					UIToastr.kk2("友情提示", "资料已经提交，请勿重复操作", 'warning');
				} else {
					$(form).ajaxSubmit(
							{success : function(json) {
									var json = eval('(' + json + ')');
									if (json.type == 'success') {// 成功刷新当前页面
										UIToastr.kk2(json.title, json.content,
												json.type);
										isSubmit = true;
										setInterval("window.location.href='"
												+ json.url + "'", 1000);
									} else {// 失败
										UIToastr.kk2(json.title, json.content,
												json.type);
									}
								},
								error : function(json) {
									alert("error");
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
			$('.wysihtml5')
					.wysihtml5(
							{
								"stylesheets" : [ "../../assets/global/plugins/bootstrap-wysihtml5/wysiwyg-color.css" ]
							});
		}
	}

	return {
		// main function to initiate the module
		init : function() {

			handleWysihtml5();
			handleValidation1();

		}

	};

}();

$(".sys_go_back").click(function(){
	history.back(-1);
})
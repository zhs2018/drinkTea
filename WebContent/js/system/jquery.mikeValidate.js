/**
 *	@version	0.1
 *	@author:	Smily.Liang
 *	@company:	MikeCRM
 *	@date:		2013-1-28 update
 *	@copyright:	Copyright (c) MikeCRM(http://www.mikecrm.com)
 *  ---------------------------------------------------------
 *  功能：
 *  1、input的验证(required>maxlength>minlgth>email>equalTo)
**/
(function($) {
	$.fn.mikeValidate = function(options){
		//初始化数据
		var defaults = {
			rules: {
				//初始化规则
				required: false,
				maxlength: false,
				minlength: false,
				email: false,
				equalTo: false,
				remote: false,
			},
			messages: {
				//初始化错误提示信息
				required: "此项为必填项",
				maxlength: "太长了，不要输那么多",
				minlength: "太短了，多输一点啦",
				email: "请输入正确格式的电子邮件",
				equalTo: "两次输入不同，请重新输入",
				remote: "请修正该字段",
			},
			onkeyup:true,
			onfocusout: true,
			onsubmit: true,
			submitHandler: function() {
				
			},
		};

		var options =  jQuery.extend(defaults, options);

		// 验证各种组件是否符合标准
		var _requiredField = function ( element ){
			var count = 0;
			if( element.attr("type")=="checkbox" || element.attr("type")=="radio" ){
				
				element.each(function(){
					if($(this).attr("checked")){
						count++;
					}
				});
				return !count; // 有值时返回false；
			} else if(element.attr("type")=="text"){
				return !element.val();
			}
		}

		//验证的方法——默认顺序：required>maxlength>minlength>email>equalTo>remote			
		function validate(element){	
			var errorType = "required",_field;
			if (options.rules.required !== true && options.rules.required !== false) {
				options.rules.required = options.rules.required();
			}
			_field = _requiredField(element);
			if (options.rules.required) {
				 // 判断类型－ 判断是否符合标准－ 返回 true／ false;
				if ( _field ) {
					return check.error(errorType,element);
				}else{
					return maxlength(element);
				}
			}else{
				return maxlength(element);
			}
		}

		function maxlength(element){
			if (options.rules.maxlength) {
				errorType = "maxlength";
				if ( element.val().length > options.rules.maxlength ) {
					return check.error(errorType,element);
				}else{
					return minlength(element);
				}
			}else{
				return minlength(element);
			}
		}

		function minlength(element){
			if (options.rules.minlength) {
				errorType = "minlength";
				if ( element.val().length < options.rules.minlength ) {
					return check.error(errorType,element);
				}else{
					return email(element);
				}
			}else{
				return email(element);
			}
		}

		function email(element){
			if (options.rules.email) {
				errorType = "email";
				if ( !/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(element.val()) ) {
					return check.error(errorType,element);
				}else{
					return equalTo(element);
				}
			}else{
				return equalTo(element);
			}
		}

		function equalTo(element){
			if (options.rules.equalTo) {
				errorType = "equalTo";
				if ( element.val() !=  options.rules.equalTo.val() ) {
					return check.error(errorType,element);
				}else{
					return remote(element);
				}
			}else{
				return remote(element);
			}
		}

		function remote(element){
			if (options.rules.remote) {
				if (element.val()!="") {
					errorType = "remote";
					$.ajax({
						type: "POST",
						dataType: "JSON",
						url: options.rules.remote.url,
						data: options.rules.remote.data,
						success: function(data){
							if (data.flag) {
								return check.valid(element);
							}else{
								return check.error(errorType,element);
							}
						}
					});
				};
			}else{
				return check.valid(element);
			}
		}


		//验证的结果
		var check ={
			valid: function(element){
				element.removeClass("error");
				element.siblings(".error").remove();
				return true;
			},
			error: function(errorType,element){
				element.addClass("error");
				element.siblings("label.error").remove();
				element.after("<label class='error' style='position: static; margin-left: 10px; '>"+options.messages[errorType]+"</label>");
				return false;
			}
		};

		return this.each(function(){

			// 绑定自定义事件，触发提交验证
			$(this).bind("submitValidate", function(e){
				validate($(e.target));
			});

			//input正在输入时验证——默认为true
			if (options.onkeyup) {
				$(this).keyup(function(){	
					validate($(this));
				});	
			};

			//input失去焦点时进行验证——默认为true
			if (options.onfocusout) {								
				$(this).focusout(function(){
					validate($(this));
				});			
			};

			//点击class为validate_submit的提交按钮式先进行验证——默认为true
			if (options.onsubmit) {
				$(this).closest("form").find(".validate_submit").unbind("click");							
				$(this).closest("form").find(".validate_submit").bind("click",function(){
					$(this).closest("form").find("input").each(function(){
						$(this).trigger("submitValidate");
					});
					if ( $("label.error").length == 0) {
						options.submitHandler();
					};
					
					// var errorCount = 0;
					// //这里也可以添加其他验证
					// $(this).closest("form").find("input").each(function(){
					// 	console.log(options);
					// 	if( !validate($(this)) ){
					// 		errorCount++;
					// 	}
					// });
					// //errorCount==0，说明验证通过，提交表单	
					// if (errorCount == 0) {
					// 	options.submitHandler();
					// };
				});			
			};		
		});		
	}
})(jQuery);
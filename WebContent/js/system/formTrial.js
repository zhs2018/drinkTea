var MKGlobal = {
	formId: 5886,
	fb: getURLParam('FB'),
	mailRegEx: /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i,
	urlRegEx: /^(file|gopher|news|nntp|telnet|http|ftp|https|ftps|sftp):\/\//,
	animating: false,
	unsaveinfo: 0,
	formSchemeId: null,
	formScheme: {
		// 颜色的信息
	},
	firstSave:true
};

MKGlobal.addUnsaveCount = function(){

	MKGlobal.unsaveinfo++;

	if(MKGlobal.unsaveinfo > 0 && $('.btn_example_save').hasClass('allsaved') ){
		$('.btn_example_save').removeClass('allsaved').text('保存表单');
	}
	if(MKGlobal.unsaveinfo == 0 && !$('.btn_example_save').hasClass('allsaved') ){
		$('.btn_example_save').addClass('allsaved').text('已保存');
	}
};

$(document).ready(function ($) {
	//头部active属性

	$(".formBuilder_example").niceScroll({
		cursorborder: "",
		cursorcolor: "#999"
	});

	$(".formBuilder_edit").niceScroll({
		cursorborder: "",
		cursorcolor: "#999",
		zindex: "10"
	});
	$(".formBuilder_color .style_pad").niceScroll({
		cursorborder: "",
		cursorcolor: "#999"
	});
	$(".formBuilder_color .style_design_item").niceScroll({
		cursorborder: "",
		cursorcolor: "#999"
	});
	// $(".style_design").getNiceScroll().hide();

	$(".group_operate").niceScroll({
		cursorborder: "",
		cursorcolor: "#999"
	});

	renderPage();

	// ---
	$(window).bind('beforeunload', function () {
		if (MKGlobal.unsaveinfo !== 0) {
			return '您有编辑的内容尚未保存，确定不保存离开吗？';
		}
	});

});


// -- basic

function getURLParam(val) {
	var reg = new RegExp("[?&]" + val + "=([^?&]*)[&]?", "i");
	var match = location.search.match(reg);
	return match == null ? false : match[1];
}

// render page 
// 初始化页面中的各种组件的事件
function renderPage() {
	// -- 编辑切换
	var $color = $('.formBuilder_color');
	$(".formBuilder_interim_edit").click(function () {
		var $this = $(this),
			index = $(".formBuilder_interim_edit").index($this),
			isColor = $this.hasClass('formBuilder_interim_edit_color');

		$this.addClass('formBuilder_interim_edit_active').siblings('.formBuilder_interim_edit').removeClass('formBuilder_interim_edit_active');

		if(isColor){	// 为了适应多种屏幕下的显示效果做的更改
			$color.removeClass('color_hide');
		}
		$(".formBuilder_edit:eq(" + index + ")").show().siblings('.formBuilder_edit').addClass('formBuilder_edit_hidden').hide();
		if($('.formBuilder_interim_edit_color:visible').length>0 && !isColor){
			$color.addClass('color_hide');
		} else {
			$color.removeClass('color_hide');
		}
	});

	$(".form_edit_expand_title").click(function(){
		var $container = $(this).siblings('.form_edit_expand_container'),
			$this = $(this);
		if($container.hasClass('form_expand_show')){
			$container.slideUp(300,function(){
				$container.removeClass('form_expand_show');
				$('.formBuilder_edit').getNiceScroll().resize();
				$this.find('.pullicon').attr('src','images/icon/pulldownBlue.png');
			});
		} else {
			$container.addClass('form_expand_show').slideDown(300,function(){
				$('.formBuilder_edit').getNiceScroll().resize();
				$this.find('.pullicon').attr('src','images/icon/pullupBlue.png');
			});
		}

	}).append('<img class="pullicon" src="images/icon/pullupBlue.png">');


	$(".formBuilder_example").getNiceScroll().resize();
	// 载入 配色信息 -- 队列2
	getFormScheme();

	$(document).dequeue('MKFORMEDIT');
}


// 将字符串的 样式转换成 obj
function parseFormScheme(str){
	var o = {},
		cssArray,
		styleList = ['fw','fs','flh','wb','wt','img','imgp','imgr','imgf','lb','lt','logo','timg','fb','ft','it','hb','ht'],
		defaultsValue = ['640px', '16px', '25px', 'F4F5F0', '333333', '', 'left', '', '', 'FEFEFE', '222222', '', '', 'FFFFFF', '333333', 'FFF8DC', '333333','333333'];
	if(str){
		str += '';
		cssArray = str.split(',');
		for(var i=0,len = styleList.length; i<len; i++){
			o[styleList[i]] = cssArray[i]||defaultsValue[i];
		}
	}
	return o;
}

// 读取配色方案
function getFormScheme() {
	function t(o,flag){
		var r = '';
		r += '<div class="formBuilder_color_items" schemeInfo="' + o.info + '" >';
		if(!flag){
			r += '<div class="removeself_color" schemeid="'+o.id+'"></div>'
		}
		r += '<div class="color_show_block csbs" style-name="formBuilder_color_wallpaper" bk="'+o.wb+'" t="'+o.wt+'" style="background-color:#'+o.wb+';color:#' + o.wt + '">';
		r += '<div class="csb_title csbs" style-name="formBuilder_color_title" bk="'+o.lb+'" t="'+o.lt+'" style="background-color:#'+o.lb+';color:#'+o.lt+';">'+o.name+'</div>';
		r += '<div class="csb_contect csbs" style-name="formBuilder_color_form" bk="'+o.fb+'" t="'+o.ft+'" style="background-color:#'+o.fb+';color:#'+o.ft+';">';
		r += '<p class="csb_stitle" style="background-color:#'+o.ft+';color:#'+o.ft+';"></p>';
		r += '<p class="csb_des csbs" style-name="formBuilder_color_instruction" t="'+o.it+'" style="background-color:#'+o.it+';color:#'+o.it+';"></p>';
		r += '<p class="csb_highlight csbs" bk="'+o.hb+'" t="'+o.ht+'" style-name="formBuilder_color_highlight" style="background:#' + o.hb + ';color:#' + o.ht + '">';
		r += '</div></div></div>';
		return r;
	}
}


function __saveLineGenerate(){
	var styleList = ['fw','fs','flh','wb','wt','img','imgp','imgr','imgf','lb','lt','logo','timg','fb','ft','it','hb','ht'],
		defaultsValue = ['640px', '16px', '25px', 'F4F5F0', '333333', '', 'left', '', '', 'FEFEFE', '222222', '', '', 'FFFFFF', '333333', 'FFF8DC', '333333','333333'],
		ans = [];
	for(var i=0,len = styleList.length; i<len; i++){
		ans[i] = MKGlobal.formSchemeStruct[styleList[i]]||defaultsValue[i];
	}

	return ans.join(',');
}

function generateJSON($ui, callback) {
	//存储json串
	var json,
		flags = [],
		groups = [],
		contactState = true,
		mailSenderState = true,
		sendToEmail = '',
		sendToName = '',
		sendToEmailArray = null,
	// ---
		fieldValues = "",
		startTime = "",
		endTime = "",
		endFeedbackNum = "",
		endSubmitOnce = "",
	// ---
		setPreValueList = [];

	$(".mgTagItem").each(function () {
		var value = $(this).find("span").text();
		flags.push('{\\"N\\":\\"' + value + '\\",\\"L\\":\\"' + encodeValue(value) + '\\"}');
	});
	$(".viewContact_group").each(function () {
		groups.push($(this).attr("groupid"));
	});

	contactState = ($('#autoAddContact').attr('checked') == 'checked');
	mailSenderState = ($('#autoSendMail').attr('checked') == 'checked');

	if (mailSenderState) {
		// -- 模拟回车，以防止没有输入完全的邮箱不录入
		var $feedbackEmail = $('.feedback_email'),
			feedbackEmail = $feedbackEmail.attr('id'),
			$feedbackEmailInput = $('#'+feedbackEmail+'-tagInput'),
			__e = jQuery.Event("keypress");
		__e.which = 13;

		$feedbackEmailInput.val($feedbackEmailInput.val()+String.fromCharCode(__e.which));
		$feedbackEmailInput.trigger(__e);

		sendToEmail = $feedbackEmail.mikeTag_AllTag();
		sendToEmailArray = [];
		$.each(sendToEmail, function (email, emailId) {
			sendToEmailArray.push(email);
		});
		sendToEmail = sendToEmailArray.join(';');
		sendToName = $('.feedback_name').val();
		if(sendToEmailArray.length<1){
			mailSenderState = false;
		}
	}

	// 保存
	if (MKGlobal.formId) {
		// 将标签信息写入表单中等待存储
		var typeMap = ['showtext', 'openlink'];
		// console.log($('#payment_ali').attr('checked')=='checked');
		$(".form_title").attr({
			"flags": "\"[" + flags.join(",") + "]\"",
			"groups": groups.join(";"),
			"contact": contactState,
			"sendmail": mailSenderState,
			"sendtoemail": sendToEmail,
			"sendtoname": sendToName,
			"paymenttype": ($('#payment_ali').attr('checked')=='checked')?'ALIPAY':'',
			"backgroundimg": $('.formBuilder_example').css('background-image'),
			"backgroundtype": $('.formBuilder_example').css('background-repeat'),
			'titlebackgroundimg': ($('.form_title').css('background-image')||''),
			"type": $.inArray($('.aftersubmit').find('.radio>input[checked="checked"]').val(), typeMap),
			"finishForm": $('.aftersubmit').find('.formBuilder_edit_input').val()
		});
		json = $.toJSON($(".formBuilder_example_form").formCreator("generate"));

		//我从这里开始改的啊~不要出Bug啊~！！啊~！！！
		// alterForm(id,"TEMPLATE",json,_self);
		// if($(".active_color").attr("shemeid")){
		// 	alterForm(id,"STYLEID",$(".active_color").attr("shemeid"));
		// }
		if (json != MKGlobal.preFormJSON) {
			if (fieldValues == "") {
				fieldValues += '"TEMPLATE":' + json;
			} else {
				fieldValues += ',"TEMPLATE":' + json;
			}

			setPreValueList.push((function (a) {
				return function () {
					MKGlobal.preFormJSON = a;
				};
			})(json));

		}


		if (fieldValues == "") {
			fieldValues += '"STYLE":"' + __saveLineGenerate() + '"';
		} else {
			fieldValues += ',"STYLE":"' + __saveLineGenerate() + '"';
		}
		MKGlobal.preFormSchemeInfo = __saveLineGenerate();

		// if (MKGlobal.formSchemeId) {
		// 	if (__saveLineGenerate() != MKGlobal.preFormSchemeInfo) {
		// 		if (fieldValues == "") {
		// 			fieldValues += '"STYLE":"' + __saveLineGenerate() + '"';
		// 		} else {
		// 			fieldValues += ',"STYLE":"' + __saveLineGenerate() + '"';
		// 		}
		// 	//	MKGlobal.preFormSchemeId = MKGlobal.formSchemeId;
		// 		setPreValueList.push((function (a) {
		// 			return function () {
		// 				MKGlobal.preFormSchemeInfo = a;
		// 			};
		// 		})(__saveLineGenerate()));
		// 	}
		// }

		if ($(".formStart_field").css("display") == "block") {
			startTime = $(".formStart_field").find("#form_startTime input").val();
			if (startTime != "") {
				startTime = startTime + ":00";
			}
			if (startTime != MKGlobal.formInfo.formControl.startDate) {
				if (fieldValues == "") {
					fieldValues += '"STARTDATETIME":"' + startTime + '"';
				} else {
					fieldValues += ',"STARTDATETIME":"' + startTime + '"';
				}
				//	MKGlobal.formInfo.formControl.startDate = startTime;
				setPreValueList.push((function (a) {
					return function () {
						MKGlobal.formInfo.formControl.startDate = a;
					};
				})(startTime));
			}
		} else {
			if (MKGlobal.formInfo.formControl.startDate != "") {
				if (fieldValues == "") {
					fieldValues += '"STARTDATETIME":""';
				} else {
					fieldValues += ',"STARTDATETIME":""';
				}
				//	MKGlobal.formInfo.formControl.startDate = "";
				setPreValueList.push((function (a) {
					return function () {
						MKGlobal.formInfo.formControl.startDate = a;
					};
				})(""));
			}
		}

		if ($(".formEndTime_field").css("display") == "block") {
			endTime = $(".formEndTime_field").find("#form_endTime input").val();
			if (endTime != "") {
				endTime = endTime + ":00";
			}
			if (endTime != MKGlobal.formInfo.formControl.stopDate) {
				if (fieldValues == "") {
					fieldValues += '"STOPDATETIME":"' + endTime + '"';
				} else {
					fieldValues += ',"STOPDATETIME":"' + endTime + '"';
				}
				//	MKGlobal.formInfo.formControl.stopDate = endTime;
				setPreValueList.push((function (a) {
					return function () {
						MKGlobal.formInfo.formControl.stopDate = a;
					};
				})(endTime));
			}
		} else {
			if (MKGlobal.formInfo.formControl.stopDate != "") {
				if (fieldValues == "") {
					fieldValues += '"STOPDATETIME":""';
				} else {
					fieldValues += ',"STOPDATETIME":""';
				}
				//	MKGlobal.formInfo.formControl.stopDate = "";
				setPreValueList.push((function (a) {
					return function () {
						MKGlobal.formInfo.formControl.stopDate = a;
					};
				})(""));
			}
		}

		if ($(".formEndMax_field").css("display") == "block") {
			endFeedbackNum = $(".formEndMax_field").find(".formEnd_feedbackNum input").val();
			if (endFeedbackNum != MKGlobal.formInfo.formControl.maxFeedback) {
				if (fieldValues == "") {
					fieldValues += '"MAX":"' + endFeedbackNum + '"';
				} else {
					fieldValues += ',"MAX":"' + endFeedbackNum + '"';
				}
				//	MKGlobal.formInfo.formControl.maxFeedback = endFeedbackNum;
				setPreValueList.push((function (a) {
					return function () {
						MKGlobal.formInfo.formControl.maxFeedback = a;
					};
				})(endFeedbackNum));
			}
		} else {
			if (MKGlobal.formInfo.formControl.maxFeedback != "") {
				if (fieldValues == "") {
					fieldValues += '"MAX":""';
				} else {
					fieldValues += ',"MAX":""';
				}
				//	MKGlobal.formInfo.formControl.maxFeedback = "";
				setPreValueList.push((function (a) {
					return function () {
						MKGlobal.formInfo.formControl.maxFeedback = a;
					};
				})(""));
			}
		}

		//这里增加 设置不可重复提交 字段
		if ($('#endFormSubmitOnce').attr("checked") == 'checked') {
			if (MKGlobal.formInfo.formSubmitOnce !== 1) {
				if (fieldValues == "") {
					fieldValues += '"SUBMITONCE":true';
				} else {
					fieldValues += ',"SUBMITONCE":true';
				}
				//	MKGlobal.formInfo.formSubmitOnce = 1;
				setPreValueList.push((function (a) {
					return function () {
						MKGlobal.formInfo.formSubmitOnce = a;
					};
				})(1));
			}
		} else {
			if (MKGlobal.formInfo.formSubmitOnce !== 0) {
				if (fieldValues == "") {
					fieldValues += '"SUBMITONCE":false';
				} else {
					fieldValues += ',"SUBMITONCE":false';
				}
			}
			//		MKGlobal.formInfo.formSubmitOnce = 0;
			setPreValueList.push((function (a) {
				return function () {
					MKGlobal.formInfo.formSubmitOnce = a;
				};
			})(0));
		}
	}
}

function encodeValue(value) {
	var newValue = "";
	for (var i = 0; i < value.length; i++) {
		if (/^[\u4e00-\u9fa5]+/.test(value.charAt(i))) {
			newValue += getChineseSpell(value.charAt(i))[1];
		} else {
			newValue += value.charAt(i).toLowerCase();
		}
	}
	return newValue.replace(/[^a-zA-Z0-9]/g, '');
}

var mgColorManager = (function () {
	var mgColor = '#ffffff',
		colorInfo = {};

	function getColorHSV(r, g, b) {
		var r = parseInt(('' + r).replace(/\s/g, ''), 10);
		var g = parseInt(('' + g).replace(/\s/g, ''), 10);
		var b = parseInt(('' + b).replace(/\s/g, ''), 10);

		r = r / 255;
		g = g / 255;
		b = b / 255;

		var minRGB = Math.min(r, Math.min(g, b)),
			maxRGB = Math.max(r, Math.max(g, b)),
			computedH, computedS, computedV;

		var d = (r == minRGB) ? g - b : ((b == minRGB) ? r - g : b - r);
		var h = (r == minRGB) ? 3 : ((b == minRGB) ? 1 : 5);

		computedH = 60 * (h - d / (maxRGB - minRGB));
		computedS = (maxRGB - minRGB) / maxRGB;
		computedV = maxRGB;
		return [computedH, computedS, computedV];
	}

	function HEX2RGB(hex) {
		// Expand shorthand form (e.g. "03F") to full form (e.g. "0033FF")
		var shorthandRegex = /^#?([a-f\d])([a-f\d])([a-f\d])$/i;
		hex = hex.replace(shorthandRegex, function (m, r, g, b) {
			return r + r + g + g + b + b;
		});

		var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
		return result ? {
			r: parseInt(result[1], 16),
			g: parseInt(result[2], 16),
			b: parseInt(result[3], 16)
		} : null;
	}

	function COLORGARYLVL(r, g, b) {
		return parseInt(r) * 0.299 + parseInt(g) * 0.587 + parseInt(b) * 0.114;
	}

	function HSV2HEX(h, s, v) {
		var c = s * v,
			t = Math.abs((h / 60) % 2 - 1),
			x = c * (1 - t),
			m = v - c,
			r_t = 0, g_t = 0, b_t = 0;

		if (h >= 0 && h < 60) {
			r_t = c;
			g_t = x;
		} else if (h >= 60 && h < 120) {
			r_t = x;
			g_t = c;
		} else if (h >= 120 && h < 180) {
			b_t = x;
			g_t = c;
		} else if (h >= 180 && h < 240) {
			g_t = x;
			b_t = c;
		} else if (h >= 240 && h < 300) {
			r_t = x;
			b_t = c;
		} else if (h >= 300 && h < 360) {
			b_t = x;
			r_t = c;
		}

		function _toHEX(v) {
			return (Math.floor(v * 255)).toString(16);
		}

		return _toHEX(r_t + m) + _toHEX(g_t + m) + _toHEX(b_t + m); //[(r_t+m)*255, (g_t+m)*255, (b_t+m)*255];
	}


	return {
		setColor: function (str) {
			mgColor = str;
			var rgbC = HEX2RGB(str);
			colorInfo.R = rgbC.r;
			colorInfo.G = rgbC.g;
			colorInfo.B = rgbC.b;

			var hsvC = getColorHSV(colorInfo.R, colorInfo.G, colorInfo.B);

			colorInfo.H = Math.floor(hsvC[0]);
			colorInfo.S = Math.floor(hsvC[1] * 100);
			colorInfo.V = Math.floor(hsvC[2] * 100);
			return this;
		},
		getHSV2RGB: function (arrayHSV) {
			if(arrayHSV){
				return HSV2HEX(arrayHSV[0],arrayHSV[1],arrayHSV[2]);
			}
			return HSV2HEX(colorInfo.H, colorInfo.S / 100, colorInfo.V / 100);
		},
		getColorVal: function (colorStr, valueType) {
			var rgbC = HEX2RGB(colorStr),
				t = {};
			t.R = rgbC.r;
			t.G = rgbC.g;
			t.B = rgbC.b;

			var hsvC = getColorHSV(t.R, t.G, t.B);

			t.H = Math.floor(hsvC[0]);
			t.S = Math.floor(hsvC[1] * 100);
			t.V = Math.floor(hsvC[2] * 100);
			t.GY = COLORGARYLVL(t.R, t.G, t.B);
			if(valueType){
				return t[valueType];
			} else {
				return t;
			}
			
		},
		getColorGroup: function () {
			var brighten = colorInfo.S / 100,
				black = colorInfo.V / 100;

			var bkcolor = mgColor,
				bk_hover_color = HSV2HEX(colorInfo.H, (brighten - 0.08 > 0 ? brighten - 0.08 : 0), ((black - 0) + 0.14 < 1 ? (black - 0) + 0.14 : 1)),
				shadowcolor = HSV2HEX(colorInfo.H, ((brighten - 0.07 > 0) ? brighten + 0.07 : 0), (black - 0.06 > 0 ? (black - 0.06) : 0)),
				bordercolor = HSV2HEX(colorInfo.H, ((brighten + 0.04 < 1) ? brighten + 0.04 : 1), (black - 0.36 > 0 ? black - 0.36 : 0));

			shadowcolor = HSV2HEX(colorInfo.H, 0.52, 0.59);
			// bordercolor = HSV2HEX(colorInfo.H,0.60,0.37); 
			return {
				'BK': bkcolor,
				'BKH': bk_hover_color,
				'SD': shadowcolor,
				'BD': bordercolor
			};
		}
	};
})();
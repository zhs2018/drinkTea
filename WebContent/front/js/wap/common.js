function getMessageCount() {
	$.ajax({
		type : "GET",
		url : shop_do_main + "/sysmessage/getCountbySysmessage.html",
		data : {},
		success : function(result) {
			var result = JSON.parse(result);
			$(".badge").html(result.Count);
		}
	});
}
function Map() {
	this.container = new Object();
}

Map.prototype.put = function(key, value) {
	this.container[key] = value;
}

Map.prototype.get = function(key) {
	return this.container[key];
}

Map.prototype.keySet = function() {
	var keyset = new Array();
	var count = 0;
	for ( var key in this.container) {
		// 跳过object的extend函数
		if (key == 'extend') {
			continue;
		}
		keyset[count] = key;
		count++;
	}
	return keyset;
}

Map.prototype.size = function() {
	var count = 0;
	for ( var key in this.container) {
		// 跳过object的extend函数
		if (key == 'extend') {
			continue;
		}
		count++;
	}
	return count;
}

Map.prototype.remove = function(key) {
	delete this.container[key];
}

Map.prototype.toString = function() {
	var str = "";
	for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
		str = str + this.container[keys[i]] + ";";
	}
	return str;
}

/** ***********************************转换时间格式************************************ */
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

function show() {
	bootbox.dialog({
		message : "您尚未登录，请登录后再进行此操作！",
		title : "<center>提示</center>",
		buttons : {
			main : {
				label : "确定",
				className : "blue",
				callback : function() {
					window.location.href = shop_do_main + "/login.html";
				}
			}
		}
	});
}

function showReply() {
	bootbox.dialog({
		message : "评论成功",
		title : "<center>提示</center>",
		buttons : {
			main : {
				label : "确定",
				className : "blue",
				callback : function() {

				}
			}
		}
	});
}

function showReplyIfLogin() {
	var bol = true;
	$.ajax({
		url : shop_do_main + "/streetsnap/userIfLogin.html",
		dataType : "json",
		type : "post",
		async : false,
		success : function(data) {
			if (data.result > 0) {

				bol = true;
			} else {
				bol = false;
			}
		}
	});
	return bol;

}

function showMessage(message) {
	$.dialog("alertHasOk", "", message, 0);
}
function showMessageRefresh(message) {
	$.dialog("alertHasOk", "", message, 0, function() {
		window.location.href = window.location.href;
	});
}

function showError(data) {
	if (data == undefined || data == "") {
		$.dialog("alertHasOk", "", "网络中断……");
	} else {
		$.dialog("alertHasOk", "", message, 0);
	}

}
function timeStamp2String(time) {
	var datetime = new Date("yyyy-MM-dd HH:mm:ss");
	if (time != undefined) {
		datetime.setTime(time);
		var year = datetime.getFullYear();
		var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
		var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
		var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
		var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
		var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
		return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
	}
}
function imgZuhe(imgurl, size) {
	if (undefined == imgurl || imgurl == "") {
		return "";
	} else {
		var fisrt = imgurl.substring(0, imgurl.indexOf('.'));
		var end = imgurl.substring(imgurl.indexOf('.'), imgurl.length);
		return fisrt + size + end;
	}
}

function getRequest(param) {
	var url = location.search; // 获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	return theRequest[param];
}

var JPlaceHolder = {
	// 检测
	_check : function() {
		return 'placeholder' in document.createElement('input');
	},
	// 初始化
	init : function() {
		if (!this._check()) {
			this.fix();
		}
	},
	// 修复
	fix : function() {
		jQuery(':input[placeholder]').each(function(index, element) {
			var self = $(this), txt = self.attr('placeholder');
			self.wrap($('<div></div>').css({
				position : 'relative',
				zoom : '1',
				border : 'none',
				background : 'none',
				padding : 'none',
				margin : 'none'
			}));
			var pos = self.position(), h = self.outerHeight(true), paddingleft = self.css('padding-left');
			var holder = $('<span></span>').text(txt).css({
				position : 'absolute',
				left : pos.left,
				top : '6px',
				height : '30px',
				lienHeight : '30px',
				paddingLeft : paddingleft,
				color : '#aaa'
			}).appendTo(self.parent());
			self.focusin(function(e) {
				holder.hide();
			}).focusout(function(e) {
				if (!self.val()) {
					holder.show();
				}
			});
			holder.click(function(e) {
				holder.hide();
				self.focus();
			});
		});
	}
};

var UIToastr = function() {
	var kk = function(text) {
		alert(text);
	}
	return {
		// main function to initiate the module
		init : function() {

			var i = -1, toastCount = 0, $toastlast, getMessage = function() {
				i++;
				if (i === msgs.length) {
					i = 0;
				}

				return msgs[i];
			};
		},
		kk2 : function(title, msg, shortCutFunction) {
			toastr.options = {
				"closeButton" : true,
				"debug" : false,
				"positionClass" : "toast-top-right",
				"onclick" : null,
				"showDuration" : "1000",
				"hideDuration" : "1000",
				"timeOut" : "5000",
				"extendedTimeOut" : "1000",
				"showEasing" : "swing",
				"hideEasing" : "linear",
				"showMethod" : "fadeIn",
				"hideMethod" : "fadeOut"
			};

			$("#toastrOptions").text(
					"Command: toastr[" + shortCutFunction + "](\"" + msg
							+ (title ? "\", \"" + title : '')
							+ "\")\n\ntoastr.options = "
							+ JSON.stringify(toastr.options, null, 2));

			var $toast = toastr[shortCutFunction](msg, title);
			$toastlast = $toast;
		}

	};

}();
//弹框
function showMessage(message) {
	bootbox.dialog({
		message : message,
		title : "提示",
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
//弹框，没有确认按钮 主要用于加载中
function showTip(message) {
	bootbox.dialog({
		message : message,
		title : "温馨提示",
		buttons : {
		}
	});
}
//弹框，确认按钮为刷新当前页
function showMessageVsRefresh(message) {
	bootbox.dialog({
		message : message,
		title : "提示",
		buttons : {
			main : {
				label : "确定",
				className : "blue",
				callback : function() {
					window.location.href = window.location.href;
				}
			}
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

function imgZuhe(imgurl, size) {
	var fisrt = imgurl.substring(0, imgurl.indexOf('.'));
	var end = imgurl.substring(imgurl.indexOf('.'), imgurl.length);
	return fisrt + size + end;
}

function isPositiveNum(s) {//是否为正整数
	var re = /^[0-9]*[1-9][0-9]*$/;
	return re.test(s)
}
//时间戳转换函数
function timeStamp2String(time) {
	var datetime = new Date();
	datetime.setTime(time);
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0"
			+ (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate()
			: datetime.getDate();
	var hour = datetime.getHours() < 10 ? "0" + datetime.getHours()
			: datetime.getHours();
	var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes()
			: datetime.getMinutes();
	var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds()
			: datetime.getSeconds();
	return year + "-" + month + "-" + date + " " + hour + ":" + minute
			+ ":" + second;
}

function MoneyCheck(money) {
	var isNum = /^\d+(\.\d+)?$/;
	if (!isNum.test(money)) {
		alert("请输入正确的金钱");
		return false;
	} else {
		return true;
	}
}

function toFilexdWx(s) {//2位小数
	if(undefined!=s){
		s = s.toFixed(2);
	}
	return s;
}

function substrings(content,s) {//2位小数
	if(undefined!=s){
		if(s.length>=s){
			return s.substring(0,s)+"...";
		}else{
			s = s;
		}
	}
	return s;
}
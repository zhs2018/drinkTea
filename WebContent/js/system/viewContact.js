var	tmpValue, // 存放临时的值，用于一些特殊位置的比对
	cnReg = /^[\u4e00-\u9fa5]+/, //用这个正则表达式可以取出中文字符  /[\u4e00-\u9fa5]/gi
	groupArray = {},
	tagArray,
	loadingCount=0,
	groupInfomationsArray,
	MIKE = {};

MIKE.historyCount = 0;
// 2013-07-25 添加记录多条的object
MIKE.multipleInfo = {};
/**
 *	文字转换的方法 - 将文字转换成拼音首字母 ,大写字母转换成小写字母
 *	@param	value 传入一个字符串，将这个字符串解析
 *	@author Samuel Shen
 *	@2012 - 11 - 30
 */

function encodeNameValue(value) {
	var newValue = "",tmpVal = "",tmpChineseSpell;

	for(var i = 0; i < value.length; i++) {
		if(cnReg.test( value.charAt(i) )) {
			tmpChineseSpell = getChineseSpell(value.charAt(i));
			newValue += tmpChineseSpell[1];
			tmpVal += tmpChineseSpell[0];
		} else {
			newValue += value.charAt(i).toLowerCase();
			tmpVal += value.charAt(i).toLowerCase();
		}
	}
	newValue = tmpVal.replace(/[^a-zA-Z0-9]/g,'')+';'+newValue.replace(/[^a-zA-Z0-9]/g,'');
	return newValue;
}

function encodeValue(value) {
	var newValue = "";
	for(var i = 0; i < value.length; i++) {
		if(cnReg.test( value.charAt(i) )) {
			newValue += getChineseSpell(value.charAt(i))[1];
		} else {
			newValue += value.charAt(i).toLowerCase();
		}
	}
	return newValue.replace(/[^a-zA-Z0-9]/g,'');
}

/**
 *	日期的处理方法，专门用于处理日期，将日期进行处理转换成合适的对象
 *	@param	date js的日期对象
 *	@return	dateObject - 返回一个日期对象，里面包含了
 *				year: 完整的年
 *				month:  完整的数字显示的月 0 - 11
 *				customMonthStamp:  显示诸如 201212 这样的数字
 *				monthCN: 显示中文的月份
 *				dayInMonth: 显示目前是第几天
 *				dayInWeek: 显示是星期几的数字表示
 *				weekCN: 显示中文表示的星期
 *				shortFormateTime: 显示短型时间： 诸如 15:10
 *	@author Samuel Shen
 *	@2012 - 12 - 07
 */

function MKDateFormat(date) {
	// 2013-12-26 进行简单的压缩
	var dateObject, weekCN, monthCN,
		weekCNMap = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"],
		monthCNMap = ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"];

	weekCN = weekCNMap[parseInt(date.getDay(),0)];
	monthCN = monthCNMap[parseInt(date.getMonth(),0)];

	dateObject = {
		year: date.getFullYear(),
		// 完整的年
		month: date.getMonth(),
		// 完整的数字显示的月 0 - 11
		customMonthStamp: date.getFullYear() * 100 + parseInt(date.getMonth()+1),
		// 显示诸如 201212 这样的数字
		monthCN: monthCN,
		// 显示中文的月份
		dayInMonth: date.getDate(),
		// 显示目前是第几天
		dayInWeek: date.getDay(),
		// 显示是星期几的数字表示
		weekCN: weekCN,
		// 显示中文表示的星期
		shortFormateTime: date.getHours() + ":" + ((date.getMinutes() < 10) ? "0" : "") + date.getMinutes() // 显示短型时间： 诸如 15:10 / 12:09
	};

	return dateObject;
}

/*
 *	插入一条新的电话，或者是联系方式的方法
 *	@param	ui
 *		用于确定插入的位置
 *	@param	name
		插入的组件的标题的名字
 *	@param	value
		插入组件的值
 *	@param	gid
		ajax获取的"id" 
 *	@author Samuel Shen
 *	@2012 - 11 - 30
*/
function addNewInfo(ui, name, value, gid) {
	//ui.parent(".info").clone().insertAfter(ui.parent(".info")).find("input").val(value).attr({"gid": gid,"preValue":value}).parent(".info").find("p").text(name).parent(".info").find(".btn-group .btn-dropdown span").text(name).attr("preValue",name);
	ui.parent(".info").clone().show().insertAfter(ui.parent(".info")).find(".describe").show().html(value).attr({"gid": gid,"preValue":value}).parent(".info").find("p").text(name).parent(".info").find(".btn-group .btn-dropdown span").text(name).attr("preValue","");
	ui.hide();
}

/**
 *	在viewContact页面上所有的ajaxHandle 方法的集合，都写在这个里面了。
 */
var contactHandleMethod = {
	/**
	 *	进行笔记写入的方法，写入结束之后会进行数据的刷新。
	 *	@param	value 文本框中的值
	 *	@author Samuel Shen
	 *	@2012 - 12 - 12
	 */
	addContactNote: function(value) {
		$.ajax({
			url: "handler/handleAddContactNote.php",
			type: "POST",
			dataType: "JSON",
			data: {
				"DATA": "{\"CTID\":\"" + g_contatctId + "\",\"TEXT\":" + JSON.stringify(value) + "}"
			},
			success: function(data) {
				if(data.flag) {
					contactHandleMethod.getHistoryInfo(0);
				}
			}
		});
	},
	/**
	*	对一条笔记进行修改的方法，修改完成以后更新数据。
	*/
	alterContactNote: function(id, value) {
		$.ajax({
			url: "handler/handleAlterContactNote.php",
			type: "POST",
			dataType: "JSON",
			data: {
				"DATA": "{\"CTNID\":\"" + id + "\",\"TEXT\":" +  JSON.stringify(value) + "}"
			},
			success: function(data) {
				if(data.flag) {
					contactHandleMethod.getHistoryInfo(0);
				}
			}
		});
	},
	/**
	 *	删除一条笔记的方法,删除之后会去刷新日志列表的数据
	 */
	removeContactNote: function(id){
		$.ajax({
			url: "handler/handleRemoveContactNote.php",
			type: "POST",
			dataType: "JSON",
			data: {
				//DATA='{"CTNID":"5"}';
				"DATA": "{\"CTNID\":\"" + id + "\"}"
			},
			success: function(data) {
				if(data.flag) {
					contactHandleMethod.getHistoryInfo(0);
				}
			}
		});
	},
	/**
	 *	修改值的时候调用的方法 每次只能修改一条记录。修改完成之后进行数据的刷新。
	 *	@param	fieldName:
	 *		字段的名称，表示这个字段描述的是什么一个空间
	 *		NAME, GENDER, CITY, EMAIL, COMPANY, JOB, WEBSITE, ADDRESS, NOTE   INFO, IM, TAG
	 *	@param	value:
	 *		传入的参数，参数是一个已经构造好的字符串，json格式
	 *	@author Samuel Shen
	 *	@2012 - 12 - 07
	 */
	alterData: function(fieldName, value, async) {
		var data = "{ \"ID\":\"" + g_contatctId + "\",\"FIELD\":\"" + fieldName + "\",\"VALUE\":" + value + "}",
			state = (!async);

		$.ajax({
			url: "handler/handleAlterContact.php",
			type: "POST",
			dataType: "json",
			async: state,
			data: {
				"DATA": data
			},
			success: function(data) {
				if(data.flag) {
					// 刷新日志数据
					contactHandleMethod.getHistoryInfo(0);
				}
			}
		});
	},
	/**
	 * 读取数据的方法,最后返回的是指定的一项的数据的ID
	 * 适用于INFO、TAG 和 IM 的数据添加
	 * 使用了 同步方法！ -- 异步会很大概率取不到刚刚存进去的值 --也可以避免ajax的嵌套
	 *
	 * @param	fieldName 这个空间的名字
	 * @param	lineNumber	行号，从0开始
	 * @return	这个数据的ID
	 * @author Samuel Shen
	 * @2012 - 12 - 07
	 */
	getOneFieldDataOn: function(fieldName, lineNumber, async) {
		var id,
			state = (!async);

		$.ajax({
			url: "handler/handleGetContact.php",
			type: "POST",
			async: state,
			data: {
				"DATA": "{\"ID\":\"" + g_contatctId + "\"}"
			},
			dataType: "json",
			// 增加datatype就可以少做编码这一步了。
			success: function(data) {
				id = data.data[fieldName.toLowerCase()][lineNumber]["id"];
			}
		});
		return id;
	},
	/**
	 *	获取日志部分的内容。并将日志内容显示。
	 *	@author Samuel Shen
	 *	@2012 - 12 - 12 update
	 */
	getHistoryInfo: function(maxId) {
		$.ajax({
			url: "handler/handleGetContactHistory.php",
			type: "POST",
			data: {
				"DATA": "{\"ID\":\"" + g_contatctId + "\",\"TYPE\":\"1,2,3,4,5,6,7,8,9,10\",\"MAXID\":\""+maxId+"\"}"
			},
			dataType: "json",
			success: function(result) {
				if(result.flag) {
					if(maxId == 0){
						$(".history_list").empty().unbind('scroll');
					}
					var data = {data:[]};

					var objectLength = 0;
					var trueLength = 0;
					for(i in result.data) {
						if(result.data[i].des !== false && result.data.hasOwnProperty(i)) {
							data.data[objectLength] = result.data[i];
							objectLength++;
						}
						trueLength++;
					}

					//$.each(data.data,function(i){
					var his = {},
						min = 1;
					if(trueLength == 21){
						min = 2;
					}
					for(var i = 0; i <= objectLength - min; i++) {
							var currentObj = {
								data:data.data[i],
								val: i
							};
							var historyInfo = {
								html: '',
								obj: ''
							},
							formatedTime = mkHistoryDateFormat(data.data[i].time),
							// 获取当前的日期的格式化的对象
							monthPlace = monthBlockCreator(formatedTime);
							$(document).queue('ajaxGetHistoryFeedback', (function (obj){
								return (function(){
									var tObj = obj,
										i = tObj.val;
									his[i] = historyInfo;
									if (data.data[i].type == "1") {
										createHistoryHTML(data.data[i].des, (data.data[i].name)?(data.data[i].name):"我", data.data[i].time, data.data[i].type, data.data[i].id, his[i], "", "", "", data.data[i].note);
									}else if (data.data[i].type == "8") {
										createHistoryHTML(data.data[i].des, (data.data[i].name)?(data.data[i].name):"我", data.data[i].time, data.data[i].type, data.data[i].id, his[i], data.data[i].feedbackId, data.data[i].formId);
									}else if (data.data[i].type == "9" || data.data[i].type == "10") {
										createHistoryHTML(data.data[i].mailSubject, (data.data[i].name)?(data.data[i].name):"我", data.data[i].time, data.data[i].type, data.data[i].id, his[i], "", "",(data.data[i].mail)?(data.data[i].mail):"");
									}else {
										createHistoryHTML(data.data[i].des, (data.data[i].name)?(data.data[i].name):"我", data.data[i].time, data.data[i].type, data.data[i].id, his[i]);
									}
								});
							})(currentObj));

							$(document).queue('ajaxGetHistoryFeedback', (function (obj,formatedTimeStamp){
								return (function(){
									var tObj = obj;
									var i = obj.val;
									var historyHTML = his[i].obj;
									if(historyHTML){
										var lastHistoryData = ((i == 0)?"":data.data[i-1]);
										if (lastHistoryData !== ""){
											var lastHistoryDate = new Date(data.data[i-1].time).toLocaleDateString();
											var currentHistoryDate = new Date(data.data[i].time).toLocaleDateString();
											var hideFlag = false;
											// 判断是否同类型————这里改了！！！！！！！！！
											if ( (data.data[i].type >= 4 && data.data[i].type <= 7 && data.data[i-1].type >= 4 && data.data[i-1].type <= 7) || (data.data[i].type >= 9 && data.data[i].type <= 10 && data.data[i-1].type >= 9 && data.data[i-1].type <= 10) || (data.data[i].type == data.data[i-1].type) ) {
												// 判断是否同日期
												if ( currentHistoryDate == lastHistoryDate ){
													hideFlag = true;
												}
											}

											if (hideFlag) {
												// 隐藏日期图标
												historyHTML.find(".week").css("visibility", "hidden");
												historyHTML.find(".day").css("visibility", "hidden");
											}
										}
										historyHTML.appendTo("#"+formatedTimeStamp);//("#"+formatedTimeStamp+" .month");
									}
									$(document).dequeue('ajaxGetHistoryFeedback');
								});
							})(currentObj,formatedTime.customMonthStamp));
							delete currentObj;
					}

					$(document).queue('ajaxGetHistoryFeedback',function(){
						$(".history").show();
						$(".history_list").getNiceScroll().resize();


						$('.history_list').unbind('scroll').bind('scroll',function (evt){
							var scrollHeight = this.scrollHeight,
								scrollTop = this.scrollTop,
								currentItemLength = $(this).find(".day").length,
								flagItemLength = ((currentItemLength<16)?0:(currentItemLength-15));

							// --lazy load 后 就把 以前的 事件 给解除绑定了，需要重新加回来，
							// -- 这里就是不科学的代表啊！ 2014-02-10
							var monthLength = $(".month").not(":first").length;
							for(var i = 2; i <= monthLength; i++) {
								if($(".month:eq(" + i + ")").position().top > 0) {
									var monthTopItem = $(".month:eq(" + (i - 1) + ")");
									var monthhtml = monthTopItem.html();
									isChange = true;
									break;
								}
								var monthhtml = $(".month:eq(" + i + ")").html();
							}
							$(".month_top").html(monthhtml);
							if(trueLength == 21){
								MIKE.historyCount = result.data[trueLength-1].id;
								if($(this).find(".day:eq("+flagItemLength+")").offset().top < $(window).height()) {
									contactHandleMethod.getHistoryInfo(MIKE.historyCount);
									$(this).unbind('scroll');
								}
							}
						});

						$(".history_filterBox").each(function(){
							historyButtonActive($(this));
						});
					});

					$(document).dequeue('ajaxGetHistoryFeedback');

				}
				//$(".history").mgLoading("show");
				if ($(".monthBlock>div:visible").length > 0) {
					$(".history_list").css("visibility", "visible");
					$('.history_list').removeClass('history_list_hide');
				}
				loadingCount++;
				if(loadingCount>1){
					$(".loading").remove();
					$('.information_detail').removeClass('information_detail_hide');
					loadingFinish();
				}
			}
		});
	}
};


function loadingFinish(){
	// check height
	var $noteField = $('#NOTE .describe')[0],
		$this = $('#NOTE .describe'),
		val = $this.val().replace(/(<([^>]+)>)/g, "");

	if(!inputLengthChecck(val,256)){
		$this.val(val.substr(0,256));
		$this.blur();
	}
	$this.height(10).height($noteField.scrollHeight);
	$(".information_detail").getNiceScroll().resize();
	$(".information_detail").scrollTop(1000);
}
function inputLengthChecck(val,lenNum) {
	return ($.trim(val).length < lenNum);
}
/**
 *	传入一个Format的日期对象，进行月份的判断操作的方法。
 *	@param	formatedTime 进行格式化过的日期对象
 *	@author Samuel Shen
 *	@2012 - 12 - 07
 */

function monthBlockCreator(formatedTime) {
	var count = 0; //计数器，记录的是要插入的元素的位置
	//查询区块，不存在就直接创造一个
	if(!$(".monthBlock").length) {
		$(".month_top").html(formatedTime.monthCN + "<br /><font class=\"year\">" + formatedTime.year + "</font>");
		$("<div class=\"monthBlock\" id=" + formatedTime.customMonthStamp + "><p class=\"month\">" + formatedTime.monthCN + "<br /><font class=\"year\">" + formatedTime.year + "</font></p></div>").appendTo(".history_list");
		$(".history_list .month:first").css("display", "none");
	} else {
		if($("#"+formatedTime.customMonthStamp).length<1){
			$(".monthBlock").each(function() {
				var $next = $(this).next();
				if(formatedTime.customMonthStamp > $(this).attr("id")) {
					$(".history_list .month").removeAttr("style");
					$(".month_top").html(formatedTime.monthCN + "<br /><font class=\"year\">" + formatedTime.year + "</font>");
					$("<div class=\"monthBlock\" id=" + formatedTime.customMonthStamp + "><p class=\"month\">" + formatedTime.monthCN + "<br /><font class=\"year\">" + formatedTime.year + "</font></p></div>").insertBefore(this);
					$(".history_list .month:first").css("display", "none");
					return false;
				} else if(formatedTime.customMonthStamp < $(this).attr("id") && ( $next.length===0 || formatedTime.customMonthStamp > $next.attr("id"))){
					$("<div class=\"monthBlock\" id=" + formatedTime.customMonthStamp + "><p class=\"month\">" + formatedTime.monthCN + "<br /><font class=\"year\">" + formatedTime.year + "</font></p></div>").insertAfter(this);
					return false;
				}
			});
		}
	}
}

/**
 *	格式化出History中传入的时间，返回一个对象数组，想要什么调用什么。
 *	@param	datatring 数据库中的存放方式 诸如 2012-12-12 12:12:12
 *	@return	处理过的时间对象
 *	@author Samuel Shen
 *	@2012 - 12 - 12 update
 */

function mkHistoryDateFormat(dateString) {
	var dateTimeArray = dateString.split(/[^0-9]/g),
		date = new Date(),
		dateObject, weekCN, monthCN;
	date.setFullYear(dateTimeArray[0], (dateTimeArray[1] - 1), dateTimeArray[2]); // 格式化一个时间
	date.setHours(dateTimeArray[3]);
	date.setMinutes(dateTimeArray[4]);
	date.setSeconds(dateTimeArray[5]);
	// IMPROTANT!
	dateObject = MKDateFormat(date);
	return dateObject;
}

/**
 *	对传入的数据进行HTML的生成
 *	@param	describe 详细日志的内容 / 为空的时候(false)就会不执行HTML的生成
 *	@param	name 操作人的姓名
 *	@param	time 操作执行的时间
 *	@param	type 日志的类型
 *	@return	一段HTML的代码
 *	@author Samuel Shen
 *	@2012 - 12 - 12 update
 */

function createHistoryHTML(describe, name, time, type, id, obj, feedbackId, formId, mailId, noteId) {
	var formatedTime = {},
		historyBlockHTML = "";
	if(describe){
		describe = MK_ESCAPE.unescape(describe).replace(/<br>/g,' ').replace(/\n/g,' ');
		describe = MK_ESCAPE.tohtmlStr(describe); // 字符串的特殊符号转义处理

		var formName,
			generatedCode = "",
			historyType, historyHint, historyImage;
		//获取处理好的时间对象
		formatedTime = mkHistoryDateFormat(time);
		
		// history_blog 部分
		if(type >= 4 && type <= 7) {
			historyType = "\"history_blog\"";
			historyImage = "history_icon_log";
			if(type == 7) historyHint = "更新了群组";
			if(type == 6) historyHint = "更新了标签";
			if(type == 5) historyHint = "更新了联系方式";
			if(type == 4) historyHint = "新建了联系人";
		}
		//表单反馈部分
		else if (type == 8) {
			historyType  = 'history_feedback';
			historyImage = "history_icon_feedback";
			historyHint = "收到了一封反馈";
		}
		//邮件发送部分
		else if(type >= 9 && type <= 10) {
			historyType  = 'history_mail';
			historyImage = "history_icon_mail";
			if(type == 9) historyHint = "发送了一封邮件";
			if(type == 10) historyHint = "发送的邮件已被阅读";
		}
		// history_note 部分
		else if(type == 1) {
			historyType = "\"history_note deletMust\"";
			historyImage = "history_icon_note";
			historyHint = "添加了一条笔记";
		}

		historyBlockHTML = "<div class=" + historyType + " id=\"history_"+id+"\">" + "<p class=\"week\">" + formatedTime.weekCN + "</p>" + "<p class=\"day\">" + ((formatedTime.dayInMonth < 9) ? ("0" + formatedTime.dayInMonth) : formatedTime.dayInMonth) + "</p>" + "<div class=\"history_text\">" + "<div class='"+historyImage+"'></div>" + "<div style=\"margin-left:20px;line-height:22px;\">" + "<span class=\"name\">" + name + "</span>" + "<span class=\"dowhat\">" + historyHint + "&nbsp;&nbsp;&nbsp;&nbsp;" + formatedTime.shortFormateTime + "</span><br/>";

		
		$(document).queue('_historyAjax',function(){
			if(type >= 4 && type <= 7) {
				historyBlockHTML += describe;
			} else if(type == 1) {
				historyBlockHTML += "<div class=\"note_edit_input\">" + describe + "</div>";
				historyBlockHTML += "<span class=\"note_edit\" noteId=\""+noteId+"\">编辑</span>" + "<span class=\"note_delete\" noteId=\""+noteId+"\">移除</span>";

				historyBlockHTML += "<div class=\"popwin\" id=\"popwin_noteDelete\">" + "<p class=\"popwin_title\">" + "删除笔记" + "</p>" + "<div class=\"popwin_content\">" + "<img class=\"mgb10\" src=\"images/icon/popwin_eroteme.png\" />" + "<p class=\"popwin_tips\">您确认要删除该条笔记吗？</p>" + "<a class=\"button btn_red popwin_confirm noteDelete_confirm\" onclick=\"TINY.box.hide();\">确认</a>" + "<a class=\"button btn_gray popwin_cancel\" onclick=\"TINY.box.hide();\">取消</a>" + "</div>" + "</div>";
			}else if (type == 8) {
				historyBlockHTML += '<div class="feedback_describe">'+describe+'<a class="feedback_open" feedbackId="'+feedbackId+'"> [ 展开<span class="pullDown"></span>]</a></div><a class="viewForm" href="formFeedback.php?ID='+formId+'">查看表单</a><div class="clearB"></div><div class="feedbackForm"></div>';
			}else if (type == 9 || type == 10) {
				historyBlockHTML += '<div class="mail_describe">'+describe+'</div><a class="mail_view" mailId="'+mailId+'" target="_blank" href="mailPreview.php?ID='+mailId+'">查看邮件</a><div class="clearB"></div>';
			}
			historyBlockHTML += "</div>" + "</div>" + "<div class=\"clearB\"></div>";
			historyBlockHTML += "</div>";
			$(document).dequeue('_historyAjax');
		});

		$(document).queue('_historyAjax',function(){
			obj.html = historyBlockHTML;
			obj.obj = $(historyBlockHTML);
			$(document).dequeue('ajaxGetHistoryFeedback');
		});
		
		$(document).dequeue('_historyAjax');
	} else {
		if(type == 8 && formId){
			formatedTime = mkHistoryDateFormat(time);
			historyBlockHTML = "<div class=\"history_feedback\" id=\"history_"+id+"\">" + "<p class=\"week\">" + formatedTime.weekCN + "</p>" + "<p class=\"day\">" + ((formatedTime.dayInMonth < 9) ? ("0" + formatedTime.dayInMonth) : formatedTime.dayInMonth) + "</p>" + "<div class=\"history_text\">" + "<div class='history_icon_feedback'></div>" + "<div style=\"margin-left:20px;\">" + "<span class=\"name\">" + name + "</span>" + "<span class=\"dowhat\">收到了一封反馈&nbsp;&nbsp;&nbsp;&nbsp;" + formatedTime.shortFormateTime + "</span><br/>";
			$(document).queue('_historyAjax',function(){
				historyBlockHTML += '<div class="feedback_describe">[该表单已被删除]<a class="feedback_open" feedbackId="'+feedbackId+'"> [ 展开<span class="pullDown"></span>]</a></div><div class="clearB"></div><div class="feedbackForm"></div>';
				historyBlockHTML += "</div>" + "</div>" + "<div class=\"clearB\"></div>";
				historyBlockHTML += "</div>";
				$(document).dequeue('_historyAjax');
			});
			$(document).queue('_historyAjax',function(){
				obj.html = historyBlockHTML;
				obj.obj = $(historyBlockHTML);
				$(document).dequeue('ajaxGetHistoryFeedback');
			});
			$(document).dequeue('_historyAjax');
		} else {
			obj = false;
		}
		
		$(document).dequeue('ajaxGetHistoryFeedback');
	}
}

/**
*	获取联系人的基本信息，并且将他们都按部就班的放置在应该放置的位置上。
*/
function getContactInfomation(){
	$.ajax({
		url:"handler/handleGetContact.php",
		type:"POST",
		data: {
			"DATA": "{\"ID\":\""+g_contatctId+"\"}"
		},
		dataType: "json", // 增加datatype就可以少做编码这一步了。
		success:function(data){
			var count = 0,
				cityShow, cityID,
				infoGroupWidth,
				groupname,
				contactInfo = {};
			function gender(s){
				if(s == "M")
					return "先生";
				else if(s == "F")
					return "女士";
				else return "　";
			}

			if(data.flag){
				// -- 对contactinfo 进行初始化
				contactInfo.name = MK_ESCAPE.tohtmlStr(data.data["name"]||'');
				contactInfo.gender = gender(MK_ESCAPE.tohtmlStr(data.data["gender"]||''));
				contactInfo.city = data.data["city"]||'';
				contactInfo.cityId = MK_ESCAPE.tohtmlStr(data.data["cityId"]||'');
				// --
				contactInfo.job = MK_ESCAPE.tohtmlStr(data.data["job"]||'');
				contactInfo.company = MK_ESCAPE.tohtmlStr(data.data["company"]||'');
				contactInfo.website = MK_ESCAPE.tohtmlStr(data.data["website"]||'');
				// contactInfo.note = MK_ESCAPE.tohtmlStr(data.data["note"]||'');
				contactInfo.note = (data.data["note"]||'').replace(/(<div>(.*?)<\/div>)/igm,function($, $1, $2){
					var ans = '';
					if($2 != '<br/>' && $2 != '<br>'){
						ans = $2+'\n';
					} else {
						ans+='\n';
					}
					return ans;
				});

				// contactInfo.note = MK_ESCAPE.tohtmlStr(contactInfo.note).replace('<br>','\n');

				contactInfo.address = MK_ESCAPE.tohtmlStr(data.data["address"]||'');


				$(".p_nameB").val(MK_ESCAPE.unescape(contactInfo.name));
				//input自适应
				$(".p_nameB").width(textWidth(contactInfo.name));
				$(".sex>span,.name_information_sex").text( contactInfo.gender ); // 性别显示于两个地方，所以都要进行修改
				if(contactInfo.city){
					cityID = contactInfo.cityId;
					cityShow = contactInfo.city.split(' ');
					cityShow = MK_ESCAPE.tohtmlStr(cityShow[cityShow.length-1]);
				} else {
					cityShow = "";
					cityID = "";
				}
				$(".information_top").show();
				//根据城市字符长度设定容器宽度
				$(".name_information_city").val(cityShow).attr({'preValue':cityShow,"id":cityID}).css('width',cityShow.length*13+10+'px').parent().css('min-width',cityShow.length*13+90+'px');

				// ---- 接下来的内容的显示

				$("#JOB .describe").html(contactInfo.job).attr('preValue',contactInfo.job);
				$("#COMPANY .describe").html(contactInfo.company).attr('preValue',contactInfo.company);
				$("#WEBSITE .describe").html(contactInfo.website).attr('preValue',contactInfo.website);

				$("#NOTE .describe").val(contactInfo.note).attr('preValue',contactInfo.note);

				
				// re-caculate-height
				

				$("#ADDRESS .describe").html(contactInfo.address).attr('preValue',contactInfo.address);

				//设定群组容器宽度
				infoGroupWidth = $('.information_top')[0].offsetWidth - $('.tag')[0].offsetWidth - $('.tag')[0].offsetLeft - $('.view_send')[0].offsetWidth + $('.infoTop_group')[0].offsetWidth - 130;

				$('.infoTop_group').css('width', infoGroupWidth+'px');
				$('.infoTop_groupList').css('width', infoGroupWidth-30+'px');
				// 群组信息的插入
				//$(".viewContact_group").empty();
				if(data.data["group"]){
					groupInfomationsArray = [];
					groupInfomationsArray[0] = [];
					groupInfomationsArray[1] = [];
					$.each(data.data["group"],function(i){
						groupId = data.data.group[i].id;
						groupInfomationsArray[0].push(data.data.group[i].value);
						groupInfomationsArray[1].push(data.data.group[i].id);
						groupArray[groupId] = 1;
					});

					$(".infoTop_groupList").mgTag({
						isRequirePic: false,
						isEditable: false,
						tagClass: "viewContact_group",
						customID: "groupid",
						defaultsValue: groupInfomationsArray,
						isNeedFloat: false
					});
				}

				// 循环的 Info
				var tmpInfo={},$newCompoment;
				for(var info in data.data.info){
					tmpInfo.tval = MK_ESCAPE.tohtmlStr(data.data["info"][info]["value"]||'');
					tmpInfo.tid = MK_ESCAPE.tohtmlStr(data.data["info"][info]["id"]||'');
					tmpInfo.ttype = MK_ESCAPE.tohtmlStr(data.data["info"][info]["type"]||'');
					$newCompoment = $(".describe[name=INFO]:first").closest('.info').clone().insertAfter($(".describe[name=INFO]:last").closest('.info'));
					$newCompoment.find(".describe").html(tmpInfo.tval).attr({"preValue":""+tmpInfo.tval,"gid":""+tmpInfo.tid});
					$newCompoment.find("p").text(MK_ESCAPE.unescape(tmpInfo.ttype)).parent(".info").find(".btn-group .btn-dropdown span").text(MK_ESCAPE.unescape(tmpInfo.ttype)).attr("preValue",tmpInfo.ttype);
					count++;
				}
				MIKE.multipleInfo['INFO'] = count;
				if (count!=0) {
					$(".describe[name=INFO]:first").parent(".info").remove(); // 删除html中的第一条假信息
				} else {
					//$(".add_viewContact:first").siblings(".describe").val("").attr({"value":"","preValue":"","gid":-1});
					$(".describe[name=INFO]:first").html("").attr({"preValue":"","gid":-1});
				}

				count = 0;
				tmpInfo={}; // 清空 tmpifno 下面的还能用呢
				for (var i in data.data.email){
					var emailList = data.data.email;
					tmpInfo.eval = MK_ESCAPE.tohtmlStr(emailList[i]['value']||'');
					tmpInfo.eid = MK_ESCAPE.tohtmlStr(emailList[i]['id']||'');
					
					$(".email:first").clone().insertAfter($('.email:last')).find(".describe").html(tmpInfo.eval).attr({"preValue":''+tmpInfo.eval,"gid":""+tmpInfo.eid});
					count++;
				}
				MIKE.multipleInfo['EMAIL'] = count;
				if(count != 0){
					$(".email:first").remove(); // 删除html中的第一条假信息
				} else {
					$(".email:first").find(".describe").html("").attr({"preValue":"","gid":-1});
				}

				count = 0;
				tmpInfo={};
				for(var im in data.data["im"]){
					tmpInfo.ival = MK_ESCAPE.tohtmlStr(data.data["im"][im]["value"]||'');
					tmpInfo.iid = MK_ESCAPE.tohtmlStr(data.data["im"][im]["id"]||'');
					tmpInfo.itype = MK_ESCAPE.tohtmlStr(data.data["im"][im]["type"]||'');
					$newCompoment = $(".describe[name=IM]:first").closest('.info').clone().insertAfter($(".describe[name=IM]:last").closest('.info'));
					$newCompoment.find(".describe").html(tmpInfo.ival).attr({"preValue":""+tmpInfo.ival,"gid":""+tmpInfo.iid});
					$newCompoment.find("p").text(MK_ESCAPE.unescape(tmpInfo.itype)).parent(".im").find(".btn-group .btn-dropdown span").text(MK_ESCAPE.unescape(tmpInfo.itype)).attr("preValue",tmpInfo.itype);
					count++;
				}
				MIKE.multipleInfo['IM'] = count;
				if(count!=0){
					$(".describe[name=IM]:first").parent(".info").remove();
				} else {
					//$(".add2_viewContact:first").siblings(".describe").val("").attr({"value":"","preValue":"","gid":-1});
					$(".describe[name=IM]:first").text("").attr({"preValue":"","gid":-1});
				}
				count = 0;
				tagArray = [];
				tagArray[0] = [];
				tagArray[1] = [];

				for(var i in data.data["tag"]){
					tagArray[0].push(data.data["tag"][i]["value"]);
					tagArray[1].push(data.data["tag"][i]["id"]);
				}
				tagFieldInitialize();
				$(".describe").unbind('keydown').bind("keydown",function (e){
					if ($(this).closest(".info").hasClass("note")) {

					}else{
						if (e.keyCode == 13) {
							e.preventDefault();
							e.stopPropagation();
							return false;
						}
					}
				});
				$('.describe').bind('keyup keydown keypress',function (e){
					e.stopPropagation();
				});
				loadingCount++;
				if(loadingCount>1){
					$(".loading").remove();
					$('.information_detail').show().removeClass('information_detail_hide');
					loadingFinish();
				}
				if($.trim($('.infoDiv_map').siblings('.describe').text()) === ''){
					$('.infoDiv_map').hide();
				}
			}else{
				$("#main .QRCode").remove();
				$(".loading").remove();
				$(".errorInfo").html("<p>"+data.error+"</p>").show();
			}
		}
	});
}

/**
*	标签部分的初始化方法.
*	初始化中定义了修改了标签之后运用的方法。
*/
function tagFieldInitialize(){
	$(".tag_all").mgTag({
		tagText: "+",
		isRequirePic: false,
		isRequireFlow: false,
		isNeedFloat: false,
		insertFinish: function(){
			//添加一条数据进入数据库
			//'{"ID":"1","FIELD":"TAG","VALUE":{"A":"ADD","N":"CODER","L":"coder"}}';
			contactHandleMethod.alterData("TAG","{\"A\":\"ADD\",\"N\":"+JSON.stringify($(".mgTagItem span:last").text())+",\"L\":"+JSON.stringify(encodeValue($(".mgTagItem span:last").text()))+"}");
			$(".input_addTag").focus();
			$(".mgTagItem span:last").attr("gid",contactHandleMethod.getOneFieldDataOn("TAG",$(".mgTagItem").index($(".mgTagItem:last"))-1));
			$(".input_addTag").focus();
		},
		deleteFinish: function(ui){
			//"TAG","VALUE":{"A":"DEL","ID":"7"}}';
			contactHandleMethod.alterData("TAG","{\"A\":\"DEL\",\"ID\":\""+ui.find("span").attr("gid")+"\"}");
		},
		defaultsValue: tagArray
	});
}

/**
*	判断按钮是否弹起，显示规定的项目
*/
function historyButtonActive(ui){
	var span = ui.find('span');
	if(!ui.hasClass("active")) {
		if(span.hasClass("history_filterBox_blog")) {
			$(".history_list .history_blog").hide();
		} else if(span.hasClass("history_filterBox_note")) {
			$(".history_list .history_note").hide();
		} else if(span.hasClass("history_filterBox_form")) {
			$(".history_list .history_feedback").hide();
		} else if(span.hasClass("history_filterBox_mail")) {
			$(".history_list .history_mail").hide();
		} else {
			$(".history_list .history_sms").hide();
		}
	} else {
		if(span.hasClass("history_filterBox_note")) {
			$(".history_list .history_note").show();
		} else if(span.hasClass("history_filterBox_blog")) {
			$(".history_list .history_blog").show();
		} else if(span.hasClass("history_filterBox_form")) {
			$(".history_list .history_feedback").show();
		} else if(span.hasClass("history_filterBox_mail")) {
			$(".history_list .history_mail").show();
		} else {
			$(".history_list .history_sms").show();
		}
	}
	$(".history_list .month").show();
	$(".monthBlock").each(function() {
		$(this).show();
		if(!$(this).children("div:visible").length) {
			$(this).hide();
		}
	});
	$(".history_list").getNiceScroll().resize();
	$(".monthBlock:visible:first .month").hide();
	$(".month_top").html($(".monthBlock:visible:first .month").html());

	if ($(".monthBlock>div:visible").length > 0) {
		$(".history_list").css("visibility", "visible");
	}else{
		$(".history_list").css("visibility", "hidden");
	}
}

/*添加笔记*/
function addNote() {
	$(".addNote_input").keyup(function(evt) {
		if($(".addNote_input").val().length != 0) {
			$(".note_submit").removeClass("btn_gray");
			$(".note_submit").addClass("btn_green");
			if(evt.keyCode == "13") {
				$(".note_submit").addClass("btn_gray");
				$(".note_submit").removeClass("btn_green");
				var noteText = $(".addNote_input").val();
				if(noteText != "") {
					contactHandleMethod.addContactNote(noteText);
					$(".addNote_input").val("");
				}
			}
		} else {
			$(".note_submit").addClass("btn_gray");
			$(".note_submit").removeClass("btn_green");
		}
	});
	$(".note_submit").click(function(e) {
		var noteText = $(".addNote_input").val();
		if(noteText != "") {
			contactHandleMethod.addContactNote(noteText);
			$(".addNote_input").val("");
		}
	});
}

/*时间轴*/
function timeLine() {
	//隐藏了最上面的一个月份
	// $(".month:eq(1)").css("display", "none");
	$(".history_list").scroll(function() {
		var monthLength = $(".month").not(":first").length;
		for(var i = 2; i <= monthLength; i++) {
			if($(".month:eq(" + i + ")").position().top > 0) {
				var monthTopItem = $(".month:eq(" + (i - 1) + ")");
				var monthhtml = monthTopItem.html();
				isChange = true;
				break;
			}
			var monthhtml = $(".month:eq(" + i + ")").html();
		}
		$(".month_top").html(monthhtml);
	});
}


//-----修改方法
/**
 *	增改联系人一条信息。编辑结束之后后台通讯进行刷新操作
 */
function alterContactInfomation(ui) {
	var value = '',
		ans = false,
		tmp = '';
	if(ui.attr("name") == "INFO" || ui.attr("name") == "IM") {
		value = "{";
		value += ((ui.attr("gid") == -1) ? "\"A\":\"ADD\", \"T\":\"" + ui.siblings(".btn-group").find("a span").attr("name") + "\" , " : "\"A\":\"ALTER\",\"ID\":\"" + ui.attr("gid") + "\", ");
		value += " \"V\":" + JSON.stringify($.trim(ui.text())) + "";
		value += "}";
		contactHandleMethod.alterData(ui.attr("name"), value, true);
		ans = contactHandleMethod.getOneFieldDataOn(ui.attr("name"), $("[name='" + ui.attr("name") + "']").index(ui), true);
		ui.attr("gid", ans);
	} else if(ui.attr('name') == 'EMAIL') {
		value = "{";
		value += ((ui.attr("gid") == -1) ? "\"A\":\"ADD\", \"T\":\"4\" , " : "\"A\":\"ALTER\",\"ID\":\"" + ui.attr("gid") + "\", ");
		value += " \"V\":" + JSON.stringify($.trim(ui.text())) + "";
		value += "}";
		contactHandleMethod.alterData('INFO', value, true);
		ans = contactHandleMethod.getOneFieldDataOn(ui.attr("name"), $("[name='" + ui.attr("name") + "']").index(ui), true);
		ui.attr("gid", ans);
	} else {
		if(ui.parent().hasClass('note')){
			tmp = ui.val();
		} else {
			tmp = ui.text();
		}
		contactHandleMethod.alterData(ui.parent().attr("id") , JSON.stringify(tmp));
	}
}

//修改联系人的姓名信息。
/**
*	联系人名字修改的handler，使用之后会更改数据库中的信息。
*/
function alterContactName(ui) {
	contactHandleMethod.alterData("NAME", "{\"N\":" + JSON.stringify(ui.val()) + ",\"L\":" + JSON.stringify(encodeNameValue(ui.val())) + "}");
}

/*联系人信息的编辑*/
function infoEdit() {
	$(".info .btn-group").hide();
	$(".info_edit").hide();
	$(".infoTop_edit").hide();
	$(".delete_viewContact").hide();
	$(".add_viewContact").hide();
	$(".add2_viewContact").hide();
	$(".infoSelect_edit").hide();
	$(".infoDiv_edit").hide();
//	$(".infoDiv_map").hide();
	$(".info").live("mouseover",function() {
		if ($(this).find("input").hasClass("input_border") || $(this).find(".describe").hasClass("input_border") || $(this).find(".info_select").is(":hidden")) {
		}else{
			$(this).find(".info_edit").show();
			$(this).find(".infoTop_edit").show();
			$(this).find(".infoDiv_edit").show();
			$(this).siblings(".add_viewContact").show();
			$(this).siblings(".delete_viewContact").show();
//			$(this).find(".infoDiv_map").show();
		}
	});

	$(".info").live("mouseout",function() {
		$(this).find(".info_edit").hide();
		$(this).find(".infoTop_edit").hide();
		$(this).find(".infoDiv_edit").hide();
		$(this).siblings(".add_viewContact").hide();
		$(this).siblings(".delete_viewContact").hide();
//		$(this).find(".infoDiv_map").hide();
	});

	editSex();
	editName();
	editCity();
	editNormal();
	editDiv();
	editSelect();
	showMap();

	//群组管理
	//添加群组——已有分组
	$(".group_operate li").live("click",function(){
		var groupname = $(this).text();
		var groupid = $(this).find(".group_checkbox input").attr("id");
		if($(this).find(".group_checkbox").hasClass("checked")){
			$(this).find(".group_checkbox").removeClass("checked").find(':checkbox').attr("checked", false);
			removeFromGroup(groupid,groupname);
		}else{
			$(this).find(".group_checkbox").addClass("checked").find(':checkbox').attr("checked", "checked");
			attachToGroup(groupid,groupname);
		}
	});
	//添加群组——新建分组
	$(".add_creatGroup span").live("click",function(){
		$(this).closest(".add_creatGroup").empty().append("<input class='input input_creatGroup' maxlength='8' /><a class='btn btn-primary btn_creatGroup'>创建</a><a class='btn_cancleCreatGroup'>取消</a>").find(".input").focus();
		inputval();
	});
	$(".btn_cancleCreatGroup").live("click",function(){
		$(this).closest(".add_creatGroup").empty().append("<span>新建群组</span>");
	});
	$(".btn_creatGroup").live("click",function(){
		var _self = $(this).siblings(".input_creatGroup");
		var groupname = $(this).siblings(".input_creatGroup").val();
		if(groupname !=""){
			addGroup(_self,groupname);
		}
	});
}

//获取文本宽度
var textWidth = function(text) {
		var sensor = $('<span>' + text + '</span>').css({
			"display": "none",
			"font-size": "49px",
			"font-weight": "bold"
		});
		$('body').append(sensor);
		var width = sensor.width();
		sensor.remove();
		return width; //为保证效果，宽度要比文本宽度大些
	};


//--- 页面加载结束时使用----
$(document).ready(function() {
	//禁止回车事件
	$(".describe").bind("keydown",function (e){
		if ($(this).closest(".info").hasClass("note")) {

		}else{
			if (e.keyCode == 13) {
				e.preventDefault();
				e.stopPropagation();
				return false;
			}
		}
	});

	$('.describe').die('paste').live('paste',function(){
		var $this = $(this),
			tmpVal = '';
		setTimeout(function(){
			tmpVal = $this.html().replace(/(<([^>]+)>)/g, "");
			$this.html($.trim(tmpVal));
		}, 200);
	});

	$('.describe').bind('keyup keydown keypress',function (e){
		e.stopPropagation();
	});

	$('.describe').bind('keyup keydown input',function(){
		if ($(this).closest(".info").hasClass("note")) {
			loadingFinish();
		}
	});
	// 载入动画
	//$("#main").children().show();
	//$("#main").mgLoading();
	$("<div class='loading' style=\"text-align:center;position:absolute;top:40%;width:100%;color:#949494;z-index:10;\"><span style=\"background:url('images/loading.gif') no-repeat;padding-left:30px\">正在载入联系人数据...</span></div>").insertBefore("#main");
	//$(".information_detail,.information_top,.history").mgLoading();
	// 联系人信息的编辑
	infoEdit();
	// 添加和删除
	add_viewContact();
	// 时间轴滚动带来的变化
	timeLine();
	// 获取联系人的信息/历史记录
	getContactInfomation();
	contactHandleMethod.getHistoryInfo(0);

	// 修改联系人姓名
	//alterNameMethod();
	// 笔记的添加方法
	addNote();
	// 标签初始化
	tagFieldInitialize();

	//滚动条
	$(".information_detail").niceScroll({
		cursorborder: "",
		cursorcolor: "#999",
		zindex: "10"
	});
	$(".history_list").niceScroll({
		cursorborder: "",
		cursorcolor: "#999"
	});
	$(".group_operate").niceScroll({
		cursorborder: "",
		cursorcolor: "#999"
	});
	// 发送邮件，发送短信Hover效果
	$(".btn_send").mouseover(function() {
		$(this).find(".img_send").stop().animate({
			"background-position-y": "-40px"
		}, 200);
		$(this).find("span").css("text-decoration", "underline");
	});
	$(".btn_send").mouseout(function() {
		$(this).find(".img_send").stop().animate({
			"background-position-y": "0px"
		}, 200);
		$(this).find("span").css("text-decoration", "none");
	});

	//弹窗——coming soon
	$(".btn_send").click(function(){
		TINY.box.show({
			html: $(".popwin_coming").html(),
			width: 414,
			height: 150,
			fixed: false,
			maskid: 'blackmask',
			maskopacity: 40
		});
	});
	//弹窗——删除联系人
	$(".deleteContact").click(function(){
		TINY.box.show({
			html: $(".popwin_deleteContact").html(),
			width: 414,
			height: 224,
			fixed: false,
			maskid: 'blackmask',
			maskopacity: 40,
			openjs: function() {
				$(".deleteContact_confirm").click(function() {
					TINY.box.hide();
					removeContacts();
				});
			}
		});
	});

	//弹窗——二维码
	$(".QRCode").click(function(){
		TINY.box.show({
			html: $("#popwin_QRcode").html(),
			width: 414,
			height: 302,
			fixed: false,
			maskid: 'blackmask',
			maskopacity: 40,
			boxid: 'popwin_QRcode_box',
			openjs: function(){
				var _popwin = $("#popwin_QRcode_box");
				$.ajax({
					url: "handler/handleGetContactQR.php",
					type: "post",
					data: {
						DATA:'{"CTID":"'+g_contatctId+'"}'
					},
					dataType:"json",
					beforeSend: function(){
						_popwin.find(".popwin_content").empty().append('<div class="QRCodeLoading" style="text-align:center;color:#949494;margin-top:80px;"><span style="background:url(images/loading.gif) no-repeat;padding-left:30px">正在生成二维码图片，请稍候...</span></div>');
					},
					success: function(data) {
						if (data.flag) {
							_popwin.find(".popwin_content").empty().append('扫一扫下面的二维码就可以把联系人保存到手机里啦！<div class="img_QRCode"><img style="width:72px;height:51px;vertical-align: top;margin-top: 75px;" src="./images/icon/contactQR_weixin.png" /><img src="'+data.url.replace(/\\/igm,'\/')+'" /><img style="width:72px;height:51px;vertical-align: top;margin-top: 75px;" src="./images/icon/contactQR_vcard.png" /></div>');
						}else{
							_popwin.find(".popwin_content").empty().append('<div class="QRCodeLoadFail" style="text-align:center;color:#949494;margin-top:80px;"><span>呃，二维码图片生成失败了...</span></div>');
						}
					}
				});
			}
		});
	});
	$("#cover").live("click",function(){
		$("#cover").remove();
		$(".guide_contactQR").remove();
	});
	$(".guide_contactQR").live("click",function(){
		$("#cover").remove();
		$(".guide_contactQR").remove();
	});



	// 按钮按下弹起样式
	$(".history_filterBox").click(function() {
		if($(this).hasClass("active")) {
			$(this).removeClass("active");
		}else{
			$(this).addClass("active");
		}
		historyButtonActive($(this));
	});

	// 笔记中的按键按下时候触发的事件
	$(".history_note").live('mouseover mouseout', function(event) {
		if(event.type == 'mouseover') {
			$(this).find(".note_edit").css("display", "inline-block");
			$(this).find(".note_delete").css("display", "inline-block");
		} else {
			$(this).find(".note_edit").css("display", "none");
			$(this).find(".note_delete").css("display", "none");
		}
	});
	$(".note_edit").live("click", function() {
		var editBtn = $(".note_edit");
		editBtn.siblings(".note_edit_input").attr("contenteditable","true").focus().addClass("input_border");
		$(".note_edit_input").keydown(function(event) {
			if(event.keyCode == "13") { //keyCode=13是回车键
				$(this).removeAttr("contenteditable").blur().removeClass("input_border");
				var alterNoteID = editBtn.attr("noteid");
				contactHandleMethod.alterContactNote(alterNoteID, $(this).text());
			}
		}).bind('keyup keydown keypress', function (evt){
			evt.stopPropagation();
		});
		$("body").click(function(e) {
			if($(e.target).is(".input_border")){
				return false;
			} else {
				if($(".input_border").closest(".deletMust").length > 0){
					var alterNoteID = editBtn.attr("noteid");
					contactHandleMethod.alterContactNote(alterNoteID, $(".input_border").text());
					$(".note_edit_input").removeAttr("contenteditable").blur().removeClass("input_border");
					$("body").unbind("click");
				}
			}
		});
	});

	//删除笔记时出现的弹窗
	$(".note_delete").live("click",function() {
		var deleteNote = $(this);
		TINY.box.show({
			html: $("#popwin_noteDelete").html(),
			width: 414,
			height: 230,
			fixed: false,
			maskid: 'blackmask',
			maskopacity: 40,
			openjs: function() {
				//
				deleteContactNoteID = deleteNote.attr("noteid");
				//delet = deletClick.closest(".deletMust");				
			}
		});
	});
	$(".noteDelete_confirm").live("click", function() {
		contactHandleMethod.removeContactNote(deleteContactNoteID);
	});

	// 表单反馈的展开收起——————————————————————————————————————————————————这里是反馈的展开收起
	$(".history_feedback").live('mouseover mouseout', function(event) {
		if(event.type == 'mouseover') {
			$(this).find(".viewForm").css("display", "inline-block");
		} else {
			$(this).find(".viewForm").css("display", "none");
		}
	});
	$(".feedback_open").live("click", function() {
		var _selfBtn = $(this);
		var frfbId  = _selfBtn.attr("feedbackId");
		if (_selfBtn.hasClass("open")) {//收起
			_selfBtn.removeClass("open").html(" [ 展开<span class='pullDown'></span>]");
			_selfBtn.closest(".feedback_describe").siblings(".feedbackForm").hide().empty();
		}else{//展开 
			$(this).addClass("open").html(" [ 收起<span class='pullUp'></span>]");
			$.ajax({
				url: 'handler/handleGetFormFeedback.php',
				type: 'POST',
				dataType: 'JSON',
				data: {
					"DATA": '{"FRFBID":"'+frfbId+'"}'
				},
				success: function(data) {
					if(data.flag) {
						var $feedbackField = _selfBtn.closest(".feedback_describe").siblings(".feedbackForm");
						$feedbackField.formCreator({
							jsonObject: JSON.parse(data.data[0].template)
						});

						$feedbackField.show();
						
						function renderPayList() {
							var cartHTML = '<li class="cart" id="mfcart" style="display: none;"><div class="basic_info"></div><div class="payment_info"></div><ul class="productslist"></ul><div class="totalprice"><span class="errorinfo"></span><span class="priceshow"></span></div></li>',
								$cart,
								tmpHTML = '',
								paytotal = 0,
								tmpPrice,
								$_formComponent = _selfBtn.siblings(".feedbackForm").find('.form_component'),
								$_formtitle = _selfBtn.siblings(".feedbackForm").find('.form_title');
							if($_formComponent.find('.locked[name="id_shopping"]').length > 0){
								// continue;
								$_formComponent.append(cartHTML);

								$cart = $('#mfcart');

								$cart.find('.basic_info').html('<span>'+(($_formtitle.data('sn'))?'订单号：'+$_formtitle.data('sn'):'')+'</span>');

								$_formComponent.find('.locked[name="id_shopping"]').each(function(){
									var currentShoppingVal = $(this).data('shoppinginfo');
									$.each(currentShoppingVal, function(k, v){
										if(v.quantity){
											tmpPrice = (v.quantity* (v.price*100));
											tmpHTML += '<li class="productitem"><span class="name">' + v.name + '</span><span class="num">' + v.quantity + '</span><span class="price">￥' + (tmpPrice / 100).toFixed(2) + '</span></li>';
											paytotal += tmpPrice;
										}
									});
								});

								if(tmpHTML){
									$cart.show();

									$cart.find('.productslist').append(tmpHTML);
									$cart.find(".priceshow").text('￥'+(paytotal/100).toFixed(2));
								}

							}
						}

						renderPayList();

						$(".history_list").getNiceScroll().resize();
					}
				}
			});
		}
	});

	// 邮件的查看
	$(".history_mail").live('mouseover mouseout', function(event) {
		if(event.type == 'mouseover') {
			$(this).find(".mail_view").css("display", "inline-block");
		} else {
			$(this).find(".mail_view").css("display", "none");
		}
	});

	// 城市的自动完成方法
	$(".name_information_city").autocomplete({
		minLength: 1,
		source: function( request, response ){
			var cityArray = [];
				cityArray[0] = [];
				cityArray[1] = [];
			$.ajax({
				url: "handler/handleMGGetAA.php",
				type: "post",
				data: {
					DATA:"{\"TYPING\":"+JSON.stringify($(".name_information_city").val())+"}"
				},
				success: function(data) {
					var evalJSON = JSON.parse(data);
					if(evalJSON.flag){
						for(var i in evalJSON.data){
							cityArray[0].push(evalJSON.data[i].mgAATTextCNPrompt);
							cityArray[1].push(evalJSON.data[i].mgAATId);
						}
						$(".name_information_city").attr("id",cityArray[1][0]);
						response($.map(cityArray[0],function(n,i){
							return{
								'label':cityArray[0][i],
								'value':cityArray[0][i],
								'id': cityArray[1][i]
							}
						}));
					} else{
						response(new Array("未发现匹配项"));
					}
				}
			});
		},
		select: function(event,ui){
			$(".name_information_city").attr("id",ui.item.id);
		},
		focus: function( event, ui ) {
			$(".name_information_city").attr("id",ui.item.id);
		}
	}).click(function(){
		tmpValue = $(this).attr("id");
	});

	// 修改标签
	$("#scrollbar .info .info_select").live("mouseover",function(){
		tmpValue = $(this).text();
	});
});

//手机、qq的添加和删除
function add_viewContact() {
	// var i = 0;
	// var j = 0;
	
	// init multipleInfo 

	//手机/qq/email的删除添加
	// 2013-07-24 重构，去除了冗余的方法，改进了判断是否要显示不一样的联系方法的地方 去除了判断变量，改用对象存放各个量的大小，便于扩展
	$(".delete_viewContact").live("click", function() {
		var inputName = $(this).parent(".info").find(".describe").attr("name"),
			multipleInfo = MIKE.multipleInfo,
			name;
		if(multipleInfo[inputName] <= 1 || !multipleInfo.hasOwnProperty(inputName)){
			$(this).parent(".info").find("[name='" + inputName + "']").text("").attr("preValue","");
			// --alter
			if(multipleInfo[inputName]>0){
				multipleInfo[inputName]--;
			}
			name = $(this).siblings(".describe").attr("name");
			if(name === 'EMAIL'){
				name = 'INFO';
			}
			// alterContactInfomation($(this).siblings(".describe"));
			if($(this).siblings(".describe").attr("gid") !== -1){
				contactHandleMethod.alterData(name,"{\"A\":\"DEL\",\"ID\":\""+$(this).siblings(".describe").attr("gid")+"\"}");
				$(this).siblings(".describe").attr("gid",-1);
			}
		} else {
			multipleInfo[inputName]--;
			$(this).parent(".info").remove();
			// --remove
			name = $(this).siblings(".describe").attr("name");
			if(name === 'EMAIL'){
				name = 'INFO';
			}
			contactHandleMethod.alterData(name,"{\"A\":\"DEL\",\"ID\":\""+$(this).siblings(".describe").attr("gid")+"\"}");
		}
		// 留一个
		$(".information_detail").getNiceScroll().resize();
	});


	$(".add_viewContact").live("click", function() {
		var inputName = $(this).parent(".info").find(".describe").attr("name"),
			infoText,
			multipleInfo = MIKE.multipleInfo,
			$compoment,$newCompoment,
			infoLength = 0;

		if(!multipleInfo.hasOwnProperty(inputName)){
			multipleInfo[inputName] = 1;
		}

		$compoment = $(this).parent(".info").find(".btn-group");

//		if(multipleInfo[inputName] < 5){
//			if($compoment.find(".dropdown-menu li:eq(" + (((multipleInfo[inputName]-1)>0)?(multipleInfo[inputName]-1):0) + ")").length > 0){
//				infoLength = multipleInfo[inputName]-1;
//			}
//		}


		if(!$(this).closest(".info").find('.describe').attr('gid') || $(this).closest(".info").find('.describe').attr('gid')==-1 || $(this).closest(".info").find('.describe').text() == ''){
			$(this).closest(".info").find(".infoSelect_edit,.infoDiv_edit").trigger("click");
			if(!$(this).closest(".info").find('.describe').attr('gid')){
				$(this).closest(".info").find('.describe').attr({"preValue":"","gid":"-1"});
			}
		} else {
			if($compoment.length > 0 ){
//				infoText = $compoment.find(".dropdown-menu li:eq(" + (infoLength+1) + ")").text();
//				$compoment.find(".btn-dropdown span").attr("name", infoLength);
				infoText = $compoment.find(".dropdown-menu li:eq(0)").text();
				$compoment.find(".btn-dropdown span").attr("name", (1));
				addNewInfo($(this), infoText, "", -1);
			} else {
				if($(this).parent('.info').length > 0){
					$(this).parent('.info').clone().show().insertAfter($(this).parent('.info')).find(".describe").show().html("").attr({"preValue":"","gid":"-1"});
				}
			}
			$(this).closest(".info").next().find(".infoSelect_edit,.infoDiv_edit").trigger("click");
		}
//		multipleInfo[inputName]++;


		$(".describe").unbind('keydown').bind("keydown",function (e){
			if ($(this).closest(".info").hasClass("note")) {

			}else{
				if (e.keyCode == 13) {
					e.preventDefault();
					e.stopPropagation();
					return false;
				}
			}
		});
		$('.describe').bind('keyup keydown keypress',function (e){
			e.stopPropagation();
		});
		
		$(".information_detail").getNiceScroll().resize();
	});
}

//群组列表——群组管理下拉菜单的列表
function getGroup(){
	$.ajax({
		url: 'handler/handleGetAllGroup.php',
		type: 'POST',
		dataType:'JSON',		
		success: function(data){	
			var json = eval(data);
			if (json.flag){	
				$(".group_operate li:not(:last,:first)").remove();	
				for(var i in json.data){
					var groupname = json.data[i].name;
					var groupid = json.data[i].id;	
					if (!groupArray[groupid]) {
						$(".group_operate li:first").after("<li><span class='group_checkbox'><input type='checkbox' id='"+groupid+"' /></span>"+groupname+"</li>");
					}else{
						$(".group_operate li:first").after("<li><span class='group_checkbox checked'><input type='checkbox' checked='checked' id='"+groupid+"' /></span>"+groupname+"</li>");
					}
				}
			}
		}
	});
}

//读取群组信息
function rebuildGroup(){
	$.ajax({
		url: "handler/handleGetContact.php",
		type: "POST",
		data: {
			"DATA": "{\"ID\":\""+g_contatctId+"\"}"
		},
		dataType: "json", // 增加datatype就可以少做编码这一步了。
		success:function(data){
			if(data.flag){
				var groupInfomationsArray = [];
				groupInfomationsArray[0] = [];
				groupInfomationsArray[1] = [];
				if(data.data["group"]){
					$.each(data.data["group"],function(i){
						var groupId = data.data.group[i].id;
						groupInfomationsArray[0].push(data.data.group[i].value);
						groupInfomationsArray[1].push(data.data.group[i].id);
						groupArray[groupId] = 1;
						//$("<p class=\"viewContact_group\" atgroupid='"+data.data.group[i].id+"'>"+data.data.group[i].value+"</p>").insertBefore(".dropdown_group");
					});
				}
				$(".infoTop_groupList").mgTag({
					isRequirePic: false,
					isEditable: false,
					tagClass: "viewContact_group",
					customID: "groupid",
					defaultsValue: groupInfomationsArray,
					isNeedFloat: false
				});

			}
		}
	});
}

//添加联系人到群组
function attachToGroup(groupid,groupname){
	$.ajax({
		url: 'handler/handleAttachToGroup.php',
		type: 'POST',
		dataType:'JSON',
		data: {
			"DATA":'{"GRID":"'+groupid+'","CTIDS":"'+g_contatctId+'"}'
		},		
		success: function(data){	
			if (data.flag){
	           		groupArray[groupid] = 1;
	           		rebuildGroup();
	           		contactHandleMethod.getHistoryInfo(0);
	           		//$("<p class=\"viewContact_group\" atgroupid='"+groupid+"'>"+groupname+"</p>").insertBefore(".dropdown_group");
           		}
		}	
	});
}
//添加联系人到新的群组
function addGroup(_self,groupname){
	$.ajax({
		url: 'handler/handleAddGroup.php',
		type: 'POST',
		dataType:'JSON',
		data: {
			"DATA": '{"NAME":"'+groupname+'"}'
		},
		success: function(data){
			var json = eval(data);
			if (json.flag){	
				_self.closest(".add_creatGroup").empty().append("<span>新建群组</span>");
				getGroupLast(data.data.id);
		   	}
		}	
	});
}
//群组列表（保存到群组时）——把联系人添加到新建的群组
function getGroupLast(newgroupid){
	$.ajax({
		url: 'handler/handleGetAllGroup.php',
		type: 'POST',
		dataType:'JSON',		
		success: function(data){	
			var json = eval(data);
			if (json.flag){	
				for(var i in json.data){
					var groupname = json.data[i].name;
					var groupid = json.data[i].id;	
					if ( groupid == newgroupid ) {
						$(".group_operate li:last").before("<li><span class='group_checkbox checked'><input type='checkbox' checked='checked'  id='"+groupid+"' /></span>"+groupname+"</li>");
						attachToGroup(groupid,groupname);
					}
				}	
           	}
		}	
	});
}

//把联系人移出群组
function removeFromGroup(groupid,groupname){
	$.ajax({
		url: 'handler/handleRemoveFromGroup.php',
		type: 'POST',
		dataType:'JSON',
		data:{
		"DATA": '{"GRID":"'+groupid+'","CTIDS":"'+g_contatctId+'"}'
		},
		success: function(data){	
			var json = eval(data);
			if (json.flag){
				groupArray[groupid] = 0;
				// $(".viewContact_group").each(function(){
				// 	if ($(this).attr("atgroupid") == groupid) {
				// 		$(this).remove();
				// 	}
				// });
				rebuildGroup();
				contactHandleMethod.getHistoryInfo(0);
		  	};
		}	
	});
}

//删除联系人
function removeContacts(){
	$.ajax({
		url: "handler/handleRemoveContacts.php",
		type: "post",
		dataType: "json",
		data: {
			"DATA": '{"CTIDS":"'+g_contatctId+'"}'
		},
		success: function(data){
			if (data.flag) {	
				// if (getUrlParam("FROM") == "Group") {
				// 	location.href = "group.php?ID="+getUrlParam('groupId');
				// }else{
					window.close();
				//}
			}
		}
	});
}

//修改姓名
function editName(){
	var nameval_old;
	//编辑姓名——点击编辑
	$(".infoName_edit").live("click",function(){
		$(".infoSex_cancel").trigger("click");
		$(".infoCity_cancel").trigger("click");
		nameval_old = $(this).parent(".edit_infoName").siblings(".p_nameB").val();
		//编辑姓名时的input
		$(this).parent(".edit_infoName").siblings(".p_nameB").removeAttr("readonly").focus().addClass("input_border")
		.after("<img class='infoName_confrim' src='images/icon/info_confrim.png' /><img class='infoName_cancel' src='images/icon/info_cancel.png' />");
		$(this).hide();
	});
	//提交修改
	$(".infoName_confrim").live("click",function(){
		if ( nameval_old!=$(".p_nameB").val() ) {
			// 修改名字的方法
			alterContactName($(this).siblings(".p_nameB"));
		};
		$(this).siblings(".p_nameB").removeClass("input_border").attr("readonly","true");
		$(".p_nameB").width(textWidth($(".p_nameB").val()));
		$(this).siblings(".infoName_cancel").remove();		
		$(this).remove();

		//设定群组容器宽度
		infoGroupWidth = $('.information_top')[0].offsetWidth - textWidth($(".p_nameB").val()) - $('.view_send')[0].offsetWidth - 400;
		$('.infoTop_group').css('width', infoGroupWidth+'px');
		$('.infoTop_groupList').css('width', infoGroupWidth-30+'px');		
	});
	//取消修改
	$(".infoName_cancel").live("click",function(){
		$(this).siblings("input").val(nameval_old).removeClass("input_border").attr("readonly","true");
		$(this).siblings(".infoName_confrim").remove();
		$(this).remove();
	});
}
//修改性别——点击编辑
function editSex(){
	var sexval_old;
	$(".infoSex_edit").live("click",function(){
		$(".infoName_cancel").trigger("click");
		$(".infoCity_cancel").trigger("click");
		sexval_old = $(this).siblings(".name_information_sex").text();
		$(this).parent(".infoTop_sex").append("<img class='infoSex_cancel' src='images/icon/info_cancel.png' /><img class='infoSex_confrim mgl5' src='images/icon/info_confrim.png' />");
		$(this).siblings(".info_select").hide();
		$(this).siblings(".btn-group").show();
		$(this).hide();	
	});
	//提交修改
	$(".infoSex_confrim").live("click",function(){
		if ( sexval_old != $(".name_information_sex").text() ) {
			// 对于性别的修改方法
			contactHandleMethod.tmpValue = $(".name_information_sex").text();
			if(tmpValue != $(this).siblings(".btn-group").find(".btn-dropdown span").text())
				contactHandleMethod.alterData("GENDER",( ($(this).siblings(".btn-group").find(".btn-dropdown span").attr("name")==1)?"\"M\"":"\"F\"" ));
		};	
		$(this).siblings(".btn-group").hide();
		$(this).siblings(".info_select").show();
		$(this).siblings(".infoSex_cancel").remove();
		$(this).remove();
	});
	//取消修改
	$(".infoSex_cancel").live("click",function(){
		$(this).siblings(".btn-group").hide();
		$(this).siblings(".btn-group").find(".sex span").text(sexval_old);
		$(this).siblings(".info_select").show().text(sexval_old);
		$(this).siblings(".infoSex_confrim").remove();
		$(this).remove();
	});
}
function editCity(){
	//修改城市——点击编辑
	$(".infoCity_edit").live("click",function(){
		$(".infoName_cancel").trigger("click");
		$(".infoSex_cancel").trigger("click");
		$(this).siblings("input").css('width','90px').removeAttr("readonly").focus().addClass("input_border")
		.after("<img class='infoCity_cancel' src='images/icon/info_cancel.png' /><img class='infoCity_confrim' src='images/icon/info_confrim.png' />").parent().css('min-width','160px');
		$(this).hide();	
	});
	//修改城市——提交修改
	$(".infoCity_confrim").live("click",function(){
		$(this).siblings(".infoCity_cancel").remove();
		
		var _cityInput = $(this).siblings("input");
		var newCityValue = _cityInput.val();
		while (newCityValue.indexOf(" ") > 0) {
			newCityValue = newCityValue.split(" ")[1];
		}
		
		if (newCityValue !== _cityInput.attr("preValue")){
			contactHandleMethod.alterData("CITY","\""+_cityInput.attr("id")+"\"");
		}
		_cityInput.val(newCityValue).attr("preValue",newCityValue);
		_cityInput.css('width',newCityValue.length*13+'px').parent().css('min-width',newCityValue.length*13+70+'px');
		_cityInput.removeClass("input_border").attr("readonly","true");

		$(this).remove();
	});
	//修改城市——取消修改
	$(".infoCity_cancel").live("click",function(){
		var _cityInput = $(this).siblings("input");
		var val_old = _cityInput.attr("preValue");

		_cityInput.val(val_old).removeClass("input_border").attr("readonly","true");	

		$(this).siblings(".infoCity_confrim").remove();
		$(this).remove();

		_cityInput.css('width',val_old.length*13+'px').parent().css('min-width',val_old.length*13+70+'px');	
	});
}
function editNormal(){
	//左侧列表项的编辑——点击编辑
	$(".info_edit").live("click",function(){
		//input变为可编辑的
		$(this).siblings("input").removeAttr("readonly").focus().addClass("input_border")
		.after("<img class='info_cancel' src='images/icon/info_cancel.png' /><img class='info_confrim' src='images/icon/info_confrim.png' />");
		$ (this).hide();
	}); 
	//提交修改
	$(".info_confrim").live("click",function(){
		var newVal = $(this).siblings("input").val();
		if ( newVal!=$(this).siblings("input").attr("preValue") ) {
			// 修改文字部分的方法
			alterContactInfomation($(this).parent().find(".describe"));
		};		
		$(this).siblings("input").attr("preValue",$(this).siblings("input").val());
		$(this).siblings("input").removeClass("input_border").attr("readonly","true");
		$(this).siblings(".info_cancel").remove();		
		$(this).remove();			
	});
	//enterConfrim();
	//取消修改
	$(".info_cancel").live("click",function(){
		var val_old = $(this).siblings("input").attr("preValue");
		$(this).siblings("input").val(val_old).removeClass("input_border").attr("readonly","true");
		$(this).siblings(".info_confrim").remove();
		$(this).remove();		
	});
}
function editDiv(){
	//可换行项的编辑——点击编辑
	$(".infoDiv_edit").live("click",function(){
		var $describe = $(this).siblings(".describe");
		if($describe.is('textarea')){
			$describe.removeAttr('readonly').focus().addClass('input_border').after("<img class='infoAddress_cancel' src='images/icon/info_cancel.png' /><img class='infoAddress_confrim' src='images/icon/info_confrim.png' />");
			$(this).hide();
			$(this).siblings(".add_viewContact").hide();
			$(this).siblings(".delete_viewContact").hide();
		} else {
			$describe.attr("contenteditable","true").focus().addClass("input_border")
			.after("<img class='infoAddress_cancel' src='images/icon/info_cancel.png' /><img class='infoAddress_confrim' src='images/icon/info_confrim.png' />");
			$(this).hide();
			$(this).siblings(".add_viewContact").hide();
			$(this).siblings(".delete_viewContact").hide();
		}

	});
	$('.info.address,.info.note,.info.company,.info.website,.info.job').bind('keyup keydown keypress',function (e){
		e.stopPropagation();
	});

	//修改可换行项--提交修改
	$(".infoAddress_confrim").live("click",function(){
		var $describe = $(this).siblings(".describe");
			newVal = $describe.text();
		if (newVal != $describe.attr("preValue") || $describe.attr("preValue") === '') {
			// 修改文字部分的方法
			if($describe.attr('name') == 'EMAIL'){
				if($.trim($describe.text()) == ''){
					if(MIKE.multipleInfo['EMAIL'] >= 1){
						$(this).closest(".email").remove();
					} else {
						$describe.html("");
					}
					contactHandleMethod.alterData('INFO',"{\"A\":\"DEL\",\"ID\":\""+$(this).siblings(".describe").attr("gid")+"\"}");
				} else {
					MIKE.multipleInfo['EMAIL']++;
					alterContactInfomation($describe);	
				}
			} else {
				alterContactInfomation($describe);	
			}
		}
		if($(this).closest('.info').hasClass('note')){
			$(this).siblings(".describe").attr("preValue",$(this).siblings(".describe").html());
		} else {
			$(this).siblings(".describe").attr("preValue",$(this).siblings(".describe").html());
		}
		$(this).siblings(".describe").removeClass("input_border").removeAttr("contenteditable");
		$(this).siblings(".infoAddress_cancel").remove();
		$(this).remove();
	});
	//enterConfrim();
	$('.address').on('click', '.infoAddress_confrim', function(){
		if(!MIKE.map) {
			MIKE.map = new BMap.Map("map_container");
		}
		var map = MIKE.map,
			myGeo = new BMap.Geocoder(),
			mapMarker = null;

		var position = $(this).siblings('.describe').text();
		if($.trim(position) !== ''){
			$('.infoDiv_map').show();
		} else {
			$('.infoDiv_map').hide();
		}

		if($('#map_container').height() > 0 && position){
			
			$('.unknow-place').hide();
			myGeo.getPoint(position, function(point){
				if (point) {
					map.centerAndZoom(point, 16);
					if(!mapMarker){
						mapMarker = new BMap.Marker(point);
					} else {
						mapMarker.setPosition(point);
					}

					map.addOverlay(mapMarker);
					mapMarker.addEventListener("click", function(){
						this.openInfoWindow(infoWindow);
					});
				} else {
					$('.unknow-place').show();
				}
			});
		} else {
			$('.unknow-place').hide();
			$('#map_container').css({
				'border': 0,
				'margin-bottom': 0
			}).animate({
				'height': 0
			},200);
		}
	});
	//修改可换行项--取消修改
	$(".infoAddress_cancel").live("click",function(){
		var val_old = $(this).siblings(".describe").attr("preValue") || '',
			attrName = $(this).siblings('.describe').attr('name');
		if(MIKE.multipleInfo.hasOwnProperty(attrName)){
			if(MIKE.multipleInfo[attrName] >= 1 && $(this).siblings(".describe").html() == '' ){
				$(this).closest('.info').remove();
			}
		}
		$(this).siblings(".describe").html(val_old).removeClass("input_border").removeAttr("contenteditable");
		$(this).siblings(".infoAddress_confrim").remove();
		$(this).remove();		
	});
}
function editSelect(){
	//编辑手机选项及微博选项
	$(".infoSelect,.email").live("mouseover",function() {
		if ($(this).find(".describe").hasClass("input_border")) {
		}else{
			$(this).find(".infoSelect_edit").show();
			$(this).find(".delete_viewContact").show();
			$(this).find(".add_viewContact").show();
			// $(this).find(".add2_viewContact").show();					
		}		
	});
	$(".infoSelect,.email").live("mouseout",function() {
		$(this).find(".infoSelect_edit").hide();
		$(this).find(".delete_viewContact").hide();
		$(this).find(".add_viewContact").hide();
		// $(this).find(".add2_viewContact").hide();	
	});

	//手机选项及微博选项——点击编辑
	$(".infoSelect_edit").live("click",function(){			
		//input变为可编辑的
		$(this).siblings(".describe").attr("contenteditable","true").focus().addClass("input_border")
		.after("<img class='infoSelect_cancel' src='images/icon/info_cancel.png' /><img class='infoSelect_confrim' src='images/icon/info_confrim.png' />");
		//电话、QQ等选项有下拉框
		$(this).parent(".info").find(".info_select").hide();
		$(this).parent(".info").find(".btn-group").show();
		$(this).siblings(".add_viewContact").hide();
		$(this).siblings(".delete_viewContact").hide();	
		$(this).hide();		
	});
	//手机选项及微博选项提交修改
	$(".infoSelect_confrim").live("click",function(){
		var $describe = $(this).siblings(".describe"),
			inputName =  $describe.attr("name"),
			describe_text = $describe.text(),
			describe_preVal = $describe.attr('preValue'),
			$btnSpan = $(this).siblings(".btn-group").find("span");
		if($("[name='" + inputName + "']").size() != 1) { // 判断同一个类型的有几条
			if ($.trim(describe_text) != "") { // 有内容
				if ( $btnSpan.attr("preValue") != "" && $btnSpan.text() != $btnSpan.attr("preValue") )
					contactHandleMethod.alterData($describe.attr("name"),"{\"A\":\"ALTER\",\"ID\":\""+$describe.attr("gid")+"\",\"T\":\""+$btnSpan.attr("name")+"\"}");
				if ( describe_text != describe_preVal ) {
					// 修改文字部分的方法
					alterContactInfomation($(this).parent().find(".describe"));
				}
				$btnSpan.attr("preValue",$btnSpan.text());
				$describe.attr("preValue",$describe.html());
				MIKE.multipleInfo[inputName]++;
			}else{
				$(this).closest(".infoSelect").remove();
				contactHandleMethod.alterData($describe.attr("name"),"{\"A\":\"DEL\",\"ID\":\""+$describe.attr("gid")+"\"}");
			}
		} else {
			if ( $btnSpan.text()!=$btnSpan.attr("preValue") ) {
				contactHandleMethod.alterData($describe.attr("name"),"{\"A\":\"ALTER\",\"ID\":\""+$describe.attr("gid")+"\",\"T\":\""+$btnSpan.attr("name")+"\"}");
			}

			if ( $describe.text() != $describe.attr("preValue") && $.trim($describe.text()) !== '') {
				// 修改文字部分的方法
				alterContactInfomation($(this).parent().find(".describe"));
				MIKE.multipleInfo[inputName]++;
			} else {
				$describe.text('');
			}

			$btnSpan.attr("preValue",$btnSpan.text());
			$describe.attr("preValue",$describe.html());
		}

		$describe.removeClass("input_border").removeAttr("contenteditable");
		$(this).parent(".info").find(".btn-group").hide();
		$(this).parent(".info").find(".info_select").show();
		$(this).siblings(".infoSelect_cancel").remove();
		$(this).remove();
	});


	//手机选项及微博选项取消修改
	$(".infoSelect_cancel").live("click",function(){
		var val_old = $(this).siblings(".describe").attr("preValue");
		var valBtn_old = $(this).siblings(".btn-group").find("span").attr("preValue");
		var inputName =  $(this).siblings(".describe").attr("name");
		if($("[name='" + inputName + "']").size() != 1) {
			if ($(this).siblings(".describe").text() =="") {
				$(this).closest(".infoSelect").remove();
			}
		}
		$(this).siblings(".info_select").text(valBtn_old);		
		$(this).siblings(".btn-group").find("span").text(valBtn_old);		
		$(this).siblings(".describe").html(val_old).removeClass("input_border").removeAttr("contenteditable");	
		$(this).parent(".info").find(".btn-group").hide();
		$(this).parent(".info").find(".info_select").show();
		$(this).siblings(".infoSelect_confrim").remove();
		$(this).remove();		 
	});
}
function showMap(){
	"use strict";
	var animating = false,
		myGeo = new BMap.Geocoder(),
		mapMarker = null,
		opts = {type: BMAP_NAVIGATION_CONTROL_ZOOM };

	if(!MIKE.map) {
		MIKE.map = new BMap.Map("map_container");
	}
	var map = MIKE.map;

	$('.info').on('click', '.infoDiv_map', function(event) {
		event.preventDefault();
		var cssObj = {
				'border': '1px solid #AAA',
				'margin-bottom': '5px',
				'margin-left': '40px'
			},
			animateObj = {
				'height': '243px'
			},
			expand = true,
			$map_container = $('#map_container'),
			position = $(this).siblings('.describe').text(),
			infoWindow = new BMap.InfoWindow("<p style='font-size:14px;width:200px;margin-top:4px;'>"+position+"</p>");
			infoWindow.setWidth(220);
		if(!animating && position){
			animating = true;
			if($map_container.height() > 0){
				cssObj = {
					'border': 0,
					'margin-bottom': 0
				};
				animateObj = {
					'height': 0
				};
				expand = false;
			}
			$('.unknow-place').hide();
			$map_container.css(cssObj).animate(animateObj,
				300, function() {
					animating = false;

					if(expand){
						myGeo.getPoint(position, function(point){
							if (point) {
								map.centerAndZoom(point, 16);
								if(!mapMarker){
									mapMarker = new BMap.Marker(point);
								} else {
									mapMarker.setPosition(point);
								}

								map.addOverlay(mapMarker);
								mapMarker.addEventListener("click", function(){
									this.openInfoWindow(infoWindow);
								});
							} else {
								$('.unknow-place').show();
							}
						});
						map.addControl(new BMap.NavigationControl(opts));
					} else {

					}

					$(".information_detail").getNiceScroll().resize();
				/* stuff to do after animation is complete */
			});
		}
	});
}

//编辑联系人——回车提交
// function enterConfrim(){
	// 	$(".describe").keyup(function(e){
	// 		if (e.keyCode == 13) {
	// 			var newVal = $(this).text();
	// 			if ( newVal != $(this).attr("preValue") ) {
	// 				// 修改文字部分的方法
	// 				alterContactInfomation($(this));			
	// 			};
	// 			$(this).attr("preValue",$(this).siblings(".describe").text());
	// 			$(this).removeClass("input_border").removeAttr("contenteditable");
	// 			$(this).siblings(".infoAddress_cancel").remove();
	// 			$(this).siblings(".infoAddress_confrim").remove();
	// 			//preventdefault();	
	// 			//return false;
	// 		}
	// 	});
// }

//使用jQuery获取url参数
function getUrlParam(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
} 

// /*邮件展开*/

// function histoty_box_slide(e) {
// 	if($(e).height() == "44") {
// 		//e.stopPropagation();
// 		$(".history_text").not(e).animate({
// 			height: "44px"
// 		}, 100);
// 		$(e).css({
// 			"overflow": "",
// 			"height": "auto",
// 			"min-height": "44px"
// 		});
// 		var h = $(e).height();
// 		$(e).css("height", "44px").animate({
// 			height: h
// 		}, 300);
// 	}
// }

// function historyTextSlide() {
// 	$(".history_text").click(function(e) {
// 		e.stopPropagation();
// 		histoty_box_slide(this);
// 	});
// 	$("body").click(function(e) {
// 		$(".history_text").animate({
// 			height: "44px"
// 		}, 100);
// 	});
// }

/**
 *
 *		1. 标签的类名，便于自定义样式
 *			tagClass: "mgTagItem"
 *		2. 是否需要标签图片
 *			isRequirePic: true,
 *		3. 是否支持编辑
 *			isEditable: true,
 *		3-1. 是否需要添加按钮
 *			isEnableAddButton: true,
 *		4. 添加按钮所用的文字
 *			tagText: "+"
 *		5. 标签的自动完成的url ， 需要jQuery UI的支持， False表示不支持自动完成的填充。
 *			autoCompleteUrl: false
 *		   标签自动完成成功后的函数, 需要开启 autoCompleteUrl
 *			autoCompleteData: function(ui, request, response)
 *		6. 字数长度的限制
 *			tagMaxLength: 20,
 *		7. 标签是否支持换行的标志位 true 表示的是不换行
 *			isRequireFlow: true,
 *		8. 在添加完成的时候调用的函数
 *			insertFinish: function()
 *		9. 在删除成功之后调用 ui 表示的是当前激活的按钮的DOM
 *			deleteFinish: function(ui)
 *		10. 自定义属性的名字.
 *			customID: "gid"
 *		11. 初始的数据. —— 数组形式
 *			defaultsValue: false
 *
 *	2012-12-13
 *	------------------------------------
 *	UPDATE : 	更新下来模式的切换为左右滑动类似幻灯片的切换模式。
 *	------------------------------------
 *	2012-12-14	更新了新的展示方式，添加了各种效果展现的动画，两边的按钮还是用的图片
 *				可以定义内容的类——改变样式成为可能！，可以选择是否是可以编辑的。
 *	2012-12-18	更新了很多 —— 现在的整个组件都是不定长的了，也不会自动去清空需要组件部分的内容。这一点需要注意。
 *				更新了计算长度的算法，现在计算的更加准确了。
 *
 *	------------------------------------
 *	UPDATE : 	结构和代码进行调整
 *				增加自定义的配置
 *				编码的规范
 *	------------------------------------
 *	可以用于添加标签.可以在一个标签内使用.
 *	支持按钮 Enter ； 以及 , 进行录入
 *	有一定程度的校验,防止html/js的插入
 *
 *	2012-12-03	更新了防止重复创建标签 - update ： 删除的标签现在也支持重新创建了
 *				对初始数据导入的支持
 *	2012-12-04	添加了新的属性GID z这个属性可以用来做很多事情，反正就是一个自定义的值可以存放在这个里面，便于下一步的操作。
 *
 *	2012-12-05	添加了自定义的方法。
 *
 *	2012-12-06	修改了各种不科学的方法，使代码结构更加的规范
 *
 *	2012-12-07	增加了标签图标的选择功能，用属性就可以决定是否要使用标签图标了。
 *
 *	2012-12-10	新增了对于去除下拉框的支持，新增对于自动完成的设置，但是和jQuery UI 关联 ， 数据部分的函数需要自行填充。
 *				添加对于输入交互上的改进.
 *	2012-12-11	新增长度限制,默认限制长度为20;删除标签以后会自动往回添加，尽量减少在下拉框中的元素
 *
 *	@version	0.4.5
 *	@author:	shenmo Samuel
 *	@company:	MikeCRM
 *	@date:		2012-12-18 update
 *	@copyright:	Copyright (c) 2012 - 2012 mgTag MikeCRM(http://www.mikecrm.com)
 *
 */

(function($) {
	var defaults = {
		// 标签的类名，便于自定义样式。 
		tagClass: "mgTagItem",
		// 自定义属性的名字.
		customID: "gid",
		// 是否需要标签图片
		isRequirePic: true,
		picURL: "../../image/system/icon/tag.png",
		// 是否支持编辑
		isEditable: true,
		// 是否需要添加按钮
		isEnableAddButton: true,
		tagText: "+",
		//"+添加",
		// 标签的自动完成的url ， 需要jQuery UI的支持， False表示不支持自动完成的填充。
		autoCompleteUrl: false,
		autoCompleteData: function(ui, request, response) {},
		// 字数长度的限制
		tagMaxLength: 20,
		// 标签是否支持换行的标志位
		isRequireFlow: true,
		isNeedWheel: true,
		// 模式选择
		// modle: "FLOW" // FLOW / LINE / BLOCK 
		// 在添加完成的时候调用
		insertFinish: function() {
			// alert("insert!!");
		},
		// 在删除成功之后调用 ui 表示的是当前激活的按钮的DOM
		deleteFinish: function(ui) {
			// alert("delete!!");
		},
		defaultsValue: false,
		isNeedFloat:true
	};

	// 插件的执行方法
	var tagMethods = {
		init: function(options) { // 初始化运用的方法
			// 用户可以制定的内容
			var opts = $.extend({}, defaults, options),
				_keyObject = {};

			// 清空
			//this.empty();
			// 进行重复的判断，重复的值返回false，不重复的值返回true
			var _fastSearch = {
				addKey: function(key) {
					if(key.length > 0) {
						var lowerKey = key.toLowerCase();
						if(!_keyObject[lowerKey]) {
							_keyObject[lowerKey] = 1;
							return true;
						}
					} else {
						return false;
					}
				},
				removeKey: function(key) {
					if(_keyObject[key]) {
						_keyObject[key] = 0;
					}
				}
			};

			// 一些基础会运用到的方法的封装
			var _basicMethods = {
				isInputFieldExisted: function() {
					var ans;
					(!$(".mgTagFlow").find(".mgAddTagBtn>input").length) ? ans = false : ans = true;
					return ans;
				}
			};

			// 标签的增加删除修改的方法
			var _tagController = {
				// 创建一个可以输入的区域
				createTagField: function(event, ui) { // ui = .mgContainer
					event.stopPropagation();
					if(!_basicMethods.isInputFieldExisted()) {
						ui.find(".mgAddTagBtn").html("");
						$("<input type=\"text\"></input>").appendTo(ui.find(".mgAddTagBtn")).attr({
							"class": "input input_addTag",
							"maxLength": opts.tagMaxLength
						}).focus();
						ui.find(".mgAddTagBtn").addClass("inputMode");
						this.updateContainerWidth(ui.parent());
						if(opts.isRequireFlow) _tagController._overComponentWidth(ui);
						if(opts.autoCompleteUrl) {
							ui.find(".input_addTag").autocomplete({
								minLength: 1,
								source: function(request, response) {
									opts.autoCompleteData(ui.find(".input_addTag"), request, response);
								},
								focus: function(event, ui) { // 每次选择都会改变输入框中的文字内容
									$(event.target).val(ui.item.label);
									return false;
								},
							});
						}
						ui.find(".mgAddTagBtn .input_addTag").bind("keydown", function(event) {
							//console.log(event.keyCode+"--"+event.which);
							if(event.keyCode == "13" || (!event.shiftKey && (event.keyCode == "186" || event.keyCode == "188" ))) { //keyCode=13是回车键
								if(_tagController.removeTagField(opts.tagText, ui.parent())) {
									$(".mgAddTagBtn>input").val("").focus();
									ui.find(".mgAddTagBtn").addClass("inputMode");
								} else {
									ui.find(".mgAddTagBtn").removeClass("inputMode");
								}
							}
						});
						ui.find(".mgAddTagBtn .input_addTag").bind("blur", function(event) {
							if ($(this).val() !== '') {
								_tagController.removeTagField(opts.tagText, ui.parent());
							}
							ui.find(".mgAddTagBtn").text(opts.tagText);
							ui.find(".mgAddTagBtn").removeClass("inputMode");
						});
					}
				},
				// 移除可以编辑的区域
				removeTagField: function(fieldText, ui) { // ui = mgContainer
					if(_basicMethods.isInputFieldExisted()) {
						ui.find(".mgAddTagBtn").removeClass("inputMode");
						var tagAddInputValue = $.trim(ui.find(".mgTagFlow").find(".mgAddTagBtn>input").val()); // 去除了头和尾的空格
						if(tagAddInputValue =="") {
							ui.find(".mgAddTagBtn").text(opts.tagText);
							return false;
						} else if(_tagController.addTag(ui, tagAddInputValue)) {
							opts.insertFinish();
							if(opts.isRequireFlow) {
								_tagController.showButton(ui);
								if(((ui.find(".mgAddTagBtn").offset().left + ui.find(".mgAddTagBtn").width()) > (ui.width() + ui.offset().left)) && ui.find(".mgRightChevron:visible").length > 0) {
									ui.find(".mgRightChevron").trigger("click");
								}
								_tagController.showButton(ui);
							}
							return true;
						} else return true;
					}
				},
				// 鼠标按在外面必定会显示添加按钮的方法
				clickedShowBtn: function(fieldText, event) {
					// event.stopPropagation();
					// $(".mgAddTagBtn").text(opts.tagText);
				},
				// 添加一条新的标签
				addTag: function(ui, tagValue, gid) {
					if(_fastSearch.addKey(tagValue)) {
						$("<span></span>").insertBefore(ui.find(".mgAddTagBtn")).attr("class", opts.tagClass);

						$("<span></span>").appendTo(ui.find(".mgTagFlow>." + opts.tagClass + ":last")).attr(opts.customID, gid).text(tagValue);
						if(opts.isEditable) {
							$("<a></a>").appendTo(ui.find(".mgTagFlow>." + opts.tagClass + ":last")).attr("class", "x").text("　").bind("click", function() {
								_tagController._deleteTag($(this), tagValue);
								$(this).parents(".mgContainer").css({
									"width": _tagController.updateContainerWidth($(this).parents(".mgContainer").parent())
								});
							});
						}
						// - _tagDropdown 换成 _tagFlow
						if(opts.isRequireFlow) {
							_tagController._overComponentWidth(ui);
							_tagController.showButton(ui);
							ui.find(".mgContainer").css({
								"width": _tagController.updateContainerWidth(ui)
							});
						}
						return true;
					} else {
						ui.find(".mgAddTagBtn").stop().animate({
							backgroundColor: "#d89393",
							borderLeftColor: "#b94a48",
							borderTopColor: "#b94a48",
							borderRightColor: "#b94a48",
							borderBottomColor: "#b94a48"
						}, 500).stop().animate({
							backgroundColor: "#CDE69C",
							borderLeftColor: "#A5D24B",
							borderTopColor: "#A5D24B",
							borderRightColor: "#A5D24B",
							borderBottomColor: "#A5D24B"
						}, 1000, function() {
							$(this).removeAttr("style")
						});
						return false;
					}
				},
				_overComponentWidth: function(ui) {
					// 给TagFlow 添加宽度，宽度为标签的宽度;
					var tagItemWidth = 0,
						paddingRight, paddingLeft, marginRight, marginLeft;
					ui.find("." + opts.tagClass).each(function() {
						//paddingRight = parseInt($(this).css("padding-right").replace(/[^0-9]/ig, ""));
						//paddingLeft = parseInt($(this).css("padding-left").replace(/[^0-9]/ig, ""));
						//marginRight = parseInt($(this).css("margin-right").replace(/[^0-9]/ig, ""));
						//marginLeft = parseInt($(this).css("margin-left").replace(/[^0-9]/ig, ""));
						//tagItemWidth += ($(this).width() + paddingLeft + paddingRight + marginLeft + marginRight );
						var $tmp = $(this).find('span:first').text();
						// console.log($tmp)
						// console.log($(this).find('span'));
						var $widthVal = $tmp.replace(/[\u4e00-\u9fa5]{1}/igm,'MM');
						$(this).find('span:first').text($widthVal);
						tagItemWidth +=  $(this).outerWidth(true)+4;//$(this)[0].offsetWidth+marginLeft+marginRight;
						$(this).find('span:first').text($tmp);
						
					});
					if(opts.isEnableAddButton && opts.isEditable) tagItemWidth += ui.find(".mgAddTagBtn").outerWidth(true);//ui.find(".mgAddTagBtn")[0].offsetWidth+ parseInt(ui.find(".mgAddTagBtn").css("margin-right").replace(/[^0-9]/ig, "")) + parseInt(ui.find(".mgAddTagBtn").css("margin-left").replace(/[^0-9]/ig, ""));
					//if(tagItemWidth != ui.find(".mgTagFlow").width()) {
						//console.log(tagItemWidth);
					ui.find(".mgTagFlow").css("width", tagItemWidth+6);
					//}
					if(tagItemWidth > ui.find(".mgContainer").parent().width()) {
						return true;
					} else {
						return false;
					}
				},
				// 删除一个标签，标签的位置和标签的
				_deleteTag: function(ui, tagValue) {
					_fastSearch.removeKey(tagValue);
					opts.deleteFinish(ui.parent());
					if(!ui.parent("." + opts.tagClass).parent("li").length) {
						var deleteComponent = ui.parents(".mgContainer").parent();
						ui.parent("." + opts.tagClass).remove();
						if(opts.isRequireFlow)
							_tagController.showButton(deleteComponent);
					} else {
						if($(".mgTagFlow .btn-group").find(".dropdown-menu").children("li").length == 1) {
							$(".mgTagFlow .btn-group").remove();
						} else ui.parent("." + opts.tagClass).parent("li").remove();
					}
				},
				showButton: function(ui) { // 显示按钮的方法
					if(_tagController._overComponentWidth(ui)) {
						// 右边按钮可以用。
						var lastComponent;
						if(opts.isEditable && opts.isEnableAddButton) { // 两者都要满足才会有添加按钮。
							lastComponent = ".mgAddTagBtn:visible";
						} else {
							lastComponent = "." + opts.tagClass + ":last";
						}
						 // console.log(ui.find(lastComponent));
						 // console.log(ui.find(lastComponent).offset().left);
						 // console.log(ui.find(lastComponent).width());
						 // console.log(ui.offset().left);
						if((ui.find(lastComponent).offset().left + ui.find(lastComponent).width() + 50) < (ui.width() + ui.offset().left))
							ui.find(".mgRightChevron").hide();
						else ui.find(".mgRightChevron").fadeIn();
					} else {
						ui.find(".mgRightChevron").hide();
					}
					if(ui.find(".mgTagFlow").position().left < 0) {
						ui.find(".mgLeftChevron").fadeIn();
					} else {
						ui.find(".mgLeftChevron").hide();
					}
				},
				updateContainerWidth: function(ui) { // ui 是 mgContainer 的父级
					var otherWidth = 0;
					//console.log(ui.html());
					ui.children(":not(.mgContainer)").each(function() {
						//console.log($(this).attr("class"));
						//console.log($(this).attr("class"));
						// var $tmp = $(this).text();
						// var $widthVal = $tmp.replace(/[\u4e00-\u9fa5]{1}/igm,'MM');
						// $(this).text($widthVal);
						// otherWidth +=  $(this).outerWidth(true)+4;//$(this)[0].offsetWidth+marginLeft+marginRight;
						// $(this).text($tmp);
						//console.log($(this).attr("display"));
						 otherWidth += $(this).outerWidth(true);//$(this)[0].offsetWidth; //+ parseInt($(this).css("padding-right").replace(/[^0-9]/ig, "")) + parseInt($(this).css("padding-left").replace(/[^0-9]/ig, "")) + parseInt($(this).css("margin-right").replace(/[^0-9]/ig, "")) + parseInt($(this).css("margin-left").replace(/[^0-9]/ig, ""));
					});
					//otherWidth += 20;
					//console.log( otherWidth );
					//console.log( ui.width()-otherWidth );
					//console.log( ui.find(".mgTagFlow").width() );
					if(ui.find(".mgTagFlow").width() > (ui.width() - otherWidth)){ // tagFlow 超出了当前的宽度， 则固定宽度
						//console.log("flow Bigger Than component:");
						//console.log(ui.width() - otherWidth);
						return ui.width() - otherWidth;
					} else {
						//console.log(ui.find(".mgTagFlow").width());
						return ui.find(".mgTagFlow").outerWidth(true);
					}
				}
			};



			// 插入一个容器，容器类似于遮罩，遮罩的宽度必须是固定的，通过计算父级元素的大小来得到。
			//标签的图标插入在容器元素的前面，用背景的形式进行展现
			if(opts.isRequireFlow) {

				var currentWidth = _tagController.updateContainerWidth(this);
				if(this.find(".mgContainer").length < 1) {
					$("<div></div>").prependTo(this).addClass("mgContainer");
				}
				if(opts.isNeedFloat){
					this.find(".mgContainer").css({
						"width": currentWidth,
						"float": "left",
						"height": "30px",
						"position": "relative",
						"overflow": "hidden",
						"display": "inline-block"
					});
				} else {
					this.find(".mgContainer").css({
						"width": currentWidth,
						"height": "30px",
						"position": "relative",
						"overflow": "hidden",
						"display": "inline-block"
					});
				}

				this.find(".mgContainer").empty();
				//console.log(this);
				$("<div></div>").appendTo(this.find(".mgContainer")).addClass("mgTagFlow").css({
					"width": 100,
					"left": 0,
					"position": "relative",
					"overflow": "hidden",
					"white-space": "nowrap",
					"white-space": "normal",
					"padding-top": "3px",
					"z-index":1
				}).attr("nowrap","nowrap")
				//console.log(opts.picURL + opts.isRequirePic);
				if(opts.isRequirePic) {
					//console.log(opts.picURL + opts.isRequirePic);
					$("<img/>").attr({
						"src": opts.picURL,
						"class": "tag_icon"
					}).prependTo(this); // 插入图片方法
				}

				$("<img/>").attr({
					"src": "../../image/system/icon/tag-left-chevron.png",
					"class": "mgLeftChevron"
				}).prependTo(this.find(".mgContainer")).css({
					"float": "left",
					"position": "absolute",
					"cursor": "pointer",
					"left": 0,
					"top": "0px",
					"z-index":3
				}).hide();
				$("<img/>").attr({
					"src": "../../image/system/icon/tag-right-chevron.png",
					"class": "mgRightChevron"
				}).prependTo(this.find(".mgContainer")).css({
					"float": "right",
					"cursor": "pointer",
					"position": "absolute",
					"right": 0,
					"top": "0px",
					"z-index":3
				}).hide();
				//-- 左移右移按钮事件
				this.find(".mgRightChevron").bind("click", function() {
					var leftValue = -parseInt(parseInt($(this).siblings(".mgTagFlow").css("left").replace(/[^0-9]/ig, "")) + 0.7 * $(this).parent(".mgContainer").width());
					$(this).siblings(".mgTagFlow").animate({
						"left": leftValue
					}, 400, function() {
						_tagController.showButton($(this).parent().parent());
					});
					// console.log("click!");
					return false;
				});
				this.find(".mgLeftChevron").bind("click", function() {
					var leftValue = -parseInt(parseInt($(this).siblings(".mgTagFlow").css("left").replace(/[^0-9]/ig, "")) - 0.7 * $(this).parent(".mgContainer").width());
					if(leftValue > 0) leftValue = 0;
					$(this).siblings(".mgTagFlow").animate({
						"left": leftValue
					}, 400, function() {
						_tagController.showButton($(this).parent().parent());
					});
					// console.log("click!-L");
					return false;
				});
			} else {
				if(this.find(".mgContainer").length < 1) {
					$("<div></div>").prependTo(this).addClass("mgContainer");
				}
				this.find(".mgContainer").empty();
				$("<div></div>").appendTo(this.find(".mgContainer")).addClass("mgTagFlow");
				//alert();
			}


			$("<span></span>").appendTo(this.find(".mgTagFlow")).attr("class", "mgAddTagBtn").text(opts.tagText); // 插入添加按钮的方法
			//console.log(opts);
			if(opts.defaultsValue) {
				if(opts.defaultsValue[0].length > 0) {
					for(var i in opts.defaultsValue[0]) {
						_tagController.addTag(this, opts.defaultsValue[0][i], opts.defaultsValue[1][i]);
					}
				}
				if(!opts.isEditable || !opts.isEnableAddButton) { // 不需要编辑或者不需要添加按钮的时候移除添加按钮
					this.find(".mgAddTagBtn").remove();
				}
			}

			// 创建输入框
			this.find(".mgAddTagBtn").bind("click", function(event) {
				_tagController.createTagField(event, $(this).parents(".mgContainer"));
			});
			// 点击任意地方消除
			$("body").live("click", function(event) {
				if($(event.target).attr("class") == "ui-corner-all") {
					return false;
				} else if($(event.target).attr("class") != "input_addTag" || $(event.target).attr("class") != "ui-corner-all") {
					var e = jQuery.Event("keydown"); //模拟一个键盘事件
					e.keyCode = 13;
					$(".mgAddTagBtn").find("input").trigger(e);
					if($(".input_addTag").val() != "") $(".mgAddTagBtn").find("input").val("").trigger(e);
				}
			});
			// 支持滚轮
			if(opts.isRequireFlow){
				if(opts.isNeedWheel){
					this.find(".mgTagFlow").mousewheel(function(event, delta, deltaX, deltaY) {
						// console.log(delta);
						if(delta > 0) {
							if($(this).siblings(".mgLeftChevron:visible").length) {
								if(parseInt($(this).css("left"))>=-40){
									$(this).stop().animate({
										"left": "0px"
									}, 10, function() {
										_tagController.showButton($(this).parent().parent());
									});
								} else {
									$(this).stop().animate({
										"left": "+=40"
									}, 10, function() {
										_tagController.showButton($(this).parent().parent());
									});
								}
							}
						}
						if(delta < 0) {
							if($(this).siblings(".mgRightChevron:visible").length) {
								$(this).animate({
									"left": "-=40"
								}, 10, function() {
									_tagController.showButton($(this).parent().parent());
								});
							}
						}
						return false; // prevent default
					});
				} else {
					return false;
				}
			}
		},
		get: function() { // 获取所有的标签的数组
			var tagArray = new Array();
			this.find(".mgTagFlow").children("span:not(.mgAddTagBtn)").each(function() {
				tagArray.push($(this).find("span:first").text());
			});
			return tagArray;
		}
	};

	$.fn.mgTag = function() {
		var method = arguments[0];
		// 方法调用逻辑
		if(tagMethods[method]) {
			method = tagMethods[method];
			// 我们的方法是作为参数传入的，把它从参数列表中删除，因为调用方法时并不需要它
			arguments = Array.prototype.slice.call(arguments, 1);
		} else if(typeof(method) == 'object' || !method) {
			method = tagMethods.init;
		} else {
			$.error('Method == ' + method + ' == does not exist on jQuery.mgTag - CREATE BY MIKECRM');
			return this;
		}
		// 用apply方法来调用我们的方法并传入参数
		return method.apply(this, arguments);
	};

})(jQuery);
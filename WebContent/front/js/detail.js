$(document).ready(
		function() {
			var div_nav_fixed = document.getElementById("div_nav_fixed");

			window.onscroll = function() {
				var fiexdtop = div_nav_fixed.offsetTop;
				var top = document.documentElement.scrollTop
						|| document.body.scrollTop;
				if (top > fiexdtop) {
					$("#div_nav").addClass("fixed");
				} else {
					$("#div_nav").removeClass("fixed");
				}
			};
		});

var time_range = function(beginTime, endTime, nowTime) {
	var strb = beginTime.split(":");
	if (strb.length != 2) {
		return false;
	}

	var stre = endTime.split(":");
	if (stre.length != 2) {
		return false;
	}

	var strn = nowTime.split(":");
	if (stre.length != 2) {
		return false;
	}
	var b = new Date();
	var e = new Date();
	var n = new Date();

	b.setHours(strb[0]);
	b.setMinutes(strb[1]);
	e.setHours(stre[0]);
	e.setMinutes(stre[1]);
	n.setHours(strn[0]);
	n.setMinutes(strn[1]);

	if (n.getTime() - b.getTime() > 0 && n.getTime() - e.getTime() < 0) {
		return true;
	} else {
		alert("当前时间是：" + n.getHours() + ":" + n.getMinutes() + "，不在该时间范围内！");
		return false;
	}
}

/** ******************** */
$(function() {
	initPreview();
	initScroll();
	// selectSku2(APP.goodsId);
	getEvaluation(1);//获取评价
	
	//图文详情和评价切换
	$(".box li").click(function(){
		var num = $(this).children().attr("data-idx");
		$(this).parent().find("a").removeClass("on");
		$(this).children().addClass("on");
		
		$("#div_sections > section").removeClass("on");
		$("#div_sections > section").eq(num).addClass("on");
	});
});

//
function initScroll() {
	var swiper_wrapper = document.getElementById("widget_wrap").querySelector(
			"ul");
	var len = swiper_wrapper.querySelectorAll("li").length;
	if (len <= 1) {
		return;
	}
	swiper_wrapper.parentNode.appendChild(swiper_wrapper.cloneNode(true));
	swiper_wrapper.parentNode.appendChild(swiper_wrapper.cloneNode(true));
	myScroll = new iScroll('widget_wrap', {
		snap : true,
		momentum : false,
		hScrollbar : false,
		useTransition : true,
		handleClick : false,
		onBeforeScrollStart : function(e) {
		},
		onScrollMove : function(e) {
			e.preventDefault();
		},
		onBeforeScrollEnd : function(evt) {
			if (this.currPageX >= (len * 2 - 1) && this.dirX == 1) {
				this.tmp_currPageX = len;
				this.rePos = true;
			} else if (this.currPageX <= len && this.dirX == -1) {
				this.tmp_currPageX = 2 * len - 1;
				this.rePos = true;
			}
		},
		onScrollEnd : function(evt) {
			if (this.rePos) {
				this.rePos = false;
				this.currPageX = this.tmp_currPageX;
				this.scrollToPage(this.currPageX, 0, 0);
			} else {
				return;
			}
		}
	}).scrollToPage(len, 0, 0);
}

/** 收藏模块* */
window.favEvt = function(evt) {
	var ele = evt.target, state = ele.classList.contains("on");
	if (favEvt.disabled) {
		return;
	}
	favEvt.disabled = true;
	evt.preventDefault();
	$.post(basePath + "/front/shop/usercollection.html", {
		"userid" : userid,
		"prodid" : $("#prodid").val()
	}, function(res) {
		delete favEvt.disabled;
		if (res == 0) {
			$("#icon_fav1").removeClass("on");
		} else {
			$("#icon_fav1").addClass("on");
		}
	});
}

window.add2Shopcart = function(thi, evt) {
	var tips = [];
	var specId = 0;
	if (is_support != 0) {
		var chose = $("input[name='sku_0']:checked").length;
		if (chose == 0) {
			tips.unshift("请选择商品规格~");
			alert(tips[0]);
			return;
		} else {
			specId = $("input[name='sku_0']:checked").attr("value");
		}
	}
	// 3：库存不足
	if (sku_inventorynumber > 0) {
		if (2 == sellState) {
			tips.unshift("该商品已经下架了哦~");
			alert(tips[0]);
			return;
		} else if (true == selltime && sellState == 0) {
			tips.unshift("该商品还没有到销售时间，请耐心等待");
			alert(tips[0]);
			return;
		}
	} else {
		tips.unshift("您来晚了，商品已经卖完啦~");
		alert(tips[0]);
		return;
	}

	thi.setAttribute("disabled", "disabled");
	var _loading = loading();
	$
			.ajax({
				type : "POST",
				url : basePath + "/front/shop/editcar.html",
				data : {
					prodid : $("#prodid").val(),
					userid : userid,
					count : $("#sku_number").val(),
					specId : specId,
					type : 1
				},
				async : true,
				success : function(res) {
					_loading.destroy();
					thi.removeAttribute("disabled");
					if (1 == res.Status) {
						confirm(
								"成功加入购物车",
								{
									TPL : '<div class="widget_wrap" style="z-index:{zIndex2};" >\
			                    <div class="widget_header"></div>\
			                    <div class="widget_body">{str}</div>\
			                    <div class="widget_footer">\
			                        <ul>\
			                            <li><button type="button" value="0" style="width:83px;">去结算</button></li>\
			                            <li><button type="button" value="1" style="width:83px;">继续购物</button></li>\
			                        </ul>\
			                    </div>\
			                </div>',
									callBack : function(evt) {
										if (evt && (ele = evt.target)
												&& ("BUTTON" == ele.tagName)) {
											var val = ele.getAttribute("value");
											if (0 == val) {
												location.href = basePath
														+ "/front/shop/showcar.html?userid="
														+ userid;
											} else {
												// selectSku2(APP.goodsId);
												// location.reload();
											}
											return true;
										}
									}
								});
					} else {
						confirm(
								res.Message,
								{
									TPL : '<div class="widget_wrap" style="z-index:{zIndex2};" >\
			                    <div class="widget_header"></div>\
			                    <div class="widget_body">{str}</div>\
			                    <div class="widget_footer">\
			                        <ul>\
			                            <li><button type="button" value="0" style="width:83px;">我的购物车</button></li>\
			                        </ul>\
			                    </div>\
			                </div>',
									callBack : function(evt) {
										if (evt && (ele = evt.target)
												&& ("BUTTON" == ele.tagName)) {
											var val = ele.getAttribute("value");
											if (0 == val) {
												location.href = basePath
														+ "/front/shop/showcar.html?userid="
														+ userid;
											} else {
												// selectSku2(APP.goodsId);
												// location.reload();
											}
											return true;
										}
									}
								});
					}
				},
				dataType : "json"
			});
}
window.buy = function(thi, evt) {
	var tips = [];
	var specId = 0;
	if (is_support != 0) {
		var chose = $("input[name='sku_0']:checked").length;
		if (chose == 0) {
			tips.unshift("请选择商品规格~");
			alert(tips[0]);
			return;
		} else {
			specId = $("input[name='sku_0']:checked").attr("value");
		}
	}
	// 3：库存不足
	if (sku_inventorynumber > 0) {
		if (2 == sellState) {
			tips.unshift("该商品已经下架了哦~");
			alert(tips[0]);
			return;
		} else if (true == selltime && sellState == 0) {
			tips.unshift("该商品还没有到销售时间，请耐心等待");
			alert(tips[0]);
			return;
		}
	} else {
		tips.unshift("您来晚了，商品已经卖完啦~");
		alert(tips[0]);
		return;
	}
	thi.setAttribute("disabled", "disabled");
	var _loading = loading();
	$.ajax({
		type : "POST",
		url : basePath + "/front/shop/editcar.html",
		data : {
			prodid : $("#prodid").val(),
			userid : userid,
			count : $("#sku_number").val(),
			type : 3,
			specId : specId
		},
		async : true,
		success : function(res) {
			_loading.destroy();
			thi.removeAttribute("disabled");
			if (0 < res.Status) {
				location.href = basePath + "/front/shop/orderpage.html?prods="
						+ res.Status + "-" + $sk_number.val() + ",&userid="
						+ userid;
			}else{
				alert(res.Message);
			}
		},
		dataType : "json"
	});
}

//获取审核通过的评论
function getEvaluation(currentPage){
	$.ajax({
		url : basePath + "/front/shop/getevallist.html",
		type : "post",
		dataType : "json",
		data : {
			prodId : $("#prodid").val(),
			currentPage : currentPage
		},
		async : true,
		success : function(data) {
			var html = "";
			var tt = $.parseJSON(data.list);
			$(tt).each(function(index) {
					var val = tt[index];
					html += "<ul id='list_comments_"+index+"' class='list_comments'>"+
				                	"<li><div class='tbox'>"+
				            		"<div><span class='img_wrap'><img src='"+val.picUrl+"'></span><p>"+val.userName+"</p><p style='align:center;'></p></div>"+
				            		"<div><p class='comment_content'>"+val.content+"</p>"+
				            		"<p><label class='comment_rate' data-rate='5'></label><label class='comment_time'>"+val.evalTime+"</label></p>"+
				            		"<p></p><div class='reply' style='display:none;'>null</div><p></p></div></div>"+
				            	"</li>"+
				            "</ul>";
			});
			if (html == "") {
				$("#eval_ul")
						.html("<div id=\"list_comments\"><p class=\"no_comments\">暂无评价</p></div>");
			}else{
				$("#eval_ul").html(html+"<div id='commentpage' class='page_and_btn'></div>");
				$("#commentpage").html(data.page.ajaxPage.pageStr);
			}
		},
		error : function() {
		}
	});			
}

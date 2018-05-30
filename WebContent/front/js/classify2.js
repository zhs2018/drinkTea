
define(function (require, exports, module) {
    var $ = require("lib_cmd/zepto"),
		main = require("js_cmd/main"),
		iScroll = require("lib_cmd/iScroll-cmd"),
		$eles = {},
		ele = {};

	//
	function initPage(){
		$eles.iscroll_wraps.css({"height":$(window).height()+"px", "overflow":"auto"});
		//

		//
		$eles.list_nav_lis.on("click", function(evt){
			ele.index = parseInt(this.getAttribute("data-index") );
		});
		//
		ele.scroller_nav = new iScroll('scroller_nav', {
			vScrollbar: false,
			handleClick: false,
			onBeforeScrollStart: function(e){}
		});
		ele.scroller_body = new iScroll('scroller_body', {
			vScrollbar: false,
			handleClick: false,
			onBeforeScrollStart: function(e){}
		});

		ele.scroller_body.wrapper.addEventListener("click", function(){
			sessionStorage.setItem("ele-idx", ele.index);
		}, false);

		var idx = sessionStorage.getItem("ele-idx");
		if(idx){
			ele.index = idx;
			ele.scroller_nav.scrollTo(0, -(46*idx), 0);
			sessionStorage.removeItem("ele-idx");
		}
	}
	$(function(){
		$eles = {
			classify_2: $("#classify_2"),
			list_nav: $("#list_nav"),
			list_nav_lis: $("#list_nav li"),
			iscroll_wraps: $("#scroller_nav, #scroller_body")
		}
		//
		ele = (function(){
			function Ele(){
				var index = 0;
				this.scroller_nav = null;
				this.scroller_body = null;

				Object.defineProperty(this, "index", {
					get: function(){
						return index;
					},
					set: function(v){
						index = v;
						console.log(index);
						$("#list_nav li a, .list_ul").removeClass("on");
						$(".list_ul").eq(ele.index).addClass("on");
						$eles.list_nav_lis.find("a").eq(ele.index).addClass("on");
						this.scroller_body.refresh();
					}
				});

			}
			return new Ele();
		})();
		//
		initPage();
	});
});
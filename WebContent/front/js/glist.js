define(function(require, exports, moudle){
	var $ = require("zepto"),
		main = require("main"),
		myDialog = require("myDialog"),
		scrollEvt = require("scrollEvt");
		iTemplate = require("iTemplate"),
		$eles = {},
		ele = {};


	

	(function(win, doc){
		var dc = localStorage.getItem("glist"), dataCache;
		if(dc && (dataCache = JSON.parse(dc)) && dataCache.flag ){
			
		}else{
			dataCache = {
				data:[],
				pageIndex:0,
				pageSize:20,
				index:0,
				flag:false
			};
		}

		dataCache.recordInfo = function(thi, evt, index){
			dataCache.flag = true;
			dataCache.index = index;
			dataCache.st = doc.body.scrollTop;
			localStorage.setItem("glist", JSON.stringify(dataCache) );
		}
		dataCache.destroy = function(){
			localStorage.removeItem("glist");
		}

		win.dataCache = dataCache;
	})(window, document);


	/**头部菜单模块**/
	window.menuNav = function(type){
		switch(type){
			case 0:
				var container = document.querySelectorAll(".container")[0];
				if(container.getAttribute("style")){
					window.myScroll&&myScroll.sleep(false);
					container.removeAttribute("style");
					document.getElementById("sideBar").classList.remove("on");
					setTimeout(function(){
						container.classList.remove("animate");
						$(".rotate_body").removeAttr("style");
					}, 350);
				}else{
					window.myScroll&&myScroll.sleep(true);
					var height = $(window).height();
					container.classList.add("animate");
					container.setAttribute("style", "height:"+height+"px;");
					$(".rotate_body").height(height);
					document.getElementById("sideBar").classList.add("on");
					//var w = document.body.offsetWidth;
					//var w1 = Math.cos((60/180)*Math.PI)*w - Math.sin((30/180)*Math.PI)*180;
					//document.getElementById("sideBar").style.width = w-w1+"px";
				}
			break;
			case 1:
				$("#sort_div").toggleClass("on");
			break;
			case 2:
				dataCache.glist_big = true;
				$("#glist_ul").addClass("glist_big");
				$(".icon_one_grid, .icon_more_grid").removeClass("on").eq(arguments[1]).addClass("on");
			break;
			case 3:
				dataCache.glist_big = false;
				$("#glist_ul").removeClass("glist_big");
				$(".icon_one_grid, .icon_more_grid").removeClass("on").eq(arguments[1]).addClass("on");
			break;
			default:

			break;
		}
	}

});
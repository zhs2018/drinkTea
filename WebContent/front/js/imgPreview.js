previewImg = (function(){
		var imgsSrc = [];
		function previewImg(thi, evt, urls, _imgsSrc) {
		    if (typeof window.WeixinJSBridge != 'undefined') {
		    	imgsSrc = urls.split(",");
		        imgsSrc.length&&WeixinJSBridge.invoke('imagePreview',{ 'current':imgsSrc[0], 'urls':_imgsSrc||imgsSrc} );   
		    }else{
		    	alert("请使用微信预览");
		    }
		}
		return previewImg;
	})();

	function initPreview(){
		//
		var $imgs = $("#widget_wrap ul img"),
			imgsSrc = [[],[]];
		$imgs.each(function(){
			if(this.src){
				imgsSrc[0].push(this.src);
			}
		});
		$("#widget_wrap").live("click", function(evt){
			var ele = evt.target;
			if("IMG" == ele.tagName){
				previewImg(this, evt, ele.src, imgsSrc[0]);
			}
		});
		//
		$imgs = $("#info_detail_2_section img");
		$imgs.each(function(){
			if(this.src){
				imgsSrc[1].push(this.src);
			}
		});
		$("#info_detail_2_section").live("click", function(evt){
			var ele = evt.target;
			if("IMG" == ele.tagName){
				previewImg(this, evt, ele.src, imgsSrc[1]);
			}
		});
	}

	window.previewImg = previewImg;
	window.initPreview = initPreview;
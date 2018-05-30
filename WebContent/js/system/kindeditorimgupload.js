$(function(){
	KindEditor
		.ready(function(K) {
			var editor = K.editor({
				themeType : "image",
				allowFileManager : true
	
			});
			$(document).on("click",
							'a.insertimage',
							function(e) {editor.loadPlugin('image',
												function() {editor.plugin.imageDialog({
																imageUrl : $(e.target).parent().prev().val(),
																clickFn : function(url,title,width,height,border,align) {
																	$(e.target).parent().prev().val(url);
																	$(e.target).prev().attr("src",url).css("display","");
																	editor.hideDialog();
																}
															});
												});
							});
	
	});
})

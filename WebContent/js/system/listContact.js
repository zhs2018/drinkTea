$(document).ready(function() {

	// 弹窗——批量删除联系人
	$(".listContact_removeObjectSignle").click(function(){
		var delId=$(this).attr("id");
		var title=$(this).attr("title");
		 bootbox.dialog({
			 message: "确定要删除这个"+title+"?",
			 title: "提示",
			 buttons: {
			 success: {
			 label: "取消!",
			 className: "white",
			 callback: function() {
			 }
			 },
			 main: {
			 label: "确定!",
			 className: "blue",
			 callback: function() {
				 var DATA = '';
					DATA = '{"CTIDS":"'+delId+'","EXCLUSION":"false"}';
					$.ajax({
						url: "delone",
						type: "post",
						data: {
							"DATA": DATA
						},
						success: function(data){
							if (eval("("+data+")").flag) {
								bootbox.dialog({
									 message: "成功删除",
									 title: "提示",
									 buttons: {
									 main: {
									 label: "确定!",
									 className: "blue",
									 callback: function() {
										 window.location.href=window.location.href;
									 }
									 }
									 }
								 });

							}
						}
					});
			 }
			 }
			 }
		 });
	});

//	// 弹窗——批量删除联系人
//	$(".listContact_removeObjectSignleDouble").click(function(){
//		var delId=$(this).attr("id");
//		var title=$(this).attr("title");
//		 bootbox.dialog({
//			 message: "确定要删除这个"+title+"?",
//			 title: "提示",
//			 buttons: {
//			 success: {
//			 label: "取消!",
//			 className: "white",
//			 callback: function() {
//			 }
//			 },
//			 main: {
//			 label: "确定!",
//			 className: "blue",
//			 callback: function() {
//				 bootbox.dialog({
//					 message: "确定要删除这个"+title+"?",
//					 title: "再次提示",
//					 buttons: {
//					 success: {
//					 label: "取消!",
//					 className: "white",
//					 callback: function() {
//					 }
//					 },
//					 main: {
//					 label: "确定!",
//					 className: "blue",
//					 callback: function() {
//						 var DATA = '';
//							DATA = '{"CTIDS":"'+delId+'","EXCLUSION":"false"}';
//								$.ajax({
//									url: "/jgmarkting/system/syscompany/delone.html",
//									type: "post",
//									data: {
//										"DATA": DATA
//									},
//									beforeSend:function(){
//										$('body').append('<div class="tips_cover"></div><div class="cover_content"><div class="coverIng"><span>正在删除'+title+'，请稍候...</span></div></div>');
//									},
//									success: function(data){
//										if (eval("("+data+")").flag) {
//											$('.cover_content').empty().append('<div class="cover_after"><span>成功删除'+title+'~</span></div>');
//											window.location.href=window.location.href;
//										}
//									}
//								});
//					 		}
//					 	}
//					 }
//				 });
//
//			 }
//			 }
//			 }
//		 });
//	});
//
//	// 弹窗——批量删除联系人
//	$(".listContact_removeObjectWinning").click(function(){
//		var delId=$(this).attr("id");
//		var title=$(this).attr("title");
//		 bootbox.dialog({
//			 message: "确定要删除这个"+title+"?",
//			 title: "提示",
//			 buttons: {
//			 success: {
//			 label: "取消!",
//			 className: "white",
//			 callback: function() {
//			 }
//			 },
//			 main: {
//			 label: "确定!",
//			 className: "blue",
//			 callback: function() {
//					 var DATA = '';
//						DATA = '{"CTIDS":"'+delId+'","EXCLUSION":"false"}';
//						$.ajax({
//							url: "delonewinning.html",
//							type: "post",
//							data: {
//								"DATA": DATA
//							},
//							beforeSend:function(){
//								$('body').append('<div class="tips_cover"></div><div class="cover_content"><div class="coverIng"><span>正在删除'+title+'，请稍候...</span></div></div>');
//							},
//							success: function(data){
//								if (eval("("+data+")").flag) {
//									$('.cover_content').empty().append('<div class="cover_after"><span>成功删除'+title+'~</span></div>');
//									window.location.href=window.location.href;
//								}
//							}
//						});
//			 		}
//			 	}
//			 }
//		 });
//	});
//
//

});
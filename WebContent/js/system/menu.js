$(function() {

	//验证表单的规则
	$('#menu_form').validate({
		rules: {
			name: "required",
			sort:"digits",
			content:"required"
		},
		debug: true,
	});

	var delmenu = '';
	$('.validate_submit').click(function(event) {
		//验证表单的规则
		var valid = $('#menu_form').valid();
		if (!valid) {
			return false;
		}
		var array = new Array();
		$('.menuItem').each(function(index, el) {
			var obj = new Object();
			obj.id = $(this).find('input[name=id]').val();
			obj.parentId = $(this).find('input[name=parentId]').val();
			obj.sort = $(this).find('input[name=sort]').val();
			obj.name = $(this).find('input[name=name]').val();
			obj.type = $(this).find('select').val();
			obj.content = $(this).find('input[name=content]').val();
//			obj.responseId = $(this).find('input[name=responseId]').val();
//			obj.companyId = $('input[name=companyId]').val();
			array.push(obj);
		});
		$.ajax({
			url: RESOURCEDOMAIN+'/system/menu/savemenu',
			type: 'post',
			dataType: 'text',
			data: {
				menus: JSON.stringify(array),
				delmenus: delmenu
			},
			success:function(data){
				bootbox.dialog({
					message : data,
					title : "提示",
					buttons : {
						main : {
							label : "确定!",
							className : "blue",
							callback : function() {
								window.location.href=window.location.href;
							}
						}
					}
				});
			}
		})
	});

	$('.add_menu').click(function(event) {
		if ($('.primary').length >= 3) {
			bootbox.dialog({
				message : "主菜单个数不能超过3个",
				title : "提示",
				buttons : {
					main : {
						label : "确定!",
						className : "blue",
						callback : function() {
						}
					}
				}
			});
			return false;
		}
		var append_menu = '<tr class="menuItem primary"><td><input type="hidden" name="id" /> <input type="hidden" name="parentId" value="0" /><input type="text"  class="form-control" name="sort" value="0" /></td><td><input type="text" name="name"  class="form-control" /></td><td><select name="type"  class="form-control"><option value="scancode_push">扫一扫</option><option value="view">跳转链接</option></select></td><td><input type="text" name="content"  class="form-control"/> <input type="hidden"name="responseId" /></td></tr>';
		$('tbody').append(append_menu);
	});
	$(document).on('click', '.del_menu', function() {
		var menuId = $(this).attr('menuId');
		delmenu = delmenu + menuId + ",";
		$(this).parent().parent().remove();
		$('.item' + menuId).remove();
	})

	$(document).on('click', '.add_submenu', function() {
		var parentId = $(this).attr('parentId');
		if ($('.item' + parentId).length >= 5) {
			bootbox.dialog({
				message : "子菜单个数不能超过5个",
				title : "提示",
				buttons : {
					main : {
						label : "确定!",
						className : "blue",
						callback : function() {
						}
					}
				}
			});
			return false;
		}
		var append_submenu = '<tr class="menuItem item' + parentId + '"><td><input type="hidden" name="id" /> <input type="hidden" name="parentId" value="' + parentId + '" /><input type="text"  class="form-control" name="sort" value="0" /></td><td>|____<input type="text" name="name" style="margin-top: -20px; margin-left: 40px; width: 150px;" class="form-control" /></td><td><select name="type"  class="form-control"><option value="scancode_push">扫一扫</option><option value="view">跳转链接</option></select></td><td><input type="text" name="content"  class="form-control"/> <input type="hidden"name="responseId" /></td><td><a href="javascript:void(0);" class="del_submenu">删除</a></td></tr>';
		$(this).parent().parent().after(append_submenu);
	})

	$(document).on('click', '.del_submenu', function() {
		var menuId = $(this).attr('menuId');
		delmenu = delmenu + menuId + ",";
		$(this).parent().parent().remove();
	})

	//更新微信的自定义菜单
	$('.update_weixinmenu').click(function(event) {
		$(this).html("生成中.....")
		$.ajax({
			url:RESOURCEDOMAIN+ '/system/menu/updatewxmenu',
			type: 'POST',
			dataType: 'text',
		})
		.done(function(data) {
			bootbox.dialog({
				message : data,
				title : "提示",
				buttons : {
					success : {
						label : "取消!",
						className : "white",
						callback : function() {
							$(".update_weixinmenu").html("生成自定义菜单")
						}
					},
					main : {
						label : "确定!",
						className : "blue",
						callback : function() {
							$(".update_weixinmenu").html("生成自定义菜单")
						}
					}
				}
			});
		});

	});

})
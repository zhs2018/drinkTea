/* 所有的编辑的操作，都在这里进行 
 2013-12-10
 @Author samuel 第三次
 */


var MKFORM = {};

// !!! TODO !!! 一定要记得上线修改！
//MKFORM.localHost = 'http://localhost/~samuel/Mike_CRM/trunk/_HTML/';
MKFORM.localHost = 'http://www.mikecrm.com/';
// MKFORM.localHost = 'http://192.168.1.104/mikeCRM/trunk/_HTML/';
// MKFORM.localHost = 'http://localhost/MikeCRM/trunk/_HTML/';

MKFORM.uploadhint = '请选择2M内的jpg、png图片';

// --- 组件关系表
// -- 组件名称 
//	form_edit_title \ form_edit_instruct \ form_edit_required \ form_edit_size
//	\ form_edit_texttype \ form_edit_checkboxset \ form_edit_selectset \ form_edit_selecttype \ form_edit_checkboxlogicset
//	\ form_edit_datetype \ form_edit_starnum  \ form_edit_textalign \ form_edit_picture


// !!!!! 这个部分 在添加新的修改方法的时候一定要过来加一下，不然一定会被editManager干掉的
MKFORM.editMap = {
	'id_singleLine': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size', 'form_edit_texttype'],
	'id_number': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'id_multiple': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size', 'form_edit_texttype'],
	'id_dropDown': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_selectset', 'form_edit_selecttype'],
	'id_radio': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_radioset', 'form_edit_selecttype','form_edit_selectlayout'],
	'id_checkBox': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_checkboxset', 'form_edit_selecttype', 'form_edit_checkboxlogicset','form_edit_selectlayout'],
	'id_section': ['form_edit_title', 'form_edit_instruct', 'form_edit_textalign'], // 分割线的粗细颜色 呵呵呵呵呵呵
	'id_picture': ['form_edit_instruct','form_edit_picture', 'form_edit_textalign', 'form_edit_picture_link'],
	'id_fileUpload': ['form_edit_title', 'form_edit_required', 'form_edit_instruct', 'form_edit_filetype'],
	'id_star': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_starnum'],
	'id_date': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_datetype'],
	'id_shopping': ['form_alert_payment', 'form_edit_title', 'form_edit_required', 'form_edit_instruct', 'form_edit_shoppinglist', 'form_alipaysetting'],
	// TODO !!
	'id_pictureCheckbox': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_pic_checkboxset','form_edit_checkboxlogicset','form_edit_picselecttype'],
	'id_pictureRadio': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_pic_radioset','form_edit_picselecttype'],
	'basic_name': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'basic_birthday': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'basic_job': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'basic_email': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'basic_city': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'basic_company': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'basic_adress': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'basic_phone': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'basic_weixin': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'basic_fax': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'basic_gender': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_radioset','form_edit_selectlayout'],
	'basic_website': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'basic_qq': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'basic_position': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size'],
	'basic_mobile': ['form_edit_title', 'form_edit_instruct', 'form_edit_required', 'form_edit_size']
};

MKFORM.nameMap = {
	'basic_name': '姓名',
	'basic_job': '职位',
	'basic_city': '城市',
	'basic_company': '公司',
	'basic_adress': '通讯地址',
	'basic_phone': '固话',
	'basic_weixin': '微信',
	'basic_fax': '传真',
	'basic_gender': '称谓',
	'basic_email': 'E-mail',
	'basic_website': '网址',
	'basic_qq': 'QQ',
	'basic_position': '——',
	'basic_mobile': '手机',
	'basic_birthday': '生日'
};

// 这个部分是 绑定的时候调用函数的列表
MKFORM.editFunctionMap = {
	'id_singleLine': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired', 'settingTextType'],
	'id_number': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'id_multiple': ['settingTitle', 'settingTextSize', 'settingInstruct', 'settingRequired', 'settingTextType'],
	'id_dropDown': ['settingTitle', 'settingSelectField', 'settingInstruct', 'settingRequired', 'settingChooseType'],
	'id_radio': ['settingTitle', 'settingRadioField', 'settingInstruct', 'settingRequired', 'settingChooseType','settingSelectLayout'],
	'id_checkBox': ['settingTitle', 'settingCheckboxField', 'settingInstruct', 'settingRequired', 'settingChooseType', 'settingCheckboxSelectLogic','settingSelectLayout'],
	'id_section': ['settingTitle', 'settingSubTitle','settingSubTitleAlign'],
	'id_picture': ['settingSubTitle','settingSubTitleAlign','settingPicture','settingPictureLink'],
	'id_star': ['settingTitle', 'settingInstruct', 'settingRequired', 'settingStarNum'],
	'id_date': ['settingTitle', 'settingInstruct', 'settingRequired', 'settingDateType'],
	'id_fileUpload': ['settingTitle', 'settingRequired', 'settingInstruct','settingFileType'],
	'id_shopping': ['settingTitle', 'settingRequired', 'settingInstruct', 'settingShoppingField'],
	'id_pictureCheckbox': ['settingTitle', 'settingPictureCheckboxField', 'settingInstruct', 'settingRequired','settingCheckboxSelectLogic','settingPicChooseType'],
	'id_pictureRadio': ['settingTitle', 'settingPictureRadioField', 'settingInstruct', 'settingRequired','settingPicChooseType'],
	'basic_name': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'basic_birthday': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'basic_job': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'basic_city': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'basic_company': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'basic_adress': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'basic_phone': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'basic_weixin': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'basic_fax': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'basic_gender': ['settingTitle', 'settingGenderField', 'settingInstruct', 'settingRequired','settingSelectLayout'],
	'basic_email': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'basic_website': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'basic_qq': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'basic_position': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired'],
	'basic_mobile': ['settingTitle', 'settingInputSize', 'settingInstruct', 'settingRequired']
};

MKFORM.currentComponent = ''; // 记录id
MKFORM.currentChanged = true; // 记录完 id 要来修改一下这个 这个用来判断需不需要重新绑定事件

MKFORM.editManager = function (currentType) {
	// var editFieldAll = ['form_edit_title','form_edit_instruct','form_edit_required','form_edit_size','form_edit_texttype','form_edit_selectset','form_edit_selecttype','form_edit_radioset','form_edit_checkboxset','form_edit_checkboxlogicset','form_edit_starnum','form_edit_datetype','form_edit_shoppinglist'];
	// get ALL
	if (currentType) {
		$('.form_componentEdit_tips').hide();
	} else {
		$('.form_componentEdit_tips').show();
	}
	if (currentType) {
		if (currentType.indexOf('basic') >= 0) {
			$('.formBuilder_edit_titleTip').show();
			$('.formBuilder_edit_titleTip .formBuilder_edit_titleTip_highlight').text(MKFORM.nameMap[currentType]);
		} else {
			$('.formBuilder_edit_titleTip').hide();
		}
	}

	$('.form_edit').each(function (i) {
		var editId = $(this).attr('id');
		if ($.inArray(editId, MKFORM.editMap[currentType]) < 0) {
			$(this).hide();
		} else {
			$(this).show();
		}
		// -- 非列表的隐藏,其他的显示……先不要动画了，不知道怎么加
	});

}; // 判断元素是否已经显示，显示的


MKFORM.componentSetting = {
	'settingTitle': (function () {
		// -- return obj
		var $editField = $('#form_edit_title'), // 获取title 对象
			$selectedCom,
			$titleField,
			oldValue;

		// 对当前的组件进行事件的绑定
		function mkBind() {
			var titleVal;
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				$titleField = $selectedCom.find('.title');
				titleVal = $titleField.html().replace(/<br(\/)*>/igm, "\n");
				oldValue = $titleField.html().replace(/<br(\/)*>/igm, "\n");
				$editField.find('.title_textarea').val(titleVal).unbind('input keyup').bind('input keyup',function () {
					$titleField.html($(this).val().replace(/[\r\n]/igm, '<br/>')); // 回车转义保存
					MKGlobal.addUnsaveCount();
					// alert();
				}).bind('mkReset', function () {
						$(this).val($titleField.html().replace(/<br(\/)*>/igm, "\n"));
						MKGlobal.addUnsaveCount();
					});
			}
		}

		return {
			redo: function () {
				// current edit field clean
				return $titleField.text(oldValue.replace(/[\r\n]/igm, '<br/>'));
			},
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'settingTextSize': (function () {
		var $editField = $('#form_edit_size'), // 获取title 对象
			$selectedCom;

		function mkBind() {
			var $textareaField,
				inputClassList = ['small', 'medium', 'large'],
				oldVal = '';
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				$textareaField = $selectedCom.find('.textarea');
				// set val
				for (var i = 0, len = inputClassList.length; i < len; i++) {
					if ($textareaField.hasClass(inputClassList[i])) {
						oldVal = inputClassList[i];
						break;
					}
				}
				$selectedCom.data('size', oldVal);
				$('#editsize_' + oldVal).attr('checked', 'checked');
				$editField.find('input:radio').unbind('change').bind('change', function () {
					$textareaField.attr('class', 'textarea ' + $(this).val());
					$selectedCom.data('size', $(this).val());
					MKGlobal.addUnsaveCount();
				});
			}
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'settingInputSize': (function () {
		var $editField = $('#form_edit_size'), // 获取title 对象
			$selectedCom;

		function mkBind() {
			var $inputField,
				inputClassList = ['small', 'medium', 'large'],
				oldVal = '';
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				$inputField = $selectedCom.find('input');
				// set val
				for (var i = 0, len = inputClassList.length; i < len; i++) {
					if ($inputField.hasClass(inputClassList[i])) {
						oldVal = inputClassList[i];
						break;
					}
				}

				$selectedCom.data('size', oldVal);
				$('#editsize_' + oldVal).attr('checked', 'checked');

				$editField.find('input:radio').unbind('change').bind('change', function () {
					$inputField.attr('class', 'input ' + $(this).val());
					$selectedCom.data('size', $(this).val());
					MKGlobal.addUnsaveCount();
				});
			}
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'settingRequired': (function () {
		var $editField = $('#form_edit_required'),
			$selectedCom;

		function mkBind() {
			var $requiredField;
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				$requiredField = $selectedCom.find('.title').next('span'); // com_required

				if ($requiredField.text() !== '') {
					$editField.find('input:checkbox').attr('checked', 'checked');
				} else {
					$editField.find('input:checkbox').removeAttr('checked');
				}

				$editField.find('input:checkbox').unbind('change').bind('change', function () {
					var showRequired = '*';
					if (!$(this).attr('checked')) {
						showRequired = '';
					}
					$requiredField.text(showRequired);
					MKGlobal.addUnsaveCount();
				});
			}
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'settingInstruct': (function () {
		var $editField = $('#form_edit_instruct'),
			$selectedCom;

		function mkBind() {
			var $instructField;
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				$instructField = $selectedCom.find('.instruct');
				// set value

				$editField.find('.instruct_textarea').val('');
				if ($instructField.length > 0) {
					$editField.find('.instruct_textarea').val(MKFORM.formUtility.mkHtmlString($instructField.html().replace(/<br(\/)*>/igm, "\n")));
				}
				$editField.find('.instruct_textarea').unbind('input keyup').bind('input keyup', function () {
					var instructStyle = MKGlobal.formScheme.instruction,
						tmpHTML = '';
					if ($.trim($(this).val()) !== '') {

						if ($instructField.length === 0) {
							if (!instructStyle) {
								instructStyle = '';
							}
							$selectedCom.find('.title_field').after('<p class="instruct" style="' + instructStyle + '"></p>');
							$instructField = $selectedCom.find('.instruct');
						}

						tmpHTML =  MKFORM.formUtility.mkStringEval($(this).val().replace(/[\r\n]/igm, '<br/>'));

						$instructField.html(tmpHTML);

					} else {
						$instructField.remove();
						$instructField = $selectedCom.find('.instruct');
					}
					MKGlobal.addUnsaveCount();
				});
				// 添加链接
				$editField.find('.form_edit_addLink').unbind('click').bind('click', function () {
					var $instructField = $editField.find(".instruct_textarea"),
						ins_position = MKFORM.formUtility.getCurrentCursorPosition($instructField),
						__val = $instructField.val();

					$instructField.val(__val.substr(0,ins_position)+" [链接文字](http://www.mikecrm.com) "+__val.substr(ins_position)).trigger("keyup");
				});
			}
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'settingSubTitle': (function () {
		// 分割线的标题就都靠你了！
		// .subtitle
		var $editField = $('#form_edit_instruct'),
			$selectedCom;

		function mkBind() {
			var $subtitleField;
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				$subtitleField = $selectedCom.find('.subtitle');
				// set value

				$editField.find('.instruct_textarea').val(MKFORM.formUtility.mkHtmlString($subtitleField.html().replace(/<br(\/)*>/igm, "\n")));

				$editField.find('.instruct_textarea').unbind('input keyup').bind('input keyup', function () {
					$subtitleField.html(MKFORM.formUtility.mkStringEval($(this).val().replace(/[\r\n]/igm, '<br/>')));
					MKGlobal.addUnsaveCount();
				});

				// 添加链接
				$editField.find('.form_edit_addLink').unbind('click').bind('click', function () {
					var $instructField = $editField.find(".instruct_textarea"),
						ins_position = MKFORM.formUtility.getCurrentCursorPosition($instructField),
						__val = $instructField.val();

					$instructField.val(__val.substr(0,ins_position)+" [链接文字](http://www.mikecrm.com) "+__val.substr(ins_position)).trigger("keyup");
				});
			}
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'settingSubTitleAlign':(function(){
		// 分割线、 图片的说明文字的居中
		var $editField = $('#form_edit_textalign'),
			$selectedCom;

		function mkBind(){
			var $subtitleField;
			if(MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				$subtitleField = $selectedCom.find('.subtitle');
				// set val
				var currentAlign = $subtitleField.css('text-align');
				$editField.find('#textalignstyle_'+currentAlign).attr('checked','checked');

				$editField.find('input[type="radio"]').unbind('click').bind('click',function(){
					var selectedAlign = $(this).val();
					$subtitleField.css('text-align',selectedAlign);
					MKGlobal.addUnsaveCount();
				});
			}
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'settingPicture': (function(){
		// 图片组件的图片上传
		var $editField = $('#form_edit_picture'),
			$selectedCom;

		function checkimg($ui){
			$ui.error(function () {
				$(this).hide();
				$ui.empty();
			});
		}

		function mkBind(){
			var $picField;
			if(MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				$picField = $selectedCom.find('.title_field');
				// set val
				$editField.find('.upload_file p').removeAttr('style').text('请选择小于2M的文件进行上传');
				// 上传图片的设置

				MKFORM.formUtility.formImgUpload($selectedCom.find('.in_pic_upload'),(function(selectedCom){
					var $_selectedCom = $(selectedCom),
						$_picField = $_selectedCom.find('.title_field');
					return function (e, data) {
						var imgPath = data.result.data.url.replace(/[\\]/g, '/'),$img;
						// console.log(selectedCom);
						// console.log($_picField);
						if($_picField.find('img').length>0){
							// updatePicture
							$img = $_picField.find('img');
							checkimg($img);
							$img.attr('src',MKFORM.localHost + imgPath);
						} else {
							$img = $('<img>').attr('src',MKFORM.localHost + imgPath).css('width','100%');
							checkimg($img);
							$picField.empty().append($img);
						}
						MKGlobal.addUnsaveCount();
					};
				})('#' + MKFORM.currentComponent));

				// 绑定上传部分事件
				$editField.find('.input_file').fileupload({
					dataType: "json",
					url: 'handler/handleUploadFormPicture.php',
					drop: function (e) {
						return false;
					},
					add: function (e, data) {
						$selectedCom.find('.in_pic_upload').fileupload('add', {
							files: data.files
						});
					}
				});

				

			}
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'settingTextType': (function () {
		var $editField = $('#form_edit_texttype'),
			$selectedCom;

		function mkBind() {
			var currentType;
			// -- 判断当前的组件的类型，进行渲染啊，更换啊，等等等的
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				currentType = $selectedCom.attr('name');
				if (currentType === 'id_singleLine') {
					$('#textfieldstyle_single').attr('checked', 'checked');
				} else if (currentType === 'id_multiple') {
					$('#textfieldstyle_multi').attr('checked', 'checked');
				}
			}

			$editField.find('input:radio').unbind('change').bind('change', function () {
				// ---
				var type = $(this).attr('id'),
					changeMap = {
						'textfieldstyle_multi': {
							'name': 'id_multiple',
							'componentType': '<textarea class="textarea %%SIZE%%" disabled="disabled"></textarea>'
						},
						'textfieldstyle_single': {
							'name': 'id_singleLine',
							'componentType': '<input type="text" class="input %%SIZE%%" disabled="disabled"/>'
						}
					},
					newInfo;

				newInfo = changeMap[type];

				$selectedCom.attr('name', newInfo.name).find('div').not('.deleteButton').html(newInfo.componentType.replace('%%SIZE%%', $selectedCom.data('size')));
				MKGlobal.addUnsaveCount();
				renderFormComponent($selectedCom);

			});

		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'settingSelectField': (function () {
		// 选项设置
		var selectItemList = [],
			$editField = $('#form_edit_selectset'),
			$selectedCom;

		function _addLine() {
			$editField.find('.addButton').unbind('click').bind('click', function () {
				// add Line ...
				var optionTemplate = '<option>选项</option>',
					listNum = selectItemList.length,
					editTemplate = '<li class="editselect_item" lineNum="' + listNum + '"><input class="editstatus" type="radio" name="select_' + $selectedCom.attr('id') + '"><input type="text" class="edittext input" value="选项"><p class="removeButton"></p></li>';

				$(optionTemplate).attr({
					'name': listNum,
					'listfield': listNum
				}).appendTo($selectedCom.find('select'));

				var $editLine = $(editTemplate);

				$editField.find('.editselect_item').last().after($editLine);
				$editLine.find('.edittext').select().focus();
				$editField.find('.addButton').remove();
				$editField.find('.editselect_item').last().append('<p class="addButton"></p>');

				selectItemList.push('选项');
				MKGlobal.addUnsaveCount();
				// ---
				_removeLine();
				_addLine();
				_editLine();
				$(".formBuilder_edit").getNiceScroll().resize();
			});
		}

		function _removeLine() {
			$editField.find('.removeButton').unbind('click').bind('click', function () {
				// remove 自己
				var num = $(this).parent().attr('lineNum'),
					$corElem = $selectedCom.find('option[listField="' + $(this).parent().attr('lineNum') + '"]');
				if ($editField.find('.editselect_item').length > 1) {
					var currentNum = $(this).attr('lineNum');
					delete selectItemList[currentNum];
					$corElem.remove();
					$(this).parent().remove();
					$editField.find('.addButton').remove();
					$editField.find('.editselect_item').last().append('<p class="addButton"></p>');
					if ($editField.find('.editstatus[checked="checked"]').length === 0) {
						$editField.find('.editstatus').first().attr('checked', 'checked');
					}
					_addLine();
				} else {
					$(this).siblings('.edittext').val('');
					$corElem.text('');
					selectItemList[num] = '';
				}
				if (num == "-1") {
					$selectedCom.find('select').attr('defaultTip',false);
				}
				MKGlobal.addUnsaveCount();
				$(".formBuilder_edit").getNiceScroll().resize();
			});
		}

		function _editLine() {
			$editField.find('.edittext').unbind('input keyup').bind('input keyup', function (e) {
				var num = $(this).parent().attr('lineNum'),
					$corElem = $selectedCom.find('option[listField="' + $(this).parent().attr('lineNum') + '"]'),
					keyCode = e.keyCode || e.which;

				$corElem.text($(this).val());
				selectItemList[num] = $(this).val();
				MKGlobal.addUnsaveCount();

				if($(this).parent().find('.addButton').length > 0){
					if(keyCode == 13){
						$(this).parent().find('.addButton').trigger('click');
					}
				}
			});
			$editField.find('.editstatus').unbind('change').bind('change', function () {
				var $corElem = $selectedCom.find('option[listField="' + $(this).parent().attr('lineNum') + '"]');
				$corElem.attr('selected', 'selected').siblings().removeAttr('selected');
				MKGlobal.addUnsaveCount();
			});
		}

		function _managerOther() {
			$editField.find('.removeOtherButton').unbind('click').bind('click', function () {
				//	$selectedCom.find('.other').remove();
				$selectedCom.find('select').attr('other','false');
				$editField.find('.other-input').html('<span class="add-other-select">添加其他选项</span>');
				_managerOther();
				MKGlobal.addUnsaveCount();
			});

			$editField.find('.add-other-select').unbind('click').bind('click', function () {
				$selectedCom.find('select').attr('other','true');

				$editField.find('.other-input').html('<span>其它:</span><input type="text" class="text input" value="" disabled="disabled"><p class="removeOtherButton"></p>');
				_managerOther();
				MKGlobal.addUnsaveCount();
			});
		}

		function mkBind() {
			var tempListHTML = '';
			selectItemList = [];
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				// set selectItemList
				if ($selectedCom.find('option[selected="selected"]').length === 0) {
					$selectedCom.find('option').first().attr('selected', 'selected');
				}
				$selectedCom.find('option').each(function (i) {
					if ($(this).attr("name") == "-1") {
						$selectedCom.find('select').attr('defaultTip',true);
						var tmpVal = $(this).attr('listField', "-1").text();
						tempListHTML += '<li class="editselect_item" lineNum="-1"><input class="editstatus" type="radio" name="select_' + $selectedCom.attr('id') + '" ' + ($(this).attr('selected') ? 'checked="checked"' : '') + '><input type="text" class="edittext input" value="' + tmpVal + '" readonly="readonly"><p class="removeButton"></p></li>';
					}else{
						var listNum = selectItemList.length,
							tmpVal = $(this).attr('listField', listNum).text();
						selectItemList.push(tmpVal);
						tempListHTML += '<li class="editselect_item" lineNum="' + listNum + '"><input class="editstatus" type="radio" name="select_' + $selectedCom.attr('id') + '" ' + ($(this).attr('selected') ? 'checked="checked"' : '') + '><input type="text" class="edittext input" value="' + tmpVal + '"><p class="removeButton"></p></li>';						
					}
				});

				if ($selectedCom.find('select').attr('other') == 'true') {
					tempListHTML += '<li class="other-input"><span>其它:</span><input type="text" class="text input" value="" disabled="disabled"><p class="removeOtherButton"></p></li>';
				} else {
					tempListHTML += '<li class="other-input"><span class="add-other-select">添加其他选项</span></li>';
				}

				$editField.find('.editselect_list').empty().append(tempListHTML);
				$editField.find('.editselect_item').last().append('<p class="addButton"></p>');


				$selectedCom.unbind('getItemList').bind('getItemList', function (event, list) {
					list.dataInfo = selectItemList;
					list.hasOther = ($selectedCom.find('select').attr('other')=='true');
					list.defaultTip = ($selectedCom.find('select').attr('defaultTip')=='true');
				});

				// -- event bind
				_removeLine();
				_addLine();
				_editLine();
				_managerOther();
			}
		}

		return {
			bind: function () {
				return mkBind();
			},
			getItemList: function () {
				return selectItemList;
			}
		};
	})(),
	'settingCheckboxField': (function () {
		// 多选框设置
		var selectItemList = [],
			$editField = $('#form_edit_checkboxset'),
			$selectedCom, checkboxName;

		function _addLine() {
			$editField.find('.addButton').unbind('click').bind('click', function () {
				// add Line ...
				var optionTemplate = '<li class="optionsLine medium"><input type="checkbox" name="' + checkboxName + '" value="0" disabled="disabled"><label class="optionValue">选项</label></li>',
					listNum = selectItemList.length,
					editTemplate = '<li class="editcheckbox_item" lineNum="' + listNum + '"><input class="editstatus" type="checkbox" name="checkbox_' + $selectedCom.attr('id') + '"><input type="text" class="edittext input" value="选项"><p class="removeButton"></p></li>';

				$(optionTemplate).insertAfter($selectedCom.find('.optionsLine').not('.other').last()).attr('listfield', listNum).find('input:checkbox').attr({
					'name': checkboxName,
					'value': listNum
				});

				var $editLine = $(editTemplate);
				$editField.find('.editcheckbox_item').last().after($editLine);
				$editLine.find('.edittext').select().focus();
				$editField.find('.addButton').remove();
				$editField.find('.editcheckbox_item').last().append('<p class="addButton"></p>');

				selectItemList.push('选项');
				MKGlobal.addUnsaveCount();
				// ---
				_removeLine();
				_addLine();
				_editLine();
				$(".formBuilder_edit").getNiceScroll().resize();
			});
		}

		function _removeLine() {
			$editField.find('.removeButton').unbind('click').bind('click', function () {
				// remove 自己
				var num = $(this).parent().attr('lineNum'),
					$corElem = $selectedCom.find('.optionsLine[listField="' + $(this).parent().attr('lineNum') + '"]');
				if ($editField.find('.editcheckbox_item').length > 1) {
					var currentNum = $(this).attr('lineNum');
					delete selectItemList[currentNum];
					$corElem.remove();
					$(this).parent().remove();
					$editField.find('.addButton').remove();
					$editField.find('.editcheckbox_item').last().append('<p class="addButton"></p>');

					_addLine();
				} else {
					$(this).siblings('.edittext').val('');
					$corElem.find('.optionValue').text('');
					selectItemList[num] = '';
				}
				MKGlobal.addUnsaveCount();
				$(".formBuilder_edit").getNiceScroll().resize();
			});
		}

		function _editLine() {
			$editField.find('.edittext').unbind('input keyup').bind('input keyup', function (e) {
				var num = $(this).parent().attr('lineNum'),
					$corElem = $selectedCom.find('.optionsLine[listField="' + $(this).parent().attr('lineNum') + '"]'),
					keyCode = e.keyCode || e.which;
				$corElem.find('.optionValue').text($(this).val());
				selectItemList[num] = $(this).val();
				MKGlobal.addUnsaveCount();
				if($(this).parent().find('.addButton').length > 0){
					if(keyCode == 13){
						$(this).parent().find('.addButton').trigger('click');
					}
				}
			});
			$editField.find('.editstatus').unbind('change').bind('change', function () {
				var $corElem = $selectedCom.find('.optionsLine[listField="' + $(this).parent().attr('lineNum') + '"]');
				$corElem.find('input:checkbox').attr('checked', ($(this).attr('checked') === 'checked'));
				MKGlobal.addUnsaveCount();
			});
		}

		function _managerOther() {
			$editField.find('.removeOtherButton').unbind('click').bind('click', function () {
				$selectedCom.find('.other').remove();
				$editField.find('.other-input').html('<span class="add-other-select">添加其他选项</span>');
				_managerOther();
				MKGlobal.addUnsaveCount();
			});

			$editField.find('.add-other-select').unbind('click').bind('click', function () {
				$selectedCom.find('.optionGarden').append('<li class="optionsLine medium other"><input type="checkbox" name="' + checkboxName + '" disabled="disabled" >' + '其它:<input type="text" class="input medium" disabled="disabled"/>' + '</li>');

				$editField.find('.other-input').html('<span>其它:</span><input type="text" class="text input" value="" disabled="disabled"><p class="removeOtherButton"></p>');
				_managerOther();
				MKGlobal.addUnsaveCount();
			});
		}

		function mkBind() {
			var tempListHTML = '';
			selectItemList = [];
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				// set selectItemList
				checkboxName = $selectedCom.find('.optionsLine').first().find('input:checkbox').attr('name');
				$selectedCom.find('.optionsLine:not(.other)').each(function (i) {
					var listNum = selectItemList.length,
						tmpVal = $(this).attr('listField', listNum).find('.optionValue').text();
					selectItemList.push(tmpVal);
					tempListHTML += '<li class="editcheckbox_item" lineNum="' + listNum + '"><input class="editstatus" type="checkbox" name="checkbox_' + $selectedCom.attr('id') + '" ' + ($(this).attr('checked') ? 'checked="checked"' : '') + '><input type="text" class="edittext input" value="' + tmpVal + '"><p class="removeButton"></p></li>';
				});
				if ($selectedCom.find('.other').length > 0) {
					tempListHTML += '<li class="other-input"><span>其它:</span><input type="text" class="text input" value="" disabled="disabled"><p class="removeOtherButton"></p></li>';
				} else {
					tempListHTML += '<li class="other-input"><span class="add-other-select">添加其他选项</span></li>';
				}


				$editField.find('.editcheckbox_list').empty().append(tempListHTML);
				$editField.find('.editcheckbox_item').last().append('<p class="addButton"></p>');

				$selectedCom.unbind('getItemList').bind('getItemList', function (event, list) {
					list.dataInfo = selectItemList;
					list.hasOther = ($selectedCom.find('.other').length > 0);
					list.defaultTip = ($selectedCom.find('.optionGarden').attr('defaultTip')=='true');
				});

				_removeLine();
				_addLine();
				_editLine();
				_managerOther();
			}
		}

		return {
			bind: function () {
				return mkBind();
			},
			getItemList: function () {
				return selectItemList;
			}
		};
	})(),
	'settingRadioField': (function () {
		var selectItemList = [],
			$editField = $('#form_edit_radioset'),
			$selectedCom, radioName;

		function _addLine() {
			$editField.find('.addButton').unbind('click').bind('click', function () {
				// add Line ...
				var optionTemplate = '<li class="optionsLine medium"><input type="radio" name="' + radioName + '" value="0"><label class="optionValue">选项</label></li>',
					listNum = selectItemList.length,
					editTemplate = '<li class="editradio_item" lineNum="' + listNum + '"><input class="editstatus" type="radio" name="radio_' + $selectedCom.attr('id') + '"><input type="text" class="edittext input" value="选项"><p class="removeButton"></p></li>';

				$(optionTemplate).insertAfter($selectedCom.find('.optionsLine').not('.other').last()).attr('listfield', listNum).find('input:radio').attr({
					'name': radioName,
					'value': listNum
				});
				var $editLine = $(editTemplate);
				$editField.find('.editradio_item').last().after($editLine);
				$editLine.find('.edittext').select().focus();
				$editField.find('.addButton').remove();
				$editField.find('.editradio_item').last().append('<p class="addButton"></p>');

				selectItemList.push('选项');
				MKGlobal.addUnsaveCount();
				// ---
				_removeLine();
				_addLine();
				_editLine();
				$(".formBuilder_edit").getNiceScroll().resize();
			});
		}

		function _removeLine() {
			$editField.find('.removeButton').unbind('click').bind('click', function () {
				// remove 自己
				var num = $(this).parent().attr('lineNum'),
					$corElem = $selectedCom.find('.optionsLine[listField="' + $(this).parent().attr('lineNum') + '"]');
				if ($editField.find('.editradio_item').length > 1) {
					var currentNum = $(this).attr('lineNum');
					delete selectItemList[currentNum];
					$corElem.remove();
					$(this).parent().remove();
					$editField.find('.addButton').remove();
					$editField.find('.editradio_item').last().append('<p class="addButton"></p>');
					_addLine();
				} else {
					$(this).siblings('.edittext').val('');
					$corElem.find('.optionValue').text('');
					selectItemList[num] = '';
				}
				MKGlobal.addUnsaveCount();
				$(".formBuilder_edit").getNiceScroll().resize();
			});
		}

		function _editLine() {
			$editField.find('.edittext').unbind('input keyup').bind('input keyup', function (e) {
				var num = $(this).parent().attr('lineNum'),
					$corElem = $selectedCom.find('.optionsLine[listField="' + $(this).parent().attr('lineNum') + '"]'),
					keyCode = e.keyCode || e.which;
				$corElem.find('.optionValue').text($(this).val());
				selectItemList[num] = $(this).val();
				MKGlobal.addUnsaveCount();
				if($(this).parent().find('.addButton').length > 0){
					if(keyCode == 13){
						$(this).parent().find('.addButton').trigger('click');
					}
				}
			});
			$editField.find('.editstatus').unbind('change').bind('change', function () {
				var $corElem = $selectedCom.find('.optionsLine[listField="' + $(this).parent().attr('lineNum') + '"]');
				$corElem.find('input:radio').attr('checked', 'checked');
				MKGlobal.addUnsaveCount();
			});
		}

		function _managerOther() {
			$editField.find('.removeOtherButton').unbind('click').bind('click', function () {
				$selectedCom.find('.other').remove();
				$editField.find('.other-input').html('<span class="add-other-select">添加其他选项</span>');
				_managerOther();
				MKGlobal.addUnsaveCount();
			});

			$editField.find('.add-other-select').unbind('click').bind('click', function () {
				$selectedCom.find('.optionGarden').append('<li class="optionsLine medium other"><input type="radio" name="' + radioName + '" disabled="disabled">' + '其它:<input type="text" class="input medium" disabled="disabled"/>' + '</li>');

				$editField.find('.other-input').html('<span>其它:</span><input type="text" class="text input" value="" disabled="disabled"><p class="removeOtherButton"></p>');
				_managerOther();
				MKGlobal.addUnsaveCount();
			});
		}


		function mkBind() {
			var tempListHTML = '';
			selectItemList = [];
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				// set selectItemList
				radioName = $selectedCom.find('.optionsLine').first().find('input:radio').attr('name');
				$selectedCom.find('.optionsLine:not(.other)').each(function (i) {
					var listNum = selectItemList.length,
						tmpVal = $(this).attr('listField', listNum).find('.optionValue').text();
					selectItemList.push(tmpVal);
					tempListHTML += '<li class="editradio_item" lineNum="' + listNum + '"><input class="editstatus" type="radio" name="radio_' + $selectedCom.attr('id') + '" ' + ($(this).attr('checked') ? 'checked="checked"' : '') + '><input type="text" class="edittext input" value="' + tmpVal + '"><p class="removeButton"></p></li>';
				});

				if ($selectedCom.find('.other').length > 0) {
					tempListHTML += '<li class="other-input"><span>其它:</span><input type="text" class="text input" value="" disabled="disabled"><p class="removeOtherButton"></p></li>';
				} else {
					tempListHTML += '<li class="other-input"><span class="add-other-select">添加其他选项</span></li>';
				}

				$editField.find('.editradio_list').empty().append(tempListHTML);
				$editField.find('.editradio_item').last().append('<p class="addButton"></p>');

				$selectedCom.unbind('getItemList').bind('getItemList', function (event, list) {
					list.dataInfo = selectItemList;
					list.hasOther = ($selectedCom.find('.other').length > 0);
					list.defaultTip = ($selectedCom.find('.optionGarden').attr('defaultTip')=='true');
				});

				_removeLine();
				_addLine();
				_editLine();
				_managerOther();
			}
		}

		return {
			bind: function () {
				return mkBind();
			},
			getItemList: function () {
				return selectItemList;
			}
		};
	})(),
	'settingGenderField': (function () {
		var selectItemList = [],
			$editField = $('#form_edit_radioset'),
			$selectedCom, radioName;

		function _editLine() {
			$editField.find('.edittext').unbind('input keyup').bind('input keyup', function () {
				var num = $(this).parent().attr('lineNum'),
					$corElem = $selectedCom.find('.optionsLine[listField="' + $(this).parent().attr('lineNum') + '"]');
				$corElem.find('.optionValue').text($(this).val());
				selectItemList[num] = $(this).val();
			});
			$editField.find('.editstatus').unbind('change').bind('change', function () {
				var $corElem = $selectedCom.find('.optionsLine[listField="' + $(this).parent().attr('lineNum') + '"]');
				$corElem.find('input:radio').attr('checked', 'checked');
				MKGlobal.addUnsaveCount();
			});
		}

		function mkBind() {
			var tempListHTML = '';
			selectItemList = [];
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				// set selectItemList
				radioName = $selectedCom.find('.optionsLine').first().attr('name');
				$selectedCom.find('.optionsLine:not(.other)').each(function (i) {
					var listNum = selectItemList.length,
						tmpVal = $(this).attr('listField', listNum).find('.optionValue').text();
					selectItemList.push(tmpVal);
					tempListHTML += '<li class="editradio_item" lineNum="' + listNum + '"><input class="editstatus" type="radio" name="radio_' + $selectedCom.attr('id') + '" ' + ($(this).attr('checked') ? 'checked="checked"' : '') + '><input type="text" class="edittext input" value="' + tmpVal + '"></li>';
				});


				$editField.find('.editradio_list').empty().append(tempListHTML);

				_editLine();
			}
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'settingChooseType': (function () {
		// 这个是选择 选择组件的类型 多选 单选 下拉 互相切换
		// 记得选择完成以后调用重新渲染
		// 记得修改当前的组件的类型
		// 修改后再重新渲染
		var $editField = $('#form_edit_selecttype'),
			$selectedCom,
			_typeMap = {
				'id_checkBox': 'editsize_checkbox',
				'id_radio': 'editsize_radio',
				'id_dropDown': 'editsize_select'
			},
			_map = {
				'id_checkBox': 'checkbox',
				'id_radio': 'radio',
				'id_dropDown': 'select'
			},
			_comMap = {
				'checkbox': 'id_checkBox',
				'radio': 'id_radio',
				'select': 'id_dropDown'
			};

		function generateHTML(type, id, obj,layout) {
			var _r = '',
				_layoutType = '';
			if(layout){
				_layoutType = 'layout-type-'+layout;
			}
			if (type === 'checkbox') {
				$.each(obj.dataInfo, function (k, v) {
					_r += '<li class="optionsLine medium" listfield="' + k + '"><input type="checkbox" name="checkbox' + id + '" value="' + k + '" disabled="true"><label class="optionValue">' + v + '</label></li>';
				});
				if (obj.hasOther) {
					_r += '<li class="optionsLine medium other"><input type="checkbox" name="checkbox' + id + '" disabled="true">其它:<input type="text" class="input medium"></li>';
				}
				_r = '<ul class="optionGarden ui-sortable '+_layoutType+'" defaultTip="'+(obj.defaultTip||false)+'">' + _r + '</ul><div class="clearB"></div>';
			} else if (type === 'radio') {
				$.each(obj.dataInfo, function (k, v) {
					_r += '<li class="optionsLine medium" listfield="' + k + '"><input type="radio" name="radio' + id + '" value="' + k + '" disabled="true"><label class="optionValue">' + v + '</label></li>';
				});
				if (obj.hasOther) {
					_r += '<li class="optionsLine medium other"><input type="radio" name="radio' + id + '" disabled="true">其它:<input type="text" class="input medium"></li>';
				}
				_r = '<ul class="optionGarden ui-sortable '+_layoutType+'" defaultTip="'+(obj.defaultTip||false)+'">' + _r + '</ul><div class="clearB"></div>';
			} else if (type === 'select') {
				$.each(obj.dataInfo, function (k, v) {
					_r += '<option name="' + k + '">' + v + '</option>';
				});
				if (obj.defaultTip) {
					_r = '<select class="medium" disabled="true" other="'+(obj.hasOther||false)+'" defaultTip="'+(obj.defaultTip||false)+'"><option name="-1">请选择</option>' + _r + '</select>';
				}else{
					_r = '<select class="medium" disabled="true" other="'+(obj.hasOther||false)+'" defaultTip="'+(obj.defaultTip||false)+'">' + _r + '</select>';
				}
			}
			return _r;
		}

		function mkBind() {
			var currentType,
				currentId_Num;
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				currentId_Num = $selectedCom.attr('id').replace('com', '');
				currentType = _typeMap[$selectedCom.attr('name')];
				// show current state
				$editField.find('#' + currentType).attr('checked', 'checked');

				$editField.find('input:radio').unbind('change').bind('change', function () {
					// ----
					var selectedType = $(this).val(),
						selectVal = {'dataInfo': [], 'hasOther': false, 'defaultTip':false},
						tmpHTML = '';

					$selectedCom.trigger('getItemList', [selectVal]);

					$selectedCom.find('.' + _map[$selectedCom.attr('name')]).attr('class', selectedType).html(generateHTML(selectedType, currentId_Num, selectVal,$selectedCom.data('layoutType')));
					$selectedCom.attr('name', _comMap[selectedType]);
					renderFormComponent($selectedCom);
					addOptionDrag();
					MKGlobal.addUnsaveCount();
				});
			}
		}

		return {
			bind: function () {
				mkBind();
			}
		};
	})(),
	'settingSelectLayout': (function () {
		var $editField = $('#form_edit_selectlayout'),
			$selectedCom,
			typeMap = {
				'single':1,
				'two':2,
				'three':3,
				'four':4
			},
			layoutClass = "layout-type";

		function mkBind(){
			var _layout = "single";
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				// --- set val
				$editField.find('input:radio').removeAttr('checked');
				if($selectedCom.data('layoutType')){
					_layout = $selectedCom.data('layoutType');
				}
				$selectedCom.data('layoutType',_layout);
				$editField.find('#editlayout_' + _layout).attr('checked', 'checked');
				$editField.find('input:radio').unbind('change').bind('change', function () {
					var selectedType = $(this).val(),
						$optionGarden = $selectedCom.find('.optionGarden');
					$selectedCom.data('layoutType',selectedType);
					$optionGarden.attr('class','optionGarden ui-sortable '+layoutClass+'-'+selectedType);
					$optionGarden.find('.clearB').remove();
					if(typeMap[selectedType] !== 1){
						var tmpList = [];
						$optionGarden.find('.optionsLine').each(function (i){
						// 
							if((i+1)%(typeMap[selectedType]) === 0 && i !== 0){
								// $(this).after('<li class="clearB"></li>');
								tmpList.push($(this));
							}
						});
						$.each(tmpList, function(k,v){
							v.after('<li class="clearB"></li>');
						});
					}
					MKGlobal.addUnsaveCount();
				});
			}
		}
		return {
			bind: function() {
				mkBind();
			}
		};
	})(),
	'settingShoppingField': (function () {
		var $editField = $('#form_edit_shoppinglist'),
			$selectedCom,
			shoppingInfo,
			shoppingItemTemplate = '<div class="pic_place"><img src="%%PIC%%"/></div><div class="text_place"><a class="item_name" a_link="%%LINK%%">%%NAME%%</a><p class="item_price">%%PRICE%%</p><p class="item_select"><span class="remove">-</span><input class="itemnum" value="0"><span class="add">+</span></p></div>',
			$editItemTemplate = $editField.find('.meta').clone();

		$editItemTemplate.removeClass('meta').find('.shoppingitem_preview').hide();

		function _checkClearB(){
			$selectedCom.find('.shopping-item').not('.empty').each(function(i){
				if(i%3 === 0){
					$(this).addClass('clearB');
				} else {
					$(this).removeClass('clearB');
				}
			});
		}

		function _addLine() {
			$editField.find('.add_shopping_item').unbind('click').bind('click', function () {
				// -- add New line
				var $tmpEditField = $editItemTemplate.clone();

				$tmpEditField.find('.shoppingitem_edit').attr('newItem', true);
				$tmpEditField.find('.defaultimg').show();
				$tmpEditField.insertBefore($editField.find('.addShopping_item'));

				_editLine();
				$(".formBuilder_edit").getNiceScroll().resize();
				MKGlobal.addUnsaveCount();
			});
		}

		function _editLine() {
			// ... 进行组件的编辑       --- add cancel delete
			$editField.find('.btn_canceledit').unbind('click').bind('click', function () {
				var $_editField = $(this).closest('.shoppingitem_edit'),
					$_deleteBtn = $_editField.closest('.shoppingitem_preview_container').siblings('.deleteitem');
				if ($_editField.attr('newItem')) {
					$(this).closest('.editShopping_item').remove();
				} else {
					$_editField.hide().siblings('.shoppingitem_preview').show();
					$_deleteBtn.show();
				}
				MKGlobal.addUnsaveCount();
			});

			$editField.find('.deleteitem').unbind('click').bind('click', function () {
				var $infoField = $(this).closest('.editShopping_item'),
					siid = $infoField.find('.shoppingitem_edit').attr('siid');
				shoppingInfo.splice(siid, 1);
				$selectedCom.find('.shopping-item[itemid="' + siid + '"]').remove();
				if ($selectedCom.find('.shopping-item').not('.empty').length === 0) {
					$selectedCom.find('.empty').show();
				}
				$infoField.remove();
				$selectedCom.data('shoppingInfo', shoppingInfo);
				MKGlobal.addUnsaveCount();
				_checkClearB();
				$(".formBuilder_edit").getNiceScroll().resize();
				$(".formBuilder_example").getNiceScroll().resize();
			});

			$editField.find('.shoppingitem_preview').unbind('click').bind('click', function () {
				var $_editField = $(this).siblings('.shoppingitem_edit'),
					$_deleteBtn = $_editField.closest('.shoppingitem_preview_container').siblings('.deleteitem');
				$_editField.show();
				$(this).hide();
				$_deleteBtn.hide();
				$(".formBuilder_edit").getNiceScroll().resize();
			});

			MKFORM.formUtility.formButtonImgUpload($editField.find('.input_file'), function (e, data) {
				var imgPath = data.result.data.url.replace(/[\\]/g, '/'),
					$currentField = $(e.target).closest('.editShopping_item');

				$currentField.find('.editimg').attr('src', MKFORM.localHost + imgPath).show().error(function () {
					$(this).hide();
					imgPath = '';
					$currentField.find('.defaultimg').show();
				});
				$currentField.find('.defaultimg').hide();
				$currentField.find('.upload_shopping_file').attr('imgsrc', ((imgPath) ? (MKFORM.localHost + imgPath) : ''));
				MKGlobal.addUnsaveCount();
			});


			$editField.find('.btn_additem').unbind('click').bind('click', function () {
				var $_editField = $(this).closest('.shoppingitem_edit'),
					$_previewField = $_editField.siblings('.shoppingitem_preview'),
					$_deleteBtn = $_editField.closest('.shoppingitem_preview_container').siblings('.deleteitem'),
					tmpObj = {},
					tmpHTML = '', reg = /^(file|gopher|news|nntp|telnet|http|ftp|https|ftps|sftp):\/\//;

				tmpObj["name"] = $_editField.find('.shopping_name input').val().replace(/\"/g, ' ');
				tmpObj["pic"] = $_editField.find('.upload_shopping_file').attr('imgsrc');
				tmpObj["link"] = $_editField.find('.shopping_link input').val();
				tmpObj["describe"] = ($_editField.find('.shopping_describe input').val() || '').replace(/\"/g, ' ');
				tmpObj["price"] = parseFloat(($_editField.find('.shopping_price input').val() || '0').replace(/[^\d^\.]/g, '')).toFixed(2);
				tmpObj["num"] = $_editField.find('.shopping_num input').val().replace(/[^\d]/g, '') || '';

				if ($.trim(tmpObj.link).length > 0) {
					tmpObj["link"] = ( (!reg.test(tmpObj["link"])) ? ("http://" + tmpObj["link"]) : tmpObj["link"] );
				}

				if (tmpObj['num'] == 0) {
					tmpObj["num"] = '';
				}

				if (isNaN(tmpObj['price'])) {
					tmpObj["price"] = 0;
				}

				$_editField.find('.shopping_price input').val(tmpObj['price']);
				// --

				if (tmpObj.name) {
					if (tmpObj.price > 9999) {
						$_editField.find('.errorinfo').show().text('抱歉，暂时还不支持大额的商品。');
						window.setTimeout(function () {
							$_editField.find('.errorinfo').fadeOut(200);
						}, 1200);
					} else {
						var imgFlag = false;
						tmpHTML = shoppingItemTemplate.replace(/(%%(.*?)%%)/igm, function () {
							var tmpVal = '';
							if (arguments[2] === 'PRICE') {
								if (tmpObj[arguments[2].toLowerCase()]) {
									tmpVal = '￥' + tmpObj[arguments[2].toLowerCase()];
								}
								return tmpVal;
							}
							return tmpObj[arguments[2].toLowerCase()] || '';
						});
						if(tmpObj.pic){
							imgFlag = true;
						}
						if ($_editField.attr('newItem')) {
							// 新增一个
							var dataLength = shoppingInfo.length;
							$selectedCom.find('.empty').hide();

							$_editField.removeAttr('newItem');
							$_editField.attr('siid', dataLength);

							$('<li class="shopping-item'+(imgFlag?'':' nopic')+'">' + tmpHTML + '</li>').attr('itemid', dataLength).appendTo($selectedCom.find('.shoppingList'));
							shoppingInfo.push(tmpObj);


						} else {
							// 修改本来的值... 其实就是重新写值
							var $tmpItem = $selectedCom.find('.shopping-item[itemid="' + $_editField.attr('siid') + '"]');
							shoppingInfo[$_editField.attr('siid')] = tmpObj;
							$tmpItem.html(tmpHTML);
							if(!imgFlag){
								$tmpItem.addClass('nopic');
							} else {
								$tmpItem.removeClass('nopic');
							}

						}
						$_previewField.find('.previewinfo').text(tmpObj.name);
						if(imgFlag){
							$_previewField.find('img').attr('src', tmpObj.pic);
						} else {
							$_previewField.find('img').attr('src','images/icon/formDefaultImage.png');
						}
						_checkClearB();

						$selectedCom.data('shoppingInfo', shoppingInfo);
						$_editField.hide();
						$_previewField.show();
						$_deleteBtn.show();
						$(".formBuilder_edit").getNiceScroll().resize();
						$(".formBuilder_example").getNiceScroll().resize();
						MKGlobal.addUnsaveCount();
					}

				} else {
					$_editField.find('.errorinfo').show().text('必须填写商品名称');
					window.setTimeout(function () {
						$_editField.find('.errorinfo').fadeOut(200);
					}, 1200);
				}

			});

		}

		function mkBind() {
			var $tmp;
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				// getList of
				shoppingInfo = $selectedCom.data('shoppingInfo');
				if (!shoppingInfo) {
					shoppingInfo = [];
					$selectedCom.data('shoppingInfo', []);
				}
				$editField.find('.editShopping_item').not('.meta').remove();
				// 通过 shopping Info 来创建 列表
				if (shoppingInfo.length > 0) {
					$.each(shoppingInfo, function (key, val) {
						// TODO shopping
						$tmp = $editItemTemplate.clone();

						$tmp.find('.shoppingitem_edit').attr('siid', key).hide();
						$tmp.find('.deleteitem').show();
						$tmp.find('.shoppingitem_preview').show();
						// --
						$tmp.find('.shopping_name input').val(val.name);
						$tmp.find('.upload_shopping_file').attr('imgsrc', val.pic);

						if (!val.pic) {
							$tmp.find('.defaultimg').show();
						}

						$tmp.find('.editimg').attr('src', val.pic).show().error(function () {
							$(this).hide();
							$tmp.find('.defaultimg').show();
						});


						$tmp.find('.shopping_link input').val(val.link);
						$tmp.find('.shopping_describe input').val(val.describe);
						$tmp.find('.shopping_price input').val(val.price);
						$tmp.find('.shopping_num input').val(val.num);

						$tmp.find('.previewinfo').text(val.name);
						$tmp.find('.previewimg img').attr('src', val.pic||'images/icon/formDefaultImage.png');
						// --

						$tmp.insertBefore($editField.find('.addShopping_item'));
					});
				}

				_addLine();
				_editLine();
			}
		}

		return {
			bind: function () {
				mkBind();
			}
		};
	})(),
	'settingCheckboxSelectLogic': (function () {
		// TODO finish checkbox logic
		var $editField = $('#form_edit_checkboxlogicset'),
			$selectedCom;

		function mkBind() {
			var componentData;
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				componentData = $selectedCom.data('__MGComponentSelect');
				if (!componentData) {
					$selectedCom.data('__MGComponentSelect', {
						enable: false,
						number: '',
						type: 0
					});
					componentData = {
						enable: false,
						number: '',
						type: 0
					};
				}
				// setval
				$editField.find('.errorinfo').hide();
				if (componentData.enable) {
					$editField.find('.select-number-enable').attr('checked', 'checked');
					$editField.find('.edit-select-number').removeAttr('disabled');
					$editField.find('.select-number-type').removeAttr('disabled');
				} else {
					$editField.find('.select-number-enable').removeAttr('checked');
					$editField.find('.edit-select-number').attr('disabled', 'disabled').val('');
					$editField.find('.select-number-type').attr('disabled', 'disabled');
				}
				$editField.find('.select-number-type').find('option').each(function () {
					if (parseInt($(this).val(), 0) === componentData.type) {
						$(this).attr('selected', 'selected');
					} else {
						$(this).removeAttr('selected');
					}
				});
				$editField.find('.edit-select-number').val(componentData.number);

				// event bind
				$editField.find('.select-number-enable').unbind('change').bind('change', function () {
					var typeValue = $(this).attr('checked');
					$selectedCom.data('__MGComponentSelect').enable = (typeValue == 'checked');
					componentData.enable = (typeValue == 'checked');
					if (typeValue == 'checked') {
						$editField.find('.edit-select-number').removeAttr('disabled').val($selectedCom.data('__MGComponentSelect').number).select().focus();
						$editField.find('.select-number-type').removeAttr('disabled');
					} else {
						$editField.find('.edit-select-number').attr('disabled', 'disabled').val('');
						$editField.find('.select-number-type').attr('disabled', 'disabled');
					}

					MKGlobal.addUnsaveCount();
				});
				$editField.find('.select-number-type').unbind('change').bind('change', function () {
					var typeValue = parseInt($(this).val(), 0);
					$selectedCom.data('__MGComponentSelect').type = typeValue;
					componentData.type = typeValue;
					MKGlobal.addUnsaveCount();
				});
				$editField.find('.edit-select-number').unbind('input keyup').bind('input keyup', function () {
					var number = $(this).val().replace(/[^\d]/g, ''),
						check = statusCheck();
					if (!check.status) {
						number = check.num;
					}

					$(this).val(number);
					$selectedCom.data('__MGComponentSelect').number = number;
					componentData.number = number;
					MKGlobal.addUnsaveCount();

				});
			}
		}

		function statusCheck() {
			var choiceInfo = {},
				currentType = $selectedCom.data('__MGComponentSelect').type,
				choiceLength, _rStatus = true, _rNum, _errorInfo = false,
				$numberinput = $editField.find('.edit-select-number');

			$selectedCom.trigger('getItemList', [choiceInfo]);

			choiceLength = choiceInfo.dataInfo.length + ((choiceInfo.hasOther) ? 1 : 0);

			if (parseInt(currentType) === 0) {
				if (choiceLength < parseInt($numberinput.val()) || parseInt($numberinput.val()) <= 0) {
					_rStatus = false;
					_rNum = choiceLength;
					_errorInfo = true;
				} else {
					_rNum = parseInt($numberinput.val().replace(/[^\d]/g, ''));
				}
			} else if (parseInt(currentType) === 1) {
				if (choiceLength < parseInt($numberinput.val()) || parseInt($numberinput.val()) <= 0) {
					_rStatus = false;
					_rNum = 2;
					_errorInfo = true;
				} else {
					_rNum = parseInt($numberinput.val().replace(/[^\d]/g, ''));
				}
			} else if (parseInt(currentType) === 2) {
				if (choiceLength < parseInt($numberinput.val()) || parseInt($numberinput.val()) <= 0) {
					_rStatus = false;
					_rNum = 2;
					_errorInfo = true;
				} else {
					_rNum = parseInt($numberinput.val().replace(/[^\d]/g, ''));
				}
			}

			return {
				status: _rStatus,
				num: _rNum
			};
		}

		return {
			bind: function () {
				mkBind();
			}
		};
	})(),
	'settingDateType': (function () {
		var $editField = $('#form_edit_datetype'),
			$selectedCom;

		function mkBind() {
			var dateType;
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);

				dateType = $selectedCom.find('.date').attr('datetype');

				$editField.find('input[value="' + dateType + '"]').attr('checked', 'checked');

				$editField.find('input:radio').unbind('change').bind('change', function () {
					var dateType = $(this).val();
					$selectedCom.find('.date').attr('datetype', dateType);
					MKGlobal.addUnsaveCount();
				});
			}
		}

		return {
			bind: function () {
				mkBind();
			}
		};
	})(),
	'settingPictureCheckboxField': (function(){
		var selectItemList = [],
			$editField = $('#form_edit_pic_checkboxset'),
			$selectedCom, checkboxName;

		// check clear
		function _checkClearB(){
			$selectedCom.find('.picturecheckbox-item').not('.empty').each(function(i){
				if(i%3 === 0){
					$(this).addClass('clearB');
				} else {
					$(this).removeClass('clearB');
				}
			});
		}

		// eventbind
		function _addLine(){
			$editField.off('click.addLine').on('click.addLine','.addButton',function(){
				// add Line ...
				var option = '<li class="picturecheckbox-item"><div class="piccheckbox_img"></div><div class="piccheckbox_contect"><input type="checkbox" name="' + checkboxName + '" value="0" disabled="disabled"><label class="optionValue">选项</label></div></li>',
					listNum = selectItemList.length,
					editTemplate = '<li class="editpicturecheckbox_item" lineNum="' + listNum + '"><input class="editstatus" type="checkbox" name="piccheckbox_' + $selectedCom.attr('id') + '"><div class="pictextcontect"><div class="imgcontect"><img src="" alt="" class="choicePicture"><div class="upload_choiceimg"><input type="file" size="1" class="uploadimg" name="_FILE_"/> <span class="upload_btn">上传图片</span> </div></div><input type="text" class="imgedittext" value="选项"></div><p class="removeButton"></p></li>';
				$(option).appendTo($selectedCom.find('.pictureCheckboxList')).attr('listfield', listNum).find('input:checkbox').attr({
					'name': checkboxName,
					'value': listNum
				});

				var $editLine = $(editTemplate);
				$editField.find('.editpicturecheckbox_item').last().after($editLine);
				// $editLine.find('.edittext').select().focus();
				$editField.find('.addButton').remove();
				$editField.find('.editpicturecheckbox_item').last().append('<p class="addButton"></p>');
				_checkClearB();
				selectItemList.push({name:'选项',img:''});
				MKGlobal.addUnsaveCount();
				// ---
				_removeLine();
				_addLine();
				_editLine();
				$(".formBuilder_edit").getNiceScroll().resize();
			});
		}

		function _removeLine(){
			$editField.off('click.removeLine').on('click.removeLine','.removeButton',function(){
				// remove 自己
				var $_editField = $(this).closest('.editpicturecheckbox_item'),
					num = $_editField.attr('lineNum'),
					$corElem = $selectedCom.find('.picturecheckbox-item[listField="' + $_editField.attr('lineNum') + '"]'),
					$editLine = $editField.find('.editpicturecheckbox_item');
				if ($editLine.length > 1) {
					var currentNum = $(this).attr('lineNum');
					delete selectItemList[currentNum];
					$corElem.remove();
					$_editField.remove();
					$editField.find('.addButton').remove();
					$editField.find('.editpicturecheckbox_item').last().append('<p class="addButton"></p>');
					_checkClearB();
					_addLine();
				} else {
					$_editField.find('.imgedittext').val('');
					$_editField.find('.upload_btn').show();
					$_editField.find('.imgcontect img').hide();
					$corElem.find('.optionValue').text('');
					$corElem.find('.picc_img').remove();
					$corElem.find('.piccheckbox_img').removeAttr('style');
					selectItemList[num].name = '';
					selectItemList[num].img = '';
				}
				MKGlobal.addUnsaveCount();
				$(".formBuilder_edit").getNiceScroll().resize();
			});
		}

		function _editLine(){
			// text edit
			$editField.off('keyup.editLine').on('keyup.editLine', '.imgedittext', function (e){
				var $_editField = $(this).closest('.editpicturecheckbox_item'),
					num = $_editField.attr('lineNum'),
					$corElem = $selectedCom.find('.picturecheckbox-item[listField="' + num + '"]'),
					value = $.trim($(this).val());
				$corElem.find('.optionValue').text(value);
				selectItemList[num].name = value;
				MKGlobal.addUnsaveCount();
				if($_editField.find('.addButton').length > 0){
					// 最后一行， 按回车创建新的一个
					if(e.which == 13){
						$_editField.find('.addButton').trigger('click');
					}
				}
			});
			// status edit
			$editField.off('change.changeStatus').on('change.changeStatus','.editstatus',function (e){
				var $corElem = $selectedCom.find('.picturecheckbox-item[listField="' + $(this).closest('.editpicturecheckbox_item').attr('lineNum') + '"]');
				$corElem.find('input:checkbox').attr('checked', ($(this).attr('checked') === 'checked'));
				MKGlobal.addUnsaveCount();
			});
			// pic edit
			MKFORM.formUtility.formSelectImgUpload($editField.find('.uploadimg'), function (e, data) {
				var imgPath = data.result.data.url.replace(/[\\]/g, '/'),
					$this = $(e.target),
					$currentField = $this.closest('.editpicturecheckbox_item');
				$this.siblings('.upload_btn').hide();
				// $this.parent().css('background-color','#FFF');
				$currentField.find('.choicePicture').attr('src', MKFORM.localHost + imgPath).show().error(function () {
					$(this).hide();
					imgPath = '';
					$this.siblings('.upload_btn').show();
					// $this.parent().css('background-color','#E4E3E4');
				});
				// $currentField.find('.upload_shopping_file').attr('imgsrc', ((imgPath) ? (MKFORM.localHost + imgPath) : ''));
				var $imgF = $selectedCom.find('.picturecheckbox-item[listField="' + $currentField.attr('linenum') + '"]').find('.piccheckbox_img');
				if($imgF.find('.picc_img').length===0){
					$imgF.show().append('<img class="picc_img"/>');
				}
				selectItemList[$currentField.attr('linenum')].img = MKFORM.localHost + imgPath;
				$imgF.find('.picc_img').attr('src', MKFORM.localHost + imgPath);
				MKGlobal.addUnsaveCount();
			});
		}

		function mkBind(){
			var tempListHTML = [];
			selectItemList = [];

			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				checkboxName = $selectedCom.find('.piccheckbox_contect input[type="checkbox"]').attr('name'); 
				// 显示编辑项目
				var $items = $selectedCom.find('.picturecheckbox-item').not('.empty');
				if($items.length > 0){
					$items.each(function(){
						var listNum = selectItemList.length,
							tmpVal = $(this).attr('listField', listNum).find('.optionValue').text();
						selectItemList.push({
							img: $(this).find('.picc_img').attr('src')||'',
							name: tmpVal
						});
						var $imgInfo = $(this).find('.picc_img'),
							__img = '<img src="" alt="" class="choicePicture"><div class="upload_choiceimg"><input type="file" size="1" class="uploadimg" name="_FILE_"/><span class="upload_btn">上传图片</span>';
						if($imgInfo.length>0){
							__img = '<img src="'+$imgInfo.attr('src')+'" alt="" class="choicePicture" style="display: inline;"><div class="upload_choiceimg"><input type="file" size="1" class="uploadimg" name="_FILE_"/><span class="upload_btn" style="display:none">上传图片</span>';
						}
						tempListHTML.push('<li class="editpicturecheckbox_item" lineNum="' + listNum + '"><input class="editstatus" type="checkbox" name="piccheckbox_' + $selectedCom.attr('id') + '" ' + ($(this).attr('checked') ? 'checked="checked"' : '') + '><div class="pictextcontect"><div class="imgcontect">'+__img+'</div></div><input type="text" class="imgedittext" value="' + tmpVal + '"></div><p class="removeButton"></p>');

					});
					tempListHTML[tempListHTML.length-1] += '<p class="addButton"></p>';
				}

				tempListHTML = tempListHTML.join('</li>');

				// tempListHTML += '<li class="addPictureCheckbox_item"><div class="add_checkbox_item">+</div></li>';
				$editField.find('.editpiccheckbox_list').empty().append(tempListHTML);

				$selectedCom.unbind('getItemList').bind('getItemList', function (event, list) {
					list.dataInfo = selectItemList;
				});

				_addLine();
				_editLine();
				_removeLine();
			}
		}
		return {
			bind: mkBind,
			getItemList: function(){
				return selectItemList;
			}
		};
	})(),
	'settingPictureRadioField': (function(){
		var selectItemList = [],
			$editField = $('#form_edit_pic_radioset'),
			$selectedCom, radioName;

		// check clear
		function _checkClearB(){
			$selectedCom.find('.pictureradio-item').not('.empty').each(function(i){
				if(i%3 === 0){
					$(this).addClass('clearB');
				} else {
					$(this).removeClass('clearB');
				}
			});
		}

		// eventbind
		function _addLine(){
			$editField.off('click.addLine').on('click.addLine','.addButton',function(){
				// add Line ...
				var option = '<li class="pictureradio-item"><div class="picradio_img"></div><div class="picradio_contect"><input type="radio" name="' + radioName + '" value="0" disabled="disabled"><label class="optionValue">选项</label></div></li>',
					listNum = selectItemList.length,
					editTemplate = '<li class="editpictureradio_item" lineNum="' + listNum + '"><input class="editstatus" type="radio" name="picradio_' + $selectedCom.attr('id') + '"><div class="pictextcontect"><div class="imgcontect"><img src="" alt="" class="choicePicture"><div class="upload_choiceimg"><input type="file" size="1" class="uploadimg" name="_FILE_"/> <span class="upload_btn">上传图片</span> </div></div><input type="text" class="imgedittext" value="选项"></div><p class="removeButton"></p></li>';
				$(option).appendTo($selectedCom.find('.pictureRadioList')).attr('listfield', listNum).find('input:radio').attr({
					'name': radioName,
					'value': listNum
				});

				var $editLine = $(editTemplate);
				$editField.find('.editpictureradio_item').last().after($editLine);
				// $editLine.find('.edittext').select().focus();
				$editField.find('.addButton').remove();
				$editField.find('.editpictureradio_item').last().append('<p class="addButton"></p>');
				_checkClearB();
				selectItemList.push({name:'选项',img:''});
				MKGlobal.addUnsaveCount();
				// ---
				_removeLine();
				_addLine();
				_editLine();
				$(".formBuilder_edit").getNiceScroll().resize();
			});
		}

		function _removeLine(){
			$editField.off('click.removeLine').on('click.removeLine','.removeButton',function(){
				// remove 自己
				var $_editField = $(this).closest('.editpictureradio_item'),
					num = $_editField.attr('lineNum'),
					$corElem = $selectedCom.find('.pictureradio-item[listField="' + $_editField.attr('lineNum') + '"]'),
					$editLine = $editField.find('.editpictureradio_item');
				if ($editLine.length > 1) {
					var currentNum = $(this).attr('lineNum');
					delete selectItemList[currentNum];
					$corElem.remove();
					$_editField.remove();
					$editField.find('.addButton').remove();
					$editField.find('.editpictureradio_item').last().append('<p class="addButton"></p>');
					_checkClearB();
					_addLine();
				} else {
					$_editField.find('.imgedittext').val('');
					$_editField.find('.upload_btn').show();
					$_editField.find('.imgcontect img').hide();
					$corElem.find('.optionValue').text('');
					$corElem.find('.picc_img').remove();
					$corElem.find('.picradio_img').removeAttr('style');
					selectItemList[num].name = '';
					selectItemList[num].img = '';
				}
				MKGlobal.addUnsaveCount();
				$(".formBuilder_edit").getNiceScroll().resize();
			});
		}

		function _editLine(){
			// text edit
			$editField.off('keyup.editLine').on('keyup.editLine', '.imgedittext', function (e){
				var $_editField = $(this).closest('.editpictureradio_item'),
					num = $_editField.attr('lineNum'),
					$corElem = $selectedCom.find('.pictureradio-item[listField="' + num + '"]'),
					value = $.trim($(this).val());
				$corElem.find('.optionValue').text(value);
				selectItemList[num].name = value;
				MKGlobal.addUnsaveCount();
				if($_editField.find('.addButton').length > 0){
					// 最后一行， 按回车创建新的一个
					if(e.which == 13){
						$_editField.find('.addButton').trigger('click');
					}
				}
			});
			// status edit
			$editField.off('change.changeStatus').on('change.changeStatus','.editstatus',function (e){
				var $corElem = $selectedCom.find('.pictureradio-item[listField="' + $(this).closest('.editpictureradio_item').attr('lineNum') + '"]');
				$corElem.find('input:radio').attr('checked', ($(this).attr('checked') === 'checked'));
				MKGlobal.addUnsaveCount();
			});
			// pic edit
			MKFORM.formUtility.formSelectImgUpload($editField.find('.uploadimg'), function (e, data) {
				var imgPath = data.result.data.url.replace(/[\\]/g, '/'),
					$this = $(e.target),
					$currentField = $this.closest('.editpictureradio_item');
				$this.siblings('.upload_btn').hide();
				// $this.parent().css('background-color','#FFF');
				$currentField.find('.choicePicture').attr('src', MKFORM.localHost + imgPath).show().error(function () {
					$(this).hide();
					imgPath = '';
					$this.siblings('.upload_btn').show();
					// $this.parent().css('background-color','#E4E3E4');
				});
				// $currentField.find('.upload_shopping_file').attr('imgsrc', ((imgPath) ? (MKFORM.localHost + imgPath) : ''));
				var $imgF = $selectedCom.find('.pictureradio-item[listField="' + $currentField.attr('linenum') + '"]').find('.picradio_img');
				if($imgF.find('.picc_img').length===0){
					$imgF.show().append('<img class="picc_img"/>');
				}
				selectItemList[$currentField.attr('linenum')].img = MKFORM.localHost + imgPath;
				$imgF.find('.picc_img').attr('src', MKFORM.localHost + imgPath);
				MKGlobal.addUnsaveCount();
			});
		}

		function mkBind(){
			var tempListHTML = [];
			selectItemList = [];

			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				radioName = $selectedCom.find('.picradio_contect input[type="radio"]').attr('name'); 
				// 显示编辑项目
				var $items = $selectedCom.find('.pictureradio-item').not('.empty');
				if($items.length > 0){
					$items.each(function(){
						var listNum = selectItemList.length,
							tmpVal = $(this).attr('listField', listNum).find('.optionValue').text();
						selectItemList.push({
							img: $(this).find('.picc_img').attr('src')||'',
							name: tmpVal
						});
						var $imgInfo = $(this).find('.picc_img'),
							__img = '<img src="" alt="" class="choicePicture"><div class="upload_choiceimg"><input type="file" size="1" class="uploadimg" name="_FILE_"/><span class="upload_btn">上传图片</span>';
						if($imgInfo.length>0){
							__img = '<img src="'+$imgInfo.attr('src')+'" alt="" class="choicePicture" style="display: inline;"><div class="upload_choiceimg"><input type="file" size="1" class="uploadimg" name="_FILE_"/><span class="upload_btn" style="display:none">上传图片</span>';
						}
						tempListHTML.push('<li class="editpictureradio_item" lineNum="' + listNum + '"><input class="editstatus" type="radio" name="picradio_' + $selectedCom.attr('id') + '" ' + ($(this).attr('checked') ? 'checked="checked"' : '') + '><div class="pictextcontect"><div class="imgcontect">'+__img+'</div></div><input type="text" class="imgedittext" value="' + tmpVal + '"></div><p class="removeButton"></p>');

					});
					tempListHTML[tempListHTML.length-1] += '<p class="addButton"></p>';
				}

				tempListHTML = tempListHTML.join('</li>');

				// tempListHTML += '<li class="addPictureRadio_item"><div class="add_radio_item">+</div></li>';
				$editField.find('.editpicradio_list').empty().append(tempListHTML);

				$selectedCom.unbind('getItemList').bind('getItemList', function (event, list) {
					list.dataInfo = selectItemList;
				});

				_addLine();
				_editLine();
				_removeLine();
			}
		}
		return {
			bind: mkBind,
			getItemList: function(){
				return selectItemList;
			}
		};
	})(),
	'settingPicChooseType': (function () {
		// 这个是选择 选择组件的类型 多选 单选 下拉 互相切换
		// 记得选择完成以后调用重新渲染
		// 记得修改当前的组件的类型
		// 修改后再重新渲染
		var $editField = $('#form_edit_picselecttype'),
			$selectedCom,
			_typeMap = {
				'id_pictureCheckbox': 'piceditsize_checkbox',
				'id_pictureRadio': 'piceditsize_radio'
			},
			_map = {
				'id_pictureCheckbox': 'checkbox',
				'id_pictureRadio': 'radio'
			},
			_comMap = {
				'checkbox': 'id_pictureCheckbox',
				'radio': 'id_pictureRadio'
			};

		function generateHTML(type, id, obj) {
			var _r = '';
			if (type === 'checkbox') {
				$.each(obj.dataInfo, function (n, v) {
					var _img = '';
					if(v.img){
						_img = '<img class="picc_img" src="'+decodeURIComponent(v.img)+'">';
					}
					_r += '<li class="picturecheckbox-item'+((n%3==0)?' clearB':'')+'" listfield="'+n+'"><div class="piccheckbox_img" '+(v.img?'style="display: block;"':'')+'>'+_img+'</div><div class="piccheckbox_contect"><input type="checkbox" name="picturecheckbox'+id+'" value="'+n+'" disabled="disabled"><label class="optionValue">'+v.name+'</label></div></li>';
				});

				_r = '<ul class="pictureCheckboxList">' + _r + '</ul><div class="clearB"></div>';
			} else if (type === 'radio') {
				$.each(obj.dataInfo, function (n, v) {
					var _img = '';
					if(v.img){
						_img = '<img class="picc_img" src="'+decodeURIComponent(v.img)+'">';
					}
					_r += '<li class="pictureradio-item'+((n%3==0)?' clearB':'')+'" listfield="'+n+'"><div class="picradio_img" '+(v.img?'style="display: block;"':'')+'>'+_img+'</div><div class="picradio_contect"><input type="radio" name="pictureradio'+id+'" value="'+n+'" disabled="disabled"><label class="optionValue">'+v.name+'</label></div></li>';
				});

				_r = '<ul class="pictureCheckboxList">' + _r + '</ul><div class="clearB"></div>';
			}
			return _r;
		}

		function mkBind() {
			var currentType,
				currentId_Num;
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				currentId_Num = $selectedCom.attr('id').replace('com', '');
				currentType = _typeMap[$selectedCom.attr('name')];
				// show current state
				$editField.find('#' + currentType).attr('checked', 'checked');

				$editField.find('input:radio').unbind('change').bind('change', function () {
					// ----
					var selectedType = $(this).val(),
						selectVal = {'dataInfo': [], 'hasOther': false, 'defaultTip':false},
						tmpHTML = '';

					$selectedCom.trigger('getItemList', [selectVal]);

					$selectedCom.find('.picture_' + _map[$selectedCom.attr('name')]).attr('class', 'picture_'+selectedType).html(generateHTML(selectedType, currentId_Num, selectVal));
					$selectedCom.attr('name', _comMap[selectedType]);
					renderFormComponent($selectedCom);
					MKGlobal.addUnsaveCount();
				});
			}
		}

		return {
			bind: function () {
				mkBind();
			}
		};
	})(),
	'settingStarNum': (function () {
		var $editField = $('#form_edit_starnum'),
			$selectedCom;

		function mkBind() {
			var starNum;
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);

				starNum = $selectedCom.find('.starGroup').find('.star').length;

				$editField.find('option[value="' + starNum + '"]').attr('selected', 'selected');

				$editField.find('select').unbind('change').bind('change', function () {
					var tmpHTML = '';

					for (var i = 0, len = ($(this).val() - 0); i < len; i++) {
						tmpHTML += '<span class="star"></span>';
					}

					$selectedCom.find('.starGroup').empty().append(tmpHTML);
					MKGlobal.addUnsaveCount();
				});
			}
		}

		return {
			bind: function () {
				mkBind();
			}
		};
	})(),
	'settingStarType': (function () {
		// TODO 短期 搞不定啊  -- 需要图片 、 图片有了就什么都有了
	})(),
	'settingFileType': (function(){

		var $editField = $('#form_edit_filetype'),
			$selectedCom;

		function mkBind(){
			var $list = $editField.find('.type_select'),
				typedata = '';
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				$list.data('animating',false);
				$editField.find('.type_select_detail_field input').removeAttr('checked');
				typedata = $selectedCom.data('typedata');
				$editField.find('.limit-set-detail').removeAttr('style');
				$('#editlimittype').removeAttr('checked');
				$editField.find('.type_select_expand').removeClass('type_select_expand_to_close').text('展开详细');
				$editField.find('.type_select_detail').removeAttr('style');
				if(typedata){
					$editField.find('.limit-set-detail').show();
					$('#editlimittype').attr('checked','checked');
					$('.detail_name').each(function(i){
						if($.inArray($(this).text(),typedata)>=0){
							$(this).siblings('input').attr('checked','checked');
						}
					});
				}
				selectlistCheck($selectedCom);
				$editField.find('#editlimittype').unbind('click').bind('click',function (e) {
					if($(this).attr('checked')){
						$editField.find('.limit-set-detail').show();
						selectlistCheck($selectedCom);
					} else {
						$editField.find('.limit-set-detail').hide();
						$selectedCom.data('typedata',false);
						$selectedCom.find('.filetype-hint').remove();
						$editField.find('.type_select_detail_field input').removeAttr('checked');
					}
					MKGlobal.addUnsaveCount();
					$('.formBuilder_edit').getNiceScroll().resize();
				});

				$editField.find('.type_select_expand').unbind('click').bind('click',function(e){
					e.stopPropagation();
					if($(this).hasClass('type_select_expand_to_close')){
						$(this).removeClass('type_select_expand_to_close').text('展开详细').parent().siblings('.type_select_detail').hide();
					} else {
						$(this).addClass('type_select_expand_to_close').text('收起').parent().siblings('.type_select_detail').show();
					}
					$('.formBuilder_edit').getNiceScroll().resize();

					return false;
				});

				$editField.find('.type_select_big').unbind('change').bind('change',function (){
					var $typeField = $(this).closest('.type_select_field');
					if($(this).attr('checked')){
						// -- all check
						$typeField.siblings('.type_select_detail').find('.type_select_detail_field input').attr('checked','checked');
					} else {
						$typeField.siblings('.type_select_detail').find('.type_select_detail_field input').removeAttr('checked');
					}
					selectlistCheck($selectedCom);
					MKGlobal.addUnsaveCount();
				});

				$editField.find('.type_select_detail_field input').unbind('change').bind('change',function (){
					// var $typeField = $(this).closest('.type_select_item');
					selectlistCheck($selectedCom);
					MKGlobal.addUnsaveCount();
				});

			}

			function selectlistCheck($ui) {
				var list = [];
				$editField.find('.type_select_item').each(function () {
					var check_detail = [],
						$detailItem = $(this).find('.type_select_detail_item'),
						$desShow = $(this).find('.type_des'),ans = '';

					$detailItem.each(function () {
						var fname = $(this).find('.detail_name').text();
						if ($(this).find('input').attr('checked')) {
							check_detail.push(fname);
							list.push(fname);
						}
					});

					if(check_detail.length === $detailItem.length){
						ans = '全部';
						$(this).find('.type_select_big').attr('checked','checked');
					} else {
						if(check_detail.length>0) {
							ans = '('+check_detail.join(',')+')';
						}
						$(this).find('.type_select_big').removeAttr('checked');
					}

					$desShow.text(ans);

				});


				if(list.length === 0){
					list = false;
					$ui.find('.filetype-hint').remove();
				} else {
					if($ui.find('.filetype-hint').length > 0){
						$ui.find('.filetype-hint').text('支持 '+list.join(', '));
					} else {
						$('<p class="filetype-hint" style="'+MKGlobal.formScheme.instruction+'">支持 '+list.join(', ')+'</p>').insertBefore($ui.find('.upload_file').parent());
					}

				}
				$ui.data('typedata',list);
			}
		}

		return {
			bind: function(){
				mkBind();
			}
		};
	})(),
	'settingPictureLink': (function(){
		// -- return obj
		var $editField = $('#form_edit_picture_link'), // 获取title 对象
			$selectedCom,
			$pictureField;

		// 对当前的组件进行事件的绑定
		function mkBind() {
			var pictureLink;
			if (MKFORM.currentComponent) {
				$selectedCom = $('#' + MKFORM.currentComponent);
				$pictureField = $selectedCom.find('.title_field');
				var link = $pictureField.attr('img-link');
				if(link == 'false'){
					link = '';
				}
				pictureLink = decodeURIComponent(link||'');
				$editField.find('.edit_input').val(pictureLink).unbind('input keyup').bind('input keyup',function () {
					$pictureField.attr('img-link',encodeURIComponent($.trim($(this).val()))); // 回车转义保存
					MKGlobal.addUnsaveCount();
				}).unbind('paste').bind('paste',function(){
						$pictureField.attr('img-link',encodeURIComponent($.trim($(this).val()))); // 回车转义保存
						MKGlobal.addUnsaveCount();
					});
			}
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'settingHoverDelete': (function (){
		var $hoverCom;
		function mkBind(id){
			$hoverCom = $('#' + id);
			if($hoverCom.find('.deleteButton').length < 1){
				$('.deleteButton').each(function(){
					if(!$(this).parent().hasClass('clicked')){
						$(this).remove();
					}
				});
				$hoverCom.prepend('<div class="deleteButton"></div>');

				$hoverCom.find('.deleteButton').unbind('click').bind('click', function () {
					var $componentField = $('.formBuilder_interim_edit').eq(1);
					MKFORM.currentComponent = '';
					$hoverCom.remove();
					// 只有当第3个的时候切回第二栏的组件选择
					if ($('.formBuilder_interim_edit_active').index() === 2) {
						$componentField.trigger('click');
					}

					renderFormComponent();
					var $componentList = $('.form_component').children('.ui-draggable');
					MKFORM.formUtility.formContactCheck($componentList);
					MKFORM.formUtility.formPaymentCheck($componentList);
					MKFORM.formUtility.formLimitedItemCheck($componentList);
					MKGlobal.addUnsaveCount();
				});
			}
		}


		return {
			bind: function(id){
				mkBind(id);
			}
		};
	})(),
	'settingDeleteSelf': (function () {
		// ---del
		var $selectCom;

		function mkBind() {
			if (MKFORM.currentComponent) {
				$selectCom = $('#' + MKFORM.currentComponent);
				$('.deleteButton').remove();
				$selectCom.prepend('<div class="deleteButton"></div>');
				$selectCom.find('.deleteButton').unbind('click').bind('click', function () {
					var $componentField = $('.formBuilder_interim_edit').eq(1);
					MKFORM.currentComponent = '';
					$selectCom.remove();
					// 只有当第3个的时候切回第二栏的组件选择
					if ($('.formBuilder_interim_edit_active').index() === 2) {
						$componentField.trigger('click');
					}

					renderFormComponent();
					var $componentList = $('.form_component').children('.ui-draggable');
					MKFORM.formUtility.formContactCheck($componentList);
					MKFORM.formUtility.formPaymentCheck($componentList);
					MKFORM.formUtility.formLimitedItemCheck($componentList);
					MKGlobal.addUnsaveCount();
				});
			}
		}

		return {
			bind: function () {
				mkBind();
			}
		};
	})()

};

MKFORM.formSetting = {
	// 集成了 渲染 、 事件绑定
	'formTitle': (function () {
		var $titleField = $('.formName_input');

		function mkBind() {
			// --
			var $formTitleField = $('.formBuilder_main').find('.title>h2');
			$titleField.val($formTitleField.text()).unbind('input keyup').bind('input keyup', function () {
				var currentVal = $(this).val();
				$formTitleField.text(currentVal);
				MKGlobal.addUnsaveCount();
			});
		}

		return {
			bind: function () {
				mkBind();
			}
		};
	})(),
	'formSubTitle': (function () {
		var $subtitleField = $('.formInstruct_input');

		function mkBind() {
			var $formSubtitleField = $('.formBuilder_main').find('.title>div');
			$subtitleField.val($formSubtitleField.text()).unbind('input keyup').bind('input keyup', function () {
				var currentVal = $(this).val();
				$formSubtitleField.text(currentVal);
				MKGlobal.addUnsaveCount();
			});
		}

		return {
			bind: function () {
				mkBind();
			}
		};
	})(),
	'formChangeLogo': (function () {
		function mkBind() {
			var $settingField = $('.sdi_logobackgroundImg'),
				$logofield = $('#face'),
				$trigger = $('#form_changelogo');
			function showImg(){
				if(MKGlobal.formInfo.logoAvailable === true){
					$logofield.attr('src', MKGlobal.formSchemeStruct.logo).show();
					$('.formLogo').show();
					$('.title').addClass('haslogo');
					$logofield.error(function () {
						$('.formLogo').hide();
						$(this).hide();
						$('.title').removeClass('haslogo');
					});
				} else {
					$('.title').removeClass('haslogo');
					$('.formLogo').hide();
					$logofield.hide();
				}
			}

			if(MKGlobal.formInfo.formLogo){
				MKGlobal.formInfo.logoAvailable = true;
				MKGlobal.formSchemeStruct.logo = MKGlobal.formInfo.formLogo;
				$settingField.find('.operation').show();
			} else {
				MKGlobal.formInfo.logoAvailable = false;
				$settingField.find('.operation').hide();
			}

			showImg();
			// 回显关键信息
			if(MKGlobal.formInfo.logoAvailable){
				// 如果是可以显示的
				$trigger.attr('checked','checked').parent().addClass('checked');
			}

			$trigger.unbind('change').bind('change',function(){
				var $this = $(this);
				if($this.attr('checked') === 'checked'){
					MKGlobal.formInfo.logoAvailable = true;
					$settingField.find('.operation').show();
				} else {
					MKGlobal.formInfo.logoAvailable = false;
					$settingField.find('.operation').hide();
				}
				showImg();
				MKGlobal.addUnsaveCount();
			});

			// 2.2 上传图片的设置
			MKFORM.formUtility.formImgUpload($settingField.find('.input_file'), function (e, data) {
				var imgPath = data.result.data.url.replace(/[\\]/g, '/');
				MKGlobal.formSchemeStruct.logo = MKFORM.localHost + imgPath;
				// alert('logo');
				showImg();
				MKGlobal.addUnsaveCount();
			});
		}



		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'formChangeTitleBackground': (function () {
		function mkBind() {
			var $settingField = $('.sdi_TitlebackgroundImg'),
				$logofield = $('.form_title'),
				$trigger = $('#form_title_background');
			function showImg(){
				// alert('showimg-title');
				MKFORM.formUtility.formSchemeCSSGenerator();
			}

			if(MKGlobal.formInfo.formTitleBackground !== 'none' && MKGlobal.formInfo.formTitleBackground){
				MKGlobal.formInfo.titlebkAvailable = true;
				$settingField.find('.operation').show();
			} else {
				MKGlobal.formInfo.titlebkAvailable = false;
				$settingField.find('.operation').hide();
			}

			// showImg();
			// 回显关键信息
			if(MKGlobal.formInfo.titlebkAvailable){
				// 如果是可以显示的
				$trigger.attr('checked','checked').parent().addClass('checked');
			}

			$trigger.unbind('change').bind('change',function(){
				var $this = $(this);
				if($this.attr('checked') === 'checked'){
					MKGlobal.formInfo.titlebkAvailable = true;
					$settingField.find('.operation').show();
				} else {
					MKGlobal.formInfo.titlebkAvailable = false;
					$settingField.find('.operation').hide();
				}
				showImg();
				MKGlobal.addUnsaveCount();
			});

			// 2.2 上传图片的设置
			MKFORM.formUtility.formImgUpload($settingField.find('.input_file'), function (e, data) {
				var imgPath = data.result.data.url.replace(/[\\]/g, '/');
				MKGlobal.formSchemeStruct.timg = 'url('+MKFORM.localHost + imgPath+')';
				showImg();
				MKGlobal.addUnsaveCount();
			});
		}



		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'formSchemeChange': (function () {
		// 事件的绑定
		var schemeMap = {
			'formBuilder_color_instruction': 'instruction',
			'formBuilder_color_title': 'title',
			'formBuilder_color_wallpaper': 'wallpaper',
			'formBuilder_color_form': 'form',
			'formBuilder_color_highlight': 'highlight'
		};

		function mkBind() {
			$('.formBuilder_color_items').unbind('click').bind('click', function () {
				// ----
				var currentSchemeInfo = $(this).attr('schemeInfo'),
					schemeName = '',
					schemeStyle = '';
				if (currentSchemeInfo !== MKGlobal.preFormSchemeInfo) {
					$('.formBuilder_color_items').removeClass('active_color');
					$(this).addClass('active_color');
					MKGlobal.preFormSchemeInfo = currentSchemeInfo;
				}
				MKFORM.formSetting.formCustomScheme._init($(this));

				MKGlobal.addUnsaveCount();
			});

			$('.removeself_color').unbind('click').bind('click',function(){
				var id = $(this).attr('schemeid');
				$.ajax({
					url: 'handler/handleRemoveFormStyleScheme.php',
					type: 'POST',
					dataType: 'json',
					data: {
						DATA: JSON.stringify({
							"ID" : id
						})
					}
				}).done(function(data){
					if(data.flag){
						getFormScheme();
						$(document).queue('MKFORMEDIT',function(){
							MKFORM.formSetting.formSchemeChange.bind();
						});
						$(document).dequeue('MKFORMEDIT');
					}
				});
				return false;
			});
		}

		return {
			bind: function () {
				mkBind();
			}
		};
	})(),
	'formCustomScheme':(function(){
		var _handle = {
			changeToggle: function(){
				var $this = $(this);
				if(!$this.hasClass('active')){
					$this.addClass('active').siblings('.active').removeClass('active');
				}
			},
			showDesign: function(){
				// 隐藏显示
				var $this = $(this),
					$stylePad = $this.closest('.style_design'),
					$orgPad = $stylePad.siblings('.style_pad'),
					h = 32;

				if(undefined === $this.data('isopen')){
					$this.data('isopen',true);
					(function($ui){
						window.setTimeout(function(){
							$ui.data('isopen',false);
						},12000);
					})($this);
				}

				$this.data('isopen',!$this.data('isopen'));

				if($this.data('isopen')){
					h = '62%';
					$('.sdp_save').show();
					$('.sdp_arrow').find('img').attr('src','images/icon/pulldownBlue.png');
				} else {
					$('.sdp_save').hide();
					$('.sdp_arrow').find('img').attr('src','images/icon/pullupBlue.png');
				}
				// $this.attr('human',true);
				MKGlobal.human = true;

				$stylePad.css({
					height: h
				});
				$orgPad.css({
					bottom: h
				});
				$orgPad.getNiceScroll().resize();
				$stylePad.find('.style_design_item').getNiceScroll().resize();
				window.setTimeout(function(){
					$orgPad.getNiceScroll().resize();
					$stylePad.find('.style_design_item').getNiceScroll().resize();
				},300);
				// $stylePad.stop().animate({
				// 	height: h},
				// 	200, function() {
				// 		$orgPad.getNiceScroll().resize();
				// 		$stylePad.find('.style_design_item').getNiceScroll().resize();
				// });
				// $orgPad.stop().animate({
				// 	bottom: h},
				// 	200, function() {
				// 		$orgPad.getNiceScroll().resize();
				// 		$stylePad.find('.style_design_item').getNiceScroll().resize();
				// });
			},
			font_colorBind: function(hsb, hex, rgb, el, fromSetColor){
				var $el = $(el),
					$colorPad = $el.find('.in_color'),
					type = $el.attr('gt');
				$colorPad.css('color','#'+hex);
				if(type === 'ft'){
					MKGlobal.formSchemeStruct['ht'] = hex;
				}
				MKGlobal.formSchemeStruct[type] = hex;
				MKFORM.formUtility.formSchemeCSSGenerator();
				MKGlobal.addUnsaveCount();
			},
			background_colorBind: function(hsb, hex, rgb, el, fromSetColor){
				var $el = $(el),
					$colorPad = $el.find('.in_color'),
					type = $el.attr('gt'),
					colorObj,brighten,black;
				$colorPad.css('backgroundColor','#'+hex);
				if(type === 'fb'){
					colorObj = mgColorManager.getColorVal(hex);

					brighten = colorObj.S / 100;
					black = colorObj.V / 100;
	
					if(colorObj.GY>180 && colorObj.GY<254.8){
						// darken
						MKGlobal.formSchemeStruct['hb'] = mgColorManager.getHSV2RGB([colorObj.H, ((brighten + 0.07 < 1) ? brighten + 0.07 : 1), (black - 0.16 > 0 ? (black - 0.16) : 0)]);
					} else if(colorObj.GY>254.8){
						// gray
						MKGlobal.formSchemeStruct['hb'] = 'FFF8DC';
					} else if(colorObj.GY<22 && colorObj.GY>0){
						// HSV fix
						MKGlobal.formSchemeStruct['hb'] = mgColorManager.getHSV2RGB([colorObj.H,(brighten - 0.08 > 0 ? brighten - 0.08 : 0), ((black - 0) + 0.24 < 1 ? (black - 0) + 0.24 : 1)]);
					} else {
						// lighter
						MKGlobal.formSchemeStruct['hb'] = mgColorManager.getHSV2RGB([colorObj.H,(brighten - 0.08 > 0 ? brighten - 0.08 : 0), ((black - 0) + 0.14 < 1 ? (black - 0) + 0.14 : 1)]);
					}
				}
				MKGlobal.formSchemeStruct[type] = hex;
				MKGlobal.addUnsaveCount();
				MKFORM.formUtility.formSchemeCSSGenerator();
			},
			changeSize: function(){
				var $this = $(this);

				// if(!$this.hasClass('active')){
					// $this.addClass('active').siblings('.active').removeClass('active');
					MKGlobal.formSchemeStruct.fw = $this.attr('chose-info');
					MKGlobal.addUnsaveCount();
				// }
			},
			changeFontSize: function(){
				var $this = $(this);
					MKGlobal.formSchemeStruct.fs = $this.attr('chose-info');
					MKFORM.formUtility.formSchemeCSSGenerator();
					MKGlobal.addUnsaveCount();
			},
			changeLineHeight: function(){
				var $this = $(this);
					MKGlobal.formSchemeStruct.flh = $this.attr('chose-info');
					MKFORM.formUtility.formSchemeCSSGenerator();
					MKGlobal.addUnsaveCount();
			},
			saveScheme: function(e){
				if(e||e.stopPropagation){
					e.stopPropagation();
				}
				var $this = $(this),
					$savePad = $this.closest('.sdi_save_btn'),
					$infoPad = $savePad.siblings('.sdi_saveinfo_input'),
					$designField = $savePad.closest('.style_design_item');

				TINY.box.show({
						html:$(".popwin_scheme").html(),
						width: 414,
						height: 238,
						animate:true,
						boxid: 'save_scheme',
						close:true,
						openjs: function(){

							function __selfCssGener(){
								var styleList = ['fw','fs','flh','wb','wt','img','imgp','imgr','imgf','lb','lt','logo','timg','fb','ft','it','hb','ht'],
									defaultsValue = ['640px', '16px', '25px', 'F4F5F0', '333333', '', 'left', '', '', 'FEFEFE', '222222', '', '', 'FFFFFF', '333333','333333', 'FFF8DC', '333333'],
									ans = [],
									needSave = $.extend(true, {}, MKGlobal.formSchemeStruct);
								if(!MKGlobal.formInfo.backgroundAvailable){
									needSave.img = '';
									needSave.imgp = '';
									needSave.imgr = '';
									needSave.imgf = '';
								} else {
									needSave.img = MKGlobal.formInfo.background;
									needSave.imgr = MKGlobal.formInfo.backgroundtype;
								}
								if(!MKGlobal.formInfo.logoAvailable){
									needSave.logo = '';
								}
								if(!MKGlobal.formInfo.titlebkAvailable){
									needSave.timg = '';
								}
								for(var i=0,len = styleList.length; i<len; i++){
									ans[i] = needSave[styleList[i]]||defaultsValue[i];
								}

								return ans.join(',');
							}

							var $box = $('#save_scheme'),
								res = __selfCssGener();

							$box.find(".btn_cancel").click(function(){
								TINY.box.hide();
							});

							$box.find(".btn_confrim").unbind('click').click(function(){
								if( MKGlobal.preFormSchemeInfo !== res){
									$.ajax({
										url: 'handler/handleAddFormStyleScheme.php',
										type: 'POST',
										dataType: 'json',
										data: {
											DATA: JSON.stringify({
												NAME: $box.find('.schemename').val(),
												CSS: res
											})
										}
									}).done(function(data){
										if(data.flag){
											getFormScheme();
											$(document).queue('MKFORMEDIT',function(){
												MKFORM.formSetting.formSchemeChange.bind();
											});
											$(document).dequeue('MKFORMEDIT');
											TINY.box.hide();
										}
									});
								} else {
									TINY.box.hide();
								}
							});
						}
					});

			}
		};

		function mkBind(){
			var $styleDesign = $('.style_design'),
				$styleDesignPad = $styleDesign.find('.style_design_pad'),
				$styleSave = $styleDesign.find('.sdp_save'),
				$wb = $styleDesign.find('.sdi_backgroundcolor'),
				$tb = $styleDesign.find('.sdi_titlebackgroundcolor'),
				$ft = $styleDesign.find('.sdi_mainfontcolor'),
				$fb = $styleDesign.find('.sdi_mainbackgroundcolor'),
				$tt = $styleDesign.find('.sdi_titlefontcolor'),
				$it = $styleDesign.find('.sdi_desfontcolor');

			$styleDesignPad.data('isopen',false);

			$styleDesignPad.off('click.showDesign').on('click.showDesign',_handle.showDesign);
			$styleDesign.find('.stateBtn').off('click.changeToggle').on('click.changeToggle',_handle.changeToggle);
			$styleSave.off('click.save').on('click.save',_handle.saveScheme);
			MKFORM.formUtility.formColorPicker($wb,_handle.background_colorBind);
			MKFORM.formUtility.formColorPicker($tb,_handle.background_colorBind);
			MKFORM.formUtility.formColorPicker($ft,_handle.font_colorBind);
			MKFORM.formUtility.formColorPicker($fb,_handle.background_colorBind);
			MKFORM.formUtility.formColorPicker($tt,_handle.font_colorBind);
			MKFORM.formUtility.formColorPicker($it,_handle.font_colorBind);

			$styleDesign.find('.sdi_formwidth').find('.stateBtn').unbind('click.changeStyle').bind('click.changeStyle',_handle.changeSize);
			$styleDesign.find('.sdi_formfontsize').find('.stateBtn').unbind('click.changeStyle').bind('click.changeStyle',_handle.changeFontSize);
			$styleDesign.find('.sdi_formlineheight').find('.stateBtn').unbind('click.changeStyle').bind('click.changeStyle',_handle.changeLineHeight);

			MKFORM.formSetting.formCustomScheme._init($('.formBuilder_color_outer').find('.active_color'),true);
		}

		function styleInit($ui,silent){
			var $defaultStyle = $('.style_pad'),
			$styleDesign = $('.style_design'),
			$designItem = $styleDesign.find('.style_design_item');

			// 显示
			if(!silent){
				if(!MKGlobal.human){
					// $defaultStyle.animate({
					// 	bottom: '62%'},200,function(){
					// 		$defaultStyle.getNiceScroll().resize();
					// 	});
					// $styleDesign.animate({
					// 	height: '62%'},200, function(){
					// 		$designItem.show();
					// 		$designItem.getNiceScroll().resize();
					// 	});
					
					$defaultStyle.css({
						bottom: '62%'
					});
					$styleDesign.css({
						height: '62%'
					});
					$designItem.show();
					$defaultStyle.getNiceScroll().resize();
					$designItem.getNiceScroll().resize();
					window.setTimeout(function(){
						$defaultStyle.getNiceScroll().resize();
						$designItem.getNiceScroll().resize();
					},300);
					$('.style_design_pad').data('isopen',true);
					$('.sdp_save').show();
					$('.sdp_arrow').find('img').attr('src','images/icon/pulldownBlue.png');
				}
			} else {
				$designItem.show();
				$designItem.getNiceScroll().resize();
			}

			if($ui.length>0){
				var tmpO = parseFormScheme($ui.attr('schemeinfo'));
				if(MKGlobal.formSchemeStruct.img){
					tmpO.img = MKGlobal.formSchemeStruct.img;
				}
				if(MKGlobal.formSchemeStruct.timg){
					tmpO.timg = MKGlobal.formSchemeStruct.timg;
				}
				if(MKGlobal.formSchemeStruct.logo){
					tmpO.logo = MKGlobal.formSchemeStruct.logo;
				}

				MKGlobal.formSchemeStruct = tmpO;
				MKFORM.formUtility.formSchemeCSSGenerator();
			} else {
				if(!MKGlobal.preFormSchemeInfo){
					MKGlobal.formSchemeStruct.wb = 'F4F5F0';
					MKGlobal.formSchemeStruct.wt = '333333';
					MKGlobal.formSchemeStruct.lb = 'FEFEFE';
					MKGlobal.formSchemeStruct.lt = '222222';
					MKGlobal.formSchemeStruct.fb = 'FFFFFF';
					MKGlobal.formSchemeStruct.ft = '333333';
					MKGlobal.formSchemeStruct.it = '333333';
					MKGlobal.formSchemeStruct.hb = 'FFF8DC';
					MKGlobal.formSchemeStruct.ht = '333333';
				}
			}

			// 将 配色方案中的图片信息同步到目前这张表单上去
			if(MKGlobal.formSchemeStruct.img){
				if(MKGlobal.formInfo.background === 'none' || !MKGlobal.formInfo.background){
					MKGlobal.formInfo.background = MKGlobal.formSchemeStruct.img;
				}
			}
			if(MKGlobal.formSchemeStruct.logo){
				if(MKGlobal.formInfo.formLogo === 'none' || !MKGlobal.formInfo.formLogo){
					MKGlobal.formInfo.formLogo = MKGlobal.formSchemeStruct.logo;
				}
			}
			if(MKGlobal.formSchemeStruct.timg){
				if(MKGlobal.formInfo.formTitleBackground === 'none' || !MKGlobal.formInfo.formTitleBackground){
					MKGlobal.formInfo.formTitleBackground = MKGlobal.formSchemeStruct.logo;
				}
			}
			
			// 设置初始值
			$styleDesign.find('.sdi_backgroundcolor').colpickSetColor(MKGlobal.formSchemeStruct.wb).find('.in_color').attr('style','background-color:#'+MKGlobal.formSchemeStruct.wb);
			$styleDesign.find('.sdi_titlebackgroundcolor').colpickSetColor(MKGlobal.formSchemeStruct.lb).find('.in_color').attr('style','background-color:#'+MKGlobal.formSchemeStruct.lb);
			$styleDesign.find('.sdi_mainfontcolor').colpickSetColor(MKGlobal.formSchemeStruct.ft).find('.in_color').attr('style','color:#'+MKGlobal.formSchemeStruct.ft+';');
			$styleDesign.find('.sdi_mainbackgroundcolor').colpickSetColor(MKGlobal.formSchemeStruct.fb).find('.in_color').attr('style','background-color:#'+MKGlobal.formSchemeStruct.fb);
			$styleDesign.find('.sdi_titlefontcolor').colpickSetColor(MKGlobal.formSchemeStruct.lt).find('.in_color').attr('style','color:#'+MKGlobal.formSchemeStruct.lt+';');
			$styleDesign.find('.sdi_desfontcolor').colpickSetColor(MKGlobal.formSchemeStruct.it).find('.in_color').attr('style','color:#'+MKGlobal.formSchemeStruct.it+';');

			// init 图片的对齐部分
			var $bkp = $styleDesign.find('.sdi_backgroundImg'),
				$fw = $styleDesign.find('.sdi_formwidth'),
				$fs = $styleDesign.find('.sdi_formfontsize'),
				$flh = $styleDesign.find('.sdi_formlineheight');

			$bkp.find('.active').removeClass('active');
			$bkp.find('.stateBtn[chose-info="'+MKGlobal.formSchemeStruct.imgp+'"]').addClass('active');
			$fw.find('.active').removeClass('active');
			$fw.find('.stateBtn[chose-info="'+MKGlobal.formSchemeStruct.fw+'"]').addClass('active');
			$fs.find('.active').removeClass('active');
			$fs.find('.stateBtn[chose-info="'+MKGlobal.formSchemeStruct.fs+'"]').addClass('active');
			$flh.find('.active').removeClass('active');
			$flh.find('.stateBtn[chose-info="'+MKGlobal.formSchemeStruct.flh+'"]').addClass('active');

		}

		return {
			bind: mkBind,
			_init: styleInit
		};
	})(),
	'formStartTime': (function () {
		// -- start time;
		function mkBind() {
			var $startButton = $('#startFormSetting'),
				$startField;
			// set Val;
			if (MKGlobal.formInfo.formControl.startDate) {
				$startButton.attr('checked', 'checked');
				$startField = $startButton.parent().addClass('checked').siblings('.formStart_field').show();
				$startField.find('#form_startTime input').val(MKGlobal.formInfo.formControl.startDate.substring(16, 0));
			}

			// date picker bind ...
			$('#form_startTime').datetimepicker({
				language: 'zh-CN',
				pickSeconds: false
			}).on('changeDate', function () {
					var startDateStr = $(this).find("input").val().replace(/-/g, "/") + ':00',
						endDateStr = '',
						selectedDate = new Date(startDateStr),
						currentDate = new Date();

					if ($(".formEnd_field").css('display') != 'none') {
						endDateStr = $("#form_endTime").find("input").val();
					}

					if (selectedDate < currentDate) {
						$(this).parent().siblings(".formStartError").css("visibility", "visible").text("启用表单的时间不能早于当前时间哦~~").data('currenttype', 'BCT'); // before current time
					} else if (endDateStr != "" && selectedDate > new Date(endDateStr)) {
						$(this).parent().siblings(".formStartError").css("visibility", "visible").text("启用表单的时间需早于停用表单的时间哦~~").data('currenttype', 'NBET'); // need before end time
					} else {
						$(this).parent().siblings(".formStartError").css("visibility", "hidden").data('currenttype', null);
						if ($(this).closest('.formBuilder_timeSetting').find(".formEndError").data('currenttype') == 'NLST') {
							$(this).closest('.formBuilder_timeSetting').find(".formEndError").css("visibility", "hidden").data('currenttype', null);
						}
					}
					MKGlobal.addUnsaveCount();
				});

			$startButton.unbind('change').bind('change', function () {
				if ($(this).attr("checked") != "checked") {
					$(this).parent().siblings(".formStart_field").hide();
				} else {
					$(this).parent().siblings(".formStart_field").show();
				}
				$(".formStart_field #form_startTime").find("input").val("");
				$(".formStart_field").find(".formStartError").css("visibility","hidden");
				if ($(".formEndTime_field").find(".formEndError").text() == "停用表单的时间需晚于启用表单的时间哦~~") {
					$(".formEndTime_field").find(".formEndError").css("visibility","hidden");
				}
				MKGlobal.addUnsaveCount();
			});
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'formEndTime': (function () {
		// ---
		function mkBind() {
			var $endButton = $('#endFormSetting'),
				$endField;
			// set Val;
			if (MKGlobal.formInfo.formControl.stopDate) {
				$endButton.attr('checked', 'checked');
				$endField = $endButton.parent().addClass('checked').siblings('.formEndTime_field').show();
				$endField.find('#form_endTime input').val(MKGlobal.formInfo.formControl.stopDate.substring(16, 0));
			}

			// date picker bind ...
			$('#form_endTime').datetimepicker({
				language: 'zh-CN',
				pickSeconds: false
			}).on('changeDate', function () {
					var startDateStr = '',
						endDateStr = $(this).find("input").val().replace(/-/g, "/") + ':00',
						selectedDate = new Date(endDateStr),
						currentDate = new Date();

					if ($(".formStart_field").css('display') != 'none') {
						startDateStr = $("#form_startTime").find("input").val();
					}

					if (selectedDate < currentDate) {
						$(this).parent().siblings(".formEndError").css("visibility", "visible").text("停用表单的时间不能早于当前时间哦~~").data('currenttype', 'BCT');
					} else if (startDateStr != "" && selectedDate <= new Date(startDateStr)) {
						$(this).parent().siblings(".formEndError").css("visibility", "visible").text("停用表单的时间需晚于启用表单的时间哦~~").data('currenttype', 'NLST'); // need late than start time
					} else {
						if ($(this).closest('.formBuilder_timeSetting').find(".formStartError").data('currenttype') == 'NBET') {
							$(this).closest('.formBuilder_timeSetting').find(".formStartError").css("visibility", "hidden").data('currenttype', null);
						}
						$(this).parent().siblings(".formEndError").css("visibility", "hidden").data('currenttype', null);
					}
					MKGlobal.addUnsaveCount();
				});


			$endButton.unbind('change').bind('change', function () {
				if ($(this).attr("checked") != "checked") {
					$(this).parent().siblings(".formEndTime_field").hide();
				} else {
					$(this).parent().siblings(".formEndTime_field").show();
				}
				$(".formEndTime_field #form_endTime").find("input").val("");
				$(".formEndTime_field").find(".formEndError").css("visibility","hidden");
				if ($(".formStart_field").find(".formStartError").text() == "启用表单的时间需早于停用表单的时间哦~~") {
					$(".formStart_field").find(".formStartError").css("visibility","hidden");
				}
				MKGlobal.addUnsaveCount();
			});
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'formSetMaxiumFeedback': (function () {
		function mkBind() {
			var $endMaxButton = $('#endFormMax'),
				$endField;
			// set Val;
			if (MKGlobal.formInfo.formControl.maxFeedback) {
				$endMaxButton.attr('checked', 'checked');
				$endField = $endMaxButton.parent().addClass('checked').siblings('.formEndMax_field').show();
				$endField.find('.formEnd_feedbackNum input').val(MKGlobal.formInfo.formControl.maxFeedback);
			}

			$(".formEnd_feedbackNum").find(".feedbackNum").bind("change",function(){
				var feedbackNum = $(this).val();
				var currentNum = $(this).parent().siblings(".current_feedbackNum").find("span").text();
				if ( feedbackNum != "" && parseInt(feedbackNum) < parseInt(currentNum)) {
					$(this).parent().siblings(".formEndMaxError").css("visibility","visible").text("停用表单时的反馈数不能少于当前的反馈数哦~~");
				}else{
					$(this).parent().siblings(".formEndMaxError").css("visibility","hidden");
				}
			});

			$(".formEndMax_field .current_feedbackNum").find("span").text(MKGlobal.formInfo.feedbackNum);
			$endMaxButton.unbind('change').bind('change', function () {
				if ($(this).attr("checked") != "checked") {
					$(this).parent().siblings(".formEndMax_field").hide();
				} else {
					$(this).parent().siblings(".formEndMax_field").show();
				}
				MKGlobal.addUnsaveCount();
				$(".formEndMax_field .formEnd_feedbackNum").find("input").val("");
				$(".formEndMax_field").find(".formEndMaxError").css("visibility","hidden");
			});
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'formSetUnique': (function () {
		function mkBind() {
			var $uniqueButton = $('#endFormSubmitOnce');
			// set value
			if (MKGlobal.formInfo.formSubmitOnce === 1) {
				$uniqueButton.attr('checked', 'checked');
				$uniqueButton.parent().addClass('checked');
			}

			$uniqueButton.unbind('change').bind('change', function () {
				MKGlobal.addUnsaveCount();
			});
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'formSetPayment': (function () {
		function mkBind() {
			var $alipayBtn = $('#payment_ali');
			if ($.inArray('ALIPAY', MKGlobal.formInfo.formPaymentInfo) < 0) {
				$alipayBtn.removeAttr('checked').parent().removeClass('checked');
			} else {
				$alipayBtn.attr('checked','checked').parent().addClass('checked');
			}
			$alipayBtn.unbind('change').bind('change', function () {
				MKGlobal.addUnsaveCount();
			});
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'formAfterSubmit': (function () {
		// get current type ...
		var afterSubmitType, afterSubmitValue,
			typeMap = ['showtext', 'openlink'];

		function mkBind() {
			var $block = $('.aftersubmit');
			afterSubmitType = $('.form_title').attr('type');
			afterSubmitValue = $('.form_title').attr('finishform');
			$block.find('input:radio').removeAttr('checked');
			$block.find('input:radio[value="' + typeMap[parseInt(afterSubmitType, 0)] + '"]').attr('checked', 'checked');
			$block.find('.formBuilder_edit_input').val(afterSubmitValue);

			$block.find('input:radio').unbind('change').bind('change', function () {
				$block.find('input:radio').removeAttr('checked');
				$(this).attr('checked', 'checked');
				$('.form_title').attr('type', $.inArray($(this).val(), typeMap));
				MKGlobal.addUnsaveCount();
			});

			$block.find('.formBuilder_edit_input').unbind('input keyup').bind('input keyup', function () {
				$('.form_title').attr('finishform', $(this).val());
				MKGlobal.addUnsaveCount();
			});
		}

		return {
			bind: function () {
				mkBind();
			}
		};
	})(),
	'formFeedbackNotice': (function () {
		function mkBind() {
			// set Value
			var emailinfo = send_mail_to_Email,
				nameinfo = send_mail_to_Name,
				$emailField = $('.feedback_email'),
				$sendButton = $('#autoSendMail');

			$emailField.mikeTag({
				'tagClass': 'MGTAG-i',
				'defaultText': '',
				'RegEx': /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i
			});


			if (MKGlobal.formInfo.sendMailInfo.email) {
				emailinfo = MKGlobal.formInfo.sendMailInfo.email
			}
			if (MKGlobal.formInfo.sendMailInfo.name) {
				nameinfo = MKGlobal.formInfo.sendMailInfo.name;
			}

			$emailField.mikeTag_Add(emailinfo.split(';'));
			$('.feedback_name').val(nameinfo).unbind('keyup').bind('keyup',function(){
				MKGlobal.addUnsaveCount();
			});
			$('.mikeContainer').find('input').die('keyup').live('keyup',function(){
				MKGlobal.addUnsaveCount();
			});

			if (!MKGlobal.formInfo.sendMailInfo.needSend) {
				$sendButton.removeAttr('checked');
				$sendButton.parent().removeClass('checked').siblings('.formFeedbackMail').hide();
			} else {
				$sendButton.attr('checked', 'checked');
				$sendButton.parent().addClass('checked').siblings('.formFeedbackMail').show();
			}

			$sendButton.unbind('change').bind('change', function () {
				if ($(this).attr("checked") != "checked") {
					$(this).parent().siblings('div.formFeedbackMail').hide();
				} else {
					$(this).parent().siblings('div.formFeedbackMail').show();
				}
				MKGlobal.addUnsaveCount();
			});
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'formAutoCreateContact': (function () {
		//---
		var $contactAdd =$("#autoAddContact"),
			$contactSetting = $contactAdd.parent().siblings('.formCreateContacts');
		function mkBind() {
			// MKGlobal.formInfo.newContact = true;
			if(!MKGlobal.formInfo.newContact){
				$contactAdd.removeAttr('checked');
				$contactAdd.parent().removeClass('checked');
			}

			if ($contactAdd.attr('checked') == 'checked') {
				$contactSetting.show();
			} else {
				$contactSetting.hide();
			}
			$contactAdd.bind('change', function () {
				if($('.tip_info').is(':visible') != true){
					if ($(this).attr("checked") != "checked") {
						$contactSetting.hide();
					} else {
						$contactSetting.show();
					}
				} else {
					window.setTimeout(function(){
						$contactAdd.removeAttr('checked').parent().removeClass('checked');
					},0);

				}
				MKGlobal.addUnsaveCount();
			});
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'formTitleClick': (function () {
		// 点击 title 部分 跳转 到 表单设置
		function mkBind() {
			$('.formBuilder_main .form_title').unbind('click').bind('click', function () {
				// --
				var $editField = $('.formBuilder_interim_edit').eq(0);
				if (!$editField.hasClass('formBuilder_interim_edit_active')) {
					$editField.trigger('click');
				}
			});
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'formChangeBackground': (function () {
		var tmpBackground = '';

		function mkBind() {
			var $settingField = $('.sdi_backgroundImg'),
				$setBackground = $('#form_background'),	// 是否显示背景图
				$backgroundController = $settingField.find('.upload_background_image_style'),
				$setBackgroundRepeat = $('#background_repeat'),
				$setBackgroundFix = $('#background_fix'),
				$setBackgroundPosition = $backgroundController.find('.sdi_stateBtn');

			// 初始化
			if(MKGlobal.formInfo.backgroundtype !== 'no-repeat'){
				$setBackgroundRepeat.attr('checked', 'checked').parent().addClass('checked');
			}

			if(MKGlobal.formSchemeStruct.imgf === 'fix'){
				$setBackgroundFix.attr('checked', 'checked').parent().addClass('checked');
			}

			if(MKGlobal.formSchemeStruct.imgp){
				$setBackgroundPosition.find('.active').removeClass('active');
				$setBackgroundPosition.find('.stateBtn[chose-info="'+MKGlobal.formSchemeStruct.imgp+'"]').addClass('active');
			} else {
				$setBackgroundPosition.find('.active').removeClass('active');
				$setBackgroundPosition.find('.stateBtn').eq(0).addClass('active');
				MKGlobal.formSchemeStruct.imgp = $setBackgroundPosition.eq(0).attr('chose-info');
			}

			if (MKGlobal.formInfo.background && MKGlobal.formInfo.background != 'none' && MKGlobal.formInfo.background != false){
				$setBackground.attr('checked', 'checked').parent().addClass('checked');
				MKGlobal.formInfo.backgroundAvailable = true;
				$settingField.find('.operation').show();
			} else {
				MKGlobal.formInfo.backgroundAvailable = false;
				$settingField.find('.operation').hide();
			}

			// 背景图片显示的控制
			$setBackground.unbind('change').bind('change', function () {
				var hint = MKFORM.uploadhint;
				if ($(this).attr("checked") != "checked") {
					MKGlobal.formInfo.backgroundAvailable = false;
					$settingField.find('.operation').hide();
				} else {
					MKGlobal.formInfo.backgroundAvailable = true;
					$settingField.find('.operation').show();
				}

				// alert(MKGlobal.formInfo.backgroundAvailable);

				MKFORM.formUtility.formSchemeCSSGenerator();
				if(MKGlobal.formInfo.background && MKGlobal.formInfo.background !== 'none' ){
					hint = '文件已经上传';
				}
				$settingField.find('.input_file').next('p').text(hint);

				MKGlobal.addUnsaveCount();
			});


			// 2.2 上传图片的设置
			MKFORM.formUtility.formImgUpload($settingField.find('.input_file'), function (e, data) {
				var imgPath = data.result.data.url.replace(/[\\]/g, '/');
				// TODO !!!! 上线前要改成 www.mikecrm.com
				MKGlobal.addUnsaveCount();
				MKGlobal.formInfo.background = 'url(' + MKFORM.localHost + imgPath + ')';
				MKFORM.formUtility.formSchemeCSSGenerator();
			});


			$setBackgroundRepeat.unbind('change').bind('change', function () {
				if ($(this).attr('checked') == 'checked') {

					MKGlobal.formInfo.backgroundtype = 'repeat';
				} else {
					MKGlobal.formInfo.backgroundtype = 'no-repeat';
				}
				MKGlobal.formSchemeStruct.imgp = MKGlobal.formInfo.backgroundtype;
				MKFORM.formUtility.formSchemeCSSGenerator();
				MKGlobal.addUnsaveCount();
			});

			$setBackgroundFix.unbind('change').bind('change', function () {
				if ($(this).attr('checked') == 'checked') {

					MKGlobal.formInfo.backgroundfix = 'fix';
				} else {
					MKGlobal.formInfo.backgroundfix = '';
				}
				MKGlobal.formSchemeStruct.imgf = MKGlobal.formInfo.backgroundfix;
				MKFORM.formUtility.formSchemeCSSGenerator();
				MKGlobal.addUnsaveCount();
			});

			$setBackgroundPosition.find('.stateBtn').unbind('click').bind('click',function(){
				var $this = $(this);
				if(!$this.hasClass('active')){
					$this.addClass('active').siblings('.active').removeClass('active');
					MKGlobal.formSchemeStruct.imgp = $this.attr('chose-info');
					MKFORM.formUtility.formSchemeCSSGenerator();
					MKGlobal.addUnsaveCount();
				}
			});
		}

		return {
			bind: function () {
				return mkBind();
			}
		};
	})(),
	'formFinishLoading': function () {
		$('.globalLoading').fadeOut(400);
	}
};

MKFORM.formUtility = {
	formImgUpload: function ($ui, uploadCallback) {
		$ui.fileupload({
			dataType: "json",
			url: 'handler/handleUploadFormPicture.php',
			drop: function (e) {
				return false;
			},
			add: function (e, data) {
				var flag = false;
				if (data.files[0].size) {
					if (data.files[0].size < 2000000) {
						$(this).attr('hasFile', true);
						$(this).siblings('p').text(data.files[0].name).css("color", "#333333");
						flag = true;
					} else {
						$(this).siblings('p').text('请上传小于2M的图片…').css('color', '#B94A48');
					}
				} else {
					$(this).attr('hasFile', true);
					$(this).siblings('p').text(data.files[0].name).css("color", "#333333");
					flag = true;
				}
				if (flag) {
					data.submit();
				}
			},
			start: function (e, data) {
				$(this).siblings('p').text('开始上传……');
			},
			progressall: function (e, data) {
				var progress = parseInt(data.loaded / data.total * 90, 10);
				$(this).siblings('p').css('color', '#333').text('正在上传……' + progress + '%').siblings('.progress').css('width', progress * 0.9 + '%');
			},
			done: function (e, data) {
				// var uploadFlag = data.result.data.flag;
				var uploadFlag = data.result.flag;
				if (uploadFlag) {
					$(this).siblings('p').css('color', '#333').text(data.files[0].name).siblings('.progress').css('width', '90%');

					uploadCallback(e, data);
				} else {
					$(this).siblings('p').css('color', '#333').text('文件超过大小，上传失败。').siblings('.progress').css('width', '90%');
					$('.validate_submit').removeAttr('style').text('提交');
				}
				$(this).siblings('.progress').fadeOut();
			}
		});
	},
	formButtonImgUpload: function ($ui, uploadCallback) {
		$ui.fileupload({
			dataType: "json",
			url: 'handler/handleUploadFormPicture.php',
			drop: function (e) {
				return false;
			},
			add: function (e, data) {
				var flag = false;
				if (data.files[0].size) {
					if (data.files[0].size < 2000000) {
						$(this).attr('hasFile', true);
						// $(this).parent().siblings('.errorinfo').text(data.files[0].name).css("color", "#333333");
						flag = true;
					} else {
						// $(this).siblings('p').css('color', '#B94A48');
						$(this).parent().siblings('.uploadinfo').text('上传的文件太大了…').show().css('color', '#B94A48');
					}
				} else {
					$(this).attr('hasFile', true);
					// $(this).siblings('p').text(data.files[0].name).css("color", "#333333");
					flag = true;
				}
				if (flag) {
					data.submit();
				}
			},
			start: function (e, data) {
				$(this).parent().siblings('.uploadinfo').text('开始上传！').show().css('color', '#333');
			},
			progressall: function (e, data) {
				var progress = parseInt(data.loaded / data.total * 90, 10);
				$(this).parent().siblings('.uploadinfo').text('上传中…' + progress + '%').show().css('color', '#333');
			},
			done: function (e, data) {
				// var uploadFlag = data.result.data.flag;
				var uploadFlag = data.result.flag;
				if (uploadFlag) {
					$(this).parent().siblings('.uploadinfo').text('上传成功!').show().css('color', '#333');
					window.setTimeout((function ($ui) {
						return function () {
							$ui.parent().siblings('.uploadinfo').hide();
						};
					})($(this)), 300);
					uploadCallback(e, data);

				} else {
					$(this).parent().siblings('.uploadinfo').text('上传失败...').show().css('color', '#333');
				}
				$(this).siblings('.progress').fadeOut();
			}
		});
	},
	formSelectImgUpload: function ($ui, uploadCallback) {
		$ui.fileupload({
			dataType: "json",
			url: 'handler/handleUploadFormPicture.php',
			drop: function (e) {
				return false;
			},
			add: function (e, data) {
				var flag = false;
				if (data.files[0].size) {
					if (data.files[0].size < 2000000) {
						$(this).attr('hasFile', true);
						flag = true;
					} else {
						$(this).siblings('.upload_btn').text('文件太大').show();
					}
				} else {
					$(this).attr('hasFile', true);
					flag = true;
				}
				if (flag) {
					data.submit();
				}
			},
			start: function (e, data) {
				$(this).siblings('.upload_btn').text('开始上传').show();
			},
			progressall: function (e, data) {
				var progress = parseInt(data.loaded / data.total * 90, 10);
				$(this).siblings('.upload_btn').text('' + progress + '%...');
			},
			done: function (e, data) {
				var uploadFlag = data.result.flag;
				if (uploadFlag) {
					$(this).siblings('.upload_btn').text('上传成功').show();

					uploadCallback(e, data);

				} else {
					$(this).siblings('.upload_btn').text('上传失败').show();
				}
			}
		});
	},
	formSetCurrentTime: function () {
		var currentTime;

		function dateString(val) {
			if (val < 10) {
				return '0' + val;
			}
			return val;
		}

		function dateShow(currentTime) {
			return '当前时间：<span class="year">' + currentTime.getFullYear() + '</span> 年 <span class="month">' + dateString(currentTime.getMonth() + 1) + '</span> 月 <span class="day">' + dateString(currentTime.getDate()) + '</span> 日 <span class="time">' + dateString(currentTime.getHours()) + ':' + dateString(currentTime.getMinutes()) + '</span>';
		}

		currentTime = new Date();
		$('.current_time').html(dateShow(currentTime));

		window.setInterval(function () {
			currentTime = new Date();
			$('.current_time').html(dateShow(currentTime));
		}, 60000);
	},
	getCurrentCursorPosition: function($ui) {
		var el = $ui.get(0);
		var pos = 0;
		if('selectionStart' in el) {
			pos = el.selectionStart;
		} else if('selection' in document) {
			el.focus();
			var Sel = document.selection.createRange();
			var SelLength = document.selection.createRange().text.length;
			Sel.moveStart('character', -el.value.length);
			pos = Sel.text.length - SelLength;
		}
		return pos;
	},
	formContactCheck: function ($ui) {
		var _r = false;
		// false 就是 没有 联系人组件 、、 true 就是 有 联系人组件
		$ui.each(function () {
			if ($(this).attr('name').indexOf('basic') >= 0) {
				_r = true;
				return false;
			}
		});

		$('.tip_need_contactcomponent').unbind('click').bind('click', function () {
			// -- click to
			var $editField = $('.formBuilder_interim_edit').eq(1),
				$component = $('.formBuilder_edit').eq(1),
				$contact_describeField = $('.contactsUtility>.utility');

			$contact_describeField.each(function (i) {
				var $this = $(this);
				(function (t) {
					$this.css({
						'backgroundColor': '#FFE0C7',
						'color': '#E85305',
						'borderColor': '#E85305'
					});
					window.setTimeout(function () {
						$this.animate({
							'backgroundColor': '#fff',
							'color': '#115A83',
							'borderColor': '#92AFBC'
						}, 200, function () {
							$this.removeAttr('style');
						});
					}, (300 + t));
				})(i * 20);
			});
			$editField.trigger('click');
			$component.scrollTop($component.height());

		});

		if (_r) {
			$('.tip_need_contactcomponent').hide();

			if(!$('#autoAddContact').attr('checked') && $('.createcontact').find('.input_checkbox label').hasClass('gray')){
				$('.form_edit_contact').trigger('click');
				$('.formCreateContacts').show();
			}
			$('.createcontact').show().find('.input_checkbox label').removeClass('gray');			
		} else {
			$('.tip_need_contactcomponent').show();
			$('.createcontact').show().find('.input_checkbox label').addClass('gray');
			$('.formCreateContacts').hide();
			$('#autoAddContact').removeAttr('checked').parent().removeClass('checked');

		}

		return _r;
	},
	formPaymentCheck: function ($ui) {
		var _r = false;
		$ui.each(function () {
			if ($(this).attr('name').indexOf('id_shopping') >= 0) {
				_r = true;
				return false;
			}
		});

		if(!_r){
			// -- no id_shopping;
			$('#payment_ali').removeAttr('checked');
		}

		$('.tip_need_payment').unbind('click').bind('click', function () {
			// -- click to
			var $editField = $('.formBuilder_interim_edit').eq(1);
			$editField.trigger('click');
		});


		if (_r) {
			$('.tip_need_payment').hide();
			$('.setpaymentinfo').show();
		} else {
			$('.tip_need_payment').show();
			$('.setpaymentinfo').hide();
		}

		return _r;
	},
	formLimitedItemCheck: function($ui){
		var limitList = {
			"basic_name": 1,
			"basic_city": 1,
			"basic_gender": 1,
			"basic_company": 1,
			"basic_adress": 1,
			"basic_job": 1,
			"basic_website": 1,
			'basic_birthday':1
		},resList = {
			"basic_name": 0,
			"basic_city": 0,
			"basic_gender": 0,
			"basic_company": 0,
			"basic_adress": 0,
			"basic_job": 0,
			"basic_website": 0,
			'basic_birthday':0
		};

		$ui.each(function () {
			// --
			var name = $(this).attr('name'),
				limitCount = limitList[name];
			if(limitCount){
				resList[name]++;
			}
		});

		for(var i in resList){
			if(resList[i] >= limitList[i]){
				$('#'+i).addClass('limit-disable').attr('title',"该表单已添加过该组件");
			} else {
				$('#'+i).removeClass('limit-disable').attr('title',"点击选择或拖动到左侧");
			}
		}
	},
	getSimpleDate: function (date) {
		var monthList = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];
		return monthList[date.getMonth()] + '' + date.getDate();
	},
	mkStringEval: function (s) {
		var LINK_REG = /\[(.+?)]\(([^\(\)]*?)\)/g,
			LINK_TEST_REG = MKGlobal.urlRegEx;
		return s.replace(LINK_REG,function($0,$1,$2){

			var linkList = $.trim($2).split(' ');
				uriTest = (!LINK_TEST_REG.test(linkList[0])),
				newURI = '',
				tmpTitle = '';
			if(uriTest){
				return $0;
			} else {
				newURI = linkList[0].split("://");
				newURI = newURI[0]+'://'+encodeURIComponent(newURI[1]);
				if(linkList[1]){
					tmpTitle = 'title='+JSON.stringify(linkList[1]);
				}
			}
			return '<a link-save="'+newURI+'" target="_blank">'+$1+'<img width="12" height="12" src="images/icon/externalLink.png"></a>';
		});
	},
	mkHtmlString: function (s){
		var TAG_A_REG = /<[aA][^>]*link-save="([^"]*)"[^>]*>([^<>]*)<['img''IMG'][^>]*src="images\/icon\/externalLink.png"[^>]*><\/[aA]>/g;
		return s.replace(TAG_A_REG,function($,$1,$2){
			return '['+$2+']('+decodeURIComponent($1)+')';
		});
	},
	mkStringEncode: function (s) {
		return (typeof s != "string") ? s :
			s.replace(/"|&|'|<|>|[\x00-\x20]|[\x7F-\xFF]|[\u0100-\u2700]/g,
				function ($0) {
					var c = $0.charCodeAt(0),
						r = ["&#"];
					c = (c == 0x20) ? 0xA0 : c;
					r.push(c);
					r.push(";");
					return r.join("");
				});
	},
	formSchemeCSSGenerator: function(){
		function __addCss(name, content){
			var styleElem = document.getElementsByTagName('style');
			for(var i=0,len=styleElem.length; i<len; i++){
				if(styleElem[i]){
					if(name === styleElem[i].id){
						styleElem[i].parentNode.removeChild(styleElem[i]);
					}
				}
			}
			$('<style id="'+name+'">'+content+'</style>').appendTo('head');
		}
		var cssInfo = '',
			backgroundInfo = '',
			titlebk = '';

		if(MKGlobal.formInfo.backgroundAvailable){
			if(MKGlobal.formInfo.background){
				backgroundInfo += 'background-image:'+(MKGlobal.formInfo.background||'')+';';
			}
			if(MKGlobal.formInfo.backgroundtype){
				backgroundInfo += 'background-repeat:'+MKGlobal.formInfo.backgroundtype+';';
			}
			if(MKGlobal.formSchemeStruct.imgp){
				backgroundInfo += 'background-position:'+MKGlobal.formSchemeStruct.imgp+' top;';
			}
		}

		if(MKGlobal.formInfo.titlebkAvailable){
			if(MKGlobal.formSchemeStruct.timg){
				titlebk += 'background-image:'+(MKGlobal.formSchemeStruct.timg||'')+';';
			}
		}

		// console.log(MKGlobal.formInfo);

		// console.log(MKGlobal.formSchemeStruct);
		cssInfo += '.formBuilder_example_all .formBuilder_example{background-color:#'+MKGlobal.formSchemeStruct.wb+'; color:#'+MKGlobal.formSchemeStruct.wt+';'+backgroundInfo+'}';
		cssInfo += '.formBuilder_example_all .form_title{background-color:#'+MKGlobal.formSchemeStruct.lb+';color:#'+MKGlobal.formSchemeStruct.lt+';'+titlebk+'}';
		cssInfo += '.formBuilder_example_all .formBuilder_main .form_component {background-color:#'+MKGlobal.formSchemeStruct.fb+';color:#'+MKGlobal.formSchemeStruct.ft+';}';
		cssInfo += '.formBuilder_example_all .formBuilder_main .form_component>li{background-color:#'+MKGlobal.formSchemeStruct.fb+';color:#'+MKGlobal.formSchemeStruct.ft+';}';
		cssInfo += '.formBuilder_example_all .form_component .title {font-size:'+MKGlobal.formSchemeStruct.fs+';line-height: '+MKGlobal.formSchemeStruct.flh+';}';
		cssInfo += '.formBuilder_example_all .form_component .instruct{color:#'+MKGlobal.formSchemeStruct.it+';border-color:#'+MKGlobal.formSchemeStruct.it+'}';
		cssInfo += '.formBuilder_example_all .formBuilder_main .form_component .clicked{background-color:#'+MKGlobal.formSchemeStruct.hb+';color:#'+MKGlobal.formSchemeStruct.ht+'}';

		cssInfo += '.style_design_pad .sdp_color_wb{background-color:#'+MKGlobal.formSchemeStruct.wb+'}';
		cssInfo += '.style_design_pad .sdp_color_lb{background-color:#'+MKGlobal.formSchemeStruct.lb+'}';
		cssInfo += '.style_design_pad .sdp_color_ft{background-color:#'+MKGlobal.formSchemeStruct.ft+'}';
		cssInfo += '.style_design_pad .sdp_color_fb{background-color:#'+MKGlobal.formSchemeStruct.fb+'}';
		cssInfo += '.style_design_pad .sdp_color_it{background-color:#'+MKGlobal.formSchemeStruct.it+'}';
		cssInfo += '.style_design_pad .sdp_color_lt{background-color:#'+MKGlobal.formSchemeStruct.lt+'}';
		// console.log(cssInfo);

		__addCss('form_style_scheme',cssInfo);
	},
	formColorPicker: function($ui,changeCallback){
		$ui.colpick({
			layout: 'hex',
			submit: 0,
			onChange: changeCallback
		});
	}
};

// 表单中的每一个组件绑定事件
function renderFormComponent($ui) {
	// 记得最后要 nicescroll resize 一下
	if ($ui) {
		var componentId = $ui.attr('id'),
			componentName = $ui.attr('name');

		if (MKFORM.currentComponent !== componentId) {
			MKFORM.currentComponent = componentId;
			MKFORM.currentChanged = true;
		} else {
			MKFORM.currentChanged = false;
		}
		MKFORM.editManager(componentName);

		$.each(MKFORM.editFunctionMap[componentName], function (key, val) {
			MKFORM.componentSetting[val].bind();
		});
	} else {
		MKFORM.editManager(false);
	}

	$('#form_componentEdit').getNiceScroll().resize();
}

// 有些时候进行了组件类型的修改，需要调用重新渲染一下
function reRenderFormComponent(id) {
	// 记得最后要 nicescroll resize 一下
}

// 绑定拖动的事件 / 初始化
function init() {
	$(document).queue('MKFORMEDIT', function () {
		// 1. 添加组件 的拖动
		var $componentList;
		utilityDrag().bind();
		formItemSortable();
		addOptionDrag();
		serialSet();
		// 2. 表单设置的初始化
		MKFORM.formSetting.formTitle.bind();
		MKFORM.formSetting.formSubTitle.bind();
		MKFORM.formSetting.formSchemeChange.bind();
		MKFORM.formSetting.formChangeLogo.bind();

		MKFORM.formSetting.formTitleClick.bind();
 
		MKFORM.formSetting.formChangeTitleBackground.bind();

		MKFORM.formSetting.formChangeBackground.bind();
		MKFORM.formSetting.formCustomScheme.bind();
		// 收集时间控制
		MKFORM.formSetting.formStartTime.bind();
		MKFORM.formSetting.formSetMaxiumFeedback.bind();
		MKFORM.formSetting.formSetUnique.bind();
		MKFORM.formSetting.formEndTime.bind();

		MKFORM.formSetting.formSetPayment.bind();

		MKFORM.formSetting.formAutoCreateContact.bind();

		MKFORM.formSetting.formAfterSubmit.bind();
		MKFORM.formSetting.formFeedbackNotice.bind();
		// TODO 完成初始化的工作
		// finish ...
		MKFORM.formUtility.formSetCurrentTime();
		MKFORM.formSetting.formFinishLoading();

		$componentList = $('.form_component').children('.ui-draggable');
		MKFORM.formUtility.formContactCheck($componentList);
		MKFORM.formUtility.formPaymentCheck($componentList);
		MKFORM.formUtility.formLimitedItemCheck($componentList);

		// --
		MKGlobal.unsaveinfo = 0;
		// $('.btn_example_save').addClass('allsaved').text('');
	});
}


// 给 id 下的 option中的元素加上 拖动事件
function addOptionDrag() {
	var $componentContainer = $(".formBuilder_main .form_component");
	$componentContainer.find(".optionGarden").sortable({
		opacity: 0.8,   //拖动时候的透明度
//		axis: "y",
		stop: function (ui, event) {
			$(this).parents(".ui-draggable").trigger("click");
			MKGlobal.addUnsaveCount();
		}
	});
}

MKFORM.serialCount = (function () {
	var count = 0;
	return {
		_getCount: function () {
			return count;
		},
		_setCount: function (newCount) {
			count = newCount;
		},
		_selfAdd: function () {
			count++;
		}
	};
})();

function serialSet() {
	var serialArray = [];
	// - 算出最大的一个值
	$(".ui-draggable").each(function () {
		serialArray.push($(this).attr("id").replace(/[^\d]/g, ''));
	});
	serialArray.sort(function (a, b) {
		return (a - b);
	});
	// 赋值最大值
	if (serialArray.length > 0) {
		MKFORM.serialCount._setCount(serialArray[serialArray.length - 1]);
	}
}

function utilityDrag() {
	var htmlMap = {
		id_checkBox: function (name) {
			var options = '',
				count = 0,
				defaults = ["选项1", "选项2", "选项3"];
			$.each(defaults, function (n) {
				options += '<li class="optionsLine medium"><input type="checkbox" name="checkbox' + MKFORM.serialCount._getCount() + '" value="' + count + '" disabled=true><label class="optionValue">' + defaults[n] + '</label></li>';
				count++;
			});
			return '<span class="title_field"><label class="title">' + name + '</label><span class="com_required"></span></span><div class="checkbox"><ul class="optionGarden">' + options + '</ul><div class="clearB"></div></div>';
		},
		id_dropDown: function (name) {
			var options = '',
				count = 0,
				defaults = ["选项1", "选项2", "选项3"];
			$.each(defaults, function (n) {
				options += '<option name="' + count + '">' + defaults[n] + '</option>';
				count++;
			});
			return '<span class="title_field"><label class="title">' + name + '</label><span class="com_required"></span></span><div class="select"><select class="medium" disabled=true><option name="-1">请选择</option>' + options + '</select></div>';
		},
		id_multiple: function (name) {
			return '<span class="title_field"><label class="title">' + name + '</label><span class="com_required"></span></span><div><textarea class="textarea medium" disabled=true></textarea></div>';
		},
		id_number: function (name) {
			return this.id_singleLine(name);
		},
		id_radio: function (name) {
			var options = '',
				count = 0,
				defaults = ["选项1", "选项2", "选项3"];
			$.each(defaults, function (n) {
				options += '<li class="optionsLine medium"><input type="radio" name="radio' + MKFORM.serialCount._getCount() + '" value="' + count + '" disabled=true><label class="optionValue">' + defaults[n] + '</label></li>';
				count++;
			});
			return '<span class="title_field"><label class="title">' + name + '</label><span class="com_required"></span></span><div class="radio"><ul class="optionGarden">' + options + '</ul><div class="clearB"></div></div>';
		},
		id_singleLine: function (name) {
			return '<span class="title_field"><label class="title">' + name + '</label><span class="com_required"></span></span><div><input type="text" class = "input medium" disabled=true></div>';
		},
		id_section: function (name) {
			var defaultSubtitle = "这里填写你的描述";
			return '<span class="title_field"><label class="title">' + name + '</label></span><hr><div class="subtitle">' + defaultSubtitle + '</div>';
		},
		id_fileUpload: function (name) {
			return  '<span class="title_field"><label class="title">' + name + '</label><span class="com_required"></span></span><div><div class="upload_file medium"><input type="file" class="input_file" name="_FILE_" data-url="" disabled=true><p>请选择小于20M的文件进行上传</p><img src="images/icon/importFileAdd.png"></div></div>';
		},
		id_star: function (name) {
			return  '<span class="title_field"><label class="title">' + name + '</label><span class="com_required"></span></span><div><div class="starGroup" starSelected="0"><span class="star"></span><span class="star"></span><span class="star"></span><span class="star"></span><span class="star"></span></div></div>';
		},
		id_date: function (name) {
			return  '<span class="title_field"><label class="title">' + name + '</label><span class="com_required"></span></span><div><input type="text" class = "date input medium" dateType="d" disabled=true></div>';
		},
		id_shopping: function (name) {
			var title = '<span class="title_field"><label class="title">' + name + '</label><span class="com_required"></span></span>',
				field = '<div class="shopping"><ul class="shoppingList"><li class="shopping-item empty">暂时没有商品</li></ul><div class="clearB"></div></div>';
			return title + field;
		},
		id_picture: function (name) {
			var defaultSubtitle = "这里是"+name+",写下你对它的描述";
			return '<div class="title_field"><img src="images/icon/formDefaultPicture.png" style="width: 100%;"><input type="file" class="in_pic_upload" name="_FILE_" /></div><div class="subtitle">' + defaultSubtitle + '</div>';
		},
		id_pictureCheckbox: function (name) {
			var title = '<span class="title_field"><label class="title">' + name + '</label><span class="com_required"></span></span>',
				field = '',options = '',
				count = 0,
				defaults = ["选项1", "选项2", "选项3"];
			$.each(defaults, function (n) {
				options += '<li class="picturecheckbox-item'+(count===0?' clearB':'')+'" listfield="0"><div class="piccheckbox_img"></div><div class="piccheckbox_contect"><input type="checkbox" name="picturecheckbox' + MKFORM.serialCount._getCount() + '" value="' + count + '" disabled="disabled"><label class="optionValue">' + defaults[n] + '</label></div></li>';
				count++;
			});;

			field = '<div class="picture_checkbox"><ul class="pictureCheckboxList">'+options+'</ul><div class="clearB"></div></div>';
			return title + field;
		},
		id_pictureRadio: function (name) {
			var title = '<span class="title_field"><label class="title">' + name + '</label><span class="com_required"></span></span>',
				field = '',options = '',
				count = 0,
				defaults = ["选项1", "选项2", "选项3"];
			$.each(defaults, function (n) {
				options += '<li class="pictureradio-item'+(count===0?' clearB':'')+'" listfield="0"><div class="picradio_img"></div><div class="picradio_contect"><input type="radio" name="pictureradio' + MKFORM.serialCount._getCount() + '" value="' + count + '" disabled="disabled"><label class="optionValue">' + defaults[n] + '</label></div></li>';
				count++;
			});;

			field = '<div class="picture_radio"><ul class="pictureRadioList">'+options+'</ul><div class="clearB"></div></div>';
			return title + field;
		},
		basic_name: function (name) {
			return '<span class="title_field"><label class="title">' + name + '</label><span class="com_required">*</span></span><div><input type="text" class = "input medium" disabled=true></div>';
		},
		basic_city: function (name) {
			return this.basic_name(name);
		},
		basic_job: function (name) {
			return this.basic_name(name);
		},
		basic_company: function (name) {
			return this.basic_name(name);
		},
		basic_weixin: function (name) {
			return this.basic_name(name);
		},
		basic_adress: function (name) {
			return this.basic_name(name);
		},
		basic_phone: function (name) {
			return this.basic_name(name);
		},
		basic_birthday: function (name) {
			return this.basic_name(name);
		},
		basic_fax: function (name) {
			return this.basic_name(name);
		},
		basic_gender: function (name) {
			var options = '',
				count = 0,
				defaults = ["先生", "女士"];
			$.each(defaults, function (n) {
				options += '<li class="optionsLine medium"><input type="radio" name="radio' + MKFORM.serialCount._getCount() + '" genderValue="' + ((count === 1) ? "F" : "M") + '" value="' + count + '" disabled=true><label class="optionValue">' + defaults[n] + '</label></li>';
				count++;
			});
			return '<span class="title_field"><label class="title">' + name + '</label><span class="com_required">*</span></span><div class="radio"><ul class="optionGarden">' + options + '</ul><div class="clearB"></div></div>';
		},
		basic_email: function (name) {
			return this.basic_name(name);
		},
		basic_website: function (name) {
			return this.basic_name(name);
		},
		basic_mobile: function (name) {
			return this.basic_name(name);
		},
		basic_qq: function (name) {
			return this.basic_name(name);
		},
		"undefined": function () {
			return "";
		}
	};

	function MKBind() {
		$('.formBuilder_edit').find('.utility').unbind('click').bind('click',function () {
			var $componentContainer = $(".formBuilder_main .form_component"),
				utilityId = $(this).attr('id'),
				utilityText = $(this).find('.title').text(),
				componentHTML = htmlMap[utilityId](utilityText);

			// add To last ...
			if(!$(this).hasClass('limit-disable')){
				MKFORM.serialCount._selfAdd();
				$(this).clone().html(componentHTML).appendTo($componentContainer).removeClass('utility').attr({
					"id": "com" + MKFORM.serialCount._getCount(),
					"name": utilityId,
					"title": "点击进行修改,拖动交换位置,按住Ctrl拖动即可复制."
				}).addClass('unedited');

				formItemSortable();
				addOptionDrag();

				$(".formBuilder_example").stop().animate({
					scrollTop: $componentContainer.height()
				}, 300, function () {
					$(".formBuilder_example").getNiceScroll().resize();
				});
				MKGlobal.addUnsaveCount();
			}
		}).draggable({
				appendTo: "body",
				helper: "clone",
				cancel: ".limit-disable",
				opacity: 0.8,
				revert: false,
				distance: 2,
				connectToSortable: $(".formBuilder_main .form_component"),
				start: function (event, ui) {
					$(".ui-draggable-dragging").css({
						"height": "auto",
						"float": "none",
						"width": "170px"
					}).attr("id", $(event.target).attr("id"));
				},
				drag: function (event, ui) {
				},
				stop: function (event, ui) {
					var utilityId = ui.helper.attr('id'),
						utilityText = ui.helper.text(),
						componentHTML = htmlMap[utilityId](utilityText),
						$newComponent;
					MKFORM.serialCount._selfAdd();
					$newComponent = $('.form_component').find('.utility');
					$newComponent.html(componentHTML).removeClass('utility').attr({
						"id": "com" + MKFORM.serialCount._getCount(),
						"name": utilityId,
						"title": "点击进行修改,拖动交换位置,按住Ctrl拖动即可复制."
					});
					formItemSortable();
					addOptionDrag();
					MKGlobal.addUnsaveCount();
					$newComponent.trigger('click');

				}
			});
	}

	return {
		'addUtility': function (name, html) {
			htmlMap[name] = html;
		},
		bind: function () {
			return MKBind();
		}
	};
}

function formItemSortable() {
	// 对所有的表单中的元素增加sortable
	var $componentContainer = $(".formBuilder_main .form_component");
	$componentContainer.find('.locked').attr({
		'class': 'ui-draggable',
		'title': '拖动交换位置,按住Ctrl拖动即可复制.'
	});
	var ctrlCopy = false,
		scrollFlag = true;
	// TODO CHECK contact field / payment field
	$componentContainer.sortable({
		items: ">li:not(.placeholder)",
		opacity: 0.8,   //拖动时候的透明度
		// axis: "y",
		cancel: ".locked",
		distance: 6,
		start: function (event, ui) {
			var isClicked = false;
			// ui.helper / ui.item / ui.placeholder
			if (event.ctrlKey) { //在拖动开始的时候判断是否按下了Ctrl
				if (ui.helper.find(".deleteButton").length) {
					isClicked = true;
					ui.helper.find(".deleteButton").remove();
				}
				MKFORM.serialCount._selfAdd();
				// -- 2014 - 01 - 23
				// 复制的原理 ： 其实是在拖动开始都时候，复制一份当前的元素插入到原来的位置，把需要拖动的元素移走就可以了
				// 这里其实可以优化 ： 这个时候取消拖动，新插入都应该删除才是。 -- 需要计数器的重新计算 云云。
				// -- @shenmo 
				$("<li class='" + ui.helper.attr("class") + "' id=com" + MKFORM.serialCount._getCount() + " >" + ui.helper.html() + "</li>").attr({"name": ui.helper.attr("name"), "title": ui.helper.attr("title")}).data($.extend(true,{},ui.item.data())).insertAfter(ui.helper).removeClass("clicked");
				if (isClicked) {
					ui.helper.prepend("<div class='deleteButton'></div>");
				}
				ctrlCopy = true;
			}
			ui.placeholder.empty().append(ui.helper.html()).css({'opacity': '0.2', 'visibility': 'visible'}).find('.deleteButton').remove();
		},
		sort: function (event, ui) {
			$(this).find(".placeholder").remove();
			// ui.placeholder
			ui.placeholder.css({'width': '100%', "float": 'none'});
			$(".ui-sortable-helper").addClass('buildFormSort').css('width', '500px');
			//$(".ui-draggable-dragging").html(createcomponent(ui.helper));
			if (ui.helper.hasClass('utility')) {
				ui.helper.css('width', '170px');
				ui.placeholder.css({'width': '90%', "float": 'none'});
			}

			ui.placeholder.empty().append(ui.helper.html()).css({'opacity': '0.2', 'visibility': 'visible'}).find('.deleteButton').remove();
			// - 2014 - 01 - 05 判断当前的位置，进行翻页
			var currentOffset = ui.offset,
				$builder = $('.formBuilder_example'),
				currentPlace = $builder.scrollTop();
			if (scrollFlag) {
				scrollFlag = false;
				if (currentOffset.left > 90 && currentOffset.left < 660) {
					// 在拖动的排序区域内
					if (currentOffset.top > 80 && currentOffset.top < 180) {
						// 向上翻页
						$builder.scrollTop((currentPlace - 20) > 0 ? currentPlace - 20 : 0);
					} else if ($(window).height() - currentOffset.top > 60 && $(window).height() - currentOffset.top < 160) {
						// 向下翻页
						$builder.scrollTop((currentPlace + 20) > 0 ? currentPlace + 20 : 0);
					}
				}
				window.setTimeout(function () {
					scrollFlag = true;
				}, 84);
			}

		},
		out: function (event, ui) {
			if (ui.helper) {
				ui.helper.css('width', '170px');
			}
		},
		over: function (event, ui) {
			ui.helper.css('width', '500px');
			if (ui.helper.hasClass('utility')) {
				ui.helper.css('width', '170px');
			}
		},
		update: function (event, ui) {
			$(this).find(".buildFormSort").removeClass('buildFormSort');
		},
		beforeStop: function (event, ui) {
			$(this).find(".buildFormSort").removeClass('buildFormSort');
		},
		stop: function () {
			MKGlobal.addUnsaveCount();
			if (ctrlCopy) {
				formItemSortable();
				addOptionDrag();
				MKFORM.componentSetting.settingDeleteSelf.bind();
				ctrlCopy = false;
			}
		}
	}).find('.ui-draggable').unbind('click').bind('click', function () {
			// 编辑的开始
			var $editField = $('.formBuilder_interim_edit').eq(2);
			renderFormComponent($(this));
			MKFORM.componentSetting.settingDeleteSelf.bind();
			if (!$editField.hasClass('formBuilder_interim_edit_active')) {
				$editField.trigger('click');
			}
			$(this).removeClass('unedited');
		}).unbind('mouseover').bind('mouseover',function(){
			var currentId = $(this).attr('id');
			MKFORM.componentSetting.settingHoverDelete.bind(currentId);
		});

	var $componentList = $('.form_component').children('.ui-draggable');
	MKFORM.formUtility.formContactCheck($componentList);
	MKFORM.formUtility.formPaymentCheck($componentList);
	MKFORM.formUtility.formLimitedItemCheck($componentList);
}

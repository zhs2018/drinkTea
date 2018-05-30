function changeSum(thi, type, index) {
	var $thi = $("#sum" + index);
	$.ajax({
		type : "POST",
		url : basePath + "/front/shop/editcount.html",
		data : {
			specId : $thi.attr("specid"),
			userid : userid,
			count : 1,
			type : type,
			carid : $thi.attr("carid")
		},
		async : true,
		success : function(res) {
			if (res.Status == "success") {
				switch (type) {
				case "-1":
					if (parseInt($thi.val()) > 1) {
						$thi.val(parseInt($thi.val()) - 1);
					}
					break;
				case "1":
					$thi.val(parseInt($thi.val()) + 1);
					break;
				}
				getTotal();
			} else {
				alert(res.Message);
			}

		},
		dataType : "json"
	});

}

function delInvalidGoods() {
	var kk="";
	$(".nousedd").each(function(){
		kk+=$(this).val()+",";
	  });
	$.ajax({
		type : "POST",
		url : basePath + "/front/shop/removeNoUser.html",
		data : {
			userid : userid,
			kk:kk
		},
		async : false,
		success : function(res) {
			window.location.href = window.location.href;
		},
		dataType : "json"
	});
}

function checkout() {
	var prods = "";
	var i = 0;
	$(".price span").each(
			function() {
				var temp = $(this).parent().parent().parent().children()
						.children().eq(0).children();
				if (temp.attr("ng-checked") != undefined) {
					prods = prods + temp.attr("value") + "-"
							+ $("#sum" + i).val() + ",";
				}
				i++;
			});

	if (prods == "") {
		alert("请勾选要支付的商品!");
		return;
	}
	window.location.href = basePath + "/front/shop/orderpage.html?prods="
			+ prods + "&userid=" + userid + "&fromcar=" + 1;
}

function selectAll() {
	if ($("#checkAll").attr("ng-checked") == undefined) {
		$(".radio").each(function() {
			$(this).attr("ng-checked", "true");
		});
	} else {
		$(".radio").each(function() {
			$(this).removeAttr("ng-checked");
		});
	}
	getTotal();
}

function delcart() {
	var ids = "";
	var i = 0;
	$(".price span").each(
			function() {
				var temp = $(this).parent().parent().parent().children()
						.children().eq(0).children();
				if (temp.attr("ng-checked") != undefined) {
					ids = ids + temp.attr("value") + ",";
				}
				i++;
			});
	$.ajax({
		type : "POST",
		url : basePath + "/front/shop/editcar.html",
		data : {
			prodid : ids,
			userid : userid,
			count : 0,
			type : 2
		},
		async : true,
		success : function(res) {
			window.location.href = window.location.href;
		},
		dataType : "json"
	});

}

function selectOne(thi) {
	if ($(thi).children().children().attr("ng-checked") == undefined) {
		$(thi).children().children().attr("ng-checked", "true");
	} else {
		$(thi).children().children().removeAttr("ng-checked");
	}
	getTotal();
}

function getTotal() {
	var totalPrice = 0;
	var totalCount = 0;
	var i = 0;
	$(".price span").each(
			function() {
				if ($(this).parent().parent().parent().children().children()
						.eq(0).children().attr("ng-checked") != undefined) {
					totalPrice = totalPrice + $(this).html()
							* parseInt($("#sum" + i).val());
					totalCount = parseInt(totalCount)
							+ parseInt($("#sum" + i).val());
				}
				i++;
			});
	if (isNaN(totalPrice)) {
		totalPrice = 0;
		$(".icon_del").addClass("hidden");
	} else {
		$(".icon_del").removeClass("hidden");
	}

	if (totalPrice == 0) {
		$(".icon_del").addClass("hidden");
		$("#checkAll").removeAttr("ng-checked")
	}
	if (isNaN(totalCount)) {
		totalCount = 0;
	}
	$(".price_total").html("￥" + totalPrice.toFixed(2));
	$(".count_total").html("(共" + totalCount + "件，不含运费)");
	// alert(totalPrice);
}
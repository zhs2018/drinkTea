<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			回复列表 <small>通过回复与用户交流</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					回复管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 回复列表 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-6" style="width: 100%">
		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class=" fa fa-cogs"></i>信息
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>文章标题</th>
								<th>回复类型</th>
								<th>描述</th>
								<th>自动回复</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty responseId}">
									<c:forEach items="${responseId}" var="product" varStatus="vs">

										<tr class="">
											<td>${vs.index +1}</td>
											<td>${product.responseTitle}</td>
											<td><c:if test="${product.responseType==1}">文本</c:if> <c:if
													test="${product.responseType==2}">图文</c:if> <c:if
													test="${product.responseType==3}">语音</c:if></td>
											<td><c:if test="${fn:length(product.responseDes)>30}">${fn:substring(product.responseDes, 0, 30)}...</c:if>
												<c:if test="${fn:length(product.responseDes)<30}">${fn:substring(product.responseDes, 0, 30)}</c:if></td>
											<td><c:if test="${product.responseClass==1}">首次关注</c:if>
												<c:if test="${product.responseClass==2}">命令模式</c:if> <c:if
													test="${product.responseClass==3}">自定义菜单模式</c:if> <c:if
													test="${product.responseClass==4}">缺省模式</c:if></td>
											<td><a class="btn default btn-xs purple"
												href="javascript:void(0)"
												onclick="editProduct(${product.responseId})"> <i
													class="fa fa-edit"></i> 编辑
											</a> &nbsp; &nbsp;<a id="${product.responseId }"
												class="btn default btn-xs black listContact_removeObjectSignle"
												title="回复" href="javascript:void(0)"> <i
													class="fa fa-trash-o"></i> 删除
											</a></td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="7">没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<div class="page_and_btn">
						<div></div>
						${obj.page.pageStr }
					</div>
				</div>
			</div>
		</div>
		<!-- END SAMPLE TABLE PORTLET-->
	</div>
</div>
<script>
	function editProduct(id) {
		window.location.href = "getone.html?id=" + id
				+ "&typeid=${param.typeid}";
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/js/system/listContact.js"></script>
</div>
<!-- BEGIN CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="footer">
	<div class="footer-inner">2014 &copy; kasiait521@gmail.com</div>
	<div class="footer-tools">
		<span class="go-top"> <i class="fa fa-angle-up"></i>
		</span>
	</div>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${SHOPDOMAIN}/assets/plugins/respond.min.js"></script>
<script src="${SHOPDOMAIN}/assets/plugins/excanvas.min.js"></script>
<![endif]-->

<script src="${SHOPDOMAIN}/assets/plugins/jquery-migrate-1.2.1.min.js"
	type="text/javascript"></script>
<script src="${SHOPDOMAIN}/assets/plugins/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="${SHOPDOMAIN}/assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
	type="text/javascript"></script>
<script
	src="${SHOPDOMAIN}/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
	type="text/javascript"></script>
<script src="${SHOPDOMAIN}/assets/plugins/jquery.blockui.min.js"
	type="text/javascript"></script>
<script src="${SHOPDOMAIN}/assets/plugins/jquery.cokie.min.js"
	type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<script src="${SHOPDOMAIN}/assets/scripts/core/app.js"></script>
<script src="${SHOPDOMAIN}/assets/plugins/bootbox/bootbox.min.js"
	type="text/javascript"></script>
<script>
	jQuery(document).ready(function() {
		App.init();
	});
</script>
<!-- END JAVASCRIPTS -->
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-37564768-1' ]);
	_gaq.push([ '_setDomainName', 'keenthemes.com' ]);
	_gaq.push([ '_setAllowLinker', true ]);
	_gaq.push([ '_trackPageview' ]);
	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://'
				: 'http://')
				+ 'stats.g.doubleclick.net/dc.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();

	$(function() {

		$(".page-sidebar-menu #menus${typeid}").addClass("active open");
		$("#menus${typeid}").children().eq(0).find('.arrow').remove();
		$("#menus${typeid}")
				.children()
				.eq(0)
				.append(
						"<span class='selected'></span> <span class='arrow open'></span>");

		if ("${param.professionid}" != "") {
			$("#menus${param.professionid}").parent().parent().addClass(
					"active open");
			$("#menus${param.professionid}").addClass("active ");
			$("#menus${param.professionid}").parent().parent().children().eq(0)
					.find('.arrow').addClass("active open");
			$("#menus${param.professionid}").parent().parent().children().eq(1)
					.css("display", "block");
		}

		if ("${param.classId}" != "") {
			$("#menuss${param.classId}").parent().parent().parent().parent()
					.addClass("active open");
			$("#menuss${param.classId}").parent().parent().parent().parent()
					.css("display", "block");
			$("#menuss${param.classId}").parent().parent().parent().css(
					"display", "block");
			$("#menuss${param.classId}").parent().parent().parent().addClass(
					"active open");
			$("#menuss${param.classId}").parent().parent().addClass(
					"active open");
			$("#menuss${param.classId}").addClass("active ");
			$("#menuss${param.classId}").parent().parent().children().eq(0)
					.find('.arrow').addClass("active open");
			$("#menuss${param.classId}").parent().parent().children().eq(1)
					.css("display", "block");

		}
	})
</script>
</body>
<!-- END BODY -->
</html>
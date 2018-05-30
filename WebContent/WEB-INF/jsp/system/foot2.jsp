<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN FOOTER -->
<div class="footer">
	<!-- <div class="footer-inner">2017 &copy; 山东和合信息科技有限公司</div> -->
	<div class="footer-tools">
		<span class="go-top"> <i class="fa fa-angle-up"></i> </span>
	</div>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${RESOURCEDOMAIN}/assets/plugins/respond.min.js"></script>
<script src="${RESOURCEDOMAIN}/assets/plugins/excanvas.min.js"></script>
<![endif]-->
<%-- <script src="${RESOURCEDOMAIN}/css/system/jstree/ui-tree.js" type="text/javascript"></script>
<script src="${RESOURCEDOMAIN}/css/system/jstree/ui-tree.min.js" type="text/javascript"></script> --%>
<script src="${RESOURCEDOMAIN}/css/system/jstree/dist/jstree.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${RESOURCEDOMAIN}/js/layer/layer.js"></script>
<script
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-toastr/toastr.min.js"></script>
<script src="${RESOURCEDOMAIN}/js/system/listContact.js" type="text/javascript"></script>
<script src="${RESOURCEDOMAIN}/assets/plugins/jquery-migrate-1.2.1.min.js"
	type="text/javascript"></script>
<script src="${RESOURCEDOMAIN}/assets/plugins/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="${RESOURCEDOMAIN}/assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
	type="text/javascript"></script>
<script
	src="${RESOURCEDOMAIN}/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
	type="text/javascript"></script>
<script src="${RESOURCEDOMAIN}/assets/plugins/jquery.blockui.min.js"
	type="text/javascript"></script>
<script src="${RESOURCEDOMAIN}/assets/plugins/jquery.cokie.min.js"
	type="text/javascript"></script>
<script src="${RESOURCEDOMAIN}/assets/plugins/uniform/jquery.uniform.min.js"
	type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<script src="${RESOURCEDOMAIN}/assets/scripts/core/app.js"></script>
<script src="${RESOURCEDOMAIN}/assets/plugins/bootbox/bootbox.min.js"
	type="text/javascript"></script>
<script src="${RESOURCEDOMAIN}/js/system/newContact.js"></script>
<SCRIPT src="${RESOURCEDOMAIN}/js/system/jquery.mikeValidate.js"></SCRIPT>

<SCRIPT src="${RESOURCEDOMAIN}/js/system/jquery.validate.js"></SCRIPT>
<SCRIPT src="${RESOURCEDOMAIN}/js/system/additional-methods.min.js"></SCRIPT>
<SCRIPT src="${RESOURCEDOMAIN}/js/system/form-validation.js"></SCRIPT>

<script src="${RESOURCEDOMAIN}/js/jquery.form.js" type="text/javascript"></script>

<%-- <SCRIPT src="${RESOURCEDOMAIN}/js/system/jquery.metadata.js"></SCRIPT>

<SCRIPT src="${RESOURCEDOMAIN}/js/system/jquery.validate.message_cn.js"></SCRIPT> --%>
<script
	src="${RESOURCEDOMAIN}/js/system/common.js"></script>
<script
	src="${RESOURCEDOMAIN}/js/system/metronic.js"
	type="text/javascript"></script>
<script>
	jQuery(document).ready(function() {
		Metronic.init(); // init metronic core components
		App.init();
	});
</script>
<!-- END JAVASCRIPTS -->
<script type="text/javascript">

	$(function() {

		$(".page-sidebar-menu #menus${param.typeid}").addClass("active open");
		$("#menus${param.typeid}").children().eq(0).find('.arrow')
				.remove();
		$("#menus${param.typeid}")
				.children()
				.eq(0)
				.append(
						"<span class='selected'></span> <span class='arrow open'></span>");
		if ("${param.professionid}" != "") {
			$("#menus${param.professionid}").parent().parent()
					.addClass("active open");
			$("#menus${param.professionid}")
					.addClass("active ");
			$("#menus${param.professionid}").parent().parent()
					.children().eq(0).find('.arrow').addClass(
							"active open");
			$("#menus${param.professionid}").parent().parent()
					.children().eq(1).css("display", "block");
		}

		if ("${param.classId}" != "") {
			$("#menuss${param.classId}").parent().parent()
					.parent().parent().addClass("active open");
			$("#menuss${param.classId}").parent().parent()
					.parent().parent().css("display", "block");
			$("#menuss${param.classId}").parent().parent()
					.parent().css("display", "block");
			$("#menuss${param.classId}").parent().parent()
					.parent().addClass("active open");
			$("#menuss${param.classId}").parent().parent()
					.addClass("active open");
			$("#menuss${param.classId}").addClass("active ");
			$("#menuss${param.classId}").parent().parent()
					.children().eq(0).find('.arrow').addClass(
							"active open");
			$("#menuss${param.classId}").parent().parent()
					.children().eq(1).css("display", "block");

		}
	})
</script>
</body>
<!-- END BODY -->
</html>
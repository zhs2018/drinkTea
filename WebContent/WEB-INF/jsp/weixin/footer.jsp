<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<link href="${SHOPDOMAIN}/front/css/widget_menu2.css" rel="stylesheet" />
<link href="${SHOPDOMAIN}/front/css/font-awesome.css" rel="stylesheet" />
<link href="${SHOPDOMAIN}/front/css/wicons.css" rel="stylesheet" />
<link href="${SHOPDOMAIN}/front/css/weimobfont2.css" rel="stylesheet" />
<div data-role="widget" data-widget="menu_2" class="menu_2">
			<div class="widget_wrap">
				<ul>
					<li style="background: #666666"><a
						href="${SHOPDOMAIN }/front/indexv.html" class="on"
						style="color: #FFFFFF"> <span class="icon-wmfont wm-home"></span>
							首页
					</a></li>
					<li style="background: #666666"><a
						href="${SHOPDOMAIN }/front/showcar.html?userid=" class="on"
						style="color: #FFFFFF"> <span class="icon-shopping-cart"></span>
							购物车
					</a></li>
					<li style="background: #666666"><a
						href="${SHOPDOMAIN }/front/user/myorder.html?type=0" class="on"
						style="color: #FFFFFF"> <span class="icon-file-text"></span>
							我的订单
					</a></li>
					<!-- <li style="background: #666666"><a
						href="${SHOPDOMAIN }/front/catalist.html" class="on"
						style="color: #FFFFFF"> <span class="icon-th-large"></span> 分类
					</a></li> -->



					<li style="background: #666666"><a
						href="${SHOPDOMAIN }/front/user/center.html" class="on"
						style="color: #FFFFFF"> <span class="icon-wmfont wm-community"></span>
							个人中心
					</a></li>

				</ul>
			</div>
			<script>
		        $("body").css("padding-bottom", "53px");
		    </script>
		</div>
/**
 * @文件名称: FXSHOP.java
 * @类路径: com.wxltsoft.framework.config
 * @描述: 系统常量说明
 * @作者：kasiaits
 * @时间：2013-3-7 上午11:12:10
 */

package com.wxsoft.drinkTea.framework.config;

/**
 * @类功能说明：常量说明
 * @类修改者：kasiait
 * @修改日期：2013-3-7
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-7 上午11:12:10
 */

public interface SCHOOLMGR {

	/** Controller注解命名空间字段 */
	/** 系统首页命名空间注解字段 */

	public final static String FXSHOP_ANNTATION_DEFAULT = "/";
	/** 前端展示命名空间注解字段 */
	public final static String FXSHOP_ANNOTATION_FRONT = "/front";
	/** 管理端命名空间注解字段 */
	public final static String FXSHOP_ANNOTATION_SYSTEM = "/system";
	/** 管理端命名空间注解字段 */
	public final static String WEBSERVICE = "/webservice";
	/** 管理端命名空间注解字段 */
	public final static String FXSHOP_ANNOTATION_SYSTEM_RESOURCE = "/system/topic";
	/** 管理端命名空间注解字段 */
	public final static String FXSHOP_ANNOTATION_SYSTEM_MENUS = "/menus";
	/** 用户个人命名空间注解字段 */
	public final static String FXSHOP_ANNOTATION_USER_PROFILE = "/user/profile";
	/** 文本编辑器命名空间注解字段 */
	public final static String FXSHOP_ANNOTATION_NAMESPACE_EDITOR = "/kindeditor";


	public final static String FX_DOMAIN_WWW = "http://fenxiao.sjyywei.com";
//	public final static String FX_DOMAIN_IMAGE = "http://image.weifenxiao123.com";
//
	/** front登陆的公司id */
	public final static String COMPANY_ID = "company_idk";
	/** Access注解命名空间字段 */
	/** 前端展示命名空间权限注解字段 */
	public final static String FXSHOP_ACCESS_ANNOTATION_USER = "customer";
	/** 管理端命名空间权限注解字段 */
	public final static String FXSHOP_ACCESS_ANNOTATION_SYSTEM = "system";
	/** 用户个人命名空间权限注解字段 */
	public final static String FXSHOP_ACCESS_ANNOTATION_USER_PROFILE = "customer";

	/** 发送邮件用户名和密码配置信息 */
	public final static String FXSHOP_EMAIL_PROPERTIES_HOST = "smtp.163.com";
	public final static String FXSHOP_EMAIL_PROPERTIES_FROM = "xxxx@163.com";
	public final static String FXSHOP_EMAIL_PROPERTIES_USERNAME = "";
	public final static String FXSHOP_EMAIL_PROPERTIES_USERPASSWORD = "";

	/** Session中变量声明 **/
	/** 上一引用页面从session中获取的变量 */
	public final static String FXSHOP_SESSION_REFERRER_PAGE_URL = "referrer";
	/** 存放在SESSION里面的验证码 */
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	/** 存放在SESSION里面的登陆用户 */
	public static final String SESSION_USER = "sessionUser";
	/** 存放在SESSION里面的登陆用户角色Id */
	public static final String SESSION_USER_ROLE_ID = "sessionRoleId";
	/** 存放在SESSION里面的登陆用户角色名称 */
	public static final String SESSION_USER_ROLE_CODE = "sessionRoleCode";
	/** 存放在SESSION里面的登陆用户角色名称 */
	public static final String SESSION_USER_ROLE_NAME = "sessionRoleName";
	/** 存放文档管理的menu_id */
	public static final Integer WEN_DANG_GUAN_LI = 5;
	/** 存放预约管理的menu_id */
	public static final Integer YU_YUE_GUAN_LI = 7;
	/** 存放企业品牌站的menu_id */
	public static final Integer QI_YE_PING_PAI_ZHAN = 1;

	/** 处理文件上传排除关键字 */
	public final static String FXSHOP_FRAMEWORK_FILEUPLOAD_FILTER = "jsonfile";

	/** jsp viewresolver 目录字段 */
	/** 管理员目录 **/
	public final static String SYSTEM_PATH_ADMIN = "/system";
	/** 用户属性空间 **/
	public final static String SYSTEM_PATH_USER = "/user";
	/** 网站前台页面 **/
	public final static String SYSTEM_PATH_FRONT = "/front";
	/** 商品目录 **/
	public final static String SYSTEM_PATH_PRODUCT = "/product";
	/** 角色目录 **/
	public final static String SYSTEM_PATH_ROLE = "/role";
	/** 订单目录 **/
	public final static String SYSTEM_PATH_ORDER = "/order";

	/** Paypal付款页面 */
	public final static String SYSTEM_PATH_PAYPAL = "/front/paypal";

	/** 基于url拦截器的匹配正则表达式 */
	public static final String NO_INTERCEPTOR_PATH = ".([^system])*/((login) | (logout) | (code) |(index)).*"; // 不对匹配该值的访问路径拦截（正则）
	public static final String INTERCEPTOR_SYSTEM_PATH = "/system/[^login].*";

	/** JSON数据key */
	public static final String FXSHOP_JSON_LOGIN_FASLE_MESSAGE = "json:";
	/** 鉴权失败信息返回码 */
	public static final String FXSHOP_JSON_INTERCEPTER_RETCODE = "retCode";
	/** 鉴权失败信息返回码 */
	public static final String FXSHOP_JSON_INTERCEPTER_SUCCESS_CODE = "0";
	/** 鉴权失败信息返回码 */
	public static final String FXSHOP_JSON_INTERCEPTER_FAIL_CODE = "-1";
	/** 鉴权失败信息返回码 */
	public static final String FXSHOP_JSON_INTERCEPTER_RET_MESSAGE = "retMsg";

	/** SysMenu返回码 */
	public static final String SysMenu = "SysMenu";

	/** ROLE返回码 */
	public static final String ROLEWIXIN = "ROLEWIXIN";

	/** ROLE返回码 */
	public static final String SECOND_ROLEWIXIN = "SECONDROLEWIXIN";

	/** ROLE返回码 */
	public static final String WEIXINSYS_DEL_SUB_MENU = "delete_sub_menu";

}

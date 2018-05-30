package com.wxsoft.drinkTea.framework.constant;

/**
 * @类功能说明：系统SESSION变量定义
 */
public interface SessionDef {

    // 如下为前台用户登陆用session定义
	/** 存放在SESSION里面的登陆用户(前台用) */
	public static final String LOGIN_USER_INFO = "loginUserInfo";
	/** 存放在SESSION里面的登陆用户公司信息(前台用) */
	public static final String LOGIN_USER_COMPANY_INFO = "loginUserCompanyInfo";
	/** 存放在SESSION里面的用户微信OPENID(前台用) */
	public static final String WEIXIN_OPEN_ID = "weixinOpenId";

	// 如下为后台管理用session定义
	/** 后台管理登陆用户信息 */
	public static final String SYS_LOGIN_USER_INFO = "sysLoginUserInfo";

	/** 后台管理用户登陆后菜单列表（有权限控制） */
	public static final String SYS_MENU_LIST = "sysMenuList";
	/** 后台管理用户登陆后菜单列表（有权限控制） */
	public static final String SYS_COMPANY_LIST = "sysCompanyList";
	/** 后台管理用户登陆后可访问的菜单列表（访问控制用） */
    public static final String SYS_ACCESSED_URL_LIST = "sysAccessedUrlList";
	/** 后台管理用户登陆后当前菜单信息（导航条用） */
	public static final String SYS_MENU_INFO = "sysMenuInfo";
	/** 后台管理用户登陆后当前菜单ID */
	public static final String SYS_MENU_CURRENT_ID = "menuid";

}

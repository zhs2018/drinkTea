/**   
 * @文件名称: Auth.java
 * @类路径: com.wxltsoft.framework.plugin
 * @描述: TODO
 * @作者：kasiaits
 * @时间：2013-3-12 下午01:11:21  
 */

package com.wxsoft.drinkTea.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @类功能说明：拦截器注解
 * @类修改者：kasiait
 * @修改日期：2013-3-12
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-12 下午01:11:21
 */
@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {
	boolean intercepter() default false; // 是否需要拦截

	String value() default ""; // 预留

	String rule() default ""; // 权限角色编码，与用户所属的角色编码比较

	String path() default ""; // 路径 url，如果拦截失败，则转到对应的处理页面的路径

	boolean weixinfrom() default false; // 是否是来自于微信浏览器，如果是，那么进行判断来源
	
	boolean auth() default false;//判断是否需要验证 auth 首页
	
	boolean userauth() default false;//判断是否需要验证 auth 用户页面
}

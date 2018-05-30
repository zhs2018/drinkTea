package com.wxsoft.drinkTea.framework.base;

/**
 * 
 * @类功能说明：系统基础数据配置，系统加载的时候从数据库中取出
 * @类修改者：kasiait
 * @修改日期：2013-3-20
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-20 下午02:58:19
 */
public class BaseConfigBean {

	/**
	 * 配置key
	 */
	private String key;
	/**
	 * 配置value
	 */
	private String value;
	/**
	 * 描述
	 */
	private String desc;

	public BaseConfigBean(String key , String value , String desc) {
		this.key = key;
		this.value = value;
		this.desc = desc;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}

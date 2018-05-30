package com.wxsoft.drinkTea.platform.weixin.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;


/**
 * @文件名称: MessageReading.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class MessageReading extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private Integer userId;
	//
	private Integer messageId;
	//
	private String readTime;
	//
	private Integer readSign;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getUserId(){
		return userId;
	}
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	public Integer getMessageId(){
		return messageId;
	}
	public void setMessageId(Integer messageId){
		this.messageId = messageId;
	}
	public String getReadTime(){
		return readTime;
	}
	public void setReadTime(String readTime){
		this.readTime = readTime;
	}
	public Integer getReadSign(){
		return readSign;
	}
	public void setReadSign(Integer readSign){
		this.readSign = readSign;
	}
	public CommonPage getPage(){
		return page;
	}
	public void setPage(CommonPage page){
		this.page = page;
	}

	// 排序规则
	private String orderBy;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
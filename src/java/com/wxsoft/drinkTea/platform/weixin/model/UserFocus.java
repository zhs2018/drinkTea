package com.wxsoft.drinkTea.platform.weixin.model;

import java.util.Date;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;

import java.math.BigDecimal;


/**
 * @文件名称: UserFocus.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class UserFocus extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private Integer userId;
	//
	private Integer openid;
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
	public Integer getOpenid(){
		return openid;
	}
	public void setOpenid(Integer openid){
		this.openid = openid;
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
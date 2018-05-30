package com.wxsoft.drinkTea.platform.system.catalog.model;

import java.util.Date;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;

import java.math.BigDecimal;


/**
 * @文件名称: ProvinceManage.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class ProvinceManage extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 分类管理
	private Integer id;
	//
	private String provinceName;
	//
	private Date addTime;
	//
	private Integer isVisable;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getProvinceName(){
		return provinceName;
	}
	public void setProvinceName(String provinceName){
		this.provinceName = provinceName;
	}
	public Date getAddTime(){
		return addTime;
	}
	public void setAddTime(Date addTime){
		this.addTime = addTime;
	}
	public Integer getIsVisable(){
		return isVisable;
	}
	public void setIsVisable(Integer isVisable){
		this.isVisable = isVisable;
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
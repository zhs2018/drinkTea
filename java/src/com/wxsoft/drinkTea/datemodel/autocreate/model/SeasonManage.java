package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: SeasonManage.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class SeasonManage extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 分类管理
	private Integer id;
	// 
	private String seasonName;
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
	public String getSeasonName(){
		return seasonName;
	}
	public void setSeasonName(String seasonName){
		this.seasonName = seasonName;
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
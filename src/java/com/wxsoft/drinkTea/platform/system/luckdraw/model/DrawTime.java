package com.wxsoft.drinkTea.platform.system.luckdraw.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;


/**
 * @文件名称: DrawTime.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class DrawTime extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String startIme;
	//
	private String  endTime;
	//
	private Integer visible;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String  getStartIme(){
		return startIme;
	}
	public void setStartIme(String startIme){
		this.startIme = startIme;
	}
	public String getEndTime(){
		return endTime;
	}
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}
	public Integer getVisible(){
		return visible;
	}
	public void setVisible(Integer visible){
		this.visible = visible;
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
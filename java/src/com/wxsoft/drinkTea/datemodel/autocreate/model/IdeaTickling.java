package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: IdeaTickling.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class IdeaTickling extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 
	private Integer id;
	// 
	private String contents;
	// 
	private Integer phone;
	// 
	private Integer commodityId;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getContents(){
		return contents;
	}
	public void setContents(String contents){
		this.contents = contents;
	}
	public Integer getPhone(){
		return phone;
	}
	public void setPhone(Integer phone){
		this.phone = phone;
	}
	public Integer getCommodityId(){
		return commodityId;
	}
	public void setCommodityId(Integer commodityId){
		this.commodityId = commodityId;
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
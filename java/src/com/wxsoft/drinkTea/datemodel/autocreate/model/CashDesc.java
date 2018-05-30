package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: CashDesc.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class CashDesc extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 
	private Integer id;
	// 
	private String cashTitle;
	// 
	private String cashDesc;
	// 
	private String cashImage;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getCashTitle(){
		return cashTitle;
	}
	public void setCashTitle(String cashTitle){
		this.cashTitle = cashTitle;
	}
	public String getCashDesc(){
		return cashDesc;
	}
	public void setCashDesc(String cashDesc){
		this.cashDesc = cashDesc;
	}
	public String getCashImage(){
		return cashImage;
	}
	public void setCashImage(String cashImage){
		this.cashImage = cashImage;
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
package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: CashRecord.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class CashRecord extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 
	private Integer id;
	// 
	private Integer userId;
	// 
	private Date cashTime;
	// 
	private Double cashMoney;
	// 
	private String createPeople;
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
	public Date getCashTime(){
		return cashTime;
	}
	public void setCashTime(Date cashTime){
		this.cashTime = cashTime;
	}
	public Double getCashMoney(){
		return cashMoney;
	}
	public void setCashMoney(Double cashMoney){
		this.cashMoney = cashMoney;
	}
	public String getCreatePeople(){
		return createPeople;
	}
	public void setCreatePeople(String createPeople){
		this.createPeople = createPeople;
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
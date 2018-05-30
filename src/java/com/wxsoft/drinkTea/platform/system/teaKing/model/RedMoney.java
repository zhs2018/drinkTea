package com.wxsoft.drinkTea.platform.system.teaKing.model;

import java.util.Date;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;

import java.math.BigDecimal;


/**
 * @文件名称: RedMoney.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class RedMoney extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private Double moneyNum;
	//
	private Double moneyNums;
	//
	private Integer countWeb;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Double getMoneyNum(){
		return moneyNum;
	}
	public void setMoneyNum(Double moneyNum){
		this.moneyNum = moneyNum;
	}
	public Double getMoneyNums(){
		return moneyNums;
	}
	public void setMoneyNums(Double moneyNums){
		this.moneyNums = moneyNums;
	}
	public Integer getCountWeb(){
		return countWeb;
	}
	public void setCountWeb(Integer countWeb){
		this.countWeb = countWeb;
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
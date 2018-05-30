package com.wxsoft.drinkTea.platform.weixin.model;

import java.util.Date;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;

import java.math.BigDecimal;


/**
 * @文件名称: RedEnvelope.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class RedEnvelope extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private Integer userId;
	// 创建时间
	private Date answerTime;
	// 小包id
	private Integer packageId;
	//
	private Double money;
	// 一套题目的id
	private Integer subjectsId;
	// 0：扫码答题  2：题王争霸 1:20道题奖励
	private Integer type;
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
	public Date getAnswerTime(){
		return answerTime;
	}
	public void setAnswerTime(Date answerTime){
		this.answerTime = answerTime;
	}
	public Integer getPackageId(){
		return packageId;
	}
	public void setPackageId(Integer packageId){
		this.packageId = packageId;
	}
	public Double getMoney(){
		return money;
	}
	public void setMoney(Double money){
		this.money = money;
	}
	public Integer getSubjectsId(){
		return subjectsId;
	}
	public void setSubjectsId(Integer subjectsId){
		this.subjectsId = subjectsId;
	}
	public Integer getType(){
		return type;
	}
	public void setType(Integer type){
		this.type = type;
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
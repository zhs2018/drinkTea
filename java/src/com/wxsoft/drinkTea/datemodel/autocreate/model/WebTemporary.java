package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: WebTemporary.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class WebTemporary extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 
	private Integer id;
	// 
	private String userName;
	// 
	private String image;
	// 
	private Integer rightCount;
	// 
	private String allTime;
	// 
	private String member;
	// 
	private Double money;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getImage(){
		return image;
	}
	public void setImage(String image){
		this.image = image;
	}
	public Integer getRightCount(){
		return rightCount;
	}
	public void setRightCount(Integer rightCount){
		this.rightCount = rightCount;
	}
	public String getAllTime(){
		return allTime;
	}
	public void setAllTime(String allTime){
		this.allTime = allTime;
	}
	public String getMember(){
		return member;
	}
	public void setMember(String member){
		this.member = member;
	}
	public Double getMoney(){
		return money;
	}
	public void setMoney(Double money){
		this.money = money;
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
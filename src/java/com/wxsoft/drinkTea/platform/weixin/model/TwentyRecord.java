package com.wxsoft.drinkTea.platform.weixin.model;

import java.util.Date;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;

import java.math.BigDecimal;


/**
 * @文件名称: TwentyRecord.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class TwentyRecord extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 20道题的记录表
	private Integer id;
	//
	private Integer userId;
	// 20道题的开始时间
	private String startTime;
	//
	private String endTime;
	// 答完15道题的奖金
	private Float money;
	// 答题数量
	private Integer count;
	// 1可用，  2删除  每答完15道题就删除
	private Integer visable;
	// 普通分页
	private CommonPage page;


	//本次答题获取的奖金
	private Float nowMoney;
	//本次答对多少题
	private Integer nowRight;
	private Integer allRight;//总共答对题目数

	public Integer getAllRight() {
		return allRight;
	}
	public void setAllRight(Integer allRight) {
		this.allRight = allRight;
	}
	public Integer getNowRight() {
		return nowRight;
	}
	public void setNowRight(Integer nowRight) {
		this.nowRight = nowRight;
	}
	public Float getNowMoney() {
		return nowMoney;
	}
	public void setNowMoney(Float nowMoney) {
		this.nowMoney = nowMoney;
	}
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
	public String getStartTime(){
		return startTime;
	}
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}
	public String getEndTime(){
		return endTime;
	}
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}
	public Float getMoney(){
		return money;
	}
	public void setMoney(Float money){
		this.money = money;
	}
	public Integer getCount(){
		return count;
	}
	public void setCount(Integer count){
		this.count = count;
	}
	public Integer getVisable(){
		return visable;
	}
	public void setVisable(Integer visable){
		this.visable = visable;
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
	@Override
	public String toString() {
		return "TwentyRecord [id=" + id + ", userId=" + userId + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", money=" + money + ", count=" + count + ", visable=" + visable + ", page=" + page + ", nowMoney="
				+ nowMoney + ", nowRight=" + nowRight + ", orderBy=" + orderBy + "]";
	}


}
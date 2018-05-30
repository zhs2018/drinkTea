package com.wxsoft.drinkTea.platform.weixin.model;

import java.util.Date;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;

import java.math.BigDecimal;


/**
 * @文件名称: UserAnswer.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class UserAnswer extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 用戶答案
	private Integer id;
	//
	private Integer userId;
	// 小包id
	private Integer packetId;
	// 用戶答案
	private String userAnswer;
	//
	private Integer rightOrWrong;
	//
	private Integer subjectId;
	//0：没有统计  1：已经统计
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
	public Integer getPacketId(){
		return packetId;
	}
	public void setPacketId(Integer packetId){
		this.packetId = packetId;
	}
	public String getUserAnswer(){
		return userAnswer;
	}
	public void setUserAnswer(String userAnswer){
		this.userAnswer = userAnswer;
	}
	public Integer getRightOrWrong(){
		return rightOrWrong;
	}
	public void setRightOrWrong(Integer rightOrWrong){
		this.rightOrWrong = rightOrWrong;
	}
	public Integer getSubjectId(){
		return subjectId;
	}
	public void setSubjectId(Integer subjectId){
		this.subjectId = subjectId;
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
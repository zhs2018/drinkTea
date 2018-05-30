package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: SubUserAnswer.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class SubUserAnswer extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 用戶答案
	private Integer id;
	// 
	private Integer userId;
	// 小包id
	private Integer packetId;
	// 
	private Integer twentyRecordId;
	// 用戶答案存的是optionid
	private Integer userAnswer;
	// 0 是 对，1 是错
	private Integer rightOrWrong;
	// 
	private Integer visable;
	// 
	private Integer subjectKuId;
	// 
	private Integer subjectSign;
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
	public Integer getTwentyRecordId(){
		return twentyRecordId;
	}
	public void setTwentyRecordId(Integer twentyRecordId){
		this.twentyRecordId = twentyRecordId;
	}
	public Integer getUserAnswer(){
		return userAnswer;
	}
	public void setUserAnswer(Integer userAnswer){
		this.userAnswer = userAnswer;
	}
	public Integer getRightOrWrong(){
		return rightOrWrong;
	}
	public void setRightOrWrong(Integer rightOrWrong){
		this.rightOrWrong = rightOrWrong;
	}
	public Integer getVisable(){
		return visable;
	}
	public void setVisable(Integer visable){
		this.visable = visable;
	}
	public Integer getSubjectKuId(){
		return subjectKuId;
	}
	public void setSubjectKuId(Integer subjectKuId){
		this.subjectKuId = subjectKuId;
	}
	public Integer getSubjectSign(){
		return subjectSign;
	}
	public void setSubjectSign(Integer subjectSign){
		this.subjectSign = subjectSign;
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
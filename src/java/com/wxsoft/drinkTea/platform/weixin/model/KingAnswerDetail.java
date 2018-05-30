package com.wxsoft.drinkTea.platform.weixin.model;

import java.util.Date;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;
import com.wxsoft.drinkTea.platform.system.sysconfig.model.SubjectKing;

import java.math.BigDecimal;


/**
 * @文件名称: KingAnswerDetail.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class KingAnswerDetail extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 答题详情表
	private Integer id;
	// 题目id
	private Integer subId;
	// 答题总表的id
	private Integer answerId;
	// 赛事id，即具体参加的啥比赛
	private Integer kingId;
	// 用户是否答对  0答对了   1错了
	private Integer type;
	// 答每一道题的剩余时间
	private Integer overTime;
	// 删除标志
	private Integer visable;
	// 用户答案（ABCD ""）
	private String answer;
	//该道题目在整套题中的顺序
	private Integer num;
	// 普通分页
	private CommonPage page;

	private SubjectKing subject;


	public SubjectKing getSubject() {
		return subject;
	}
	public void setSubject(SubjectKing subject) {
		this.subject = subject;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getSubId(){
		return subId;
	}
	public void setSubId(Integer subId){
		this.subId = subId;
	}
	public Integer getAnswerId(){
		return answerId;
	}
	public void setAnswerId(Integer answerId){
		this.answerId = answerId;
	}
	public Integer getKingId(){
		return kingId;
	}
	public void setKingId(Integer kingId){
		this.kingId = kingId;
	}
	public Integer getType(){
		return type;
	}
	public void setType(Integer type){
		this.type = type;
	}
	public Integer getOverTime(){
		return overTime;
	}
	public void setOverTime(Integer overTime){
		this.overTime = overTime;
	}
	public Integer getVisable(){
		return visable;
	}
	public void setVisable(Integer visable){
		this.visable = visable;
	}
	public String getAnswer(){
		return answer;
	}
	public void setAnswer(String answer){
		this.answer = answer;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
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
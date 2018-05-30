package com.wxsoft.drinkTea.platform.weixin.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;


/**
 * @文件名称: KingUserAnswer.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class KingUserAnswer extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 争霸赛用户答题信息表
	private Integer id;
	// 用户id
	private Integer userId;
	// 赛事id
	private Integer kingId;
	// 剩余时间毫秒数的和
	private Integer overTime;
	// 题目id
	private Integer subKingId;
	// 答题时间格式（yyyyMMddHHssmm）
	private String answerTime;
	// 用户答题的答案，存的是optionId
	private Integer userAnwser;// -1 为用户没有答题
	// 删除标志
	private Integer visiable;
	// 普通分页
	private CommonPage page;

	private WebUser user;//当前答案的用户信息

	//用户后去的红包数
	private Double money;

	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}

	private String beginTime;

	private String endTime;

	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public WebUser getUser() {
		return user;
	}
	public void setUser(WebUser user) {
		this.user = user;
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
	public Integer getKingId(){
		return kingId;
	}
	public void setKingId(Integer kingId){
		this.kingId = kingId;
	}
	public Integer getOverTime(){
		return overTime;
	}
	public void setOverTime(Integer overTime){
		this.overTime = overTime;
	}
	public Integer getSubKingId(){
		return subKingId;
	}
	public void setSubKingId(Integer subKingId){
		this.subKingId = subKingId;
	}
	public String getAnswerTime(){
		return answerTime;
	}
	public void setAnswerTime(String answerTime){
		this.answerTime = answerTime;
	}
	public Integer getUserAnwser(){
		return userAnwser;
	}
	public void setUserAnwser(Integer userAnwser){
		this.userAnwser = userAnwser;
	}
	public Integer getVisiable(){
		return visiable;
	}
	public void setVisiable(Integer visiable){
		this.visiable = visiable;
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
		return "KingUserAnswer [id=" + id + ", userId=" + userId + ", kingId=" + kingId + ", overTime=" + overTime
				+ ", subKingId=" + subKingId + ", answerTime=" + answerTime + ", userAnwser=" + userAnwser
				+ ", visiable=" + visiable + ", page=" + page + ", user=" + user + ", orderBy=" + orderBy + "]";
	}


}
package com.wxsoft.drinkTea.platform.weixin.model;

import java.util.Date;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;
import com.wxsoft.drinkTea.framework.utils.MoneyUtils;

import java.math.BigDecimal;


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
	// 答对的数量
	private Integer rightCount;
	// 答错数量
	private Integer wrongCount;
	// 剩余时间秒数的和
	private Integer sumOverTime;
	// 答题时间格式（yyyyMMddHHssmm）
	private String answerTime;
	// 删除标志
	private Integer visiable;
	// 普通分页
	private CommonPage page;

	//分数
	private Integer score;

	//排名
	private Integer ranking;

	private WebUser user;


	public WebUser getUser() {
		return user;
	}
	public void setUser(WebUser user) {
		this.user = user;
	}
	public Integer getRanking() {
		return ranking;
	}
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
	public Integer getScore() {
		score = MoneyUtils.getScoreByUserAnser(this);
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
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
	public Integer getRightCount(){
		return rightCount;
	}
	public void setRightCount(Integer rightCount){
		this.rightCount = rightCount;
	}
	public Integer getWrongCount(){
		return wrongCount;
	}
	public void setWrongCount(Integer wrongCount){
		this.wrongCount = wrongCount;
	}
	public Integer getSumOverTime(){
		return sumOverTime;
	}
	public void setSumOverTime(Integer sumOverTime){
		this.sumOverTime = sumOverTime;
	}
	public String getAnswerTime(){
		return answerTime;
	}
	public void setAnswerTime(String answerTime){
		this.answerTime = answerTime;
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

	// 排序规则  right_count DESC,sum_over_time DESC,answer_time
	private String orderBy;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
package com.wxsoft.drinkTea.platform.weixin.model;

import java.util.Date;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;


/**
 * 争霸赛基本信息表
 * @author 王桐睿
 * com.wxsoft.drinkTea.platform.weixin.model
 * 2017年4月1日下午2:05:20
 */
public class KingBaseInfo extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 争霸赛开始时间
	private Date startTime;
	//争霸赛结束时间
	private Date endTime;
	// 各项赛事的规则
	private String role;
	// 争霸赛的类型  1是日赛  2是周赛   3是月赛  4是季度赛   5是年度决赛
	private Integer type;
	// 赛事创建时间
	private Date createTime;
	// 该赛事需要答题的数量
	private Integer num;
	// 该赛事需要答哪些题目，即题目id，中间用逗号隔开
	private String subIds;
	// 删除标志  1是可用， 2是删除
	private Integer visable;
	//添加题目的介绍
	private String intro;
	// 普通分页
	private CommonPage page;

	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Date getStartTime(){
		return startTime;
	}
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	public String getRole(){
		return role;
	}
	public void setRole(String role){
		this.role = role;
	}
	public Integer getType(){
		return type;
	}
	public void setType(Integer type){
		this.type = type;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Integer getNum(){
		return num;
	}
	public void setNum(Integer num){
		this.num = num;
	}
	public String getSubIds(){
		return subIds;
	}
	public void setSubIds(String subIds){
		this.subIds = subIds;
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
}
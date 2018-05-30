package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: RecommendFriend.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class RecommendFriend extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 
	private Integer id;
	// 
	private Integer recommendId;
	// 
	private Integer recommendedId;
	// 
	private Integer visible;
	// 
	private Date createTime;
	// 
	private Date delTime;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getRecommendId(){
		return recommendId;
	}
	public void setRecommendId(Integer recommendId){
		this.recommendId = recommendId;
	}
	public Integer getRecommendedId(){
		return recommendedId;
	}
	public void setRecommendedId(Integer recommendedId){
		this.recommendedId = recommendedId;
	}
	public Integer getVisible(){
		return visible;
	}
	public void setVisible(Integer visible){
		this.visible = visible;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getDelTime(){
		return delTime;
	}
	public void setDelTime(Date delTime){
		this.delTime = delTime;
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
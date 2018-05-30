package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: WebsTeb.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class WebsTeb extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 
	private Integer id;
	// 
	private String userName;
	// 
	private String image;
	// 
	private Integer answerAllcount;
	// 
	private Integer rightAllcount;
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
	public Integer getAnswerAllcount(){
		return answerAllcount;
	}
	public void setAnswerAllcount(Integer answerAllcount){
		this.answerAllcount = answerAllcount;
	}
	public Integer getRightAllcount(){
		return rightAllcount;
	}
	public void setRightAllcount(Integer rightAllcount){
		this.rightAllcount = rightAllcount;
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
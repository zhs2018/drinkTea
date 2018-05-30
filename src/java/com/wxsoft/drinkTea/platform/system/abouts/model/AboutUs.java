package com.wxsoft.drinkTea.platform.system.abouts.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;


/**
 * @文件名称: AboutUs.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class AboutUs extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String fielda;
	//
	private String content;
	//
	private String headline;
	//
	private String fieldb;
	//
	private String fieldc;
	//
	private String picture;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getFielda(){
		return fielda;
	}
	public void setFielda(String fielda){
		this.fielda = fielda;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getHeadline(){
		return headline;
	}
	public void setHeadline(String headline){
		this.headline = headline;
	}
	public String getFieldb(){
		return fieldb;
	}
	public void setFieldb(String fieldb){
		this.fieldb = fieldb;
	}
	public String getFieldc(){
		return fieldc;
	}
	public void setFieldc(String fieldc){
		this.fieldc = fieldc;
	}
	public String getPicture(){
		return picture;
	}
	public void setPicture(String picture){
		this.picture = picture;
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
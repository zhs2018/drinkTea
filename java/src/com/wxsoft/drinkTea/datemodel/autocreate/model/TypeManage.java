package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: TypeManage.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class TypeManage extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 分类管理
	private Integer id;
	// 
	private String typeName;
	// 
	private String typeSort;
	// 
	private String typeListImg;
	// 
	private String typeListImgFirst;
	// 
	private Date addTime;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getTypeName(){
		return typeName;
	}
	public void setTypeName(String typeName){
		this.typeName = typeName;
	}
	public String getTypeSort(){
		return typeSort;
	}
	public void setTypeSort(String typeSort){
		this.typeSort = typeSort;
	}
	public String getTypeListImg(){
		return typeListImg;
	}
	public void setTypeListImg(String typeListImg){
		this.typeListImg = typeListImg;
	}
	public String getTypeListImgFirst(){
		return typeListImgFirst;
	}
	public void setTypeListImgFirst(String typeListImgFirst){
		this.typeListImgFirst = typeListImgFirst;
	}
	public Date getAddTime(){
		return addTime;
	}
	public void setAddTime(Date addTime){
		this.addTime = addTime;
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
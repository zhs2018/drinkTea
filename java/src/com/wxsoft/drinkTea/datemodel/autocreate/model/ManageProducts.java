package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: ManageProducts.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class ManageProducts extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 商品表
	private Integer id;
	// 
	private String name;
	// 
	private Double price;
	// 
	private Double beforePrice;
	// 
	private Double nowPrice;
	// 
	private String picture;
	// 
	private String introduce;
	// 
	private String type;
	// 
	private String specifications;
	// 
	private Double cost;
	// 
	private String typeSort;
	// 
	private Integer restGoods;
	// 
	private String stat;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public Double getPrice(){
		return price;
	}
	public void setPrice(Double price){
		this.price = price;
	}
	public Double getBeforePrice(){
		return beforePrice;
	}
	public void setBeforePrice(Double beforePrice){
		this.beforePrice = beforePrice;
	}
	public Double getNowPrice(){
		return nowPrice;
	}
	public void setNowPrice(Double nowPrice){
		this.nowPrice = nowPrice;
	}
	public String getPicture(){
		return picture;
	}
	public void setPicture(String picture){
		this.picture = picture;
	}
	public String getIntroduce(){
		return introduce;
	}
	public void setIntroduce(String introduce){
		this.introduce = introduce;
	}
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getSpecifications(){
		return specifications;
	}
	public void setSpecifications(String specifications){
		this.specifications = specifications;
	}
	public Double getCost(){
		return cost;
	}
	public void setCost(Double cost){
		this.cost = cost;
	}
	public String getTypeSort(){
		return typeSort;
	}
	public void setTypeSort(String typeSort){
		this.typeSort = typeSort;
	}
	public Integer getRestGoods(){
		return restGoods;
	}
	public void setRestGoods(Integer restGoods){
		this.restGoods = restGoods;
	}
	public String getStat(){
		return stat;
	}
	public void setStat(String stat){
		this.stat = stat;
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
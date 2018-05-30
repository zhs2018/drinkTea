package com.wxsoft.drinkTea.platform.system.login.model;

import java.math.BigDecimal;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;


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
	private Integer saleCount;
	//
	private Double beforePrice;
	//
	private BigDecimal nowPrice;
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

	private Integer count;

	private Integer valiable;

	//1:公益商品  2:抽獎商品   3:普通商品
	private Integer sign;

	//是否为免费领取的商品1:免费领取 2为不免费领取
	private Integer tab;

	public Integer getTab() {
		return tab;
	}
	public void setTab(Integer tab) {
		this.tab = tab;
	}
	public Integer getSign() {
		return sign;
	}
	public void setSign(Integer sign) {
		this.sign = sign;
	}
	public Integer getValiable() {
		return valiable;
	}
	public void setValiable(Integer valiable) {
		this.valiable = valiable;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
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

	public Integer getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}
	public Double getBeforePrice(){
		return beforePrice;
	}
	public void setBeforePrice(Double beforePrice){
		this.beforePrice = beforePrice;
	}
	public BigDecimal getNowPrice(){
		return nowPrice;
	}
	public void setNowPrice(BigDecimal bigDecimal){
		this.nowPrice = bigDecimal;
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

	private String pictureOne;
	private String pictureTwo;

	public String getPictureOne() {
		return pictureOne;
	}
	public void setPictureOne(String pictureOne) {
		this.pictureOne = pictureOne;
	}
	public String getPictureTwo() {
		return pictureTwo;
	}
	public void setPictureTwo(String pictureTwo) {
		this.pictureTwo = pictureTwo;
	}

	private Integer cur;

	public Integer getCur() {
		return cur;
	}
	public void setCur(Integer cur) {
		this.cur = cur;
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
package com.wxsoft.drinkTea.platform.system.order.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;


/**
 * @文件名称: GoodsOrder.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class GoodsOrder extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private Integer goodsId;
	//
	private Integer orderId;
	//
	private Integer goodsCount;
	// 普通分页
	private CommonPage page;

	private ManageProducts manageProducts;

	private ProductOrder productOrder;

	private WebUser webUser;


	public ManageProducts getManageProducts() {
		return manageProducts;
	}
	public void setManageProducts(ManageProducts manageProducts) {
		this.manageProducts = manageProducts;
	}
	public ProductOrder getProductOrder() {
		return productOrder;
	}
	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}
	public WebUser getWebUser() {
		return webUser;
	}
	public void setWebUser(WebUser webUser) {
		this.webUser = webUser;
	}

	private Integer userId;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getGoodsId(){
		return goodsId;
	}
	public void setGoodsId(Integer goodsId){
		this.goodsId = goodsId;
	}
	public Integer getOrderId(){
		return orderId;
	}
	public void setOrderId(Integer orderId){
		this.orderId = orderId;
	}
	public Integer getGoodsCount(){
		return goodsCount;
	}
	public void setGoodsCount(Integer goodsCount){
		this.goodsCount = goodsCount;
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
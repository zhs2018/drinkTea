package com.wxsoft.drinkTea.platform.weixin.model;

import java.util.Date;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.order.model.GoodsOrder;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;

import java.math.BigDecimal;


/**
 * @文件名称: RefundMessage.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class RefundMessage extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private Integer userId;
	//
	private Integer orderId;
	//
	private Integer addressId;
	//
	private String refundMoney;
	//
	private Date refTime;
	//
	private String refundReason;
	//
	private String remarkMesssage;
	// 普通分页
	private CommonPage page;

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
	public Integer getOrderId(){
		return orderId;
	}
	public void setOrderId(Integer orderId){
		this.orderId = orderId;
	}
	public Integer getAddressId(){
		return addressId;
	}
	public void setAddressId(Integer addressId){
		this.addressId = addressId;
	}
	public String getRefundMoney(){
		return refundMoney;
	}
	public void setRefundMoney(String refundMoney){
		this.refundMoney = refundMoney;
	}
	public Date getRefTime(){
		return refTime;
	}
	public void setRefTime(Date refTime){
		this.refTime = refTime;
	}
	public String getRefundReason(){
		return refundReason;
	}
	public void setRefundReason(String refundReason){
		this.refundReason = refundReason;
	}
	public String getRemarkMesssage(){
		return remarkMesssage;
	}
	public void setRemarkMesssage(String remarkMesssage){
		this.remarkMesssage = remarkMesssage;
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

	private ProductOrder productOrder;

	private ManageProducts manageProducts;
	private GoodsOrder goodsOrder;


	public GoodsOrder getGoodsOrder() {
		return goodsOrder;
	}
	public void setGoodsOrder(GoodsOrder goodsOrder) {
		this.goodsOrder = goodsOrder;
	}
	public ProductOrder getProductOrder() {
		return productOrder;
	}
	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}
	public ManageProducts getManageProducts() {
		return manageProducts;
	}
	public void setManageProducts(ManageProducts manageProducts) {
		this.manageProducts = manageProducts;
	}


}
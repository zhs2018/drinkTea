package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: ProductOrder.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class ProductOrder extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 订单
	private Integer id;
	// 
	private Integer orderNumber;
	// 
	private Integer userId;
	// 
	private Integer giftBoxId;
	// 
	private Integer goodsId;
	// 购买包数
	private Integer count;
	// 总价格
	private BigDecimal price;
	// 订单时间
	private Date orderTime;
	// 支付时间
	private Date applyTime;
	// 发货时间
	private Date goodsTime;
	// 
	private String goodsAddress;
	// 
	private Integer addressId;
	// 
	private Integer orderState;
	// 
	private String customerMessage;
	// 
	private Integer orderSerialNum;
	// 
	private Integer applyMethod;
	// 临时价格
	private BigDecimal tempprice;
	// 
	private Integer companyId;
	// 
	private Date startTime;
	// 
	private Date endTime;
	// 收货时间
	private Date acceptTime;
	// 财务id
	private Integer caiwuId;
	// 
	private Integer shouhouId;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getOrderNumber(){
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber){
		this.orderNumber = orderNumber;
	}
	public Integer getUserId(){
		return userId;
	}
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	public Integer getGiftBoxId(){
		return giftBoxId;
	}
	public void setGiftBoxId(Integer giftBoxId){
		this.giftBoxId = giftBoxId;
	}
	public Integer getGoodsId(){
		return goodsId;
	}
	public void setGoodsId(Integer goodsId){
		this.goodsId = goodsId;
	}
	public Integer getCount(){
		return count;
	}
	public void setCount(Integer count){
		this.count = count;
	}
	public BigDecimal getPrice(){
		return price;
	}
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	public Date getOrderTime(){
		return orderTime;
	}
	public void setOrderTime(Date orderTime){
		this.orderTime = orderTime;
	}
	public Date getApplyTime(){
		return applyTime;
	}
	public void setApplyTime(Date applyTime){
		this.applyTime = applyTime;
	}
	public Date getGoodsTime(){
		return goodsTime;
	}
	public void setGoodsTime(Date goodsTime){
		this.goodsTime = goodsTime;
	}
	public String getGoodsAddress(){
		return goodsAddress;
	}
	public void setGoodsAddress(String goodsAddress){
		this.goodsAddress = goodsAddress;
	}
	public Integer getAddressId(){
		return addressId;
	}
	public void setAddressId(Integer addressId){
		this.addressId = addressId;
	}
	public Integer getOrderState(){
		return orderState;
	}
	public void setOrderState(Integer orderState){
		this.orderState = orderState;
	}
	public String getCustomerMessage(){
		return customerMessage;
	}
	public void setCustomerMessage(String customerMessage){
		this.customerMessage = customerMessage;
	}
	public Integer getOrderSerialNum(){
		return orderSerialNum;
	}
	public void setOrderSerialNum(Integer orderSerialNum){
		this.orderSerialNum = orderSerialNum;
	}
	public Integer getApplyMethod(){
		return applyMethod;
	}
	public void setApplyMethod(Integer applyMethod){
		this.applyMethod = applyMethod;
	}
	public BigDecimal getTempprice(){
		return tempprice;
	}
	public void setTempprice(BigDecimal tempprice){
		this.tempprice = tempprice;
	}
	public Integer getCompanyId(){
		return companyId;
	}
	public void setCompanyId(Integer companyId){
		this.companyId = companyId;
	}
	public Date getStartTime(){
		return startTime;
	}
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	public Date getEndTime(){
		return endTime;
	}
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	public Date getAcceptTime(){
		return acceptTime;
	}
	public void setAcceptTime(Date acceptTime){
		this.acceptTime = acceptTime;
	}
	public Integer getCaiwuId(){
		return caiwuId;
	}
	public void setCaiwuId(Integer caiwuId){
		this.caiwuId = caiwuId;
	}
	public Integer getShouhouId(){
		return shouhouId;
	}
	public void setShouhouId(Integer shouhouId){
		this.shouhouId = shouhouId;
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
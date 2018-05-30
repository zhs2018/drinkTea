package com.wxsoft.drinkTea.platform.system.shouhou.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;

/**
 * 售后的实体类
 * @author 王桐睿
 * com.wxsoft.drinkTea.platform.system.shouhou.model
 * 2017年4月6日上午9:01:08
 */
public class ShouhouBean extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 售后服务
	private Integer id;
	//
	private Integer goodsId;
	//
	private Integer userId;
	//
	private Integer money;
	//
	private String reason;
	//1审核中 70审核通过 80审核不通过
	private Integer stat;

	private Long orderNumber;

	private String userName;

	private String goodsName;

	private String remark;

	private String noReason;



	public String getNoReason() {
		return noReason;
	}
	public void setNoReason(String noReason) {
		this.noReason = noReason;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public String getMpName() {
		return mpName;
	}
	public void setMpName(String mpName) {
		this.mpName = mpName;
	}
	public String getWuUserName() {
		return wuUserName;
	}
	public void setWuUserName(String wuUserName) {
		this.wuUserName = wuUserName;
	}
	public String getWuPhone() {
		return wuPhone;
	}
	public void setWuPhone(String wuPhone) {
		this.wuPhone = wuPhone;
	}

	private Integer price;
	private Integer orderState;//订单状态
	private String mpName;
	private String wuUserName;
	private String wuPhone;

	private Integer orderId;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	// 普通分页
	private CommonPage page;

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
	public Integer getUserId(){
		return userId;
	}
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	public Integer getMoney(){
		return money;
	}
	public void setMoney(Integer money){
		this.money = money;
	}
	public String getReason(){
		return reason;
	}
	public void setReason(String reason){
		this.reason = reason;
	}
	public Integer getStat(){
		return stat;
	}
	public void setStat(Integer stat){
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
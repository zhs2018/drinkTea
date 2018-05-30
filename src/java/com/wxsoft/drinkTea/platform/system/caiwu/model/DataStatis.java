package com.wxsoft.drinkTea.platform.system.caiwu.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;

public class DataStatis extends BaseBean{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	private Double totalPrice;

	private String orderTime;
	private String applyTime;


	public Double getTotalPrice() {
		return totalPrice;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}


}

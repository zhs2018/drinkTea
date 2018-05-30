package com.wxsoft.drinkTea.platform.weixin.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;

public class OrderModel extends BaseBean{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

    private String telPhone;

    private Integer addressId;

    private String goodName;
    private Integer goodId;

    private Double nowPrice;

    private Integer count;

    private Double redMoney;

    private Double money;

    private String customerMessage;

    private String price;

    private Double BeforePrice;



	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Double getBeforePrice() {
		return BeforePrice;
	}

	public void setBeforePrice(Double beforePrice) {
		BeforePrice = beforePrice;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public Double getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(Double nowPrice) {
		this.nowPrice = nowPrice;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getRedMoney() {
		return redMoney;
	}

	public void setRedMoney(Double redMoney) {
		this.redMoney = redMoney;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getCustomerMessage() {
		return customerMessage;
	}

	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderModel [userName=" + userName + ", telPhone=" + telPhone + ", addressId=" + addressId
				+ ", goodName=" + goodName + ", goodId=" + goodId + ", nowPrice=" + nowPrice + ", count=" + count
				+ ", redMoney=" + redMoney + ", money=" + money + ", customerMessage=" + customerMessage + ", price="
				+ price + ", BeforePrice=" + BeforePrice + "]";
	}


}

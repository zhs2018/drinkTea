package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: UserAddress.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class UserAddress extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 用戶地址
	private Integer id;
	// 
	private Integer userId;
	// 
	private String province;
	// 
	private String city;
	// 
	private String area;
	// 刪除标记
	private String deleteSign;
	// 地址详情
	private String addressDetails;
	// 
	private String userName;
	// 
	private Integer telPhone;
	// 地址状态
	private Integer status;
	// 地址状态（用于in条件）
	private String statusForIn;
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
	public String getProvince(){
		return province;
	}
	public void setProvince(String province){
		this.province = province;
	}
	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}
	public String getArea(){
		return area;
	}
	public void setArea(String area){
		this.area = area;
	}
	public String getDeleteSign(){
		return deleteSign;
	}
	public void setDeleteSign(String deleteSign){
		this.deleteSign = deleteSign;
	}
	public String getAddressDetails(){
		return addressDetails;
	}
	public void setAddressDetails(String addressDetails){
		this.addressDetails = addressDetails;
	}
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public Integer getTelPhone(){
		return telPhone;
	}
	public void setTelPhone(Integer telPhone){
		this.telPhone = telPhone;
	}
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public String getStatusForIn(){
		return statusForIn;
	}
	public void setStatusForIn(String statusForIn){
		this.statusForIn = statusForIn;
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
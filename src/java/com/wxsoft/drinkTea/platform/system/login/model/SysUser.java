package com.wxsoft.drinkTea.platform.system.login.model;

import java.util.Date;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;


/**
 * 用户表，用于登录等功能
 * @author 王桐睿
 * com.wxsoft.drinkTea.platform.system.login.model
 * 2017年3月21日下午3:47:25
 */
public class SysUser extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String userName;
	//
	private String password;
	//
	private String phone;
	//
	private String marker;
	//
	private String image;
	//
	private String addressId;
	// 权限id
	private Integer roleId;
	// 普通分页
	private CommonPage page;
	//员工入职时间
	private Date createTime;
	//删除标志 1是有用 2是删除
	private Integer visable;
	//工号
	private String jobNumber;

	private String roleName;

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	//会员数量
	private Integer count;


	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public Integer getVisable() {
		return visable;
	}
	public void setVisable(Integer visable) {
		this.visable = visable;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPhone(){
		return phone;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getMarker(){
		return marker;
	}
	public void setMarker(String marker){
		this.marker = marker;
	}
	public String getImage(){
		return image;
	}
	public void setImage(String image){
		this.image = image;
	}
	public String getAddressId(){
		return addressId;
	}
	public void setAddressId(String addressId){
		this.addressId = addressId;
	}
	public Integer getRoleId(){
		return roleId;
	}
	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
	public CommonPage getPage(){
		return page;
	}
	public void setPage(CommonPage page){
		this.page = page;
	}

	private Integer orderId;

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	private Long orderNumber;

	private Double price;

	private Date orderTime;

	private String name;

	private String telPhone;

	private String customerMessage;

	private Integer orderState;

	private String uaName;
	private String uaPhone;
	private String mpName;

	private Date endTime;
	private Date startTime;

	private String userAddress;

	private Integer tabs;


	public Integer getTabs() {
		return tabs;
	}
	public void setTabs(Integer tabs) {
		this.tabs = tabs;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getUaName() {
		return uaName;
	}
	public void setUaName(String uaName) {
		this.uaName = uaName;
	}
	public String getUaPhone() {
		return uaPhone;
	}
	public void setUaPhone(String uaPhone) {
		this.uaPhone = uaPhone;
	}
	public String getMpName() {
		return mpName;
	}
	public void setMpName(String mpName) {
		this.mpName = mpName;
	}
	public Long getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getCustomerMessage() {
		return customerMessage;
	}
	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}


private Integer ty;
private String  ttime;



	public Integer getTy() {
	return ty;
}
public void setTy(Integer ty) {
	this.ty = ty;
}
public String getTtime() {
	return ttime;
}
public void setTtime(String ttime) {
	this.ttime = ttime;
}

	// 排序规则
	private String orderBy;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	@Override
	public String toString() {
		return "SysUser [id=" + id + ", userName=" + userName + ", password=" + password + ", phone=" + phone
				+ ", marker=" + marker + ", image=" + image + ", addressId=" + addressId + ", roleId=" + roleId
				+ ", page=" + page + ", createTime=" + createTime + ", visable=" + visable + ", jobNumber=" + jobNumber
				+ ", orderBy=" + orderBy + "]";
	}

}
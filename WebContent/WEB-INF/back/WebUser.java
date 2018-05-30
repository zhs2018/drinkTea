package com.wxsoft.drinkTea.platform.weixin.model;

import java.util.Date;
import java.util.List;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;

import java.math.BigDecimal;


/**
 * @文件名称: WebUser.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class WebUser extends BaseBean {
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
	//
	private Integer roleId;
	// 用户加入时间
	private Date createtime;
	// 删除标志 1有用    2删除
	private Integer visable;
	//
	private String openId;
	//
	private Integer sex;
	//
	private Date focusTime;
	// 普通分页
	private CommonPage page;

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
	public Date getCreatetime(){
		return createtime;
	}
	public void setCreatetime(Date createtime){
		this.createtime = createtime;
	}
	public Integer getVisable(){
		return visable;
	}
	public void setVisable(Integer visable){
		this.visable = visable;
	}
	public String getOpenId(){
		return openId;
	}
	public void setOpenId(String openId){
		this.openId = openId;
	}
	public Integer getSex(){
		return sex;
	}
	public void setSex(Integer sex){
		this.sex = sex;
	}
	public Date getFocusTime(){
		return focusTime;
	}
	public void setFocusTime(Date focusTime){
		this.focusTime = focusTime;
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
	//收货地址
	private String address;

	//收货地址集合
	private List<UserAddress> userAddresses;

	public List<UserAddress> getUserAddresses() {
		return userAddresses;
	}
	public void setUserAddresses(List<UserAddress> userAddresses) {
		this.userAddresses = userAddresses;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
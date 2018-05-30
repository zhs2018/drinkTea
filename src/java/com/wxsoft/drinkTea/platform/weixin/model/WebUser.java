package com.wxsoft.drinkTea.platform.weixin.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;
import com.wxsoft.drinkTea.platform.system.cash.model.CashRecord;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.login.model.SysUser;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;


/**
 * @文件名称: WebUser.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：xyc
 *
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
	private Integer addressId;
	//
	private Integer roleId;
	// 用户加入时间，格式yyyyMMddHHmmss
	private String createtime;
	// 删除标志 1有用    2删除
	private Integer visable;
	//
	private String openId;
	//
	private Integer sex;
	// 毫秒数
	private String focusTime;
	// 可答题数量（15道题算1）
	private Integer answerCount;
	// 总消费金额
	private Float money;
	// 会员对应的员工id
	private Integer sysUserId;
	// age是年龄段，存的是标志
	private Integer age;
	//用户分享二维码
	private String qrcode;
	//真实姓名
	private String name;
	// 普通分页
	private CommonPage page;

   private Double moneyAll;


	public Double getMoneyAll() {
	return moneyAll;
}

public void setMoneyAll(Double moneyAll) {
	this.moneyAll = moneyAll;
}




	//用户抽奖次数
	private Integer drawCount;

	//用户免费领取的次数
	private Integer freeRec;

	//用户提取红包记录
	private CashRecord cashRecord;

	//用户提取的总红包数
	private Double cashMoney;

	//用户提现办理人
	private String createPeople;

	private String answerTime;

	public String getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}

	public String getCreatePeople() {
		return createPeople;
	}

	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}

	public Double getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(Double cashMoney) {
		this.cashMoney = cashMoney;
	}

	public CashRecord getCashRecord() {
		return cashRecord;
	}

	public void setCashRecord(CashRecord cashRecord) {
		this.cashRecord = cashRecord;
	}

	public Integer getFreeRec() {
		return freeRec;
	}

	public void setFreeRecevice(Integer freeRec) {
		this.freeRec = freeRec;
	}

	public Integer getDrawCount() {
		return drawCount;
	}

	public void setDrawCount(Integer drawCount) {
		this.drawCount = drawCount;
	}




	//绑定的员工信息
	private SysUser sysUser;

	private String beginTime;
	private String endTime;

	private Double restMoney;

	public Double getRestMoney() {
		return restMoney;
	}
	public void setRestMoney(Double restMoney) {
		this.restMoney = restMoney;
	}

	//答题情况 （用于扫码答题情况查询）
	private Integer answerAllCount;
	private Integer rightAllCount;
	//红包总钱数
	private Double redMoney;

	//答题情况 （用于茶王答题情况查询）
	//答对的数量
	private Integer rightCount;
	//剩余的总时间

	private Integer allCount;

	public Integer getAllCount() {
		return allCount;
	}

	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}




	private String allTime;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}



	private UserAddress userAddress;

	private ManageProducts manageProducts;

	private ProductOrder productOrder;

	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
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

	public Integer getRightCount() {
		return rightCount;
	}
	public void setRightCount(Integer rightCount) {
		this.rightCount = rightCount;
	}
	public String getAllTime() {
		return allTime;
	}
	public void setAllTime(String allTime) {
		this.allTime = allTime;
	}
	public Double getRedMoney() {
		return redMoney;
	}
	public void setRedMoney(Double redMoney) {
		this.redMoney = redMoney;
	}
	public Integer getAnswerAllCount() {
		return answerAllCount;
	}
	public void setAnswerAllCount(Integer answerAllCount) {
		this.answerAllCount = answerAllCount;
	}
	public Integer getRightAllCount() {
		return rightAllCount;
	}
	public void setRightAllCount(Integer rightAllCount) {
		this.rightAllCount = rightAllCount;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
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
	public Integer getAddressId(){
		return addressId;
	}
	public void setAddressId(Integer addressId){
		this.addressId = addressId;
	}
	public Integer getRoleId(){
		return roleId;
	}
	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
	public String getCreatetime(){
		return createtime;
	}
	public void setCreatetime(String createtime){
		this.createtime = createtime;
	}
	public Integer getVisable(){
		return visable;
	}
	public void setVisable(Integer visable){
		this.visable = visable;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
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
	public String getFocusTime(){
		return focusTime;
	}
	public void setFocusTime(String focusTime){
		this.focusTime = focusTime;
	}
	public Integer getAnswerCount(){
		return answerCount;
	}
	public void setAnswerCount(Integer answerCount){
		this.answerCount = answerCount;
	}
	public Float getMoney(){
		return money;
	}
	public void setMoney(Float money){
		this.money = money;
	}
	public Integer getSysUserId(){
		return sysUserId;
	}
	public void setSysUserId(Integer sysUserId){
		this.sysUserId = sysUserId;
	}
	public Integer getAge(){
		return age;
	}
	public void setAge(Integer age){
		this.age = age;
	}
	public CommonPage getPage(){
		return page;
	}
	public void setPage(CommonPage page){
		this.page = page;
	}

	private String member;
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}

	private Double mo;

	public Double getMo() {
		return mo;
	}
	public void setMo(Double mo) {
		this.mo = mo;
	}

	//用户是否阅读消息
	private Integer rederSign;

	public Integer getRederSign() {
		return rederSign;
	}
	public void setRederSign(Integer rederSign) {
		this.rederSign = rederSign;
	}
	//红包总金额
	private double moneyX;

	//分红总金额
	private double moneyY;

	//分红加红包总金额
	private double moneyXY;




	public double getMoneyX() {
		return moneyX;
	}

	public void setMoneyX(double moneyX) {
		this.moneyX = moneyX;
	}

	public double getMoneyY() {
		return moneyY;
	}

	public void setMoneyY(double moneyY) {
		this.moneyY = moneyY;
	}

	public double getMoneyXY() {
		return moneyXY;
	}

	public void setMoneyXY(double moneyXY) {
		this.moneyXY = moneyXY;
	}

	public void setFreeRec(Integer freeRec) {
		this.freeRec = freeRec;
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
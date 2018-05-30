package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: BaseConfig.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class BaseConfig extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 系统管理中的基本配置
	private Integer id;
	// 
	private String companyName;
	// 联系人
	private String contactName;
	// 
	private String tel;
	// 
	private String appid;
	// 
	private String appsecret;
	// 
	private Date openTime;
	// 
	private String accountConnection;
	// 
	private Integer ghId;
	// 
	private String money;
	// 
	private String apply;
	// 
	private String qrCode;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getCompanyName(){
		return companyName;
	}
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	public String getContactName(){
		return contactName;
	}
	public void setContactName(String contactName){
		this.contactName = contactName;
	}
	public String getTel(){
		return tel;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	public String getAppid(){
		return appid;
	}
	public void setAppid(String appid){
		this.appid = appid;
	}
	public String getAppsecret(){
		return appsecret;
	}
	public void setAppsecret(String appsecret){
		this.appsecret = appsecret;
	}
	public Date getOpenTime(){
		return openTime;
	}
	public void setOpenTime(Date openTime){
		this.openTime = openTime;
	}
	public String getAccountConnection(){
		return accountConnection;
	}
	public void setAccountConnection(String accountConnection){
		this.accountConnection = accountConnection;
	}
	public Integer getGhId(){
		return ghId;
	}
	public void setGhId(Integer ghId){
		this.ghId = ghId;
	}
	public String getMoney(){
		return money;
	}
	public void setMoney(String money){
		this.money = money;
	}
	public String getApply(){
		return apply;
	}
	public void setApply(String apply){
		this.apply = apply;
	}
	public String getQrCode(){
		return qrCode;
	}
	public void setQrCode(String qrCode){
		this.qrCode = qrCode;
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
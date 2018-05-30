package com.wxsoft.drinkTea.platform.system.company.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;


/**
 * @文件名称: CompanyPro.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class CompanyPro extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String name;
	//
	private String address;
	//
	private Integer companyPhone;
	//
	private String picture;
	//
	private String content;
	//
	private String people;
	//
	private String phone;
	//
	private String qrCode;
	//
	private Integer isCashOnDelivery;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public Integer getCompanyPhone(){
		return companyPhone;
	}
	public void setCompanyPhone(Integer companyPhone){
		this.companyPhone = companyPhone;
	}
	public String getPicture(){
		return picture;
	}
	public void setPicture(String picture){
		this.picture = picture;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getPeople(){
		return people;
	}
	public void setPeople(String people){
		this.people = people;
	}
	public String getPhone(){
		return phone;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getQrCode(){
		return qrCode;
	}
	public void setQrCode(String qrCode){
		this.qrCode = qrCode;
	}
	public Integer getIsCashOnDelivery(){
		return isCashOnDelivery;
	}
	public void setIsCashOnDelivery(Integer isCashOnDelivery){
		this.isCashOnDelivery = isCashOnDelivery;
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
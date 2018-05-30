package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: PackageSubject.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class PackageSubject extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 
	private Integer smlPakId;
	// 
	private Integer subId;
	// 
	private Integer id;
	// 普通分页
	private CommonPage page;

	public Integer getSmlPakId(){
		return smlPakId;
	}
	public void setSmlPakId(Integer smlPakId){
		this.smlPakId = smlPakId;
	}
	public Integer getSubId(){
		return subId;
	}
	public void setSubId(Integer subId){
		this.subId = subId;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
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
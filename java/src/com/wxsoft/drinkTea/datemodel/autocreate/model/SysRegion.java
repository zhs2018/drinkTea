package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: SysRegion.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class SysRegion extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 地市编码
	private String regionid;
	// 
	private String regionname;
	// 
	private String parentid;
	// 保留字段
	private Integer sn;
	// 层级从1开始
	private Integer levels;
	// 拼音码
	private String alphabetic;
	// 普通分页
	private CommonPage page;

	public String getRegionid(){
		return regionid;
	}
	public void setRegionid(String regionid){
		this.regionid = regionid;
	}
	public String getRegionname(){
		return regionname;
	}
	public void setRegionname(String regionname){
		this.regionname = regionname;
	}
	public String getParentid(){
		return parentid;
	}
	public void setParentid(String parentid){
		this.parentid = parentid;
	}
	public Integer getSn(){
		return sn;
	}
	public void setSn(Integer sn){
		this.sn = sn;
	}
	public Integer getLevels(){
		return levels;
	}
	public void setLevels(Integer levels){
		this.levels = levels;
	}
	public String getAlphabetic(){
		return alphabetic;
	}
	public void setAlphabetic(String alphabetic){
		this.alphabetic = alphabetic;
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
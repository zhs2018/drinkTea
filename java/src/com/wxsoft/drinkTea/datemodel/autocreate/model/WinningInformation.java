package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: WinningInformation.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class WinningInformation extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 
	private Integer id;
	// 
	private Integer userid;
	// 
	private Integer prizeid;
	// 
	private Date time;
	// 
	private Integer visible;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getUserid(){
		return userid;
	}
	public void setUserid(Integer userid){
		this.userid = userid;
	}
	public Integer getPrizeid(){
		return prizeid;
	}
	public void setPrizeid(Integer prizeid){
		this.prizeid = prizeid;
	}
	public Date getTime(){
		return time;
	}
	public void setTime(Date time){
		this.time = time;
	}
	public Integer getVisible(){
		return visible;
	}
	public void setVisible(Integer visible){
		this.visible = visible;
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
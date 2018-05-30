package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: LuckDraw.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class LuckDraw extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 幸运抽奖
	private Integer id;
	// 
	private String title;
	// 
	private String intro;
	// 
	private Date time;
	// 
	private Integer sign;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getIntro(){
		return intro;
	}
	public void setIntro(String intro){
		this.intro = intro;
	}
	public Date getTime(){
		return time;
	}
	public void setTime(Date time){
		this.time = time;
	}
	public Integer getSign(){
		return sign;
	}
	public void setSign(Integer sign){
		this.sign = sign;
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
package com.wxsoft.drinkTea.platform.weixin.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;


/**
 * @文件名称: SubOption.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class SubOption extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 题目id
	private Integer subId;
	// 一个选项
	private String option;
	// 0 是扫码答题  1是茶王争霸赛
	private Integer type;
	//
	private Integer visable;
	// 普通分页
	private CommonPage page;

	private String[] ops;
   //区分扫码答题中种类（1），省份（2），季节（3）
	private Integer sign;

	public Integer getSign() {
		return sign;
	}
	public void setSign(Integer sign) {
		this.sign = sign;
	}
	public String[] getOps() {
		return ops;
	}
	public void setOps(String[] ops) {
		this.ops = ops;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getSubId(){
		return subId;
	}
	public void setSubId(Integer subId){
		this.subId = subId;
	}
	public String getOption(){
		return option;
	}
	public void setOption(String option){
		this.option = option;
	}
	public Integer getType(){
		return type;
	}
	public void setType(Integer type){
		this.type = type;
	}
	public Integer getVisable(){
		return visable;
	}
	public void setVisable(Integer visable){
		this.visable = visable;
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
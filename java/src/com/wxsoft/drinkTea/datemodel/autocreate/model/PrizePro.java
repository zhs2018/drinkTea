package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: PrizePro.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class PrizePro extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 奖品
	private Integer id;
	// 
	private String prizeName;
	// 
	private Integer restNum;
	// 
	private String prizeType;
	// 
	private String prizeImg;
	// 
	private String prizeNum;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getPrizeName(){
		return prizeName;
	}
	public void setPrizeName(String prizeName){
		this.prizeName = prizeName;
	}
	public Integer getRestNum(){
		return restNum;
	}
	public void setRestNum(Integer restNum){
		this.restNum = restNum;
	}
	public String getPrizeType(){
		return prizeType;
	}
	public void setPrizeType(String prizeType){
		this.prizeType = prizeType;
	}
	public String getPrizeImg(){
		return prizeImg;
	}
	public void setPrizeImg(String prizeImg){
		this.prizeImg = prizeImg;
	}
	public String getPrizeNum(){
		return prizeNum;
	}
	public void setPrizeNum(String prizeNum){
		this.prizeNum = prizeNum;
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
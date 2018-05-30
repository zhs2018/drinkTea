package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: SubjectKu.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class SubjectKu extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 题库
	private Integer id;
	// 
	private Integer subNumber;
	// 
	private String name;
	// 
	private String optionA;
	// 
	private String optionB;
	// 
	private String optionC;
	// 
	private String optionD;
	// 
	private String answer;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getSubNumber(){
		return subNumber;
	}
	public void setSubNumber(Integer subNumber){
		this.subNumber = subNumber;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getOptionA(){
		return optionA;
	}
	public void setOptionA(String optionA){
		this.optionA = optionA;
	}
	public String getOptionB(){
		return optionB;
	}
	public void setOptionB(String optionB){
		this.optionB = optionB;
	}
	public String getOptionC(){
		return optionC;
	}
	public void setOptionC(String optionC){
		this.optionC = optionC;
	}
	public String getOptionD(){
		return optionD;
	}
	public void setOptionD(String optionD){
		this.optionD = optionD;
	}
	public String getAnswer(){
		return answer;
	}
	public void setAnswer(String answer){
		this.answer = answer;
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
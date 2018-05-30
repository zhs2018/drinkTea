package com.wxsoft.drinkTea.platform.system.sysconfig.model;

import java.util.Date;
import java.util.List;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;
import com.wxsoft.drinkTea.platform.weixin.model.SubOption;


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
	private Date createTime;
	//
	private Integer visable;
	// 日赛的题目，是一张图片
    private String img;

    //茶叶的介绍
    private String intro;

	// 普通分页
	private CommonPage page;

	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	//选项
	private List<SubOption> options;
	private SubOption userAnswer;
	private Integer type;//0 对 1：错

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public SubOption getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(SubOption userAnswer) {
		this.userAnswer = userAnswer;
	}
	public List<SubOption> getOptions() {
		return options;
	}
	public void setOptions(List<SubOption> options) {
		this.options = options;
	}
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
	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
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

	private List<SubOption> opList1;
	private List<SubOption> opList2;
	private List<SubOption> opList3;
	public List<SubOption> getOpList1() {
		return opList1;
	}
	public void setOpList1(List<SubOption> opList1) {
		this.opList1 = opList1;
	}
	public List<SubOption> getOpList2() {
		return opList2;
	}
	public void setOpList2(List<SubOption> opList2) {
		this.opList2 = opList2;
	}
	public List<SubOption> getOpList3() {
		return opList3;
	}
	public void setOpList3(List<SubOption> opList3) {
		this.opList3 = opList3;
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
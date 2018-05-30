package com.wxsoft.drinkTea.platform.system.sysconfig.model;

import java.util.List;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;
import com.wxsoft.drinkTea.platform.weixin.model.SubOption;


/**
 * @文件名称: SubjectKing.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class SubjectKing extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 题库
	private Integer id;
	//
	private Integer subNumber;
	// 日赛的题目，是一张图片
	private String img;
	// 存的题目选项表的id
	private Integer answer;
	// 添加题目的时间  存年月日 只存数字yyyyMMdd
	private String createTime;
	//添加的题目的介绍
	private String intro;

	// 普通分页
	private CommonPage page;

	private Integer cur;

	public Integer getCur() {
		return cur;
	}
	public void setCur(Integer cur) {
		this.cur = cur;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}

	private List<SubOption> options;

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
	public String getImg(){
		return img;
	}
	public void setImg(String img){
		this.img = img;
	}
	public Integer getAnswer(){
		return answer;
	}
	public void setAnswer(Integer answer){
		this.answer = answer;
	}
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
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
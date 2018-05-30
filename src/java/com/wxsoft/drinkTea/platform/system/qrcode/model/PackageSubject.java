package com.wxsoft.drinkTea.platform.system.qrcode.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;
import com.wxsoft.drinkTea.platform.system.sysconfig.model.SubjectKu;


/**
 * 小包和题库关联表
 * @author 王桐睿
 * com.wxsoft.drinkTea.platform.system.qrcode.model
 * 2017年3月29日下午7:16:32
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

	private Integer optionId;
	private Integer visable;

	private String optionName;

	//题目包含选项
	private SubjectKu subjectKu;

	//哪一题的第几问（1,2,3）问
	private Integer signId;

	public Integer getSignId() {
		return signId;
	}
	public void setSignId(Integer signId) {
		this.signId = signId;
	}
	public SubjectKu getSubjectKu() {
		return subjectKu;
	}
	public void setSubjectKu(SubjectKu subjectKu) {
		this.subjectKu = subjectKu;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public Integer getOptionId() {
		return optionId;
	}
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	public Integer getVisable() {
		return visable;
	}
	public void setVisable(Integer visable) {
		this.visable = visable;
	}
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
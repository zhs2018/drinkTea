package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: MessageCenter.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class MessageCenter extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 
	private Integer id;
	// 标题
	private String headTitle;
	// 
	private String textContent;
	// 内容
	private String centerContent;
	// 
	private String footContent;
	// 发布时间
	private Date releaseTime;
	// 是否撤销
	private String revokeSign;
	// 撤销时间
	private Date revokeTime;
	// 是否删除
	private Integer delSign;
	// 删除时间
	private Date delTime;
	// 优先级
	private String priority;
	// 用户编号
	private Integer userNum;
	// 用户类型
	private String userType;
	// 是否阅读
	private String readSign;
	// 阅读时间
	private Date readTime;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getHeadTitle(){
		return headTitle;
	}
	public void setHeadTitle(String headTitle){
		this.headTitle = headTitle;
	}
	public String getTextContent(){
		return textContent;
	}
	public void setTextContent(String textContent){
		this.textContent = textContent;
	}
	public String getCenterContent(){
		return centerContent;
	}
	public void setCenterContent(String centerContent){
		this.centerContent = centerContent;
	}
	public String getFootContent(){
		return footContent;
	}
	public void setFootContent(String footContent){
		this.footContent = footContent;
	}
	public Date getReleaseTime(){
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime){
		this.releaseTime = releaseTime;
	}
	public String getRevokeSign(){
		return revokeSign;
	}
	public void setRevokeSign(String revokeSign){
		this.revokeSign = revokeSign;
	}
	public Date getRevokeTime(){
		return revokeTime;
	}
	public void setRevokeTime(Date revokeTime){
		this.revokeTime = revokeTime;
	}
	public Integer getDelSign(){
		return delSign;
	}
	public void setDelSign(Integer delSign){
		this.delSign = delSign;
	}
	public Date getDelTime(){
		return delTime;
	}
	public void setDelTime(Date delTime){
		this.delTime = delTime;
	}
	public String getPriority(){
		return priority;
	}
	public void setPriority(String priority){
		this.priority = priority;
	}
	public Integer getUserNum(){
		return userNum;
	}
	public void setUserNum(Integer userNum){
		this.userNum = userNum;
	}
	public String getUserType(){
		return userType;
	}
	public void setUserType(String userType){
		this.userType = userType;
	}
	public String getReadSign(){
		return readSign;
	}
	public void setReadSign(String readSign){
		this.readSign = readSign;
	}
	public Date getReadTime(){
		return readTime;
	}
	public void setReadTime(Date readTime){
		this.readTime = readTime;
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
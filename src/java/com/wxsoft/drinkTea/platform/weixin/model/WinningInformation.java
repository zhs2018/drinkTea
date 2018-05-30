package com.wxsoft.drinkTea.platform.weixin.model;

import java.util.Date;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;
import com.wxsoft.drinkTea.platform.system.luckdraw.model.PrizePro;

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

	private WebUser webUser;
	private PrizePro prizePro;
	private UserAddress userAddress;

	private Integer sendSign;

	public Integer getSendSign() {
		return sendSign;
	}
	public void setSendSign(Integer sendSign) {
		this.sendSign = sendSign;
	}
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	public WebUser getWebUser() {
		return webUser;
	}
	public void setWebUser(WebUser webUser) {
		this.webUser = webUser;
	}
	public PrizePro getPrizePro() {
		return prizePro;
	}
	public void setPrizePro(PrizePro prizePro) {
		this.prizePro = prizePro;
	}
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
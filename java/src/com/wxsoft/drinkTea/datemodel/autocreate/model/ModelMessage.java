package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: ModelMessage.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class ModelMessage extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 系統設置中的模型消息設置
	private Integer id;
	// 
	private String submitKey;
	// 
	private String submitHead;
	// 
	private String submitFoot;
	// 
	private String sendRemind;
	// 
	private String sendHead;
	// 
	private String sendFoot;
	// 
	private String applySucc;
	// 
	private String applyHead;
	// 
	private String resultInfo;
	// 
	private String resultHead;
	// 
	private String resultFoot;
	// 
	private String refundInfo;
	// 
	private String refundHead;
	// 
	private String refundFoot;
	// 
	private String newUserInfo;
	// 
	private String newUserHead;
	// 
	private String accountInfo;
	// 
	private String qrCode;
	// 
	private String drawInfo;
	// 
	private String drawHead;
	// 
	private String drawFoot;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getSubmitKey(){
		return submitKey;
	}
	public void setSubmitKey(String submitKey){
		this.submitKey = submitKey;
	}
	public String getSubmitHead(){
		return submitHead;
	}
	public void setSubmitHead(String submitHead){
		this.submitHead = submitHead;
	}
	public String getSubmitFoot(){
		return submitFoot;
	}
	public void setSubmitFoot(String submitFoot){
		this.submitFoot = submitFoot;
	}
	public String getSendRemind(){
		return sendRemind;
	}
	public void setSendRemind(String sendRemind){
		this.sendRemind = sendRemind;
	}
	public String getSendHead(){
		return sendHead;
	}
	public void setSendHead(String sendHead){
		this.sendHead = sendHead;
	}
	public String getSendFoot(){
		return sendFoot;
	}
	public void setSendFoot(String sendFoot){
		this.sendFoot = sendFoot;
	}
	public String getApplySucc(){
		return applySucc;
	}
	public void setApplySucc(String applySucc){
		this.applySucc = applySucc;
	}
	public String getApplyHead(){
		return applyHead;
	}
	public void setApplyHead(String applyHead){
		this.applyHead = applyHead;
	}
	public String getResultInfo(){
		return resultInfo;
	}
	public void setResultInfo(String resultInfo){
		this.resultInfo = resultInfo;
	}
	public String getResultHead(){
		return resultHead;
	}
	public void setResultHead(String resultHead){
		this.resultHead = resultHead;
	}
	public String getResultFoot(){
		return resultFoot;
	}
	public void setResultFoot(String resultFoot){
		this.resultFoot = resultFoot;
	}
	public String getRefundInfo(){
		return refundInfo;
	}
	public void setRefundInfo(String refundInfo){
		this.refundInfo = refundInfo;
	}
	public String getRefundHead(){
		return refundHead;
	}
	public void setRefundHead(String refundHead){
		this.refundHead = refundHead;
	}
	public String getRefundFoot(){
		return refundFoot;
	}
	public void setRefundFoot(String refundFoot){
		this.refundFoot = refundFoot;
	}
	public String getNewUserInfo(){
		return newUserInfo;
	}
	public void setNewUserInfo(String newUserInfo){
		this.newUserInfo = newUserInfo;
	}
	public String getNewUserHead(){
		return newUserHead;
	}
	public void setNewUserHead(String newUserHead){
		this.newUserHead = newUserHead;
	}
	public String getAccountInfo(){
		return accountInfo;
	}
	public void setAccountInfo(String accountInfo){
		this.accountInfo = accountInfo;
	}
	public String getQrCode(){
		return qrCode;
	}
	public void setQrCode(String qrCode){
		this.qrCode = qrCode;
	}
	public String getDrawInfo(){
		return drawInfo;
	}
	public void setDrawInfo(String drawInfo){
		this.drawInfo = drawInfo;
	}
	public String getDrawHead(){
		return drawHead;
	}
	public void setDrawHead(String drawHead){
		this.drawHead = drawHead;
	}
	public String getDrawFoot(){
		return drawFoot;
	}
	public void setDrawFoot(String drawFoot){
		this.drawFoot = drawFoot;
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
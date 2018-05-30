package com.wxsoft.drinkTea.platform.system.evaluate.model;

import java.util.Date;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;


/**
 * 评论表的实体类
 * @author 王桐睿
 * com.wxsoft.drinkTea.platform.system.evaluate.model
 * 2017年3月28日上午9:32:32
 */
public class EvaluatePro extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 评价表
	private Integer id;
	// 商品id
	private Integer goodsId;
	// 用户id
	private Integer userId;
	// 等级
	private Integer rank;
	// 评论内容
	private String content;
	// 评价时间
	private Date evaluateTime;
	// 审核时间
	private Date checkoutTime;
	// 审核状态  0未审核  1审核通过  2审核拒绝
	private Integer status;
	// 审核状态  0未审核  1审核通过  2审核拒绝（用于in条件）
	private String statusForIn;
	// 普通分页
	private CommonPage page;

	private WebUser webUser;
	//评论人姓名
	private String wuName;

	//评论的商品名称
	private String mpName;

	private String image;

	public String getWuName() {
		return wuName;
	}
	public void setWuName(String wuName) {
		this.wuName = wuName;
	}
	public String getMpName() {
		return mpName;
	}
	public void setMpName(String mpName) {
		this.mpName = mpName;
	}
	public WebUser getWebUser() {
		return webUser;
	}
	public void setWebUser(WebUser webUser) {
		this.webUser = webUser;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getGoodsId(){
		return goodsId;
	}
	public void setGoodsId(Integer goodsId){
		this.goodsId = goodsId;
	}
	public Integer getUserId(){
		return userId;
	}
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	public Integer getRank(){
		return rank;
	}
	public void setRank(Integer rank){
		this.rank = rank;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	public Date getEvaluateTime(){
		return evaluateTime;
	}
	public void setEvaluateTime(Date evaluateTime){
		this.evaluateTime = evaluateTime;
	}
	public Date getCheckoutTime(){
		return checkoutTime;
	}
	public void setCheckoutTime(Date checkoutTime){
		this.checkoutTime = checkoutTime;
	}
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public String getStatusForIn(){
		return statusForIn;
	}
	public void setStatusForIn(String statusForIn){
		this.statusForIn = statusForIn;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
package com.wxsoft.drinkTea.platform.weixin.model;


import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;



/**
 * @文件名称: UserFriend.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class UserFriend extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 用户好友表
	private Integer id;
	//
	private Integer userId;
	//
	private Integer friendId;
	// 创建时间：yyyyMMddHHmmss
	private String createTime;
	// 是否删除
	private Integer visiable;
	// 删除时间
	private String delTime;
	//好友类型
	private Integer type;
	// 普通分页
	private CommonPage page;



	private WebUser user;

	public WebUser getUser() {
		return user;
	}
	public void setUser(WebUser user) {
		this.user = user;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getUserId(){
		return userId;
	}
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	public Integer getFriendId(){
		return friendId;
	}
	public void setFriendId(Integer friendId){
		this.friendId = friendId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}
	public Integer getVisiable(){
		return visiable;
	}
	public void setVisiable(Integer visiable){
		this.visiable = visiable;
	}
	public String getDelTime(){
		return delTime;
	}
	public void setDelTime(String delTime){
		this.delTime = delTime;
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
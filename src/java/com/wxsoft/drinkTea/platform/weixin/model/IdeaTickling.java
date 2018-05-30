package com.wxsoft.drinkTea.platform.weixin.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;

/**
 * @文件名称: IdeaTickling.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/ @作者： @公司：
 */
public class IdeaTickling extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String contents;
	//
	private String phone;
	//
	private Integer commodityId;
	// 普通分页
	private CommonPage page;
    //用户名
	private String userName;
	//意见反馈的状态1：未读 2：已读
	private Integer state;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
package com.wxsoft.drinkTea.platform.system.userRole.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;


/**
 * 角色和菜单关联表
 * @author 王桐睿
 * com.wxsoft.drinkTea.platform.system.userRole.model
 * 2017年3月23日下午4:29:06
 */
public class SysRoleMenu extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 角色id
	private Integer roleId;
	// 菜单id
	private Integer menuId;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getRoleId(){
		return roleId;
	}
	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
	public Integer getMenuId(){
		return menuId;
	}
	public void setMenuId(Integer menuId){
		this.menuId = menuId;
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
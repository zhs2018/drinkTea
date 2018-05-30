package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: SysMenu.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class SysMenu extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 
	private Integer id;
	// 菜单名称
	private String name;
	// 父级id
	private Integer parentId;
	// 排序字段
	private Integer sort;
	// 图标标识
	private String icon;
	// 连接路径
	private String linkUrl;
	// 权限id
	private Integer roleId;
	// 删除标志
	private Integer visable;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public Integer getParentId(){
		return parentId;
	}
	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}
	public Integer getSort(){
		return sort;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}
	public String getIcon(){
		return icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
	public String getLinkUrl(){
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl){
		this.linkUrl = linkUrl;
	}
	public Integer getRoleId(){
		return roleId;
	}
	public void setRoleId(Integer roleId){
		this.roleId = roleId;
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

	// 排序规则
	private String orderBy;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
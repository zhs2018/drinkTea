package com.wxsoft.drinkTea.platform.system.login.model;

import java.util.List;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;


/**
 * 系统菜单
 * @author 王桐睿
 * com.wxsoft.drinkTea.platform.system.login.model
 * 2017年3月21日下午4:10:06
 */
public class SysMenu extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 菜单名称
	private String name;
	// 父级id
	private Integer parentId;
	// 图标标识
	private String icon;
	// 连接路径
	private String linkUrl;
	//排序字段
	private Integer sort;
	// 删除标志
	private Integer visable;
	// 普通分页
	private CommonPage page;
	//菜单
	private List<SysMenu> sysMenuList;



	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public List<SysMenu> getSysMenuList() {
		return sysMenuList;
	}
	public void setSysMenuList(List<SysMenu> sysMenuList) {
		this.sysMenuList = sysMenuList;
	}
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
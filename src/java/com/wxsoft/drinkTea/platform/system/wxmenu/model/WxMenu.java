package com.wxsoft.drinkTea.platform.system.wxmenu.model;

import java.util.List;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;



/**
 * @文件名称: WxMenu.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class WxMenu extends BaseBean {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String name;
	//
	private String type;
	//
	private String key;
	//
	private Integer parentId;
	//
	private Integer sort;
	//
	private String content;
	//
	private String visible;
	// 普通分页
	private CommonPage page;

	private List<WxMenu> childMenu;

	public List<WxMenu> getChildMenu() {
		return childMenu;
	}
	public void setChildMenu(List<WxMenu> childMenu) {
		this.childMenu = childMenu;
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
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getKey(){
		return key;
	}
	public void setKey(String key){
		this.key = key;
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
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getVisible(){
		return visible;
	}
	public void setVisible(String visible){
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
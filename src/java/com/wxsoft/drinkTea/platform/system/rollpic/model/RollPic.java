package com.wxsoft.drinkTea.platform.system.rollpic.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;


/**
 * @文件名称: RollPic.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class RollPic extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 主键
	private Integer id;
	// 标题
	private String title;
	// 图片地址
	private String picUrl;
	// 链接地址
	private String linkUrl;
	// 排序从小到大
	private Integer sorts;
	// 是否可用1可用 0不可用
	private Integer isVisable;
	// 录入时间
	private String addtime;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getPicUrl(){
		return picUrl;
	}
	public void setPicUrl(String picUrl){
		this.picUrl = picUrl;
	}
	public String getLinkUrl(){
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl){
		this.linkUrl = linkUrl;
	}
	public Integer getSorts(){
		return sorts;
	}
	public void setSorts(Integer sorts){
		this.sorts = sorts;
	}
	public Integer getIsVisable(){
		return isVisable;
	}
	public void setIsVisable(Integer isVisable){
		this.isVisable = isVisable;
	}
	public String getAddtime(){
		return addtime;
	}
	public void setAddtime(String addtime){
		this.addtime = addtime;
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
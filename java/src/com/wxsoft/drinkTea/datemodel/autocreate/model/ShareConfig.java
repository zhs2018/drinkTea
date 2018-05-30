package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: ShareConfig.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class ShareConfig extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 系统配置中的分享配置
	private Integer id;
	// 
	private String firstTitle;
	// 
	private String shareTitle;
	// 
	private String shareDesc;
	// 
	private String sharePic;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getFirstTitle(){
		return firstTitle;
	}
	public void setFirstTitle(String firstTitle){
		this.firstTitle = firstTitle;
	}
	public String getShareTitle(){
		return shareTitle;
	}
	public void setShareTitle(String shareTitle){
		this.shareTitle = shareTitle;
	}
	public String getShareDesc(){
		return shareDesc;
	}
	public void setShareDesc(String shareDesc){
		this.shareDesc = shareDesc;
	}
	public String getSharePic(){
		return sharePic;
	}
	public void setSharePic(String sharePic){
		this.sharePic = sharePic;
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
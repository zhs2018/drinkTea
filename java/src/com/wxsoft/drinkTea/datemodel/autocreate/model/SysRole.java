package com.wxsoft.drinkTea.datemodel.autocreate.model;

import java.util.Date;
import com.wxsoft.framework.base.BaseBean;
import com.wxsoft.framework.mybatis.plugin.CommonPage;
import java.math.BigDecimal;


/**
 * @文件名称: SysRole.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class SysRole extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 权限id
	private Integer id;
	// 权限名称
	private String roleName;
	// 创建时间
	private Date createtime;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getRoleName(){
		return roleName;
	}
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	public Date getCreatetime(){
		return createtime;
	}
	public void setCreatetime(Date createtime){
		this.createtime = createtime;
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
package com.wxsoft.drinkTea.platform.system.qrcode.model;

import java.util.Date;
import java.util.List;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;

/**
 * 小包实体类
 *
 * @author 王桐睿 com.wxsoft.drinkTea.platform.system.qrcode.model
 *         2017年3月29日下午7:17:31
 */
public class SmallPackage extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 小包
	private Integer id;
	private String falseId;
	//
	private String name;
	//
	private Date createTime;
	//
	private Date updateTime;
	// 普通分页
	private CommonPage page;
	// 创建小包的用户id
	private Integer createBy;
	// 通过小包创建id查出来的用户名
	private String createName;
	// 删除标志 1可用 2已删除
	private Integer visable;
	// 每个小包关联的题号
	private List<Integer> codes;

	private Integer userId;

	private Integer QuestionNum;

	//二维码图片的地址路径
	private String url;

	//需要和package_subject表关联题目id，答案id

	//
	private Date startTime;
	//
	private Date endTime;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getQuestionNum() {
		return QuestionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		QuestionNum = questionNum;
	}

	private String subOneId;
	private String subTwoId;
	private String subThreeId;
	private String subFourId;
	private String opOneId;
	private String opTwoId;
	private String opThreeId;
	private String opFourId;



	public String getFalseId() {
		return falseId;
	}

	public void setFalseId(String falseId) {
		this.falseId = falseId;
	}

	public String getSubOneId() {
		return subOneId;
	}

	public void setSubOneId(String subOneId) {
		this.subOneId = subOneId;
	}

	public String getSubTwoId() {
		return subTwoId;
	}

	public void setSubTwoId(String subTwoId) {
		this.subTwoId = subTwoId;
	}

	public String getSubThreeId() {
		return subThreeId;
	}

	public void setSubThreeId(String subThreeId) {
		this.subThreeId = subThreeId;
	}

	public String getSubFourId() {
		return subFourId;
	}

	public void setSubFourId(String subFourId) {
		this.subFourId = subFourId;
	}

	public String getOpOneId() {
		return opOneId;
	}

	public void setOpOneId(String opOneId) {
		this.opOneId = opOneId;
	}

	public String getOpTwoId() {
		return opTwoId;
	}

	public void setOpTwoId(String opTwoId) {
		this.opTwoId = opTwoId;
	}

	public String getOpThreeId() {
		return opThreeId;
	}

	public void setOpThreeId(String opThreeId) {
		this.opThreeId = opThreeId;
	}

	public String getOpFourId() {
		return opFourId;
	}

	public void setOpFourId(String opFourId) {
		this.opFourId = opFourId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Integer> getCodes() {
		return codes;
	}

	public void setCodes(List<Integer> codes) {
		this.codes = codes;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getVisable() {
		return visable;
	}

	public void setVisable(Integer visable) {
		this.visable = visable;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
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
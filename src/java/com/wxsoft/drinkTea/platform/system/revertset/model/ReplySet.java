package com.wxsoft.drinkTea.platform.system.revertset.model;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;


/**
 * @文件名称: ReplySet.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/
 * @作者：
 * @公司：
 */
public class ReplySet extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 回复设置
	private Integer id;
	//
	private String keyWord;
	//
	private String replyPattern;
	//
	private String title;
	//
	private String img;
	//
	private String describe;
	//
	private String replyMessage;
	//
	private String musicUrl;
	//
	private String superMusicUrl;
	//
	private String replyType;
	//
	private String likess;
	// 普通分页
	private CommonPage page;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getKeyWord(){
		return keyWord;
	}
	public void setKeyWord(String keyWord){
		this.keyWord = keyWord;
	}
	public String getReplyPattern(){
		return replyPattern;
	}
	public void setReplyPattern(String replyPattern){
		this.replyPattern = replyPattern;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getImg(){
		return img;
	}
	public void setImg(String img){
		this.img = img;
	}
	public String getDescribe(){
		return describe;
	}
	public void setDescribe(String describe){
		this.describe = describe;
	}
	public String getReplyMessage(){
		return replyMessage;
	}
	public void setReplyMessage(String replyMessage){
		this.replyMessage = replyMessage;
	}
	public String getMusicUrl(){
		return musicUrl;
	}
	public void setMusicUrl(String musicUrl){
		this.musicUrl = musicUrl;
	}
	public String getSuperMusicUrl(){
		return superMusicUrl;
	}
	public void setSuperMusicUrl(String superMusicUrl){
		this.superMusicUrl = superMusicUrl;
	}
	public String getReplyType(){
		return replyType;
	}
	public void setReplyType(String replyType){
		this.replyType = replyType;
	}
	public String getLikess(){
		return likess;
	}
	public void setLikess(String likess){
		this.likess = likess;
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
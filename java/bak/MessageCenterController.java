package com.wxsoft.drinkTea.platform.weixin.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.platform.system.messageCenter.mapper.MessageCenterMapper;
import com.wxsoft.drinkTea.platform.system.messageCenter.model.MessageCenter;

/**
 *
 * @author lzj
 * @描述：消息中心
 *
 */
@Controller
@RequestMapping("/shop")
public class MessageCenterController extends BaseAction {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MessageCenterMapper messageCenterMapper;

	@RequestMapping("/perCenter")
	public ModelAndView messageCenter() {
		ModelAndView mv = new ModelAndView();
		MessageCenter mCenter = new MessageCenter();
		List<MessageCenter> mList = messageCenterMapper.getListBy(mCenter);
		for (MessageCenter messageCenter : mList) {
			messageCenter.setReleaseTime(messageCenter.getReleaseTime().substring(0, 16));
		}
		mv.addObject("ObjList", mList);
		mv.setViewName("weixin/messageCenter");
		return mv;

	}

	@RequestMapping("/content")
	public ModelAndView messageContent(Integer id) {
		ModelAndView mv = new ModelAndView();
		MessageCenter mCenter = new MessageCenter();
		mCenter.setReadSign("2");
		mCenter.setReadTime(new Date());
		mCenter.setId(id);
		messageCenterMapper.updateByPrimaryKey(mCenter);
		MessageCenter messageCenter = messageCenterMapper.selectByPrimaryKey(id);
		messageCenter.setReleaseTime(messageCenter.getReleaseTime().substring(0, 16));
		mv.addObject("ob", messageCenter);
		mv.setViewName("weixin/messageContent");
		return mv;

	}
}

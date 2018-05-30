package com.wxsoft.drinkTea.platform.system.messageCenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.platform.system.messageCenter.mapper.MessageCenterMapper;
import com.wxsoft.drinkTea.platform.system.messageCenter.model.MessageCenter;

/**
 *
 * @author lzj @创建时间：2017-04-11 上午10:03:52
 *
 */

@RequestMapping(SCHOOLMGR.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/messageCenter")
@Controller
public class MessageCenterContoller extends BaseAction {
	/**
	 * @author lzj
	 * @序列号
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private MessageCenterMapper messageCenterMapper;

	@RequestMapping("/info")
	public ModelAndView getAdd() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/messageCenter/info");
		return mv;
	}

	@RequestMapping("/save")
	public ModelAndView save(MessageCenter messageCenter) {
		ModelAndView mv = new ModelAndView();
		int flag = 0;
		if (null != messageCenter.getId()) {
			flag = messageCenterMapper.updateByPrimaryKey(messageCenter);
		} else {

			flag = messageCenterMapper.insert(messageCenter);
		}
		MessageCenter mc = new MessageCenter();
		List<MessageCenter> list = messageCenterMapper.getListBy(mc);
		if (flag == 1) {
			mv.addObject("ObjList", list);
			mv.setViewName("redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/messageCenter/list");
		} else {
			mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/messageCenter/info");
		}
		return mv;
	}

	@RequestMapping("/list")
	public ModelAndView save() {
		ModelAndView mv = new ModelAndView();
		MessageCenter mc = new MessageCenter();
		List<MessageCenter> list = messageCenterMapper.getListBy(mc);
		for (MessageCenter messageCenter : list) {
			if (null != messageCenter) {
				messageCenter.setReleaseTime(messageCenter.getReleaseTime().substring(0, 16));
			}
		}
		mv.addObject("ObjList", list);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/messageCenter/list");
		return mv;
	}

	@RequestMapping("/getone")
	public ModelAndView editor(Integer id) {
		ModelAndView mv = new ModelAndView();
		MessageCenter mCenter = messageCenterMapper.selectByPrimaryKey(id);
		mCenter.setReleaseTime(mCenter.getReleaseTime().substring(0, 16));
		mv.addObject("Obj", mCenter);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/messageCenter/info");
		return mv;
	}

	@RequestMapping("/delones/{id}")
	public ModelAndView delMessage(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		int flag = messageCenterMapper.deleteByPrimaryKey(id);
		if (flag == 1) {
			mv.setViewName("redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/messageCenter/list");
		}
		return mv;
	}

}

package com.wxsoft.drinkTea.platform.weixin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.platform.weixin.mapper.RedEnvelopeMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.RedEnvelope;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 * @author lzj
 * @描述：红包
 * @时间：2017-4-10
 */
@RequestMapping("shop")
@Controller
public class RedEnvelopeController extends BaseAction {

	@Autowired
	private RedEnvelopeMapper redEnvelopeMapper;

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	 /**
	  * @author lzj
	  * @描述：我的红包
	  * @时间：2017-4-10
	  * @param session
	  * @return
	  */

	@RequestMapping("/myRedEnvelope")
	public ModelAndView RedEnvelope(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		WebUser us = (WebUser) session.getAttribute("user");
		if (us == null) {
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/index");
			return mv;
		}
		RedEnvelope re = new RedEnvelope();
		re.setUserId(us.getId());
		Double money = 0.0;
		List<RedEnvelope> list = redEnvelopeMapper.getListBy(re);
		for (RedEnvelope redEnvelope : list) {
			money += redEnvelope.getMoney();
		}
		mv.addObject("money", money);
		mv.addObject("objList", list);
		mv.addObject("user", us);
		mv.setViewName("weixin/myRedEnvelope");
		return mv;
	}
}

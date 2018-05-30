package com.wxsoft.drinkTea.platform.weixin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.platform.system.evaluate.mapper.EvaluateProMapper;
import com.wxsoft.drinkTea.platform.system.evaluate.model.EvaluatePro;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;
/*@RequestMapping("shop")
@Controller
public class EvaluationController extends BaseAction{

	*//**
	 *
	 *//*
	private static final long serialVersionUID = 1L;
	@Autowired
	private EvaluateProMapper evaluateProMapper;
	@Autowired
	private WebUserMapper webUserMapper;
	@RequestMapping("/evaluate")
	public ModelAndView jumpToCommodityEvaluation(HttpSession session,EvaluatePro evaluatePro){
		ModelAndView mv = new ModelAndView();
	WebUser webUser = (WebUser) session.getAttribute("user");
	if(null!=webUser){
		List<EvaluatePro> list = evaluateProMapper.getListBy(evaluatePro);
		List<EvaluatePro> list1 = new ArrayList<>();
		for(EvaluatePro eva : list){
			WebUser user = webUserMapper.selectByPrimaryKey(eva.getUserId());
			if(null!=user){
				eva.setContent(eva.getContent());
				eva.setEvaluateTime(eva.getEvaluateTime());
				eva.setWuName(user.getUserName());
				eva.setImage(user.getImage());
				list1.add(eva);
			}
		}
		mv.addObject("resultList",list1);
		mv.setViewName("weixin/commodityEvaluation");
	}

		return mv;
	}
}*/

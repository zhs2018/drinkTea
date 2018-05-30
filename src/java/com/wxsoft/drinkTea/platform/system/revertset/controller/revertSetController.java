package com.wxsoft.drinkTea.platform.system.revertset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;

@Controller
@RequestMapping("system/revert")
public class revertSetController extends BaseAction{

	/**
	 *回复设置
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping("/list")
	public ModelAndView mod(){
		ModelAndView modelAndView=new ModelAndView("system/restoreSet/list");
		return modelAndView;
	}

	@RequestMapping("/focuson")
	public ModelAndView modl(){
		ModelAndView modelAnd=new ModelAndView("system/restoreSet/focuson");
		return modelAnd;
	}

	@RequestMapping("/addresponse")
	public ModelAndView modlo(){
		ModelAndView modelAndV=new ModelAndView("system/restoreSet/addresponse");
		return modelAndV;
	}
}

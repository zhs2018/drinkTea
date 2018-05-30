package com.wxsoft.drinkTea.platform.system.basicconfiguration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;

/**
 * @基本配置
 *@xyc
 * */
@Controller
@RequestMapping("/system/baseconfig")
public class basicconFigController extends BaseAction{

	private static final long serialVersionUID = 1L;
	/**
	 * @跳转
	 * */
	@RequestMapping("/baseSet")
	public ModelAndView modelAndView(){
		ModelAndView modelAnd=new ModelAndView("/system/configmessage/baseSet");
		return modelAnd;
	}
}

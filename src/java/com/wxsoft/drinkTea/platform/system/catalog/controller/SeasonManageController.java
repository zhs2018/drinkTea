package com.wxsoft.drinkTea.platform.system.catalog.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.platform.system.catalog.mapper.SeasonManageMapper;
import com.wxsoft.drinkTea.platform.system.catalog.model.SeasonManage;

@Controller
@RequestMapping(SCHOOLMGR.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/season")
public class SeasonManageController  extends BaseAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SeasonManageMapper seasonManageMapper;

	@RequestMapping("/list")
	public ModelAndView seasonList(SeasonManage sm){
		ModelAndView mv=  new ModelAndView();
		sm.setIsVisable(1);
		List<SeasonManage> smList = seasonManageMapper.getPageListBy(sm);
         mv.addObject("smList",smList);
         mv.addObject("page",sm);
         mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/catalog/seasonlist");
		return mv;
	}

	@RequestMapping("/seasoninfo")
	public ModelAndView seasoninfo(){
		ModelAndView mv=  new ModelAndView();
         mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/catalog/seasoninfo");
		return mv;
	}

	@RequestMapping("/save")
	public ModelAndView save(SeasonManage sm ,Integer cur){
		ModelAndView mv=  new ModelAndView();
		int flag =0;
		if(sm.getId()!=null){

		 flag = seasonManageMapper.updateByPrimaryKey(sm);

		}else{
			sm.setAddTime(new Date());
			sm.setIsVisable(1);
			flag = seasonManageMapper.insert(sm);
		}

		if(flag==1){
			if(cur!=null){
				mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/season/list?page.currentPage="+cur);
			}else{
				mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/season/list");
			}

		}else{
			mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/catalog/seasoninfo");
		}
		return mv;
	}

	@RequestMapping("/editorSeason")
	public ModelAndView save(Integer id ,Integer cur){
		ModelAndView mv=  new ModelAndView();
		SeasonManage sm = 	seasonManageMapper.selectByPrimaryKey(id);
		if(sm!=null){
			mv.addObject("sm",sm);
			mv.addObject("cur",cur);
			mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/catalog/seasoninfo");
		}else{
			mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/season/list");

		}
		return mv;
	}

	@RequestMapping("/delSeason")
	public ModelAndView delSeason(Integer id){
		ModelAndView mv = new ModelAndView();
		if(id!=null){
			SeasonManage sm = new SeasonManage();
			sm.setId(id);
			sm.setIsVisable(2);
        seasonManageMapper.updateByPrimaryKey(sm);
		}
		mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/season/list");
		return mv;
	}

}

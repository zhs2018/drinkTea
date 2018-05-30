package com.wxsoft.drinkTea.platform.system.publicWelfare.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.platform.system.publicWelfare.mapper.PublicWelfareMapper;
import com.wxsoft.drinkTea.platform.system.publicWelfare.model.PublicWelfare;
@Controller
@RequestMapping("system/welfare")
public class WelfareController extends BaseAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
    @Autowired
    private PublicWelfareMapper publicWelfareMapper;

	@RequestMapping("/list")
	public ModelAndView welfareList(){
	 ModelAndView mv = new ModelAndView();
	 PublicWelfare pw = new PublicWelfare();
	 PublicWelfare pWelfare =  publicWelfareMapper.selectBy(pw);
	 mv.addObject("ObjList",pWelfare);
	 mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/publicWelfare/welfarelist");
	    return mv;
	}


    @RequestMapping("/change/{id}")
    public ModelAndView change(@PathVariable Integer id){
    	ModelAndView mv = new ModelAndView();
    	PublicWelfare pWelfare =  publicWelfareMapper.selectByPrimaryKey(id);
    	pWelfare.setSign(2);
    	pWelfare.setTime(new Date());
        publicWelfareMapper.updateByPrimaryKey(pWelfare);
    mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/welfare/list");
	return mv;
     }

    @RequestMapping("/state")
    public ModelAndView state(Integer id){
    	ModelAndView mv = new ModelAndView();
    	PublicWelfare pWelfare =  publicWelfareMapper.selectByPrimaryKey(id);
    	pWelfare.setSign(1);
        publicWelfareMapper.updateByPrimaryKey(pWelfare);
    mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/welfare/list");
	return mv;
     }

    @RequestMapping("/changeState")
    public ModelAndView changeState(Integer id){
    	ModelAndView mv = new ModelAndView();
    	PublicWelfare pWelfare =  publicWelfareMapper.selectByPrimaryKey(id);
        mv.addObject("welfare",pWelfare);
    mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/publicWelfare/info");
	return mv;
     }


    @RequestMapping("/update")
    public ModelAndView changeState(PublicWelfare publicWelfare){
    	ModelAndView mv = new ModelAndView();
    	publicWelfare.setSign(1);
    	publicWelfareMapper.updateByPrimaryKey(publicWelfare);
      mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/welfare/list");
	return mv;
     }
}

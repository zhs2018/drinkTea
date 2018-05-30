package com.wxsoft.drinkTea.platform.system.luckdraw.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.platform.system.luckdraw.mapper.LuckDrawMapper;
import com.wxsoft.drinkTea.platform.system.luckdraw.model.LuckDraw;
@Controller
@RequestMapping("system/luckdraw")
public class LuckDrawController extends BaseAction{


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	    @Autowired
	    private LuckDrawMapper luckDrawMapper;

		@RequestMapping("/list")
		public ModelAndView welfareList(){
		 ModelAndView mv = new ModelAndView();
		 LuckDraw ld = new LuckDraw();
		 LuckDraw lDraw =  luckDrawMapper.selectBy(ld);
		 mv.addObject("ObjList",lDraw);
		 mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/prizes/drawlist");
		    return mv;
		}


	    @RequestMapping("/change/{id}")
	    public ModelAndView change(@PathVariable Integer id){
	    	ModelAndView mv = new ModelAndView();
	    	LuckDraw lDraw =  luckDrawMapper.selectByPrimaryKey(id);
	    	lDraw.setSign(2);
	    	lDraw.setTime(new Date());
	    	luckDrawMapper.updateByPrimaryKey(lDraw);
	    mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/luckdraw/list");
		return mv;
	     }

	    @RequestMapping("/state")
	    public ModelAndView state(Integer id){
	    	ModelAndView mv = new ModelAndView();
	    	LuckDraw lDraw =  luckDrawMapper.selectByPrimaryKey(id);
	    	lDraw.setSign(1);
	    	luckDrawMapper.updateByPrimaryKey(lDraw);
	    mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/luckdraw/list");
		return mv;
	     }

	    @RequestMapping("/changeState")
	    public ModelAndView changeState(Integer id){
	    	ModelAndView mv = new ModelAndView();
	    	LuckDraw lDraw =  luckDrawMapper.selectByPrimaryKey(id);
	        mv.addObject("draw",lDraw);
	    mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/prizes/drawinfo");
		return mv;
	     }


	    @RequestMapping("/update")
	    public ModelAndView changeState(LuckDraw luckDraw){
	    	ModelAndView mv = new ModelAndView();
	    	luckDraw.setSign(1);
	    	luckDrawMapper.updateByPrimaryKey(luckDraw);
	      mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/luckdraw/list");
		return mv;
	     }
}

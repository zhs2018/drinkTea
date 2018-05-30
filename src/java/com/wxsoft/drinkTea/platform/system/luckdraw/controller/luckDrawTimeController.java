package com.wxsoft.drinkTea.platform.system.luckdraw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.platform.system.luckdraw.mapper.DrawTimeMapper;
import com.wxsoft.drinkTea.platform.system.luckdraw.model.DrawTime;
@Controller
@RequestMapping("system/luckdraw")
public class luckDrawTimeController extends BaseAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private DrawTimeMapper drawTimeMapper;

    @RequestMapping("/time/{id}")
	public ModelAndView time(@PathVariable Integer id){
		ModelAndView mv = new ModelAndView();
		if(id!=null){
     		DrawTime drawTime=drawTimeMapper.selectByPrimaryKey(id);
     		mv.addObject("drawTime",drawTime);
		}
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/prizes/time");
		return mv;
	}

    @RequestMapping("/saveTime")
    public ModelAndView saveTime(DrawTime time){
    	ModelAndView mv = new ModelAndView();
     if(time.getStartIme()!=null&&time.getEndTime()!=null){
    	 DrawTime drawTime = new DrawTime();
    	 drawTime.setStartIme(time.getStartIme());
    	 drawTime.setEndTime(time.getEndTime());
    	 drawTime.setVisible(1);
    	 drawTime.setId(1);
    	 drawTimeMapper.updateByPrimaryKey(drawTime);
     }
    	mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/luckdraw/timeList");
    	return mv;
    }
    @RequestMapping("/timeList")
    public ModelAndView timeList(){
    	ModelAndView mv = new ModelAndView();
    	DrawTime time = new  DrawTime();
    	DrawTime drawTime = drawTimeMapper.selectBy(time);
    	mv.addObject("drawTime",drawTime);
    	mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/prizes/timelist");
    	return mv;
    }
}

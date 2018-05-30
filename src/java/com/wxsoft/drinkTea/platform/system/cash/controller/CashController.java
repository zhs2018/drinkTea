package com.wxsoft.drinkTea.platform.system.cash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.framework.controller.BaseController;
import com.wxsoft.drinkTea.platform.system.cash.mapper.CashOutMapper;
import com.wxsoft.drinkTea.platform.system.cash.model.CashOut;

@Controller
@RequestMapping(SCHOOLMGR.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/cash")
public class CashController  extends BaseController{

	@Autowired
	private CashOutMapper cashOutMapper;

    @RequestMapping("/cashlist")
	public ModelAndView cashList(CashOut co ){
		ModelAndView mv = new ModelAndView();
		List<CashOut> cOut = cashOutMapper.getListBy(co);
		mv.addObject("cashOut",cOut);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/cash/cashList");
		return mv;
	}


    @RequestMapping("/editCash")
    public ModelAndView editCash(CashOut cashOut){
    	ModelAndView mv = new ModelAndView();
     cashOut = cashOutMapper.selectBy(cashOut);
      mv.addObject("cashOut",cashOut);
    	mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/cash/cashInfo");
    	return mv;
    }

    @RequestMapping("/saveCash")
    public ModelAndView saveCash(CashOut cashOut){
    	ModelAndView mv = new ModelAndView();
    	if(cashOut.getId()!=null){
    		cashOutMapper.updateByPrimaryKey(cashOut);
    	}else{
    		cashOutMapper.insert(cashOut);

    	}
    	mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/cash/cashlist");
    	return mv;
    }
}

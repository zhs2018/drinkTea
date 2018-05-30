package com.wxsoft.drinkTea.platform.system.cash.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.framework.controller.BaseController;
import com.wxsoft.drinkTea.platform.system.cash.mapper.CashRecordMapper;
import com.wxsoft.drinkTea.platform.system.cash.model.CashRecord;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

@Controller
@RequestMapping(SCHOOLMGR.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/cashmember")
public class CashMemberController extends BaseController{

	@Autowired
	private WebUserMapper webUserMapper;

	@Autowired
	private CashRecordMapper cashRecordMapper;

   //到用户提取红包列表
	@RequestMapping("/cashlist")
	public ModelAndView cashmemberList(WebUser webUser){
		ModelAndView mv = new ModelAndView();
		List<WebUser> userList = webUserMapper.getPageListLike(webUser);
		for (WebUser webUser2 : userList) {
			CashRecord cashRecord = new CashRecord();
			cashRecord.setUserId(webUser2.getId());
			double money  = 0;
			List<CashRecord> cashList =  cashRecordMapper.getListBy(cashRecord);
			for (CashRecord cashRecord2 : cashList) {
				money = cashRecord2.getCashMoney()+money;
			}
			webUser2.setCashMoney(money);
		}
		mv.addObject("userList",userList);
		mv.addObject("page",webUser);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/membermanagement/cashmember");
		return mv;
	}

   //红包金额提取记录列表
	@RequestMapping("/cashRecordList")
	public ModelAndView cashRecordList(CashRecord cashRecord){
		ModelAndView mv = new ModelAndView();
		List<CashRecord> cashRecordList = cashRecordMapper.getPageListBy(cashRecord);
		  for (CashRecord cashRecord2 : cashRecordList) {
			 WebUser webUser =  webUserMapper.selectByPrimaryKey(cashRecord2.getUserId());
			 cashRecord2.setWebUser(webUser);
		}
		mv.addObject("cashRecordList",cashRecordList);
		mv.addObject("page",cashRecord);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/membermanagement/cashregister");
		return mv;
	}

	//编辑红包金额提取
	@RequestMapping("/moneyRegister")
	public ModelAndView moneyRegister(Integer id){
		ModelAndView mv = new ModelAndView();
		WebUser webUser = webUserMapper.selectByPrimaryKey(id);
		mv.addObject("user",webUser);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/membermanagement/cashedit");
		return mv;

	}

	//更新用户剩余金额（提现）
	@RequestMapping("/saveCashMoney")
	public ModelAndView saveCashMoney(WebUser webUser){
		ModelAndView mv = new ModelAndView();
		if(webUser!=null){
			webUser.setRestMoney(webUser.getRestMoney()-webUser.getCashMoney());
			webUserMapper.updateByPrimaryKey(webUser);

			CashRecord cashRecord = new CashRecord();
			cashRecord.setCreatePeople(webUser.getCreatePeople());
			cashRecord.setCashMoney(webUser.getCashMoney());
			cashRecord.setCashTime(new Date());
			cashRecord.setUserId(webUser.getId());
			cashRecordMapper.insert(cashRecord);
		}
		mv.setViewName("redirect:"+SCHOOLMGR.SYSTEM_PATH_ADMIN + "/cashmember/cashlist");
		return mv;

	}


}

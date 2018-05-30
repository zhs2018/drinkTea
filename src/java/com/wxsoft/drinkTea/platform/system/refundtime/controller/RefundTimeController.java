package com.wxsoft.drinkTea.platform.system.refundtime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.platform.system.refundtime.mapper.RefundTimeMapper;
import com.wxsoft.drinkTea.platform.system.refundtime.model.RefundTime;
import com.wxsoft.drinkTea.platform.system.teaKing.mapper.RedMoneyMapper;
import com.wxsoft.drinkTea.platform.system.teaKing.model.RedMoney;

@Controller
@RequestMapping("system/tking")
public class RefundTimeController extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private RefundTimeMapper refundTimeMapper;
	@Autowired
	private RedMoneyMapper redMoneyMapper;

	@RequestMapping("timeChange")
	public ModelAndView timeChange() {
		ModelAndView mv = new ModelAndView();
		RefundTime rt = new RefundTime();
		List<RefundTime> rte = refundTimeMapper.getListBy(rt);
		mv.addObject("refundTime", rte);
		RedMoney redMoney = new RedMoney();
		List<RedMoney> redM = redMoneyMapper.getListBy(redMoney);
		mv.addObject("redMoney", redM);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/refundtime/teaKingList");
		return mv;
	}

	@RequestMapping("refundTime/edit")
	public ModelAndView editorTime() {
		ModelAndView mv = new ModelAndView();
		RefundTime rt = new RefundTime();
		RefundTime rte = refundTimeMapper.selectBy(rt);
		mv.addObject("refundTime", rte);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/refundtime/editTime");
		return mv;

	}

	// 红包
	@RequestMapping("refundTime/editRed")
	public ModelAndView editorRed() {
		ModelAndView mv = new ModelAndView();
		RedMoney rt = new RedMoney();
		RedMoney rte = redMoneyMapper.selectBy(rt);
		mv.addObject("redMoney", rte);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/refundtime/editRed");
		return mv;

	}

	// 倒计时
	@RequestMapping("refundTime/save")
	public ModelAndView saveTime(RefundTime refundTime) {
		ModelAndView mv = new ModelAndView();
		int flag = refundTimeMapper.updateByPrimaryKey(refundTime);
		if (flag == 1) {
			mv.setViewName("redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/tking/timeChange");
		} else {
			mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/refundtime/editTime");
		}

		return mv;

	}

	// 红包
	@RequestMapping("refundTime/saveRed")
	public ModelAndView saveRed(RedMoney redMoney) {
		ModelAndView mv = new ModelAndView();
		int flag = redMoneyMapper.updateByPrimaryKey(redMoney);
		if (flag == 1) {
			mv.setViewName("redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/tking/timeChange");
		} else {
			mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/refundtime/editRed");
		}

		return mv;

	}
}

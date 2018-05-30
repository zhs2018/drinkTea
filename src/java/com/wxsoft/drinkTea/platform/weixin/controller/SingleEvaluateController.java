package com.wxsoft.drinkTea.platform.weixin.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.platform.system.evaluate.mapper.EvaluateProMapper;
import com.wxsoft.drinkTea.platform.system.evaluate.model.EvaluatePro;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

@RequestMapping()
@Controller
public class SingleEvaluateController extends BaseAction{

	/**
	 * author:zss
	 *查看某个商品评价
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private EvaluateProMapper evaluateProMapper;
	@Autowired
	private WebUserMapper webUserMapper;
	@RequestMapping("/eve")
	public ModelAndView jumpToCommodityEvaluation(ManageProducts manageProducts,Integer id){
		ModelAndView mv = new ModelAndView();
		EvaluatePro evaluatePro = new EvaluatePro();
		evaluatePro.setGoodsId(id);
		evaluatePro.setStatus(1);
		List<EvaluatePro> list = evaluateProMapper.getListBy(evaluatePro);
		List<EvaluatePro> list1 = new ArrayList<>();
		for(EvaluatePro eva : list){
			WebUser user = webUserMapper.selectByPrimaryKey(eva.getUserId());
			eva.setContent(eva.getContent());
			eva.setEvaluateTime(eva.getEvaluateTime());
			eva.setWuName(user.getUserName());
			eva.setImage(user.getImage());
			list1.add(eva);
		}

		mv.addObject("resultList",list1);
		mv.setViewName("weixin/singleEvaluate");

		return mv;
	}
}

package com.wxsoft.drinkTea.platform.system.evaluate.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.platform.system.evaluate.mapper.EvaluateProMapper;
import com.wxsoft.drinkTea.platform.system.evaluate.model.EvaluatePro;
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 * 评论管理的controller
 * @author 王桐睿
 * com.wxsoft.drinkTea.platform.system.evaluate.controller
 * 2017年3月28日上午9:44:54
 */
@Controller
@RequestMapping("/system/evaluate")
public class EvaluateController extends BaseAction{

	/** */
	private static final long serialVersionUID = 1L;

	@Autowired
	private EvaluateProMapper evaluateMapper;
	@Autowired
	private WebUserMapper webuserMapper;
	@Autowired
	private ManageProductsMapper manageProductsMapper;

	/**
	 * @Title: evaluateList
	 * @date 时间：2017年3月28日上午9:47:23
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description:显示待审核的评论列表
	 */
	@RequestMapping("/wait")
	public ModelAndView waitList(EvaluatePro ep){
		ModelAndView mav = new ModelAndView("/system/evaluate/waitlist");
		ep.setStatus(0);
		List<EvaluatePro> list = evaluateMapper.getPageListLike(ep);
		for (EvaluatePro evaluatePro : list) {
		WebUser webUser = webuserMapper.selectByPrimaryKey(evaluatePro.getUserId());
		     evaluatePro.setWuName(webUser.getUserName());
		    ManageProducts manageProduct= manageProductsMapper.selectByPrimaryKey(evaluatePro.getGoodsId());
		    evaluatePro.setMpName(manageProduct.getName());
		}
		mav.addObject("objList",list);
		mav.addObject("ep",ep);
		return mav;
	}

	/**
	 * @Title: yesList
	 * @date 时间：2017年3月28日上午10:42:57
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description:显示已经审核通过的列表
	 */
	@RequestMapping("/yes")
	public ModelAndView yesList(EvaluatePro ep){
		ModelAndView mav = new ModelAndView("/system/evaluate/yeslist");
		ep.setStatus(1);
		List<EvaluatePro> list = evaluateMapper.getPageListLike(ep);
		for (EvaluatePro evaluatePro : list) {
		WebUser webUser = webuserMapper.selectByPrimaryKey(evaluatePro.getUserId());
		     evaluatePro.setWuName(webUser.getUserName());
		    ManageProducts manageProduct= manageProductsMapper.selectByPrimaryKey(evaluatePro.getGoodsId());
		    evaluatePro.setMpName(manageProduct.getName());
		}
		mav.addObject("objList",list);
		mav.addObject("ep",ep);
		return mav;
	}

	/**
	 * @Title: noList
	 * @date 时间：2017年3月28日上午10:43:43
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description:显示审核拒绝的列表
	 */
	@RequestMapping("/no")
	public ModelAndView noList(EvaluatePro ep){
		ModelAndView mav = new ModelAndView("/system/evaluate/nolist");
		ep.setStatus(2);
		List<EvaluatePro> list = evaluateMapper.getPageListLike(ep);
		for (EvaluatePro evaluatePro : list) {
		WebUser webUser = webuserMapper.selectByPrimaryKey(evaluatePro.getUserId());
		     evaluatePro.setWuName(webUser.getUserName());
		    ManageProducts manageProduct= manageProductsMapper.selectByPrimaryKey(evaluatePro.getGoodsId());
		    evaluatePro.setMpName(manageProduct.getName());
		}
		mav.addObject("objList",list);
		mav.addObject("ep",ep);
		return mav;
	}

	/**
	 * @Title: checkout
	 * @date 时间：2017年3月29日上午11:46:09
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description: 审核评论，即修改审核状态
	 */
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public void checkout(HttpServletResponse response,EvaluatePro param){
		Map<String,String> result = new HashMap<String,String>();//存放返回前台的信息
		int a = evaluateMapper.updateByPrimaryKey(param);
		if(a < 1 ){
			result.put("status", "0");
			result.put("Message", "审核评论失败，请稍后再试");
		}else{
			result.put("status", "1");
		}
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}

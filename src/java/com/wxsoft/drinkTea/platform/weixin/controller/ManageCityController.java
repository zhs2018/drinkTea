package com.wxsoft.drinkTea.platform.weixin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.platform.system.evaluate.mapper.EvaluateProMapper;
import com.wxsoft.drinkTea.platform.system.evaluate.model.EvaluatePro;
/**
 * @author lzj
 * @描述：商城
 * @时间 2017-4-12 17:06
 */
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;

@Controller
@RequestMapping()
public class ManageCityController extends BaseAction {
	@Autowired
	private ManageProductsMapper manageProductsMapper;
	@Autowired
	private EvaluateProMapper evaluateProMapper;

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
/**
 * @author lzj
 * @描述：进入商城主页
 * @时间：2017-4-13
 * @return
 */

	@RequestMapping("/rule")
	public ModelAndView rule() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("weixin/rule");
		return mv;
	}

	@RequestMapping("/manageCity")
	public ModelAndView manageCity() {
		ModelAndView mv = new ModelAndView();
		ManageProducts mProducts = new ManageProducts();
		mProducts.setSign(3);
		mProducts.setValiable(1);
		List<ManageProducts> mList = manageProductsMapper.getListBy(mProducts);
		mv.addObject("ManageProduct", mList);
		mv.setViewName("weixin/classification");
		return mv;
	}
	/**
	 * @author lzj
	 * @描述：进入商品详情页
	 * @时间：2017-4-13
	 * @return
	 */
	@RequestMapping("/productDetails")
	public ModelAndView productDetails(Integer id) {
		ModelAndView mv = new ModelAndView();
		ManageProducts mmProducts = manageProductsMapper.selectByPrimaryKey(id);
		if(null!=mmProducts.getSaleCount()){
			mv.addObject("mP", mmProducts);
		}else{
			mmProducts.setSaleCount(0);
		}
		EvaluatePro evaluatePro = new EvaluatePro();
		evaluatePro.setGoodsId(id);
		evaluatePro.setStatus(1);
		List<EvaluatePro> list = evaluateProMapper.getListBy(evaluatePro);
		if(0==list.size()){
			mv.addObject("count",0);
		}else{
			mv.addObject("count",list.size());
		}

		mv.setViewName("weixin/index");
		mv.addObject("mP", mmProducts);
		return mv;
	}
	/**
	 * @author lzj
	 * @描述：根据不同的条件选择商品排序
	 * @时间：2017-4-13
	 * @return
	 */
	@RequestMapping("/relyPrice")
	public ModelAndView relyPrice(Integer ty) {
		ModelAndView mv = new ModelAndView();
		System.out.println("ty:" + ty);
		ManageProducts mProducts = new ManageProducts();
		if(ty==1){
			mProducts.setSign(3);
			mProducts.setValiable(1);
			List<ManageProducts> mList = manageProductsMapper.getListBy(mProducts);
			mv.addObject("ManageProduct", mList);
		}else if(ty==2){
			mProducts.setSign(3);
			mProducts.setValiable(1);
			List<ManageProducts> mList = manageProductsMapper.getListBysaleCount(mProducts);
			mv.addObject("ManageProduct", mList);
		}else if (ty == 3) {
			mProducts.setSign(3);
			mProducts.setValiable(1);
			List<ManageProducts> mList = manageProductsMapper.getListByPrice(mProducts);
			mv.addObject("ManageProduct", mList);
		}
		 mv.addObject("ty", ty);
		 mv.setViewName("weixin/classification");
		return mv;
	}
}

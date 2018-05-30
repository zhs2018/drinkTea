/**
 * @文件名称: CaiwuControlloer.java
 * @类路径: com.wxsoft.drinkTea.platform.system.caiwu.controller
 * @作者：zss
 * @时间：2017-03-27 上午11:25:52
 */
package com.wxsoft.drinkTea.platform.system.caiwu.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.framework.utils.Tools;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;

/**
 * @类功能说明：财务管理
 * @类修改者：zss @修改说明：
 */
@Controller
@RequestMapping(SCHOOLMGR.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/caiwu")
public class CaiwuControlloer extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ProductOrderMapper productOrderMapper;

	/**
	 * @描述: 入账资金记录
	 * @作者: zss
	 * @修改内容 @参数： @param response @参数： @return
	 * @return ModelAndView
	 * @throws @修改：lzj
	 * 			@时间：2017-4-20 09:50
	 */
	@RequestMapping("/moneylist")
	public ModelAndView moneylist(HttpServletRequest request, ProductOrder productOrder, HttpSession session,
			String ttime) {
		ModelAndView mv = new ModelAndView();
		Double allMoney = 0.0;
		List<ProductOrder> listM = productOrderMapper.getListBy(productOrder);
		for (ProductOrder productOrder2 : listM) {
			if (productOrder2.getOrderState()!=null) {
				if (productOrder2.getOrderState() == 30 || productOrder2.getOrderState() == 40
						|| productOrder2.getOrderState() == 50 || productOrder2.getOrderState() == 60
						|| productOrder2.getOrderState() == 80) {
					double money = productOrder2.getPrice();
					allMoney = allMoney + money;
				}
			}
		}
		List<ProductOrder> list = productOrderMapper.getPageListByOrderState(productOrder);
		mv.addObject("objList", list);
		mv.addObject("moneyAll", allMoney);
		mv.addObject("productOrder", productOrder);
		mv.setViewName("/system/caiwu/moneylist");
		return mv;
	}

	/**
	 * @描述: 入账资金记录根据时间查询
	 * @作者: zss
	 * @修改内容 @参数： @param response @参数： @return
	 * @return ModelAndView
	 * @throws @修改：lzj
	 * 			@时间：2017-4-20 11:00
	 */
	@RequestMapping("/moneylists")
	public ModelAndView moneylists(HttpServletRequest request, ProductOrder productOrder, HttpSession session,
			String ttime) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/caiwu/moneylist");
		if (Tools.isEmpty(ttime)) {
			Double allMoney = 0.0;
			List<ProductOrder> listM = productOrderMapper.getListBy(productOrder);
			for (ProductOrder productOrder2 : listM) {
				if (productOrder2.getOrderState()!=null) {
					if (productOrder2.getOrderState() == 30 || productOrder2.getOrderState() == 40
							|| productOrder2.getOrderState() == 50 || productOrder2.getOrderState() == 60
							|| productOrder2.getOrderState() == 80) {
						double money = productOrder2.getPrice();
						allMoney = allMoney + money;
					}
			}
				}
			List<ProductOrder> list = productOrderMapper.getPageListByOrderState(productOrder);
			mv.addObject("objList", list);
			mv.addObject("moneyAll", allMoney);
			mv.addObject("productOrder", productOrder);
		} else if (Tools.notEmpty(ttime)) {
			try {
				String start = ttime.split("~")[0].trim();
				String end = ttime.split("~")[1].trim();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = dateFormat.parse(start);
				Date date2 = dateFormat.parse(end);
				productOrder.setStartTime(date1);
				productOrder.setEndTime(date2);
				Double allMoney = 0.0;
				List<ProductOrder> listM = productOrderMapper.getBycount(productOrder);
				for (ProductOrder productOrder2 : listM) {
					if (productOrder2.getOrderState()!=null) {
						if (productOrder2.getOrderState() == 30 || productOrder2.getOrderState() == 40
								|| productOrder2.getOrderState() == 50 || productOrder2.getOrderState() == 60
								|| productOrder2.getOrderState() == 80) {
							double money = productOrder2.getPrice();
							allMoney = allMoney + money;
						}
				}
				List<ProductOrder> list = productOrderMapper.getPageListByApplyTime(productOrder);
				mv.addObject("objList", list);
				mv.addObject("moneyAll", allMoney);
				mv.addObject("ttime", ttime);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mv;
	}

	/**
	 * @描述: 销售资金记录
	 * @作者: zss
	 * @修改内容 @参数： @param response @参数： @return
	 * @return ModelAndView
	 * @throws @修改：lzj
	 * 			@时间：2017-4-20 11:35
	 */
	@RequestMapping("/moneylist2")
	public ModelAndView moneylist2(HttpServletRequest request, String ttime, ProductOrder productOrder,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Double allMoney = 0.0;
		List<ProductOrder> listM = productOrderMapper.getListBy(productOrder);
		for (ProductOrder productOrder2 : listM) {
			if(productOrder2.getOrderState()!=null){
				if (productOrder2.getOrderState() == 30 || productOrder2.getOrderState() == 40
						|| productOrder2.getOrderState() == 50 || productOrder2.getOrderState() == 60
						|| productOrder2.getOrderState() == 70 || productOrder2.getOrderState() == 80) {
					double money = productOrder2.getPrice();
					allMoney = allMoney + money;
			}
			}
		}
		List<ProductOrder> list = productOrderMapper.getPageListByOrderStates(productOrder);
		mv.addObject("objList", list);
		mv.addObject("moneyAll", allMoney);
		mv.addObject("productOrder", productOrder);
		mv.setViewName("/system/caiwu/moneylist2");
		return mv;
	}

	/**
	 * @描述: 销售资金记录根据时间查询
	 * @作者: zss
	 * @修改内容 @参数： @param response @参数： @return
	 * @return ModelAndView
	 * @throws @修改：lzj
	 * 			@时间：2017-4-20 11:55
	 */
	@RequestMapping("/moneylists2")
	public ModelAndView moneylists2(HttpServletRequest request, ProductOrder productOrder, String ttime,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/caiwu/moneylist2");
		if (Tools.isEmpty(ttime)) {
			Double allMoney = 0.0;
			List<ProductOrder> listM = productOrderMapper.getListBy(productOrder);
			for (ProductOrder productOrder2 : listM) {
				if(productOrder2.getOrderState()!=null){
					if (productOrder2.getOrderState() == 30 || productOrder2.getOrderState() == 40
							|| productOrder2.getOrderState() == 50 || productOrder2.getOrderState() == 60
							|| productOrder2.getOrderState() == 70 || productOrder2.getOrderState() == 80) {
						double money = productOrder2.getPrice();
						allMoney = allMoney + money;
					}
				}

			}
			List<ProductOrder> list = productOrderMapper.getPageListByOrderStates(productOrder);
			mv.addObject("objList", list);
			mv.addObject("moneyAll", allMoney);
			mv.addObject("productOrder", productOrder);
		} else if (Tools.notEmpty(ttime)) {
			try {
				String start = ttime.split("~")[0].trim();
				String end = ttime.split("~")[1].trim();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = dateFormat.parse(start);
				Date date2 = dateFormat.parse(end);
				productOrder.setStartTime(date1);
				productOrder.setEndTime(date2);
				Double allMoney = 0.0;
				List<ProductOrder> listM = productOrderMapper.getBycount1(productOrder);
				for (ProductOrder productOrder2 : listM) {
					if(productOrder2.getOrderState()!=null){
						if (productOrder2.getOrderState() == 30 || productOrder2.getOrderState() == 40
								|| productOrder2.getOrderState() == 50 || productOrder2.getOrderState() == 60
								|| productOrder2.getOrderState() == 70 || productOrder2.getOrderState() == 80) {
							double money = productOrder2.getPrice();
							allMoney = allMoney + money;
						}
					}

				}
				List<ProductOrder> list = productOrderMapper.getPageListByApplyTime(productOrder);
				mv.addObject("objList", list);
				mv.addObject("moneyAll", allMoney);
				mv.addObject("ttime", ttime);
				mv.addObject("productOrder", productOrder);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mv;
	}
}
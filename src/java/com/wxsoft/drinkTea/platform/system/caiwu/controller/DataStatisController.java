package com.wxsoft.drinkTea.platform.system.caiwu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.platform.system.caiwu.model.DataStatis;
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;

/**
 *
 * @author lzj
 * @描述：数据中心 @时间：2017-4-21 11:00
 *
 */
@Controller
@RequestMapping("/system")
public class DataStatisController extends BaseAction {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ManageProductsMapper manageProductsMapper;
	@Autowired
	private ProductOrderMapper productOrderMapper;

	@RequestMapping("/dataStatis")
	public ModelAndView orderList() {
		ModelAndView mv = new ModelAndView();
		ManageProducts manageProducts = new ManageProducts();
		manageProducts.setValiable(1);
		int pOnCounts = manageProductsMapper.getTotalCount(1);
		manageProducts.setValiable(2);
		int pOutCounts = manageProductsMapper.getTotalCount(2);
		ProductOrder productOrder = new ProductOrder();
		productOrder.setOrderState(10);
		int CancelOrderCount = productOrderMapper.getOrderCounts(productOrder);
		productOrder.setOrderState(20);
		int HasOrderCount = productOrderMapper.getOrderCounts(productOrder);
		productOrder.setOrderState(30);
		int SendOrderCount = productOrderMapper.getOrderCounts(productOrder);
		productOrder.setOrderState(40);
		int AcceptOrderCount = productOrderMapper.getOrderCounts(productOrder);
		productOrder.setOrderState(60);
		int refundOrderCount = productOrderMapper.getOrderCounts(productOrder);
		int FinishOrderCount = productOrderMapper.getOrdersCounts(productOrder);
		ProductOrder productOrder2 = new ProductOrder();
		List<ProductOrder> list = productOrderMapper.getListBy(productOrder2);
		System.out.println(list.size() + "_+_+_++");
		Double totalMoney = 0.0;
		for (ProductOrder productOrder3 : list) {
			if (null != productOrder3.getApplyTime()) {
				totalMoney = totalMoney + productOrder3.getPrice();
			}
		}
		mv.addObject("pOnCounts", pOnCounts);
		mv.addObject("pOutCounts", pOutCounts);
		mv.addObject("CancelOrderCount", CancelOrderCount);
		mv.addObject("HasOrderCount", HasOrderCount);
		mv.addObject("SendOrderCount", SendOrderCount);
		mv.addObject("AcceptOrderCount", AcceptOrderCount);
		mv.addObject("refundOrderCount", refundOrderCount);
		mv.addObject("FinishOrderCount", FinishOrderCount);
		mv.addObject("AllOrderCount", list.size());
		mv.addObject("totalMoney", totalMoney);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/main2");
		return mv;
	}

	@RequestMapping("/getData")
	@ResponseBody
	public JSONObject getDataStatis(String sTime) {
		JSONObject json = new JSONObject();
		DataStatis dStatis = new DataStatis();
		dStatis.setApplyTime(sTime);
		List<DataStatis> pList = productOrderMapper.getListByTime(dStatis);
		List<DataStatis> pList2 = new ArrayList<>();
		for (DataStatis dataStatis : pList) {
			DataStatis dataStatis2 = new DataStatis();
			dataStatis2.setApplyTime((dataStatis.getApplyTime()).substring(8));
			dataStatis2.setTotalPrice(dataStatis.getTotalPrice());
			pList2.add(dataStatis2);
		}
		if (pList2.size() < 1 || null == pList2) {
			System.out.println("输出的结果是");
			json.put("result", "no");
		} else {
			json.put("data", pList2);
			json.put("result", "ok");
		}
		return json;
	}

}

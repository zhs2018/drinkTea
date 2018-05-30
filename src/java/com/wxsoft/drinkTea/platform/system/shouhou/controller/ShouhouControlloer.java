/**
 * @描述: TODO
 * @作者：zss
 */
package com.wxsoft.drinkTea.platform.system.shouhou.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.controller.BaseController;
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.order.mapper.GoodsOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.GoodsOrder;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.system.shouhou.mapper.ShouhouServiceMapper;
import com.wxsoft.drinkTea.platform.system.shouhou.model.ShouhouBean;
import com.wxsoft.drinkTea.platform.weixin.mapper.RefundMessageMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.UserAddressMapper;
import com.wxsoft.drinkTea.platform.weixin.model.RefundMessage;
import com.wxsoft.drinkTea.platform.weixin.model.UserAddress;

/**
 * @类功能说明：售后管理 @修改说明：
 * @作者：zss
 */

@Controller
@RequestMapping("/system/shouhou")
public class ShouhouControlloer extends BaseController {

	@Autowired
	private ShouhouServiceMapper shouhouServiceMapper;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private RefundMessageMapper refundMessageMapper;
	@Autowired
	private UserAddressMapper userAddressMapper;
	@Autowired
	private GoodsOrderMapper goodsOrderMappper;
	@Autowired
	private ManageProductsMapper manageProductsMapper;

	/**
	 *
	 * @描述: 退货订单信息 @作者: zss @修改内容 @参数： @param response @参数： @return @return
	 * ModelAndView @throws
	 */
	@RequestMapping("/returnOrder/{ty}")
	public ModelAndView returnordersinfo(@PathVariable Integer ty) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/shouhou/returnlist");
		ShouhouBean bean = new ShouhouBean();
		bean.setStat(ty);
		List<ShouhouBean> shouhouBeans = shouhouServiceMapper.getPageListBy(bean);
		mv.addObject("ty", ty);
		mv.addObject("orResultlist", shouhouBeans);
		return mv;
	}

	/**
	 * 退货
	 *
	 * @param session
	 * @param loginname
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView shenqing() {
		ModelAndView mv = new ModelAndView();
		ShouhouBean bean = new ShouhouBean();
		// 显示所有申请退货的
		bean.setStat(-1);
		List<ShouhouBean> shouhouBeans = shouhouServiceMapper.getPageListBy(bean);
		mv.addObject("os", bean);
		mv.addObject("shenqing", shouhouBeans.size());
		mv.addObject("orResultlist", shouhouBeans);
		mv.setViewName("system/shouhou/returnlist");
		return mv;
	}

	/**
	 * @Title: shenhezhong
	 * @date 时间：2017年4月6日下午1:36:50
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description:显示待审核列表
	 * @修改：lzj @时间：2017-4-19 09:20
	 */
	@RequestMapping(value = "/ing")
	public ModelAndView shenhezhong(ProductOrder pOrder) {
		ModelAndView mv = new ModelAndView();
		pOrder.setOrderState(60);
		List<ProductOrder> productOrders = productOrderMapper.getPageListBy(pOrder);
		List<ProductOrder> list = new ArrayList<>();
		for (ProductOrder productOrder : productOrders) {
			ProductOrder prO = new ProductOrder();
			GoodsOrder gOrder = new GoodsOrder();
			gOrder.setOrderId(productOrder.getId());
			ManageProducts mProducts = manageProductsMapper.selectByPrimaryKey(goodsOrderMappper.selectBy(gOrder).getGoodsId());
			RefundMessage refundM = new RefundMessage();
			refundM.setOrderId(productOrder.getId());
			RefundMessage refundMessage = refundMessageMapper.selectBy(refundM);
			UserAddress userAddress = userAddressMapper.selectByPrimaryKey(productOrder.getAddressId());
			prO.setUserAddress(userAddress);
			prO.setRefundMessage(refundMessage);
			prO.setManageProducts(mProducts);
			prO.setOrderNumber(productOrder.getOrderNumber());
			prO.setOrderState(productOrder.getOrderState());
			prO.setId(productOrder.getId());
			list.add(prO);
		}
		mv.addObject("os",pOrder);
		mv.addObject("ing", list.size());
		mv.addObject("orResultlist", list);
		mv.setViewName("system/shouhou/ing");
		return mv;
	}

	/**
	 * @Title: yes
	 * @date 时间：2017年4月6日下午1:37:10
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description:显示已通过列表
	 * @修改：lzj @时间：2017-4-19 10:20
	 */
	@RequestMapping(value = "/yes")
	public ModelAndView yes(ProductOrder pOrder) {
		ModelAndView mv = new ModelAndView();
		pOrder.setOrderState(70);
		List<ProductOrder> productOrders = productOrderMapper.getPageListBy(pOrder);
		List<ProductOrder> list = new ArrayList<>();
		for (ProductOrder productOrder : productOrders) {
			ProductOrder prO = new ProductOrder();
			GoodsOrder gOrder = new GoodsOrder();
			gOrder.setOrderId(productOrder.getId());
			ManageProducts mProducts = manageProductsMapper.selectByPrimaryKey(goodsOrderMappper.selectBy(gOrder).getGoodsId());
			RefundMessage refundM = new RefundMessage();
			refundM.setOrderId(productOrder.getId());
			RefundMessage refundMessage = refundMessageMapper.selectBy(refundM);
			UserAddress userAddress = userAddressMapper.selectByPrimaryKey(productOrder.getAddressId());
			prO.setUserAddress(userAddress);
			prO.setRefundMessage(refundMessage);
			prO.setManageProducts(mProducts);
			prO.setOrderNumber(productOrder.getOrderNumber());
			prO.setOrderState(productOrder.getOrderState());
			prO.setId(productOrder.getId());
			list.add(prO);
		}
		mv.addObject("os",pOrder);
		mv.addObject("yes", list.size());
		mv.addObject("orResultlist", list);
		mv.setViewName("system/shouhou/yes");
		return mv;
	}

	/**
	 * @Title: no
	 * @date 时间：2017年4月6日下午1:37:27
	 * @author 作者： 王桐睿
	 * @return 返回类型：ModelAndView
	 * @Description:显示已拒绝列表
	 * @修改：lzj @时间：2017-4-19 11:00
	 */
	@RequestMapping(value = "/no")
	public ModelAndView no(ProductOrder pOrder) {
		ModelAndView mv = new ModelAndView();
		pOrder.setOrderState(80);
		List<ProductOrder> productOrders = productOrderMapper.getPageListBy(pOrder);
		List<ProductOrder> list = new ArrayList<>();
		for (ProductOrder productOrder : productOrders) {
			ProductOrder prO = new ProductOrder();
			GoodsOrder gOrder = new GoodsOrder();
			gOrder.setOrderId(productOrder.getId());
			ManageProducts mProducts = manageProductsMapper.selectByPrimaryKey(goodsOrderMappper.selectBy(gOrder).getGoodsId());
			RefundMessage refundM = new RefundMessage();
			refundM.setOrderId(productOrder.getId());
			RefundMessage refundMessage = refundMessageMapper.selectBy(refundM);
			UserAddress userAddress = userAddressMapper.selectByPrimaryKey(productOrder.getAddressId());
			prO.setUserAddress(userAddress);
			prO.setRefundMessage(refundMessage);
			prO.setManageProducts(mProducts);
			prO.setOrderNumber(productOrder.getOrderNumber());
			prO.setOrderState(productOrder.getOrderState());
			prO.setNoReason(productOrder.getNoReason());
			list.add(prO);
		}
		mv.addObject("os",pOrder);
		mv.addObject("no", list.size());
		mv.addObject("orResultlist", list);
		mv.setViewName("system/shouhou/no");
		return mv;
	}

	/**
	 * @Title: shenheResult
	 * @date 时间：2017年4月6日下午1:38:38
	 * @author 作者： 王桐睿
	 * @return 返回类型：void
	 * @Description:审核的处理方法
	 */
	@RequestMapping(value = "/shenheResult")
	public void shenheResult(HttpServletResponse response, ProductOrder bean) {
		Map<String, String> map = new HashMap<String, String>();// 存放返回前台的信息
		if (bean != null) {
			int a = productOrderMapper.updateByPrimaryKey(bean);
			if (a == 1) {
				map.put("status", "1");
			} else {
				map.put("status", "0");
				map.put("Message", "审核失败，请稍后再试");
			}
		} else {
			map.put("status", "0");
			map.put("Message", "获取审核状态失败，请稍后再试");
		}
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
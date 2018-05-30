package com.wxsoft.drinkTea.platform.weixin.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.order.mapper.GoodsOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.GoodsOrder;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.weixin.mapper.RefundMessageMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.UserAddressMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.RefundMessage;
import com.wxsoft.drinkTea.platform.weixin.model.UserAddress;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;


/**
 *
 * @author lzj 订单
 *
 */
@RequestMapping("shop")
@Controller
public class OrdersController extends BaseAction {
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private GoodsOrderMapper goodsOrderMapper;
	@Autowired
	private ManageProductsMapper manageProductsMapper;
	@Autowired
	private UserAddressMapper userAddressMapper;
	@Autowired
	private RefundMessageMapper refundMessageMapper;

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @author lzj
	 * @描述：跳转到支付成功
	 * @时间：2017-4-11
	 */
	@RequestMapping("/paymentSuccess")
	public ModelAndView getForm(Integer id) {
		ModelAndView mv = new ModelAndView();
      ProductOrder productOrder = productOrderMapper.selectByPrimaryKey(id);
        UserAddress userAddress= userAddressMapper.selectByPrimaryKey(productOrder.getAddressId());
        GoodsOrder goodsOrder = new GoodsOrder();
          goodsOrder.setOrderId(id);
         GoodsOrder gOrder =  goodsOrderMapper.selectBy(goodsOrder);
            mv.addObject("address", userAddress);
            mv.addObject("order", productOrder);
            mv.addObject("gOrder",gOrder);
            mv.setViewName("weixin/paymentSuccess");

		return mv;
	}

	/**
	 * @author lzj
	 * @描述：跳转到index页面
	 * @时间：2017-4-11
	 */
	@RequestMapping("/indexs")
	public ModelAndView getindex() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("weixin/index");
		return mv;

	}

	/**
	 * @author lzj
	 * @描述：我的订单
	 * @时间：2017-4-11
	 */
	@RequestMapping("/myOrder")
	public ModelAndView getMyOrder(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		WebUser webUser = (WebUser) session.getAttribute("user");
		if (webUser != null) {
			ProductOrder pr = new ProductOrder();
			System.out.println("yonghu id"+webUser.getId());
			pr.setUserId(webUser.getId());
			List<ProductOrder> orderList = productOrderMapper.getListBy(pr);
			for (ProductOrder productOrder : orderList) {
				GoodsOrder go = new GoodsOrder();
				go.setOrderId(productOrder.getId());
				List<GoodsOrder> gList = goodsOrderMapper.getListBy(go);
				List<ManageProducts> manageList = new ArrayList<>();
				for (GoodsOrder goodsOrder : gList) {
					productOrder.setCount(goodsOrder.getGoodsCount());
					ManageProducts mp = manageProductsMapper.selectByPrimaryKey(goodsOrder.getGoodsId());
			                           		manageList.add(mp);
				}
				productOrder.setProducts(manageList);
			}
			mv.addObject("OrderList", orderList);
			mv.setViewName("weixin/myOrder");
		} else {
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/index");
		}
		return mv;
	}

	/**
	 * @author lzj
	 * @描述：根据订单的状态显示订单
	 * @时间：2017-4-11
	 */
	@RequestMapping("/orderStatus/{ty}")
	public ModelAndView getOrderStatus(@PathVariable Integer ty,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		WebUser wu = (WebUser) session.getAttribute("user");
		if(wu == null){
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/index");
			return  mv;
		}
		if (ty == 2) {
			ProductOrder pro = new ProductOrder();
			pro.setUserId(wu.getId());
			pro.setOrderState(20);
			List<ProductOrder> orderList = OrdersUtils.orderUtils(productOrderMapper, goodsOrderMapper,
					manageProductsMapper, pro);
			mv.addObject("OrderList", orderList);

		}
		if (ty == 3) {
			ProductOrder pro = new ProductOrder();
			pro.setUserId(wu.getId());
			pro.setOrderState(30);
			List<ProductOrder> orderList = OrdersUtils.orderUtils(productOrderMapper, goodsOrderMapper,
					manageProductsMapper, pro);
			mv.addObject("OrderList", orderList);
		}
		if (ty == 4) {
			ProductOrder pro = new ProductOrder();
			pro.setUserId(wu.getId());
			pro.setOrderState(40);
			List<ProductOrder> orderList = OrdersUtils.orderUtils(productOrderMapper, goodsOrderMapper,
					manageProductsMapper, pro);
			mv.addObject("OrderList", orderList);
		}
		if (ty == 5) {
			ProductOrder pro = new ProductOrder();
			pro.setUserId(wu.getId());
			pro.setOrderState(50);
			List<ProductOrder> orderList = OrdersUtils.orderUtils(productOrderMapper, goodsOrderMapper,
					manageProductsMapper, pro);
			mv.addObject("OrderList", orderList);
		}
		if (ty == 1) {
			ProductOrder pr = new ProductOrder();
			pr.setUserId(wu.getId());
			List<ProductOrder> orderList = productOrderMapper.getListBy(pr);
			for (ProductOrder productOrder : orderList) {
				GoodsOrder go = new GoodsOrder();
				go.setOrderId(productOrder.getId());
				List<GoodsOrder> gList = goodsOrderMapper.getListBy(go);
				List<ManageProducts> manageList = new ArrayList<>();
				for (GoodsOrder goodsOrder : gList) {
					productOrder.setCount(goodsOrder.getGoodsCount());
					ManageProducts mp = manageProductsMapper.selectByPrimaryKey(goodsOrder.getGoodsId());
					manageList.add(mp);
				}
				productOrder.setProducts(manageList);
			}
			mv.addObject("OrderList", orderList);

		}
		 mv.addObject("ty", ty);
		mv.setViewName("weixin/myOrder");
		return mv;
	}

	/**
	 * @author lzj
	 * @描述：查看订单详情
	 * @时间：2017-4-11
	 */
	@RequestMapping("/seeOrders/{id}/{orderStatus}")
	public ModelAndView getSee(HttpSession session,@PathVariable Integer id, @PathVariable Integer orderStatus) {
		ModelAndView mv = new ModelAndView();
		ProductOrder po = new ProductOrder();
		po.setId(id);
		po.setOrderState(orderStatus);
		ProductOrder pr = productOrderMapper.selectBy(po);
		UserAddress us = new UserAddress();
		us.setId(pr.getAddressId());
		UserAddress userAddress = userAddressMapper.selectBy(us);
		mv.addObject("Address", userAddress);
		GoodsOrder go = new GoodsOrder();
		go.setOrderId(id);

		List<GoodsOrder> gList = goodsOrderMapper.getListBy(go);
		List<ManageProducts> manageList = new ArrayList<>();
		for (GoodsOrder goodsOrder : gList) {
			pr.setCount(goodsOrder.getGoodsCount());
			ManageProducts mp = manageProductsMapper.selectByPrimaryKey(goodsOrder.getGoodsId());
			manageList.add(mp);
		}
		mv.addObject("Order", pr);
		mv.addObject("Products", manageList);
		if (orderStatus == 20) {
			mv.setViewName("weixin/orderDetails-pendingPayment");
		}
		if (orderStatus == 30) {
			mv.setViewName("weixin/orderDetails-pendingDelivery");
		}
		if (orderStatus == 40) {
			mv.setViewName("weixin/orderDetails-pendingReceipt");
		}
		if (orderStatus == 50) {
			mv.setViewName("weixin/orderDetails-successfulTrade");
		}
		if(orderStatus ==60||orderStatus ==70||orderStatus ==80){
			RefundMessage refundMessage = new RefundMessage();
			refundMessage.setOrderId(id);
		RefundMessage rMessage =refundMessageMapper.selectBy(refundMessage);
			mv.addObject("refund", rMessage);
			mv.setViewName("weixin/refundDetail");
		}


		return mv;
	}

	/**
	 * @author lzj
	 * @描述：取消订单
	 * @时间：2017-4-11
	 */
	@RequestMapping("/cancel_Order")
	public void cancelOrder(Integer id, HttpServletResponse response) {
		Map<String, String> map = new HashMap<>();
		ProductOrder productOrder = new ProductOrder();
		productOrder.setOrderState(10);
		productOrder.setId(id);
		int flag = productOrderMapper.updateByPrimaryKey(productOrder);
		if (flag == 1) {
			map.put("status", "1");
		}
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author lzj
	 * @描述：立即评价商品
	 * @时间：2017-4-11
	 */
	@RequestMapping("/evaluateOrder/{id}")
	public ModelAndView evaluateOrder(@PathVariable Integer id) {
		System.out.println("shuchu "+id);
		ModelAndView mv = new ModelAndView();
		if(null!=id){
			GoodsOrder go = new GoodsOrder();
			go.setOrderId(id);
			GoodsOrder goodsOrder = goodsOrderMapper.selectBy(go);
			ManageProducts manageProducts = manageProductsMapper.selectByPrimaryKey(goodsOrder.getGoodsId());
			mv.addObject("orderId", id);
			mv.addObject("mp", manageProducts);
		}
		mv.setViewName("weixin/publishedEvaluation");
		return mv;
	}

	/**
	 * @author yzy
	 * @描述：确认收货
	 * @时间：2017-4-21
	 */
	@RequestMapping(value = "/confirmReceipt", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject confirmReceipt(Integer id) {
		JSONObject jsonObject = new JSONObject();
		if(null!=id){
			ProductOrder order = productOrderMapper.selectByPrimaryKey(id);
			if (order == null) {
				jsonObject.put("result", "1");
				jsonObject.put("message", "确认收货失败，该订单已经被删除！");
			}else{
				//50:已完成（待评价）？
				order.setOrderState(50);
				order.setAcceptTime(new Date());
				int count = productOrderMapper.updateByPrimaryKey(order);
				if(count == 1){
					jsonObject.put("result", "0");
					jsonObject.put("message", "ok");
				}else{
					jsonObject.put("result", "1");
					jsonObject.put("message", "确认收货失败，请重试！");
				}
//				mv.setViewName("redirect:"+ DomainProperties.DOMAIN_WWW+"/shop/evaluateOrder/"+id);
			}
		}else{
			jsonObject.put("result", "1");
			jsonObject.put("message", "系统错误，找不到该订单！");
		}
		return jsonObject;
	}
}

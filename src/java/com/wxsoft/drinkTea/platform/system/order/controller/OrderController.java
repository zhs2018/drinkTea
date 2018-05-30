package com.wxsoft.drinkTea.platform.system.order.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.framework.utils.OrderSendExcelView;
import com.wxsoft.drinkTea.framework.utils.Tools;
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.order.mapper.GoodsOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.GoodsOrder;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.system.order.service.IOrderService;
import com.wxsoft.drinkTea.platform.weixin.mapper.UserAddressMapper;
import com.wxsoft.drinkTea.platform.weixin.model.UserAddress;

@Controller
@RequestMapping(SCHOOLMGR.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/orders")
public class OrderController {
	@Autowired
	private IOrderService orderService;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private GoodsOrderMapper goodsOrderMapper;
	@Autowired
	private ManageProductsMapper manageProductsMapper;
	@Autowired
	private UserAddressMapper userAddressMapper;

	/**
	 *
	 * @描述: 显示订单 @作者:lzj @日期:2017-3-28 @修改内容 @参数： @param @参数： @return
	 *      ModelAndView @throws
	 */
	@RequestMapping("/list")
	public ModelAndView orderList(ProductOrder obj) {
		ModelAndView mv = new ModelAndView();
		List<ProductOrder> orders = productOrderMapper.getPageListBy(obj);
		List<ProductOrder> list = orderService.getList(orders);
		mv.addObject("orResultlist", list);
		mv.addObject("obj", obj);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/orders/list");
		return mv;
	}

	/**
	 * @描述: 订单详细信息 @作者: lzj @日期:2017-3-28 @参数： @param @参数： @return
	 *      ModelAndView @throws
	 */

	@RequestMapping("/ordersinfo/{orderid}")
	public ModelAndView ordersInfo(HttpServletRequest request, @PathVariable Integer orderid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		ProductOrder list = orderService.getListMess(orderid);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/orders/info");
		mv.addObject("orders", list);
		return mv;
	}

	/**
	 *
	 * @描述: 根据不同条件查询订单 @作者:lzj @日期:2017-3-30 @修改内容 @参数： @param
	 *      response @参数： @return ModelAndView @throws
	 */
	@RequestMapping("/orderslist")
	public ModelAndView ordersList(HttpServletRequest request, ProductOrder or, String ordersnq, Integer orderNumber,
			String ttime, HttpSession session, Integer ty) {
		session.setAttribute("sessionTime", ttime);
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/orders/list");
		if (!Tools.notEmpty(ttime)) {
			if (null != ty) {
				if (null == ty || ty == -1) {
					List<ProductOrder> orders = productOrderMapper.getPageListBy(or);
					List<ProductOrder> list = orderService.getList(orders);
					mv.addObject("orResultlist", list);
					mv.addObject("obj", or);
					mv.addObject("ty", ty);
					mv.addObject("ttime", session.getAttribute("sessionTime"));
				} else if (20 == ty) {
					or.setOrderState(ty);
					List<ProductOrder> list = productOrderMapper.getPageListBy(or);
					List<ProductOrder> list1 = OrderUtils.getList(list, productOrderMapper, userAddressMapper);
					mv.addObject("orResultlist", list1);
					mv.addObject("obj", or);
					mv.addObject("ty", ty);
					mv.addObject("ttime", session.getAttribute("sessionTime"));
				} else if (30 == ty) {
					or.setOrderState(ty);
					List<ProductOrder> list = productOrderMapper.getPageListBy(or);
					mv.addObject("ty", ty);
					List<ProductOrder> list1 = OrderUtils.getList(list, productOrderMapper, userAddressMapper);
					mv.addObject("orResultlist", list1);
					mv.addObject("ty", ty);
					mv.addObject("obj", or);
					mv.addObject("ttime", session.getAttribute("sessionTime"));
				} else if (40 == ty) {
					or.setOrderState(ty);
					List<ProductOrder> list = productOrderMapper.getPageListBy(or);
					List<ProductOrder> list1 = OrderUtils.getList(list, productOrderMapper, userAddressMapper);
					mv.addObject("orResultlist", list1);
					mv.addObject("ty", ty);
					mv.addObject("obj", or);
					mv.addObject("ttime", session.getAttribute("sessionTime"));
				} else if (50 == ty) {
					// or.setOrderState(ty);
					List<ProductOrder> list = productOrderMapper.getPageListBySt(or);
					// List<ProductOrder> list1 = OrderUtils.getList(list,
					// productOrderMapper, userAddressMapper);
					mv.addObject("orResultlist", list);
					mv.addObject("ty", ty);
					mv.addObject("obj", or);
					mv.addObject("ttime", session.getAttribute("sessionTime"));
				} else if (10 == ty) {
					or.setOrderState(ty);
					List<ProductOrder> list = productOrderMapper.getPageListBy(or);
					List<ProductOrder> list1 = OrderUtils.getList(list, productOrderMapper, userAddressMapper);
					mv.addObject("orResultlist", list1);
					mv.addObject("obj", or);
					mv.addObject("ty", ty);
					mv.addObject("ttime", session.getAttribute("sessionTime"));
				}
			} else {
				List<ProductOrder> lists = productOrderMapper.getPageListBy(or);
				for (int i = 0; i < lists.size(); i++) {
					UserAddress uAddress = userAddressMapper.selectByPrimaryKey(lists.get(i).getAddressId());
					lists.get(i).setUserAddress(uAddress);
				}
				mv.addObject("orderid", orderNumber);
				mv.addObject("orResultlist", lists);
				mv.addObject("obj", or);
				mv.addObject("ttime", ttime);
			}
		} else if (Tools.notEmpty(ttime)) {
			try {
				String start = ttime.split("~")[0].trim();
				String end = ttime.split("~")[1].trim();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = dateFormat.parse(start);
				Date date2 = dateFormat.parse(end);
				or.setStartTime(date1);
				or.setEndTime(date2);
				if (null != ty) {
					if (null == ty || ty == -1) {
						List<ProductOrder> list = productOrderMapper.getPageListByTimeKey(or);
						for (int i = 0; i < list.size(); i++) {
							UserAddress uAddress = userAddressMapper.selectByPrimaryKey(list.get(i).getAddressId());
							list.get(i).setUserAddress(uAddress);
						}
						mv.addObject("ttime", ttime);
						mv.addObject("obj", or);
						mv.addObject("orResultlist", list);
						mv.addObject("ty", ty);
						mv.addObject("ttime", session.getAttribute("sessionTime"));
					} else if (20 == ty) {
						or.setOrderState(ty);
						List<ProductOrder> list = productOrderMapper.getPageListByOrderSt(or);
						List<ProductOrder> list1 = OrderUtils.getList(list, productOrderMapper, userAddressMapper);
						mv.addObject("orResultlist", list1);
						mv.addObject("obj", or);
						mv.addObject("ty", ty);
						mv.addObject("ttime", session.getAttribute("sessionTime"));
					} else if (30 == ty) {
						or.setOrderState(ty);
						List<ProductOrder> list = productOrderMapper.getPageListByOrderSt(or);
						mv.addObject("ty", ty);
						List<ProductOrder> list1 = OrderUtils.getList(list, productOrderMapper, userAddressMapper);
						mv.addObject("orResultlist", list1);
						mv.addObject("ty", ty);
						mv.addObject("obj", or);
						mv.addObject("ttime", session.getAttribute("sessionTime"));
					} else if (40 == ty) {
						or.setOrderState(ty);
						List<ProductOrder> list = productOrderMapper.getPageListByOrderSt(or);
						List<ProductOrder> list1 = OrderUtils.getList(list, productOrderMapper, userAddressMapper);
						mv.addObject("orResultlist", list1);
						mv.addObject("ty", ty);
						mv.addObject("obj", or);
						mv.addObject("ttime", session.getAttribute("sessionTime"));
					} else if (50 == ty) {
						// or.setOrderState(ty);
						List<ProductOrder> list = productOrderMapper.getPageListBySt(or);
						// List<ProductOrder> list =
						// productOrderMapper.getPageListByOrderSt(or);
						// List<ProductOrder> list1 = OrderUtils.getList(list,
						// productOrderMapper, userAddressMapper);
						mv.addObject("orResultlist", list);
						mv.addObject("ty", ty);
						mv.addObject("obj", or);
						mv.addObject("ttime", session.getAttribute("sessionTime"));
					} else if (10 == ty) {
						or.setOrderState(ty);
						List<ProductOrder> list = productOrderMapper.getPageListByOrderSt(or);
						List<ProductOrder> list1 = OrderUtils.getList(list, productOrderMapper, userAddressMapper);
						mv.addObject("orResultlist", list1);
						mv.addObject("obj", or);
						mv.addObject("ty", ty);
						mv.addObject("ttime", session.getAttribute("sessionTime"));
					}
				} else {
					List<ProductOrder> list = productOrderMapper.getPageListByTimeKey(or);
					for (int i = 0; i < list.size(); i++) {
						UserAddress uAddress = userAddressMapper.selectByPrimaryKey(list.get(i).getAddressId());
						list.get(i).setUserAddress(uAddress);
					}
					mv.addObject("ttime", ttime);
					mv.addObject("obj", or);
					mv.addObject("orResultlist", list);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mv;
	}

	/**
	 *
	 * @描述: 根据状态不同查询订单 @作者:lzj @日期:2017-3-30 @修改内容 @参数： @param @return
	 *      ModelAndView @throws
	 */

	@RequestMapping("/orderlist")
	public ModelAndView ordersList(HttpServletRequest request, ProductOrder productOrder, Integer orderid, String ttime,
			Integer ty, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/orders/list");
		if (null == ty || ty == -1) {
			List<ProductOrder> orders = productOrderMapper.getPageListBy(productOrder);
			List<ProductOrder> list = orderService.getList(orders);
			mv.addObject("orResultlist", list);
			mv.addObject("obj", productOrder);
			mv.addObject("ty", ty);
			mv.addObject("ttime", session.getAttribute("sessionTime"));
		} else if (20 == ty) {
			productOrder.setOrderState(ty);
			List<ProductOrder> list = productOrderMapper.getPageListBy(productOrder);
			List<ProductOrder> list1 = OrderUtils.getList(list, productOrderMapper, userAddressMapper);
			mv.addObject("orResultlist", list1);
			mv.addObject("obj", productOrder);
			mv.addObject("ty", ty);
			mv.addObject("ttime", session.getAttribute("sessionTime"));
		} else if (30 == ty) {
			productOrder.setOrderState(ty);
			List<ProductOrder> list = productOrderMapper.getPageListBy(productOrder);
			mv.addObject("ty", ty);
			List<ProductOrder> list1 = OrderUtils.getList(list, productOrderMapper, userAddressMapper);
			mv.addObject("orResultlist", list1);
			mv.addObject("ty", ty);
			mv.addObject("obj", productOrder);
			mv.addObject("ttime", session.getAttribute("sessionTime"));
		} else if (40 == ty) {
			productOrder.setOrderState(ty);
			List<ProductOrder> list = productOrderMapper.getPageListBy(productOrder);
			List<ProductOrder> list1 = OrderUtils.getList(list, productOrderMapper, userAddressMapper);
			mv.addObject("orResultlist", list1);
			mv.addObject("ty", ty);
			mv.addObject("obj", productOrder);
			mv.addObject("ttime", session.getAttribute("sessionTime"));
		} else if (50 == ty) {
			List<ProductOrder> list = productOrderMapper.getPageListBySt(productOrder);
			// productOrder.setOrderState(ty);
			// List<ProductOrder> list =
			// productOrderMapper.getPageListBy(productOrder);
			// List<ProductOrder> list1 = OrderUtils.getList(list,
			// productOrderMapper, userAddressMapper);
			mv.addObject("orResultlist", list);
			mv.addObject("ty", ty);
			mv.addObject("obj", productOrder);
			mv.addObject("ttime", session.getAttribute("sessionTime"));
		} else if (10 == ty) {
			productOrder.setOrderState(ty);
			List<ProductOrder> list = productOrderMapper.getPageListBy(productOrder);
			List<ProductOrder> list1 = OrderUtils.getList(list, productOrderMapper, userAddressMapper);
			mv.addObject("orResultlist", list1);
			mv.addObject("obj", productOrder);
			mv.addObject("ty", ty);
			mv.addObject("ttime", session.getAttribute("sessionTime"));
		}
		return mv;
	}

	@RequestMapping("/toexcel")
	public ModelAndView toexcelbatch(HttpSession session, String ttime, HttpServletRequest request) {
		ProductOrder productOrder = new ProductOrder();
		String start = ttime.split("~")[0].trim();
		String end = ttime.split("~")[1].trim();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1;
		Date date2;
		try {
			date1 = dateFormat.parse(start);
			date2 = dateFormat.parse(end);
			productOrder.setStartTime(date1);
			productOrder.setEndTime(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<ProductOrder> orResultlist = productOrderMapper.getListCountOrder(productOrder);
		for (ProductOrder productOrder2 : orResultlist) {
			GoodsOrder goodsOrder = new GoodsOrder();
			goodsOrder.setOrderId(productOrder2.getId());
			GoodsOrder gOrder = goodsOrderMapper.selectBy(goodsOrder);
			if (gOrder != null) {
				ManageProducts mProducts = manageProductsMapper.selectByPrimaryKey(gOrder.getGoodsId());
				UserAddress uAddress = userAddressMapper.selectByPrimaryKey(productOrder2.getAddressId());
				productOrder2.setManageProducts(mProducts);
				productOrder2.setUserAddress(uAddress);
				productOrder2.setCount(gOrder.getGoodsCount());
			}
		}
		List<String> headtitle = new ArrayList<String>();// 标题
		Map<String, Object> dataMap = new HashMap<String, Object>();
		headtitle.add("订单编号");
		headtitle.add("商品名称");
		headtitle.add("商品单价");
		headtitle.add("商品数量");
		headtitle.add("商品总价格");
		headtitle.add("下单时间");
		headtitle.add("收货人姓名");
		headtitle.add("联系方式");
		headtitle.add("买家留言");
		dataMap.put("titles", headtitle);
		OrderSendExcelView erv = null;
		ModelAndView mv = null;
		erv = new OrderSendExcelView();
		dataMap.put("orResultlist", orResultlist);
		mv = new ModelAndView(erv, dataMap);
		return mv;
	}

	/**
	 *
	 * @描述: 显示订单 @作者:lzj @日期:2017-4-25 @修改内容 @参数： @param @参数： @return
	 *      ModelAndView @throws
	 */
	@RequestMapping("/stateChage")
	public ModelAndView stateChage(Integer id) {
		ModelAndView mv = new ModelAndView();
		ProductOrder productOrder = new ProductOrder();
		productOrder.setOrderState(40);
		productOrder.setGoodsTime(new Date());
		productOrder.setId(id);
		productOrderMapper.updateByPrimaryKey(productOrder);
		mv.setViewName("redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/orders/list");
		return mv;
	}

}

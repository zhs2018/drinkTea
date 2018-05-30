
package com.wxsoft.drinkTea.platform.weixin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.framework.utils.Tools;
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.order.mapper.GoodsOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.GoodsOrder;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.weixin.mapper.UserAddressMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.OrderModel;
import com.wxsoft.drinkTea.platform.weixin.model.UserAddress;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

@Controller
@RequestMapping()
public class FreeReceivedController extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserAddressMapper userAddressMapper;

	@Autowired
	private ManageProductsMapper manageProductsMapper;

	@Autowired
	private ProductOrderMapper productOrderMapper;

	@Autowired
	private GoodsOrderMapper goodsOrderMapper;

	@Autowired
	private WebUserMapper webUserMapper;

	@RequestMapping("/freeRecevice")
	public ModelAndView freeRecevice(HttpSession session, Integer id, Integer proId) {
		ModelAndView mv = new ModelAndView();
		WebUser wUser = (WebUser) session.getAttribute("user");
		if (wUser != null) {
			if (null != id) {
				UserAddress ua = new UserAddress();
				ua.setUserId(wUser.getId());
				ua.setValiable(1);
				UserAddress u = userAddressMapper.selectByPrimaryKey(id);
				// 商品
				ManageProducts mp = manageProductsMapper.selectByPrimaryKey(proId);
				mv.addObject("obj", u);
				mv.addObject("mp", mp);
				session.setAttribute("mpId", mp.getId());
				mv.setViewName("weixin/freeRecevice");
			} else {
				UserAddress us = new UserAddress();
				us.setUserId(wUser.getId());
				us.setStatus(2);
				us.setValiable(1);
				UserAddress userAddress = userAddressMapper.selectBy(us);
				if (userAddress != null) {
					ManageProducts mp = manageProductsMapper.selectByPrimaryKey(proId);
					mv.addObject("obj", userAddress);
					mv.addObject("mp", mp);
					session.setAttribute("mpId", mp.getId());
					mv.setViewName("weixin/freeRecevice");
				} else {
					ManageProducts mp = manageProductsMapper.selectByPrimaryKey(proId);
					session.setAttribute("mpId", mp.getId());
					mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/myAddress");
				}
			}
		} else {
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/index");
		}
		return mv;
	}

	@RequestMapping("/frees")
	public ModelAndView recevice(OrderModel orderModel, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		WebUser user = (WebUser) session.getAttribute("user");
		if (user != null) {
			if (Tools.notEmpty(user.getName()) && Tools.notEmpty(user.getPhone())) {
				ProductOrder os = new ProductOrder();
				os.setUserId(user.getId());
				os.setTabs(1);
				List<ProductOrder> pList = productOrderMapper.getListBy(os);
				if (pList != null && pList.size() > 0) {
					Date orderTime = null;
					for (ProductOrder productOrder : pList) {
						Date time = productOrder.getOrderTime();
						if (orderTime != null) {
							if (orderTime.before(time)) {
								orderTime = time;
							}
						} else {
							orderTime = time;
						}
					}
					Long t = orderTime.getTime() + (long) (30 * ((1000 * 60 * 60 * 24) + 0.5));
					if (new Date().getTime() > t) {
						ProductOrder order = new ProductOrder();
						order.setOrderNumber(new Date().getTime());
						order.setOrderTime(new Date());
						order.setAddressId(orderModel.getAddressId());
						order.setUserId(user.getId());
						order.setPrice(0.0);
						order.setOrderState(1);
						order.setTabs(1);// 免費領取
						int m = productOrderMapper.insert(order);
						order = productOrderMapper.selectBy(order);
						GoodsOrder goodsOrder = new GoodsOrder();
						goodsOrder.setOrderId(order.getId());// 订单id
						goodsOrder.setGoodsCount(1);
						goodsOrder.setGoodsId(orderModel.getGoodId());
						goodsOrderMapper.insert(goodsOrder);
						ManageProducts manageProducts = manageProductsMapper.selectByPrimaryKey(orderModel.getGoodId());
						if (manageProducts.getSaleCount() != null) {
							manageProducts.setSaleCount(manageProducts.getSaleCount() + 1);
						} else {
							manageProducts.setSaleCount(1);
						}
						manageProductsMapper.updateByPrimaryKey(manageProducts);
						if (user.getFreeRec() != null) {
							user.setFreeRecevice(user.getFreeRec() + 1);
						} else {
							user.setFreeRecevice(1);
						}
						webUserMapper.updateByPrimaryKey(user);
						mv.addObject("me", "恭喜");
						ManageProducts mProducts = new ManageProducts();
						mProducts.setSign(3);
						List<ManageProducts> mList = manageProductsMapper.getListBy(mProducts);
						mv.addObject("ManageProduct", mList);
						mv.setViewName("weixin/classification");

					} else {
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
						String st = sdf.format(new Date(t));
						System.out.println(sdf.format(new Date(t)));
						mv.addObject("message", st);
						ManageProducts mProducts = new ManageProducts();
						mProducts.setSign(3);
						List<ManageProducts> mList = manageProductsMapper.getListBy(mProducts);
						mv.addObject("ManageProduct", mList);
						mv.setViewName("weixin/classification");
					}

				} else {
					ProductOrder order = new ProductOrder();
					order.setOrderNumber(new Date().getTime());
					order.setOrderTime(new Date());
					order.setAddressId(orderModel.getAddressId());
					order.setUserId(user.getId());
					order.setPrice(0.0);
					order.setOrderState(1); // 免費領取
					order.setTabs(1);
					int m = productOrderMapper.insert(order);
					order = productOrderMapper.selectBy(order);
					GoodsOrder goodsOrder = new GoodsOrder();
					goodsOrder.setOrderId(order.getId());// 订单id
					goodsOrder.setGoodsCount(1);
					goodsOrder.setGoodsId(orderModel.getGoodId());
					goodsOrderMapper.insert(goodsOrder);
					ManageProducts manageProducts = manageProductsMapper.selectByPrimaryKey(orderModel.getGoodId());
					if (manageProducts.getSaleCount() != null) {
						manageProducts.setSaleCount(manageProducts.getSaleCount() + 1);
					} else {
						manageProducts.setSaleCount(1);
					}
					manageProductsMapper.updateByPrimaryKey(manageProducts);
					if (user.getFreeRec() != null) {
						user.setFreeRecevice(user.getFreeRec() + 1);
					} else {
						user.setFreeRecevice(1);
					}
					webUserMapper.updateByPrimaryKey(user);
					mv.addObject("me", "恭喜");
					ManageProducts mProducts = new ManageProducts();
					mProducts.setSign(3);
					List<ManageProducts> mList = manageProductsMapper.getListBy(mProducts);
					mv.addObject("ManageProduct", mList);
					mv.setViewName("weixin/classification");
				}
			} else {
				mv.setViewName("weixin/updateUser");
			}
		} else {
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/index");
		}
		return mv;
	}

}

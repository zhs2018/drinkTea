package com.wxsoft.drinkTea.platform.weixin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.allinpay.syb.lib.SybConstants;
import com.allinpay.syb.lib.SybPayService;
import com.allinpay.syb.lib.SybUtil;
import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.framework.utils.MoneyUtils;
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
import com.wxsoft.drinkTea.platform.weixin.service.PayService;

@Controller
public class TeaPayController extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserAddressMapper userAddressMapper;
	@Autowired
	private ManageProductsMapper manageProductsMapper;
	@Autowired
	private PayService payService;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private WebUserMapper webUserMapper;
	@Autowired
	private GoodsOrderMapper goodsOrderMapper;

	private SybPayService sybPayService = new SybPayService();

	@RequestMapping("/confirmOrder")
	public ModelAndView jumpToConfirmOrder(HttpSession session, Integer id, Integer proId) {
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
				Double reM = wUser.getRestMoney();
				mv.addObject("money", reM);
				mv.addObject("obj", u);
				mv.addObject("mp", mp);
				session.setAttribute("mpId", mp.getId());
				mv.setViewName("weixin/confirmOrder");
			} else {
				UserAddress us = new UserAddress();
				us.setUserId(wUser.getId());
				us.setStatus(2);
				us.setValiable(1);
				UserAddress userAddress = userAddressMapper.selectBy(us);
				if (userAddress != null) {
					ManageProducts mp = manageProductsMapper.selectByPrimaryKey(proId);
					Double remM = wUser.getRestMoney();
					mv.addObject("money", remM);
					mv.addObject("obj", userAddress);
					mv.addObject("mp", mp);
					session.setAttribute("mpId", mp.getId());
					mv.setViewName("weixin/confirmOrder");
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

	/**
	 *
	 * @author lzj 保存提交表单
	 * @修改信息 判断各种情况...并做出响应
	 * @修改人 yzy
	 *
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject SaveOrder(OrderModel orderModel, HttpServletResponse response, HttpSession session, Integer id) {
		JSONObject map = new JSONObject();
		logger.info("orderModel  info ：" + orderModel.toString());
		WebUser user = (WebUser) session.getAttribute("user");
		// 存订单
		if (user != null) {
			if (id == null && (orderModel == null || orderModel.getPrice() == null
					|| "".equals(orderModel.getPrice().trim()) || Float.valueOf(orderModel.getPrice()) == 0)) {
				map.put("result", "1");
				map.put("message", "订单金额核对错误！");
				return map;
			}
			if (id != null) {
				ProductOrder pOrder = productOrderMapper.selectByPrimaryKey(id);
				if (pOrder != null && pOrder.getId() != null && pOrder.getPrice() != null
						&& pOrder.getPrice().floatValue() != 0) {
					GoodsOrder goodsOrder = new GoodsOrder();
					goodsOrder.setOrderId(pOrder.getId());
					GoodsOrder gOrder = goodsOrderMapper.selectBy(goodsOrder);
					if (gOrder == null) {
						map.put("result", "1");
						map.put("message", "该订单已失效！");
						return map;
					}
					ManageProducts product = manageProductsMapper.selectByPrimaryKey(gOrder.getGoodsId());
					if (product != null) {
						Map<String, String> result = null;
						try {
							result = sybPayService.pay(MoneyUtils.getPriceByPenny(pOrder.getPrice()),
									String.valueOf(pOrder.getOrderNumber()), "W02", product.getName(),
									pOrder.getCustomerMessage(), user.getOpenId(),
									DomainProperties.DOMAIN_WWW + "/allinpaySuccess", "no_credit");
							map.put("payInfo", result.get("payinfo"));
							pOrder.setTlorderNumbers(result.get("trxid"));
							pOrder.setWxorderNumbers(result.get("chnltrxid"));
							logger.info("通联返回的支付信息：" + result.get("payinfo"));
							logger.info("获取信息" + result.get("chnltrxid"));
							logger.info("获取信息" + result.get("trxid"));
							map.put("result", "0");
							map.put("id", pOrder.getId() + "");
							map.put("money", MoneyUtils.doubleToString(pOrder.getPrice()));
							map.put("message", "提交订单成功！");
						} catch (Exception e) {
							logger.debug("通联支付错误信息", result);
							map.put("result", "1");
							map.put("message", "提交订单失败！");
							e.printStackTrace();
						}
					} else {
						map.put("result", "1");
						map.put("message", "提交订单失败！");
					}
				} else {
					map.put("result", "1");
					map.put("message", "请核对订单编号！");
				}
			} else {
				ProductOrder order = payService.saveOrder(user, orderModel);
				if (order != null && order.getId() != null) {
					ManageProducts product = manageProductsMapper.selectByPrimaryKey(orderModel.getGoodId());
					if (product != null) {
						Map<String, String> result = null;
						try {
							result = sybPayService.pay(MoneyUtils.getPriceByPenny(order.getPrice()),
									String.valueOf(order.getOrderNumber()), "W02", product.getName(),
									order.getCustomerMessage(), user.getOpenId(),
									DomainProperties.DOMAIN_WWW + "/allinpaySuccess", "no_credit");
							map.put("payInfo", result.get("payinfo"));
							order.setTlorderNumbers(result.get("trxid"));
							order.setWxorderNumbers(result.get("chnltrxid"));
							logger.info("通联返回的支付信息：" + result.get("payinfo"));
							logger.info("获取信息" + result.get("chnltrxid"));
							logger.info("获取信息" + result.get("trxid"));
							map.put("result", "0");
							map.put("id", order.getId() + "");
							map.put("money", MoneyUtils.doubleToString(order.getPrice()));
							map.put("message", "提交订单成功！");
						} catch (Exception e) {
							logger.debug("通联支付错误信息", result);
							map.put("result", "1");
							map.put("message", "提交订单失败！");
							e.printStackTrace();
						}
					} else {
						map.put("result", "1");
						map.put("message", "提交订单失败！");
					}
				} else {
					map.put("result", "1");
					map.put("message", "提交订单失败！");
				}
			}
		} else {
			map.put("result", "1");
			map.put("message", "请登录！");
		}
		return map;
	}

	@RequestMapping("allinpaySuccess")
	public void checkPayInfo(HttpServletRequest request, HttpServletResponse response) {
		logger.info("后台接收到通联消息");
		try {
			request.setCharacterEncoding("gbk");// 通知传输的编码为GBK
			response.setCharacterEncoding("gbk");
			SortedMap<String, String> params = getParams(request);// 动态遍历获取所有收到的参数,此步非常关键,因为收银宝以后可能会加字段,动态获取可以兼容
			boolean isSign = SybUtil.validSign((TreeMap<String, String>) params, SybConstants.SYB_APPKEY);// 接受到推送通知,首先验签
			// trxstatus
			if (params.get("trxstatus") != null) {
				String trxstatu = params.get("trxstatus");
				String orderNum = params.get("cusorderid");
				ProductOrder order = new ProductOrder();
				order.setOrderNumber(Long.valueOf(orderNum));
				ProductOrder pOrder = productOrderMapper.selectByOrderNum(order);
				if ("0000".equals(trxstatu)) {// 交易成功
					try {
						if (pOrder != null) {
							pOrder.setApplyTime(new Date());
							GoodsOrder goodsOrder = new GoodsOrder();
							goodsOrder.setOrderId(pOrder.getId());
							GoodsOrder gOrder = goodsOrderMapper.selectBy(goodsOrder);
							ManageProducts manageProducts = manageProductsMapper
									.selectByPrimaryKey(gOrder.getGoodsId());
							if (manageProducts.getSaleCount() != null) {
								manageProducts.setSaleCount(manageProducts.getSaleCount() + 1);
							} else {
								manageProducts.setSaleCount(1);
							}
							if (manageProducts.getRestGoods() != null) {
								manageProducts.setRestGoods(manageProducts.getRestGoods() - 1);
							} else {
								manageProducts.setRestGoods(0);
							}
							// 这服务（事务）
							payService.updateProduct(manageProducts);
							pOrder.setOrderState(30);
							payService.updateOrder(pOrder);

							WebUser webUser = webUserMapper.selectByPrimaryKey(pOrder.getUserId());
							if (webUser != null) {
								WebUser user = new WebUser();
								user.setId(webUser.getId());
								if (webUser.getMoney() != null) {
									user.setMoney(webUser.getMoney() + new Float(pOrder.getPrice()));
								} else {
									user.setMoney(new Float(pOrder.getPrice()));
								}
								if (manageProducts.getTab() == 3) {
									if (webUser.getAnswerCount() != null) {
										user.setAnswerCount(webUser.getAnswerCount() + 15 * gOrder.getGoodsCount());
									} else {
										user.setAnswerCount(15 * gOrder.getGoodsCount());
									}
								}
								if (manageProducts.getSign() == 2) {
									if (webUser.getDrawCount() != null) {
										user.setAnswerCount(webUser.getDrawCount() + gOrder.getGoodsCount());
									} else {
										user.setDrawCount(gOrder.getGoodsCount());
									}
								}

								webUserMapper.updateByPrimaryKey(user);
							} else {
								logger.info("系统出现徐错误！");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					try {
						if (pOrder != null) {
							if (pOrder.getRedMoney() != null) {
								Double useRedMoney = pOrder.getRedMoney();
								WebUser webUser = webUserMapper.selectByPrimaryKey(pOrder.getUserId());
								if (webUser.getRestMoney() != null) {
									Double Money = webUser.getRestMoney();
									Double restMoney = Money + useRedMoney;
									webUser.setRestMoney(restMoney);
								} else {
									webUser.setRestMoney(useRedMoney);
								}
								// 服务（事务）
								payService.updateWebUser(webUser);
							} else {
								logger.info("没有使用红包");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			logger.info("验签结果:" + isSign);
			logger.info("所有结果" + params.get("cusorderid"));

			// 验签完毕进行业务处理

		} catch (Exception e) {// 处理异常
			e.printStackTrace();
		} finally {// 收到通知,返回success
			try {
				response.getOutputStream().write("success".getBytes());
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 动态遍历获取所有收到的参数,此步非常关键,因为收银宝以后可能会加字段,动态获取可以兼容由于收银宝加字段而引起的签名异常
	 *
	 * @param request
	 * @return
	 */
	private SortedMap<String, String> getParams(HttpServletRequest request) {
		SortedMap<String, String> map = new TreeMap<String, String>();
		Map reqMap = request.getParameterMap();
		for (Object key : reqMap.keySet()) {
			String value = ((String[]) reqMap.get(key))[0];
			System.out.println(key + ":" + value);
			map.put(key.toString(), value);
		}
		return map;
	}

}

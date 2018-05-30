package com.wxsoft.drinkTea.platform.weixin.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	private GoodsOrderMapper  goodsOrderMapper;

	private SybPayService sybPayService = new SybPayService();

	@RequestMapping("/confirmOrder")
	public ModelAndView jumpToConfirmOrder(HttpSession session, Integer id,Integer proId) {
		ModelAndView mv = new ModelAndView();
		WebUser wUser = (WebUser) session.getAttribute("user");
		if (wUser != null) {
		   if (null != id) {
			      UserAddress ua = new UserAddress();
			         ua.setUserId(wUser.getId());
			         ua.setValiable(1);
				        UserAddress u = userAddressMapper.selectByPrimaryKey(id);
				        //商品
				        ManageProducts mp = manageProductsMapper.selectByPrimaryKey(proId);
				          Double reM =  wUser.getRestMoney();
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
					Double remM =  wUser.getRestMoney();
					System.out.println("输出一下ID："+wUser.getId());
				   System.out.println("红包钱数"+remM);
					mv.addObject("money", remM);
					mv.addObject("obj", userAddress);
					mv.addObject("mp", mp);
					session.setAttribute("mpId", mp.getId());
					mv.setViewName("weixin/confirmOrder");
				      } else {
				    	  ManageProducts mp = manageProductsMapper.selectByPrimaryKey(proId);
				    	  session.setAttribute("mpId", mp.getId());
					mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/myAddress");
				}
			}
		} else {
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/index");
		}
		return mv;
	}

	/**
	 *
	 * @author lzj 保存提交表单
	 *
	 */
	@RequestMapping("/save")
	public void SaveOrder(OrderModel orderModel, HttpServletResponse response,HttpSession session) {
		Map<String, String> map = new HashMap<>();
		WebUser user = (WebUser) session.getAttribute("user");
		//存订单
		if(user != null){
			ProductOrder order = payService.saveOrder(user,orderModel);
			if(order != null && order.getId()!=null){
				ManageProducts product = manageProductsMapper.selectByPrimaryKey(orderModel.getGoodId());
				if(product != null){
					Map<String,String> result = null;
					order.setPrice(0.01);
					try {
						result = sybPayService.pay(MoneyUtils.getPriceByPenny(order.getPrice()), String.valueOf(order.getOrderNumber()), "W02", product.getName(), order.getCustomerMessage(), user.getOpenId(), DomainProperties.DOMAIN_WWW+"/allinpaySuccess", "no_credit");
						map.put("payInfo", result.get("payinfo"));
						order.setTlorderNumbers(result.get("trxid"));
						order.setWxorderNumbers(result.get("chnltrxid"));
						logger.info("通联返回的支付信息："+result.get("payinfo"));
						logger.info("获取信息"+result.get("chnltrxid"));
						logger.info("获取信息"+result.get("trxid"));
						map.put("result", "0");
						map.put("id", order.getId()+"");
						map.put("money", MoneyUtils.doubleToString(order.getPrice()));
					    map.put("message", "提交订单成功！");
					} catch (Exception e) {
						logger.debug("通联支付错误信息",result);
						map.put("result", "1");
					    map.put("message", "提交订单失败！");
						e.printStackTrace();
					}
				}else{
					map.put("result", "1");
				    map.put("message", "提交订单失败！");
				}
			}else{
				map.put("result", "1");
			    map.put("message", "提交订单失败！");
			}
		}else{
			map.put("result", "1");
			map.put("message", "请登录！");
		}
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("allinpaySuccess")
	public void checkPayInfo(HttpServletRequest request,HttpServletResponse response){
		logger.info("后台接收到通联消息");
		try {
			request.setCharacterEncoding("gbk");//通知传输的编码为GBK
			response.setCharacterEncoding("gbk");
			SortedMap<String, String> params = getParams(request);//动态遍历获取所有收到的参数,此步非常关键,因为收银宝以后可能会加字段,动态获取可以兼容
			boolean isSign = SybUtil.validSign((TreeMap<String, String>)params, SybConstants.SYB_APPKEY);// 接受到推送通知,首先验签
//			trxstatus
			if(params.get("trxstatus")!=null){
				String trxstatu = params.get("trxstatus");
				String orderNum= params.get("cusorderid");
				System.out.println("输出一下orderNum："+orderNum);
//				 Long orderNums = Long.getLong(orderNum);
			  ProductOrder order = new ProductOrder();
			  order.setOrderNumber(Long.valueOf(orderNum));
				 System.out.println("输出一下order："+order.getOrderNumber());
			    ProductOrder pOrder = productOrderMapper.selectByOrderNum(order);
			    if("0000".equals(trxstatu)){//交易成功
					try {
						System.out.println("这里执行到啦。。。");
						if(pOrder!=null){
							GoodsOrder goodsOrder = new GoodsOrder();
							goodsOrder.setOrderId(pOrder.getId());
					GoodsOrder gOrder=goodsOrderMapper.selectBy(goodsOrder);
					ManageProducts manageProducts =	manageProductsMapper.selectByPrimaryKey(gOrder.getGoodsId());
					 if(manageProducts.getSaleCount()!=null){
						 System.out.println("输出一下结果看一下");
						int count = manageProducts.getSaleCount();
						int counts = count+1;
						manageProducts.setSaleCount(counts);
						int flag = manageProductsMapper.updateByPrimaryKey(manageProducts);
						System.out.println("输出一下flag"+flag);
					 }else{
						    manageProducts.setSaleCount(1);
						    System.out.println("输出一下结果看一下la、、、、、、、");
							int flag= manageProductsMapper.updateByPrimaryKey(manageProducts);
							System.out.println("输出一下flag"+flag);
					   }
							pOrder.setOrderState(30);//待收货
							System.out.println("。。。。。。。。。输出一下结果看一下la、、、、、、、");
							payService.updateOrder(pOrder);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}else{
					try{
					 if(pOrder!=null){
						 if(pOrder.getRedMoney()!=null) {
							 Double useRedMoney = pOrder.getRedMoney();
							 WebUser webUser =  webUserMapper.selectByPrimaryKey(pOrder.getUserId());
							 if(webUser.getRestMoney()!=null){
								 Double Money = webUser.getRestMoney();
								 Double restMoney = Money + useRedMoney;
								 webUser.setRestMoney(restMoney);
		                        int flag = webUserMapper.updateByPrimaryKey(webUser);
		                        System.out.println("输出一下：flag"+flag);
							 }else{
								 webUser.setRestMoney(useRedMoney);
								 int flag = webUserMapper.updateByPrimaryKey(webUser);
								 System.out.println("输出一下：flag"+flag);
							 }
						 }else{
							 	 logger.info("不执行任何操作");
						 }
						}
					   }catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			logger.info("验签结果:"+isSign);
			logger.info("所有结果"+params.get("cusorderid"));

			//验签完毕进行业务处理

		} catch (Exception e) {//处理异常
			e.printStackTrace();
		}
		finally{//收到通知,返回success
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
	 * @param request
	 * @return
	 */
	private SortedMap<String, String> getParams(HttpServletRequest request){
		SortedMap<String, String> map = new TreeMap<String, String>();
		Map reqMap = request.getParameterMap();
		for(Object key:reqMap.keySet()){
			String value = ((String[])reqMap.get(key))[0];
			System.out.println(key+";"+value);
			map.put(key.toString(),value);
		}
		return map;
	}


}

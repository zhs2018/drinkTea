package com.wxsoft.drinkTea.platform.weixin.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.properties.DomainProperties;
import com.wxsoft.drinkTea.platform.weixin.mapper.UserAddressMapper;
import com.wxsoft.drinkTea.platform.weixin.model.UserAddress;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 *
 * @author yzy 立即购买
 *
 */
@RequestMapping("/shop")
@Controller
public class ConfirmOrder extends BaseAction {
	@Autowired
	private UserAddressMapper userAddressMapper;



	/**
	 * @author lzj 跳转到确认订单页面
	 *
	 * id:订单id
	 * proId：商品id
	 */
	private static final long serialVersionUID = 1L;
//	@RequestMapping("/confirmOrder")
//	public ModelAndView jumpToConfirmOrder(HttpSession session, Integer id,Integer proId) {
//		ModelAndView mv = new ModelAndView();
//		WebUser wUser = (WebUser) session.getAttribute("user");
//		if (wUser != null) {
//		   if (null != id) {
//			      UserAddress ua = new UserAddress();
//			         ua.setUserId(wUser.getId());
//			         ua.setValiable(1);
//				        UserAddress u = userAddressMapper.selectByPrimaryKey(id);
//				        //商品
//				        ManageProducts mp = manageProductsMapper.selectByPrimaryKey(proId);
//				          Double reM =  wUser.getRestMoney();
//				          mv.addObject("money", reM);
//                          mv.addObject("obj", u);
//				          mv.addObject("mp", mp);
//				          session.setAttribute("mpId", mp.getId());
//				          mv.setViewName("weixin/confirmOrder");
//			 } else {
//				       UserAddress us = new UserAddress();
//				          us.setUserId(wUser.getId());
//				             us.setStatus(2);
//				             us.setValiable(1);
//				          UserAddress userAddress = userAddressMapper.selectBy(us);
//				              if (userAddress != null) {
//					ManageProducts mp = manageProductsMapper.selectByPrimaryKey(proId);
//					Double remM =  wUser.getRestMoney();
//					 System.out.println("红包钱数"+remM);
//					mv.addObject("money", remM);
//					mv.addObject("obj", userAddress);
//					mv.addObject("mp", mp);
//					session.setAttribute("mpId", mp.getId());
//					mv.setViewName("weixin/confirmOrder");
//				      } else {
//				    	  ManageProducts mp = manageProductsMapper.selectByPrimaryKey(proId);
//				    	  session.setAttribute("mpId", mp.getId());
//					mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/myAddress");
//				}
//			}
//		} else {
//			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/index");
//		}
//		return mv;
//	}

	/**
	 *
	 * @author lzj 跳转到地址列表
	 *
	 */
	@RequestMapping("/myAddress")
	public ModelAndView getMyAddress(HttpSession session,Integer mpID) {
		ModelAndView mv = new ModelAndView();
		WebUser webUser = (WebUser) session.getAttribute("user");
		if (webUser != null) {
			UserAddress userAddress = new UserAddress();
			userAddress.setUserId(webUser.getId());
			userAddress.setValiable(1);
			List<UserAddress> userAddress1 = userAddressMapper.getListBy(userAddress);
			if (null == userAddress1 || userAddress1.size() <= 0) {
				mv.setViewName("weixin/addAddress");
			} else {
				mv.addObject("objList", userAddress1);
				mv.addObject("mpId",session.getAttribute("mp"));
				mv.setViewName("weixin/myAddress");
			}
		} else {
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/index");
		}
		return mv;
	}

	/**
	 *
	 * @author lzj 编辑地址
	 *
	 */
	@RequestMapping("/edit_Address/{id}")
	public ModelAndView getEditAddress(@PathVariable Integer id, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		WebUser webUser = (WebUser) session.getAttribute("user");
		if (webUser != null) {
			UserAddress userAddress = new UserAddress();
			userAddress.setId(id);
			UserAddress userAdd = userAddressMapper.selectBy(userAddress);
			if(null!=userAdd){
				mv.addObject("obj", userAdd);
				mv.setViewName("weixin/addAddress");
			}else{
				mv.setViewName("weixin/myAddress");
			}
		} else {
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/shop/index");
		}
		return mv;
	}

	/**
	 *
	 * @author lzj 刪除地址
	 *
	 */
	@RequestMapping("/del_Address")
	public void getDelAddress(Integer id, HttpServletResponse response, HttpSession session) {
		Map<String, String> map = new HashMap<>();
		if(null!=id){
			UserAddress userAddres = new UserAddress();
			userAddres.setId(id);
			userAddres.setValiable(2);
			int n = userAddressMapper.updateByPrimaryKey(userAddres);
			System.out.println(n+"_+_+_+");
			if (n == 1) {
				map.put("status", "1");
			}else{
				map.put("satus","0");
			}
		}else{
                map.put("status","0");
		}
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 *
	 * @author lzj 跳转到添加新地址
	 *
	 */
	@RequestMapping("/add_Address")
	public ModelAndView getAddAddress() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("weixin/addAddress");
		return mv;
	}


	/**
	 *
	 * @author lzj 保存添加的新地址
	 *
	 */
	@RequestMapping("/save_Address")
	public void SaveAddress(UserAddress userAddress,HttpServletResponse response, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		int flag = 0;
		WebUser wUser = (WebUser) session.getAttribute("user");
		if (null == userAddress.getId() && wUser != null) {
			if (1 == userAddress.getStatus()) {
				userAddress.setStatus(1);
				userAddress.setValiable(1);
				userAddress.setUserId(wUser.getId());
				flag = userAddressMapper.insert(userAddress);
			} else {
				UserAddress uA = new UserAddress();
				uA.setUserId(wUser.getId());
				List<UserAddress> list = userAddressMapper.getListBy(uA);
				if(list.size()<=0){
					userAddress.setUserId(wUser.getId());
					userAddress.setValiable(1);
					userAddress.setStatus(2);
					flag = userAddressMapper.insert(userAddress);
				}else{
					for (UserAddress userAddress2 : list) {
						userAddress2.setStatus(1);
						userAddressMapper.update(userAddress2);
				}
					userAddress.setValiable(1);
					userAddress.setStatus(2);
					userAddress.setUserId(wUser.getId());
				flag = userAddressMapper.insert(userAddress);
				}
			}
		} else if (null != userAddress.getId() && wUser != null) {
			if (1 == userAddress.getStatus()) {
				userAddress.setStatus(1);
				flag = userAddressMapper.updateByPrimaryKey(userAddress);
			} else {
				UserAddress uA = new UserAddress();
				uA.setUserId(wUser.getId());
				List<UserAddress> list = userAddressMapper.getListBy(uA);
				for (UserAddress userAddress2 : list) {
						userAddress2.setStatus(1);
						userAddressMapper.update(userAddress2);
				}
				userAddress.setStatus(2);
				flag = userAddressMapper.updateByPrimaryKey(userAddress);
			}

		}
		if (flag == 1) {
			map.put("status", "1");
		} else {
			map.put("status", "0");
			map.put("Message", "添加新地址失败");
		}
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @author lzj 在myOrder中设置默认地址
	 *
	 */
	@RequestMapping("/change_Address")
	public void getChange(Integer userId, Integer id,Integer mpID, HttpServletResponse response,HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		if(null!=userId){
			UserAddress u = new UserAddress();
			u.setUserId(userId);
			List<UserAddress> list = userAddressMapper.getListBy(u);
			for (UserAddress userAddress : list) {
				if(userAddress.getStatus()==2){
					userAddress.setStatus(1);
				}
				userAddressMapper.updateByPrimaryKey(userAddress);
			    }
			    if(null!=id){
				UserAddress us = new UserAddress();
				us.setId(id);
				us.setStatus(2);
				userAddressMapper.updateByPrimaryKey(us);
			    }else{
				map.put("status","0");
			   }
		}else{
			map.put("status","0");
		}

		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	/**
//	 *
//	 * @author lzj 保存提交表单
//	 *
//	 */
//	@RequestMapping("/save")
//	public void SaveOrder(OrderModel orderModel, HttpServletResponse response,HttpSession session) {
//		Map<String, String> map = new HashMap<>();
//		WebUser user = (WebUser) session.getAttribute("user");
//		//存订单
//		if(user != null){
//			ProductOrder order = payService.saveOrder(user,orderModel);
//			if(order != null && order.getId()!=null){
//				ManageProducts product = manageProductsMapper.selectByPrimaryKey(orderModel.getGoodId());
//				if(product != null){
//					Map<String,String> result = null;
//					order.setPrice(0.01);
//					try {
//						result = sybPayService.pay(MoneyUtils.getPriceByPenny(order.getPrice()), String.valueOf(order.getOrderNumber()), "W02", product.getName(), order.getCustomerMessage(), user.getOpenId(), DomainProperties.DOMAIN_WWW+"shop/allinpaySuccess", "no_credit");
//						map.put("payInfo", result.get("payinfo"));
//						logger.info("通联返回的支付信息："+result.get("payinfo"));
//						map.put("result", "0");
//						map.put("id", order.getId()+"");
//						map.put("money", MoneyUtils.doubleToString(order.getPrice()));
//					    map.put("message", "提交订单成功！");
//					} catch (Exception e) {
//						logger.debug("通联支付错误信息",result);
//						map.put("result", "1");
//					    map.put("message", "提交订单失败！");
//						e.printStackTrace();
//					}
//				}else{
//					map.put("result", "1");
//				    map.put("message", "提交订单失败！");
//				}
//			}else{
//				map.put("result", "1");
//			    map.put("message", "提交订单失败！");
//			}
//		}else{
//			map.put("result", "1");
//			map.put("message", "请登录！");
//		}
//		try {
//			responseAjax(map, response);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@RequestMapping("allinpaySuccess")
//	public void checkPayInfo(HttpServletRequest request,HttpServletResponse response){
//		System.out.println("接收到通知");
//		try {
//			request.setCharacterEncoding("gbk");//通知传输的编码为GBK
//			response.setCharacterEncoding("gbk");
//			SortedMap<String, String> params = getParams(request);//动态遍历获取所有收到的参数,此步非常关键,因为收银宝以后可能会加字段,动态获取可以兼容
//			boolean isSign = SybUtil.validSign((TreeMap<String, String>)params, SybConstants.SYB_APPKEY);// 接受到推送通知,首先验签
//			System.out.println("验签结果:"+isSign);
//			//验签完毕进行业务处理
//			System.out.println("可以处理自己的业务逻辑了");
//
//		} catch (Exception e) {//处理异常
//			e.printStackTrace();
//		}
//		finally{//收到通知,返回success
//			try {
//				response.getOutputStream().write("success".getBytes());
//				response.flushBuffer();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * 动态遍历获取所有收到的参数,此步非常关键,因为收银宝以后可能会加字段,动态获取可以兼容由于收银宝加字段而引起的签名异常
//	 * @param request
//	 * @return
//	 */
//	private SortedMap<String, String> getParams(HttpServletRequest request){
//		SortedMap<String, String> map = new TreeMap<String, String>();
//		Map reqMap = request.getParameterMap();
//		for(Object key:reqMap.keySet()){
//			String value = ((String[])reqMap.get(key))[0];
//			System.out.println(key+";"+value);
//			map.put(key.toString(),value);
//		}
//		return map;
//	}
}

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
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.weixin.mapper.UserAddressMapper;
import com.wxsoft.drinkTea.platform.weixin.model.UserAddress;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 *
 *
 */
@RequestMapping("")
@Controller
public class ConfirmOrder extends BaseAction {
	@Autowired
	private UserAddressMapper userAddressMapper;
	@Autowired
	private ManageProductsMapper manageProductsMapper;

	/**
	 * @author lzj 跳转到确认订单页面
	 *
	 *         id:订单id proId：商品id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @author lzj 跳转到地址列表
	 *
	 */
	@RequestMapping("/myAddress")
	public ModelAndView getMyAddress(HttpSession session, Integer mpID) {
		if (mpID == null) {
			mpID = (Integer) session.getAttribute("mpId");
		}
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
				ManageProducts manageProduct = manageProductsMapper.selectByPrimaryKey(mpID);
				if (manageProduct != null) {
					mv.addObject("objList", userAddress1);
					mv.addObject("mpId", session.getAttribute("mp"));
					mv.addObject("mpTab", manageProduct.getTab());
					mv.setViewName("weixin/myAddress");
				} else {
					mv.addObject("objList", userAddress1);
					mv.setViewName("weixin/myAddress");
				}
			}
		} else {
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/index");
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
			if (null != userAdd) {
				mv.addObject("obj", userAdd);
				mv.setViewName("weixin/addAddress");
			} else {
				mv.setViewName("weixin/myAddress");
			}
		} else {
			mv.setViewName("redirect:" + DomainProperties.DOMAIN_WWW + "/index");
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
		if (null != id) {
			UserAddress userAddres = new UserAddress();
			userAddres.setId(id);
			userAddres.setValiable(2);
			int n = userAddressMapper.updateByPrimaryKey(userAddres);
			System.out.println(n + "_+_+_+");
			if (n == 1) {
				map.put("status", "1");
			} else {
				map.put("satus", "0");
			}
		} else {
			map.put("status", "0");
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
	public void SaveAddress(UserAddress userAddress, HttpServletResponse response, HttpSession session) {
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
				if (list.size() <= 0) {
					userAddress.setUserId(wUser.getId());
					userAddress.setValiable(1);
					userAddress.setStatus(2);
					flag = userAddressMapper.insert(userAddress);
				} else {
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
	public void getChange(Integer userId, Integer id, Integer mpID, HttpServletResponse response, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		if (null != userId) {
			UserAddress u = new UserAddress();
			u.setUserId(userId);
			List<UserAddress> list = userAddressMapper.getListBy(u);
			for (UserAddress userAddress : list) {
				if (userAddress.getStatus() == 2) {
					userAddress.setStatus(1);
				}
				userAddressMapper.updateByPrimaryKey(userAddress);
			}
			if (null != id) {
				UserAddress us = new UserAddress();
				us.setId(id);
				us.setStatus(2);
				userAddressMapper.updateByPrimaryKey(us);
			} else {
				map.put("status", "0");
			}
		} else {
			map.put("status", "0");
		}

		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

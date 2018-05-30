package com.wxsoft.drinkTea.platform.system.order.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.config.SCHOOLMGR;
import com.wxsoft.drinkTea.framework.utils.Tools;
import com.wxsoft.drinkTea.platform.system.login.mapper.SysUserMapper;
import com.wxsoft.drinkTea.platform.system.login.model.SysUser;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.system.order.service.IOrderService;

/**
 * @author lzj @时间：2017-4-24 16：40 员工查看订单
 */
@Controller
@RequestMapping(SCHOOLMGR.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/order")
public class sysUserOrder extends BaseAction {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private ProductOrderMapper productOrderMapper;

	/**
	 *
	 * @描述: 显示订单 @作者:lzj @日期:2017-4-24 @修改内容 @参数： @param @参数： @return
	 *      ModelAndView @throws
	 */
	@RequestMapping("/list")
	public ModelAndView orderList(SysUser sysUse,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		SysUser sysUser = (SysUser) session.getAttribute("sessionUser");
		sysUse.setId(sysUser.getId());
		sysUse = sysUserMapper.selectBy(sysUse);
		if (null != sysUser) {
			List<SysUser> pList = sysUserMapper.getPageListBysql(sysUse);
//			for (SysUser sysUser2 : pList) {
//				System.out.println(sysUser2.getOrderTime() + "aijsdvb aiusdvh ");
//			}
			mv.addObject("obj", sysUse);
			mv.addObject("orResultlist", pList);
		}
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/order/list");
		return mv;
	}

	/**
	 *
	 * @描述: 显示订单 @作者:lzj @日期:2017-4-25 @修改内容 @参数： @param @参数： @return
	 *      ModelAndView @throws
	 */
	@RequestMapping("/OrdersList")
	public ModelAndView ordersList(HttpSession session, SysUser sysUser) {
		ModelAndView mv = new ModelAndView();
		session.setAttribute("sessionTime", sysUser.getTtime());
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/order/list");
		SysUser sysUser2 = (SysUser) session.getAttribute("sessionUser");
		if (null != sysUser2) {
			sysUser.setId(sysUser2.getId());
			if (!Tools.notEmpty(sysUser.getTtime())) {
				if (null != sysUser.getTy()) {
					if (null == sysUser.getTy() || sysUser.getTy() == -1) {
						List<SysUser> pList = sysUserMapper.getPageListBysql(sysUser);
						mv.addObject("obj", sysUser);
						mv.addObject("orResultlist", pList);
						mv.addObject("ty", sysUser.getTy());
						mv.addObject("ttime", session.getAttribute("sessionTime"));
					} else if (20 == sysUser.getTy()) {
						sysUser.setOrderState(sysUser.getTy());
						List<SysUser> list = sysUserMapper.getPageListByOrderState(sysUser);
						mv.addObject("orResultlist", list);
						mv.addObject("obj", sysUser);
						mv.addObject("ty", sysUser.getTy());
						mv.addObject("ttime", session.getAttribute("sessionTime"));
					} else if (30 == sysUser.getTy()) {
						sysUser.setOrderState(sysUser.getTy());
						List<SysUser> list = sysUserMapper.getPageListByOrderState(sysUser);
						mv.addObject("orResultlist", list);
						mv.addObject("obj", sysUser);
						mv.addObject("ty", sysUser.getTy());
						mv.addObject("ttime", session.getAttribute("sessionTime"));
					} else if (40 == sysUser.getTy()) {
						sysUser.setOrderState(sysUser.getTy());
						List<SysUser> list = sysUserMapper.getPageListByOrderState(sysUser);
						mv.addObject("orResultlist", list);
						mv.addObject("obj", sysUser);
						mv.addObject("ty", sysUser.getTy());
						mv.addObject("ttime", session.getAttribute("sessionTime"));
					} else if (50 == sysUser.getTy()) {
						sysUser.setOrderState(sysUser.getTy());
						List<SysUser> list = sysUserMapper.getPageListByOrderState(sysUser);
						mv.addObject("orResultlist", list);
						mv.addObject("obj", sysUser);
						mv.addObject("ty", sysUser.getTy());
						mv.addObject("ttime", session.getAttribute("sessionTime"));
					} else if (10 == sysUser.getTy()) {
						sysUser.setOrderState(sysUser.getTy());
						List<SysUser> list = sysUserMapper.getPageListByOrderState(sysUser);
						mv.addObject("orResultlist", list);
						mv.addObject("obj", sysUser);
						mv.addObject("ty", sysUser.getTy());
						mv.addObject("ttime", session.getAttribute("sessionTime"));
					}
				} else {
					List<SysUser> pList = sysUserMapper.getPageListBysql(sysUser);
					mv.addObject("obj", sysUser);
					mv.addObject("orResultlist", pList);
					mv.addObject("ttime", session.getAttribute("sessionTime"));
				}
			} else if (Tools.notEmpty(sysUser.getTtime())) {
				try {
					String start = sysUser.getTtime().split("~")[0].trim();
					String end = sysUser.getTtime().split("~")[1].trim();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date1 = dateFormat.parse(start);
					Date date2 = dateFormat.parse(end);
					sysUser.setStartTime(date1);
					sysUser.setEndTime(date2);
					if (null != sysUser.getTy()) {
						if (null == sysUser.getTy() || sysUser.getTy() == -1) {
							List<SysUser> list = sysUserMapper.getPageListByTime(sysUser);
							mv.addObject("ttime", sysUser.getTtime());
							mv.addObject("obj", sysUser);
							mv.addObject("orResultlist", list);
							mv.addObject("ty", sysUser.getTy());
							mv.addObject("ttime", session.getAttribute("sessionTime"));
						} else if (20 == sysUser.getTy()) {
							sysUser.setOrderState(sysUser.getTy());
							List<SysUser> list = sysUserMapper.getPageListByTimeOrState(sysUser);
							mv.addObject("ttime", sysUser.getTtime());
							mv.addObject("obj", sysUser);
							mv.addObject("orResultlist", list);
							mv.addObject("ty", sysUser.getTy());
							mv.addObject("ttime", session.getAttribute("sessionTime"));
						} else if (30 == sysUser.getTy()) {
							sysUser.setOrderState(sysUser.getTy());
							List<SysUser> list = sysUserMapper.getPageListByTimeOrState(sysUser);
							mv.addObject("ttime", sysUser.getTtime());
							mv.addObject("obj", sysUser);
							mv.addObject("orResultlist", list);
							mv.addObject("ty", sysUser.getTy());
							mv.addObject("ttime", session.getAttribute("sessionTime"));
						} else if (40 == sysUser.getTy()) {
							sysUser.setOrderState(sysUser.getTy());
							List<SysUser> list = sysUserMapper.getPageListByTimeOrState(sysUser);
							mv.addObject("ttime", sysUser.getTtime());
							mv.addObject("obj", sysUser);
							mv.addObject("orResultlist", list);
							mv.addObject("ty", sysUser.getTy());
							mv.addObject("ttime", session.getAttribute("sessionTime"));
						} else if (50 == sysUser.getTy()) {
							sysUser.setOrderState(sysUser.getTy());
							List<SysUser> list = sysUserMapper.getPageListByTimeOrState(sysUser);
							mv.addObject("ttime", sysUser.getTtime());
							mv.addObject("obj", sysUser);
							mv.addObject("orResultlist", list);
							mv.addObject("ty", sysUser.getTy());
							mv.addObject("ttime", session.getAttribute("sessionTime"));
						} else if (10 == sysUser.getTy()) {
							sysUser.setOrderState(sysUser.getTy());
							List<SysUser> list = sysUserMapper.getPageListByTimeOrState(sysUser);
							mv.addObject("ttime", sysUser.getTtime());
							mv.addObject("obj", sysUser);
							mv.addObject("orResultlist", list);
							mv.addObject("ty", sysUser.getTy());
							mv.addObject("ttime", session.getAttribute("sessionTime"));
						}
					} else {
						List<SysUser> list = sysUserMapper.getPageListByTime(sysUser);
						mv.addObject("ttime", sysUser.getTtime());
						mv.addObject("obj", sysUser);
						mv.addObject("orResultlist", list);
						mv.addObject("ty", sysUser.getTy());
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		}

		return mv;
	}

	/**
	 *
	 * @描述: 根据状态不同查询订单 @作者:lzj @日期:2017-4-25 @修改内容 @参数： @param @return
	 *      ModelAndView @throws
	 */

	@RequestMapping("/orderlist")
	public ModelAndView ordersList(HttpServletRequest request, Integer orderid, String ttime, Integer ty,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/order/list");
		SysUser sysUser = (SysUser) session.getAttribute("sessionUser");
		if (null == ty || ty == -1) {
			List<SysUser> pList = sysUserMapper.getPageListBysql(sysUser);
			mv.addObject("obj", sysUser);
			mv.addObject("orResultlist", pList);
			mv.addObject("ttime", session.getAttribute("sessionTime"));
		} else if (20 == ty) {
			sysUser.setOrderState(ty);
			List<SysUser> list = sysUserMapper.getPageListByOrderState(sysUser);
			mv.addObject("orResultlist", list);
			mv.addObject("obj", sysUser);
			mv.addObject("ty", ty);
			mv.addObject("ttime", session.getAttribute("sessionTime"));
		} else if (30 == ty) {
			sysUser.setOrderState(ty);
			List<SysUser> list = sysUserMapper.getPageListByOrderState(sysUser);
			mv.addObject("orResultlist", list);
			mv.addObject("obj", sysUser);
			mv.addObject("ty", ty);
			mv.addObject("ttime", session.getAttribute("sessionTime"));
		} else if (40 == ty) {
			sysUser.setOrderState(ty);
			List<SysUser> list = sysUserMapper.getPageListByOrderState(sysUser);
			mv.addObject("orResultlist", list);
			mv.addObject("obj", sysUser);
			mv.addObject("ty", ty);
			mv.addObject("ttime", session.getAttribute("sessionTime"));
		} else if (50 == ty) {
			sysUser.setOrderState(ty);
			List<SysUser> list = sysUserMapper.getPageListByOrderState(sysUser);
			mv.addObject("orResultlist", list);
			mv.addObject("obj", sysUser);
			mv.addObject("ty", ty);
			mv.addObject("ttime", session.getAttribute("sessionTime"));
		} else if (10 == ty) {
			sysUser.setOrderState(ty);
			List<SysUser> list = sysUserMapper.getPageListByOrderState(sysUser);
			mv.addObject("orResultlist", list);
			mv.addObject("obj", sysUser);
			mv.addObject("ty", ty);
			mv.addObject("ttime", session.getAttribute("sessionTime"));
		}

		return mv;
	}

	/**
	 * @描述: 订单详细信息 @作者: lzj @日期:2017-4-25 @参数： @param @参数： @return
	 *      ModelAndView @throws
	 */

	@RequestMapping("/ordersinfo/{orderid}")
	public ModelAndView ordersInfo(HttpServletRequest request, @PathVariable Integer orderid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		ProductOrder list = orderService.getListMesses(orderid);
		mv.setViewName(SCHOOLMGR.SYSTEM_PATH_ADMIN + "/order/info");
		mv.addObject("orders", list);
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
		mv.setViewName("redirect:" + SCHOOLMGR.SYSTEM_PATH_ADMIN + "/order/list");
		return mv;
	}
}

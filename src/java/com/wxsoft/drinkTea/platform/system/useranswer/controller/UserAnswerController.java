package com.wxsoft.drinkTea.platform.system.useranswer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.framework.utils.DateUtils;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.system.teaKing.mapper.RedMoneyMapper;
import com.wxsoft.drinkTea.platform.system.teaKing.model.RedMoney;
import com.wxsoft.drinkTea.platform.system.useranswer.service.UserAnswerService;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 * 用户答题情况查询
 *
 * @创建时间 2017.4.20
 *
 */
@Controller
@RequestMapping("system/userAnswer")
public class UserAnswerController extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private WebUserMapper webUserMapper;
	@Autowired
	private ProductOrderMapper productOrderMappper;
	@Autowired
	private RedMoneyMapper redMoneyMapper;

	/**
	 * 用户扫码答题情况
	 *
	 * @return
	 */
	@RequestMapping("userSubject")
	public ModelAndView userSubjectList(WebUser webUser, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/userAnswer/userSubject");
		List<WebUser> webUsers = webUserMapper.getPageListByQrecord(webUser);
		modelAndView.addObject("user", webUser);
		modelAndView.addObject("users", webUsers);
		return modelAndView;
	}

	/**
	 * 题王争霸答题情况
	 *
	 * @return WebTemporary webT,
	 */
	@RequestMapping("userKing")
	public ModelAndView userKingList(WebUser web, String ttime) {
		ModelAndView modelAndView = new ModelAndView();
		RedMoney rm = new RedMoney();
		RedMoney redM = redMoneyMapper.selectBy(rm);
		// 查询打过题目的人
		if (ttime != null && !"".equals(ttime)) {
			String[] sTime = ttime.split("~");
			web.setBeginTime(DateUtils.getDateFormat(DateUtils.parseSimpleFormatDate(sTime[0]), "yyyyMMdd"));
			web.setEndTime(DateUtils.getDateFormat(DateUtils.parseSimpleFormatDate(sTime[1]), "yyyyMMdd"));
			List<WebUser> webUsers = webUserMapper.getPageListByKingAnswerAndTime(web);
			for (WebUser webUser2 : webUsers) {
				ProductOrder productOrder = new ProductOrder();
				productOrder.setUserId(webUser2.getId());
				List<ProductOrder> list = productOrderMappper.getListBy(productOrder);
				if (list != null && list.size() > 1) {
					webUser2.setMember("会员");
					webUser2.setMo(webUser2.getRightCount() * redM.getMoneyNum());
				} else if (list.size() == 1) {
					for (ProductOrder productOrder2 : list) {
						if (productOrder2.getPrice() != null && productOrder2.getPrice() != 0) {
							webUser2.setMember("会员");
							webUser2.setMo(webUser2.getRightCount() * redM.getMoneyNum());
						} else {
							webUser2.setMember("非会员");
							webUser2.setMo(webUser2.getRightCount() * redM.getMoneyNums());
						}
					}
				} else {
					webUser2.setMember("非会员");
					webUser2.setMo(webUser2.getRightCount() * redM.getMoneyNums());
				}
			}
			modelAndView.setViewName("system/userAnswer/userKing");
			modelAndView.addObject("page", web);
			modelAndView.addObject("users", webUsers);
			modelAndView.addObject("sTime", ttime);
		} else {

			List<WebUser> webUsers = webUserMapper.getPageListByKingAnswer(web);
			for (WebUser webUser2 : webUsers) {
				ProductOrder productOrder = new ProductOrder();
				productOrder.setUserId(webUser2.getId());
				List<ProductOrder> list = productOrderMappper.getListBy(productOrder);
				if (list != null && !list.isEmpty()) {
					webUser2.setMember("会员");
					webUser2.setMo(webUser2.getRightCount() * redM.getMoneyNum());
				} else {
					webUser2.setMember("非会员");
					webUser2.setMo(webUser2.getRightCount() * redM.getMoneyNums());
				}
			}
			modelAndView.setViewName("system/userAnswer/userKing");
			modelAndView.addObject("page", web);
			modelAndView.addObject("users", webUsers);
		}
		return modelAndView;
	}
	
	
}

package com.wxsoft.drinkTea.platform.weixin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.drinkTea.framework.base.BaseAction;
import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.order.mapper.GoodsOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.GoodsOrder;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.weixin.mapper.RefundMessageMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.RefundMessage;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;
/**
 *
 * @author lzj
 * @描述：申请退款
 * @时间：2017-4-10
 *
 */
@Controller
@RequestMapping()
public class RefundController extends BaseAction {
	@Autowired
	private ManageProductsMapper manageProductsMapper;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private GoodsOrderMapper goodsOrderMapper;
//	@Autowired
//	private WebUserMapper webUserMapper;
	@Autowired
	private RefundMessageMapper refundMessageMapper;
	@Autowired
	private WebUserMapper webUserMapper;
	/**
	 *序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *@author lzj
	 *@描述：跳转到申请原因
	 *@2017-4-10
	 */
	@RequestMapping("/refund_Money/{id}")
	public ModelAndView getRefund(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("OrderId", id);
		mv.setViewName("weixin/refund");
		return mv;

	}
	/**
	 *@author lzj
	 *@描述：从个人中心跳转到退款详情
	 *@时间：2017-4-10
	 */
	//急需要测
	@RequestMapping("/refunds")
	public ModelAndView getRefund(HttpSession Session) {
		ModelAndView mv = new ModelAndView();
	WebUser webUser = (WebUser) Session.getAttribute("user");
		//WebUser webUser = webUserMapper.selectByPrimaryKey(129);
		if (null != webUser) {
			RefundMessage refundMessage = new RefundMessage();
			refundMessage.setUserId(webUser.getId());
		     List<RefundMessage> rfm = refundMessageMapper.getListBy(refundMessage);
		     List<RefundMessage> result = new ArrayList<>();
			for (RefundMessage refundMessage2 : rfm) {
				RefundMessage rMessage = new RefundMessage();
			  ProductOrder po = productOrderMapper.selectByPrimaryKey(refundMessage2.getOrderId());
			  GoodsOrder goodsOrder = new GoodsOrder();
				goodsOrder.setOrderId(po.getId());
				GoodsOrder gOrder = goodsOrderMapper.selectBy(goodsOrder);
				logger.info(gOrder.getGoodsId()+"  --");
			   ManageProducts manageProducts = manageProductsMapper.selectByPrimaryKey(gOrder.getGoodsId());
			   rMessage.setManageProducts(manageProducts);
			   rMessage.setProductOrder(po);
			   rMessage.setRefundMoney(refundMessage2.getRefundMoney());
			   rMessage.setRefTime(refundMessage2.getRefTime());
			   rMessage.setGoodsOrder(gOrder);
			   result.add(rMessage);
			}
			 mv.addObject("refund", result);
			 mv.setViewName("weixin/refundDet");
		}else{
			mv.setViewName("");
		}

		return mv;
	}

	/**
	 *@author lzj
	 *@描述：从退款申请原因跳转到退款详情
	 *@时间:2017-4-10
	 */
	@RequestMapping("/refund_reason")
	public ModelAndView RefundReason(RefundMessage refundMessage,HttpSession session) {
		ModelAndView mv = new ModelAndView();
        WebUser webUser = (WebUser) session.getAttribute("user");
		if(null!=webUser){
			refundMessage.setUserId(webUser.getId());
			refundMessage.setRefTime(new Date());
		 int flag = refundMessageMapper.insert(refundMessage);
		 if(flag==1){
		  ProductOrder po = productOrderMapper.selectByPrimaryKey(refundMessage.getOrderId());
			  po.setOrderState(60);
			productOrderMapper.updateByPrimaryKey(po);
			GoodsOrder go = new GoodsOrder();
			go.setOrderId(po.getId());
			refundMessage.setUserId(goodsOrderMapper.selectBy(go).getGoodsId());
			ManageProducts mp = manageProductsMapper.selectByPrimaryKey(goodsOrderMapper.selectBy(go).getGoodsId());
			mv.addObject("manage", mp);
			mv.addObject("order", po);
			mv.addObject("refund", refundMessage);
			mv.setViewName("weixin/refundDetails");
		 }
		}

		return mv;
	}

}

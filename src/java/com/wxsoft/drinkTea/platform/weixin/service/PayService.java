package com.wxsoft.drinkTea.platform.weixin.service;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.logging.log4j.core.impl.LogEventFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.order.mapper.GoodsOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.GoodsOrder;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.system.recommendfriend.mapper.RecommendFriendMapper;
import com.wxsoft.drinkTea.platform.system.recommendfriend.model.RecommendFriend;
import com.wxsoft.drinkTea.platform.weixin.mapper.RedEnvelopeMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.OrderModel;
import com.wxsoft.drinkTea.platform.weixin.model.RedEnvelope;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 * 支付
 * @author Administrator
 *
 */
@Service
public class PayService {

	@Autowired
	private WebUserMapper webUserMapper;
	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private GoodsOrderMapper goodsOrderMapper;
	@Autowired
	private ManageProductsMapper manageProductsMapper;
	@Autowired
	private RecommendFriendMapper recommendFriendMapper;
	@Autowired
	private RedEnvelopeMapper redEnvelopeMapper;
    //logger的获取方式
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Transactional(rollbackFor = Exception.class)
	public ProductOrder saveOrder(WebUser user, OrderModel orderModel) {

		Double redPrice = (user.getRestMoney()==null ? 0.0 : user.getRestMoney())-(orderModel.getRedMoney() == null ? 0.0:orderModel.getRedMoney());
		if(redPrice>=0){
	ManageProducts manageProducts =	manageProductsMapper.selectByPrimaryKey(orderModel.getGoodId());
	   BigDecimal pPrice=manageProducts.getNowPrice();
	   Double pManage= pPrice.doubleValue()*orderModel.getCount()-(orderModel.getRedMoney() == null ? 0.0:orderModel.getRedMoney());
//	   System.out.println("现在价格：："+pManage);
//	   System.out.println("订单价格：："+Double.valueOf(orderModel.getPrice()));
//	   System.out.println("比较：："+(pManage.floatValue()==Float.valueOf(orderModel.getPrice())));
    if(pManage.floatValue()==Float.valueOf(orderModel.getPrice())){
    	ProductOrder order = new ProductOrder();
		order.setOrderNumber(new Date().getTime());
		order.setOrderTime(new Date());
		order.setAddressId(orderModel.getAddressId());
		order.setCustomerMessage(orderModel.getCustomerMessage());
		order.setUserId(user.getId());
		order.setOrderState(20); // 待付款
		order.setRedMoney(orderModel.getRedMoney() == null ? 0.0:orderModel.getRedMoney());
		order.setPrice(Double.valueOf(orderModel.getPrice()));
		System.out.println("输出红包的钱数"+ orderModel.getRedMoney());
		int m= productOrderMapper.insert(order);
		System.out.println("输出结果为："+m);
		order = productOrderMapper.selectBy(order);
		GoodsOrder goodsOrder = new GoodsOrder();
		goodsOrder.setOrderId(order.getId());//订单id
		goodsOrder.setGoodsCount(orderModel.getCount());
		goodsOrder.setGoodsId(orderModel.getGoodId());
		goodsOrderMapper.insert(goodsOrder);
			if(orderModel.getRedMoney() != null){
	       	    double reMOney = user.getRestMoney();
	                System.out.println(reMOney);
	              double rMoney= orderModel.getRedMoney();
				    System.out.println(rMoney);
				 double restMoney = reMOney-rMoney;
				    System.out.println(restMoney);
				 user.setRestMoney(restMoney);
				 WebUser user2 = new WebUser();
				 user2.setId(user.getId());
				 user2.setRestMoney(user.getRestMoney());
				 webUserMapper.updateByPrimaryKey(user2);
	        }
			return order;
    }
		}
         return null;

	}
	@Transactional
	public void updateOrder(ProductOrder order) {
	 int flag = productOrderMapper.updateByPrimaryKey(order);
	 if(flag==1){
	   System.out.println("输出更新状态成功");
	 }else{
        System.out.println("执行事务回滚");

	 }


	}
	@Transactional
	public void updateProduct(ManageProducts manageProduct) {
	 int flag = manageProductsMapper.updateByPrimaryKey(manageProduct);
	 if(flag==1){
	   System.out.println("输出更新状态成功");
	 }else{
        System.out.println("执行事务回滚");

	 }
	}

	@Transactional
	public void updateWebUser(WebUser webUser) {
	 int flag = webUserMapper.updateByPrimaryKey(webUser);
	 if(flag==1){
	   System.out.println("输出更新状态成功");
	 }else{
        System.out.println("执行事务回滚");

	 }
	}

	@Transactional
	public void  bonus(ManageProducts manageProducts,ProductOrder pOrder,GoodsOrder gOrder){
		// 获取商品的单价，获取客户购买数量，获取客户支付订单的金额
		// 利润=客户支付订单总金额-商品单价*购买数量
		// 利润的百分值多少分给上家？
		// Double price = pOrder.getPrice();//订单总金额
		// Integer count = gOrder.getGoodsCount();//客户购买数量
		// Double mPrice =
		// (manageProducts.getNowPrice()).doubleValue();//商品单价
		// Double pPrice = pPrice - (mPrice*count);//商品的利润
		double p = 0.0;
		double pPrice = 0.0;
		if (manageProducts.getSign() == 1) {// 公益商品

		} else if (manageProducts.getSign() == 2) {// 抽奖商品
			Double price = pOrder.getPrice();// 订单总金额
			Integer count = gOrder.getGoodsCount();// 客户购买数量
			Double mPrice = (manageProducts.getNowPrice()).doubleValue();// 商品单价
			pPrice = price - (mPrice * count);// 商品的利润
			p = (0.20) * (0.15);
		} else {// 普通商品
			pPrice = pOrder.getPrice();// 订单总金额
			p = (0.20) * (0.15);
		}

		RecommendFriend recommendFriend = new RecommendFriend();
		recommendFriend.setRecommendedId(pOrder.getUserId());
		RecommendFriend rFriend = recommendFriendMapper.selectBy(recommendFriend);
		if (rFriend != null) {
			RedEnvelope redEnvelope = new RedEnvelope();
			redEnvelope.setUserId(rFriend.getRecommendId());
			redEnvelope.setType(1);// 1表示分红所得红包
			redEnvelope.setMoney(pPrice * p);
			logger.warn("shuchu L:" + pPrice * p);
			redEnvelope.setAnswerTime(new Date());
			redEnvelopeMapper.insert(redEnvelope);
			WebUser webUser = webUserMapper.selectByPrimaryKey(rFriend.getRecommendId());
			if (webUser != null) {
				WebUser user = new WebUser();
				user.setId(webUser.getId());
				if (webUser.getMoney() != null) {
					user.setMoney(webUser.getMoney() + new Float(pPrice * p));
				} else {
					user.setMoney(new Float(pPrice * p));
				}
				if (webUser.getAnswerCount() != null) {
					user.setAnswerCount(webUser.getAnswerCount() + 1);
				} else {
					user.setAnswerCount(1);
				}
				webUserMapper.updateByPrimaryKey(user);
			} else {
				logger.info("系统出现徐错误！");
			}
			logger.info("表示有1个上家，如何分红");
			RecommendFriend recommend1 = new RecommendFriend();
			recommend1.setRecommendedId(rFriend.getRecommendId());
			RecommendFriend rFriend1 = recommendFriendMapper.selectBy(recommendFriend);
			if (rFriend1 != null) {
				RedEnvelope redEnvelope1 = new RedEnvelope();
				redEnvelope1.setUserId(rFriend.getRecommendId());
				redEnvelope1.setType(1);// 1表示分红所得红包
				redEnvelope1.setMoney(pPrice * p);
				redEnvelope1.setAnswerTime(new Date());
				redEnvelopeMapper.insert(redEnvelope1);
				WebUser webUser1 = webUserMapper.selectByPrimaryKey(rFriend.getRecommendId());
				if (webUser1 != null) {
					WebUser user = new WebUser();
					user.setId(webUser1.getId());
					if (webUser1.getMoney() != null) {
						user.setMoney(webUser1.getMoney() + new Float(pPrice * p));
					} else {
						user.setMoney(new Float(pPrice * p));
					}
					if (webUser1.getAnswerCount() != null) {
						user.setAnswerCount(webUser1.getAnswerCount() + 1);
					} else {
						user.setAnswerCount(1);
					}
					webUserMapper.updateByPrimaryKey(user);
				} else {
					logger.info("系统出现徐错误！");
				}
				logger.info("表示有2个上家，如何分红");
				RecommendFriend recommend2 = new RecommendFriend();
				recommend2.setRecommendedId(rFriend1.getRecommendId());
				RecommendFriend rFriend2 = recommendFriendMapper.selectBy(recommend2);
				if (rFriend2 != null) {
					RedEnvelope redEnvelope2 = new RedEnvelope();
					redEnvelope2.setUserId(rFriend.getRecommendId());
					redEnvelope2.setType(1);// 1表示分红所得红包
					redEnvelope2.setMoney(pPrice * p);
					redEnvelope2.setAnswerTime(new Date());
					redEnvelopeMapper.insert(redEnvelope2);
					WebUser webUser2 = webUserMapper.selectByPrimaryKey(rFriend.getRecommendId());
					if (webUser2 != null) {
						WebUser user = new WebUser();
						user.setId(webUser2.getId());
						if (webUser2.getMoney() != null) {
							user.setMoney(webUser2.getMoney() + new Float(pPrice * p));
						} else {
							user.setMoney(new Float(pPrice * p));
						}
						if (webUser2.getAnswerCount() != null) {
							user.setAnswerCount(webUser2.getAnswerCount() + 1);
						} else {
							user.setAnswerCount(1);
						}
						webUserMapper.updateByPrimaryKey(user);
					} else {
						logger.info("系统出现徐错误！");
					}
					logger.info("表示有3个上家，如何分红");
					RecommendFriend recommend3 = new RecommendFriend();
					recommend2.setRecommendedId(rFriend2.getRecommendId());
					RecommendFriend rFriend3 = recommendFriendMapper.selectBy(recommend3);
					if (rFriend3 != null) {
						RedEnvelope redEnvelope3 = new RedEnvelope();
						redEnvelope3.setUserId(rFriend.getRecommendId());
						redEnvelope3.setType(1);// 1表示分红所得红包
						redEnvelope3.setMoney(pPrice * p);
						redEnvelope3.setAnswerTime(new Date());
						redEnvelopeMapper.insert(redEnvelope3);
						WebUser webUser3 = webUserMapper
								.selectByPrimaryKey(rFriend.getRecommendId());
						if (webUser3 != null) {
							WebUser user = new WebUser();
							user.setId(webUser3.getId());
							if (webUser3.getMoney() != null) {
								user.setMoney(webUser3.getMoney() + new Float(pPrice * p));
							} else {
								user.setMoney(new Float(pPrice * p));
							}
							if (webUser3.getAnswerCount() != null) {
								user.setAnswerCount(webUser3.getAnswerCount() + 1);
							} else {
								user.setAnswerCount(1);
							}
							webUserMapper.updateByPrimaryKey(user);
						} else {
							logger.info("系统出现徐错误！");
						}
						logger.info("表示有4个上家，如何分红");
						RecommendFriend recommend4 = new RecommendFriend();
						recommend2.setRecommendedId(rFriend3.getRecommendId());
						RecommendFriend rFriend4 = recommendFriendMapper.selectBy(recommend4);
						if (rFriend4 != null) {
							RedEnvelope redEnvelope4 = new RedEnvelope();
							redEnvelope4.setUserId(rFriend.getRecommendId());
							redEnvelope4.setType(1);// 1表示分红所得红包
							redEnvelope4.setMoney(pPrice * p);
							redEnvelope4.setAnswerTime(new Date());
							redEnvelopeMapper.insert(redEnvelope4);
							WebUser webUser4 = webUserMapper
									.selectByPrimaryKey(rFriend.getRecommendId());
							if (webUser4 != null) {
								WebUser user = new WebUser();
								user.setId(webUser4.getId());
								if (webUser4.getMoney() != null) {
									user.setMoney(webUser4.getMoney() + new Float(pPrice * p));
								} else {
									user.setMoney(new Float(pPrice * p));
								}
								if (webUser4.getAnswerCount() != null) {
									user.setAnswerCount(webUser4.getAnswerCount() + 1);
								} else {
									user.setAnswerCount(1);
								}
								webUserMapper.updateByPrimaryKey(user);
							} else {
								logger.info("系统出现徐错误！");
							}
							logger.info("表示有5个上家，如何分红");

						} else {
							logger.info("表示有四个上家，分红是否给四个上家");
						}
					} else {
						logger.info("表示有三个上家，分红是否给三个上家");
					}
				} else {
					logger.info("表示有两个上家，分红是否给两个上家");
				}
			} else {
				logger.info("表示有一个上家，分红是否全给一个上家");
			}
		} else {
			logger.info("表示没有上家，不做任何分红改变");
		}
	}
}

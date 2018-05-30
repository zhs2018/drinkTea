package com.wxsoft.drinkTea.platform.weixin.controller;

import java.util.ArrayList;
import java.util.List;

import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.order.mapper.GoodsOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.GoodsOrder;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
/**
 *
 * @author lzj
 * @描述：订单工具类
 * @时间：2017-4-11
 *
 */
public class OrdersUtils {

    public static List<ProductOrder> orderUtils(ProductOrderMapper productOrderMapper,GoodsOrderMapper goodsOrderMapper,ManageProductsMapper manageProductsMapper,ProductOrder pro){
    	List<ProductOrder> orderList = productOrderMapper.getListBy(pro);
		  for (ProductOrder productOrder : orderList) {
			 	    GoodsOrder go = new GoodsOrder();
				      go.setOrderId(productOrder.getId());
				      List<GoodsOrder> gList = goodsOrderMapper.getListBy(go);
				      List<ManageProducts> manageList = new ArrayList<>();
			           for (GoodsOrder goodsOrder : gList) {
			        	   productOrder.setCount(goodsOrder.getGoodsCount());
			        	   ManageProducts mp =manageProductsMapper.selectByPrimaryKey(goodsOrder.getGoodsId());
			                 manageList.add(mp);
					}
			           productOrder.setProducts(manageList);
			}
    	return orderList;
    }
}

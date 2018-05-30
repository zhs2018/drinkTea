package com.wxsoft.drinkTea.platform.system.order.controller;

import java.util.ArrayList;
import java.util.List;

import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.weixin.mapper.UserAddressMapper;
import com.wxsoft.drinkTea.platform.weixin.model.UserAddress;

public class OrderUtils {
	public static List<ProductOrder> getList(List<ProductOrder> list, ProductOrderMapper pm,
			UserAddressMapper userAddressMapper) {
		List<ProductOrder> list1 = new ArrayList<ProductOrder>();
		for (ProductOrder one : list) {
			ProductOrder param = pm.selectByPrimaryKey(one.getId());// 订单编号,商品价格,下单时间,买家留言,订单状态
			UserAddress uAddress = new UserAddress();
			uAddress.setId(one.getAddressId());
			UserAddress userAddress = userAddressMapper.selectBy(uAddress);
			param.setUserAddress(userAddress);
			list1.add(param);
		}

		return list1;
	}
}

package com.wxsoft.drinkTea.platform.system.order.service;

import java.util.List;


import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;

public interface IOrderService {
	 List<ProductOrder> selectProductOrder(ProductOrder productOrder);

	List<ProductOrder> selectByOrderBack(ProductOrder tempCount);

	int selectOrderCountsByStatus(ProductOrder order);

	List<ProductOrder> listPageByOrders(ProductOrder productOrder);

	ProductOrder selectByOrderInfo(ProductOrder os);

	List<ProductOrder> getList(List<ProductOrder> orders);

	ProductOrder getListMess(Integer orderid);

	ProductOrder getListMesses(Integer orderid);
}

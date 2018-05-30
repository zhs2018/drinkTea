   package com.wxsoft.drinkTea.platform.system.order.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.drinkTea.platform.system.login.mapper.ManageProductsMapper;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.system.order.mapper.GoodsOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.mapper.ProductOrderMapper;
import com.wxsoft.drinkTea.platform.system.order.model.GoodsOrder;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;
import com.wxsoft.drinkTea.platform.system.order.service.IOrderService;
import com.wxsoft.drinkTea.platform.weixin.mapper.UserAddressMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.UserAddress;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;
@Service
public class OrderServiceImp implements IOrderService {
	@Autowired
	private GoodsOrderMapper goodsOrderService;

	@Autowired
	private ProductOrderMapper productOrderService;
	@Autowired
	private ManageProductsMapper manageProductsService;
	@Autowired
	private WebUserMapper webUserService;
	@Autowired
	private UserAddressMapper userAddressMapper;


	@Override
	public List<ProductOrder> getList(List<ProductOrder> orders) {
		Double result = 0.0;
		for (int i=0;i<orders.size();i++) {
			UserAddress userAddress =userAddressMapper.selectByPrimaryKey(orders.get(i).getAddressId());
			GoodsOrder goodsOrder = new GoodsOrder();
			goodsOrder.setOrderId(orders.get(i).getId());
			List<GoodsOrder> tab1 = goodsOrderService.getPageListBy(goodsOrder);
			List<ManageProducts> products = new ArrayList<>();
			for (GoodsOrder goodsOrder2 : tab1) {
				ManageProducts product = manageProductsService.selectByPrimaryKey(goodsOrder2.getGoodsId());
				Double price =Double.parseDouble(String.valueOf(product.getNowPrice()));
				 int count = goodsOrder2.getGoodsCount();
                  result =+ price * count;
                  orders.get(i).setPrice(result);
				products.add(product);
			}
			orders.get(i).setProducts(products);
			orders.get(i).setUserAddress(userAddress);

		}
		return orders;
	}



	@Override
	public ProductOrder getListMess(Integer orderid) {
		ProductOrder orders = productOrderService.selectByPrimaryKey(orderid);
		Double result = 0.0;
		WebUser user =webUserService.selectByPrimaryKey(orders.getUserId());
		UserAddress userAddress = userAddressMapper.selectByPrimaryKey(orders.getAddressId());
		GoodsOrder goodsOrder = new GoodsOrder();
		goodsOrder.setOrderId(orders.getId());
		  List<GoodsOrder> tab1 = goodsOrderService.getListBy(goodsOrder);
			List<ManageProducts> products = new ArrayList<>();
			for (GoodsOrder goodsOrder2 : tab1) {
				ManageProducts product = manageProductsService.selectByPrimaryKey(goodsOrder2.getGoodsId());
				Double price =Double.parseDouble(String.valueOf(product.getNowPrice()));
				 int count = goodsOrder2.getGoodsCount();
				  orders.setCount(count);
                  result =+ price * count;
                  orders.setPrice(result);
				products.add(product);
			}
			orders.setProducts(products);
			orders.setWebUser(user);
			orders.setUserAddress(userAddress);
		return orders;
	}

	@Override
	public ProductOrder getListMesses(Integer orderid) {
		ProductOrder orders = productOrderService.selectByPrimaryKey(orderid);
		Double result = 0.0;
		WebUser user =webUserService.selectByPrimaryKey(orders.getUserId());
		UserAddress userAddress = userAddressMapper.selectByPrimaryKey(orders.getAddressId());
		GoodsOrder goodsOrder = new GoodsOrder();
		goodsOrder.setOrderId(orders.getId());
		  List<GoodsOrder> tab1 = goodsOrderService.getListBy(goodsOrder);
			List<ManageProducts> products = new ArrayList<>();
			for (GoodsOrder goodsOrder2 : tab1) {
				ManageProducts product = manageProductsService.selectByPrimaryKey(goodsOrder2.getGoodsId());
				Double price =Double.parseDouble(String.valueOf(product.getNowPrice()==null? 0 : product.getNowPrice() ));
				 int count = goodsOrder2.getGoodsCount();
				  orders.setCount(count);
                  result =+ price * count;
                  orders.setPrice(result);
				products.add(product);
			}
			orders.setProducts(products);
			orders.setWebUser(user);
			orders.setUserAddress(userAddress);
		return orders;
	}


	@Override
	public List<ProductOrder> selectProductOrder(ProductOrder productOrder) {
		return productOrderService.getPageListLike(productOrder);
	}
	@Override
	public List<ProductOrder> selectByOrderBack(ProductOrder tempCount) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int selectOrderCountsByStatus(ProductOrder order) {
		// TODO Auto-generated method stub
		return  productOrderService.selectOrderCountsByStatus(order);
	}
	@Override
	public List<ProductOrder> listPageByOrders(ProductOrder productOrder) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ProductOrder selectByOrderInfo(ProductOrder os) {
		// TODO Auto-generated method stub
		return null;
	}
}

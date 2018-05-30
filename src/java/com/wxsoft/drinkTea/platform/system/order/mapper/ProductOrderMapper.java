package com.wxsoft.drinkTea.platform.system.order.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.drinkTea.platform.system.caiwu.model.DataStatis;
import com.wxsoft.drinkTea.platform.system.order.model.ProductOrder;

/**
 * @文件名称: ProductOrderMapper.java
 * @类路径: com.wxsoft.drinkTea.datemodel.autocreate.mapper.ProductOrderMapper; @作者： @公司：
 */
@Repository
public interface ProductOrderMapper {
	// 根据条件全部查询
	List<ProductOrder> getListBy(ProductOrder clssname);

	// 根据条件分页查询
	List<ProductOrder> getPageListBy(ProductOrder clssname);

	// 查询条数
	int getCountBy(ProductOrder clssname);

	// 根据条件模糊查询全部
	List<ProductOrder> getListLike(ProductOrder clssname);

	// 根据条件模糊分页查询
	List<ProductOrder> getPageListLike(ProductOrder clssname);

	// 退货用查询
	List<ProductOrder> getPageListReturn(ProductOrder record);

	// 查询条数
	int getCountLike(ProductOrder clssname);

	// 根据主键查询实例
	ProductOrder selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	ProductOrder selectBy(ProductOrder po);

	// 根据所需插入
	int insert(ProductOrder record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(ProductOrder record);

	// 根据所需更新
	int updateByPrimaryKey(ProductOrder record);

	// 根据订单编号查询
	ProductOrder selectByOrderNum(ProductOrder recode);

	int selectOrderCountsByStatus(ProductOrder order);

	List<ProductOrder> getPageListByTimeKey(ProductOrder tempCount);

	List<ProductOrder> getPageListByApplyTime(ProductOrder record);

	List<ProductOrder> getPageListByOrderState(ProductOrder recode);

	List<ProductOrder> getPageListByOrderStates(ProductOrder recode);

	List<ProductOrder> getBycount(ProductOrder recode);

	List<ProductOrder> getBycount1(ProductOrder recode);

	List<ProductOrder> getListCountOrder(ProductOrder recode);

	// 查询不同状态订单的数量
	int getOrderCounts(ProductOrder recode);

	// 已完成订单数（完成，及退货失败）
	int getOrdersCounts(ProductOrder recode);

	// 根据状态跟时间查询
	List<ProductOrder> getPageListByOrderSt(ProductOrder recode);

	// 查询订单的每天的总金额
	List<DataStatis> getListByTime(DataStatis recode);

	//根据50状态查询50/80.90订单分页（订单查询）
	List<ProductOrder> getPageListBySt(ProductOrder productOrder);

}
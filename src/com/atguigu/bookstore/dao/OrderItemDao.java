package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;

public interface OrderItemDao {
	
	/**
	 * 向数据库中插入订单项
	 * @param orderItem
	 */
	public void saveOrderItem(OrderItem orderItem);
	
	/**
	 * 批量插入订单项的方法
	 */
	public void batchInsertOrderItems(Object[][] params);
	
	/**
	 * 根据订单号从数据库中查询对应的订单项
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> getOrderItemsByOrderId(String orderId);
}

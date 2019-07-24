package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Order;

public interface OrderDao {
	/**
	 * 将订单插入到数据库中
	 * @param order
	 */
	public void saveOrder(Order order);
	/**
	 *获取数据库中所有的订单
	 */
	public List<Order> getOrders();
	/**
	 * 根据用户的id从数据库中查询出来该用户的所有订单
	 * @param userId
	 * @return
	 */
	public List<Order> getMyOrders(int userId);
	/**
	 * 根据订单号更新订单的状态
	 * @param ordrId
	 * @param state
	 */
	public void updateOrderState(String orderId,int state);
}

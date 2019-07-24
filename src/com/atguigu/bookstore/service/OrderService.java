package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;

public interface OrderService {
	/**
	 * 去结账的方法
	 * @param user
	 * @param cart
	 * @return
	 */
	public String createOrder(User user ,Cart cart);
	/**
	 * 获取所有订单的方法
	 * @return
	 */
	public List<Order> getOrders();
	/**
	 * 根据订单号获取订单项的方法
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> getOrderItemsByOrderId(String orderId);
	/**
	 * 获取用户订单的方法
	 * @param userId
	 * @return
	 */
	public List<Order> getMyOrders(int userId);
	
	/**
	 * 
	 * 
	 * 发货或确认收货
	 */
	public void updateOrderState(String orderId , int state);
}

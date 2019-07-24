package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;

public class OrderTest {

	OrderDao orderDao = new OrderDaoImpl();
	OrderItemDao orderItemDao = new OrderItemDaoImpl();
	
	@Test
	public void test() {
		//创建订单
		Order order = new Order("13838381438", new Date(), 10, 500, 0, 1);
		//创建订单项
		OrderItem orderItem = new OrderItem(null, 2, 200, "少年阿宾", "王鹏翔", 100, "static/img/default.jpg", "13838381438");
		OrderItem orderItem2 = new OrderItem(null, 4, 100, "中年阿宾", "王鹏翔", 25, "static/img/default.jpg", "13838381438");
		OrderItem orderItem3 = new OrderItem(null, 4, 200, "老年阿宾", "王鹏翔", 50, "static/img/default.jpg", "13838381438");
		//保存订单和订单项
		orderDao.saveOrder(order);
		orderItemDao.saveOrderItem(orderItem);
		orderItemDao.saveOrderItem(orderItem2);
		orderItemDao.saveOrderItem(orderItem3);
	}
	

}

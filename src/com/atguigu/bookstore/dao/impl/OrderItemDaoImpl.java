package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

	@Override
	public void saveOrderItem(OrderItem orderItem) {
		//写sql
		String sql = "insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)";
		update(sql, orderItem.getCount(),orderItem.getAmount(),orderItem.getTitle(),orderItem.getAuthor(),orderItem.getPrice(),orderItem.getImgPath(),orderItem.getOrderId());
	}

	@Override
	public void batchInsertOrderItems(Object[][] params) {
		//写sql
		String sql = "insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)";
		batchUpdate(sql, params);		
		
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(String orderId) {

		//写sql
		String sql = "select id,count,amount,title,author,price,img_path imgPath,order_id orderId from order_items where order_id = ?";
		List<OrderItem> beanList = getBeanList(sql, orderId);
		
		return beanList;
	}
	
	
}

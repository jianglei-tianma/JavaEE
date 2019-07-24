package com.atguigu.bookstore.service.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService{
	
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	
	@Override
	public String createOrder(User user,Cart cart) {
		//获取用户id
		Integer id = user.getId();
		//生成订单号
		String orderId = System.currentTimeMillis()+""+id;
		//获取购物车中图书的总数量
		int totalCount = cart.getTotalCount();
		//获取购物车中图书的总金额
		double totalAmount = cart.getTotalAmount();
		
		//封装订单对象
		Order order = new Order(orderId, new Date(), totalCount, totalAmount, 0, id);
		//保存订单
		orderDao.saveOrder(order);
		
		//获取购物项
		List<CartItem> cartItems = cart.getCartItems();
		
		Object[][] orderItemParams = new Object[cartItems.size()][];
		Object[][] bookParams = new Object[cartItems.size()][];
		
		//遍历得到每一个购物项
		for(int i =0;i<cartItems.size();i++){
			CartItem cartItem = cartItems.get(i);
			//获取购物项中图书的数量
			int count = cartItem.getCount();
			//获取购物项中的金额小计
			double amount = cartItem.getAmount();
			//获取购物项中的图书信息
			Book book = cartItem.getBook();
			//获取书名
			String title = book.getTitle();
			//获取作者
			String author = book.getAuthor();
			//获取价格
			double price = book.getPrice();
			//获取封面
			String imgPath = book.getImgPath();
			//封装orderItems对象
			//OrderItem orderItem = new OrderItem(id, count, amount, title, author, price, imgPath, orderId);
			//保存订单项
			//orderItemDao.saveOrderItem(orderItem);
			orderItemParams[i] = new Object[]{count, amount, title, author, price, imgPath, orderId};
			
			//获取图书的库存和销量
			int sales = book.getSales();
			int stock = book.getStock();
			//重新设置图书的库存和销量
			//book.setSales(sales + count);
			//book.setStock(stock - count);
			//更新数据库中图书的库存和销量
			//bookDao.updateBook(book);
			bookParams[i] = new Object[]{sales + count,sales - count,book.getId()};
			
		}
		
		//批量插入订单项
		orderItemDao.batchInsertOrderItems(orderItemParams);
		
		//批量更新图书的库存和销量
		bookDao.batchUpdateSalesAndStock(bookParams);
		
		//结账之后需要清空购物车
		cart.clearCart();
		return orderId;
	}

	@Override
	public List<Order> getOrders() {
		return orderDao.getOrders();
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(String orderId) {	
		return orderItemDao.getOrderItemsByOrderId(orderId);
	}

	@Override
	public List<Order> getMyOrders(int userId) {

		return orderDao.getMyOrders(userId);
	}

	@Override
	public void updateOrderState(String orderId, int state) {
		orderDao.updateOrderState(orderId, state);
		
		
	}

	

}

package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * 后台管理订单
 */
public class OrderManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService = new OrderServiceImpl();
	
	
	//发货的方法
	protected void sendOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取订单号
		String orderId = request.getParameter("orderId");
		//调用orderService的方法发货
		orderService.updateOrderState(orderId, 1);
		//重定向到order_manager.jsp页面
		//response.sendRedirect(request.getContextPath()+"/OrderManagerServlet?method=sendOrder");
		//方式二：重新调用getOrders方法从数据库中再次查询所有的订单
		getOrders(request, response);
		
	}
	
	
	//获取所有订单的方法
	protected void getOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//调用orderService的方法获取所有的订单
		List<Order> orders = orderService.getOrders();
		//将orders放到request域中
		request.setAttribute("orders", orders);
		//转发所有订单的页面
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
		
	} 
	//获取订单项的方法
	protected void getOrderItemsByOrderId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		
		//调用orderService的方法获取对应的订单项
		List<OrderItem> orderItemsByOrderId = orderService.getOrderItemsByOrderId(orderId);
		request.setAttribute("orderItems", orderItemsByOrderId);
		//转发到显示所有订单的页面
		request.getRequestDispatcher("/pages/manager/order_items.jsp").forward(request, response);
		
		
	}
	


}

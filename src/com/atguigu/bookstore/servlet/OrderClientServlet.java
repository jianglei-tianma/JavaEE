package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderClientServlet
 */
public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private  OrderService orderService = new OrderServiceImpl();
	
	
	//确认收货方法
	protected void takeOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取订单号
		String orderId = request.getParameter("orderId");
		//调用orderService的方法更新订单的状态
		orderService.updateOrderState(orderId, 2);
		//调用getMyorders重新查询一下我的订单
		getMyOrders(request, response);
		
		
	}
	
	
	//获取我订单的方法
	protected void getMyOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取Session对象
    	HttpSession session = request.getSession();
    	//获取session域中的保存的用户
    	User user = (User) session.getAttribute("user");
    	Integer id = user.getId();
    	//调用orderservice的方法获取用户的所有订单
    	List<Order> myOrders = orderService.getMyOrders(id);
    	//将myOrders放置到requset域中
    	request.setAttribute("orders", myOrders);
    	//转发到显示我的订单的页面
    	request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
    	
    	
		
	}
	
	
	
	//去结账的方法
    protected void checkout(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//获取Session对象
    	HttpSession session = request.getSession();
    	//获取session域中的保存的用户
    	User user = (User) session.getAttribute("user");
    	Cart cart = (Cart)session.getAttribute("cart");
    	//调用orderService中生成订单的方法
    	String orderId = orderService.createOrder(user,cart);
    	//将订单号放到session域中
    	session.setAttribute("orderId", orderId);
    	//转发到显示订单号的页面
    	//request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request, response);
    	//重定向到显示订单号的页面
    	response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    	
    }

}

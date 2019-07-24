package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.google.gson.Gson;

/**
 * 操作购物车的Servlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();

	// 更新购物项的方法
	protected void updateCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取土图书的id
		String bookId = request.getParameter("bookId");
		//获取用户输入的图书的数量
		String bookCount = request.getParameter("bookCount");
		// 获取Session对象
		HttpSession session = request.getSession();
		// 获取购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			// 更新购物项
			cart.updateCartItem(bookId, bookCount);
			//获取购物车中图书的总数量
			int totalCount = cart.getTotalCount();
			//获取购物车中图书的总金额
			double totalAmount = cart.getTotalAmount();
			//获取Cart类中的额map
			Map<String, CartItem> map = cart.getMap();
			//获取该图书对应的购物项
			CartItem cartItem = map.get(bookId);
			//获取更新之后购物项中图书的金额小计
			double amount = cartItem.getAmount();
			//创建一个Map来放totalCount、totalAmount、amount这三个数据
			Map<String , Object> map2 = new HashMap<>();
			map2.put("totalCount", totalCount);
			map2.put("totalAmount", totalAmount);
			map2.put("amount", amount);
			//创建Gson对象
			Gson gson = new Gson();
			//将map2转换为JSON字符串
			String json = gson.toJson(map2);
			System.out.println(json);
			//将json响应到浏览器
			response.getWriter().write(json);
		}
		// 重定向到购物车页面
//		response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");

	}

	// 删除购物项的方法
	protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取土图书的id
		String bookId = request.getParameter("bookId");
		// 获取Session对象
		HttpSession session = request.getSession();
		// 获取购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			// 删除购物项
			cart.deleteCartItem(bookId);
		}
		// 重定向到购物车页面
		response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
	}

	// 清空购物车的方法
	protected void emptyCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取Session对象
		HttpSession session = request.getSession();
		// 获取购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			// 清空购物车
			cart.clearCart();
		}
		// 重定向到购物车页面
		response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
	}

	// 向购物车中添加图书的方法
	protected void addBook2Cart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取图书的id
		String bookId = request.getParameter("bookId");
		// 调用bookService的方法获取图书的信息
		Book book = bookService.getBookById(bookId);
		// 获取Session对象
		HttpSession session = request.getSession();
		// 获取session域中保存的购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			// 创建一个购物车
			cart = new Cart();
			// 将购物车放到session域中
			session.setAttribute("cart", cart);
		}
		// 将图书添加到购物车中
		cart.addBook2Cart(book);
		
		//获取购物车中的map
		Map<String, CartItem> map = cart.getMap();
		//获取该图书对应的购物项
		CartItem cartItem = map.get(bookId);
		//获取购物项中图书的数量
		int count = cartItem.getCount();
		//获取该图书的库存
		int stock = book.getStock();
		//将现在购物项中图书的数量与图书的库存进行对比
		if(count > stock){
			//将该图书在购物项中的数量设置为最大库存
			cartItem.setCount(stock);
			//设置一个错误消息，并放到session域中
			session.setAttribute("msg", "该图书的库存只有"+stock+"本！");
		}else{
			// 将图书的书名放到session域中
			session.setAttribute("bookTitle", book.getTitle());
		}
		// 获取请求头中的Referer属性值
		String header = request.getHeader("Referer");
		// 重定向到header
		// response.sendRedirect(request.getContextPath()+"/index.jsp");
		response.sendRedirect(header);
	}

}

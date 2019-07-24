package com.atguigu.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

/**
 * 前台管理图书的Servlet
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();

	// 获取带价格范围和分页的图书信息的方法
	protected void getPageBooksByPrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取页码和价格范围
		String minPrice = request.getParameter("min");
		String maxPrice = request.getParameter("max");
		// 获取页码
		String pageNo = request.getParameter("pageNo");
		// 调用bookService的方法获取带价格范围和分页的图书信息的方法
		Page<Book> pageBooksByPrice = bookService.getPageBooksByPrice(pageNo, minPrice, maxPrice);
		//将pageBooksByPrice对象放到request域中
		request.setAttribute("page", pageBooksByPrice);
//		request.setAttribute("minPrice", minPrice);
//		request.setAttribute("maxPrice", maxPrice);
		// 转发到显示所有图书的页面
		request.getRequestDispatcher("/pages/client/books.jsp").forward(request, response);
	}

	// 获取带分页图书信息的方法
	protected void getPageBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取页码
		String pageNo = request.getParameter("pageNo");
		// 调用bookService中分页的方法
		Page<Book> pageBooks = bookService.getPageBooks(pageNo);
		// 将pageBooks对象放到request域中
		request.setAttribute("page", pageBooks);
		// 转发到显示所有图书的页面
		request.getRequestDispatcher("/pages/client/books.jsp").forward(request, response);
	}

}

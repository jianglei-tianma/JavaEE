package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

/**
 * 后台管理图书的Servlet
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();

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
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}

	// 更新或者添加图书的方法
	protected void updateOrSaveBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取图书的id及修改之后的图书的信息
		String bookId = request.getParameter("bookId");
		// 获取图书信息
		String title = request.getParameter("book_name");
		String author = request.getParameter("book_author");
		String price = request.getParameter("book_price");
		String sales = request.getParameter("book_sales");
		String stock = request.getParameter("book_stock");
		if ("".equals(bookId)) {
			// 证明在添加图书
			// 封装Book对象
			Book book = new Book(null, title, author, Double.parseDouble(price), Integer.parseInt(sales),
					Integer.parseInt(stock));
			// 调用bookService的方法将图书插入到数据库中
			bookService.saveBook(book);
		} else {
			// 证明在更新图书
			// 封装Book对象
			Book book = new Book(Integer.parseInt(bookId), title, author, Double.parseDouble(price),
					Integer.parseInt(sales), Integer.parseInt(stock));
			// 调用bookService的 方法更新图书信息
			bookService.updateBook(book);
		}
		// 调用getBooks方法再次查询一遍所有的图书
		getPageBooks(request, response);
	}

	// 获取图书的方法
	protected void getBookById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取图书的id
		String bookId = request.getParameter("bookId");
		// 调用bookService的方法获取该图书的信息
		Book book = bookService.getBookById(bookId);
		// 将图书放到request域中
		request.setAttribute("book", book);
		// 转发到修改图书的页面
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}

	// 删除图书的方法
	protected void deleteBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取要删除的图书的id
		String bookId = request.getParameter("bookId");
		// 调用bookService的方法删除图书
		bookService.deleteBookById(bookId);
		// 调用getBooks方法再次从数据库中查询一遍
		getPageBooks(request, response);
	}

	// 添加图书的方法
	// protected void saveBook(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// // 获取图书信息
	// String title = request.getParameter("book_name");
	// String author = request.getParameter("book_author");
	// String price = request.getParameter("book_price");
	// String sales = request.getParameter("book_sales");
	// String stock = request.getParameter("book_stock");
	// // 封装Book对象
	// Book book = new Book(null, title, author, Double.parseDouble(price),
	// Integer.parseInt(sales),
	// Integer.parseInt(stock));
	// // 调用bookService的方法将图书插入到数据库中
	// bookService.saveBook(book);
	// // 方式一：重定向到显示所有图书的方法
	// //
	// response.sendRedirect(request.getContextPath()+"/BookManagerServlet?method=getBooks");
	// // 方式二：调用获取所有图书的方法再查一次数据库
	// getBooks(request, response);
	// }

	// 获取所有图书的方法
//	protected void getBooks(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// 调用bookServcie的获取所有图书的方法获取所有的图书
//		List<Book> books = bookService.getBooks();
//		// 将books放到request域中
//		request.setAttribute("books", books);
//		// 转发到显示所有图书的页面
//		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
//
//	}

}

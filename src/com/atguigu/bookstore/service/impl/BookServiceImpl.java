package com.atguigu.bookstore.service.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;

public class BookServiceImpl implements BookService {

	BookDao bookDao = new BookDaoImpl();

	@Override
	public List<Book> getBooks() {
		return bookDao.getBooks();
	}

	@Override
	public void saveBook(Book book) {
		bookDao.saveBook(book);
	}

	@Override
	public void deleteBookById(String bookId) {
		bookDao.deleteBookById(bookId);
	}

	@Override
	public Book getBookById(String bookId) {
		return bookDao.getBookById(bookId);
	}

	@Override
	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}

	@Override
	public Page<Book> getPageBooks(String pageNo) {
		// 创建Page对象
		Page<Book> page = new Page<>();
		// 设置一个默认的页码
		int defaultPageNo = 1;
		try {
			defaultPageNo = Integer.parseInt(pageNo);
		} catch (Exception e) {
		}
		// 将页面设置到page对象中
		page.setPageNo(defaultPageNo);
		return bookDao.getPageBooks(page);
	}

	@Override
	public Page<Book> getPageBooksByPrice(String pageNo, String minPrice, String maxPrice) {
		// 创建Page对象
		Page<Book> page = new Page<>();
		// 设置一个默认的页码
		int defaultPageNo = 1;
		// 设置一个默认的价格范围
		double defaultMinPrice = 0;
		double defaultMaxPrice = Double.MAX_VALUE;
		try {
			defaultPageNo = Integer.parseInt(pageNo);
		} catch (Exception e) {
		}
		try {
			defaultMinPrice = Double.parseDouble(minPrice);
		} catch (Exception e) {
		}
		try {
			defaultMaxPrice = Double.parseDouble(maxPrice);
		} catch (Exception e) {
		}
		// 将页面设置到page对象中
		page.setPageNo(defaultPageNo);
		return bookDao.getPageBooksByPrice(page, defaultMinPrice, defaultMaxPrice);
	}

}

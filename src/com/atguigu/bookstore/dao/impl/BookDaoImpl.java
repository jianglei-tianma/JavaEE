package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public List<Book> getBooks() {
		// 写sql语句
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from books";
		// 调用BaseDao中获取一个集合的方法
		List<Book> beanList = getBeanList(sql);
		return beanList;
	}

	@Override
	public void saveBook(Book book) {
		// 写sql语句
		String sql = "insert into books(title,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
		update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(),
				book.getImgPath());
	}

	@Override
	public void deleteBookById(String id) {
		String sql = "delete from books where id = ?";
		update(sql, id);
	}

	@Override
	public Book getBookById(String bookId) {
		// 写sql语句
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from books where id = ?";
		Book bean = getBean(sql, bookId);
		return bean;
	}

	@Override
	public void updateBook(Book book) {
		// 写slq语句
		String sql = "update books set title = ?,author=?,price=?,sales=?,stock=? where id=?";
		update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getId());
	}

	@Override
	public Page<Book> getPageBooks(Page<Book> page) {
		// 获取图书的总记录数
		String sql1 = "select count(*) from books";
		long count = (long) getSingleValue(sql1);
		// 将总记录数设置到page对象中
		page.setTotalRecord((int) count);

		String sql2 = "select id,title,author,price,sales,stock,img_path imgPath from books limit ?,?";
		List<Book> beanList = getBeanList(sql2, (page.getPageNo() - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
		// 将集合设置到page对象中
		page.setList(beanList);
		return page;
	}

	@Override
	public Page<Book> getPageBooksByPrice(Page<Book> page, double minPrice, double maxPrice) {
		// 获取图书的总记录数
		String sql1 = "select count(*) from books where price between ? and ?";
		long count = (long) getSingleValue(sql1,minPrice,maxPrice);
		// 将总记录数设置到page对象中
		page.setTotalRecord((int) count);

		String sql2 = "select id,title,author,price,sales,stock,img_path imgPath from books where price between ? and ? limit ?,?";
		List<Book> beanList = getBeanList(sql2, minPrice,maxPrice , (page.getPageNo() - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
		// 将集合设置到page对象中
		page.setList(beanList);
		return page;
	}

	@Override
	public void batchUpdateSalesAndStock(Object[][] params) {
		//写sql
		String sql ="update books set sales = ? ,stock = ? where id = ? ";
		batchUpdate(sql, params);
		
	}

}

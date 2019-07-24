package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public interface BookDao {

	/**
	 * 从数据库中查询出所有的图书
	 * 
	 * @return
	 */
	public List<Book> getBooks();

	/**
	 * 将图书插入到数据库中
	 * 
	 * @param book
	 */
	public void saveBook(Book book);

	/**
	 * 根据图书的id从数据库中删除图书
	 * 
	 * @param id
	 */
	public void deleteBookById(String id);

	/**
	 * 根据图书的id从数据库中获取该图书
	 * 
	 * @param bookId
	 * @return
	 */
	public Book getBookById(String bookId);

	/**
	 * 根据图书的id更新数据库中图书的信息
	 * 
	 * @param book
	 */
	public void updateBook(Book book);

	/**
	 * 获取带分页的图书信息
	 * 
	 * @param page
	 *            传入的是一个只有pageNo属性的page对象
	 * @return 返回的是一个包含所有属性的page对象
	 */
	public Page<Book> getPageBooks(Page<Book> page);

	/**
	 * 获取带价格范围及带分页的图书信息
	 * 
	 * @param page
	 *            传入的是一个只有pageNo属性的page对象
	 * @return 返回的是一个包含所有属性的page对象
	 */
	public Page<Book> getPageBooksByPrice(Page<Book> page, double minPrice, double maxPrice);
	
	/**
	 * 批量更新图书的库存和销量
	 * @param params
	 */
	public void batchUpdateSalesAndStock(Object[][] params);

}

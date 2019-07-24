package com.atguigu.bookstore.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;

public class BookDaoTest {

	BookDao bookDao = new BookDaoImpl();
	
	@Test
	public void testGetBooks() {
		List<Book> books = bookDao.getBooks();
		for (Book book : books) {
			System.out.println(book);
		}
	}
	
	@Test
	public void testSaveBook(){
		Book book = new Book(null, "欢乐颂", "你定吧", 66.66, 1000, 5);
		bookDao.saveBook(book );
	}
	
	@Test
	public void testGetBookById(){
		Book bookById = bookDao.getBookById("40");
		System.out.println(bookById);
	}

	@Test
	public void testUpdateBook(){
		Book book = new Book(40, "狼牙山五烈士", "作者", 88.88, 150, 350);
		bookDao.updateBook(book);
	}
	
	@Test
	public void testGetPageBooks(){
		//创建一个page对象
		Page<Book> page = new Page<>();
		//设置一个页码
		page.setPageNo(5);
		Page<Book> pageBooks = bookDao.getPageBooks(page);
		System.out.println("总记录数是："+pageBooks.getTotalRecord());
		System.out.println("总页数是："+pageBooks.getTotalPageNo());
		System.out.println("当前页是："+pageBooks.getPageNo());
		System.out.println("集合中的图书有：");
		List<Book> list = pageBooks.getList();
		for (Book book : list) {
			System.out.println(book);
		}
		
	}
}

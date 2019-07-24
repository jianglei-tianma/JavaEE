package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车中购物项类
 * @author tianma
 *
 */
public class CartItem  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Book book;//图书信息
	private int count;//购物项中图书的数量
	private double amount;//购物项中图书的金额小计
	
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//图书的金额小计是由图书的价格和图书的数量计算得到
	public double getAmount() {
		BigDecimal bigCount = new BigDecimal(count+"");
		BigDecimal bigBookPrice = new BigDecimal(book.getPrice()+"");
		
		return bigCount.multiply(bigBookPrice).doubleValue();
	}
	
	
}

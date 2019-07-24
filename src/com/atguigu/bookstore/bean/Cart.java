package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车类
 * @author tianma
 *
 */
public class Cart implements Serializable {
	

	private static final long serialVersionUID = 1L;
	//定义一个map来保存购物车中的购物项，map的key是图书的id，map的value是cartItem对象
	private Map<String,CartItem> map = new LinkedHashMap<>();
	private int totalCount; //购物车中图书的总数量，通过计算得到
	private double totalAmount;//购物车中图书的总金额，也是通过计算得到
	
	//将图书添加到购物车的方法
	public void addBook2Cart(Book book){
		//根据图书的id获取购物车总对应的购物项
		CartItem cartItem = map.get(book.getId()+"");
		//如果购物车中还没有该购物项，即cartItem为null
		if(cartItem == null){
			//证明该购物车中还没有该购物项
			cartItem = new CartItem();
			//设置图书信息
			cartItem.setBook(book);
			//设置该购物项中图书的数量为1
			cartItem.setCount(1);
			//将cartItem添加到购物车中
			map.put(book.getId()+"", cartItem);
			
		}else{
			//证明该购物车中已经有该购物项，这时只需要在之前数量的基础上加1即可
			cartItem.setCount(cartItem.getCount()+1);
		}
		
		
	}
	
	
	//更新购物项的方法
	public void updateCartItem(String bookId,String bookCount){
		//获取要更新的购物项
		CartItem cartItem = map.get(bookId);
		//将String类型的图书的数量转换为int类型
		try {
			int parseInt = Integer.parseInt(bookCount);
			//转换之后的数量大于0的时候再更新购物项中的图书的数量
			if(parseInt > 0){
				cartItem.setCount(parseInt);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//清空购物车的方法
	public void clearCart(){
		map.clear();
	}
	//删除购物项的方法
	public void deleteCartItem(String bookId){
		map.remove(bookId);
	}
	
	//获取购车车中所有购物项的方法
	public List<CartItem> getCartItems(){
		Collection<CartItem> values = map.values();
		return new ArrayList<CartItem>(values);
	}
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	//购物车中图书的总数量是各个购物项中的图书的数量之和
	public int getTotalCount() {
		int totalCount = 0;
		//获取所有的购物项
		List<CartItem> cartItems = getCartItems();
		for (CartItem cartItem : cartItems) {
			totalCount +=cartItem.getCount();
			
		}

		return totalCount;
	}
	//购物车中图书的总金额，是各个购物项中的图书的金额小计之和
	public double getTotalAmount() {
		
		//double totalAmount = 0;
		BigDecimal totalAmount = new BigDecimal("0");
		//获取所有的购物项
		List<CartItem> cartItems = getCartItems();
		for (CartItem cartItem : cartItems) {
//			totalAmount +=cartItem.getAmount();
			BigDecimal bigAmout = new BigDecimal(cartItem.getAmount()+"");
			totalAmount = totalAmount.add(bigAmout);
			
		}

		return totalAmount.doubleValue();
	}

}

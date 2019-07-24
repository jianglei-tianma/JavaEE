package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;

public class TestUserDaoImpl {
	private UserDao dao = new UserDaoImpl();
	@Test
	public void test() {
		//测试插入数据
		//boolean b = dao.saveUser(new User(null, "zhangdali", "maxiaohui", "zm@zm.com"));
		//System.out.println(b);
	}
	@Test
	public void test1() {
		//测试查询
		User u = dao.getUserByUsernameAndPassword(new User(null, "zhangdali", "maxiaohui",null));
		System.out.println(u);
	}

}

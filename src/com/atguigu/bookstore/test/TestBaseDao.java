package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BaseDao;

public class TestBaseDao {

	@Test
	public void test() {
		UserDao dao = new UserDao();
	}

	class UserDao extends BaseDao<User>{
		
	}
	
}

package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao dao = new UserDaoImpl();
	/**
	 * 实现登录的业务逻辑
	 */
	@Override
	public User login(User user) {
		return dao.getUserByUsernameAndPassword(user);
	}
	/**
	 * 实现注册的业务逻辑
	 */
	@Override
	public boolean regist(User user) {
		return dao.checkUserName(user);
	}
	@Override
	public void saveUser(User user) {
		dao.saveUser(user);
	}
}

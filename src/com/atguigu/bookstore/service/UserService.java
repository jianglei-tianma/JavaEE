package com.atguigu.bookstore.service;

import com.atguigu.bookstore.bean.User;

public interface UserService {
	/**
	 * 登录操作的业务逻辑
	 * @param user
	 * @return
	 */
	User login(User user);
	/**
	 * 注册操作的业务逻辑
	 * @param user
	 * @return
	 */
	boolean regist(User user);
	/**
	 * 保存用户的方法
	 * @param user
	 */
	void saveUser(User user);
}

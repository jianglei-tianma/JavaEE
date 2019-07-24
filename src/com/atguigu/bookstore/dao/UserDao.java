package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.bean.User;

/**
 * 规范Usr表操作的需求
 * @author Administrator
 *
 */
public interface UserDao {
	
	/**
	 * 根据账号密码查询指定用户
	 * @param user
	 * @return
	 */
	User getUserByUsernameAndPassword(User user);
	/**
	 * 用户注册
	 * 	向数据库插入一个用户信息
	 * @param user
	 * @return true 用户名可用  false 用户名已经存在
	 */

	boolean checkUserName(User user);
	/**
	 * 向数据库中插入用户的方法   
	 * @param user
	 */
	void saveUser(User user);
	
}

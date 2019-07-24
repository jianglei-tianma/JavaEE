package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.UserDao;
/**
 * user表的具体操作
 * @author Administrator
 *
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao{
	
	/**
	 * 根据账号密码查询指定用户
	 * 	返回值
	 * 		user对象不为null代表查询到了
	 */
	@Override
	public User getUserByUsernameAndPassword(User user) {
		String sql = "SELECT id,username,password,email FROM bs_user WHERE username=? AND password=?";
		return this.getBean(sql, user.getUsername(),user.getPassword());
	}
	/**
	 * 用户注册向数据库插入一条记录
	 * 	返回值
	 * 		true表示插入成功
	 */
	@Override
	public void saveUser(User user) {
		String sql = "INSERT INTO users(username,password,email) VALUES(?,?,?)";
		update(sql, user.getUsername(),user.getPassword(),user.getEmail());
	}
	@Override
	public boolean checkUserName(User user) {
		String sql = "SELECT id,username,password,email FROM bs_user WHERE username=? ";
		User bean = getBean(sql,user.getUsername());
		
		return bean == null;
	}

}

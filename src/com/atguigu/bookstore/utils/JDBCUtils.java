package com.atguigu.bookstore.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	//c3p0获取数据库连接   参数就是  哪套配置参数
	//创建了datasource一定要检查配置文件中连接的是哪个数据库
	private static DataSource source = new ComboPooledDataSource("webDataSource");
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	
	
	/**
	 * 获取数据库连接的方法
	 * @return
	 */
	/*public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}*/
	
	public static Connection getConnection(){
		//从当前线程中获取连接
		Connection connection = threadLocal.get();
		if(connection == null){
			//从数据库连接池中获取一个连接
			try {
				connection = source.getConnection();
				//将连接设置到当前线程中
				threadLocal.set(connection);
			} catch (Exception e) {

				e.printStackTrace();
			}
			
		}
		return connection;
	}
	/**
	 * 释放数据库连接
	 * @param conn
	 */
	/*public static void releaseConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}*/
	
	public static void releaseConnection(){
		
		//从当前线程中获取连接
		Connection connection = threadLocal.get();
		//关闭连接
		try {
			connection.close();
			//将关闭的连接从当前线程中移除
			threadLocal.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}

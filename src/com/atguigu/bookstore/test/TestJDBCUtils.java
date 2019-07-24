package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.atguigu.bookstore.utils.JDBCUtils;

public class TestJDBCUtils {

	@Test
	public void test() {
		Connection connection = JDBCUtils.getConnection();
		System.out.println(connection);
		//关闭数据库连接
		JDBCUtils.releaseConnection();
	}

}

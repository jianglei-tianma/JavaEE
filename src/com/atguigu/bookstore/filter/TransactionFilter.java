package com.atguigu.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.utils.JDBCUtils;

/**
 * 管理事务的Filter
 */
public class TransactionFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//获取连接
		Connection connection = JDBCUtils.getConnection();
		try {
			//开启事务
			connection.setAutoCommit(false);
			//放行请求
			chain.doFilter(request, response);
			//没有出现异常，提交事务
			connection.commit();
		} catch (Exception e) {
			try {
				//出现异常，回滚事务
				connection.rollback();
				//重定向到错误页面
				response.sendRedirect(request.getContextPath()+"/pages/error/error.jsp");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			//关闭连接
			JDBCUtils.releaseConnection();
		}
		
	}

}

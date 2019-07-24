package com.atguigu.bookstore.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class EncodingFilter
 */
public class EncodingFilter extends HttpFilter implements Filter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//获取FilterConfig对象
		FilterConfig filterConfig2 = getFilterConfig();
		//获取ServletContext对象
		ServletContext servletContext = filterConfig2.getServletContext();
		//获取当前Web应用的初始化参数
		String encoding = servletContext.getInitParameter("encoding");
		//设置字符集为encoding
		request.setCharacterEncoding(encoding);
		//放行请求
		chain.doFilter(request, response);
		
	}


}
